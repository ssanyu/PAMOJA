/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.SyntaxExpr;

import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Context_Terms.CTermList;
import GrammarNotions.Grammar.CGrammarCodes;

/**
 *
 * @author ssanyu
 */
public class CSE_List extends CTermList {

    /**
     *
     * @param i
     * @return
     */
    public CSE getElt(int i){
        return (CSE)getTerm(i);
    }

    /**
     *
     * @param i
     * @param aSE
     */
    protected void setElt(int i, CSE aSE){
        setTerm(i,aSE);
    }
    @Override
    public String eltSortLabel() {
        return "SE";
    }

    @Override
    public int eltSortCode() {
        return CGrammarCodes.scSE;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CGrammarCodes.scSEList;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "SEList";
    }

    /**
     *
     * @return
     */
    @Override
    public Class eltClass() {
        return CSE.class;
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
