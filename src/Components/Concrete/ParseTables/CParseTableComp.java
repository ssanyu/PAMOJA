/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables;


import Components.CPAMOJAComponent;
import Components.Concrete.Grammar.IGrammarComp;
import java.beans.PropertyChangeListener;

/**
 * This is a base class from which all parse table components descend.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public abstract class CParseTableComp extends CPAMOJAComponent implements IParseTableComp,PropertyChangeListener {
    /**
     * A reference to a Grammar object
     */
    protected IGrammarComp Grammar=null; 
    
    /**
     * Creates a new instance of <code>CParseTableComp</code>.
     */
    public CParseTableComp(){
        super();
    }
    
     /**
     * Get the value of Grammar
     *
     * @return the value of Grammar
     */
     public IGrammarComp getGrammar() {
        return Grammar;
    }
/**
  * Links to <code>GrammarComp</code> component via its interface.
  * Sets the value of <code>Grammar</code> and registers for property change events.
  * 
  * @param aGrammar new value of Grammar
  */
    public void setGrammar(IGrammarComp aGrammar) {
       if(Grammar!=null){
              Grammar.removePropertyChangeListener(this);
       }
       Grammar=aGrammar;
       if(Grammar!=null){
              Grammar.addPropertyChangeListener(this);
       } 
       
    }
  
}
