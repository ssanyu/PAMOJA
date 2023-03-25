/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

import GrammarNotions.SyntaxExpr.CNonTerminalDef;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSE_MultiDot;
import SymbolStream.Symbol;
import TreeLayout.CEdgeStyle;
import TreeLayout.CNodeStyle;
import TreeLayout.CTreeNode;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CNPDALL extends CParserExt implements INPDALL{
    private CParseLog log;
    private CParseStack tempStack;
    private ArrayList<CTreeNode> matchedNodes;
    private CTreeNode fTree;
    private CTreeNode topNode;
    private ArrayList<Symbol> symbolsMatched;
    private ArrayList<Symbol> symbolsUnmatched;
    private String stringMatched;
    private String stringUnmatched;
    private String action;
    private CNonTerminalDef nonTerminal;
    private String topSymbol;
    private ArrayList<CTreeNode>l;
    private String RHSSyms[];
    
    /**
     *
     */
    public CNPDALL(){
        super();
        Stack=new CParseStack();
        tempStack=new CParseStack();
        treeStack=new ArrayList();
        matchedNodes=new ArrayList();
        fParserResult=new CParserResult();
        symbolsMatched=new ArrayList();
        symbolsUnmatched=new ArrayList();
        stringMatched=new String();
        stringUnmatched=new String();
        fTree=null;
        action=new String();
        nonTerminal=null;
        topSymbol=new String();
        RHSSyms=new String[100];
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
    public String getfSym() {
        return fSym;
    }

    /**
     *
     * @param RHSSyms
     */
    public void setRHSSyms(String[] RHSSyms) {
        this.RHSSyms = RHSSyms;
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
     * @param topSymbol
     */
    public void setTopSymbol(String topSymbol) {
        this.topSymbol = topSymbol;
    }
    
    /**
     *
     * @return
     */
    public String getTopSymbol() {
        return topSymbol;
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
     * @return
     */
    public CTreeNode getTopNode() {
        return topNode;
    }

    /**
     *
     * @param topNode
     */
    public void setTopNode(CTreeNode topNode) {
        this.topNode = topNode;
    }

    /**
     *
     * @return
     */
    public ArrayList<Symbol> getSymbolsMatched() {
        return symbolsMatched;
    }

    /**
     *
     * @param symbolsMatched
     */
    public void setSymbolsMatched(ArrayList<Symbol> symbolsMatched) {
        this.symbolsMatched = symbolsMatched;
    }

    /**
     *
     * @return
     */
    public ArrayList<Symbol> getSymbolsUnmatched() {
        return symbolsUnmatched;
    }

    /**
     *
     * @param symbolsUnmatched
     */
    public void setSymbolsUnmatched(ArrayList<Symbol> symbolsUnmatched) {
        this.symbolsUnmatched = symbolsUnmatched;
    }

    /**
     *
     */
    public void start(){
        Stack=new CParseStack();
        Stack.setRightAlign(true);
        tempStack=new CParseStack();
        treeStack=new ArrayList();
        matchedNodes=new ArrayList();
        symbolsMatched=new ArrayList();
        stringMatched=new String();
        symbolsUnmatched=new ArrayList();
        symbolsUnmatched.addAll(getInputSymbolStream());
        stringUnmatched=getSymbolStreamValues();
        action=new String();
        Logs=new ArrayList();
        topSymbol=new String();
        
        CSE vStartExpr;
            // Obtain the start expression and push it onto stack.
         vStartExpr=fGrammar.getStartExpr();
            assert(fGrammar!=null): "CNDPDA_LL1.start() failed:Grammar property not assigned";
            assert(fSymbolStream!=null): "NDPDA_LL1().start() failed:SymbolStream property not assigned";
            assert(vStartExpr instanceof CSE):"NDPDA_LL1().start() failed:nStart Expression should be a syntax expression"; 
            CSE_MultiDot vDot=(CSE_MultiDot)vStartExpr;
            assert(vDot instanceof CSE_MultiDot): "Grammar should be augumented";
        
            CSE s=vDot.getList().getElt(0);
           // CSE e=vDot.getList().getElt(1);
           Stack.push(fGrammar.getName(s));
           Stack.push("$");
            
            
            fTree=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),fGrammar.getName(s),new ArrayList());
            topNode=null;
            treeStack.add(fTree);
            topNode=treeStack.get(0);
            tempStack.pushAll(Stack);
            log=new CParseLog(stringMatched,stringUnmatched,tempStack,action);
            Logs.add(log);
            fSym=getSymName(); 
    }
    
    
    
  /*  public void step(){
            topSymbol=stack.peek();
            if(fGrammar.getGrammarContext().isTerminal(topSymbol)){
                matchTerminal();
            }else if(fGrammar.getGrammarContext().isNonTerminal(topSymbol)){
                replaceNonterminal(String[] t);
            }
    }*/

    /**
     *
     */
    
    
    public void matchTerminal(){
        if(getSymName().equals(topSymbol)){
            //fTree.addSon(new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255),fSym,new ArrayList()));
           Stack.pop(); 
            String s=symbolsUnmatched.get(0).symValue();
            CTreeNode v=treeStack.get(0);
            v.addSon(new CTreeNode(CNodeStyle.NOFRAME,CEdgeStyle.ABSENT,Color.BLACK,s,new ArrayList()));
            matchedNodes.add(v);
            treeStack.remove(0);
            symbolsMatched.add(symbolsUnmatched.get(0));
            stringMatched="";
            for(int k=0;k<symbolsMatched.size();k++)
              stringMatched=stringMatched+symbolsMatched.get(k).symValue();
            symbolsUnmatched.remove(0);
            stringUnmatched=stringUnmatched.substring(getSymValue().length());
            nextSym();
            fSym=getSymName();
            if(symbolsUnmatched.size()>1 &&Stack.size()>1){
                action="Match";
            }else if(Stack.size()==1 && symbolsUnmatched.size()==1){
                action="Accept";
            }else if(symbolsUnmatched.size()>1 &&Stack.size()==1){
                action="Error";
            }
            tempStack=new CParseStack();
            tempStack.pushAll(Stack);
            Logs.add(new CParseLog(stringMatched,stringUnmatched,tempStack,action));
         }else {
             action="Error";
             Logs.add(new CParseLog(stringMatched,stringUnmatched,tempStack,action));
         } 
    }
    
    /**
     *
     */
    public void replaceNonterminal(){
        //create a tree
        //modify sStack, get the RHS of S and push onto stack.
        Stack.pop();
        if(RHSSyms.length==1 && RHSSyms[0].equals("~")){ 
            CTreeNode k=treeStack.get(0);
            k.addSon(new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255) ,RHSSyms[0],new ArrayList()));
            treeStack.remove(0);
        }else{
        treeStack.remove(0);
        for(int k=0;k<RHSSyms.length;k++){
           Stack.push(k, RHSSyms[k]); 
            if(fGrammar.getGrammarContext().isTerminal(RHSSyms[k]))
             treeStack.add(k,new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255) ,RHSSyms[k],new ArrayList()));
            else
             treeStack.add(k,new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),RHSSyms[k],new ArrayList()));   
        }
        ArrayList<CTreeNode> leaves=new ArrayList();
        leaves=fTree.Leaves(fTree);
        for (CTreeNode node: leaves){
                if(fGrammar.getGrammarContext().isNonTerminal(node.getLabel())){
                    topNode=node;
                    break;
                }
         }
        //Add sons to the topnode
        for(int k=0;k<RHSSyms.length;k++){
            topNode.addSon(treeStack.get(k));
        }
        } 
       /* for (CTreeNode item: treeStack){
            if(fGrammar.getGrammarContext().isNonTerminal(item.getLabel())){
                topNode=item;
                break;
            }
        }*/
        tempStack=new CParseStack();
        tempStack.pushAll(Stack);   
        Logs.add(new CParseLog(stringMatched,stringUnmatched,tempStack,action));
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
