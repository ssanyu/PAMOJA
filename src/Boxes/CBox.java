/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:43 CEST 2012
=====================================================*/

 
package Boxes; 

import GrammarNotions.Context_Terms.CTermTuple;
import Nodes.*;
 
/**
 * CBox is a base class which provides methods for creating the different kinds of box operators.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CBox extends CTermTuple  {
    CNode node;

    /**
     *
     */
    public CBox(){
    }

    /**
     *
     * @return
     */
    public String sortLabel(){
        return "Box";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scBox;
    }

    /**
     *
     * @return
     */
    public CNodeKind kind(){
        return CNodeKind.TUPLE;
    }

    /**
     *
     * @return
     */
    public String toText(){
        return "";
    }

    /**
     *
     * @return
     */
    public CNode getNode() {
        return node;
    }

    /**
     *
     * @param node
     */
    public void setNode(CNode node) {
        this.node = node;
    }
     
     
}
