/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.SyntaxExpr;

import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;


/**
 *
 * @author ssanyu
 */
public class CSE_Plus extends CSE {
     private CSE fArg;

    /**
     *
     * @param aArg
     */
    public CSE_Plus(CSE aArg){
        fArg=aArg;
    }

    /**
     *
     * @param aArg
     */
    protected void setArg(CSE aArg){
        fArg = aArg;
    }

    /**
     *
     * @return
     */
    public CSE getArg(){
        return fArg;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scSEPlus;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "SEPlus";
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
            fArg=(CSE)aTerm;
        else super.setTerm(i,aTerm);
    } 
    
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        return fArg.toText() + '+';
    }
   
    /**
     *
     * @param aPlus
     * @return
     */
    public static CSE fromText(String aPlus){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aPlus);
        vParser.reSet();
        vParser.parseXSE();
        return vParser.fSETree;
    }

}
