/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeLayout;

import Nodes.CNode;
import Nodes.CNodeKind;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CASTGraphBuilder extends CTreeGraph {

    /**
     *
     * @param root
     */
    public CASTGraphBuilder(CTreeNode root) {
        super(root);
    }
    
    /**
     *
     * @param aNode
     * @return
     */
    public CTreeNode ASTNodetoTreeNode(CNode aNode){
        CTreeNode vResult=null;
        if(aNode!=null){
            if(aNode.kind()==CNodeKind.TUPLE){
                ArrayList<CTreeNode> nt1=new ArrayList<>();
                for(int i=0;i<aNode.count();i++){
                    nt1.add(ASTNodetoTreeNode(aNode.getNode(i)));
                }
               if(aNode.dataCount()>0)
                vResult=new CTreeNode(CNodeStyle.NOFRAME,CEdgeStyle.FAN,Color.BLACK,aNode.getData(0),nt1);
                else vResult=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(255,153,255),aNode.sortLabel(),nt1);
            }else if(aNode.kind()==CNodeKind.LIST){
                ArrayList<CTreeNode> nt1=new ArrayList<>();
                for(int i=0;i<aNode.count();i++){
                    nt1.add(ASTNodetoTreeNode(aNode.getNode(i)));
                }
                vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,new Color(204,153,255),aNode.sortLabel(),nt1);
                         
            }else if(aNode.kind()==CNodeKind.HOLE){
                vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,Color.RED,aNode.sortLabel(),new ArrayList());
            }else if(aNode.kind()==CNodeKind.OPTION){
               ArrayList<CTreeNode> nt1=new ArrayList<>(); 
               CNode oNode=aNode.getNode(0);
               if(oNode!=null){
                 nt1.add(ASTNodetoTreeNode(oNode));
                 vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,new Color(204,153,255),aNode.sortLabel(),nt1);  
               } else{
                 vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,new Color(204,153,255),aNode.sortLabel(),new ArrayList<CTreeNode>());   
               }
            }else  vResult=new CTreeNode(CNodeStyle.OVAL,CEdgeStyle.FAN,Color.RED,"Error",new ArrayList<CTreeNode>());
        }
        
       
        
        return vResult;
    }
    
}
