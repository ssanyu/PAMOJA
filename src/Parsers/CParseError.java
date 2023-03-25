package Parsers;

import SymbolStream.CPosition;

/**
 *
 * @author HP
 */
public class CParseError{

    /**
     *
     */
    public String fMsg;

    /**
     *
     */
    public int fSym;

    /**
     *
     */
    public String fSymName;

    /**
     *
     */
    public String fSymValue;
   // public int fSymStart;

    /**
     *
     */
        public CPosition fPosition;
    
    /**
     *
     * @param aMsg
     * @param aSym
     * @param aSymName
     * @param aSymValue
     * @param aPosition
     */
    public CParseError(String aMsg,int aSym,String aSymName,String aSymValue,CPosition aPosition){
        fMsg=aMsg;
        fSym=aSym;
        fSymName=aSymName;
        fSymValue=aSymValue;
        fPosition=aPosition;
    }
    
    @Override
    public String toString(){
        // Should the other fields be included in the msg?
        return fMsg+':'+" "+ fSymName+" "+"at line: "+fPosition.Line+" , col: "+fPosition.Col;
    }
    
}