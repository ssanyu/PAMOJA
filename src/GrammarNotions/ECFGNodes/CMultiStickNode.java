/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrammarNotions.ECFGNodes;

import Nodes.CNode;
import Nodes.CNodeKind;

/**
 *
 * @author jssanyu
 */
public class CMultiStickNode extends CECFGNode {

    /**
     *
     */
    protected int fIndex;

    /**
     *
     */
    protected CECFGNode fArg;
    
    /**
     *
     */
    public CMultiStickNode(){
    
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aIndex
     * @param aArg
     */
    public CMultiStickNode(String aRoot,String aPath,int aIndex, CECFGNode aArg){
        super(aRoot,aPath);
        fIndex=aIndex;
        fArg=aArg;
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
        return CECFGNodeCodes.scMultiStickNode;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "MultiStickNode";
    }
    
    @Override
    public int count() {
        return 1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i) {
        switch(i){
            case 0: return fArg;
            default:{
                assert false:
                    "Error in CMultiStickNode.getNode" + Integer.toString(i);
                return null; // to satisfy compiler
            }
        }
    }

    /**
     *
     * @param i
     * @param aTerm
     */
    @Override
    public void setNode(int i, CNode aTerm) {
        // check pre-condition
        assert canSetNode(i, aTerm):
            "CMultiStickNode.setNode.pre failed: i = " + Integer.toString(i) +
            " aTerm.className = " + aTerm.getClass().getName();

        switch(i){
            case 0: fArg = (CECFGNode)aTerm; break;
            default: {
                assert false:
                    "Error in CMultiStickNode.setNode:" + Integer.toString(i);
            }
        }
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
     public String getData(int i){
        String result;
        switch(i){
            case 0: return fRoot;
            case 1: return fPath;
            case 2: return Integer.toString(fIndex);
            default: {
                assert false:
                    "Error in CMultiStickNode.getData" + Integer.toString(i);
                return ""; // to satisfy compiler
            }
        }
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i, String aData){
       switch(i){
            case 0: fRoot = aData; break;
            case 1: fPath = aData; break;
            case 2: fIndex = Integer.parseInt(aData); break;
            default: {
                assert false:
                    "Error in CNonTermNode.setData:" + Integer.toString(i);
            }
        }
    
    }
    
    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 3;
    }
    
}
