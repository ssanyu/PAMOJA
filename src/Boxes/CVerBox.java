/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import GrammarNotions.Context_Terms.CTerm;
import Nodes.CNodeKind;
 
/**
 * A box operator for vertical formatting of boxes.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CVerBox extends CBox  {

    /**
     *
     */
    protected CBox_List flist;

    /**
     *
     */
    protected int fvSpace;

    /**
     *
     */
    public CVerBox(){
    }

    /**
     *
     * @param alist
     * @param avSpace
     */
    public CVerBox(CBox_List alist,int avSpace){
        flist=alist;
        fvSpace=avSpace;
    }

    /**
     *
     * @return
     */
    public CBox_List getlist(){
        return flist;
    }

    /**
     *
     * @return
     */
    public int getvSpace(){
        return fvSpace;
    }

    /**
     *
     * @param alist
     */
    public void setlist(CBox_List alist){
        flist=alist;
    }

    /**
     *
     * @param avSpace
     */
    public void setvSpace(int avSpace){
        fvSpace=avSpace;
    }


    public String sortLabel(){
        return "VerBox";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scVerBox;
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
    public int termCount(){
        return super.termCount() + 1;
    }


    public CTerm getTerm(int i){
        assert 0<=i && i<termCount() : String.format("Precondition of CVerBox.getTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 return flist;

             default:
                 return super.getTerm(i);

        }
    }

    /**
     *
     * @param i
     * @param aTerm
     */
    public void setTerm(int i,CTerm aTerm){
        assert 0<=i && i<termCount() : String.format("Precondition of CVerBox.setTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 assert aTerm instanceof CBox_List : String.format("Precondition of CVerBox.setTerm() failed: i=%d , aTerm.sortLabel=%s",i,aTerm.sortLabel());
                 flist=(CBox_List)aTerm;
                 break;

             default:
                 super.setTerm(i,aTerm);
                 break;

        }
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
        assert 0<=i && i<dataCount() : String.format("Precondition of CVerBox.getData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
                 return Integer.toString(fvSpace);

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
        assert 0<=i && i<dataCount() : String.format("Precondition of CVerBox.setData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
                 fvSpace=Integer.parseInt(aData);
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
        String result = "Ver(["+flist.getElt(0).toText();
        for (int i = 1; i <= flist.termCount()-1; i++){
            result = result + ',' + flist.getElt(i).toText();
        }
        result = result + "],"+fvSpace+ ')';
        return result;
    }
}
