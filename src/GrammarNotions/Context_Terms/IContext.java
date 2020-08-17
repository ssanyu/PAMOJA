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
public interface IContext extends INode {

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


}
