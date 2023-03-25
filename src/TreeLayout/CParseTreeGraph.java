/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout;

import GrammarNotions.ECFGNodes.CECFGNode;
import GrammarNotions.ECFGNodes.CECFGNodeCodes;
import GrammarNotions.ECFGNodes.CMultiDotNode;
import GrammarNotions.ECFGNodes.CMultiStickNode;
import Nodes.CNode;
import SymbolStream.CSymbolStream;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Ssanyu
 */
public class CParseTreeGraph extends CTreeGraph {

    private CSymbolStream symbolStream;
    /**
     *
     * @param root
     */
    public CParseTreeGraph(CTreeNode root) {
        super(root);
    }

    /**
     *
     */
    public CParseTreeGraph() {
       
    }

    /**
     *
     * @return
     */
    public CSymbolStream getSymbolStream() {
        return symbolStream;
    }

    /**
     *
     * @param symbolStream
     */
    public void setSymbolStream(CSymbolStream symbolStream) {
        this.symbolStream = symbolStream;
    }
    /**
     * Tweaked function to enable suppression of stick and dot nodes in the intermediate tree produced from a parse tree
     * @param p
     * @return 
    */
   public CTreeNode ParseTreetoTreeNode(CECFGNode p){
       if(p!=null)
       System.out.println(p);
        CTreeNode r=null;
        if(p!=null){
            switch(p.sortCode()){
              case CECFGNodeCodes.scNonTermNode:
                   CECFGNode h; // used to inspect the underlying structure
                   r= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),p.getData(0),p.getAnno(p.getData(0)).toString(),new ArrayList());
                   h=p;
                   if(h.getNode(0) instanceof CMultiStickNode)
                       h=(CECFGNode)h.getNode(0);
                   if(h.getNode(0) instanceof CMultiDotNode)
                        h=(CECFGNode)h.getNode(0);
                   //Apply the function recursively to the sons of h
                   for(int i=0;i<h.count();i++){  
                       r.addSon(ParseTreetoTreeNode((CECFGNode)h.getNode(i)));
                   }  
               break; 
              case CECFGNodeCodes.scTermValueNode:
                    CTreeNode t; // for storing the value of a terminal node
                    t=new CTreeNode(CNodeStyle.NOFRAME,CEdgeStyle.ABSENT,Color.BLACK,p.getData(3),new ArrayList<CTreeNode>());
                    r= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255),p.getData(2),p.getAnno(p.getData(2)).toString(),new ArrayList());
                    r.addSon(t);
                    break;
               case CECFGNodeCodes.scTermNode:
                    r= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255),p.getData(2),new ArrayList<CTreeNode>());
               break;
              case CECFGNodeCodes.scMultiStickNode:
                   r=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,new Color(204,204,255),"",new ArrayList());   
                   r.addSon(ParseTreetoTreeNode((CECFGNode)p.getNode(0)));
             
              break;  
              case CECFGNodeCodes.scMultiDotNode:
                   r=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,new Color(204,204,255),"",new ArrayList());   
                   for(int i=0;i<p.count();i++){
                     r.addSon(ParseTreetoTreeNode((CECFGNode)p.getNode(i)));
                   }
              break;  
              case CECFGNodeCodes.scEpsNode:
                  
                    r=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255) ,"~",new ArrayList());
                    
                    break;
              case CECFGNodeCodes.scAltNode:
                    ArrayList<CTreeNode> nt4=new ArrayList<>(); 
                    for(int i=0;i<p.count();i++){
                        CECFGNode tNode=(CECFGNode)p.getNode(i);
                        nt4.add(ParseTreetoTreeNode(tNode));
                    }
                    r=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"%",nt4);   
            break;   
           case CECFGNodeCodes.scStarNode:
               ArrayList<CTreeNode> nt5=new ArrayList<>(); 
               for(int i=0;i<p.count();i++){
                   CECFGNode stNode=(CECFGNode)p.getNode(i);
                   nt5.add(ParseTreetoTreeNode(stNode));
               }
               r=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"*",nt5);   
           break;    
           case CECFGNodeCodes.scOptionNode:
               ArrayList<CTreeNode> nt6=new ArrayList<>(); 
               CECFGNode oNode=(CECFGNode)p.getNode(0);
               if(oNode!=null){
                 nt6.add(ParseTreetoTreeNode(oNode));
                 r=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"?",nt6);  
               } else{
                 r=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"?",new ArrayList<CTreeNode>());   
               }
           break;    
           case CECFGNodeCodes.scPlusNode:
               ArrayList<CTreeNode> nt7=new ArrayList<>(); 
               for(int i=0;i<p.count();i++){
                   CECFGNode pNode=(CECFGNode)p.getNode(i);
                   nt7.add(ParseTreetoTreeNode(pNode));
               }
               r=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"+",nt7);   
            break;
           default:
           r=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,Color.BLUE,"None",new ArrayList<CTreeNode>());
           break;
            }
        }
        return r;
    }
    /* *
     * @param aNode
     * @return
     */

    /**
     *
     * @param aNode
     * @return
     */
    
  public CTreeNode ECFGNodetoTreeNode(CECFGNode aNode){
        CTreeNode vResult=null;
        if(aNode!=null){
           switch(aNode.sortCode()){
               case CECFGNodeCodes.scEpsNode:
                    vResult=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),"\\Eps",new ArrayList<CTreeNode>());
                    break;
               case CECFGNodeCodes.scNonTermNode:
                   CECFGNode vNode=(CECFGNode)aNode.getNode(0);
                   ArrayList<CTreeNode> nt=new ArrayList<>();
                   nt.add(ECFGNodetoTreeNode(vNode));
                   vResult= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),aNode.getData(0),aNode.getAnno(aNode.getData(0)).toString(),nt);
               break;
               case CECFGNodeCodes.scTermValueNode:
                    CTreeNode vt=new CTreeNode(CNodeStyle.NOFRAME,CEdgeStyle.ABSENT,Color.BLACK,aNode.getData(3),new ArrayList<CTreeNode>());
                    ArrayList<CTreeNode> nt1=new ArrayList<>();
                    nt1.add(vt);
                   // vResult= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255),aNode.getData(2),new ArrayList<CTreeNode>());
                    vResult= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255),aNode.getData(2),aNode.getAnno(aNode.getData(2)).toString(),nt1);
                    break;
               case CECFGNodeCodes.scTermNode:
                    vResult= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255),aNode.getData(2),new ArrayList<CTreeNode>());
               break;
              case CECFGNodeCodes.scMultiDotNode:
                   ArrayList<CTreeNode> nt2=new ArrayList<>(); 
                   for(int i=0;i<aNode.count();i++){
                        CECFGNode mNode=(CECFGNode)aNode.getNode(i);
                        nt2.add(ECFGNodetoTreeNode(mNode));
                   }
                   vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,new Color(204,204,255),".",nt2);   
              break;
              case CECFGNodeCodes.scMultiStickNode:
                    ArrayList<CTreeNode> nt3=new ArrayList<>(); 
                    for(int i=0;i<aNode.count();i++){
                        CECFGNode sNode=(CECFGNode)aNode.getNode(i);
                        nt3.add(ECFGNodetoTreeNode(sNode));
                    }
                    vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,new Color(204,204,255),"|_"+aNode.getData(2),nt3);   
              break;  
           case CECFGNodeCodes.scAltNode:
                    ArrayList<CTreeNode> nt4=new ArrayList<>(); 
                    for(int i=0;i<aNode.count();i++){
                        CECFGNode tNode=(CECFGNode)aNode.getNode(i);
                        nt4.add(ECFGNodetoTreeNode(tNode));
                    }
                    vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"%",nt4);   
            break;   
           case CECFGNodeCodes.scStarNode:
               ArrayList<CTreeNode> nt5=new ArrayList<>(); 
               for(int i=0;i<aNode.count();i++){
                   CECFGNode stNode=(CECFGNode)aNode.getNode(i);
                   nt5.add(ECFGNodetoTreeNode(stNode));
               }
               vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"*",nt5);   
           break;    
           case CECFGNodeCodes.scOptionNode:
               ArrayList<CTreeNode> nt6=new ArrayList<>(); 
               CECFGNode oNode=(CECFGNode)aNode.getNode(0);
               if(oNode!=null){
                 nt6.add(ECFGNodetoTreeNode(oNode));
                 vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"?",nt6);  
               } else{
                 vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"?",new ArrayList<CTreeNode>());   
               }
           break;    
           case CECFGNodeCodes.scPlusNode:
               ArrayList<CTreeNode> nt7=new ArrayList<>(); 
               for(int i=0;i<aNode.count();i++){
                   CECFGNode pNode=(CECFGNode)aNode.getNode(i);
                   nt7.add(ECFGNodetoTreeNode(pNode));
               }
               vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"+",nt7);   
            break;
           default:
           vResult=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,Color.BLUE,"None",new ArrayList<CTreeNode>());
           break;
        }
       }
return vResult;

    }
    
    /**
     *
     * @param aNodes
     * @return
     */
    public ArrayList<CTreeNode> ListECFGNodetoListTreeNode(ArrayList<CNode> aNodes){
        ArrayList<CTreeNode> vNodes=new ArrayList();
        if(!aNodes.isEmpty()){
            for(int i=0;i<aNodes.size();i++){
                vNodes.add(ParseTreetoTreeNode((CECFGNode)aNodes.get(i)));
            }
            
        }
        return vNodes;
    }
}
