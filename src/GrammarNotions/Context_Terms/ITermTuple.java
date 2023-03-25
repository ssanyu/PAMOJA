/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;

/**
 *
 * @author ssanyu
 */
public interface ITermTuple extends ITerm {

    /**
     *
     * @param i
     * @param aContext
     * @return
     */
    boolean canSetContext(int i,CContext aContext);

    /**
     *
     * @return
     */
    CContextKind contextKind();

    /**
     *
     * @return
     */
    int contextCount();

    /**
     *
     * @param i
     * @return
     */
    CContext getContext(int i);

    /**
     *
     * @param i
     * @param aContext
     */
    void setContext(int i,CContext aContext);

    //References

    /**
     *
     * @param i
     * @param aRef
     * @return
     */
        boolean canSetReference(int i, CItem aRef);

    /**
     *
     * @return
     */
    int referenceCount();

    /**
     *
     * @param i
     * @return
     */
    CItem getReference(int i);

    /**
     *
     * @param i
     * @param aRef
     */
    void setReference(int i, CItem aRef);

}
