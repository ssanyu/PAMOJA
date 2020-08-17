/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrammarNotions.SyntaxExpr;

import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;


/**
 *
 * @author jssanyu
 */
public class CSE_Alt2 extends CSE {
     private CSE fLeft;
    private CSE fRight;

    /**
     *
     * @param aLeft
     * @param aRight
     */
    public CSE_Alt2(CSE aLeft, CSE aRight){
        fLeft = aLeft;
        fRight = aRight;
    }

    /**
     *
     * @param aLeft
     */
    protected void setLeft(CSE aLeft){
        fLeft = aLeft;
    }

    /**
     *
     * @param aRight
     */
    protected void setRight(CSE aRight){
        fRight = aRight;
    }

    /**
     *
     * @return
     */
    public CSE getLeft(){
        return fLeft;
    }

    /**
     *
     * @return
     */
    public CSE getRight(){
        return fRight;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scSEAlt2;

    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "SEAlt2";
    }

    /**
     *
     * @return
     */
    @Override
    public int termCount(){
        return 2;
    }
    @Override
    public void setTerm(int i, CTerm aTerm){
        if(i==0)
            fLeft=(CSE)aTerm;
        else if(i==1)
            fRight=(CSE)aTerm;
        else super.setTerm(i,aTerm);
    }
    @Override
    public CTerm getTerm(int i){
        if(i==0)
           return fLeft;
        else if(i==1)
            return fRight;
        else return super.getTerm(i);
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        return '(' + fLeft.toText() + '&' + fRight.toText() + ')';
    }
   
    /**
     *
     * @param aAlt2
     * @return
     */
    public static CSE fromText(String aAlt2){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aAlt2);
        vParser.reSet();
        vParser.parseXSE();
        return vParser.fSETree;
    }
    
}
