/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout.LRParseTreeVisualizer;

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
public class CTreeForestGraph extends CTreeGraph{
   private CStackNode stackRoot;
  
    /**
     *
     */
    public CTreeForestGraph() {
    }
    
    /**
     *
     * @param root
     */
    public CTreeForestGraph(CTreeNode root) {
        super(root);
    }

    /**
     *
     * @param stackRoot
     * @param root
     */
    public CTreeForestGraph(CStackNode stackRoot, CTreeNode root) {
        super(root);
        this.stackRoot = stackRoot;
    }

    /**
     *
     * @return
     */
    public CStackNode getStackRoot() {
        return stackRoot;
    }

    /**
     *
     * @param stackRoot
     */
    public void setStackRoot(CStackNode stackRoot) {
        this.stackRoot = stackRoot;
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
     * @param aStates
     * @return
     */
    public ArrayList<CTreeNode> StatesListtoStackNodesList(ArrayList<Integer> aStates){
        ArrayList<CTreeNode> vResult=new ArrayList();
        Object vState;
        for(int i=0;i<aStates.size();i++){
            vState=aStates.get(i);
            CTreeNode vStackNode=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,Color.black,vState+"",new ArrayList<CTreeNode>());
            vResult.add(vStackNode);
        }
        return vResult;
    }

    /**
     *
     * @param aSymbols
     * @return
     */
    public ArrayList<CTreeNode> StatesListtoStackNodesList1(ArrayList<Object> aSymbols){
        ArrayList<CTreeNode> vResult=new ArrayList();
        String vState;
        for(int i=0;i<aSymbols.size();i++){
            vState=(String)aSymbols.get(i);
            CTreeNode vStackNode=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,Color.black,vState+"",new ArrayList<CTreeNode>());
            vResult.add(vStackNode);
        }
        return vResult;
    }
    
    
    //public ArrayList<CTreeNode> Sy

   @Override
   public void calculateLayout(Graphics g){
     int vWidth=0;
     int vHeight=0;
     int vNodewidth=0;
     int vNodeheight=0;
     int vStringwidth;
     int vStringheight;
     int vEdgeheight;
     int vSubtreeAreawidth=0;
     int vSubtreeAreaheight=0;
     int vSymbolstreamwidth=0;
     int vSymbolstreamheight=0;
     FontMetrics vMetrics;
    if(stackRoot!=null){
         //calculate size of stack, depending on the size of its parts 
         ArrayList<CTreeNode> vStackNodes=stackRoot.getStackNodes();
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
             vWidth=vWidth+ vNodewidth;
             vHeight=vNodeheight;
         }
         for(int i=0;i<vStackNodes.size();i++){
             vStackNode=vStackNodes.get(i);
             vStackNode.setNodeWidth(vNodewidth);
             vStackNode.setNodeHeight(vNodeheight);
          }
        //get the tree stack list
         ArrayList<CTreeNode> treeStack=stackRoot.getSons();
        // calculate edge
         if(treeStack.isEmpty())
             vEdgeheight=0;
         else vEdgeheight=vSpace+vNodeheight/2;
        //calculate size of stack trees
        if(treeStack.isEmpty()){
            vSubtreeAreawidth=0;
            vSubtreeAreaheight=0;
         }else{
              CTreeNode vNode;
              for(int i=0;i<treeStack.size();i++){
                  vNode=treeStack.get(i);
                  auxCalc(vNode,g);
                  vSubtreeAreawidth=vSubtreeAreawidth+vNode.getWidth()+hSpace;
                  vSubtreeAreaheight=Math.max(vSubtreeAreaheight,vNode.getHeight());
              }
             if(treeStack.size()>0){
                 vSubtreeAreawidth=vSubtreeAreawidth-hSpace;
             }
         }
        //Calculate size of Symbol nodes
        ArrayList<CTreeNode> symbols=stackRoot.getReSymbolNodes();
         CTreeNode vSymbolNode;
         for(int i=0;i<symbols.size();i++){
             vSymbolNode=symbols.get(i);
             auxCalc(vSymbolNode,g);
             vSymbolstreamwidth=vSymbolstreamwidth+vSymbolNode.getWidth()+hSpace;
             vSymbolstreamheight=Math.max(vSymbolstreamheight,vSymbolNode.getHeight());
             
        }
        if(symbols.size()>0){
            vSymbolstreamwidth=vSymbolstreamwidth-hSpace;
        }
        stackRoot.setSymWidth(vSymbolstreamwidth);
        stackRoot.setSymHeight(vSymbolstreamheight-vSpace);
        //calculate position information of all stack trees - bottom aligned.
        int vX=0;
        int vY=vHeight*2+vSpace;
        CTreeNode vNode;
       for(int i=0; i<treeStack.size();i++){
          vNode=treeStack.get(i);
          switch (subTreeAlignment) {
            case TOPALIGNED:
                vNode.setX(vX);
                vNode.setY(vY);
                break;
            case BOTTOMALIGNED:
                vNode.setX(vX);
                vNode.setY(vY+vSubtreeAreaheight-vNode.getHeight());
                
            break;
        }
        vX=vX+vNode.getWidth()+hSpace;
        }
        //calaculate position information of pointer and symbol nodes
         vX=0;
         vY=vHeight*2+vSpace;
         for(int i=0; i<symbols.size();i++){
          vNode=symbols.get(i);
          switch (subTreeAlignment) {
            case TOPALIGNED:
                vNode.setX(vX);
                vNode.setY(vY);
            break;
            
            case BOTTOMALIGNED:
                vNode.setX(vX);
                vNode.setY(vY+vSubtreeAreaheight-vNode.getHeight());
                break;
        }
        vX=vX+vNode.getWidth()+hSpace;
        }
        //store size information in stackRoot
         stackRoot.setNodeWidth(vWidth);
         stackRoot.setNodeHeight(vHeight*2);
         stackRoot.setWidth(Math.max(vNodewidth,vSubtreeAreawidth));
         stackRoot.setHeight(stackRoot.getNodeHeight()+vEdgeheight+vSubtreeAreaheight);
         stackRoot.setX(hSpace);
         stackRoot.setY(0);
     }
   }
       
   @Override
   public void draw(Graphics g){
      drawTreeForest(stackRoot,hSpace,0,g);
   }
   
    /**
     *
     * @param aStackRoot
     * @param ax
     * @param ay
     * @param g
     */
    public void drawTreeForest(CStackNode aStackRoot,int ax,int ay,Graphics g){
     stackRoot=aStackRoot;   
    if(stackRoot!=null){
    //draw bounding boxes(ax,ay,aStackRoot)
       
    //draw stack
    drawStack(stackRoot,ax,ay,g);
   
    if(stackRoot.getStackNodes().size()>0){
      //draw edges,     
       drawPointers(stackRoot,ax,ay,g);
       //draw subtrees
       drawSubtrees(stackRoot,ax,ay,g);   
    }  
    drawSymbolStream(stackRoot,ax,ay,g);  
   }
   
        
   } 
   
    /**
     *
     * @param aStackRoot
     * @param ax
     * @param ay
     * @param g
     */
    public void drawStack(CStackNode aStackRoot,int ax,int ay,Graphics g){
       stackRoot=aStackRoot; 
       ArrayList<CTreeNode> vStackNodes=aStackRoot.getStackNodes();
       CTreeNode vStackNode;
       Point vStackNodeArea=new Point();
       vStackNodeArea.x=ax+aStackRoot.getWidth()/2-aStackRoot.getNodeWidth()/2;
       vStackNodeArea.y=ay;
       g.setColor(Color.black);
       for(int i=0;i<vStackNodes.size();i++){
          vStackNode=vStackNodes.get(i);
          //draw state part of stack
          g.drawRect(vStackNodeArea.x,  vStackNodeArea.y, vStackNode.getNodeWidth(), vStackNode.getNodeHeight() );
          g.drawString(vStackNode.getLabel(),vStackNodeArea.x+margin, vStackNodeArea.y+margin*4);
          //draw tree pointer part of stack
          g.drawRect(vStackNodeArea.x,  vStackNode.getNodeHeight(), vStackNode.getNodeWidth(), vStackNode.getNodeHeight() );
          vStackNodeArea.x=vStackNodeArea.x+vStackNode.getNodeWidth();
       }  
    }
   
    /**
     *
     * @param aStackRoot
     * @param ax
     * @param ay
     * @param g
     */
    public void drawPointers(CStackNode aStackRoot,int ax,int ay,Graphics g){
        stackRoot=aStackRoot;
        Point vEdgeSource=new Point();
        Point vEdgeEnd=new Point();
        CTreeNode vStackNode;
        CTreeNode vSubtree;
        g.setColor(Color.BLUE);
        for(int i=0; i<stackRoot.getStackNodes().size(); i++){
            vStackNode=stackRoot.getStackNodes().get(i);
            vSubtree=stackRoot.getSon(i);
            vEdgeSource.x=ax+(stackRoot.getWidth()/2-stackRoot.getNodeWidth()/2)+(i* vStackNode.getNodeWidth())+vStackNode.getNodeWidth()/2;
            vEdgeSource.y=ay+(stackRoot.getNodeHeight())*3/4;
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
        }
    }
    
    /**
     *
     * @param aStackRoot
     * @param ax
     * @param ay
     * @param g
     */
    public void drawSubtrees(CStackNode aStackRoot,int ax,int ay,Graphics g){
        stackRoot=aStackRoot;
         CTreeNode vSubtree;
         g.setColor(Color.BLACK);
         for(int i=0; i<stackRoot.getSons().size(); i++){
            vSubtree=stackRoot.getSons().get(i);
            drawTree(vSubtree,ax+vSubtree.getX(),ay+vSubtree.getY(),g);  
         }
}
 
