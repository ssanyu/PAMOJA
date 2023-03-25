/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import Nodes.CNodeKind;
 
/**
 * Formats a terminal symbol without data. 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CTermBox extends CBox  {

    /**
     *
     */
    protected String fsymName;

    /**
     *
     */
    public CTermBox(){
    }

    /**
     *
     * @param asymName
     */
    public CTermBox(String asymName){
        fsymName=asymName;
    }

    /**
     *
     * @return
     */
    public String getsymName(){
        return fsymName;
    }

    /**
     *
     * @param asymName
     */
    public void setsymName(String asymName){
        fsymName=asymName;
    }


    public String sortLabel(){
        return "TermBox";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scTermBox;
    }

    /**
     *
     * @return
     */
    public CNodeKind kind(){
        return CNodeKind.TUPLE;
    }

    /**
     *
     * @return
     */
    public int dataCount(){
        return super.dataCount() + 1;
    }

    /**
     *
     * @param i
     * @return
     */
    public String getData(int i){
        assert 0<=i && i<dataCount() : String.format("Precondition of CTermBox.getData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
                 return fsymName;

             default:
                 return super.getData(i);

        }
    }

    /**
     *
     * @param i
     * @param aData
     */
    public void setData(int i,String aData){
        assert 0<=i && i<dataCount() : String.format("Precondition of CTermBox.setData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
                 fsymName=aData;
                 break;

             default:
                 super.setData(i,aData);
                 break;

        }
    }
   
    /**
     *
     * @return
     */
    public String toText(){
       return "Term("+fsymName+')';
    } 
}
