/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FALayout.FALayout;

import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.RegExpr.CRE_List;
import GrammarNotions.RegExpr.CRE_MultiDot;
import GrammarNotions.RegExpr.CRE_MultiStick;
import GrammarNotions.RegExpr.CRE_Option;
import GrammarNotions.RegExpr.CRE_Plus;
import GrammarNotions.RegExpr.CRE_Star;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Ssanyu
 */
public class CFAGraph {
    private CRE re;
    private CFANode faRoot;
    private int initialState;
    private int finalState;
    private CStateShape stateShape;
    Color stateColor;
    private int hSpace;
    private int vSpace;
    private int uSpace;
    private int wSpace;
    private int rSize;
    private boolean drawBoundingBoxes;
    private int stateCount;
    private CFANodeFactory nFactory;

    public CFAGraph() {
        re=null;
        faRoot=null;
        nFactory=new CFANodeFactory();
    }

    public CStateShape getStateShape() {
        return stateShape;
    }

    public void setStateShape(CStateShape stateShape) {
        this.stateShape = stateShape;
    }

    public CRE getRe() {
        return re;
    }

   public void setRe(CRE aRE) {
         stateCount=0;
         initialState = newState();
         finalState= newState();
         re=aRE;
         faRoot = REtoFA(re);
   }

    public CFANode getFaRoot() {
        return faRoot;
    }

    public void setFaRoot(CFANode faRoot) {
        this.faRoot = faRoot;
    }

    public int gethSpace() {
        return hSpace;
    }

    public void sethSpace(int hSpace) {
        this.hSpace = hSpace;
    }

    public int getvSpace() {
        return vSpace;
    }

    public void setvSpace(int vSpace) {
        this.vSpace = vSpace;
    }

    public int getuSpace() {
        return uSpace;
    }

    public void setuSpace(int uSpace) {
        this.uSpace = uSpace;
    }

    public int getwSpace() {
        return wSpace;
    }

    public void setwSpace(int wSpace) {
        this.wSpace = wSpace;
    }

    public int getrSize() {
        return rSize;
    }

    public void setrSize(int rSize) {
        this.rSize = rSize;
    }

    public boolean isDrawBoundingBoxes() {
        return drawBoundingBoxes;
    }

    public void setDrawBoundingBoxes(boolean drawBoundingBoxes) {
        this.drawBoundingBoxes = drawBoundingBoxes;
    }
        
