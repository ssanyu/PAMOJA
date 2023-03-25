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
public class CTreeNode{
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
    
    // annotation info.
    private String anno; // annotation info.
    
    private int labelWidth=0;
    private int labelHeight=0;
    private int Nodex=0;
    private int Nodey=0;

    /**
     *
     */
    public CTreeNode(){
        this.nodeStyle = null;
        this.edgeStyle=null;
        this.color = Color.BLACK;
        this.label ="";
        this.anno="";
        this.sons =new ArrayList();
        this.x = 0;
        this.y = 0;
        this.height = 0;
        this.width = 0;
    }
    /**
     *
     * @param nodeStyle
     * @param edgeStyle
     * @param color
     * @param label
     * @param anno
     * @param sons
     */
    public CTreeNode(CNodeStyle nodeStyle, CEdgeStyle edgeStyle, Color color, String label, String anno,ArrayList<CTreeNode> sons) {
        this.nodeStyle = nodeStyle;
        this.edgeStyle=edgeStyle;
        this.color = color;
        this.label = label;
        this.anno=anno;
        this.sons = sons;
        this.x = 0;
        this.y = 0;
        this.height = 0;
        this.width = 0;
    }

    /**
     *
     * @param nodeStyle
     * @param edgeStyle
     * @param color
     * @param label
     * @param sons
     */
    public CTreeNode(CNodeStyle nodeStyle, CEdgeStyle edgeStyle, Color color, String label,ArrayList<CTreeNode> sons) {
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
     * @param aTree
     */
    public CTreeNode(CTreeNode aTree) {
        this.nodeStyle = aTree.nodeStyle;
        this.edgeStyle=aTree.edgeStyle;
        this.color = aTree.color;
        this.label = aTree.label;
        this.sons = new ArrayList<>(aTree.sons);
        this.x =aTree.x;
        this.y =aTree.y;
        this.height =aTree.height;
        this.width =aTree.width;
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
    public int getLabelWidth() {
        return labelWidth;
    }

    /**
     *
     * @param labelWidth
     */
    public void setLabelWidth(int labelWidth) {
        this.labelWidth = labelWidth;
    }

    /**
     *
     * @return
     */
    public int getLabelHeight() {
        return labelHeight;
    }

    /**
     *
     * @param labelHeight
     */
    public void setLabelHeight(int labelHeight) {
        this.labelHeight = labelHeight;
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
    public String getAnno() {
        return anno;
    }

    /**
     *
     * @param anno
     */
    public void setAnno(String anno) {
        this.anno = anno;
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
    public int getNodex() {
        return Nodex;
    }

    /**
     *
     * @param Nodex
     */
    public void setNodex(int Nodex) {
        this.Nodex = Nodex;
   }

    /**
     *
     * @return
     */
    public int getNodey() {
        return Nodey;
    }

    /**
     *
     * @param Nodey
     */
    public void setNodey(int Nodey) {
        this.Nodey = Nodey;
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
    
    /**
     *
     * @param s
     */
    public void addSon(CTreeNode s){
        sons.add(s);
    }

    /**
     *
     * @param i
     * @param s
     */
    public void addSon(int i,CTreeNode s){
        sons.add(i,s);
    }

    /**
     *
     * @return
     */
    public CTreeNode copyTree(){
        CTreeNode tree=null;
        ArrayList<CTreeNode> l=new ArrayList();
        //Add all sons to list l
        for(int j=0; j<count(); j++){
            l.add(getSon(j));
        }
        //First copy the node itself
        tree=new CTreeNode(this);
        //remove all the sons of tree
        tree.setSons(new ArrayList());
        //Copy the sons
        for(int i=0;i<l.size();i++){
            tree.addSon(i,l.get(i).copyTree());
        }
        return tree;
    }
    
    /**
     *
     * @param t
     * @return
     */
    public ArrayList<CTreeNode> Leaves(CTreeNode t){
        ArrayList<CTreeNode> leaves=new ArrayList();
        CTreeNode n;
        if(t.count()==0)
            leaves.add(t);
        else{
            for(int i=0;i<t.count();i++){
               if(t.count()==1){
                  n=t.getSon(0);
                  if(n.nodeStyle.equals(nodeStyle.NOFRAME)|| n.label.equals("~")){
                      
                  }else{
                     leaves.addAll(Leaves(t.getSon(i)));  
                  }
               }else{
              leaves.addAll(Leaves(t.getSon(i)));
               }
               
            }
        }
        
       return leaves;
    }
}
