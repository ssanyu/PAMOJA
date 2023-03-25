/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LR.SLR.SLRGenerator;

/**
 *
 * @author jssanyu
 */
public class Conflict {

    /**
     *
     */
    public int st;

    /**
     *
     */
    public String sym;
    
    /**
     *
     * @param aSt
     * @param aSym
     */
    public Conflict(int aSt,String aSym){
        st=aSt;
        sym=aSym;
    }
    
    public String toString(){
        return "State:"+ st +','+"Symbol:"+sym;
    }
    
}
