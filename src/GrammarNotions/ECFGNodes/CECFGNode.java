/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrammarNotions.ECFGNodes;

import Nodes.CNode;
//import GrammarNotations.Nodes.CNodeKind;

/**
 *
 * @author jssanyu
 */
public abstract class CECFGNode extends CNode {

    /**
     *
     */
    protected String fRoot;

    /**
     *
     */
    protected String fPath;

    /**
     *
     */
    public CECFGNode(){}

    /**
     *
     * @param aRoot
     * @param aPath
     */
    public CECFGNode(String aRoot, String aPath){
        super();
        fRoot=aRoot;
        fPath=aPath;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
     public String getData(int i){
        switch(i){
            case 0: 
                return fRoot;
            case 1: return fPath;
            default:{
                assert false: 
                    "Error in CECFGNode.getData:" + Integer.toString(i);
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
            default:{
                assert false: 
                    "Error in CECFGNode.setData:" + Integer.toString(i);
            }
       }
    }
    
    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 2;
    }
    
    /**
     *
     * @return
     */
    public String getRoot(){
        return fRoot;
    }
    
    /**
     *
     * @return
     */
    public String getPath(){
        return fPath;
    }

    /**
     *
     * @param i
     * @param aNode
     * @return
     */
    @Override
    public boolean canSetNode(int i, CNode aNode) {
        return (0 <= i) && (i < count()) && (aNode instanceof CECFGNode);
    }
    
    
    @Override
    public String toString(){
       String vLabel = sortLabel();
       if(dataCount()>0){
          vLabel=vLabel+" ["+getData(0);
          for(int i = 1; i <=dataCount()-1; i++){ 
              vLabel=vLabel+"  "+getData(i);
          }
          vLabel=vLabel+" ]";
        }
        return vLabel;
    }

}
