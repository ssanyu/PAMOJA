package Components.Concrete.Parser;


import Components.CPAMOJAComponent;
import Components.Concrete.Grammar.IGrammarComp;
import Components.INodeObject;
import Components.Lexical.SymbolStream.ISymbolStreamComp;


import Parsers.CParser;
import Parsers.CParserResult;
import java.beans.PropertyChangeListener;

/**
 * This is intended to be an abstract base class for both interpreting parser and table-driven parser components.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */

public abstract class CParserComp extends CPAMOJAComponent implements IParserComp,INodeObject,PropertyChangeListener{
    /**
     * A reference to the Grammar object.
     */
    protected IGrammarComp Grammar=null;  
    /**
     * A reference to Symbol Stream object.
     */
    protected ISymbolStreamComp SymbolStream=null;  
    
    /**
     * Creates a new instance of <code>CParserComp</code>.
     */
    protected boolean parseWithNonTerminal;

    /**
     *
     */
    protected String nonTerminal;

    /**
     * Creates an instance of a Parser component.
     */
    public CParserComp(){
        super();
        parseWithNonTerminal=false;
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
     /**
     * Get the symbolstream object.
     * @return the value of the symbolstream object
     */  
    public ISymbolStreamComp getSymbolStream() {
        return SymbolStream;
    }
    /**
    * Links to <code>SymbolStreamComp</code> component via its interface.
    * Sets the value of <code>SymbolStream</code> and registers for property change events.
    * 
     * @param aSymbolStream
    */
    public void setSymbolStream(ISymbolStreamComp aSymbolStream) {
       if(SymbolStream!=null){
              SymbolStream.removePropertyChangeListener(this);
       }
       SymbolStream=aSymbolStream;
       if(SymbolStream!=null){
              SymbolStream.addPropertyChangeListener(this);
       }
    }
    /**
     * Returns a parser object. 
     * This an abstract method to be implemented by the descendants.
     * @return the parser object.
     */
     public abstract CParser  getParser();

    /**
     * Sets the specified parser object to this parser component.
     * This an abstract method to be implemented by the descendants.
     * @param parser @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
     */
    public abstract void setParser(CParser parser);
    /**
     * Returns a parser-result object containing boolean value (indicating whether a parse was successful or not) and the parse tree constructed. 
     * This an abstract method to be implemented by the descendants.
     * 
     * @return the value of the parser-result object.
     */
     public abstract CParserResult getParserResult(); 
     
     
       
    }
    
    
    
