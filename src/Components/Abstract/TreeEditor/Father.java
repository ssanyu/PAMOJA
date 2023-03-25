/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.TreeEditor;

import Nodes.CNode;

/**
 * Defines a node object representing a parent node.
 * 
 * @author jssanyu
 */
public class Father {

    /**
     *
     */
    public CNode fFather;

    /**
     *
     */
    public int fIndex;
    
    /**
     * Creates a new Father node.
     * 
     * @param aFather
     * @param aIndex
     */
    public Father(CNode aFather, int aIndex){
        fFather=aFather;
        fIndex=aIndex;
    }
    
}
