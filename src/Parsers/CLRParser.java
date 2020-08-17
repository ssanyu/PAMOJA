/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

/**
 *
 * @author jssanyu
 */
public abstract class CLRParser extends CParserExt {

    /**
     *
     */
    public CLRParser(){
        super();
    }
    @Override
     public abstract CParserResult parse(); 

    @Override
    public abstract CParserResult parseNonTerminal(String aName); 
}
