/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SLR.SLRAutomata;

/**
 *
 * @author jssanyu
 */
public class Shift extends Move {
    
    /**
     *
     */
    public int state;
    
    /**
     *
     */
    public Shift(){}

    /**
     *
     * @param aState
     */
    public Shift(int aState){
        state=aState;
    }
    
    @Override
    public String toString(){
        return "S("+ state+")";
    }
    
}
