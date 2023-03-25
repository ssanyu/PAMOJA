/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

/**
 *
 * @author HP
 */
public interface INPDALL extends IParser{

    /**
     *
     */
    void start(); 

    /**
     *
     * @return
     */
    String getfSym();

    /**
     *
     */
    void replaceNonterminal();

    /**
     *
     */
    void matchTerminal();
    
}
