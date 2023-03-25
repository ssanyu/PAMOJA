/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.SyntaxExpr;

import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;

/**
 *
 * @author ssanyu
 */
public class CSE_Eps extends CSE {
  
    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scSEEps;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "CSEEps";
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        return Character.toString('~');
    }
    public String toString(){
        return Character.toString('~');
    }
    /**
     *
     * @param aEps
     * @return
     */
    public static CSE fromText(String aEps){
       CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aEps);
        vParser.reSet();
        vParser.parseXSE();
        return vParser.fSETree;
    }


}
