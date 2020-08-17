/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import GrammarNotions.Context_Terms.CItem;
import GrammarNotions.Context_Terms.CTerm;
import Nodes.CNodeKind;
 
/**
 * The class implements a structural representation of a pattern definition.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CPatternDef extends CItem  {

    /**
     *
     */
    protected CBox fpattern;

    /**
     *
     */
    public CPatternDef(){
    }

    /**
     *
     * @param aname
     * @param apattern
     */
    public CPatternDef(String aname,CBox apattern){
        super(aname);
        fpattern=apattern;
    }

    /**
     *
     * @return
     */
    public CBox getpattern(){
        return fpattern;
    }

    /**
     *
     * @param apattern
     */
    public void setpattern(CBox apattern){
        fpattern=apattern;
    }

    /**
     *
     * @return
     */
    public String sortLabel(){
        return "PatternDef";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scPatternDef;
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

    /**
     *
     * @param i
     * @return
     */
    public CTerm getTerm(int i){
        assert 0<=i && i<termCount() : String.format("Precondition of CPatternDef.getTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 return fpattern;

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
        assert 0<=i && i<termCount() : String.format("Precondition of CPatternDef.setTerm() failed: i=%d",i);
        switch(i-super.termCount()){
             case 0:
                 assert aTerm instanceof CBox : String.format("Precondition of CPatternDef.setTerm() failed: i=%d , aTerm.sortLabel=%s",i,aTerm.sortLabel());
                 fpattern=(CBox)aTerm;
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
        return fpattern.toText();
    }
}
