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
public class errorActionNPDA extends ActionNPDALR {

    /**
     *
     * @param parser
     */
    public errorActionNPDA(CNPDALR parser) {
        super(parser);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean execute() {
        backup();
        parser.reduce();
        return true;
    }

    
    
}
