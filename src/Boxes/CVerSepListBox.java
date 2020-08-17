/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import GrammarNotions.Context_Terms.CTerm;
import Nodes.CNodeKind;
 
/**
 * A box operator for vertical formatting of boxes with a separator.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CVerSepListBox extends CBox  {

    /**
     *
     */
    protected CTermBox fopen;

    /**
     *
     */
    protected CTermBox fsep;

    /**
     *
     */
    protected CTermBox fclose;

    /**
     *
     */
    public CVerSepListBox(){
    }

    /**
     *
     * @param aopen
     * @param asep
     * @param aclose
     */
    public CVerSepListBox(CTermBox aopen,CTermBox asep,CTermBox aclose){
        fopen=aopen;
        fsep=asep;
        fclose=aclose;
    }

    /**
     *
     * @return
     */
    public CTermBox getopen(){
        return fopen;
    }

    /**
     *
     * @return
     */
    public CTermBox getsep(){
        return fsep;
    }

    /**
     *
     * @return
     */
    public CTermBox getclose(){
        return fclose;
    }

    /**
     *
     * @param aopen
     */
    public void setopen(CTermBox aopen){
        fopen=aopen;
    }

    /**
     *
     * @param asep
     */
    public void setsep(CTermBox asep){
        fsep=asep;
    }

    /**
     *
     * @param aclose
     */
    public void setclose(CTermBox aclose){
        fclose=aclose;
    }


    public String sortLabel(){
        return "VerSepListBox";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scVerSepListBox;
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
        return super.termCount() + 3;
    }


    public CTerm getTerm(int i){
        assert 0<=i && i<termCount() : String.format("Precondition of CVerSepListBox.getTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 return fopen;

             case 1:
                 return fsep;

             case 2:
                 return fclose;

             default:
                 return super.getTerm(i);

        }
    }


    public void setTerm(int i,CTerm aTerm){
        assert 0<=i && i<termCount() : String.format("Precondition of CVerSepListBox.setTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 assert aTerm instanceof CTermBox : String.format("Precondition of CVerSepListBox.setTerm() failed: i=%d , aTerm.sortLabel=%s",i,aTerm.sortLabel());
                 fopen=(CTermBox)aTerm;
                 break;

             case 1:
                 assert aTerm instanceof CTermBox : String.format("Precondition of CVerSepListBox.setTerm() failed: i=%d , aTerm.sortLabel=%s",i,aTerm.sortLabel());
                 fsep=(CTermBox)aTerm;
                 break;

             case 2:
                 assert aTerm instanceof CTermBox : String.format("Precondition of CVerSepListBox.setTerm() failed: i=%d , aTerm.sortLabel=%s",i,aTerm.sortLabel());
                 fclose=(CTermBox)aTerm;
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
        return "Sel(VerSep(["+fopen.toText()+','+fsep.toText()+','+fclose.toText()+"]))";
    }
}
