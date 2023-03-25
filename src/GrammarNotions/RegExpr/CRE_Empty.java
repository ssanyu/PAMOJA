/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrammarNotions.RegExpr;

import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;



/*
 * @author jssanyu
 */

/**
 *
 * @author HP
 */

public class CRE_Empty extends CRE {

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
            return CGrammarCodes.scREEmpty;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "REEmpty";
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
         return ""+'$';
     }
    /**
     *
     * @param aEmpty
     * @return
     */
    public static CRE fromText(String aEmpty){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aEmpty);
        vParser.reSet();
        vParser.parseRE();
        return vParser.fRETree;
    }
    
    
}
