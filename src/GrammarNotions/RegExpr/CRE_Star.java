/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.RegExpr;

import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;


/**
 *
 * @author ssanyu
 */
public class CRE_Star extends CRE {
    private CRE fArg;

    /**
     *
     * @param aArg
     */
    public CRE_Star(CRE aArg){
        fArg = aArg;
    }

    /**
     *
     * @param aArg
     */
    protected void setArg (CRE aArg){
         fArg = aArg;
    }

    /**
     *
     * @return
     */
    public CRE Arg(){
        return fArg;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scREStar;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "REStar";
    }

    /**
     *
     * @return
     */
    @Override
    public int termCount(){
        return 1;
    }
    @Override
    public CTerm getTerm(int i){
        if(i==0)
            return fArg;
        else return super.getTerm(i);
    }
    @Override
    public void setTerm(int i,CTerm aTerm){
        if(i==0)
            fArg=(CRE)aTerm;
        else super.setTerm(i,aTerm);
    }
   
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        return fArg.toText() + '*';
    }
   
    public String toString(){
        return fArg.toString() + '*';
    }
    /**
     *
     * @param aStar
     * @return
     */
    public static CRE fromText(String aStar){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aStar);
        vParser.reSet();
        vParser.parseRE();
        return vParser.fRETree;
    }
}
