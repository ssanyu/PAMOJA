/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.InterpretingParser.Deterministic;

import Components.Concrete.Parser.IParserCompExt;

/**
 * Defines services for interacting with other components ( like, Grammar, SymbolStream and Parser components).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IDeterministicParserComp extends IParserCompExt{

    /**
     * Determines if the parser is set to start parsing at any NonTerminal.
     * @return <code>true</code> if the parser is set to start parsing at any NonTerminal, otherwise <code>false</code>.
     */
    boolean isParseWithNonTerminal();

    /**
     * Sets the parseWithNonTerminal mode of this parser to the specified value.
     * @param parseWithNonTerminal if parseWithNonTerminal mode is allowed for this parser then <code>true</code>, else <code>false</code>.
     */
    void setParseWithNonTerminal(boolean parseWithNonTerminal);

    /**
     * Returns the name of a NonTerminal to be used as the start-expression for parsing.
     * @return the name of a NonTerminal to be used as the start-expression.
     */
    String getNonTerminal();

     /**
     * Sets the the given name of a NonTerminal to be used as the start-expression.
     * @param nonTerminal the name of a NonTerminal.
     */
    void setNonTerminal(String nonTerminal);
}
