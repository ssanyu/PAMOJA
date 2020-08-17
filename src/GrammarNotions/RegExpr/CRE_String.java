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
public class CRE_String extends CRE {
    private String fStr;

    /**
     *
     * @param aStr
     */
    public CRE_String(String aStr){
        fStr=aStr;
    }

    /**
     *
     * @param aStr
     */
    protected void setStr(String aStr){
        fStr=aStr;
    }

    /**
     *
     * @return
     */
    public String  Str(){
        return fStr;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scREString;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "REString";
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
            return fStr;
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
            fStr=aData;
        else super.setData(i,aData);
    }
   
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        return '"' + fStr + '"';
    }
    
    /**
     *
     * @param aString
     * @return
     */
    public static CRE fromText(String aString){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aString);
        vParser.reSet();
        vParser.parseRE();
        return vParser.fRETree;
    }
}
