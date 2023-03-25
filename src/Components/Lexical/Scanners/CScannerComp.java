/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.Scanners;


import Components.CPAMOJAComponent;
import Components.Concrete.Grammar.IGrammarComp;
import SymbolStream.CSymbolStream;
import java.beans.PropertyChangeListener;
/**
 * This is an abstract base class for all Scanner components.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public abstract class CScannerComp extends CPAMOJAComponent implements IScannerComp,PropertyChangeListener{

    /**
     *
     */
    protected CSymbolStream SymbolStream;

    /**
     *
     */
    protected IGrammarComp Grammar;

    /**
     *
     */
    public CScannerComp(){
       super();
       SymbolStream=new CSymbolStream();
   }
    /**
     * Get the grammar object.
     * @return the value of the grammar object
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
  //Abstract methods
    @Override
  public abstract void createSymbolStream(String aText); 
    @Override
  public abstract CSymbolStream getSymbolStream(); 

    /**
     *
     * @param aStream
     */
    @Override
  public abstract void setSymbolStream(CSymbolStream aStream);
}