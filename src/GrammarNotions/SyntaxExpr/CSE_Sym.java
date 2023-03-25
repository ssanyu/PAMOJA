/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.SyntaxExpr;

import GrammarNotions.Context_Terms.CItem;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;

/**
 *
 * @author ssanyu
 */
public class CSE_Sym extends CSE {
     private CSymDec fDec;
     private String fName;

    /**
     *
     * @param aName
     * @param aDec
     */
    public CSE_Sym(String aName,CSymDec aDec){
        fName=aName;
        fDec=aDec;
    }

    /**
     *
     * @param aDec
     */
    public void setDec(CSymDec aDec){
        fDec = aDec;
    }

    /**
     *
     * @return
     */
    public CSymDec getDec(){
        return fDec;
    }

    /**
     *
     * @param aName
     */
    protected void setName(String aName){
        assert fDec==null : String.format("CSESym.setName() failed: aName=%s",aName);
    }

    /**
     *
     * @return
     */
    public String getName(){
       if(fDec==null)
            return fName;
        else return fDec.getName();
         
    }

    /**
     *
     * @return
     */
    @Override
     public int sortCode(){
            return CGrammarCodes.scSESym;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "SESym";
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
     * @return
     */
    @Override
    public int referenceCount(){
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
    @Override
    public CItem getReference(int i){
        if(i==0)
            return fDec;
        else return super.getReference(i);
    }

    /**
     *
     * @param i
     * @param aRef
     */
    @Override
    public void setReference(int i,CItem aRef){
        if(i==0)
            fDec=(CSymDec)aRef;
        else super.setReference(i,aRef);
    }
   
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        
       if(fDec!=null){
         if(fDec instanceof CTerminalDec &&((CTerminalDec)fDec).hasData()){
            return fDec.getName()+'@';
         }else{
             return fDec.getName();
         }
       }else{
         return fName;
       }
   }
   public String toString(){
        
       if(fDec!=null){
         if(fDec instanceof CTerminalDec &&((CTerminalDec)fDec).hasData()){
            return fDec.getName();
         }else{
             return fDec.getName();
         }
       }else{
         return fName;
       }
   }
    /**
     *
     * @param aSym
     * @return
     */
    public static CSE fromText(String aSym){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aSym);
        vParser.reSet();
        vParser.parseXSE();
        return vParser.fSETree;
    }



}
