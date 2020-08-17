package GrammarNotions.RegExpr;

import GrammarNotions.Grammar.CGrammarCodes;
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
public class CRE_Char extends CRE{
    private char fChar;
    
    /**
     *
     * @param aChar
     */
    public CRE_Char(char aChar){
        fChar=aChar;
    }

    /**
     *
     * @param aChar
     */
    public void setChar(char aChar){
        fChar=aChar;
    }

    /**
     *
     * @return
     */
    public char Char(){
        return fChar;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
            return CGrammarCodes.scREChar;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "REChar";
    }

    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i){
        if(i==0)
            return Character.toString(fChar);
        else return super.getData(i);
   }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i,String aData){
        if(i==0)
            fChar=aData.charAt(0);
        else super.setData(i,aData);
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        return "'"+fChar+"'";
    }
    
    /**
     *
     * @param aChar
     * @return
     */
    public static CRE fromText(String aChar){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aChar);
        vParser.reSet();
        vParser.parseRE();
        return vParser.fRETree;
    }
}
