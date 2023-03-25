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
public interface INPDALR extends IParser{
    
    /**
     *
     */
    void start();

    /**
     *
     */
    void shift();

    /**
     *
     */
    void error();

    /**
     *
     */
    void accept();

    /**
     *
     */
    void reduce();
    
}
