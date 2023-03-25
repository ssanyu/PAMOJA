/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstractor;

import GrammarNotions.ECFGNodes.*;
import Nodes.CNode;
import Nodes.CNodeFactory;
import Nodes.CNodeKind;
import Nodes.INodeList;
import Nodes.INodeOption;

/**
 * A class that realizes a mapping from essential parse trees to 
 * abstract syntax trees.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CAbstraction implements IAbstraction{

    private CNode ParseTree;
    private CNode TransformedParseTree;
    private CNodeFactory NodeFactory;
    private CNode AST;

    /**
     *  Creates a new <code> CAbstraction </code>
     */
    public CAbstraction() {
             ParseTree=null;
             TransformedParseTree=null;
             NodeFactory=null;
             AST=null;
           }

    /**
     * Gets the ParseTree object associated with this CAbstraction.
     *
     * @return an instance of CNode representing the ParseTree of this CAbstraction.
     */
    public CNode getParseTree() {
        return ParseTree;
    }

    /**
     * Sets the ParseTree object that is mapped to an abstract syntax tree.
     *
     * @param aParseTree the CNode object.
     */
    public void setParseTree(CNode aParseTree) {
        ParseTree = aParseTree;
    }

    /**
     * Gets the TransformedParseTree object corresponding to <code> ParseTree </code>.
     *
     * @return an instance of CNode representing the TransformedParseTree.
     */
    @Override
    public CNode getTransformedParseTree() {
        return TransformedParseTree;
    }

    /**
     * Sets the TransformedParseTree object.
     *
     * @param aTransformedParseTree the CNode object
     */
    @Override
    public void setTransformedParseTree(CNode aTransformedParseTree) {
        TransformedParseTree = aTransformedParseTree;
    }

    
    /**
     * Gets the AST object corresponding to the ParseTree.
     *
     * @return an instance of CNode representing the AST.
     */
    @Override
    public CNode getAST() {
        return AST;
    }

    /**
     * Sets the AST object after transformation of the ParseTree.
     *
     * @param aAST the CNode object.
     */
    @Override
    public void setAST(CNode aAST) {
       AST = aAST;
    }


    /**
     * Returns a NodeFactory object responsible for creating the different kinds 
     *         of abstract syntax tree nodes.
     *
     * @return an instance of <code> CNodeFactory </code> representing the <code> NodeFactory </code>.
     */
    @Override
    public CNodeFactory getNodeFactory() {
        return NodeFactory;
    }

    /**
     * Sets the NodeFactory object to be used in the creation of the different kinds
     * of abstract syntax tree nodes.
     *
     * @param aNodeFactory the CNodeFactory object.
     */
    @Override
    public void setNodeFactory(CNodeFactory aNodeFactory) {
       NodeFactory = aNodeFactory;
    }

    /**
     * Transforms the assigned aParseTree object by replacing all occurrences of CMultiDotNode nodes by 
     * CTupleNode nodes, and returns an instance of the <code> TransformedParseTree </code>.
     * 
     * @param aParseTree the CNode object to be transformed.
     * @return an instance of <code> CNode </code> representing the <code> TransformedParseTree </code>.
     * @see ECFGTreeTransformations#trMultiDotToTuple
     */
    @Override
    public CNode transformParseTree(CNode aParseTree){
        ParseTree=aParseTree;
        return TransformedParseTree=ECFGTreeTransformations.trMultiDotToTuple((CECFGNode)ParseTree);
       
    }
    
    /**
     * Returns the AST object corresponding to given ParseTree object.
     * 
     * @param aNode the CECFGNode object to be mapped to an AST.
     * @return an instance of <code> CNode </code> representing the <code> AST </code>.
     */
    public CNode abstr(CECFGNode aNode){
       
        
      switch(aNode.sortCode()){
          
          case CECFGNodeCodes.scEpsNode:
              return abstrEpsNode( (CEpsNode) aNode);
          case CECFGNodeCodes.scTermNode:
              return abstrTermNode( (CTermNode) aNode);
          case CECFGNodeCodes.scTermValueNode:
              return abstrTermValueNode( (CTermValueNode) aNode);
          case CECFGNodeCodes.scNonTermNode:
              return abstrNonTermNode( (CNonTermNode) aNode);
          case CECFGNodeCodes.scMultiDotNode:
               return abstrMultiDotNode( (CMultiDotNode) aNode);
          case CECFGNodeCodes.scMultiStickNode:
              
              return abstrMultiStickNode( (CMultiStickNode) aNode);
          case CECFGNodeCodes.scStarNode:
              return abstrStarNode((CStarNode) aNode);
          case CECFGNodeCodes.scPlusNode:
              return abstrPlusNode((CPlusNode) aNode);
          case CECFGNodeCodes.scOptionNode:
             return abstrOptionNode((COptionNode) aNode);
         //case CECFGNodeCodes.scECFGNodeList:
          case CECFGNodeCodes.scTupleNode:
              return abstrTupleNode( (CTupleNode) aNode);
          case CECFGNodeCodes.scAltNode:
              return abstrAltNode((CAltNode) aNode);
         //case CECFGNodeCodes.scChainNode:
          default: return null;
      }  
     
    }
    
    /**
     * Returns a <code> null </code> node. This method is intended to be used to prevent 
     * mapping of ParseTree nodes of type <code> CEpsNode </code> to AST nodes. There are no <code>CEpsNode</code> nodes 
     * in the abstract syntax.
     * 
     * @param aNode the CEpsNode to be mapped.
     * @return always returns <code> null </code>.
     */
    public CNode abstrEpsNode(CEpsNode aNode){
        assert false: "Method CAbstraction.abstrEpsNode should never be called.";
        return null;
    }
    
     /**
     * Returns a <code> null </code> node. This method is intended to be used to prevent 
     * mapping of ParseTree nodes of type <code> CTermNode </code> to AST nodes. There are no <code>CTermNode</code> nodes 
     * in the abstract syntax.
     * 
     * @param aNode the CEpsNode to be mapped.
     * @return always returns <code> null </code>.
     */
    public CNode abstrTermNode(CTermNode aNode){
        assert false: "Method CAbstraction.abstrTermNode should never be called.";
        return null;
    }
    
    /**
     * Returns a <code> null </code> node. This method is intended to be used to prevent 
     * mapping of ParseTree nodes of type <code> CTermValueNode </code> to AST nodes. There are no <code>CTermValueNode</code> nodes 
     * in the abstract syntax.
     * 
     * @param aNode the CEpsNode to be mapped.
     * @return always returns <code> null </code>.
     */
    public CNode abstrTermValueNode(CTermValueNode aNode){
        assert false: "Method CAbstraction.abstrTermValueNode should never be called.";
        return null;
    }
    
    /**
     * Returns an abstract syntax tree (ast) node corresponding to CNonTermNode object.
     * 
     * @param aNode the CNonTermNode to be mapped to an abstract syntax tree (ast) node.
     * @return the <code> ast </code> node corresponding to the <code>CNonTermNode</code>.
     */
    public CNode abstrNonTermNode(CNonTermNode aNode){
        return abstr((CECFGNode)aNode.getBody()); // return abstr(aNode)
    }
    
    /**
     * Returns ast node corresponding to COptionNode object.
     * If <code>aNode</code> is an instance of <code>COptionNode</code> and is present then it will be mapped to its 
     * corresponding ast node.
     * 
     * 
     * @param aNode the COptionNode to be mapped to an ast node.
     * @return the <code> ast </code> node corresponding to the <code>COptionNode</code>.
     * 
     */
    public CNode abstrOptionNode(COptionNode aNode){
       CNode r = NodeFactory.makeNodeofLabel(aNode.getRoot());
	assert r.kind()==CNodeKind.OPTION: String.format("CAbstraction.abstrOptionNode(%s) doesn't support INodeOption",aNode.sortLabel()); 
	if (aNode.isPresent()){
	   ((INodeOption) r).setPresent(abstr( (CECFGNode)aNode.getNode(0)) );
	}else{
	   ((INodeOption) r).setAbsent();
	}
	return r;
    }
        
    /**
     * Returns a <code> null </code> node. This method is intended to be used to prevent 
     * mapping of ParseTree nodes of type <code> CMultiDotNode </code> to AST nodes. There are no <code>CMultiDotNode</code> nodes 
     * in the abstract syntax.
     * 
     * @param aNode the CMultiDotNode to be mapped.
     * @return always returns <code> null </code>.
     */
    public CNode abstrMultiDotNode(CMultiDotNode aNode){
       assert false: "Method CAbstraction.abstrMultiDotNode should never be called.";
       return null;
    }
    
   /**
     * Returns ast node corresponding to CMultiStickNode object.
     *  
     * 
     * @param aNode the CMultiStickNode to be mapped to an ast node.
     * @return the <code> ast </code> node corresponding to the <code>CMultiStickNode</code>.
     * 
     */
    public CNode abstrMultiStickNode(CMultiStickNode aNode){
         return abstr( (CECFGNode) aNode.getNode(0));
    }
    
    /**
     * Returns ast node corresponding to CTupleNode object.
     *  
     * 
     * @param aNode the CTupleNode to be mapped to an ast node.
     * @return the <code> ast </code> node corresponding to the <code>CTupleNode</code>.
     * 
     */
    public CNode abstrTupleNode(CTupleNode aNode){
        CNode r = NodeFactory.makeNodeofLabel(aNode.getRoot()); 
       
        for (int i = 0; i < aNode.count(); i++){
          System.out.println(r+"::"+aNode.getNode(i));
          r.setNode(i, abstr( (CECFGNode)aNode.getNode(i)));
        }
      
        // copy data elements starting from 2; 0 and 1 contain root and path
        
        for (int j = 2; j < aNode.dataCount(); j++){
            r.setData(j-2, aNode.getData(j));
           
        }
         
        return r;
    }
    
    /**
     * Returns ast node corresponding to CStarNode object.
     *  
     * 
     * @param aNode the CStarNode to be mapped to an ast node.
     * @return the <code> ast </code> node corresponding to the <code>CStarNode</code>.
     * 
     */
    public CNode abstrStarNode(CStarNode aNode){
        CNode r = NodeFactory.makeNodeofLabel(aNode.getRoot()); 
        assert (r.kind()==CNodeKind.LIST):String.format("CAbstraction.abstrStarNode(%s)"
                + "doesn't support INodeList",aNode.sortLabel());
        
        for (int i = 0; i < aNode.count(); i++){
           ((INodeList) r).add(abstr( (CECFGNode) aNode.getNode(i)));
        }
        return r;
    }
    
    /**
     * Returns ast node corresponding to CPlusNode object.
     *  
     * 
     * @param aNode the CPlusNode to be mapped to an ast node.
     * @return the <code> ast </code> node corresponding to the <code>CPlusNode</code>.
     * 
     */
    public CNode abstrPlusNode(CPlusNode aNode){
        CNode r = NodeFactory.makeNodeofLabel(aNode.getRoot());
        assert (r.kind()==CNodeKind.LIST):String.format("CAbstraction.abstrPlusNode(%s)"
                + "doesn't support INodeList",aNode.sortLabel());
        
        ((INodeList) r).remove(r.getNode(0)); // temporaly trick to remove the hole 
        
        for (int i = 0; i < aNode.count(); i++){
            ((INodeList) r).add(abstr( (CECFGNode) aNode.getNode(i)));
        }
       
        return r;
    }
    
    /**
     * Returns ast node corresponding to CAltNode object.
     *  
     * 
     * @param aNode the CAltNode to be mapped to an ast node.
     * @return the <code> ast </code> node corresponding to the <code>CAltNode</code>.
     * 
     */
    public CNode abstrAltNode(CAltNode aNode){
        CNode r = NodeFactory.makeNodeofLabel(aNode.getRoot()); 
        
        assert (r.kind()==CNodeKind.LIST):String.format("CAbstraction.abstrAltNode(%s)"
                + "doesn't support INodeList",aNode.sortLabel());
        if(r.count()==1)
           ((INodeList) r).remove(r.getNode(0)); // temporaly trick to remove the hole 
        for (int i = 0; i < aNode.count(); i++){
            ((INodeList) r).add(abstr( (CECFGNode) aNode.getNode(i)));
        }
        
        return r;
    }

   
}
