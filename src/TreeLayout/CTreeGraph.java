/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Ssanyu
 */
public class CTreeGraph {

    /**
     *
     */
    protected CTreeNode root;

    /**
     *
     */
    protected int hSpace;

    /**
     *
     */
    protected int vSpace;

    /**
     *
     */
    protected int margin;

    /**
     *
     */
    protected boolean drawBoundingBoxes;

    /**
     *
     */
    protected CHorizontalAlignment subTreeAlignment; 
    
    /**
     *
     */
    public CTreeGraph(){
        
    }
  
    /**
     *
     * @param root
     */
        public CTreeGraph(CTreeNode root){
        this.root = root;
    }

    /**
     *
     * @return
     */
    public CTreeNode getRoot(){
        return root;
    }

    /**
     *
     * @param root
     */
    public void setRoot(CTreeNode root){
        this.root = root;
    }

    /**
     *
     * @return
     */
    public int getHSpace(){
        return hSpace;
    }

    /**
     *
     * @param hSpace
     */
    public void setHSpace(int hSpace){
        this.hSpace = hSpace;
    }

    /**
     *
     * @return
     */
    public int getVSpace(){
        return vSpace;
    }

    /**
     *
     * @param vSpace
     */
    public void setVSpace(int vSpace){
        this.vSpace = vSpace;
    }

    /**
     *
     * @return
     */
    public int getMargin() {
        return margin;
    }

    /**
     *
     * @param margin
     */
    public void setMargin(int margin) {
        this.margin = margin;
    }

    /**
     *
     * @return
     */
    public boolean isDrawBoundingBoxes() {
        return drawBoundingBoxes;
    }

    /**
     *
     * @param drawBoundingBoxes
     */
    public void setDrawBoundingBoxes(boolean drawBoundingBoxes) {
        this.drawBoundingBoxes = drawBoundingBoxes;
    }

    /**
     *
     * @return
     */
    public CHorizontalAlignment getSubTreeAlignment() {
        return subTreeAlignment;
    }

    /**
     *
     * @param subTreeAlignment
     */
    public void setSubTreeAlignment(CHorizontalAlignment subTreeAlignment) {
        this.subTreeAlignment = subTreeAlignment;
    }
    
    /**
     *
     * @param g
     */
    public void calculateLayout(Graphics g){
        if(root != null){
            auxCalc(root,g);
            root.setX(hSpace);
            root.setY(0);
        }
    }
    
    /**
     *
     * @param aNode
     * @param g
     */
    protected void auxCalc(CTreeNode aNode, Graphics g){
        CTreeNode vNode;

        int vStringHeight = 0;
        int vStringWidth = 0;
        int vNodeHeight = 0;
        int vNodeWidth = 0;
        int vEdgeHeight;
        int vSubTreeWidth;
        int vSubTreeHeight;
             
        FontMetrics vMetrics;
       // Calculate the size of the node, depending on node style, string and margin
       // CNodeStyle vNodeStyle= aNode.getNodeStyle();
       switch (aNode.getNodeStyle()) {
            case NOLABEL:
               vNodeWidth=0; 
               vNodeHeight=0;
                break;
            case NOFRAME: 
            case OVAL:
            case RECTANGLE:
                vMetrics=g.getFontMetrics();
                
                vStringWidth=vMetrics.stringWidth(aNode.getLabel());
                vStringHeight=vMetrics.getHeight();
                
                vNodeWidth= vStringWidth + 2*margin;
                vNodeHeight= vStringHeight + 2*margin;
                break;
           default: 
               vNodeWidth=0; 
               vNodeHeight=0;
           break;
        }
      // calculate height of the edge area
       if (aNode.count() == 0){
           vEdgeHeight = 0;
       } else{
           vEdgeHeight = vSpace;
       }
      //Apply recursively, calculate size of all subtree area 
        vSubTreeWidth=0;
        vSubTreeHeight=0;
        for(int i=0; i<aNode.count();i++){
            vNode=aNode.getSon(i);
            auxCalc(vNode,g);
            vSubTreeWidth=vSubTreeWidth+vNode.getWidth()+hSpace;
            vSubTreeHeight=Math.max(vSubTreeHeight,vNode.getHeight());
        }
        if(aNode.count()>0){
            vSubTreeWidth= vSubTreeWidth-hSpace;
        }
       //Calculate position information (top/bottom aligned) for all subtrees and store in corresponding node
        int vX=0;
        int vY;
        if(aNode.getEdgeStyle()==CEdgeStyle.ABSENT)
             vY=vNodeHeight;
        else vY=vNodeHeight+vSpace;
        for(int i=0; i<aNode.count();i++){
          vNode=aNode.getSon(i);
          switch (subTreeAlignment) {
            case TOPALIGNED:
                vNode.setX(vX);
                vNode.setY(vY);
            break;
            case BOTTOMALIGNED:
                vNode.setX(vX);
                vNode.setY(vY+vSubTreeHeight-vNode.getHeight());
            break;
        }
        vX=vX+vNode.getWidth()+hSpace;
        }
        //Store size information in tree
//        aNode.setStringSize(new Dimension(vStringWidth, vStringHeight) );
        aNode.setWidth(Math.max(vNodeWidth, vSubTreeWidth));
        aNode.setHeight(vNodeHeight+vEdgeHeight+vSubTreeHeight);
        aNode.setNodeWidth(vNodeWidth);
        aNode.setNodeHeight(vNodeHeight);
    }
          
