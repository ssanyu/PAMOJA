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
public class CNonTermNode extends CECFGNode {

    /**
     *
     */
    protected String fSymname;

    /**
     *
     */
    protected CECFGNode fBody;
    
    /**
     *
     */
    public CNonTermNode(){}

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aSymName
     * @param aBody
     */
    public CNonTermNode(String aRoot,String aPath,String aSymName,CECFGNode aBody){
        super(aRoot,aPath);
        fSymname=aSymName;
        fBody=aBody;
    }
    
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
    public CECFGNode getBody(){
       return fBody;
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
        return CECFGNodeCodes.scNonTermNode;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "NonTermNode";
    }
    
    /**
     *
     * @return
     */
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
            case 0: return fBody;
            default:{
                assert false:
                    "Error in CNonTermNode.getNode" + Integer.toString(i);
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
            "CNonTermNode.setNode.pre failed: i = " + Integer.toString(i) +
            " aTerm.className = " + aTerm.getClass().getName();

        switch(i){
            case 0: fBody = (CECFGNode)aTerm; break;
            default: {
                assert false:
                    "Error in CNonTermNode.setNode:" + Integer.toString(i);
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
            case 0:  return fRoot;
            case 1:  return fPath;
            case 2:  return fSymname;
            default: {
                assert false:
                    "Error in CNonTermNode.getData" + Integer.toString(i);
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
            case 2: fSymname = aData; break;
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
