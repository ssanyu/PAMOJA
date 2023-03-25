/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables;


import Components.CPAMOJAComponent;
import Components.Concrete.Grammar.IGrammarComp;
import GrammarNotions.Grammar.CGrammar;
import TableGenerators.CParseTable;
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
     * The string representation of parse tables.
     */
    protected String TableText=""; 
    
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
  
     /**
     * Returns the string representation of  parse tables.
     * @return the string representation of  parse tables
     */
    @Override
   public String getTableText(){
       return TableText;
   }
   
    /**
     * Sets the string representation of  parse tables.
     * @param aTableText the string representation of  parse tables to set.
     */
    @Override
    public void setTableText(String aTableText){
      //TO DO  
    }

    /**
     *
     * @param aTable
     */
    public abstract void setTableStructure(CParseTable aTable);

    /**
     *
     * @return
     */
    public abstract CParseTable getTableStructure();

    /**
     *
     */
    protected abstract void clear();

    /**
     *
     * @param aGrammar
     */
    protected abstract void updateTables(CGrammar aGrammar);
}