    /**
     *
     * @param g
     */
    public void draw(Graphics g){
        drawTree(root,hSpace,0,g);
    }
       
       // Draw the tree

    /**
     *
     * @param aNode
     * @param ax
     * @param ay
     * @param g
     */
    protected void drawTree(CTreeNode aNode, int ax, int ay, Graphics g){
        if(isDrawBoundingBoxes()==true){
           drawBoundBox(aNode,ax,ay,g);
        }
           drawNode(aNode,ax,ay,g);
           drawEdges(aNode,ax,ay,g);
           drawSubTrees(aNode,ax,ay,g);
    }

    /**
     *
     * @param aNode
     * @param ax
     * @param ay
     * @param g
     */
    protected void drawBoundBox(CTreeNode aNode, int ax, int ay, Graphics g){
        Rectangle vBoundBox=new Rectangle(ax,ay, aNode.getWidth(), aNode.getHeight());
        g.setColor(Color.red);
        g.drawRect(vBoundBox.x,vBoundBox.y,vBoundBox.width,vBoundBox.height);
    }

    /**
     *
     * @param aNode
     * @param ax
     * @param ay
     * @param g
     */
    protected void drawNode(CTreeNode aNode, int ax, int ay, Graphics g){
           Point vNodeArea=new Point();
           vNodeArea.x= ax + aNode.getWidth()/2 - aNode.getNodeWidth()/2;
          
           vNodeArea.y=ay;
           aNode.setNodex(vNodeArea.x);
           aNode.setNodey(vNodeArea.y);
           g.setColor(aNode.getColor());
           switch (aNode.getNodeStyle()) {
            case NOLABEL:  
                // do nothing
                break;
            case NOFRAME:
                g.drawString(aNode.getLabel(), vNodeArea.x+margin, vNodeArea.y+margin*4);
                break;
            case OVAL:
              //g.setColor(Color.LIGHT_GRAY);
              g.fillOval(vNodeArea.x, vNodeArea.y, aNode.getNodeWidth(), aNode.getNodeHeight() );
              g.setColor(Color.black);
              g.drawString(aNode.getLabel(),vNodeArea.x+margin, vNodeArea.y+margin*4);
               break; 
            case RECTANGLE:
              g.fillRect(vNodeArea.x, vNodeArea.y, aNode.getNodeWidth(), aNode.getNodeHeight() );
              g.setColor(Color.black);
              g.drawString(aNode.getLabel(),vNodeArea.x+margin, vNodeArea.y+margin*4);
              //System.out.println(aNode.getLabel()+"="+vNodeArea.x+","+vNodeArea.y);
                 if(aNode.getAnno()!=null)
                   g.drawString(aNode.getAnno(), vNodeArea.x+aNode.getNodeWidth()*3/4, vNodeArea.y);
              break;
            default: 
                // do nothing
              break;
        }
       }
       
