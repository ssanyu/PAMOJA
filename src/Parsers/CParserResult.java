/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Parsers;

import Nodes.CNode;

/**
 *
 * @author ssanyu
 */
public class CParserResult {
    private CNode fNode;
    private boolean fSuccess;
    
    /**
     *
     * @param aSuccess
     * @param aNode
     */
    public CParserResult(boolean aSuccess, CNode aNode){
        fSuccess=aSuccess;
        fNode=aNode;
    }

    /**
     *
     */
    public CParserResult(){
        fNode=null;
        fSuccess=false;
    }
    
    /**
     *
     * @param aNode
     */
    public void setNode(CNode aNode){
       
           fNode=aNode;
       
   }

    /**
     *
     * @return
     */
    public CNode getNode(){
       return fNode;
   }

    /**
     *
     * @return
     */
    public boolean isSuccess(){
       if(fSuccess==true)
           return true;
       else return false;
   }

    /**
     *
     * @param aSuccess
     */
    public void setSuccess(boolean aSuccess){
       fSuccess=aSuccess;
   }
   
    /**
     *
     */
    public void clear(){
       fSuccess=false;
       fNode=null;
      
   }
  
   

}
