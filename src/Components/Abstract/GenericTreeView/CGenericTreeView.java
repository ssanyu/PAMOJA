/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.GenericTreeView;


import Components.INodeObject;
import Nodes.CNode;
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * A tree view for displaying and inspecting all kinds of PAMOJA-trees (such as parse trees, abstract syntax trees, and box trees).
 * <p>
 * It may be connected to any PAMOJA component which holds or generates data whose internal structure is in form of a tree. 
 * Examples of these PAMOJA components are: Parsers, Grammar, Signature, ParseTree,
 * Abstractor, AST, Patterns and AST2BoxTree. It observes such components for a change in the tree and updates its tree view.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CGenericTreeView extends JTree implements PropertyChangeListener{
    
    /**
     * A reference to <code>NodeObject</code> object.
     */
       public INodeObject NodeObject;

      //  public IParser Parser=null;
       DefaultTreeModel tr;
       /**
        * Creates a new instance of <code> CGenericTreeView</code>.
        */
    public CGenericTreeView(){
           super();
           this.setBorder(new BevelBorder(BevelBorder.RAISED));
           setCellRenderer(new MyTreeCellRenderer());
           updateTreeView(null);
    }
    /**
     * Enables a GenericTreeView to access a component implementing <code>INodeObject</code>.
     *
     * @return the INodeObject interface.
     */
    public INodeObject getNodeObject(){
        return NodeObject;
    }
    /**
     * Connect to a component holding a node object, via its interface.
     * Register for property change events, retrieve the current value of a node (tree) and update the tree view.
     *
     * @param aNodeObject new value of NodeObject
     */
   public void setNodeObject(INodeObject aNodeObject){
       CNode vNode=null;
       if(NodeObject!=null){
              NodeObject.removePropertyChangeListener(this);
       }
       NodeObject=aNodeObject;
       if(NodeObject!=null){
              NodeObject.addPropertyChangeListener(this);
              vNode=NodeObject.getNode();
       } 
       updateTreeView(vNode);
   }
    
    /**
     * Maps a given instance of a PAMOJA <code>CNode</code> to a Java's DefaultMutableTreeNode.
     * <p>
     * DefaultMutableTreeNode provides operations for examining and modifying a node's parent and children and 
     * also operations for examining the tree that the node is a part of. 
     * 
     * @param aNode the CNode object.
     * @return a DefaultMutableTreeNode object corresponding to CNode.
     */
    public DefaultMutableTreeNode mapCNodeTreeToTreeModel(CNode aNode){
        if (aNode == null){
            return new DefaultMutableTreeNode("nil");
        }else{
            DefaultMutableTreeNode result = new DefaultMutableTreeNode(aNode);
            // Recursively build subtrees
            for (int i = 0; i < aNode.count(); i++){
                result.add(mapCNodeTreeToTreeModel(aNode.getNode(i)));
            }
            return result;
        }
     }
    
    /**
     * Updates the GenericTreeview with the given instance of CNode.
     * 
     * @param aNode the CNodeObject.
     */
    public void updateTreeView(CNode aNode){
          // Build a TreeModel 
          DefaultMutableTreeNode root = mapCNodeTreeToTreeModel(aNode);
          tr = new DefaultTreeModel(root);
          // Set the model just built as model of genericTreeView
          setModel(tr);
          expandAll();
    }
    
    /**
     * Adjusts the focus to the specified node in the tree.
     * 
     * @param aNode the DefaultMutableTreeNode object. 
     */
    public void adjustFocus(DefaultMutableTreeNode aNode){
         this.setSelectionPath(new TreePath(tr.getPathToRoot(aNode)));
    }
    
    /**
     * Handles property change events. If the property change is from a component containing a node object, retrieves its node object, and updates its view. 
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        if(evt.getSource()==NodeObject){
            
            updateTreeView(NodeObject.getNode());
        }
        
    }

    /**
     * Expands all nodes in the tree.
     */
    public void expandAll(){
       for (int i = 0; i < this.getRowCount(); i++) {
           this.expandRow(i);
       }
   }

    /**
     * Resets the GenericTreeView.
     */
    public void clear(){
       updateTreeView(null);
   }
   
    /**
     * Paints nodes of the tree in different colors.
     */
    public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);

         // Assuming you have a tree of Strings
        String node = ((DefaultMutableTreeNode) value).toString();
        
        // If the node is a leaf and ends with ">"
        if (leaf && node.endsWith(">")) {
            //Paint the node in red
            setForeground(Color.RED);
            
        }
        return this;
    }
} 

 }
    
