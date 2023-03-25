/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables;

import Components.Concrete.Grammar.IGrammarComp;
import Components.IPAMOJAComponent;
import TableGenerators.CParseTable;



/**
 * Defines services for interacting with other components ( like, Grammar component).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IParseTableComp extends IPAMOJAComponent{
   
      /**
     * Get the value of Grammar
     *
     * @return the value of Grammar
     */
     IGrammarComp getGrammar(); 
/**
  * Links to <code>GrammarComp</code> component via its interface.
  * Sets the value of <code>Grammar</code> and registers for property change events.
  * 
  * @param aGrammar new value of Grammar
  */
   void setGrammar(IGrammarComp aGrammar);
   
    /**
     * Returns the string representation of  parse tables.
     * @return the string representation of L parse tables
     */
        String getTableText();

     /**
     * Sets the string representation of parse tables.
     * @param aTableText the string representation of parse tables to set.
     */
    void setTableText(String aTableText);

    /**
     *
     * @return
     */
    public CParseTable getTableStructure();

    /**
     *
     * @param TableStructure
     */
    public void setTableStructure(CParseTable TableStructure);
    
}