    private void auxCalc(CFANode aNode, Graphics g){
        int vWidth, vHeight, vMaxWidth;
        Dimension vSize, vEltSize;
        int vX, vY;;
        Point vPos;
        
        if(aNode.getKind()==CFANodeKind.EMPTY){
        // calculate size
          vSize = new Dimension( 2*rSize + wSpace, 2*rSize );
          aNode.setWidth(vSize.width);
          aNode.setHeight(vSize.height);
        // calculate relative positions of subgraphs
        // none
        }else if(aNode.getKind()==CFANodeKind.EPS){
                 // calculate size
          vSize = new Dimension( 2*rSize + wSpace, 2*rSize );
          aNode.setWidth(vSize.width);
          aNode.setHeight(vSize.height);
        }else if(aNode.getKind()==CFANodeKind.STRING){
            //calculate width of text
           FontMetrics vMetrics=g.getFontMetrics();
           int vStringWidth=vMetrics.stringWidth(aNode.getName());
           int vStringHeight=vMetrics.getHeight();
           // calculate size
           vSize = new Dimension(2*rSize + Math.max(vStringWidth,wSpace),Math.max(2*rSize, rSize + vStringHeight));
           aNode.setWidth(vSize.width);
           aNode.setHeight(vSize.height);
           // calculate relative positions of subgraphs
        // none
        }else if(aNode.getKind()==CFANodeKind.DOT){
            vWidth=0;
            vHeight=0;
            vEltSize=new Dimension();
            for(int i=0;i<aNode.getElts().size();i++){
                auxCalc(aNode.getElts().get(i),g);
                vEltSize.width=aNode.getElts().get(i).getWidth();
                vEltSize.height=aNode.getElts().get(i).getHeight();
                vWidth=vWidth+vEltSize.width;
                if(vEltSize.height>vHeight){
                    vHeight=vEltSize.height;
                }
            }
            vSize=new Dimension(vWidth,vHeight);
            aNode.setWidth(vSize.width);
            aNode.setHeight(vSize.height);
            // calculate relative positions of subgraphs
            vX=0;
            for(int i=0;i<aNode.getElts().size();i++){
             vEltSize.width=aNode.getElts().get(i).getWidth();
             vEltSize.height=aNode.getElts().get(i).getHeight();
             vPos = new Point(vX, (aNode.getHeight() - vEltSize.height)/2);
             aNode.getElts().get(i).setX(vPos.x);
             aNode.getElts().get(i).setY(vPos.y);
             vX = vX + vEltSize.width;
            }
        } else if(aNode.getKind()==CFANodeKind.STICK){
          // calculate size
          vMaxWidth = 0;
          vHeight = 0;
          vEltSize=new Dimension();
          for(int i=0; i<aNode.getElts().size();i++){
            auxCalc(aNode.getElts().get(i),g);
            vEltSize.width=aNode.getElts().get(i).getWidth();
            vEltSize.height=aNode.getElts().get(i).getHeight();
            if (vEltSize.width > vMaxWidth){
               vMaxWidth = vEltSize.width;
            }
            vHeight = vHeight + vEltSize.height;
          }
          vWidth = vMaxWidth + 4*rSize + 2*hSpace;
          vHeight = vHeight + (aNode.getElts().size()-1)*vSpace;
          vSize = new Dimension(vWidth, vHeight);
          aNode.setWidth(vSize.width);
          aNode.setHeight(vSize.height);
          // calculate relative positions of subgraphs
          vY = 0;
          for(int i=0;i<aNode.getElts().size();i++){
            vEltSize.width=aNode.getElts().get(i).getWidth();
            vEltSize.height=aNode.getElts().get(i).getHeight();
            vPos = new Point(hSpace + 2*rSize + (vMaxWidth - vEltSize.width) / 2,vY);
            aNode.getElts().get(i).setX(vPos.x);
            aNode.getElts().get(i).setY(vPos.y);
            vY = vY + vEltSize.height + vSpace;
          }   
        }else if(aNode.getKind()==CFANodeKind.STAR){
            vEltSize=new Dimension();
            // calculate size
          auxCalc(aNode.getElt(),g);
          vEltSize.width = aNode.getElt().getWidth();
          vEltSize.height=aNode.getElt().getHeight();
          vWidth = vEltSize.width + 4*rSize + 2*hSpace;
          vHeight = vEltSize.height + 2*uSpace;
          vSize =new Dimension(vWidth, vHeight);
          aNode.setWidth(vSize.width);
          aNode.setHeight(vSize.height);
          // calculate relative positions of subgraphs
          vPos = new Point(hSpace + 2*rSize, uSpace);
          aNode.getElt().setX(vPos.x);
          aNode.getElt().setY(vPos.y);
        }else if(aNode.getKind()==CFANodeKind.OPTION){
            
        }else if(aNode.getKind()==CFANodeKind.PLUS){
            
        }    
    }
    
    
    private void drawBoundingBox(CFANode aNode, int aX, int aY, Graphics g){
           g.setColor(Color.red);
           g.drawRect(aX,aY,aNode.getWidth(),aNode.getHeight());
    }
    private void drawEdges(CFANode aNode, int aX, int aY, Graphics g){
        Point vStart,vEnd;
        int vDeltaX, vDeltaY;
        CFANode vSub;
        Point vLeftPoint, vRightPoint;
        
        vDeltaX=0;
        vDeltaY=0;
        
        
         g.setColor(Color.black);
        vStart=new Point(aX+rSize,aY+aNode.getHeight()/2);
        vEnd=new Point(aX+aNode.getWidth()-rSize, aY+aNode.getHeight()/2);
        
        if(aNode.getKind()==CFANodeKind.EMPTY){
          // no edge
        }else if(aNode.getKind()==CFANodeKind.EPS){
          g.drawLine(vStart.x+vDeltaX,vStart.y,vEnd.x-vDeltaX, vEnd.y);
          drawEdgeLabel("$",(vStart.x+vEnd.x)/2,(vStart.y+vEnd.y)/2,g); // Should look for Unicode character of epslon
                
        }else if(aNode.getKind()==CFANodeKind.STRING){
           g.drawLine(vStart.x+vDeltaX,vStart.y,vEnd.x-vDeltaX, vEnd.y);
           drawEdgeLabel(aNode.getName(),(vStart.x+vEnd.x)/2,(vStart.y+vEnd.y)/2,g);
        }else if(aNode.getKind()==CFANodeKind.DOT){
            //No edges to draw
        }else if(aNode.getKind()==CFANodeKind.STICK){
          for(int i=0; i<aNode.getElts().size();i++){
            // Determine connection points of subgraph
            vSub = aNode.getElts().get(i);
            vLeftPoint=new Point(aX + vSub.getX(),
              aY + vSub.getY() + vSub.getHeight() / 2);
              vRightPoint=new Point(aX + vSub.getX() + vSub.getWidth(),
              aY + vSub.getY() + vSub.getHeight() / 2); 
              
              // Draw left broken epsilon edge
            g.drawLine(vStart.x + vDeltaX, vStart.y,vStart.x + hSpace , vLeftPoint.y);
            g.drawLine(vStart.x + hSpace , vLeftPoint.y,vLeftPoint.x - vDeltaX-rSize, vLeftPoint.y);
            drawEdgeLabel("$",
              vStart.x + (hSpace + rSize) / 2,
              (vStart.y + vLeftPoint.y) / 2,
              g);

            // Draw right broken epsilon edge
            g.drawLine(vRightPoint.x + vDeltaX+rSize, vRightPoint.y,vEnd.x - hSpace , vRightPoint.y);
            g.drawLine(vEnd.x - hSpace, vRightPoint.y,vEnd.x - vDeltaX, vEnd.y);
            drawEdgeLabel("$",          
              vEnd.x - (hSpace - rSize) / 2,
              (vRightPoint.y + vEnd.y) / 2,
              g);
          }    
        
        }  else if(aNode.getKind()==CFANodeKind.STAR){
          // Determine connection points of subgraph
            vSub = aNode.getElt();
            vLeftPoint=new Point(
              aX + vSub.getX(),
              aY + vSub.getY() + vSub.getHeight()/ 2);
            vRightPoint=new Point (
              aX + vSub.getX()+ vSub.getWidth(),
              aY + vSub.getY() + vSub.getHeight() /2);

            // Draw epsilon edge from VStart to VLeftPoint
            g.drawLine(vStart.x + vDeltaX, vStart.y,vLeftPoint.x - vDeltaX-rSize, vLeftPoint.y);
            drawEdgeLabel("$",
                    (vStart.x + vLeftPoint.x)/2,
                   (vStart.y + vLeftPoint.y)/2, 
                   g);

            // Draw epsilon edge from VRightPoint to VEnd
            g.drawLine(vRightPoint.x + vDeltaX+rSize, vRightPoint.y,vEnd.x - vDeltaX, vEnd.y);
            drawEdgeLabel(
              "$",
              (vRightPoint.x + vEnd.x) / 2,
              (vRightPoint.y + vEnd.y) / 2,
              g);

            // Draw broken epsilon edge from VStart to VEnd
            g.drawLine(vStart.x + vDeltaX, vStart.y,vLeftPoint.x, aY + aNode.getHeight());
            g.drawLine(vLeftPoint.x, aY + aNode.getHeight(),vRightPoint.x, aY + aNode.getHeight());
            g.drawLine(vRightPoint.x, aY + aNode.getHeight(),vEnd.x - vDeltaX, vEnd.y);
            drawEdgeLabel(
              "$",
              (vLeftPoint.x + vRightPoint.x)/2,
              aY + aNode.getHeight(),
              g);

            // Draw (backward) epsilon edge from VRightPoint to VLeftPoint
            g.drawLine(vRightPoint.x, (vRightPoint.y - vDeltaY)-rSize,vRightPoint.x, aY);
            g.drawLine(vRightPoint.x, aY,vLeftPoint.x, aY);
            g.drawLine(vLeftPoint.x, aY,vLeftPoint.x, (vLeftPoint.y - vDeltaY)-rSize);
            drawEdgeLabel(
              "$",
              (vLeftPoint.x + vRightPoint.x) / 2,
              aY,
              g);
        
        }else if(aNode.getKind()==CFANodeKind.OPTION){
            
        }else if(aNode.getKind()==CFANodeKind.PLUS){
            
        }
    }
    private void drawState(int aState, int aX, int aY, Graphics g){
        Rectangle vStateRect;
        String vText;
        Point vTextLoc;
    
                   
             
        vStateRect=new Rectangle(aX-rSize, aY-rSize, 2*rSize, 2*rSize);
        vText = Integer.toString(aState);
        FontMetrics vMetrics=g.getFontMetrics();
        int vStringWidth=vMetrics.stringWidth(vText);
        int vStringHeight=vMetrics.getHeight();
        int vX = (vStringWidth)/2;
        int vY = (vStringHeight)/2;
  
        vTextLoc=new Point(aX-vX,aY);
  
           g.setColor(Color.blue);
           if(stateShape==CStateShape.SQUARE){
              g.drawRect(vStateRect.x, vStateRect.y, vStateRect.width,vStateRect.height); 
              g.drawString(vText,vTextLoc.x,vTextLoc.y);
           }else if(stateShape==CStateShape.CIRCLE){
              g.drawOval(vStateRect.x, vStateRect.y, vStateRect.width,vStateRect.height);
              g.drawString(vText,vTextLoc.x,vTextLoc.y);
           }
           
   }
   
private void drawEdgeLabel(String aLabel, int aX, int aY, Graphics g){
   
    int vX,vY;
    Rectangle vTextRect;
    FontMetrics vMetrics=g.getFontMetrics();
    int vStringWidth=vMetrics.stringWidth(aLabel);
    int vStringHeight=vMetrics.getHeight();
    vX = (vStringWidth)/2;
    vY = (vStringHeight)/2;
    vTextRect=new Rectangle(aX - vX, aY - vY, vX+vX, vY+vY);
    vTextRect.grow(5,0);
    g.setColor(Color.white);
    g.fillRect(vTextRect.x,vTextRect.y,vTextRect.width,vTextRect.height);
    g.setColor(Color.black);
    g.drawString(aLabel,aX - vX, aY );

    }
    
    
    private void drawStates(CFANode aNode, int aX, int aY, Graphics g){
        Point vLeftPoint, vRightPoint;
        CFANode vSub;     
        if(aNode.getKind()==CFANodeKind.EMPTY||aNode.getKind()==CFANodeKind.EPS||aNode.getKind()==CFANodeKind.STRING){
          // nothing
        } else if(aNode.getKind()==CFANodeKind.DOT){
            for(int i=1;i<aNode.getElts().size();i++){
             vSub=aNode.getElts().get(i);
             vLeftPoint=new Point(aX + vSub.getX(),
             aY + vSub.getY() + vSub.getHeight()/2);
             drawState(aNode.getIStates().get(i-1), vLeftPoint.x, vLeftPoint.y, g);
            }
        } else if(aNode.getKind()==CFANodeKind.STICK){
          for(int i=0;i<aNode.getElts().size();i++){
              vSub=aNode.getElts().get(i);
              vLeftPoint=new Point(
              aX + vSub.getX(),
              aY + vSub.getY() + vSub.getHeight() / 2);
              vRightPoint=new Point(
              aX + vSub.getX() + vSub.getWidth(),
              aY + vSub.getY() + vSub.getHeight() / 2);

            // Draw states at connection points
            drawState( aNode.getIStates().get(2*i), vLeftPoint.x, vLeftPoint.y, g);
            drawState(aNode.getIStates().get(2*i+1), vRightPoint.x, vRightPoint.y, g);
          }
        }else if(aNode.getKind()==CFANodeKind.STAR||aNode.getKind()==CFANodeKind.OPTION||aNode.getKind()==CFANodeKind.PLUS){
          
            // Determine connection points of subgraph
           vSub=aNode.getElt();
           vLeftPoint=new Point(
            aX + vSub.getX(),
            aY + vSub.getY() + vSub.getHeight() / 2);
            vRightPoint=new Point(
            aX + vSub.getX() + vSub.getWidth(),
            aY + vSub.getY() + vSub.getHeight() / 2);

          // Draw states at connection points
          drawState(aNode.getIStates().get(0), vLeftPoint.x, vLeftPoint.y, g);
          drawState(aNode.getIStates().get(1), vRightPoint.x, vRightPoint.y, g);
        }
    }
   
