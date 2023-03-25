/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;

/**
 *
 * @author ssanyu
 */
public interface IContextList extends IContext {

    /**
     *
     * @param aContext
     * @return
     */
    boolean canAddContext(CContext aContext);

    /**
     *
     * @param i
     * @param aContext
     * @return
     */
    boolean canInsertContext(int i, CContext aContext);

    /**
     *
     * @param aContext
     */
    void addContext(CContext aContext);

    /**
     *
     * @param i
     * @param aContext
     */
    void insertContext(int i, CContext aContext);

}
