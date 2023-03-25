/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Lexical.ScanTables;

import Automata.NFADFA.CDFA;
import Automata.NFADFA.CNFA;
import Components.IPAMOJAComponent;



/**
 * Defines services for interacting with other components ( like, Grammar component).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IScanTableComp extends IPAMOJAComponent{

    /**
     * Returns the structural representation of NFA tables.
     * @return the structural representation of NFA tables.
     */
    CNFA getNFATableStructure();

    /**
     * Sets the structural representation of NFA tables.
     * @param aNFATableStructure the structural representation of NFA tables.
     */
    void setNFATableStructure(CNFA aNFATableStructure);
   
    /**
     * Returns the textual representation of DFA tables.
     * @return the textual representation of DFA tables.
     */
        String getDFATableText();

    /**
     * Sets the textual representation of DFA tables.
     * @param aDFATableText the textual representation of DFA tables.
     */
    void setDFATableText(String aDFATableText);
    //internal representation of Scan tables

    /**
     * Returns the structural representation of DFA tables.
     * @return the structural representation of DFA tables.
     */
        CDFA getDFATableStructure();

    /**
     * Sets the structural representation of DFA tables.
     * @param aDFATableStructure the structural representation of DFA tables.
     */
    void setDFATableStructure(CDFA aDFATableStructure);
    
    /*
     * Invariant 1: Either DFATableText,DFATableStructure =null or
     *              DFATableText is equivalent to DFATableStructure
     */
    
}
