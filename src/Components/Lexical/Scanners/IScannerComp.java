 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.Scanners;

import Components.IPAMOJAComponent;
import Scanners.CScanner;
import SymbolStream.CSymbolStream;




/**
 * Interface for all scanner components providing services for interacting with other components (such as
 * parsers or language-aware editors) in the framework.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IScannerComp extends IPAMOJAComponent {
 
    /**
     * Returns a Scanner object associated with this Scanner component.
     * @return Scanner object associated with this Scanner component.
     */
    CScanner getScanner();

    /**
     * Accesses the symbol stream.
     * @return the symbol stream.
     */
     CSymbolStream getSymbolStream(); 

    /**
     * Sets the specified symbol stream.
     * @param aStream the CSymbolStream object.
     */
    void setSymbolStream(CSymbolStream aStream);

    /**
     * Scans the specified input text and creates a symbol stream.
     * @param aText the input text to scan.
     */
    void createSymbolStream(String aText);

}


