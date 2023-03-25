package GrammarNotions.RegExpr;

import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.Context_Terms.CTermTuple;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chemerik
 */
/* Base class for all forms of regular expressions over characters */
public class CRE extends CTermTuple{
   
    /**
     *
     */
    public CRE(){
        super();
    }
    
    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scRE;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "CRE";

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
     * @param aRE
     * @return
     */
    public static CRE fromText(String aRE){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aRE);
        vParser.reSet();
        vParser.parseRE();
        return vParser.fRETree;
    }

}
