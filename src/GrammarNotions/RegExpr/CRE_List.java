/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.RegExpr;

import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Context_Terms.CTermList;
import GrammarNotions.Grammar.CGrammarCodes;

/**
 *
 * @author ssanyu
 */
public class CRE_List extends CTermList {

    /**
     *
     * @param i
     * @return
     */
    public CRE getElt(int i){
        return (CRE)getTerm(i);
    }

    /**
     *
     * @param i
     * @param aRE
     */
    protected void setElt(int i, CRE aRE){
        setTerm(i,aRE);
    }
    @Override
    public String eltSortLabel() {
        return "RE";
    }

    @Override
    public int eltSortCode() {
        return CGrammarCodes.scRE;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CGrammarCodes.scREList;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "REList";
    }

    /**
     *
     * @return
     */
    @Override
    public Class eltClass() {
        return CRE.class;
    }

    /**
     *
     * @param i
     * @param aTerm
     * @return
     */
    @Override
    public boolean canSetTerm(int i, CTerm aTerm) {
        return 0<=i & i<termCount();
    }


}
