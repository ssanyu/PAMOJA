/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables.LLParseTables;


import Components.Concrete.ParseTables.IParseTableComp;
import TableGenerators.CParseTable;
import TableGenerators.LL.LLTable.CLLTable;

import java.util.ArrayList;

/**
 * Defines services for interacting with other components ( like, Grammar component).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ILLParseTableComp extends IParseTableComp {
    

    /**
     * Returns internal representation of LL(1) parse tables.
     * @return the internal representation of LL(1) parse tables
     */
        CLLTable getTableStructure();

    /**
     * Sets the value of the internal structure of LL(1) parse tables and notifies observers about <code>LLTableStructure</code> property changes.
     * @param aLLTableStructure the value of the internal structure of LL(1) parse tables.
     */
    void setTableStructure(CParseTable aLLTableStructure);
    

    /**
     * Returns a list of non-terminal symbol-names used in constructing the parse tables.
     * @return the list of non-terminal symbol-names 
     */
        public ArrayList<String> getNontermAlphabet();

        /**
     * Returns a list of terminal symbol values and non-terminal symbol-names to be used in constructing the parse tables.
     * @return the list of terminal symbol values and non-terminal symbol-names
     */
        public ArrayList<String> getTermAlphabet();
    
    
}
