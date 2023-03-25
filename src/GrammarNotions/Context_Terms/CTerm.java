/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;

import Nodes.CNode;
import java.util.ArrayList;

/**
 *
 * @author ssanyu
 */
public abstract class CTerm extends CNode implements ITerm  {

    /**
     *
     */
    public CTerm(){
      super();
    }

    /**
     *
     * @param aNodes
     * @param aData
     */
    public CTerm(ArrayList<CNode> aNodes, ArrayList<String> aData){
       super(aNodes,aData);
    }

    /**
     *
     * @param i
     * @param aTerm
     * @return
     */
    @Override
    public abstract boolean canSetTerm(int i,CTerm aTerm);

    /**
     *
     * @return
     */
    @Override
    public abstract int termCount();

    /**
     *
     * @param i
     * @return
     */
    @Override
    public abstract CTerm getTerm(int i);
    @Override
    public abstract void setTerm(int i,CTerm aTerm);

    /**
     *
     * @return
     */
    @Override
    public abstract CTerm copy();

}
