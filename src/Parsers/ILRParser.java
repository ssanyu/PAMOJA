/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

import TableGenerators.LR.Move;




/**
 *
 * @author HP
 */
public interface ILRParser extends IParser{
    /**
     * Derives a given sentence step by step, based on the given grammar.
     * <p>
     * The method is abstract and any descendants of {@code CLRParser} should implement 
     * it to provide customized parsing.
     * @return 
     */
    
    
    Move getAction();

    /**
     *
     * @param n
     * @return
     */
    String getProdName(int n);
    

}
