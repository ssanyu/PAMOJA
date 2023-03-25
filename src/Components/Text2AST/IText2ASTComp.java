/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Text2AST;

import Components.IPAMOJAComponent;
import Components.Specifications.Language.ILanguageComp;
import Nodes.CNode;
import Nodes.CNodeFactory;
import SymbolStream.CSymbolStream;

/**
 * Interface provides services for interacting with other components such as Language and Typecheckers.
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IText2ASTComp extends IPAMOJAComponent {
    
    /**
     * Returns a symbolstream.
     * 
     * @return the CSymbolStream object.
     */
    CSymbolStream getSymbolStream();

    /**
     * Sets the specified text to this Text2AST component.
     * @param text the String object.
     */
    void setText(String text);

    /**
     * Returns Parser generated messages.
     * @return Parser generated messages.
     */
    String getParserMsg();

    /**
     * Returns an AST.
     * @return the CNode object.
     */
    CNode getAst();

    /**
     * Sets parser generated messages to this Text2AST component.
     * @param parserMsg parser generated messages
     */
    void setParserMsg(String parserMsg);

    /**
     * Returns a Language reference.
     * @return
     */
    ILanguageComp getLanguage();

    /**
     * Connects to the Language component.
     * @param aLanguage ILanguageComp
     */
    void setLanguage(ILanguageComp aLanguage);

     /**
     * Returns a NodeFactory object associated with this Text2AST.
     * 
     * @return CNodeFactory object.
     */
    CNodeFactory getNodeFactory();

    /**
     * Sets the NodeFactory to be used by the Text2AST.
     * 
     * @param nodeFactory the nodefactory to be set.
     */
    void setNodeFactory(CNodeFactory nodeFactory);
    
    /**
     * Returns a NonTerminal used as a start expression for parsing.
     * @return a NonTerminal name
     */
    String getNonTerminal();
 
    /**
     * Sets the specified NonTerminal to be used as a start expression for parsing.
     * @param nonTerminal a NonTerminal name
     */
    void setNonTerminal(String nonTerminal); 
}
