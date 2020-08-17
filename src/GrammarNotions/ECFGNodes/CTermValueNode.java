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
public class CTermValueNode extends CECFGNode {

    /**
     *
     */
    protected String fSymname;

    /**
     *
     */
    protected String fSymvalue;
    
    /**
     *
     */
    public CTermValueNode(){}

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aSymName
     * @param aSymValue
     */
    public CTermValueNode(String aRoot,String aPath,String aSymName,String aSymValue){
         super(aRoot,aPath);
         fSymname=aSymName;
         fSymvalue=aSymValue;
    }
   // public CTermValueNode(String aRoot,String aPath,String aSymName,String aSymValue)
    
    /**
     *
     * @return
     */
    public String getSymName(){
       return fSymname;
   }

    /**
     *
     * @return
     */
    public String getSymValue(){
       return fSymvalue;
   }

   @Override
   public int count(){
       return 0;
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
        return CECFGNodeCodes.scTermValueNode;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "TermValueNode";
    }
    
    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i){
        assert false:
            "Index error in CTermNode.getNode()" + Integer.toString(i);
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
            "Index error in CTermNode.setNode()" + Integer.toString(i);
    };

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
            case 2: return fSymname;
            case 3: return fSymvalue;
            default:{
                assert false:
                    "Error in CTermValueNode.getData"+Integer.toString(i);
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
            case 0: fRoot=aData; break;
            case 1: fPath=aData; break;
            case 2: fSymname=aData; break;
            case 3: fSymvalue=aData; break;
            default: {
                assert false:
                    "Error in CTermNode.setData:" + Integer.toString(i);
            }
        }
    }
    
    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 4;
    }
    
}
