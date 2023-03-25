/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.NPDA.NPDALR;

import Components.Concrete.Parser.IParserComp;
import Nodes.CNode;
import Parsers.CNPDALR;
import TreeLayout.CTreeNode;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface INPDALRComp extends IParserComp{

    /**
     *
     */
    void parseStep();

    /**
     *
     */
    void start();

    /**
     *
     */
    void step();

    /**
     *
     */
    void shift();

    /**
     *
     */
    void error();

    /**
     *
     */
    void accept();

    /**
     *
     */
    void callEnd();

    /**
     *
     */
    void reduce();
    
    /**
     *
     * @param parser
     */
    void setParser(CNPDALR parser);

    /**
     *
     * @return
     */
    CNode getNode();
    
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
