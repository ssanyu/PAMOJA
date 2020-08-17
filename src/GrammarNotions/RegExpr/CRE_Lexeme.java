/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.RegExpr;

import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.Context_Terms.CItem;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;


/**
 *
 * @author ssanyu
 */
public class CRE_Lexeme extends CRE {
    private CLexemeDef fDef;
    private String fName;

    /**
     *
     * @param aDef
     * @param aName
     */
    public CRE_Lexeme(CLexemeDef aDef, String aName){
        fDef=aDef;
        fName=aName;
    }

    /**
     *
     * @param aDef
     */
    public void setDef(CLexemeDef aDef){
        fDef=aDef;
    }

    /**
     *
     * @return
     */
    public String getName(){
        if(fDef==null){
            return fName;
        }else{
            return fDef.getName();
        }

    }

    /**
     *
     * @param aName
     */
    protected void setName(String aName){
        assert fDef==null: String.format("In CRE_Lexeme.setName() failed: aName=%s",aName);
        fName=aName;
    }

    /**
     *
     * @return
     */
    public CLexemeDef getDef(){
        return fDef;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scRELexeme;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "RELexeme";
    }

    /**
     *
     * @return
     */
    public int refCount(){
        return 1;

    }

    /**
     *
     * @param i
     * @return
     */
    public CItem getRefence(int i){
        if(i==0)
            return fDef;
        else return super.getReference(i);
    }

    /**
     *
     * @param i
     * @param aRef
     */
    @Override
    public void setReference(int i , CItem aRef){
            if(i==0)
                fDef=(CLexemeDef)aRef;
            else
                super.setReference(i,aRef);
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
            return getName();
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
            setName(aData);
        else super.setData(i,aData);
    }

    /**
     *
     * @return
     */
    @Override
    public String toText(){
      return fName;
    }
   
    /**
     *
     * @param aLex
     * @return
     */
    public static CRE fromText(String aLex){
       CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aLex);
        vParser.reSet();
        vParser.parseRE();
        return vParser.fRETree;
    }
}
