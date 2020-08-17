/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout;

import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.RegExpr.CRE_Char;
import GrammarNotions.RegExpr.CRE_List;
import GrammarNotions.RegExpr.CRE_Range;
import GrammarNotions.RegExpr.CRE_String;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Ssanyu
 */
public class CRETreeGraph extends CTreeGraph{

    /**
     *
     * @param root
     */
    public CRETreeGraph(CTreeNode root){
        super(root);
    }
    
    /**
     *
     * @param aRE
     * @return
     */
    public CTreeNode RENodetoTreeNode(CRE aRE){
        CTreeNode vResult=null;
        if(aRE!=null){
           switch(aRE.sortCode()){
               case CGrammarCodes.scREChar:
                    CRE_Char cRE=(CRE_Char)aRE;
                    vResult=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),""+cRE.Char(),new ArrayList<CTreeNode>());
                    break;
               case CGrammarCodes.scREString:
                    CRE_String sRE=(CRE_String)aRE;
                    vResult= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),sRE.Str(),new ArrayList<CTreeNode>());
               break;
              case CGrammarCodes.scREMultiDot:
                  CRE_List vList=(CRE_List)aRE.getTerm(0);
                   ArrayList<CTreeNode> nt2=new ArrayList<>(); 
                   for(int i=0;i<vList.termCount();i++){
                        CRE mNode=vList.getElt(i);
                        nt2.add(RENodetoTreeNode(mNode));
                   }
                   vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,new Color(204,204,255),".",nt2);   
              break;
              case CGrammarCodes.scREMultiStick:
                  CRE_List vList2=(CRE_List)aRE.getTerm(0);
                    ArrayList<CTreeNode> nt3=new ArrayList<>(); 
                    for(int i=0;i<vList2.termCount();i++){
                        CRE sNode=vList2.getElt(i);
                        nt3.add(RENodetoTreeNode(sNode));
                    }
                    vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,new Color(204,204,255),"|",nt3);   
              break;  
           case CGrammarCodes.scRERange:
                  CRE_Range rRE=(CRE_Range)aRE;
                  vResult=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),"["+rRE.Low()+"-"+rRE.High()+"]",new ArrayList<CTreeNode>());
            break;   
           case CGrammarCodes.scREStar:
               ArrayList<CTreeNode> nt5=new ArrayList<>(); 
               CRE stNode=(CRE)aRE.getTerm(0);
               nt5.add(RENodetoTreeNode(stNode));
               vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"*",nt5);   
           break;    
           case CGrammarCodes.scREOPtion:
               ArrayList<CTreeNode> nt6=new ArrayList<>(); 
               CRE oNode=(CRE)aRE.getTerm(0);
               nt6.add(RENodetoTreeNode(oNode));
               vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"?",nt6);   
           break;    
           case CGrammarCodes.scREPlus:
               ArrayList<CTreeNode> nt7=new ArrayList<>(); 
               CRE pNode=(CRE)aRE.getTerm(0);
               nt7.add(RENodetoTreeNode(pNode));
               vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.COMB,new Color(204,204,255),"+",nt7);   
            break;
           case CGrammarCodes.scREEmpty:
               vResult=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),"\\Empty",new ArrayList<CTreeNode>());  
            break;  
           case CGrammarCodes.scREEps:
               vResult=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),"\\Eps",new ArrayList<CTreeNode>());  
           break; 
           default:
           vResult=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,Color.RED,"Error",new ArrayList<CTreeNode>());
           break;
        }
       
    }
return vResult;

    }
    
}
