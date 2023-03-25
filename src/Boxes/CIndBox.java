/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:43 CEST 2012
=====================================================*/

 
package Boxes; 

import GrammarNotions.Context_Terms.CTerm;
import Nodes.CNodeKind;
 
/**
 * An indentation box operator.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CIndBox extends CBox  {

    /**
     *
     */
    protected CBox farg;

    /**
     *
     */
    public CIndBox(){
    }

    /**
     *
     * @param aarg
     */
    public CIndBox(CBox aarg){
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

    /**
     *
     * @return
     */
    public String sortLabel(){
        return "IndBox";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scIndBox;
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
        assert 0<=i && i<termCount() : String.format("Precondition of CIndBox.getTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 return farg;

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
        assert 0<=i && i<termCount() : String.format("Precondition of CIndBox.setTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 assert aTerm instanceof CBox : String.format("Precondition of CIndBox.setTerm() failed: i=%d , aTerm.sortLabel=%s",i,aTerm.sortLabel());
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
       return "Ind("+farg.toText()+')';
    } 
}
