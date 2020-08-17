/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import Nodes.CNodeKind;
 
/**
 * Creates a box operator used in patterns to distinguish data elements.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CArgDataBox extends CBox  {

    /**
     *
     */
    protected String fsymName;

    /**
     *
     */
    protected int findex;

    /**
     *
     */
    public CArgDataBox(){
    }

    /**
     *
     * @param asymName
     * @param aindex
     */
    public CArgDataBox(String asymName,int aindex){
        fsymName=asymName;
        findex=aindex;
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
    public int getindex(){
        return findex;
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
     * @param aindex
     */
    public void setindex(int aindex){
        findex=aindex;
    }


    public String sortLabel(){
        return "ArgDataBox";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scArgDataBox;
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
        assert 0<=i && i<dataCount() : String.format("Precondition of CArgDataBox.getData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
                 return fsymName;

             case 1:
                 return Integer.toString(findex);

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
        assert 0<=i && i<dataCount() : String.format("Precondition of CArgDataBox.setData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
                 fsymName=aData;
                 break;

             case 1:
                 findex=Integer.parseInt(aData);
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
        return "Data"+'('+fsymName+','+findex+')';
    }
}
