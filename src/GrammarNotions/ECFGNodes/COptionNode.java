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
public class COptionNode extends CECFGNode {

    /**
     *
     */
    protected boolean fPresent;

    /**
     *
     */
    protected CECFGNode fArg;
    
    /**
     *
     */
    public COptionNode(){}

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aPresent
     * @param aArg
     */
    public COptionNode(String aRoot,String aPath, boolean aPresent, CECFGNode aArg){
        super(aRoot,aPath);
        fPresent=aPresent;
        fArg=aArg;
    }

    /**
     *
     * @return
     */
    public boolean isPresent() {
        return fPresent;
    }

    /**
     *
     * @param fPresent
     */
    public void setPresent(boolean fPresent) {
        this.fPresent = fPresent;
    }

    /**
     *
     * @return
     */
    @Override
    public CNodeKind kind(){
        return CNodeKind.OPTION;
    }
   
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CECFGNodeCodes.scOptionNode;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "OptionNode";
    }
    
    @Override
    public int count() {
        if(fPresent==true)
        return 1;
        else return 0;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i) {
        if(fPresent==true){
            switch(i){
                case 0: return fArg;
                default:{
                    assert false:
                        "Error in COptionNode.getNode" + Integer.toString(i);
                    return null; // to satisfy compiler
                }
            }
        }
        assert false:
                    "Error in COptionNode.getNode" + Integer.toString(i);
        return null; // to satisfy compiler
    }

    /**
     *
     * @param i
     * @param aTerm
     */
    @Override
    public void setNode(int i, CNode aTerm) {
       if(fPresent==true){  
         switch(i){
            case 0: fArg = (CECFGNode)aTerm; break;
            default: {
                assert false:
                    "Error in COptionNode.setNode:" + Integer.toString(i);
            }
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
        switch(i){
            case 0: return fRoot;
            case 1: return fPath;
            case 2: return Boolean.toString(fPresent);
            default: {
                assert false:
                    "Error in COptionNode.getData" + Integer.toString(i);
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
            case 2: fPresent = Boolean.valueOf(aData); break;
            default: {
                assert false:
                    "Error in COptionNode.setData:" + Integer.toString(i);
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
