/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Ssanyu
 **/
public class CTreeNode {
    private CNodeStyle nodeStyle;
    private CEdgeStyle edgeStyle;
    private Color color;
    private String label;
    private ArrayList<CTreeNode> sons; // A list of pointers to subtrees

    // layout fields
    private int x;    // relative X-xoordinate w.r.t. surrounding area.
    private int y;      // relative Y-coordinate w.r.t. surrounding area.
    private int height; // height in pixels of the tree with this node as root.
    private int width;  // width in pixels of the tree with this node as root.
    private int nodeheight; // height in pixels of the area used to represent the node
    private int nodewidth; // width in pixels of the area used to represent the node

    /**
     *
     * @param nodeStyle
     * @param edgeStyle
     * @param color
     * @param label
     * @param sons
     */
    public CTreeNode(CNodeStyle nodeStyle, CEdgeStyle edgeStyle, Color color, String label, ArrayList<CTreeNode> sons) {
        this.nodeStyle = nodeStyle;
        this.edgeStyle=edgeStyle;
        this.color = color;
        this.label = label;
        this.sons = sons;
        this.x = 0;
        this.y = 0;
        this.height = 0;
        this.width = 0;
    }

    /**
     *
     * @return
     */
    public CNodeStyle getNodeStyle() {
        return nodeStyle;
    }

    /**
     *
     * @param nodeStyle
     */
    public void setNodeStyle(CNodeStyle nodeStyle) {
        this.nodeStyle = nodeStyle;
    }

    /**
     *
     * @return
     */
    public CEdgeStyle getEdgeStyle() {
        return edgeStyle;
    }

    /**
     *
     * @param edgeStyle
     */
    public void setEdgeStyle(CEdgeStyle edgeStyle) {
        this.edgeStyle = edgeStyle;
    }

    /**
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @return
     */
    public ArrayList<CTreeNode> getSons() {
        return sons;
    }

    /**
     *
     * @param sons
     */
    public void setSons(ArrayList<CTreeNode> sons) {
        this.sons = sons;
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
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public int getNodeHeight() {
        return nodeheight;
    }

    /**
     *
     * @param aheight
     */
    public void setNodeHeight(int aheight) {
        this.nodeheight = aheight;
    }

    /**
     *
     * @return
     */
    public int getNodeWidth() {
        return nodewidth;
    }

    /**
     *
     * @param awidth
     */
    public void setNodeWidth(int awidth) {
        this.nodewidth = awidth;
    }

    /**
     *
     * @return
     */
    public int count(){
        return sons.size();
    }
    
    /**
     *
     * @param i
     * @return
     */
    public CTreeNode getSon(int i){
        return getSons().get(i);
    }
}
