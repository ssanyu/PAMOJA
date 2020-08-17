/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SLR.SLRAutomata;

/**
 *
 * @author jssanyu
 */
public class ErrorMsg extends Move {
    String error;

    /**
     *
     */
    public ErrorMsg(){
    }
    
    @Override
    public String toString(){
        return "E";
    }
    
}
