/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Parsers.CNPDALR;

/**
 *
 * @author HP
 */
public class shiftActionNPDA extends ActionNPDALR{

    /**
     *
     * @param parser
     */
    public shiftActionNPDA(CNPDALR parser) {
        super(parser);
    }
  
    /**
     *
     * @return
     */
    @Override
    public boolean execute() {
        backup();
        parser.shift();
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
