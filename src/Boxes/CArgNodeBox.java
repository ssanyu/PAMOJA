/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import Nodes.CNodeKind;
 
/**
 * Creates a box operator used in patterns to distinguish subtrees.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CArgNodeBox extends CBox  {

    /**
     *
     */
    protected int findex;

    /**
     *
     */
    public CArgNodeBox(){
    }

    /**
     *
     * @param aindex
     */
    public CArgNodeBox(int aindex){
        findex=aindex;
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
     * @param aindex
     */
    public void setindex(int aindex){
        findex=aindex;
    }


    public String sortLabel(){
        return "ArgNodeBox";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scArgNodeBox;
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
        assert 0<=i && i<dataCount() : String.format("Precondition of CArgNodeBox.getData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
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
        assert 0<=i && i<dataCount() : String.format("Precondition of CArgNodeBox.setData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
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
        return "Node"+'('+findex+')';
    }
}
