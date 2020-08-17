/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SymbolStream;

/**
 *
 * @author jssanyu
 */
public class CPosition {

    /**
     *
     */
    public int Line;

    /**
     *
     */
    public int Col;
    
    /**
     *
     */
    public CPosition(){
        Line=0;
        Col=0;
    }

    /**
     *
     * @param aLine
     * @param aCol
     */
    public CPosition(int aLine, int aCol){
        Line=aLine;
        Col=aCol;
    }
    
    /**
     *
     */
    public void reSet(){
        Line=0;
        Col=0;
    }
    
    /**
     *
     * @return
     */
    public String toText(){
        return "("+Line+","+Col+")";
    }
}
