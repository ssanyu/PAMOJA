/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.TableDrivenParser.SLRParser;


import Components.Concrete.ParseTables.LRParseTables.SLRParseTables.ISLRParseTableComp;
import Components.Concrete.Parser.TableDrivenParser.LRParser.ILRParserComp;
import TableGenerators.LR.CLRTable;
import TableGenerators.LR.SLR.SLRAutomata.CSLRTable;


/**
 * Provides services for collaborating with other components, like Grammar, SymbolStream, Treebuilder and SLRParseTable.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ISLRParserComp extends ILRParserComp{
    

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
        CSLRTable getTableStructure();

    /**
     * Sets the value of the internal structure of SLR(1) parse tables and notifies observers about <code>SLRTableStructure</code> property changes.
     * @param aSLRTableStructure the value of the internal structure of SLR(1) parse tables.
     */
     void setTableStructure(CLRTable aSLRTableStructure);
   
     ISLRParseTableComp getParseTable();
     void setParseTable(ISLRParseTableComp aParseTable);
    /**
     *
    /*
     * Invariant 1: Either SLRTableText,SLRTableStructure =null or
     *              SLRTableText is equivalent to SLRTableStructure
     */

    
   
}
