/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Lexical.SymbolStream;

import Components.Concrete.Flattener.IFlattenerComp;
import Components.IPAMOJAComponent;
import Components.Lexical.Scanners.IScannerComp;
import SymbolStream.CSymbolStream;



/**
 * An interface for SymbolSTream component.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ISymbolStreamComp extends IPAMOJAComponent {
    
    

    /**
     * Returns the internal representation of a symbol stream.
     * @return the internal representation of a symbol stream.
     */
    CSymbolStream getSymbolStreamStructure();

    /**
     * Sets the the internal representation of a symbol stream.
     * @param aSymbolStreamStructure the internal representation of a symbol stream.
     */
    void setSymbolStreamStructure(CSymbolStream aSymbolStreamStructure);

   

    /**
     * Returns the textual representation of a symbol stream.
     * @return the textual representation of a symbol stream.
     */
       String getSymbolStreamText();

    /**
     * Sets the textual representation of a symbol stream.
     * @param aSymbolStreamText the textual representation of a symbol stream.
     */
    void setSymbolStreamText(String aSymbolStreamText);
   
    /**
     * Connects to a Scanner component with the specified interface.
     * @param aScanner the specified interface.
     */
    void setScanner(IScannerComp aScanner);

    /**
     * Returns a reference to the Scanner component.
     * @return a reference to the Scanner component.
     */
    IScannerComp getScanner();
   
    /**
     * Connects to a Flattener component with the specified interface.
     * @param aFlattener the specified interface.
     */
    void setFlattener(IFlattenerComp aFlattener);

    /**
     * Returns a reference to a Flattener component.
     * @return a reference to a Flattener component.
     */
    IFlattenerComp getFlattener();
  /* Invariant 1: Either SymbolStreamText,SymbolStreamStructure= null or
   *             SymbolStreamText is equivalent to SymbolStreamStructure
   * Invariant 2: 
   */

    
}
