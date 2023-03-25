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
public class CAltNode extends CECFGAbstractListNode {

    /**
     *
     */
    public CAltNode(){
        super();
    }

    /**
     *
     * @param aRoot
     * @param aPath
     */
    public CAltNode(String aRoot, String aPath){
        super(aRoot, aPath);
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aList
     */
    public CAltNode(String aRoot, String aPath,
               ArrayList<CECFGNode> aList){
        super(aRoot, aPath, aList);
    }
    
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CECFGNodeCodes.scAltNode;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "AltNode";
    }

    
    
}
