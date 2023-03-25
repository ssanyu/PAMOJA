/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.LLParser;


import Components.Concrete.ParseTables.LLParseTables.ILLParseTableComp;
import Components.Concrete.Parser.IParseStep;
import Components.Concrete.Parser.IParserComp;
import Nodes.CNode;
import Parsers.CLLParser;
import TableGenerators.LL.LLTable.CLLTable;
import TreeLayout.CTreeNode;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface ILLParserComp extends IParserComp,IParseStep{
    CLLParser getParser();

    /**
     *
     * @param parser
     */
    void setParser(CLLParser parser);
    ILLParseTableComp getParseTable();
    void setParseTable(ILLParseTableComp aParseTable);
    /**
     *
     * @return
     */
    CNode getNode();

    /**
     *
     * @return
     */
    CTreeNode getTree();

    /**
     *
     * @param tree
     */
    void setTree(CTreeNode tree);    

    /**
     *
     * @return
     */
    CLLTable getLLParseTableStructure();
    /**
     * Returns a list of non-terminal symbol-names used in constructing the parse tables.
     * @return the list of non-terminal symbol-names 
     */
    ArrayList<String> getNontermAlphabet();

        /**
     * Returns a list of terminal symbol values and non-terminal symbol-names to be used in constructing the parse tables.
     * @return the list of terminal symbol values and non-terminal symbol-names
     */
    ArrayList<String> getTermAlphabet();
    
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
