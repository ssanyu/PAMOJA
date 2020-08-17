/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.TreeEditor;

import Components.IPAMOJAComponent;
import Components.Specifications.Language.ILanguageComp;
import Nodes.CNode;
import Nodes.CNodeFactory;
import java.util.HashMap;

/**
 * Defines services for interacting with other components and classes ( like, Language component and NodeFactory class).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ITreeEditorComp extends IPAMOJAComponent {
    //Gives access to the edited tree

    /**
     *
     * @return
     */
        CNode getTree();

    /**
     *
     * @param Tree
     */
    void setTree(CNode Tree);
    
    // Gives access to focused node

    /**
     *
     * @return
     */
        CNode getFocus();

    /**
     *
     * @param node
     */
    void setFocus(CNode node);
    
    /**
     *
     * @return
     */
    String getAcceptableFocusSort();

    /**
     *
     * @return
     */
    HashMap<String,CNode> getTemplates();

    /**
     *
     * @param aNode
     * @param aSort
     * @return
     */
    HashMap<String,CNode> getTemplates(CNode aNode,String aSort);
    
    /**
     *
     * @return
     */
    boolean isTreeEditing();

    /**
     *
     * @param treeEditing
     */
    void setTreeEditing(boolean treeEditing);
  
    //Tree editing operations

    /**
     * Remove the focused node from the list.
     */
        void remove();

   /**
     * Replace the focus with the given node.
     * 
     * @param aNode the node to replace a focus.
     */
    void replace(CNode aNode);

   /**
     * Replace the focus with the given node.
     * 
     * @param aNode the node to replace a focus.
     */
    void replaceSubTree(CNode aNode);

     /**
     * Replace a focus with the node generated from the given template.
     * 
     * @param aSelectedTemplate the String template.
     */
    void replace(String aSelectedTemplate);

    /**
     * Replaces the data of the specified node with the specified data.
     * 
     * @param aNode the CNode object.
     * @param aData a String of data.
     */
    void replace(CNode aNode, String aData);

    /**
     *
     */
    void delete();

    /**
     *  Clears the tree and creates a new root node
     */
    void clear();

   /**
     * Add a node generated from the given template to a list of nodes.
     * 
     * @param aSelectedTemplate the String template.
     */
    void add(String aSelectedTemplate);

    /**
     * Insert a node generated from the given template before the specified focus.
     * 
     * @param aTemplate the String template.
     * @param vFocus the focus specified.
     */
    void insertBefore(String aTemplate,CNode vFocus);

     /**
     * Insert a node generated from the given template after the specified focus.
     * 
     * @param aTemplate the String template.
     * @param vFocus the focus specified.
     */
    void insertAfter(String aTemplate,CNode vFocus);

    /**
     * Used to set the focus of optional node type with a node generated from the given template.
     * 
     * @param aSelectedTemplate the String template.
     */
    void setPresent(String aSelectedTemplate);
/**
     * Used to set a focused node absent if its an optional node.
     * 
     *
     */
    void setAbsent();
    
    
     /**
     * Determines if a focused node can be deleted.
     * 
     * @return <code>true</code> if delete is allowed, else <code>false</code>.
     */
        boolean canDelete();

    /**
     * Determines if the tree can be cleared and create a new root node
     * 
     * @return <code>true</code> if clear is allowed, else <code>false</code>.
     */
    boolean canClear();

    /**
     * Determines if a specified node can be replaced.
     * 
     * @return <code>true</code> if replace is allowed, else <code>false</code>.
     */
    boolean canReplace(CNode aFocus);

    /**
     * Determines if a node can be removed from the list.
     * 
     * @return <code>true</code> if remove is allowed, else <code>false</code>.
     */
    boolean canRemove();

     /**
     * Determines if a node can be inserted into the list.
     * 
     * @return <code>true</code> if insert is allowed, else <code>false</code>.
     */
    boolean canInsert();

     /**
     * Determines if a node is an optional node which can be set absent.
     * 
     * @return <code>true</code> if a node can be absent, else <code>false</code>.
     */
    boolean canSetAbsent();
    
    /**
     * This method is intended to insert an item into list0 kind of lists, to show sample code to users of the structure
   * editor.
   * 
   * @param aNode the list0 kind of list.
  */ 
    void insertFirstItemIntoLists(CNode aNode);
       
    /**
     * Access a Language component via its interface.
     *
     * @return the Language interface
     *
     */
    ILanguageComp getLanguage();

    /**
     * Connects to a Language component via its interface. Registers for
     * property change events and retrieves current value of a signature.
     * 
     * @param aLanguage an instance of the Language component.
     */
    void setLanguage(ILanguageComp aLanguage);
    
    /**
     * Returns a NodeFactory object associated with this tree editor.
     * 
     * @return CNodeFactory object.
     */
    CNodeFactory getNodeFactory();

    /**
     * Sets the NodeFactory to be used by the tree editor.
     * 
     * @param nodeFactory the nodefactory to be set.
     */
    void setNodeFactory(CNodeFactory nodeFactory);
    
    
}
