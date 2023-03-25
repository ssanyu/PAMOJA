/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrammarNotions.ECFGNodes;

//import GrammarNotations.Nodes.*;
import java.util.ArrayList;

/**
 *
 * @author jssanyu
 */
public class CTupleNode extends CECFGAbstractListNode {

    /**
     *
     */
    protected ArrayList<String> fData;
    
    /**
     *
     */
    public CTupleNode(){
        super();
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @param aData
     */
    public CTupleNode(String aRoot, String aPath, ArrayList<CECFGNode> aArgs,
                ArrayList<String> aData){
        super(aRoot, aPath, aArgs);
        fData = aData;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CECFGNodeCodes.scTupleNode;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "TupleNode";
    }
    
    /**
     *
     * @param i
     * @return
     */
    @Override
     public String getData(int i){
        // check precondition
        assert 0<=i & i<dataCount():  
            String.format("CTupleNode.getData.pre failed: i=%d, dataCount=%d .",
                i, dataCount());

        if (i <= 1)
            return super.getData(i);
        else
            return fData.get(i - 2);
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i, String aData){
        // check precondition
        assert 0<=i & i<dataCount():
            String.format("CTupleNode.setData.pre failed: i=%d, dataCount=%d .",
            i, dataCount());

        if(i<=1)
            super.setData(i, aData);
        else
            fData.set(i-2,aData);
    }
    
    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 2 + fData.size();
    }

    
    
}
