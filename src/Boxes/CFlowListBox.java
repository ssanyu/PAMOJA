/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import GrammarNotions.Context_Terms.CTerm;
import Nodes.CNodeKind;
 
/**
 * A box operator which formats boxes horizontally until no more boxes fit on the same line (i.e., uses multiple lines).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CFlowListBox extends CBox  {

    /**
     *
     */
    protected CBox_List flist;

    /**
     *
     */
    protected int fhSpace;

    /**
     *
     */
    protected int fvSpace;

    /**
     *
     */
    public CFlowListBox(){
    }

    /**
     *
     * @param alist
     * @param ahSpace
     * @param avSpace
     */
    public CFlowListBox(CBox_List alist,int ahSpace,int avSpace){
        flist=alist;
        fhSpace=ahSpace;
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
    public int gethSpace(){
        return fhSpace;
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
     * @param ahSpace
     */
    public void sethSpace(int ahSpace){
        fhSpace=ahSpace;
    }

    /**
     *
     * @param avSpace
     */
    public void setvSpace(int avSpace){
        fvSpace=avSpace;
    }


    public String sortLabel(){
        return "FlowListBox";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scFlowListBox;
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
        assert 0<=i && i<termCount() : String.format("Precondition of CFlowListBox.getTerm() failed: i=%d",i);
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
        assert 0<=i && i<termCount() : String.format("Precondition of CFlowListBox.setTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 assert aTerm instanceof CBox_List : String.format("Precondition of CFlowListBox.setTerm() failed: i=%d , aTerm.sortLabel=%s",i,aTerm.sortLabel());
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
        return super.dataCount() + 2;
    }

    /**
     *
     * @param i
     * @return
     */
    public String getData(int i){
        assert 0<=i && i<dataCount() : String.format("Precondition of CFlowListBox.getData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
                 return Integer.toString(fhSpace);

             case 1:
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
        assert 0<=i && i<dataCount() : String.format("Precondition of CFlowListBox.setData() failed: i=%d",i);
        switch(i-super.dataCount()){
             case 0:
                 fhSpace=Integer.parseInt(aData);
                 break;

             case 1:
                 fvSpace=Integer.parseInt(aData);
                 break;

             default:
                 super.setData(i,aData);
                 break;

        }
    }
}
