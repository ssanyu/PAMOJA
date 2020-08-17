/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.Abstraction;

import Components.Concrete.ParseTree.IParseTreeComp;
import Components.Concrete.Parser.IParserComp;
import Components.INodeObject;

import Nodes.CNode;
import Nodes.CNodeFactory;

/**
 * Provides services for interacting with other components ( like, AST, ParseTree and Parser components) and the node factory.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IAbstractionComp extends INodeObject {
    
     /**
     * Connects to the ParseTree component via its interface. 
     * Registers for property change events, retrieves the parse tree object and rebuilds its AST.
     *
     * @param aParseTree new value of <code>ParseTree</code>
     */
    void setParseTree(IParseTreeComp aParseTree);

     /**
     * Accesses the interface of the ParseTree component. 
     *
     * @return the ParseTree component's interface.
     */
    IParseTreeComp getParseTree();
    
     /**
     * Connect to a parser component via its interface.
     * Register for property change events, retrieve current value of parse tree object and rebuild the AST.
     *
     * @param aParser the parser interface.
     */
    void setParser(IParserComp aParser);

    /**
     * Access the interface of a Parser component.
     *
     * @return the Parser interface.
     */
    IParserComp getParser();
    
    /**
     * Set the value of AST
     *
     * @param aAST new value of AST
     */
    void setAST(CNode aAST);

     /**
     * Return the value of AST
     *
     * @return the value of AST
     */
    CNode getAST();
    
    /**
     * Set the NodeFactory object for creating AST nodes.
     *
     * @param aNodeFactory the CNodefactory object. 
     */
    public void setNodeFactory(CNodeFactory aNodeFactory);
}