double ax1,ay1, ax2, ay2;	

    /**
     *
     * @param root
     * @param ax
     * @param ay
     * @param g
     */
    public void drawSymbolStream(CStackNode root,int ax,int ay,Graphics g){
    stackRoot=root;
    CTreeNode vSubtree;
    int x=0,y=0; // start position for the symbol pointer
    if(!stackRoot.getReSymbolNodes().isEmpty()){
    vSubtree=stackRoot.getReSymbolNodes().get(0); 
    g.setColor(Color.red);
    if(stackRoot.getSons().isEmpty()){
        ax=hSpace;
        ay=stackRoot.getNodeHeight()+vSpace+stackRoot.getNodeHeight()/2;
        x=hSpace/2;
        y=ay+vSubtree.getY();
        g.drawLine(x,y,x,stackRoot.getSymHeight());
    }else{
        ax=stackRoot.getWidth()+hSpace*2;
        x=hSpace+stackRoot.getWidth()+hSpace/2;
        y=ay+vSubtree.getY();
        g.drawLine(x,y,x,y+stackRoot.getSymHeight());
    } 
    for(int i=0; i<stackRoot.getReSymbolNodes().size(); i++){
        vSubtree=stackRoot.getReSymbolNodes().get(i);
        drawTree(vSubtree,ax+vSubtree.getX(),ay+vSubtree.getY(),g); 
    } 
    }
}
// calculate the size of arrow head 

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
