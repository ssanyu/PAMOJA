/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser;

import Components.Concrete.Grammar.IGrammarComp;
import Components.IPAMOJAComponent;
import Components.Lexical.SymbolStream.ISymbolStreamComp;
import Parsers.CParser;
import Parsers.CParserResult;

/**
 * Defines services for interacting with other components ( like, Grammar and SymbolStream components).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IParserComp extends IPAMOJAComponent {

    /**
     * Returns a parser object. 
     * This an abstract method to be implemented by the descendants.
     * @return the parser object.
     */
    CParser getParser();

   /**
     * Sets the specified parser object to this parser component.
     * This an abstract method to be implemented by the descendants.
     * @param parser @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
     */
    void setParser(CParser parser);

 /**
     * Returns a parser-result object containing boolean value (indicating whether a parse was successful or not) and the parse tree constructed. 
     * This an abstract method to be implemented by the descendants.
     * 
     * @return the value of the parser-result object.
     */
 CParserResult getParserResult(); 

    /**
    * Links to <code>GrammarComp</code> component via its interface.
    * Sets the value of <code>Grammar</code> and registers for property change events.
    * 
    * @param aGrammar new value of Grammar
    */
    void setGrammar(IGrammarComp aGrammar);

   /**
     * Get the grammar object.
     * @return the value of the grammar object
     */ 
    IGrammarComp getGrammar();
 
     /**
     * Get the symbolstream object.
     * @return the value of the symbolstream object
     */ 
    ISymbolStreamComp getSymbolStream();

    /**
    * Links to <code>SymbolStreamComp</code> component via its interface.
    * Sets the value of <code>SymbolStream</code> and registers for property change events.
    * 
     * @param aSymbolStream
    */
    void setSymbolStream(ISymbolStreamComp aSymbolStream);
    
}
