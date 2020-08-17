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
public class CRE_Range extends CRE {
    private char fLow;
    private char fHigh;

    /**
     *
     * @param aLow
     * @param aHigh
     */
    public CRE_Range(char aLow, char aHigh){
        fLow=aLow;
        fHigh=aHigh;
    }

    /**
     *
     * @param aLow
     */
    protected void setLow(char aLow){
        fLow=aLow;
    }

    /**
     *
     * @param aHigh
     */
    protected void setHigh(char aHigh){
        fHigh=aHigh;
    }

    /**
     *
     * @return
     */
    public char Low(){
        return fLow;
    }

    /**
     *
     * @return
     */
    public char High(){
        return fHigh;
    }

    /**
     *
     * @return
     */
    @Override
     public int sortCode(){
        return CGrammarCodes.scRERange;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "RERange";
    }

    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 2;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i){
        if(i==0)
            return Character.toString(fLow);
        else if(i==1)
            return Character.toString(fHigh);
        else return super.getData(i);
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i, String aData){
        if(i==0)
            fLow=aData.charAt(0);
        else if(i==1)
            fHigh=aData.charAt(0);
    }
   
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        return   "'"+fLow + "'-'" + fHigh+"'" ;
    }
   
    /**
     *
     * @param aRange
     * @return
     */
    public static CRE fromText(String aRange){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aRange);
        vParser.reSet();
        vParser.parseRE();
        return vParser.fRETree;
    }
    

}
