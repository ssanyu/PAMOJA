/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

import GrammarNotions.ECFGNodes.CECFGNode;
import java.util.ArrayList;

/**
 *
 * @author jssanyu
 */
public interface IParser {
     /** 
     * Resets the parser state.
     * <p>
     * The state is set as follows:
     * <ul>
     *   <li> Removes all the error messages from the error list. The list will
     * be empty after this call returns.
     *   <li> Moves the cursor to the beginning of the stream attached to this
     *        parser.
     *   <li> Resets the result state of this parser by setting the success 
     *        property to false and frees the tree.  
     *  </ul>
     */
    void reSet();
    /**
     * Matches the current symbol (without data) from the stream with the lookahead symbol 
     * specified.
     * <p>
     * If the symbols match, the cursor is moved to the next symbol in the stream.
     * Otherwise, an InvalidTerminalException is thrown.
     * @param aSym The code of the lookahead symbol.
     * @throws InvalidTerminalException if the current symbol from the stream
     * does not match the lookahead symbol aSym.
     */
    void term(int aSym)throws InvalidTerminalException;
    /**
     * Matches the current symbol (with data) from the stream with the 
     * lookahead symbol and returns its data.
     * <p>
     * If the symbols match, the data of the current symbol is obtained and the
     * cursor is moved to the next symbol in the stream.
     * Otherwise, an InvalidTerminalException is thrown.
     * @param aSym The code of the lookahead symbol.
     * @return
     * @throws InvalidTerminalException 
     */
    String termData(int aSym)throws InvalidTerminalException;
    /**
     * Requests the stream connected to this parser to move the cursor to the
     * next symbol.
     */
    void nextSym();

    /**
     *
     */
    void previousSym();

    /**
     *
     * @return
     */
    CParserResult getParserResult(); 

    /**
     *
     * @param aParserResult
     */
    void setParserResult(CParserResult aParserResult);

    /**
     *
     * @return
     */
    CECFGNode getParseTree(); 

    /**
     *
     * @param aParseTree
     */
    void setParseTree(CECFGNode aParseTree);

    /**
     *
     * @return
     */
    ArrayList<CParseError> getParseErrorList();

    /**
     *
     * @param aParseErrorList
     */
    void setParseErrorList(ArrayList<CParseError> aParseErrorList);
    /**
     * Begins the process of deriving a given sentence based on the given grammar.
     * <p>
     * The method is abstract and any descendants of {@code CParser} should implement 
     * it to provide customized parsing.
     * @return <code>true and a parse tree</code> if the sentence belongs to
     * the language of the grammar.
     *         <code>false and no parse tree</code> otherwise.
     */
    CParserResult parse();
    /**
     * Begins the process of deriving a given sentence based on the given 
     * grammar and at the start expression specified.
     * <p>
     * The method is abstract and any descendants of {@code CParser} should implement 
     * it to provide customized parsing.
     * @param aName The Nonterminal to be used as the start expression.
     * @return <code>true and a parse tree</code> if the sentence belongs to
     * the language of the grammar.
     *         <code>false and no parse tree</code> otherwise.
     * @see CParserResult
     */
    
    CParserResult parseNonTerminal(String aName);// Does this apply to all parsers?

    /**
     *
     * @return
     */
    String getSymbolStreamValues();

    /**
     *
     * @return
     */
    CParseStack getStack();

    /**
     *
     * @param Stack
     */
    void setStack(CParseStack Stack);
}