    /**
     * @param aNode
     * @param ax
     * @param ay
     * @param g
     */
    protected void drawEdges(CTreeNode aNode,int ax,int ay,Graphics g){
         Point vEdgeSource=new Point(); // starting point of edges
         Point vEdgeEnd=new Point();  // end point of edges
         CTreeNode vNode;
         CTreeNode vFirstSon, vLastSon;
         int vBarY; // Y-coordinate of horizontal bar in comb
                  
        if(aNode.getEdgeStyle()==CEdgeStyle.ABSENT){
            
        }else{         
         vEdgeSource.x=ax+aNode.getWidth()/2;
         switch (aNode.getNodeStyle()) {
            case NOLABEL:  
                vEdgeSource.y=ay;
                break;
            case NOFRAME:
            case OVAL:
            case RECTANGLE:
                 vEdgeSource.y=ay+aNode.getNodeHeight();
                break;
         }
         g.setColor(Color.black);
         if(aNode.count()== 1){
                // special case where general algorithm gives less nice result
                    vEdgeEnd.x= vEdgeSource.x;
                    vEdgeEnd.y= ay + aNode.getSon(0).getY();
                    
                    g.drawLine(vEdgeSource.x, vEdgeSource.y, vEdgeEnd.x, vEdgeEnd.y);
                    return;
         }            
        switch (aNode.getEdgeStyle()){
            case FAN:  
               for(int i=0; i<aNode.count();i++){
                    vNode=aNode.getSon(i);
                    
                    vEdgeEnd.x= ax + vNode.getX() + vNode.getWidth()/2;
                    vEdgeEnd.y= ay + aNode.getNodeHeight() + vSpace;
                    g.drawLine(vEdgeSource.x, vEdgeSource.y, vEdgeEnd.x, vEdgeEnd.y);
                    if(subTreeAlignment == CHorizontalAlignment.BOTTOMALIGNED){
                        g.drawLine(vEdgeEnd.x, vEdgeEnd.y, vEdgeEnd.x, ay + vNode.getY());
                    }
                }
            break;
            case COMB:
                 if(aNode.count()!=0){
                    // draw vertical line from EdgeSource to horizontal bar
                    vBarY = vEdgeSource.y + vSpace/2;
                    g.drawLine(vEdgeSource.x, vEdgeSource.y, vEdgeSource.x, vBarY); 
                    // draw horizontal bar
                    vFirstSon=aNode.getSon(0);
                    vLastSon=aNode.getSon(aNode.count()-1);
                    g.drawLine(ax+vFirstSon.getX()+vFirstSon.getWidth()/2, vBarY, ax+vLastSon.getX()+vLastSon.getWidth()/2, vBarY);
                    
                    // draw vertical lines from horizontal bar to sons
                    for(int i=0; i<aNode.count(); i++){
                        vNode=aNode.getSon(i);
                        g.drawLine(ax+vNode.getX()+vNode.getWidth()/2,vBarY, ax+vNode.getX()+vNode.getWidth()/2, ay+vNode.getY());
                    }
                 }
                 break;
         }              
      }  
    }
    /**
     *
     * @param aNode
     * @param ax
     * @param ay
     * @param g
     */
    protected void drawSubTrees(CTreeNode aNode,int ax,int ay,Graphics g){
         CTreeNode vNode;
        if(aNode.count()==1){// special case where general algorithm gives less nice results
           vNode= aNode.getSon(0);
           drawTree(vNode, ax + aNode.getWidth()/2 - vNode.getWidth()/2, ay + vNode.getY(),g);
           return;
        }
        for(int i=0; i<aNode.count();i++){
             vNode=aNode.getSon(i);
             if(vNode!=null){
               drawTree(vNode,ax+vNode.getX(),ay+vNode.getY(),g);  
             }
        }
    }
}
