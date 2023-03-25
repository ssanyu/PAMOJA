/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Parsers.CLRParser;

/**
 *
 * @author HP
 */
public class shiftAction extends ActionLR{

    /**
     *
     * @param parser
     */
    public shiftAction(CLRParser parser) {
        super(parser);
    }
  
    /**
     *
     */
    @Override
    public void undo() {
     //provide backup copy to the LR parser object
       parser.previousSym();
       super.undo();
       
       
    }
    
}
