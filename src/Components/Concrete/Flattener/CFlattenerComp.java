/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Flattener;

import Components.CPAMOJAComponent;
import Components.Concrete.ParseTree.IParseTreeComp;
import GrammarNotions.ECFGNodes.CAltNode;
import GrammarNotions.ECFGNodes.CECFGNode;
import GrammarNotions.ECFGNodes.CECFGNodeCodes;
import GrammarNotions.ECFGNodes.CEpsNode;
import GrammarNotions.ECFGNodes.CMultiDotNode;
import GrammarNotions.ECFGNodes.CMultiStickNode;
import GrammarNotions.ECFGNodes.CNonTermNode;
import GrammarNotions.ECFGNodes.COptionNode;
import GrammarNotions.ECFGNodes.CPlusNode;
import GrammarNotions.ECFGNodes.CStarNode;
import GrammarNotions.ECFGNodes.CTermNode;
import GrammarNotions.ECFGNodes.CTermValueNode;
import GrammarNotions.ECFGNodes.CTupleNode;
import Nodes.CNode;
import SymbolStream.CSymKind;
import SymbolStream.CSymbolStream;
import SymbolStream.Symbol;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A Flattener component holds a mapping from parse tree to symbolstream.
 * <p>
 * It observes a ParseTree component and maintains consistency between its parse tree and that of
 * a ParseTree component. When it receives a property change in the parse tree, it re-generates its symbolstream.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CFlattenerComp extends CPAMOJAComponent implements IFlattenerComp,PropertyChangeListener{ 
    
    /**
     * A reference to <code>ParseTree</code> object.
     */

    private IParseTreeComp ParseTree;
    /**
     * A symbol stream object.
     */
    private CSymbolStream SymbolStream;
    
    private Symbol s;
    private CNode ParseTreeNode;

    /**
     * Creates a new Flattener component.
     */
    public CFlattenerComp() {
      super();
      SymbolStream=new CSymbolStream();
      SymbolStream.addNewRow();
    }

    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("Flattener",10, 10);
    }
    
    /**
     *
     * @return
     */
    public CNode getParseTreeNode() {
        return ParseTreeNode;
    }

    /**
     *
     * @param aParseTreeNode
     */
    public void setParseTreeNode(CNode aParseTreeNode) {
        if(aParseTreeNode!=null){
            ParseTreeNode = aParseTreeNode;
            setSymbolStream(flatten((CECFGNode)ParseTreeNode));
        }
    }
    
    
    /**
     * Get the value of ParseTree
     *
     * @return the value of ParseTree
     */
    @Override
    public IParseTreeComp getParseTree() {
        return ParseTree;
    }
    
    /**
     * Connects to <code>ParseTreeComp</code> component via its interface -- Set the value of <code>ParseTree</code>.
     * Register for property change events, retrieve current value of ParseTree object and regenerate the symbolstream.
     *
     * @param aParseTree new value of ParseTree
     */
    @Override
    public void setParseTree(IParseTreeComp aParseTree) {
       CNode vParseTreeStructure;
       
      
       if(ParseTree!=null){
              ParseTree.removePropertyChangeListener(this);
       }
       ParseTree=aParseTree;
       if(ParseTree!=null){
          ParseTree.addPropertyChangeListener(this);
           vParseTreeStructure=ParseTree.getParseTreeStructure();
       }else{
            vParseTreeStructure=null;
       }
       setParseTreeNode(vParseTreeStructure);
       
      
    }

    /**
     * Maps a given parse tree node to a symbolstream.
     * 
     * @param aNode the parse tree to map.
     * @return the symbolstream for the given parse tree.
     */
    public CSymbolStream flatten(CECFGNode aNode){
      if(aNode!=null){
      switch(aNode.sortCode()){
          
          case CECFGNodeCodes.scEpsNode:
              return flattenEpsNode((CEpsNode) aNode);
              
          case CECFGNodeCodes.scTermNode:
              return flattenTermNode( (CTermNode) aNode);
             
          case CECFGNodeCodes.scTermValueNode:
              return flattenTermValueNode( (CTermValueNode) aNode);
              
          case CECFGNodeCodes.scNonTermNode:
              return flattenNonTermNode( (CNonTermNode) aNode);
              
          case CECFGNodeCodes.scMultiDotNode:
              return flattenMultiDotNode( (CMultiDotNode) aNode); 
              
          case CECFGNodeCodes.scMultiStickNode:
              return flattenMultiStickNode( (CMultiStickNode) aNode); 
            
          case CECFGNodeCodes.scStarNode:
              return flattenStarNode((CStarNode) aNode); 
             
          case CECFGNodeCodes.scPlusNode:
              return flattenPlusNode((CPlusNode) aNode);
              
          case CECFGNodeCodes.scTupleNode:
              return flattenTupleNode((CTupleNode) aNode);
              
          case CECFGNodeCodes.scAltNode:
              return flattenAltNode((CAltNode) aNode);  
             
          case CECFGNodeCodes.scOptionNode:
              return flattenOptionNode((COptionNode) aNode);
             
          default: return new CSymbolStream();      
      }  
     
      }
      return new CSymbolStream();      
    }
  
    /**
     * Maps a given Epsilon node to a symbolstream.
     * 
     * @param aNode the given epsilon node.
     * @return the symbolstream corresponding to the given epsilon node.
     */
    public CSymbolStream flattenEpsNode(CEpsNode aNode){
        assert false: "Method CFlattener.flattenEpsNode should never be called.";
        return null;
  }
  
    /**
     * Maps a given Terminal node (without data) to a symbolstream.
     * 
     * @param aNode the given terminal node.
     * @return the symbolstream corresponding to the given terminal node.
     */
    public CSymbolStream flattenTermNode(CTermNode aNode){
       s=new Symbol(aNode.getSymName(),"",CSymKind.FIXED); 
       SymbolStream.addSymbol(s);
       return SymbolStream;
  }

    /**
     * Maps a given terminal node (with data) to a symbolstream.
     * 
     * @param aNode the given terminal node.
     * @return the symbolstream corresponding to the given terminal node.
     */
    public CSymbolStream flattenTermValueNode(CTermValueNode aNode){
       s=new Symbol(aNode.getSymName(),aNode.getSymValue(), CSymKind.VARIABLE); 
       SymbolStream.addSymbol(s);
       return SymbolStream;
  }
  
    /**
     * Maps a given Nonterminal node to a symbolstream.
     * 
     * @param aNode the given nonterminal node.
     * @return the symbolstream corresponding to the given nonterminal node.
     */
    public CSymbolStream flattenNonTermNode(CNonTermNode aNode){
       return flatten((CECFGNode)aNode.getBody());
  }
  
    /**
     * Maps a given Multidot node to a symbolstream.
     * 
     * @param aNode the given multidot node.
     * @return the symbolstream corresponding to the given multidot node.
     */
    public CSymbolStream flattenMultiDotNode(CMultiDotNode aNode){
       for (int i = 0; i < aNode.count(); i++){
             flatten((CECFGNode)aNode.getNode(i));
        }
       return SymbolStream;
  }
  
   /**
     * Maps a given Multistick node to a symbolstream.
     * 
     * @param aNode the given multistick node.
     * @return the symbolstream corresponding to the given multistick node.
     */
    public CSymbolStream flattenMultiStickNode(CMultiStickNode aNode){
       for (int i = 0; i < aNode.count(); i++){
             flatten((CECFGNode)aNode.getNode(i));
        }
       return SymbolStream;
  }

    /**
     * Maps a given star node to a symbolstream.
     * 
     * @param aNode the given star node.
     * @return the symbolstream corresponding to the given star node.
     */
    public CSymbolStream flattenStarNode(CStarNode aNode){
       for (int i = 0; i < aNode.count(); i++){
             flatten((CECFGNode)aNode.getNode(i));
       }
       return SymbolStream;
  }
  /**
     * Maps a given Tuple node to a symbolstream.
     * 
     * @param aNode the given tuple node.
     * @return the symbolstream corresponding to the given tuple node.
     */
    public CSymbolStream flattenTupleNode(CTupleNode aNode){
        for (int i = 0; i < aNode.count(); i++){
             flatten((CECFGNode)aNode.getNode(i));
        }
       return SymbolStream;
  }

  /**
     * Maps a given Option node to a symbolstream.
     * 
     * @param aNode the given option node.
     * @return the symbolstream corresponding to the given option node.
     */
    public CSymbolStream flattenOptionNode(COptionNode aNode){
       return SymbolStream;
  }

    /**
     * Maps a given Plus node to a symbolstream.
     * 
     * @param aNode the given plus node.
     * @return the symbolstream corresponding to the given plus node.
     */
    public CSymbolStream flattenPlusNode(CPlusNode aNode){
       for (int i = 0; i < aNode.count(); i++){
             flatten((CECFGNode)aNode.getNode(i));
       }
       return SymbolStream;
  }
  
   /**
     * Maps a given Alt node to a symbolstream.
     * 
     * @param aNode the given Alt node.
     * @return the symbolstream corresponding to the given Alt node.
     */
    public CSymbolStream flattenAltNode(CAltNode aNode){
      for (int i = 0; i < aNode.count(); i++){
             flatten((CECFGNode)aNode.getNode(i));
       }
       return SymbolStream;
       
  }

  /**
     * Handles property change events. If the property change is from a ParseTree component, retrieves its parse tree object, and updates it self. 
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       Object source=evt.getSource();
       if(source==ParseTree){
            setParseTreeNode(ParseTree.getParseTreeStructure());
            
       }
    }

    /**
     * Returns a symbol stream.
     * 
     * @return
     */
    @Override
    public CSymbolStream getSymbolStream() {
        return SymbolStream;
    }

    @Override
    public void setSymbolStream(CSymbolStream aSymbolStream) {
         
         // keep the old value
        CSymbolStream oldSymbolStream=SymbolStream;
        // save the new value
        SymbolStream=aSymbolStream;
        // fire the property change event, with a property that has changed
        support.firePropertyChange("SymbolStream",oldSymbolStream,SymbolStream);
        
    }

    
  
}
