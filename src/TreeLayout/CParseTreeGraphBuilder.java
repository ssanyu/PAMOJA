/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout;

import GrammarNotions.ECFGNodes.CECFGNode;
import GrammarNotions.ECFGNodes.CECFGNodeCodes;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Ssanyu
 */
public class CParseTreeGraphBuilder extends CTreeGraph {

    /**
     *
     * @param root
     */
    public CParseTreeGraphBuilder(CTreeNode root) {
        super(root);
    }
    
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
                   vResult= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),aNode.getData(2),nt);
               break;
               case CECFGNodeCodes.scTermValueNode:
                    CTreeNode vt=new CTreeNode(CNodeStyle.NOFRAME,CEdgeStyle.ABSENT,Color.BLACK,aNode.getData(3),new ArrayList<CTreeNode>());
                    ArrayList<CTreeNode> nt1=new ArrayList<>();
                    nt1.add(vt);
                    vResult= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(255,153,255),aNode.getData(2),nt1);
               break;
               case CECFGNodeCodes.scTermNode:
                    vResult= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(255,153,255),aNode.getData(2),new ArrayList<CTreeNode>());
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
}
