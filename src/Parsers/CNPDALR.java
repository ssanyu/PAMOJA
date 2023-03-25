/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

import TreeLayout.CEdgeStyle;
import TreeLayout.CNodeStyle;
import TreeLayout.CTreeNode;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CNPDALR extends CParserExt implements INPDALR{
    private CParseLog log;
    private CParseStack tempSymStack;
    private ArrayList<String> symStackOptions;
    private ArrayList<CTreeNode> matchedNodes;
    private CTreeNode fTree;
    private String action;
    
    /**
     *
     */
    public CNPDALR(){
        super();
        Stack=new CParseStack();
        tempSymStack=new CParseStack();
        symStackOptions=new ArrayList();
        treeStack=new ArrayList();
        matchedNodes=new ArrayList();
        fParserResult=new CParserResult();
        fTree=null;
        action=new String();
      }

    /**
     *
     * @return
     */
    public CParseStack getTempSymStack() {
        return tempSymStack;
    }

    /**
     *
     * @param tempSymStack
     */
    public void setTempSymStack(CParseStack tempSymStack) {
        this.tempSymStack = tempSymStack;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getSymStackOptions() {
        return symStackOptions;
    }

    /**
     *
     * @param symStackOptions
     */
    public void setSymStackOptions(ArrayList<String> symStackOptions) {
        this.symStackOptions = symStackOptions;
    }

    /**
     *
     * @return
     */
    public ArrayList<CTreeNode> getMatchedNodes() {
        return matchedNodes;
    }

    /**
     *
     * @param matchedNodes
     */
    public void setMatchedNodes(ArrayList<CTreeNode> matchedNodes) {
        this.matchedNodes = matchedNodes;
    }

    /**
     *
     * @return
     */
    public CTreeNode getfTree() {
        return fTree;
    }

    /**
     *
     * @param fTree
     */
    public void setfTree(CTreeNode fTree) {
        this.fTree = fTree;
    }

    /**
     *
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     *
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }
 
    /**
     *
     */
    public void start() {
       Stack=new CParseStack();
       Stack.setRightAlign(false);
       tempSymStack=new CParseStack();
       symStackOptions=new ArrayList();
       treeStack=new ArrayList();
       Logs=new ArrayList();
       symbolsMatched=new ArrayList();
       symbolsUnmatched=new ArrayList();
       symbolsUnmatched.addAll(getInputSymbolStream());
       stringMatched=new String();
       stringUnmatched=getSymbolStreamValues();
       fSym=getSymValue();
       action=new String();
       log=new CParseLog(stringMatched,stringUnmatched,tempSymStack,action);
       Logs.add(log);
    }

    /**
     *
     */
    public void shift(){
        fSym=getSymValue();
        Stack.push(fSym);
        symStackOptions.add(getSymName());
        CTreeNode t=new CTreeNode(CNodeStyle.NOFRAME,CEdgeStyle.ABSENT,Color.BLACK,fSym,new ArrayList<CTreeNode>());
        fTree= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255),getSymName(),new ArrayList());
        fTree.addSon(t);
        treeStack.add(fTree);
        symbolsMatched.add(symbolsUnmatched.get(0));
        stringMatched="";
        for(int k=0;k<symbolsMatched.size();k++)
           stringMatched=stringMatched+symbolsMatched.get(k).symValue();
        symbolsUnmatched.remove(0);
        stringUnmatched=stringUnmatched.substring(getSymValue().length());
        nextSym();
        fSym=getSymValue();
        tempSymStack=new CParseStack();
        tempSymStack.pushAll(Stack);
        log=new CParseLog(stringMatched,stringUnmatched,tempSymStack,action);
        Logs.add(log);
    }

    /**
     *
     */
    public void reduce(){
        String prodName;
        int lengthRHS;
        CTreeNode r;
        String [] t=action.split("=");
        prodName=t[0];
        String [] RHS=t[1].split("\\.");
        lengthRHS=RHS.length;
        ArrayList<CTreeNode> vDotNode;
        if(lengthRHS<=Stack.size()){
        for(int j=0;j<lengthRHS;j++){
          Stack.pop(Stack.size()-1);
          symStackOptions.remove(symStackOptions.size()-1);
        }
        Stack.push(prodName);
        symStackOptions.add(prodName);
        if(lengthRHS==0){
            r=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),"Eps",new ArrayList<CTreeNode>());
            fTree=r;
        }else{
            vDotNode=new ArrayList();
            for(int k=treeStack.size()-lengthRHS;k<treeStack.size();k++){
                vDotNode.add(treeStack.get(k));
            }
            for (int j=0; j<lengthRHS; j++){
               treeStack.remove(treeStack.size()-1);
            }
            if(vDotNode.size()==1){
               fTree=vDotNode.get(0);
               r= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),prodName,new ArrayList());
               r.addSon(fTree);
            }else{
                
               r= new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),prodName,vDotNode);
            }
            fTree=r;
            treeStack.add(fTree);
            tempSymStack=new CParseStack();
            tempSymStack.pushAll(Stack);
            log=new CParseLog(stringMatched,stringUnmatched,tempSymStack,action);
            Logs.add(log);
        }
        }
    }

    /**
     *
     */
    public void accept(){
        action="Accept";
       log=new CParseLog(stringMatched,stringUnmatched,tempSymStack,"Accept");
       Logs.add(log); 
    }

    /**
     *
     */
    public void error(){
        action="Error";
       log=new CParseLog(stringMatched,stringUnmatched,tempSymStack,"Error");
       Logs.add(log); 
    }
    
     @Override
    public CParserResult parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CParserResult parseNonTerminal(String aName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
