/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstractor;

import Nodes.CNode;
import Nodes.CNodeFactory;

/**
 * Interface describing a mapping from essential parse trees to 
 * abstract syntax trees.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IAbstraction{
   
   /**
     * Gets the TransformedParseTree object corresponding to <code> ParseTree </code>.
     *
     * @return an instance of CNode representing the TransformedParseTree.
     */
   CNode getTransformedParseTree();
 
    /**
     * Sets the TransformedParseTree object.
     *
     * @param aTransformedParseTree the CNode object
     */
    void setTransformedParseTree(CNode aTransformedParseTree); 
    
    /**
     * Transforms the assigned aParseTree object by replacing all occurrences of CMultiDotNode nodes by 
     * CTupleNode nodes, and returns an instance of the <code> TransformedParseTree </code>.
     * 
     * @param aParseTree the CNode object to be transformed.
     * @return an instance of <code> CNode </code> representing the <code> TransformedParseTree </code>.
     * @see GrammarNotions.ECFGNodes.ECFGTreeTransformations#trMultiDotToTuple
     */
    public CNode transformParseTree(CNode aParseTree);
   
    /**
     * Sets the NodeFactory object to be used in the creation of the different kinds
     * of abstract syntax tree nodes.
     *
     * @param aNodeFactory the CNodeFactory object.
     */
    void setNodeFactory(CNodeFactory aNodeFactory);

    /**
     * Returns a NodeFactory object responsible for creating the different kinds 
     *         of abstract syntax tree nodes.
     *
     * @return an instance of <code> CNodeFactory </code> representing the <code> NodeFactory </code>.
     */
    CNodeFactory getNodeFactory();
   
     /**
     * Sets the AST object after transformation of the ParseTree.
     *
     * @param aAST the CNode object.
     */
    void setAST(CNode aAST);

    /**
     * Gets the AST object corresponding to the ParseTree.
     *
     * @return an instance of CNode representing the AST.
     */
    CNode getAST();
    
}
