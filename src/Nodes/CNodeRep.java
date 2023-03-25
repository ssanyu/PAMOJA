/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes;

import General.CGeneral;
import Sets.Alphabet;
import Sets.AlphabetOps;





/**
 *
 * @author ssanyu
 */
public class CNodeRep {
    private int charPos;
    private char ch;

    /**
     *
     * @param aNode
     * @return
     */
    public static String nodeTreeToString(CNode aNode){
        StringBuffer result=new StringBuffer();
        result.append("");
        if(aNode!=null){
            result.append(aNode.sortLabel()).append('[');
            if(aNode.dataCount()>0){
                result.append('"').append(aNode.getData(0)).append('"');
                for(int i=1;i<=aNode.dataCount()-1;i++){
                    result.append(',').append('"').append(aNode.getData(i)).append('"');
                }
            }
            result.append("][");
            if(aNode.count()>0){
                result.append(nodeTreeToString(aNode.getNode(0)));
                for(int i=1;i<=aNode.count()-1;i++){
                    result.append(',').append(nodeTreeToString(aNode.getNode(i)));
                }
            }
             result.append(']');
        }
        return result.toString();
    }

    /**
     *
     * @param aString
     * @param aNodeFactory
     * @return
     */
    public CNode stringToNodeTree(String aString, CNodeFactory aNodeFactory){
        CNode vTree;
        boolean success=false;
        if(aString.length()==0){
           vTree=null;
        }else{
            charPos=1;
            nextCh(aString);
            vTree=pTree(aString,aNodeFactory);
            success=true;
        }
        if(success==false){
            vTree=null;
        }
        return vTree;
  }
    private void nextCh(String aString){
        ch=aString.charAt(charPos);
        charPos=charPos+1;
    }
    private String pData(String aString){
        String vData;
        check('"',aString);
        vData="";
        while(ch!='"'){
            vData=vData+ch;
            nextCh(aString);
        }
        check('"',aString);
        return vData;
    }
    private String pSortLabel(String aString){
         String vLabel="";
         Alphabet vSet;
         
         vSet=AlphabetOps.union(CGeneral.csLetterDigit, new Alphabet('_'));
         while(vSet.has(ch)){
             vLabel=vLabel+ch;
             nextCh(aString);
         }
         return vLabel;
    }
    private void pTuple(String aString,CNode aNode,CNodeFactory aNodeFactory){
        String vData;
        CNode vNode;
        int vNodeCount;
        int vDataCount;
        // read data part
        vDataCount=0;
        check('[',aString);
        if(ch!=']'){ //non-empty data part
           vData=pData(aString);
           aNode.setData(vDataCount,vData);
           vDataCount=1;
           while(ch==','){
               nextCh(aString);
               vData=pData(aString);
               aNode.setData(vDataCount,vData);
               vDataCount=vDataCount+1;
           }
        }
        check(']',aString);
        // read node part
        vNodeCount=0;
        check('[',aString);
        if(ch!=']'){// non-empty node part
            vNode=pTree(aString,aNodeFactory);
            aNode.setNode(vNodeCount,vNode);
            vNodeCount=1;
            while(ch==','){
                nextCh(aString);
                vNode=pTree(aString,aNodeFactory);
                aNode.setNode(vNodeCount,vNode);
                vDataCount=vDataCount+1;
            }
         }
         check(']',aString);
    }
    private void check(char aChar, String aString){
        if(ch==aChar){
            nextCh(aString);
        }else{
           System.out.println("check in " + aString + "expected "+ aChar+",found"+ ch+ "at"+ "position"+ charPos);
        }
    }
    private void pList(INodeList aList,String aString,CNodeFactory aNodeFactory){
        String vData;
        int vDataCount;
        CNode vNode;
        
         // read data part (fixed number of data (always 0?))
        vDataCount=0;
        check('[',aString);
        if(ch!=']'){// non-empty data part
           vData=pData(aString);
           aList.setData(vDataCount,vData);
           vDataCount=1;
           while(ch==','){
               nextCh(aString);
               vData=pData(aString);
               aList.setData(vDataCount,vData);
               vDataCount=vDataCount+1;
           }
        }
        check(']',aString);
        // read node part (variable number of nodes)
        check('[',aString);
        if(ch!=']'){ // non-empty node list
           vNode=pTree(aString,aNodeFactory);
           aList.add(vNode);
           while(ch==','){
               nextCh(aString);
               vNode=pTree(aString,aNodeFactory);
               aList.add(vNode);
           }
        }
        check(']',aString);
    }
    private CNode pTree(String aString,CNodeFactory aNodeFactory){
        CNode vNode;
        String vSortLabel;
        vSortLabel=pSortLabel(aString);
        vNode=aNodeFactory.makeNodeofLabel(vSortLabel);
        switch(vNode.kind()){
            case TUPLE:
                pTuple(aString,vNode,aNodeFactory);
                break;
            case LIST:
                pList((INodeList)vNode, aString,aNodeFactory);
                break;
            default:
                System.out.println("Unexpected node kind for sort:"+vNode.sortLabel());
        }
        return vNode;
    }
}
