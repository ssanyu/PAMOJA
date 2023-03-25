package Components.Concrete.Parser;


import Components.CPAMOJAComponent;
import Components.Concrete.Grammar.IGrammarComp;
import Components.INodeObject;
import Components.Lexical.SymbolStream.ISymbolStreamComp;
import Parsers.CParseLog;
import Parsers.CParseStack;


import Parsers.CParser;
import SymbolStream.Symbol;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

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
    protected ArrayList<CParseLog> Logs;
    /**
     *
     */
    protected String nonTerminal;

    /**
     *
     */
    protected CParseStack StateStack;  // Stack of States

    /**
     *
     */
    protected ArrayList<Symbol> symbolsUnmatched;

    /**
     *
     */
    protected ArrayList<Symbol> symbolsMatched;

    /**
     *
     */
    protected String stringUnmatched;

    /**
     *
     */
    protected String stringMatched;
    /**
     * Creates an instance of a Parser component.
     */
    public CParserComp(){
        super();
        parseWithNonTerminal=false;
        Logs=new ArrayList();
        StateStack=new CParseStack();
        symbolsMatched=new ArrayList();
        symbolsUnmatched=new ArrayList();
        stringUnmatched=new String();
        stringMatched=new String();
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
     *
     * @return
     */
    public ArrayList<CParseLog> getLogs() {
        return Logs;
    }

    /**
     *
     * @param Logs
     */
    public void setLogs(ArrayList<CParseLog> Logs) {
        this.Logs = Logs;
    }
 
    /**
     *
     * @return
     */
    public CParseStack getStateStack() {
        return StateStack;
    }

    /**
     *
     * @param StateStack
     */
    public void setStateStack(CParseStack StateStack) {
        this.StateStack = new CParseStack();
        this.StateStack.pushAll(StateStack);
    }

    /**
     *
     * @return
     */
    public ArrayList<Symbol> getSymbolsUnmatched() {
        return symbolsUnmatched;
    }

    /**
     *
     * @param symbolsUnmatched
     */
    public void setSymbolsUnmatched(ArrayList<Symbol> symbolsUnmatched) {
        this.symbolsUnmatched =new ArrayList();
        this.symbolsUnmatched.addAll(symbolsUnmatched);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Symbol> getSymbolsMatched() {
        return symbolsMatched;
    }

    /**
     *
     * @param symbolsMatched
     */
    public void setSymbolsMatched(ArrayList<Symbol> symbolsMatched) {
        this.symbolsMatched = new ArrayList();
        this.symbolsMatched.addAll(symbolsMatched);
    }

    /**
     *
     * @return
     */
    public String getStringUnmatched() {
        return stringUnmatched;
    }

    /**
     *
     * @param stringUnmatched
     */
    public void setStringUnmatched(String stringUnmatched) {
        this.stringUnmatched = stringUnmatched;
    }

    /**
     *
     * @return
     */
    public String getStringMatched() {
        return stringMatched;
    }

    /**
     *
     * @param stringMatched
     */
    public void setStringMatched(String stringMatched) {
        this.stringMatched = stringMatched;
    }
    
    /**
     *
     */
    public abstract void parseText();
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
    
     
     
       
    }
    
    
    
