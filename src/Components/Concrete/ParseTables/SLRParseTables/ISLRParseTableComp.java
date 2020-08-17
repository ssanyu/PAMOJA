/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables.SLRParseTables;


import Components.Concrete.ParseTables.IParseTableComp;
import SLR.SLRAutomata.CSLRTable;
import SLR.SLRAutomata.Move;
import java.util.ArrayList;

/**
 * Defines services for interacting with other components ( like, Grammar component).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ISLRParseTableComp extends IParseTableComp {
    

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
     * Returns a list of terminal symbols used in constructing the parse tables.
     * @return the list of terminal symbols 
     */
        public ArrayList<String> getStringAlphabet();

    /**
     * Returns SLR(1) parse-tables.
     * @return SLR(1) parse-tables
     */
    public ArrayList<Move>[][] getSLRTable1();    
    
}
