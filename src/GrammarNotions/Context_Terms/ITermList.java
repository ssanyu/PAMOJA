/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;
/**
 *
 * @author ssanyu
 */
public interface ITermList extends ITerm {

    /**
     *
     * @param aTerm
     * @return
     */
    boolean canAddTerm(CTerm aTerm);

    /**
     *
     * @param i
     * @param aTerm
     * @return
     */
    boolean canInsertTerm(int i, CTerm aTerm);

    /**
     *
     * @param aTerm
     */
    void addTerm(CTerm aTerm);

    /**
     *
     * @param i
     * @param aTerm
     */
    void insertTerm(int i, CTerm aTerm);

    

}
