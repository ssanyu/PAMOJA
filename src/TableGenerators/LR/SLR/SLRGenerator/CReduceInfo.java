/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LR.SLR.SLRGenerator;

import TableGenerators.LR.Reduce;




/**
 *
 * @author jssanyu
 */
public class CReduceInfo {
    int s;
    int a; //a Symbol
    Reduce r;
    
    /**
     *
     */
    public CReduceInfo(){}
    
    /**
     *
     * @param aS
     * @param aA
     * @param aR
     */
    public CReduceInfo(int aS, int aA, Reduce aR){
        s=aS;
        a=aA;
        r=aR;
    }
}
