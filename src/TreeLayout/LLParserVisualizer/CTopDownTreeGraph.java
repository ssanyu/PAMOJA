/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout.LLParserVisualizer;

import SymbolStream.Symbol;
import TreeLayout.CEdgeStyle;
import TreeLayout.CHorizontalAlignment;
import TreeLayout.CNodeStyle;
import TreeLayout.CTreeGraph;
import TreeLayout.CTreeNode;
import static TreeLayout.LRParseTreeVisualizer.Arrow.angle;
import static TreeLayout.LRParseTreeVisualizer.Arrow.len;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CTopDownTreeGraph extends CTreeGraph{
    CLLTreeNode treeNode;

    /**
     *
     */
    public CTopDownTreeGraph(){
        
    }

    /**
     *
     * @param treeNode
     */
    public CTopDownTreeGraph(CLLTreeNode treeNode) {
        this.treeNode = treeNode;
    }

    /**
     *
     * @return
     */
    public CLLTreeNode getTreeNode() {
        return treeNode;
    }

    /**
     *
     * @param treeNode
     */
    public void setTreeNode(CLLTreeNode treeNode) {
        this.treeNode = treeNode;
    }

    /**
     *
     * @param aSymbolStream
     * @return
     */
    public ArrayList<CTreeNode> SymbolStreamtoTreeNodeList(ArrayList<Symbol> aSymbolStream){
       ArrayList<CTreeNode> symList=new ArrayList();
       String vSymValue,vSymName;
       CTreeNode vName,vValue;
       for(int i=0; i<aSymbolStream.size();i++){
               vSymName=aSymbolStream.get(i).symName();
               vSymValue=aSymbolStream.get(i).symValue();
               vValue=new CTreeNode(CNodeStyle.NOFRAME,CEdgeStyle.ABSENT,Color.black,vSymValue,new ArrayList<CTreeNode>());
               ArrayList<CTreeNode> l=new ArrayList();
               l.add(vValue);
               vName=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255),vSymName,l);
               symList.add(vName);
        }
        return symList;
    }

    /**
     *
     * @param aSymbols
     * @return
     */
    public ArrayList<CTreeNode> StackSymbolListtoStackNodesList(ArrayList<Object> aSymbols){
        ArrayList<CTreeNode> vResult=new ArrayList();
       String vSymbol;
        for(int i=0;i<aSymbols.size();i++){
            vSymbol=(String)aSymbols.get(i);
            CTreeNode vStackNode=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,Color.black,vSymbol,new ArrayList<CTreeNode>());
            vResult.add(vStackNode);
        }
        return vResult;
    }
    
     @Override
   public void calculateLayout(Graphics g){
     int vWidth=0;
     int vHeight=0;
     int vNodewidth=0;
     int vNodeheight=0;
     int vStringwidth;
     int vStringheight;
     int vSymbolstreamwidth=0;
     int vSymbolstreamheight=0;
     FontMetrics vMetrics;
    if(treeNode!=null){
        
         
       //calculate size of stack, depending on the size of its parts 
         ArrayList<CTreeNode> vStackNodes=treeNode.getStackNodes();
         CTreeNode vStackNode;
         String vLabel;
         for(int i=0;i<vStackNodes.size();i++){
             vStackNode=vStackNodes.get(i);
             vMetrics=g.getFontMetrics();
             vLabel=vStackNode.getLabel();
             vStringwidth=vMetrics.stringWidth(vLabel);
             vStringheight=vMetrics.getHeight();
             vNodewidth=Math.max(vNodewidth,vStringwidth+2*margin);
             vNodeheight=Math.max(vNodeheight,vStringheight+2*margin);
             vStackNode.setLabelWidth(vStringwidth);
             vStackNode.setLabelHeight(vStringheight);
             vWidth=vWidth+ vNodewidth;
             vHeight=vNodeheight*2;
         }
         for (CTreeNode vStackNode1 : vStackNodes) {
             vStackNode = vStackNode1;
             vStackNode.setNodeWidth(vNodewidth);
             vStackNode.setNodeHeight(vNodeheight);
         }
         
        //Calculate size of Remaining Symbol nodes
        ArrayList<CTreeNode> symbols=treeNode.getRemainingSymbolNodes();
        CTreeNode vSymbolNode;
        for (CTreeNode symbol : symbols) {
             vSymbolNode = symbol;
             auxCalc(vSymbolNode,g);
             vSymbolstreamwidth=vSymbolstreamwidth+vSymbolNode.getWidth()+hSpace;
             vSymbolstreamheight=Math.max(vSymbolstreamheight,vSymbolNode.getHeight());
        }
        if(symbols.size()>0){
            vSymbolstreamwidth=vSymbolstreamwidth-hSpace;
        }
        treeNode.setSymWidth(vSymbolstreamwidth);
        treeNode.setSymHeight(vSymbolstreamheight);
        
        // Calculate sizeof the tree, depending on size of its parts;
        auxCalc(treeNode.getTree(),g);
        
        //store size information in treeNode
         treeNode.setStackHeight(vHeight);
         treeNode.setStackWidth(vWidth);
         treeNode.setTreeWidth(Math.max(treeNode.getTree().getWidth(),Math.max(treeNode.getStackWidth(),treeNode.getSymWidth())));
         treeNode.setTreeHeight(treeNode.getTree().getHeight()+ vSpace+treeNode.getStackHeight()+vSpace+treeNode.getSymHeight());
         treeNode.getTree().setX(hSpace);
         treeNode.getTree().setY(0);
         treeNode.setX(hSpace);
         treeNode.setY(0);
     }
   }
   public void draw(Graphics g){
      drawTreeNode(treeNode,hSpace,0,g);
      //reset all stackpointers to initial values, required for backward step
      /*for(int i=0;i<treeNode.stackPointers.size();i++){
          treeNode.stackPointers.get(i).setNodex(hSpace);
          treeNode.stackPointers.get(i).setNodey(0);
      }*/
   }
   
    /**
     *
     * @param aTreeNode
     * @param ax
     * @param ay
     * @param g
     */
    public void drawTreeNode(CLLTreeNode aTreeNode,int ax,int ay,Graphics g){
     treeNode=aTreeNode;  
    if(treeNode!=null){
    //draw bounding boxes(ax,ay,aStackRoot)
    //draw tree
     drawTree(treeNode,ax,ay,g);  
    //draw edges,     
     drawPointers(treeNode,ax,ay,g);
    //draw stack
      drawStack(treeNode,ax,ay,g);
      //draw symbolstream
      drawSymbolStream(treeNode,ax,ay,g);  
   }
 }
   
  
   @Override
   protected void drawEdges(CTreeNode aNode,int ax,int ay,Graphics g){
         Point vEdgeSource=new Point(); // starting point of edges
         Point vEdgeEnd=new Point();  // end point of edges/ 
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
                    if(matches(vNode)){
                        vEdgeEnd.x= ax + vNode.getX() + vNode.getWidth()/2;
                        vEdgeEnd.y= ay + aNode.getNodeHeight() + vSpace;
                        g.drawLine(vEdgeSource.x, vEdgeSource.y, vEdgeEnd.x, vEdgeEnd.y); 
                        g.drawLine(vEdgeEnd.x, vEdgeEnd.y, vEdgeEnd.x, ay + vNode.getY());
                    }else{
                        vEdgeEnd.x= ax + vNode.getX() + vNode.getWidth()/2;
                        vEdgeEnd.y= ay + aNode.getNodeHeight() + vSpace;
                        g.drawLine(vEdgeSource.x, vEdgeSource.y, vEdgeEnd.x, vEdgeEnd.y);
                        if(subTreeAlignment == CHorizontalAlignment.BOTTOMALIGNED){
                           g.drawLine(vEdgeEnd.x, vEdgeEnd.y, vEdgeEnd.x, ay + vNode.getY());
                        }
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
     * @param aTreeNode
     * @param ax
     * @param ay
     * @param g
     */
    public void drawStack(CLLTreeNode aTreeNode,int ax,int ay,Graphics g){
       treeNode=aTreeNode; 
       ArrayList<CTreeNode> vStackNodes=aTreeNode.getStackNodes();
       CTreeNode vStackNode;
       Point vStackNodeArea=new Point();
      if(!treeNode.stackPointers.isEmpty()){
          //System.out.println(treeNode.stackPointers.get(0).getNodex());
       vStackNodeArea.x=treeNode.stackPointers.get(0).getNodex();
       vStackNodeArea.y=ay+treeNode.getTree().getHeight()+vSpace;
       g.setColor(Color.black);
       for(int i=0;i<vStackNodes.size();i++){
          vStackNode=vStackNodes.get(i);
          //draw tree pointer part of stack
          g.drawRect(vStackNodeArea.x, vStackNodeArea.y, vStackNode.getNodeWidth(), vStackNode.getNodeHeight() );
          //draw state part of stack
          g.drawRect(vStackNodeArea.x, vStackNodeArea.y+vStackNode.getNodeHeight(),vStackNode.getNodeWidth(), vStackNode.getNodeHeight() );
          g.drawString(vStackNode.getLabel(),vStackNodeArea.x+vStackNode.getNodeWidth()/2-vStackNode.getLabelWidth()/2, vStackNodeArea.y+vStackNode.getNodeHeight()+margin*4);
          vStackNode.setNodex(vStackNodeArea.x);
          vStackNode.setNodey(vStackNodeArea.y);
         // g.drawRect(vStackNodeArea.x,  , vStackNode.getNodeWidth(), vStackNode.getNodeHeight() );
          vStackNodeArea.x=vStackNodeArea.x+vStackNode.getNodeWidth();
       } 
       }else{
          g.setColor(Color.black);
          vStackNode=vStackNodes.get(vStackNodes.size()-1);
          vStackNodeArea.x=treeNode.getTree().getWidth()+hSpace*2;
          vStackNodeArea.y=ay+treeNode.getTree().getHeight()+vSpace;
          vStackNode.setNodex(vStackNodeArea.x);
          vStackNode.setNodey(vStackNodeArea.y);
          g.drawRect(vStackNodeArea.x, vStackNodeArea.y, vStackNode.getNodeWidth(), vStackNode.getNodeHeight());
          //draw state part of stack
          g.drawRect(vStackNodeArea.x, vStackNodeArea.y+vStackNode.getNodeHeight(),vStackNode.getNodeWidth(), vStackNode.getNodeHeight() );
          g.drawString(vStackNode.getLabel(),vStackNodeArea.x+vStackNode.getNodeWidth()/2-vStackNode.getLabelWidth()/2, vStackNodeArea.y+vStackNode.getNodeHeight()+margin*4);
      }
    }
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
       if (aNode.count() == 0 || aNode.getEdgeStyle()==CEdgeStyle.ABSENT){
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
          //sets the height of matched nodes to that of the symbolstream
          if(matches(vNode)){
             vNode.setX(vX);
             vNode.setY(vY+vSubTreeHeight+vSpace*2+treeNode.stackHeight); //this is the usual
             //vNode.setY(vY+vSubTreeHeight+vSpace); 
            // vNode.setY(vY+vSubTreeHeight-vNode.getNodeHeight()); 
          }else{
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
   } 
        vX=vX+vNode.getWidth()+hSpace;
        }
        //Store size information in tree
       // aNode.setStringSize(new Dimension(vStringWidth, vStringHeight) );
        aNode.setWidth(Math.max(vNodeWidth, vSubTreeWidth));
        aNode.setHeight(vNodeHeight+vEdgeHeight+vSubTreeHeight);
        aNode.setNodeWidth(vNodeWidth);
        aNode.setNodeHeight(vNodeHeight);
        
    }
          
    /**
     *
     * @param aTreeNode
     * @param ax
     * @param ay
     * @param g
     */
    public void drawPointers(CLLTreeNode aTreeNode,int ax,int ay,Graphics g){
        treeNode=aTreeNode;
        Point vEdgeSource=new Point();
        Point vEdgeEnd=new Point();
        CTreeNode vStackNode;
        CTreeNode vLeaf;
        g.setColor(Color.BLUE);
        
        //get the leaves of the tree
        // CTreeNode aNode=aTreeNode.getTree();
        //ArrayList <CTreeNode> l=getLeaves(aNode);
        ArrayList<CTreeNode> leaves=treeNode.stackPointers;
        
        for(int j=0;j<leaves.size();j++){
            vLeaf=leaves.get(j);
            
            vStackNode=treeNode.getStackNodes().get(j);
            vEdgeSource.x=vStackNode.getNodex()+vStackNode.getNodeWidth()/2;
            vEdgeSource.y=vStackNode.getNodey()+vStackNode.getNodeHeight()/2;
            vEdgeEnd.x=vLeaf.getNodex()+vLeaf.getNodeWidth()/2;
            vEdgeEnd.y=vLeaf.getNodey()+vLeaf.getNodeHeight();
           //  System.out.println(vLeaf.getLabel()+":"+vLeaf.getNodex()+" "+vLeaf.getNodey());
                    
             // System.out.println(vLeaf.getNodex()+" "+vLeaf.getNodey());
            //System.out.println(vLeaf.getLabel()+": "+vEdgeEnd.x+ " "+vEdgeEnd.y);
            Graphics2D graphics2D = (Graphics2D) g;
           // if(subTreeAlignment == CHorizontalAlignment.TOPALIGNED){
            graphics2D.draw (new Line2D.Double (vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y));

               // paint arrowhead
            arrHead (vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y);
            graphics2D.draw (new Line2D.Double (vEdgeEnd.x,vEdgeEnd.y,ax1,ay1));
            graphics2D.draw (new Line2D.Double (vEdgeEnd.x,vEdgeEnd.y,ax2,ay2));
       
            //g.drawLine(vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y);
          /*  }else if(subTreeAlignment == CHorizontalAlignment.BOTTOMALIGNED){
               if(vEdgeEnd.y==ay + vLeaf.getY()){
                  graphics2D.draw (new Line2D.Double (vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y));
                  // paint arrowhead
                  arrHead (vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y);
                  graphics2D.draw (new Line2D.Double (vEdgeEnd.x,vEdgeEnd.y,ax1,ay1));
                  graphics2D.draw (new Line2D.Double (vEdgeEnd.x,vEdgeEnd.y,ax2,ay2));
               }else{
                  g.drawLine(vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y);
                  graphics2D.draw (new Line2D.Double (vEdgeEnd.x, vEdgeEnd.y, vEdgeEnd.x, ay + vLeaf.getY()));
                  // paint arrowhead
                  arrHead (vEdgeEnd.x, vEdgeEnd.y, vEdgeEnd.x, ay + vLeaf.getY());
                  graphics2D.draw (new Line2D.Double (vEdgeEnd.x,ay + vLeaf.getY(),ax1,ay1));
                  graphics2D.draw (new Line2D.Double (vEdgeEnd.x,ay + vLeaf.getY(),ax2,ay2));
                }
            }*/
        }
         
      /*  
        for(int i=1; i<stackRoot.getStackNodes().size(); i++){
            
            vSubtree=stackRoot.getSon(i-1);
            vEdgeSource.x=ax+(stackRoot.getWidth()/2-stackRoot.getNodeWidth()/2)+(i* vStackNode.getNodeWidth())+vStackNode.getNodeWidth()/2;
            vEdgeSource.y=ay+(stackRoot.getNodeHeight()*2)*3/4;
            vEdgeEnd.x=ax+vSubtree.getX()+vSubtree.getWidth()/2;
            vEdgeEnd.y=ay+vSubtree.getNodeHeight()+vSpace;
            
            Graphics2D graphics2D = (Graphics2D) g;
            if(subTreeAlignment == CHorizontalAlignment.TOPALIGNED){
               graphics2D.draw (new Line2D.Double (vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y));

               // paint arrowhead
               arrHead (vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y);
               graphics2D.draw (new Line2D.Double (vEdgeEnd.x,vEdgeEnd.y,ax1,ay1));
               graphics2D.draw (new Line2D.Double (vEdgeEnd.x,vEdgeEnd.y,ax2,ay2));
       
            //g.drawLine(vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y);
            }else if(subTreeAlignment == CHorizontalAlignment.BOTTOMALIGNED){
               if(vEdgeEnd.y==ay + vSubtree.getY()){
                  graphics2D.draw (new Line2D.Double (vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y));
                  // paint arrowhead
                  arrHead (vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y);
                  graphics2D.draw (new Line2D.Double (vEdgeEnd.x,vEdgeEnd.y,ax1,ay1));
                  graphics2D.draw (new Line2D.Double (vEdgeEnd.x,vEdgeEnd.y,ax2,ay2));
               }else{
                  g.drawLine(vEdgeSource.x,vEdgeSource.y,vEdgeEnd.x,vEdgeEnd.y);
                  graphics2D.draw (new Line2D.Double (vEdgeEnd.x, vEdgeEnd.y, vEdgeEnd.x, ay + vSubtree.getY()));
                  // paint arrowhead
                  arrHead (vEdgeEnd.x, vEdgeEnd.y, vEdgeEnd.x, ay + vSubtree.getY());
                  graphics2D.draw (new Line2D.Double (vEdgeEnd.x,ay + vSubtree.getY(),ax1,ay1));
                  graphics2D.draw (new Line2D.Double (vEdgeEnd.x,ay + vSubtree.getY(),ax2,ay2));
                }
            }
        }*/
    }

    /**
     *
     * @param root
     * @param ax
     * @param ay
     * @param g
     */
    public void drawSymbolStream(CLLTreeNode root,int ax,int ay,Graphics g){
    treeNode=root;
    CTreeNode vSubtree;
    int x=0,y; // start position for the symbol pointer
    g.setColor(Color.red);
    if(!treeNode.getStackNodes().isEmpty() && !treeNode.getRemainingSymbolNodes().isEmpty() ){
        ax=treeNode.getStackNodes().get(0).getNodex();
        ay=treeNode.getStackNodes().get(0).getNodey() +treeNode.getStackHeight()+vSpace;
        x=ax-hSpace/2;
        g.drawLine(x,ay,x,treeNode.getStackNodes().get(0).getNodey()+treeNode.getStackHeight()+vSpace+treeNode.getSymHeight());
        for(int i=0; i<treeNode.getRemainingSymbolNodes().size(); i++){
            vSubtree=treeNode.getRemainingSymbolNodes().get(i);
            drawTree(vSubtree,ax,ay,g);
            ax=ax+vSubtree.getWidth()+hSpace;
        }
    }
}
   
    /**
     *
     * @param aTreeNode
     * @param ax
     * @param ay
     * @param g
     */
    protected void drawTree(CLLTreeNode aTreeNode, int ax, int ay, Graphics g){
        CTreeNode aNode=aTreeNode.getTree();
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
    protected void drawSymbolStreamNode(CTreeNode aNode, int ax, int ay, Graphics g){
       Point vNodeArea=new Point();
           vNodeArea.x= ax + aNode.getWidth()/2 - aNode.getNodeWidth()/2;
           vNodeArea.y=treeNode.getTreeHeight()-treeNode.getSymHeight();
           aNode.setNodex(vNodeArea.x);
           aNode.setNodey(vNodeArea.y);
           g.setColor(aNode.getColor());
           g.fillRect(vNodeArea.x, vNodeArea.y, aNode.getNodeWidth(), aNode.getNodeHeight() );
           g.setColor(Color.black);
           g.drawString(aNode.getLabel(),vNodeArea.x+margin, vNodeArea.y+margin*4);
  }
          
/*  protected void drawSSNodee(CTreeNode aNode, int ax, int ay, Graphics g){
      Point vNodeArea=new Point();
      vNodeArea.x= ax + aNode.getWidth()/2 - aNode.getNodeWidth()/2;
      vNodeArea.y=treeNode.getTreeHeight()-aNode.getHeight();
      aNode.setNodex(vNodeArea.x);
           aNode.setNodey(vNodeArea.y);
           g.setColor(aNode.getColor());
           g.fillRect(vNodeArea.x, vNodeArea.y, aNode.getNodeWidth(), aNode.getNodeHeight() );
           g.setColor(Color.black);
           g.drawString(aNode.getLabel(),vNodeArea.x+margin, vNodeArea.y+margin*4);
           aNode.getSon(0).setY(treeNode.getTreeHeight());
  }*/
  
  protected void drawNode(CTreeNode aNode, int ax, int ay, Graphics g){
           Point vNodeArea=new Point();
           vNodeArea.x= ax + aNode.getWidth()/2 - aNode.getNodeWidth()/2;
           vNodeArea.y=ay;
           aNode.setNodex(vNodeArea.x);
           aNode.setNodey(vNodeArea.y);
           //System.out.println(aNode.getLabel()+":"+aNode.getNodex()+" "+aNode.getNodey());
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
              if(aNode.getAnno()!=null)
                 g.drawString(aNode.getAnno(), vNodeArea.x+aNode.getNodeWidth()*3/4, vNodeArea.y);
               break; 
            case RECTANGLE:
              g.fillRect(vNodeArea.x, vNodeArea.y, aNode.getNodeWidth(), aNode.getNodeHeight() );
              g.setColor(Color.black);
              g.drawString(aNode.getLabel(),vNodeArea.x+margin, vNodeArea.y+margin*4);
              if(aNode.getAnno()!=null)
                 g.drawString(aNode.getAnno(), vNodeArea.x+aNode.getNodeWidth()*3/4, vNodeArea.y);
               break;
            default: 
                // do nothing
              break;
        }//}
           
       } 
   boolean matches(CTreeNode aNode){
       boolean b=false;
       for(int k=0;k<treeNode.getMatchedNodes().size();k++){
           if(aNode.count()==1 && aNode.getLabel().equals(treeNode.getMatchedNodes().get(k).getLabel()) && aNode.getSon(0).getLabel().equals(treeNode.getMatchedNodes().get(k).getSon(0).getLabel()))
                b=true;
           }
       
       return b;
   }
   ArrayList<CTreeNode> aList=new ArrayList();
    ArrayList<CTreeNode> getLeaves(CTreeNode aNode){
        
        
        if(aNode.count()==0)
            aList.add(aNode);
        else{
            for(int i=0;i<aNode.count();i++){
                getLeaves(aNode.getSon(i));
            }
                
        }
        
        return aList;
    }
   // calculate the size of arrow head 
    double ax1,ay1, ax2, ay2;	

    /**
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void arrHead (double x1, double y1, double x2, double y2)
{  double c,a,beta,theta,phi;
   c = Math.sqrt ((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
   if (Math.abs(x2-x1) < 1e-6)
	if (y2<y1) theta = Math.PI/2;
	else theta = - Math.PI/2; 
   else
     { if (x2>x1)
   	   theta = Math.atan ((y1-y2)/(x2-x1)) ;
   	else
	   theta = Math.atan ((y1-y2)/(x1-x2));
     }
   a = Math.sqrt (len*len  + c*c - 2*len*c*Math.cos(angle));
   beta = Math.asin (len*Math.sin(angle)/a);
   phi = theta - beta;
   ay1 = y1 - a * Math.sin(phi);		// coordinates of arrowhead endpoint
   if (x2>x1) 
	ax1 = x1 + a * Math.cos(phi);
   else
	ax1 = x1 - a * Math.cos(phi);
   phi = theta + beta;				// second arrowhead endpoint
   ay2 = y1 - a * Math.sin(phi);
   if (x2>x1)
	ax2 = x1 + a * Math.cos(phi);
   else 
	ax2 = x1 - a * Math.cos(phi);
}

}
