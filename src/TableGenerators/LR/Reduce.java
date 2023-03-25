/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LR;

/**
 *
 * @author jssanyu
 */
public class Reduce extends Move {

    /**
     *
     */
    public int prodNo;   // Number of a NonTerminal A

    /**
     *
     */
    public String prod; // The production of A
    
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
        prodNo=-1;
        index=-1;
        length=-1;
       
    }

    /**
     *
     * @param aA
     * @param aI
     * @param aL
     * @param aP
     */
    public Reduce(int aA,int aI, int aL,String aP){
      prodNo=aA;
      index=aI;
      length=aL;
      prod=aP;
     
    }

    /**
     *
     * @return
     */
    public String getProd() {
        return prod;
    }

    /**
     *
     * @param prod
     */
    public void setProd(String prod) {
        this.prod = prod;
    }
    
    /**
     *
     * @return
     */
    public int getProdNo() {
        return prodNo;
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @return
     */
    public int getLength() {
        return length;
    }
    
    @Override
    public String toString(){
       // return "R("+ prodNo +","+ index +","+ length +")";
        return "R("+ prodNo +","+ index +")";
    }
}
