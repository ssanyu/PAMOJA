/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrammarNotions.ECFGNodes;

import Nodes.*;

/**
 *
 * @author jssanyu
 */
public class CEpsNode extends CECFGNode {
    
    /**
     *
     */
    public CEpsNode(){}

    /**
     *
     * @param aRoot
     * @param aPath
     */
    public CEpsNode(String aRoot,String aPath){
        super(aRoot,aPath);
    }

    /**
     *
     * @return
     */
    @Override
    public CNodeKind kind(){
        return CNodeKind.TUPLE;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CECFGNodeCodes.scEpsNode;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "EpsNode";
    }
    
   @Override
   public int count(){
       return 0;
   }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i){
        assert false:
            "Index error in CEpsNode.getNode()" + Integer.toString(i);
        return null; // to satisfy compiler
    };

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void setNode(int i, CNode aNode){
        assert canSetNode(i, aNode):        // always fails because count() == 0
            "Index error in CEpsNode.setNode()" + Integer.toString(i);
    };

}
