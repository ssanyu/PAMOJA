/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.NPDA.NPDALL;

import Components.Concrete.Parser.IParserComp;
import Nodes.CNode;
import Parsers.CNPDALL;
import SymbolStream.Symbol;
import TreeLayout.CTreeNode;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface INPDALLComp extends IParserComp{

    /**
     *
     */
    void start();

    /**
     *
     */
    void parseStep();

    /**
     *
     */
    void replaceNonterminal();

    /**
     *
     */
    void matchTerminal();
    
    CNPDALL getParser();

    /**
     *
     * @param parser
     */
    void setParser(CNPDALL parser);

    /**
     *
     * @return
     */
    CNode getNode();
    
    /**
     *
     * @return
     */
    ArrayList<Symbol> getUnMatchedSymbols();

    /**
     *
     * @param unMatchedSymbols
     */
    void setUnMatchedSymbols(ArrayList<Symbol> unMatchedSymbols);

    /**
     *
     * @return
     */
    ArrayList<CTreeNode> getTreeStack();

    /**
     *
     * @param treeStack
     */
    void setTreeStack(ArrayList<CTreeNode> treeStack);

    /**
     *
     * @return
     */
    ArrayList<CTreeNode> getMatchedNodes();

    /**
     *
     * @param matchedNodes
     */
    void setMatchedNodes(ArrayList<CTreeNode> matchedNodes);
    
}
