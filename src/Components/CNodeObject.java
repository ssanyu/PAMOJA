/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Nodes.CNode;

/**
 * <code>CNodeObject</code> is an abstract class defining a method for returning a <code>CNode</code> object. That is, a tree which can be viewed using a tree view.
 * <p> 
 * This class can be extended by all classes defining GAE components which return trees, such as parse trees, abstract syntax trees and box trees
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public abstract class CNodeObject extends CPAMOJAComponent {
    
    /**
     *  Creates a new <code> CNodeObject </code>
     */
    public CNodeObject(){
        super();
    }
    
    /**
     * Returns a node object to be viewed in tree form.
     * 
     * @return the tree of the component extending <code>CNodeObject</code>. 
     */
    public abstract CNode getNode(); 
  
    
}
