/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables;

import Components.Concrete.Grammar.IGrammarComp;
import Components.IPAMOJAComponent;



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
}
