/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;

import Nodes.CNode;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssanyu
 */
public abstract class CContext extends CNode implements IContext {

    /**
     *
     */
    public CContext(){
        super();
    }

    /**
     *
     * @return
     */
    public CContext auxCopy(){
       CContext vResult=null;
        try {
            //Create a new node of the same class
            vResult = (CContext) nodeClass().newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(CContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CContext.class.getName()).log(Level.SEVERE, null, ex);
        }
       //copy context elements
       for(int i=0;i<=contextCount()-1;i++){
           vResult.setContext(i, getContext(i).auxCopy());
       }
       return vResult;
    }

    /**
     *
     */
    public void clearAux(){
    for(int i=0;i<=contextCount()-1;i++){
           getContext(i).clearAux();
       }}

    /**
     *
     * @param i
     * @param aContext
     * @return
     */
    @Override
    public abstract boolean canSetContext(int i, CContext aContext);

    /**
     *
     * @return
     */
    @Override
    public abstract CContextKind contextKind();

    /**
     *
     * @return
     */
    @Override
    public abstract int contextCount();

    /**
     *
     * @return
     */
    public abstract CContext copy();

    /**
     *
     * @param i
     * @return
     */
    @Override
    public abstract CContext getContext(int i);

    /**
     *
     * @param i
     * @param aContext
     */
    @Override
    public abstract void setContext(int i, CContext aContext);

}


