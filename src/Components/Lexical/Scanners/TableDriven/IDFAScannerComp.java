/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.Scanners.TableDriven;

import Components.Lexical.ScanTables.IScanTableComp;
import Components.Lexical.Scanners.IScannerComp;



/**
 * An interface for DFAScanner component.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IDFAScannerComp extends IScannerComp {
    
    /**
     * Returns a reference to ScanTable component.
     * @return a reference to ScanTable component
     */
    IScanTableComp getScanTables(); 
    
    /**
     * Connects a DFAScanner component to ScanTable component.
     *
     * 
     * @param aScanTables a reference to ScanTables component.
     * @pre ScanTables !=null
     */
  
   void setScanTables(IScanTableComp aScanTables);
 /*   //textual representation of DFATables
    String getDFATableText();
    void setDFATableText(String aDFATableText);
    //internal representation of DFATables
    CDFA getDFATableStructure();
    void setDFATableStructure(CDFA aDFATableStructure);
    
    /*
     * Invariant 1: Either DFATableText,DFATableStructure =null or
     *              DFATableText is equivalent to DFATableStructure
     */

}
