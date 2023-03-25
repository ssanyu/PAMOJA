/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.SyntaxExpr;

import GrammarNotions.Context_Terms.CTermTuple;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;

/**
 *
 * @author ssanyu
 */
/* Base class for syntax expressions ocurring on the right-hand side of
 production rules of an ECFG */
public class CSE extends CTermTuple {

    /**
     *
     */
    public CSE(){
        super();
    }
   
    /**
     *
     * @return
     */
    @Override
     public int sortCode(){
        return CGrammarCodes.scSE;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "SE";
    }

    /**
     *
     * @return
     */
    public String toText(){
        return "";
    }
    
    /**
     *
     * @param aSE
     * @return
     */
    public static CSE fromText(String aSE){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aSE);
        vParser.reSet();
        vParser.parseXSE();
        return vParser.fSETree;
    }
    
}



