/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.TableDrivenParser.SLRParser;


import Components.Concrete.Parser.IParserCompExt;
import SLR.SLRAutomata.CSLRTable;
import SLR.SLRAutomata.Move;
import java.util.ArrayList;

/**
 * Provides services for collaborating with other components, like Grammar, SymbolStream, Treebuilder and SLRParseTable.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ISLRParserComp extends IParserCompExt {
    

     /**
     * Returns the string representation of SLR(1) parse tables.
     * @return the string representation of SLR(1) parse tables
     */
     String getSLRTableText();

    /**
     * Sets the string representation of SLR(1) parse tables.
     * @param aSLRTableText the string representation of SLR(1) parse tables to set.
     */
    void setSLRTableText(String aSLRTableText);
   

    /**
     * Returns internal representation of SLR(1) parse tables.
     * @return the internal representation of SLR(1) parse tables
     */
        CSLRTable getSLRTableStructure();

    /**
     * Sets the value of the internal structure of SLR(1) parse tables and notifies observers about <code>SLRTableStructure</code> property changes.
     * @param aSLRTableStructure the value of the internal structure of SLR(1) parse tables.
     */
    void setSLRTableStructure(CSLRTable aSLRTableStructure);
   

    /**
     *  Return the Grammar alphabet (terminals and Nonterminal names)
     *  @return Array of grammar alphabet.
     */
        public ArrayList<String> getStringAlphabet();

    /**
     *
     * @return
     */
    public ArrayList<Move>[][] getSLRTable1();
  

    
    
    /*
     * Invariant 1: Either SLRTableText,SLRTableStructure =null or
     *              SLRTableText is equivalent to SLRTableStructure
     */

    /**
     * Parses input string as a symbol stream.
     */
    void parseText();
}
