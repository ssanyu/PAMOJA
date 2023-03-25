/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;


import Nodes.CNode;
import Parsers.CParseLog;
import Parsers.CParseStack;
import SymbolStream.Symbol;
import TableGenerators.LR.Move;
import TreeLayout.CTreeNode;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Backup {
    private Move action;
    private int currentState;
    private ArrayList<CNode> nodeStack;
    private ArrayList<CTreeNode> treeStack;
    private ArrayList<Symbol> symbolsUnmacthed;
    private ArrayList<Symbol> symbolsMacthed;
    private String stringMatched;
    private String stringUnmatched;
    private ArrayList<CParseLog> logs;
    private CParseStack stack;
    private ArrayList<String> stackOptions;
    private String topSymbol;
    private ArrayList<CTreeNode> matchedNodes;
    private CTreeNode tree;
    private CTreeNode topNode;
   
    /**
     *
     * @param action
     * @param currentState
     * @param nodeStack
     * @param symbolsUnmacthed
     * @param symbolsMacthed
     * @param stringMatched
     * @param stringUnmatched
     * @param logs
     * @param stack
     */
    public Backup(Move action, int currentState, ArrayList<CNode> nodeStack, ArrayList<Symbol> symbolsUnmacthed, ArrayList<Symbol> symbolsMacthed, String stringMatched, String stringUnmatched, ArrayList<CParseLog> logs, CParseStack stack) {
        this.action = action;
        this.currentState = currentState;
        this.nodeStack = nodeStack;
        this.symbolsUnmacthed = symbolsUnmacthed;
        this.symbolsMacthed = symbolsMacthed;
        this.stringMatched = stringMatched;
        this.stringUnmatched = stringUnmatched;
        this.logs = logs;
        this.stack=stack;
       
    }

    /**
     *
     * @param treeStack
     * @param symbolsUnmacthed
     * @param symbolsMacthed
     * @param stringMatched
     * @param stringUnmatched
     * @param logs
     * @param stack
     * @param stackOptions
     */
    public Backup(ArrayList<CTreeNode> treeStack, ArrayList<Symbol> symbolsUnmacthed, ArrayList<Symbol> symbolsMacthed, String stringMatched, String stringUnmatched, ArrayList<CParseLog> logs, CParseStack stack,ArrayList<String> stackOptions) {
        this.treeStack = treeStack;
        this.symbolsUnmacthed = symbolsUnmacthed;
        this.symbolsMacthed = symbolsMacthed;
        this.stringMatched = stringMatched;
        this.stringUnmatched = stringUnmatched;
        this.logs = logs;
        this.stack=stack;
        this.stackOptions=stackOptions;
    }

    /**
     *
     * @param topNode
     * @param tree
     * @param topSymbol
     * @param treeStack
     * @param symbolsUnmacthed
     * @param symbolsMacthed
     * @param stringMatched
     * @param stringUnmatched
     * @param logs
     * @param stack
     * @param matchedNodes
     */
    public Backup(CTreeNode topNode,CTreeNode tree,String topSymbol,ArrayList<CTreeNode> treeStack, ArrayList<Symbol> symbolsUnmacthed, ArrayList<Symbol> symbolsMacthed, String stringMatched, String stringUnmatched, ArrayList<CParseLog> logs, CParseStack stack,ArrayList<CTreeNode> matchedNodes) {
        this.topNode=topNode;
        this.tree=tree;
        this.topSymbol=topSymbol;
        this.treeStack = treeStack;
        this.symbolsUnmacthed = symbolsUnmacthed;
        this.symbolsMacthed = symbolsMacthed;
        this.stringMatched = stringMatched;
        this.stringUnmatched = stringUnmatched;
        this.logs = logs;
        this.stack=stack;
        this.matchedNodes=matchedNodes;
        
    }
     
    /**
     *
     * @param tree
     * @param topSymbol
     * @param treeStack
     * @param symbolsUnmacthed
     * @param symbolsMacthed
     * @param stringMatched
     * @param stringUnmatched
     * @param logs
     * @param stack
     * @param matchedNodes
     */
    public Backup(CTreeNode tree,String topSymbol,ArrayList<CTreeNode> treeStack, ArrayList<Symbol> symbolsUnmacthed, ArrayList<Symbol> symbolsMacthed, String stringMatched, String stringUnmatched, ArrayList<CParseLog> logs, CParseStack stack,ArrayList<CTreeNode> matchedNodes) {
        this.tree=tree;
        this.topSymbol=topSymbol;
        this.treeStack = treeStack;
        this.symbolsUnmacthed = symbolsUnmacthed;
        this.symbolsMacthed = symbolsMacthed;
        this.stringMatched = stringMatched;
        this.stringUnmatched = stringUnmatched;
        this.logs = logs;
        this.stack=stack;
        this.matchedNodes=matchedNodes;
        
    }
    
    /**
     *
     * @return
     */
    public Move getAction() {
        return action;
    }

    /**
     *
     * @param action
     */
    public void setAction(Move action) {
        this.action = action;
    }

    /**
     *
     * @return
     */
    public CTreeNode getTopNode() {
        return topNode;
    }

    /**
     *
     * @param topNode
     */
    public void setTopNode(CTreeNode topNode) {
        this.topNode = topNode;
    }
    
    /**
     *
     * @return
     */
    public CTreeNode getTree() {
        return tree;
    }
   
    /**
     *
     * @param tree
     */
    public void setTree(CTreeNode tree) {
        this.tree=null;
        this.tree= tree;
    }

    /**
     *
     * @return
     */
    public ArrayList<CTreeNode> getMatchedNodes() {
        return matchedNodes;
    }

    /**
     *
     * @param matchedNodes
     */
    public void setMatchedNodes(ArrayList<CTreeNode> matchedNodes) {
        this.matchedNodes =new ArrayList();
        this.matchedNodes.addAll(matchedNodes);
    }
    
    /**
     *
     * @return
     */
    public CParseStack getStack() {
        return stack;
    }

    /**
     *
     * @param stack
     */
    public void setStack(CParseStack stack) {
        this.stack = stack;
    }

    /**
     *
     * @return
     */
    public String getTopSymbol() {
        return topSymbol;
    }

    /**
     *
     * @param topSymbol
     */
    public void setTopSymbol(String topSymbol) {
        this.topSymbol = topSymbol;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getStackOptions() {
        return stackOptions;
    }

    /**
     *
     * @param stackOptions
     */
    public void setStackOptions(ArrayList<String> stackOptions) {
        this.stackOptions = stackOptions;
    }

    /**
     *
     * @return
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     *
     * @param currentState
     */
    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    /**
     *
     * @return
     */
    public ArrayList<CNode> getNodeStack() {
        return nodeStack;
    }

    /**
     *
     * @param nodeStack
     */
    public void setNodeStack(ArrayList<CNode> nodeStack) {
        this.nodeStack = nodeStack;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<CTreeNode> getTreeStack() {
        return treeStack;
    }

    /**
     *
     * @param treeStack
     */
    public void setTreeStack(ArrayList<CTreeNode> treeStack) {
        this.treeStack = treeStack;
    }

    /**
     *
     * @return
     */
    public ArrayList<Symbol> getSymbolsUnmacthed() {
        return symbolsUnmacthed;
    }

    /**
     *
     * @param symbolsUnmacthed
     */
    public void setSymbolsUnmacthed(ArrayList<Symbol> symbolsUnmacthed) {
        this.symbolsUnmacthed = symbolsUnmacthed;
    }

    /**
     *
     * @return
     */
    public ArrayList<Symbol> getSymbolsMacthed() {
        return symbolsMacthed;
    }

    /**
     *
     * @param symbolsMacthed
     */
    public void setSymbolsMacthed(ArrayList<Symbol> symbolsMacthed) {
        this.symbolsMacthed = symbolsMacthed;
    }

    /**
     *
     * @return
     */
    public String getStringMatched() {
        return stringMatched;
    }

    /**
     *
     * @param stringMatched
     */
    public void setStringMatched(String stringMatched) {
        this.stringMatched = stringMatched;
    }

    /**
     *
     * @return
     */
    public String getStringUnmatched() {
        return stringUnmatched;
    }

    /**
     *
     * @param stringUnmatched
     */
    public void setStringUnmatched(String stringUnmatched) {
        this.stringUnmatched = stringUnmatched;
    }

    /**
     *
     * @return
     */
    public ArrayList<CParseLog> getLogs() {
        return logs;
    }

    /**
     *
     * @param logs
     */
    public void setLogs(ArrayList<CParseLog> logs) {
        this.logs = logs;
    }
}
