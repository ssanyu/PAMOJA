/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:43 CEST 2012
=====================================================*/

 
package Boxes; 

import GrammarNotions.Context_Terms.CTerm;
import Nodes.CNodeKind;
 
/**
 * Specifies a selectable box. 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSelBox extends CBox  {

    /**
     *
     */
    protected CBox farg;

    /**
     *
     */
    public CSelBox(){
    }

    /**
     *
     * @param aarg
     */
    public CSelBox(CBox aarg){
        farg=aarg;
    }

    /**
     *
     * @return
     */
    public CBox getarg(){
        return farg;
    }

    /**
     *
     * @param aarg
     */
    public void setarg(CBox aarg){
        farg=aarg;
    }


    public String sortLabel(){
        return "SelBox";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scSelBox;
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
        assert 0<=i && i<termCount() : String.format("Precondition of CSelBox.getTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 return farg;

             default:
                 return super.getTerm(i);

        }
    }


    public void setTerm(int i,CTerm aTerm){
        assert 0<=i && i<termCount() : String.format("Precondition of CSelBox.setTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 assert aTerm instanceof CBox : String.format("Precondition of CSelBox.setTerm() failed: i=%d , aTerm.sortLabel=%s",i,aTerm.sortLabel());
                 farg=(CBox)aTerm;
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
    public String toText(){
       return "Sel("+farg.toText()+')';
    } 
}
