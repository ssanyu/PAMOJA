/*
 * TODO: Enforce public invariant 1 <= count()
 */

package GrammarNotions.ECFGNodes;

import java.util.ArrayList;

/**
 *
 * @author jssanyu
 */
public class CPlusNode extends CECFGAbstractListNode {

    /**
     *
     */
    public CPlusNode(){
        super(); 
    }

    /**
     *
     * @param aRoot
     * @param aPath
     */
    public CPlusNode(String aRoot, String aPath){
        super(aRoot, aPath);
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aList
     */
    public CPlusNode(String aRoot, String aPath,
               ArrayList<CECFGNode> aList){
        super(aRoot, aPath, aList);
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CECFGNodeCodes.scPlusNode;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "PlusNode";
    }

}
