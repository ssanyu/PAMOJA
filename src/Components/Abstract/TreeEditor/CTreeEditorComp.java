/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.TreeEditor;

import Components.CPAMOJAComponent;
import Components.INodeObject;
import Components.Specifications.Language.ILanguageComp;
import Nodes.CNode;
import Nodes.CNodeFactory;
import Nodes.CNodeKind;
import Nodes.INodeList;
import Signatures.CSignature;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;



/**
 * A non-visual component with basic operations for editing the different kinds of nodes of ASTs. For instance, deleting, removing, 
 * replacing, insert before and insert after. It uses the signature specifications
 * part of a <code>Language</code> component, to determine allowable templates for
 * replacing a focused node in the AST and a node factory to generate a
 * node corresponding to a particular template.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CTreeEditorComp extends CPAMOJAComponent implements ITreeEditorComp,INodeObject,PropertyChangeListener{
    
    private ILanguageComp language;
    private CNodeFactory nodeFactory;
    
    private CNode tree;
    private CNode focus;
    private CTreeEngine  treeEngine;
    private String acceptableFocusSort;
    private HashMap<String,CNode> templates;
    private boolean treeEditing;

    /**
     * Creates an instance of a TreeEditor.
     */
    public CTreeEditorComp() { 
       tree=null;
       focus=null;
       treeEditing=true;
       treeEngine=new CTreeEngine();
       acceptableFocusSort=new String();
       templates=new HashMap<String,CNode>();
    }
    
     /**
     * Paints the UI of the TreeEditor component object. 
     * 
     * @param g the TreeEditor component object to paint.
     */
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("TreeEditor",10, 10);
    }

    /**
     * Determines if the TreeEditor is currently in editing mode.
     * 
     * @return <code>true</code> if in editing mode, else <code> false</code>.
     */
    public boolean isTreeEditing() {
        return treeEditing;
    }

    /**
     * Sets the editing state of a tree editor to the specified value.
     * 
     * @param treeEditing indicates whether this TreeEditor component is in editing mode. 
     */
    public void setTreeEditing(boolean treeEditing) {
        this.treeEditing = treeEditing;
    }
    
    /**
     * Returns the root of the AST.
     * 
     * @return the root.
     */
    @Override
    public CNode getTree(){
        return tree;
    }

    /**
     * Sets the root of the AST and notifies its observers.
     * 
     * @param tree the root.
     */
        @Override
    public void setTree(CNode tree) {
        // keep the old value
        CNode oldTree=this.tree;
       // save the new value
        this.tree=tree;
        if (!tree.equals(oldTree)) {
         treeEngine.setTree(tree);
        }
        // fire the property change event, with a property that has changed
        support.firePropertyChange("tree",oldTree,this.tree);
       
    }

     /**
     * Returns a focus in the AST.
     * 
     * @return the focus.
     */
    
    @Override
    public CNode getFocus(){
        return focus;
    }

    /**
     * Sets the focus in the AST.
     * 
     * @param focus the focused node.
     */
        @Override
    public void setFocus(CNode focus) {
        this.focus=focus;
    }    

    /**
     * Returns a NodeFactory object associated with this tree editor.
     * 
     * @return CNodeFactory object.
     */
    public CNodeFactory getNodeFactory() {
        return nodeFactory;
    }
    
   /**
     * Sets the NodeFactory to be used by the tree editor.
     * 
     * @param aNodeFactory the nodefactory to be set.
     */
        public void setNodeFactory(CNodeFactory aNodeFactory){
        nodeFactory=aNodeFactory;
    }

    /**
     * Returns a table of language-construct templates allowable at the focused node.
     * 
     * @return a HashMap of templates.
     */
    public HashMap<String, CNode> getTemplates() {
        return templates;
    }

    /**
     * Sets the given HashMap of language-construct templates allowable at a focused node.
     * 
     * @param templates the given HashMap of templates.
     */
    public void setTemplates(HashMap<String, CNode> templates) {
        this.templates = templates;
    }

    /**
     * Returns an AcceptableFocusSort.
     * 
     * @return AcceptableFocusSort in text form.
     */
    @Override
    public String getAcceptableFocusSort() {
        return acceptableFocusSort;
    }

    /**
     * Sets an AcceptableFocusSort.
     * 
     * @param acceptableFocusSort the AcceptableFocusSort to be set.
     */
    public void setAcceptableFocusSort(String acceptableFocusSort) {
        this.acceptableFocusSort = acceptableFocusSort;
    }
        
   /**
     * Access a Language component via its interface.
     *
     * @return the Language interface
     *
     */
    @Override
    public ILanguageComp getLanguage(){
        return language;
    }
    /**
     * Connects to a Language component via its interface. Registers for
     * property change events and retrieves current value of a signature.
     * 
     * @param aLanguage an instance of the Language component.
     */
        @Override
    public void setLanguage(ILanguageComp aLanguage){
        CSignature vSignatureStructure;
       if(language!=null){
              language.removePropertyChangeListener(this);
       }
       language=aLanguage;
       if(language!=null){
              language.addPropertyChangeListener(this);
              vSignatureStructure=language.getSignatureComp().getSignatureStructure();

       } else {
            vSignatureStructure=new CSignature();
       }
       treeEngine.setSignature(vSignatureStructure);
    } 
    
    /**
     * Updates the AST of the tree editor with the given AST.
     * 
     * @param aNode the CNode object.
     */
    public void updateTree(CNode aNode){
        treeEngine.setTree(aNode);
        setTree(treeEngine.getRoot());
        setFocus(treeEngine.getFocus());
        updateFocus();
    }
    
    /**
     * Updates the focused node in the AST.
     * 
     * @param aNode the focused node.
     */
    public void updateFocus(CNode aNode){
        treeEngine.setFocus(aNode);
        setTree(treeEngine.getRoot());
        setFocus(treeEngine.getFocus());
        updateFocus();
    }
    
    /**
     * Replace the focus with the given node.
     * 
     * @param aNode the node to replace a focus.
     */
    @Override
    public void replaceSubTree(CNode aNode){
        if(isTreeEditing() && aNode!=null){
            treeEngine.replace(aNode,treeEngine.getFocus());
            treeEngine.setFocus(aNode);
            updateTree();
            updateFocus();
        }
    }
    
    /**
     * Replace the focus with the given node.
     * 
     * @param aNode the node to replace a focus.
     */
    @Override
    public void replace(CNode aNode){
        if(isTreeEditing() && aNode!=null){
            insertFirstItemIntoLists(aNode);
            treeEngine.replace(aNode,treeEngine.getFocus());
            treeEngine.setFocus(aNode);
            updateTree();
            updateFocus();
        }
    }

    /**
     * Replaces the data of the specified node with the specified data.
     * 
     * @param aNode the CNode object.
     * @param aData a String of data.
     */
    @Override
    public void replace(CNode aNode, String aData){
        if(isTreeEditing() && aNode!=null && !aData.isEmpty()){
            aNode.setData(0, aData);
            treeEngine.replace(aNode,treeEngine.getFocus());
            treeEngine.setFocus(aNode);
            updateTree();
            updateFocus();
        }
    }

    /**
     * Updates the AST and focus of this tree editor.
     * 
     * 
     */
    public void updateTree(){
        setTree(treeEngine.getRoot()); 
        setFocus(treeEngine.getFocus());
        
    }
    
    /**
     * Returns a table of language-construct templates allowable for the specified node with the given sort.
     * 
     * @param aNode the CNode object.
     * @param aSort the sort name
     * @return a table of language-construct templates allowable
     */
    public HashMap<String,CNode>getTemplates(CNode aNode,String aSort){
       HashMap<String,CNode>vTemplates=new HashMap<String,CNode>();
       CNode vNode=null;
       if(aNode.kind()==CNodeKind.LIST){
              if(nodeFactory!=null){
                    for(Map.Entry<String,Class> pattern :nodeFactory.getfLabeltoClass().entrySet()){
                        vNode=nodeFactory.makeNodeWithHoles(pattern.getKey());
                        
                        if(vNode!=null){
                            
                            if(treeEngine.canAdd(vNode, aNode)){
                                if(pattern.getKey().equals(aSort)){
                                  //System.out.println(pattern.getKey() +" "+treeEngine.acceptableFocusSort());
                                  
                                }else{
                                vTemplates.put(pattern.getKey(),vNode);
                                }
                            }
                        }
                    }
                    
               }
       }
        return vTemplates;
    }
    /**
     * Update information about the focus. This includes:
     * <ul>
     * <li> Acceptable focus sort.</li>
     * <li> Acceptable templates.</li>
     * </ul>
     */
     private void updateFocus(){
         CNode vNode=null;
         HashMap<String,CNode> vTemplates;
       //currentSort=treeEngine.getFocus().sortLabel();
       switch((treeEngine.getFocus()).kind()){
           case HOLE:
           case TUPLE:
                acceptableFocusSort=treeEngine.acceptableFocusSort();
                break;
           case LIST:
               acceptableFocusSort=treeEngine.acceptableFocusSort(); // How to get this for list
               break;
           case OPTION:
                acceptableFocusSort=treeEngine.acceptableFocusSort();
               // assert false: "Method CTreeEditor.updateFocus: OPTION is not properly handled by current signature notion";
                break;
           default:
                // This should never happen
                assert false: "Method CTreeEditor.updateFocus called with unknown nodekind";
                break;
       }
       //adjust information about acceptable templates,
       vTemplates=new HashMap<String,CNode>();
       switch((treeEngine.getFocus()).kind()){
           case HOLE:
           case TUPLE: 
               if(nodeFactory!=null){
                   for(Map.Entry<String,Class> pattern :nodeFactory.getfLabeltoClass().entrySet()){
                        if(!pattern.getKey().equals(treeEngine.acceptableFocusSort())){
                                vNode=nodeFactory.makeNodeWithHoles(pattern.getKey());
                                
                        
                        }
                        
                        if(vNode!=null){
                            if(treeEngine.isAcceptableSort(vNode.sortLabel())&& pattern.getKey().equals(vNode.sortLabel()) && treeEngine.canReplace(vNode, treeEngine.getFocus())){
                                vTemplates.put(pattern.getKey(),vNode);
                                //System.out.println(pattern.getKey()+""+vNode);
                            }
                        }
                    }
                    setTemplates(vTemplates);
                   // System.out.println(vTemplates.toString());
               }
           break;
           case LIST:
                if(nodeFactory!=null){
                    for(Map.Entry<String,Class> pattern :nodeFactory.getfLabeltoClass().entrySet()){
                        
                        vNode=nodeFactory.makeNodeWithHoles(pattern.getKey());
                       // vNewNode=insertFirstItemIntoLists(vNode);
                        if(vNode!=null){
                            if(treeEngine.isAcceptableSort(vNode.sortLabel()) && treeEngine.canReplace(vNode, treeEngine.getFocus())){
                                if(pattern.getKey().equals(treeEngine.acceptableFocusSort())){
                                  //System.out.println(pattern.getKey() +" "+treeEngine.acceptableFocusSort());
                                  vTemplates.put("<"+pattern.getKey()+">",vNode) ;
                                }else{
                                vTemplates.put(pattern.getKey(),vNode);
                                }
                            }
                        }
                    }
                    setTemplates(vTemplates);
               }
           break;
           case OPTION:
                if(nodeFactory!=null){
                   for(Map.Entry<String,Class> pattern :nodeFactory.getfLabeltoClass().entrySet()){
                        if(!pattern.getKey().equals(treeEngine.acceptableFocusSort())){
                                vNode=nodeFactory.makeNodeWithHoles(pattern.getKey());
                                //vNewNode=insertFirstItemIntoLists(vNode);
                        }
                        
                        if(vNode!=null){
                            if(treeEngine.isAcceptableSort(vNode.sortLabel()) && treeEngine.canReplace(vNode, treeEngine.getFocus())){
                                vTemplates.put(pattern.getKey(),vNode);
                                
                            }
                        }
                    }
                    setTemplates(vTemplates);
               }
           break;
               // assert false: "Method CTreeEditor.updateFocus: LIST and OPTION are not properly handled by current signature notion";
               
           default:
                // This should never happen
                assert false: "Method CTreeEditor.updateFocus called with unknown nodekind";
                break;
       }
       
    }
  /**
   * This method is intended to insert an item into list0 kind of lists, to show sample code to users of the structure
   * editor.
   * 
   * @param aNode the list0 kind of list.
  */  
    @Override
  public void insertFirstItemIntoLists(CNode aNode){
      if(aNode!=null){
        HashMap<String,CNode> vTemplates=new HashMap<String,CNode>();
        if(aNode.kind()==CNodeKind.LIST && aNode.count()==0){ 
             vTemplates=getTemplates(aNode,((INodeList)aNode).eltSortLabel());
             if(vTemplates.size()==1){
                 Map.Entry<String, CNode> entry =vTemplates.entrySet().iterator().next();
                 treeEngine.add(entry.getValue(),aNode);
             }
        }else{
        for(int i=0;i<aNode.count();i++){ 
           insertFirstItemIntoLists(aNode.getNode(i)); 
        }
        }
      }
}
     /**
     * Handles property change events. If the property change is from a Language component, retrieves signature-part, and updates itself. 
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        String property=evt.getPropertyName();
        if(source==language){
           treeEngine.setSignature(language.getSignatureComp().getSignatureStructure()); 
        }
    }

    /**
     * Replace a focus with the node generated from the given template.
     * 
     * @param aTemplate the String template.
     */
    @Override
    public void replace(String aTemplate) {
        CNode vSource;
        if(isTreeEditing()){
            String vSelectedTemplate=aTemplate;
            vSource=nodeFactory.makeNodeWithHoles(vSelectedTemplate);
            insertFirstItemIntoLists(vSource);
            if(vSource!=null && treeEngine.canReplace(vSource, treeEngine.getFocus())){
                treeEngine.replace(vSource,treeEngine.getFocus());
                treeEngine.setFocus(vSource);
                updateTree();
                updateFocus();
            }
        }
    }

    /**
     * Used to set the focus of optional node type with a node generated from the given template.
     * 
     * @param aTemplate the String template.
     */
    @Override
    public void setPresent(String aTemplate){
        CNode vSource;
        if(isTreeEditing()){
            String vSelectedTemplate=aTemplate;
            vSource=nodeFactory.makeNodeWithHoles(vSelectedTemplate);
            //insertFirstItemIntoLists(vSource);
            if(vSource!=null && treeEngine.canSetPresent(vSource,treeEngine.getFocus())){
                treeEngine.setPresent(vSource,treeEngine.getFocus());
                treeEngine.setFocus(vSource);
                updateTree();
                updateFocus();
            }
        }
    }
    
    /**
     * Used to set a focused node absent if its an optional node.
     * 
     *
     */
     @Override
    public void setAbsent(){
        if(isTreeEditing() && treeEngine.canSetAbsent()){
                treeEngine.setAbsent();
                treeEngine.setFocus(treeEngine.fFocus);
                updateTree();
                updateFocus();
            
        }
    }

    /**
     * Add a node generated from the given template to a list of nodes.
     * 
     * @param aTemplate the String template.
     */
    @Override
    public void add(String aTemplate){
        CNode vSource;
        if(isTreeEditing()){
            String vSelectedTemplate=aTemplate;
            vSource=nodeFactory.makeNodeWithHoles(vSelectedTemplate);
            //insertFirstItemIntoLists(vSource);
            if(vSource!=null && treeEngine.canAdd(vSource,treeEngine.getFocus())){
                treeEngine.add(vSource,treeEngine.getFocus());
                treeEngine.setFocus(vSource);
                updateTree();
                updateFocus();
                
            }
        }
    }

    /**
     *
     */
    @Override
    public void delete() {
       CNode vNew, vOld;
       if(isTreeEditing()){
            vOld=treeEngine.getFocus();
            vNew=nodeFactory.makeNodeWithHoles(treeEngine.acceptableFocusSort());
            treeEngine.replace(vNew, vOld);
            treeEngine.setFocus(vNew);
            updateTree();
            updateFocus();
       } 
    }

    /**
     * Insert a node generated from the given template before the specified focus.
     * 
     * @param aTemplate the String template.
     * @param aFocus the focus specified.
     */
    @Override
    public void insertBefore(String aTemplate,CNode aFocus){
        CNode vSource;
        if(isTreeEditing()){
            vSource=nodeFactory.makeNodeWithHoles(aTemplate);
            if(vSource!=null && treeEngine.canInsertBefore(vSource, aFocus)){
                treeEngine.insertBefore(vSource,treeEngine.getFocus());
                treeEngine.setFocus(vSource);
                updateTree();
                updateFocus();
            }
        }
    }
    
    /**
     * Insert a node generated from the given template after the specified focus.
     * 
     * @param aTemplate the String template.
     * @param aFocus the focus specified.
     */
    @Override
    public void insertAfter(String aTemplate,CNode aFocus){
        CNode vSource;
        if(isTreeEditing()){
            vSource=nodeFactory.makeNodeWithHoles(aTemplate);
            if(vSource!=null && treeEngine.canInsertAfter(vSource, aFocus)){
                treeEngine.insertAfter(vSource,treeEngine.getFocus());
                treeEngine.setFocus(vSource);
                updateTree();
                updateFocus();
            }
        }
    }

    /**
     * Remove the focused node from the list.
     */
    @Override
    public void remove(){
        if(isTreeEditing()){
            treeEngine.remove(treeEngine.getFocus());
            updateTree();
            updateFocus();
        }
        
    }

    /**
     *  Clears the tree and creates a new root node
     */
    @Override
    public void clear() {
       CNode vNew;
       if(isTreeEditing()){
        vNew=nodeFactory.makeNodeWithHoles(treeEngine.acceptableFocusSort());
        treeEngine.setTree(vNew);
        treeEngine.setFocus(vNew);
        updateTree();
        updateFocus();
       }
    }

    /**
     * Determines if a node can be inserted into the list.
     * 
     * @return <code>true</code> if insert is allowed, else <code>false</code>.
     */
        @Override
    public boolean canInsert(){
        return treeEngine.canInsertBeforeorAfter();
    }

    /**
     * Determines if a node is an optional node which can be set absent.
     * 
     * @return <code>true</code> if a node can be absent, else <code>false</code>.
     */
    @Override
    public boolean canSetAbsent(){
        return treeEngine.canSetAbsent();
    }

    /**
     * Determines if a node can be removed from the list.
     * 
     * @return <code>true</code> if remove is allowed, else <code>false</code>.
     */
    @Override
    public boolean canRemove(){
        return treeEngine.canRemove(treeEngine.getFocus());
    }

    /**
     * Determines if a specified node can be replaced.
     * 
     * @param aFocus
     * @return <code>true</code> if replace is allowed, else <code>false</code>.
     */
    @Override
    public boolean canReplace(CNode aFocus){
        return treeEngine.canReplace(aFocus);
    }

    /**
     * Determines if a focused node can be deleted.
     * 
     * @return <code>true</code> if delete is allowed, else <code>false</code>.
     */
    @Override
    public boolean canDelete() {
        return (treeEngine.canDelete());
    }

    /**
     * Determines if the tree can be cleared and create a new root node
     * 
     * @return <code>true</code> if clear is allowed, else <code>false</code>.
     */
    @Override
    public boolean canClear() {
        return (treeEngine.canClear());
    }

    /**
     * Returns a node object (the AST) for this TreeEditor.
     * @return
     */
    @Override
    public CNode getNode() {
        return treeEngine.getRoot();
    }
    
    
}
