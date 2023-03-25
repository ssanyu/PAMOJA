/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTree;

import Components.Concrete.Parser.IParserComp;
import Components.IPAMOJAComponent;
import GrammarNotions.ECFGNodes.CECFGNode;



/**
 * Defines services for interacting with other components ( like, Parser component).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IParseTreeComp extends IPAMOJAComponent {
   /**
     * Get the value of ParseTreeStructure
     *
     * @return the value of ParseTreeStructure
     */
    CECFGNode getParseTreeStructure();

     /**
     * Sets the value of ParseTreeStructure, generates the corresponding string representation of a parse tree structure and notifies observers about <code>ParseTreeStructure</code> property changes.
     *
     * @param aParseTreeStructure
     */
    void setParseTreeStructure(CECFGNode aParseTreeStructure);

    
    /**
     * Get the value of ParseTreeText
     *
     * @return the value of ParseTreeText
     */
    String getParseTreeText();

    /**
     * Set the value of ParseTreeTex and notify observers about <code>ParseTreeText</code> property changes.
     *
     * @param aParseTreeText new value of ParseTreeText
     */
    void   setParseTreeText(String aParseTreeText);
   
    /**
     * Link to a parser component via its interface -- Set the value of the parser.
     * Register for property change events and retrieve current internal structure of a parse tree and update the internal structure of this parse tree.
     *
     * @param aParser new value of Parser
     */
    void setParser(IParserComp aParser);

    /**
     * Get the value of Parser
     *
     * @return the value of Parser
     */
    IParserComp getParser();
    
}
