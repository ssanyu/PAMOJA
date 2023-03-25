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
public class CSE_Empty extends CSE {

    /**
     *
     * @return
     */
    @Override
     public int sortCode(){
        return CGrammarCodes.scSEEmpty;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "SEEmpty";
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        return Character.toString('$');
    }
   
    public String toString(){
        return Character.toString('$');
    }
    /**
     *
     * @param aEmpty
     * @return
     */
    public static CSE fromText(String aEmpty){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aEmpty);
        vParser.reSet();
        vParser.parseXSE();
        return vParser.fSETree;
    }

}
