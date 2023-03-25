/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Nodes.CNode;

/**
 * Interface which defines a method for returning a Node object.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface INodeObject extends IPAMOJAComponent {
    
    /**
     * Returns a node object to be viewed in tree form.
     * 
     * @return the tree of the component implementing <code>INodeObject</code>. 
     */
    CNode getNode();
   
    
}