    private void drawSubGraphs(CFANode aNode, int aX, int aY, Graphics g){
       CFANode vElt;
       if(aNode.getKind()==CFANodeKind.EMPTY ||aNode.getKind()==CFANodeKind.EPS||aNode.getKind()== CFANodeKind.STRING){
         // Skip
       }else if(aNode.getKind()==CFANodeKind.DOT ||aNode.getKind()==CFANodeKind.STICK){
          for(int i=0;i<aNode.getElts().size();i++){
            vElt = aNode.getElts().get(i);
            drawGraph(vElt, aX + vElt.getX(), aY + vElt.getY(),g);
          } 
       }  else if(aNode.getKind()==CFANodeKind.STAR||aNode.getKind()==CFANodeKind.OPTION||aNode.getKind()==CFANodeKind.PLUS){
           drawGraph(aNode.getElt(), aX + aNode.getElt().getX(), aY + aNode.getElt().getY(), g);
       }     
    }
    private void drawGraph(CFANode aNode, int aX, int aY, Graphics g){
        if (drawBoundingBoxes){
            drawBoundingBox(aNode, aX, aY,g);
        }

        drawSubGraphs(aNode,aX, aY,g);
        drawEdges(aNode,aX,aY,g);
        drawStates(aNode,aX,aY,g);
    }
    
