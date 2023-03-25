/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scanners;

import SymbolStream.CSymKind;
import SymbolStream.CSymbolStream;



/**
 *
 * @author jssanyu
 */
public interface IScanner {
     
    /**
     *
     * @param aText
     */
    void setText(String aText);  // Input the text to be scanned

    /**
     *
     */
    void reSet();

    /**
     *
     * @return
     */
    int markPosition();  // Keep the start position of a symbol

    /**
     *
     * @param aMark
     */
    void reCallPosition(int aMark); // Set the scanner back to the marked position

    /**
     *
     */
    void recover();

    /**
     *
     */
    void nextSym();//gets the next symbol from the string

    /**
     *
     * @return
     */
    int getSym();

    /**
     *
     * @return
     */
    String getSymValue();// Get data for the symbol

    /**
     *
     * @return
     */
    boolean finished();// Check whether input is exhausted

    /**
     *
     * @return
     */
    String getSymName();

    /**
     *
     * @return
     */
    CSymKind getSymKind();

    /**
     *
     * @return
     */
    int getSymStart();

    /**
     *
     * @return
     */
    int getSymLength();

    /**
     *
     * @return
     */
    CSymbolStream getSymbolStream();

    /**
     *
     * @param aSymbolStream
     */
    void setSymbolStream( CSymbolStream aSymbolStream);
    
}
