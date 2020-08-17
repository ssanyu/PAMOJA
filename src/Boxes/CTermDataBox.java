/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import Nodes.CNodeKind;
 
/**
 * Formats a terminal symbol with data. 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CTermDataBox extends CBox  {

    /**
     *
     */
    protected String fsymName;

    /**
     *
     */
    protected String fdata;

    /**
     *
     */
    public CTermDataBox(){
    }

    /**
     *
     * @param asymName
     * @param adata
     */
    public CTermDataBox(String asymName,String adata){
        fsymName=asymName;
        fdata=adata;
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
     * @return
     */
    public String getdata(){
        return fdata;
    }

    /**
     *
     * @param asymName
     */
    public void setsymName(String asymName){
        fsymName=asymName;
    }

    /**
     *
     * @param adata
     */
    public void setdata(String adata){
        fdata=adata;
    }

    /**
     *
     * @return
     */
    public String sortLabel(){
        return "TermDataBox";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scTermDataBox;
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
        return super.dataCount() + 2;
    }

    /**
     *
     * @param i
     * @return
     */
    public String getData(int i){
        assert 0<=i && i<dataCount() : String.format("Precondition of CTermDataBox.getData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
                 return fsymName;

             case 1:
                 return fdata;

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
        assert 0<=i && i<dataCount() : String.format("Precondition of CTermDataBox.setData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
                 fsymName=aData;
                 break;

             case 1:
                 fdata=aData;
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
       return "TermData("+fsymName+" "+fdata+')';
    } 
}
