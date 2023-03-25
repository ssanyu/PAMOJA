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
public interface IItem extends INode{

    /**
     *
     * @param aName
     * @return
     */
    boolean canSetName(String aName);

    /**
     *
     * @return
     */
    String getName();

    /**
     *
     * @param aName
     */
    void setName(String aName);

    //subterms

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
    void setTerm(int i, CTerm aTerm);

}
