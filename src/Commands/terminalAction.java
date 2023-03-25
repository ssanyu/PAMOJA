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
public class terminalAction  extends ActionNPDALL{

    /**
     *
     * @param parser
     */
    public terminalAction(CNPDALL parser) {
        super(parser);
    }
  
    /**
     *
     * @return
     */
    @Override
    public boolean execute() {
        backup();
        parser.matchTerminal();
        return true;
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