    // layout and draw commands
    public void calculateLayout(Graphics g){
        Point vLocation;
        if(faRoot!=null){
            auxCalc(faRoot,g);
            vLocation = new Point(hSpace + rSize,faRoot.getHeight() + vSpace);
            faRoot.setX(vLocation.x);
            faRoot.setY(vLocation.y);
        }    
    }
    public void draw(Graphics g){
        int vWidth;
        int vHeight;
        drawGraph(faRoot, hSpace+rSize, vSpace,g);
        vWidth=faRoot.getWidth();
        vHeight=faRoot.getHeight();
        drawState(initialState, hSpace + rSize, (vHeight / 2) + vSpace, g);
        drawState(finalState, hSpace + rSize + vWidth , (vHeight /2) + vSpace, g);
    }

    public int getHeight(){
        if (faRoot !=null)
            return faRoot.getHeight();
        else return 0;
    }
    public int getWidth(){
        if(faRoot!=null)
            return faRoot.getWidth();
        else return 0;
  
    }
    
    public CFANode REtoFA(CRE aRE){
        CFANode vResult=nFactory.MkString("");
        switch(aRE.sortCode()){
            case CGrammarCodes.scREEmpty:
                vResult=nFactory.MkEmpty();
                break;
            case CGrammarCodes.scREEps:
                vResult= nFactory.MkEps();
                break;
            case CGrammarCodes.scREString:   
            case CGrammarCodes.scREChar:
                 vResult=nFactory.MkString(aRE.getData(0));
                 break;
            case CGrammarCodes.scREMultiDot:
                CRE_MultiDot vDot=(CRE_MultiDot)aRE;
                vResult=nFactory.MkDot(new ArrayList<CFANode>());
                CRE_List vList=(CRE_List)vDot.getTerm(0);
                vResult.getElts().add(0,REtoFA(vList.getElt(0)));
                for(int i=1; i<vList.termCount();i++){
                    vResult.getIStates().add(i-1,newState());
                    vResult.getElts().add(i,REtoFA(vList.getElt(i)));
                }
                break;
            case CGrammarCodes.scREMultiStick:
                CRE_MultiStick vStick=(CRE_MultiStick)aRE;
                vResult=nFactory.MkStick(new ArrayList<>());
                CRE_List vList1=(CRE_List)vStick.getTerm(0);
                for(int i=0; i<vList1.termCount();i++){
                    vResult.getIStates().add(2*i,newState());
                    vResult.getIStates().add(2*i+1,newState());
                    vResult.getElts().add(i,REtoFA(vList1.getElt(i)));
                }
                  
                break;
             case CGrammarCodes.scREStar:
                vResult= nFactory.MkStar(new CFANode());
                CRE_Star vStar=(CRE_Star)aRE;
                vResult.getIStates().add(0,newState());
                vResult.getIStates().add(1,newState());
                vResult.setElt(REtoFA(vStar.Arg()));
                break;
            case CGrammarCodes.scREPlus:
                CRE_Plus vPlus=(CRE_Plus)aRE;
                vResult= nFactory.MkPlus(REtoFA(vPlus.Arg()));
                break;    
                
            case CGrammarCodes.scREOPtion:
                CRE_Option vOption=(CRE_Option)aRE;
                vResult= nFactory.MkOption(REtoFA(vOption.Arg()));
                break;
        }
  
        return vResult;
    }
    private int newState(){
        return stateCount++;
    }
   
}
