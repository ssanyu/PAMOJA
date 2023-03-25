/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout.LLParserVisualizer;

import TreeLayout.CTreeNode;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CLLTreeNode {
    CTreeNode tree;
    ArrayList<CTreeNode> stackPointers;//nodes on the stack
    ArrayList<CTreeNode> matchedNodes;
    ArrayList<CTreeNode> stackNodes;
    ArrayList<CTreeNode> remainingSymbolNodes;
    int x;
    int y;
    int treeHeight;
    int treeWidth;
    int stackHeight;
    int stackWidth;
    int symHeight;
    int symWidth;

    /**
     *
     * @param tree
     * @param stackNodes
     * @param remainingSymbolNodes
     * @param x
     * @param y
     * @param treeHeight
     * @param treeWidth
     * @param stackHeight
     * @param stackWidth
     * @param symHeight
     * @param symWidth
     */
    public CLLTreeNode(CTreeNode tree, ArrayList<CTreeNode> stackNodes, ArrayList<CTreeNode> remainingSymbolNodes, int x, int y,int treeHeight, int treeWidth, int stackHeight, int stackWidth, int symHeight, int symWidth) {
        this.tree = tree;
        this.stackNodes = new ArrayList();
        this.remainingSymbolNodes = new ArrayList();
        this.x=0;
        this.y=0;
        this.treeHeight = 0;
        this.treeWidth = 0;
        this.stackHeight = 0;
        this.stackWidth = 0;
        this.symHeight = 0;
        this.symWidth = 0;
    }

    /**
     *
     * @param tree
     * @param stackPointers
     * @param matchedNodes
     * @param stackNodes
     * @param remainingSymbolNodes
     */
    public CLLTreeNode(CTreeNode tree, ArrayList<CTreeNode> stackPointers, ArrayList<CTreeNode> matchedNodes, ArrayList<CTreeNode> stackNodes, ArrayList<CTreeNode> remainingSymbolNodes) {
        this.tree = tree;
        this.stackPointers = stackPointers;
        this.matchedNodes = matchedNodes;
        this.stackNodes = stackNodes;
        this.remainingSymbolNodes = remainingSymbolNodes;
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
        this.matchedNodes = matchedNodes;
    }

    /**
     *
     * @return
     */
    public ArrayList<CTreeNode> getStackPointers() {
        return stackPointers;
    }

    /**
     *
     * @param stackPointers
     */
    public void setStackPointers(ArrayList<CTreeNode> stackPointers) {
        this.stackPointers = stackPointers;
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
        this.tree = tree;
    }

    /**
     *
     * @return
     */
    public ArrayList<CTreeNode> getStackNodes() {
        return stackNodes;
    }

    /**
     *
     * @param stackNodes
     */
    public void setStackNodes(ArrayList<CTreeNode> stackNodes) {
        this.stackNodes = stackNodes;
    }

    /**
     *
     * @return
     */
    public ArrayList<CTreeNode> getRemainingSymbolNodes() {
        return remainingSymbolNodes;
    }

    /**
     *
     * @param remainingSymbolNodes
     */
    public void setRemainingSymbolNodes(ArrayList<CTreeNode> remainingSymbolNodes) {
        this.remainingSymbolNodes = remainingSymbolNodes;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return
     */
    public int getTreeHeight() {
        return treeHeight;
    }

    /**
     *
     * @param treeHeight
     */
    public void setTreeHeight(int treeHeight) {
        this.treeHeight = treeHeight;
    }

    /**
     *
     * @return
     */
    public int getTreeWidth() {
        return treeWidth;
    }

    /**
     *
     * @param treeWidth
     */
    public void setTreeWidth(int treeWidth) {
        this.treeWidth = treeWidth;
    }

    /**
     *
     * @return
     */
    public int getStackHeight() {
        return stackHeight;
    }

    /**
     *
     * @param stackHeight
     */
    public void setStackHeight(int stackHeight) {
        this.stackHeight = stackHeight;
    }

    /**
     *
     * @return
     */
    public int getStackWidth() {
        return stackWidth;
    }

    /**
     *
     * @param stackWidth
     */
    public void setStackWidth(int stackWidth) {
        this.stackWidth = stackWidth;
    }

    /**
     *
     * @return
     */
    public int getSymHeight() {
        return symHeight;
    }

    /**
     *
     * @param symHeight
     */
    public void setSymHeight(int symHeight) {
        this.symHeight = symHeight;
    }

    /**
     *
     * @return
     */
    public int getSymWidth() {
        return symWidth;
    }

    /**
     *
     * @param symWidth
     */
    public void setSymWidth(int symWidth) {
        this.symWidth = symWidth;
    }
    
    
}
