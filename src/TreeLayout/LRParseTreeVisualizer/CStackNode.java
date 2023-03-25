/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout.LRParseTreeVisualizer;

import TreeLayout.CEdgeStyle;
import TreeLayout.CNodeStyle;
import TreeLayout.CTreeNode;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CStackNode extends CTreeNode {
    private ArrayList<CTreeNode> stackNodes;
    private ArrayList<CTreeNode> reSymbolNodes;
    private int symWidth=0;
    private int symHeight=0;

    /**
     *
     * @param stackNodes
     * @param reSymbolNodes
     * @param nodeStyle
     * @param edgeStyle
     * @param color
     * @param label
     * @param sons
     */
    public CStackNode(ArrayList<CTreeNode> stackNodes, ArrayList<CTreeNode> reSymbolNodes,CNodeStyle nodeStyle, CEdgeStyle edgeStyle, Color color, String label, ArrayList<CTreeNode> sons) {
        super(nodeStyle, edgeStyle, color, label, sons);
        this.stackNodes = stackNodes;
        this.reSymbolNodes=reSymbolNodes;
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
    public ArrayList<CTreeNode> getReSymbolNodes() {
        return reSymbolNodes;
    }

    /**
     *
     * @param reSymbolNodes
     */
    public void setReSymbolNodes(ArrayList<CTreeNode> reSymbolNodes) {
        this.reSymbolNodes = reSymbolNodes;
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

    

    
    
}
