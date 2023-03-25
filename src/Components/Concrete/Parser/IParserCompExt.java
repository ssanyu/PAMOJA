/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser;

import Components.Concrete.TreeBuilder.ITreeBuilderComp;
import GrammarNotions.ECFGNodes.CECFGNode;
import Nodes.CNode;
import java.util.ArrayList;

/**
 * An extension of <code>IParserComp</code> interface with tree building and lookahead services.
 * 
 * @see Components.Concrete.Parser.IParserComp
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IParserCompExt extends IParserComp{

    /**
     * Connects to the specified TreeBuilder component.
     * @param aTreeBuilder the TreeBuilder to connect.
     */
    void setTreeBuilder(ITreeBuilderComp aTreeBuilder);

    /**
     * Sets the tree building mode of this Parser to the specified value.
     * @param aValue if tree building mode is allowed for this parser then <code>true</code>, else <code>false</code>.
     */
    void setTreeBuilding(boolean aValue);
    

    /**
     * Sets the lookahead mode of this Parser to the specified value.
     * @param aValue if lookahead mode is allowed for this parser then <code>true</code>, else <code>false</code>.
     */
        void setUseLookAhead(boolean aValue);
    
     /**
     * Sets the specified parse tree to this parser component.
     * 
     * @param aParseTree the parse tree to set.
     */
    void setParseTree(CECFGNode aParseTree);  

    /**
     *
     * @return
     */
    CECFGNode getParseTree();

    /**
     *
     * @return
     */
    public ArrayList<CNode> getNodeStack();

    /**
     *
     * @param aNodeStack
     */
    public void setNodeStack(ArrayList<CNode> aNodeStack);
}
