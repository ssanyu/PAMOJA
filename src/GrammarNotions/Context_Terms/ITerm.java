/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;

import Nodes.INode;

/**
 *
 * @author ssanyu
 */
/* Base interface for all terms */
public interface ITerm extends INode {

    /**
     *
     * @param i
     * @param aTerm
     * @return
     */
    boolean canSetTerm(int i,CTerm aTerm);

    /**
     *
     * @return
     */
    int termCount();

    /**
     *
     * @param i
     * @return
     */
    CTerm getTerm(int i);

    /**
     *
     * @param i
     * @param aTerm
     */
    void setTerm(int i,CTerm aTerm);

    /**
     *
     * @return
     */
    CTerm copy();

}
