/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Parsers.CNPDALL;



/**
 *
 * @author HP
 */
public class nonterminalAction extends ActionNPDALL {

    /**
     *
     * @param parser
     */
    public nonterminalAction(CNPDALL parser) {
        super(parser);
    }
  
    /**
     *
     * @return
     */
    @Override
    public boolean execute() {
        backup();
        parser.replaceNonterminal();
        return true;
    }

    
         
}
