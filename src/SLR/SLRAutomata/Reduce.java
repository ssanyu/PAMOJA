/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SLR.SLRAutomata;

/**
 *
 * @author jssanyu
 */
public class Reduce extends Move {

    /**
     *
     */
    public int prod;   // Number of a NonTerminal A

    /**
     *
     */
    public int index;   // index in a multistick expression

    /**
     *
     */
    public int length;  // length of a multidot expression
    
    /**
     *
     */
    public Reduce(){
        prod=-1;
        index=-1;
        length=-1;
    }

    /**
     *
     * @param aA
     * @param aI
     * @param aL
     */
    public Reduce(int aA,int aI, int aL){
      prod=aA;
      index=aI;
      length=aL;
    }
    
    @Override
    public String toString(){
        return "R("+ prod +","+ index +","+ length +")";
    }
}
