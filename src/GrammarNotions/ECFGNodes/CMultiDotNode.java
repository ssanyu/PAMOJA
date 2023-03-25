/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrammarNotions.ECFGNodes;

import java.util.ArrayList;

/**
 *
 * @author jssanyu
 */
public class CMultiDotNode extends CECFGAbstractListNode {
    
    /**
     *
     */
    public CMultiDotNode(){
        super();
    }

    /**
     *
     * @param aRoot
     * @param aPath
     */
    public CMultiDotNode(String aRoot, String aPath){
        super(aRoot, aPath);
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aList
     */
    public CMultiDotNode(String aRoot, String aPath,
               ArrayList<CECFGNode> aList){
        super(aRoot, aPath, aList);
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CECFGNodeCodes.scMultiDotNode;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "MultiDotNode";
    }

}
