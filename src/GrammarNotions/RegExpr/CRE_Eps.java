/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.RegExpr;

import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;


/**
 *
 * @author ssanyu
 */
public class CRE_Eps extends CRE {

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
            return CGrammarCodes.scREEps;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "REEps";
    }
   
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        return Character.toString('1');
    }
    
    /**
     *
     * @param aEps
     * @return
     */
    public static CRE fromText(String aEps){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aEps);
        vParser.reSet();
        vParser.parseRE();
        return vParser.fRETree;
    }
    
}
