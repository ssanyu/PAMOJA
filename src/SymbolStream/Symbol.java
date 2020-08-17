/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SymbolStream;

/**
 *
 * @author ssanyu
 */
public class Symbol {
    private CSymKind fSymKind;
    private int fSymCode;
    private String fSymName;
    private String fSymValue;
    private int fSymLength;
    private int fSymStart;
            
  
    // constructor for Indent symbol

    /**
     *
     */
        public Symbol (){
        
    }

    /**
     *
     * @param aSymName
     * @param aSymValue
     */
    public Symbol(String aSymName, String aSymValue){
         fSymName=aSymName;
         fSymValue=aSymValue;
    }

    /**
     *
     * @param aSymName
     * @param aSymValue
     * @param aSymKind
     */
    public Symbol(String aSymName, String aSymValue, CSymKind aSymKind){
         fSymName=aSymName;
         fSymValue=aSymValue;
         fSymKind=aSymKind;
    }

    /**
     *
     * @param aSymCode
     * @param aSymName
     */
    public Symbol(int aSymCode, String aSymName){
         fSymCode=aSymCode;
         fSymName=aSymName;
         
    }

    /**
     *
     * @param aSymCode
     * @param aSymStart
     * @param aSymValue
     * @param aSymName
     * @param aSymLength
     * @param aSymKind
     */
    public Symbol(int aSymCode, int aSymStart, String aSymValue, String aSymName, int aSymLength,CSymKind aSymKind){
        fSymCode=aSymCode;
        fSymStart=aSymStart;
        fSymValue=aSymValue;
        fSymName=aSymName;
        fSymLength=aSymLength;
        fSymKind=aSymKind;
    }
    
    /**
     *
     * @return
     */
    public int symCode(){
        return fSymCode;
    }

    /**
     *
     * @return
     */
    public String symName(){
        return fSymName;
    }

    /**
     *
     * @return
     */
    public String symValue(){
        return fSymValue;
    }

    /**
     *
     * @return
     */
    public int symStart(){
        return fSymStart;
    }

    /**
     *
     * @return
     */
    public int symLength(){
        return fSymLength;
    }
   
    /**
     *
     * @return
     */
    public CSymKind symKind(){
        return fSymKind;
    }
    
    /**
     *
     * @return
     */
    public boolean hasData(){
        if(fSymKind.equals(CSymKind.VARIABLE))
           return true;
        else return false;
    }
   
 
}
