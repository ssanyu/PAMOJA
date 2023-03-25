/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Parsers.CLLParser;

/**
 *
 * @author HP
 */
public class termAction extends ActionLL{

    /**
     *
     * @param parser
     */
    public termAction(CLLParser parser) {
        super(parser);
    }
  
    /**
     *
     */
    @Override
    public void undo() {
        parser.previousSym();
        super.undo();
        
        
    }      
}
