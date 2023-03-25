/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSE_MultiDot;
import TableGenerators.LL.LLTable.CLLTable;

import TreeLayout.CEdgeStyle;
import TreeLayout.CNodeStyle;
import TreeLayout.CTreeNode;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CLLParser extends CParserExt implements ILLParser {
    private CLLTable LLtable;
    private CParseLog log;
    private CParseStack tempStack;
    private ArrayList<CTreeNode> matchedNodes;
    private CTreeNode fTree;
    private CTreeNode topNode;
        
    private String action;
    private String topSymbol;
    public int row,

    /**
     *
     */
    col=0;

    /**
     *
     */
    public CLLParser() {
        super();
        LLtable=new CLLTable(new String[0][0],new ArrayList(),new ArrayList());
        Stack=new CParseStack();
        tempStack=new CParseStack();
        matchedNodes=new ArrayList();
        fParserResult=new CParserResult();
        symbolsMatched=new ArrayList();
        symbolsUnmatched=new ArrayList();
        stringMatched=new String();
        stringUnmatched=new String();
        fTree=null;
        topNode=null;
        action=new String();
        nonTerminal=null;
        topSymbol=new String();
    }

    /**
     *
     * @return
     */
    public CLLTable getLLtable() {
        return LLtable;
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
     * @param LLtable
     */
    public void setLLtable(CLLTable LLtable) {
        this.LLtable = LLtable;
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
    public String action(){
        return action;
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
        this.matchedNodes = new ArrayList();
        this.matchedNodes.addAll(matchedNodes);
    }

    /**
     *
     * @return
     */
    public CTreeNode getTree() {
        return fTree;
    }

    /**
     *
     * @param fTree
     */
    public void setTree(CTreeNode fTree) {
        this.fTree = null;
        this.fTree=fTree;
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
     * @param topSymbol
     */
    public void setTopSymbol(String topSymbol) {
        this.topSymbol = topSymbol;
    }

    /**
     *
     * @return
     */
    public String getAction(){
        String vAction="";
       if(fGrammar.getGrammarContext().isTerminal(topSymbol)){
          vAction="pop";
       }else if(fGrammar.getGrammarContext().isNonTerminal(topSymbol)){
          vAction=LLtable.getRHS(topSymbol,getSymName());
       }
       return vAction;
    }

    /**
     *
     */
    public void  parse1() {
        start();
        while(!(topSymbol.equals("$")&& getSymValue().equals("$"))){
          step();
          if(action.equals("Error"))
             break;
        }
       
        //if(topSymbol.equals("$") && !getSymValue().equals("$"))
          //  action="Error";
    }
     
    /**
     *
     */
    public void start(){
        reSet();
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
            assert(fGrammar!=null): "CLLParser.start() failed:Grammar property not assigned";
            assert(fSymbolStream!=null): "CLLParser.start() failed:SymbolStream property not assigned";
            assert(vStartExpr instanceof CSE):"CLLPArser.start() failed:nStart Expression should be a syntax expression"; 
           // if(vStartExpr instanceof CSE_Empty){
            //    JOptionPane.showMessageDialog(null, "Please enter a grammar."
            //          , "Grammar Empty",JOptionPane.WARNING_MESSAGE); 
            
          //  }else if (vStartExpr instanceof CSE_MultiDot){
            CSE_MultiDot vDot=(CSE_MultiDot)vStartExpr;
            assert(vDot instanceof CSE_MultiDot): "Grammar should be augumented";
            CSE s=vDot.getList().getElt(0);
            Stack.push(fGrammar.getName(s));
            Stack.push("$");
            fTree=new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),fGrammar.getName(s),new ArrayList());
            treeStack.add(fTree);
            topNode=treeStack.get(0);
            tempStack.pushAll(Stack);
            topSymbol=(String)Stack.peek();
            fSymName=getSymName(); 
            action=getAction();
            log=new CParseLog(stringMatched, stringUnmatched,tempStack,action);
            Logs.add(log);
           // }
            
    }
    
    /**
     *
     */
    public void step(){
            if(topSymbol.equals("$")&& !getSymValue().equals("$")){
                action="Error";
            }else{
            if(fGrammar.getGrammarContext().isTerminal(topSymbol)){
                matchTerminal();
            }else if(fGrammar.getGrammarContext().isNonTerminal(topSymbol)){
                replaceNonterminal();
            }
         }   
   }
    
    /**
     *
     */
    public void matchTerminal(){
        if(getSymName().equals(topSymbol)){
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
            stringUnmatched=stringUnmatched.substring(s.length());
            nextSym();
            fSymName=getSymName();
            tempStack=new CParseStack();
            tempStack.pushAll(Stack);
            topSymbol=(String)Stack.peek();
            System.out.println(topSymbol);
            if(fSymName.equals("Error")){
                action="Error";
                
            }else{
                if(topSymbol.equals("$")&& getSymValue().equals("$"))
                    action="Accept";
                else action=getAction();
            }
            
            
         }else {
              action="Error";
              
         }
        
        Logs.add(new CParseLog(stringMatched,stringUnmatched,tempStack,action));
    }
    
    /**
     *
     */
    public void replaceNonterminal(){
        String vProd=LLtable.getRHS(topSymbol,getSymName());
        if(!vProd.equals("Error")){
            row=LLtable.getNonTerminals().indexOf(topSymbol);
            col=LLtable.getTerminals().indexOf(getSymName());
                     
        String [] r=vProd.split("=");
        String [] RHS=r[1].split("\\.");
        //modify stack, get the RHS of S and push onto stack. 
        Stack.pop();
        if(RHS.length==1 && RHS[0].equals("~")){ 
            CTreeNode k=treeStack.get(0);
            k.addSon(new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255) ,RHS[0],new ArrayList()));
            treeStack.remove(0);
        }else{
            treeStack.remove(0);
            for(int k=0;k<RHS.length;k++){
                Stack.push(k, RHS[k]); 
                if(fGrammar.getGrammarContext().isTerminal(RHS[k]))
                treeStack.add(k,new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.ABSENT,new Color(255,153,255) ,RHS[k],new ArrayList()));
                else
                treeStack.add(k,new CTreeNode(CNodeStyle.RECTANGLE,CEdgeStyle.FAN,new Color(204,153,255),RHS[k],new ArrayList()));   
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
            for(int k=0;k<RHS.length;k++){
                topNode.addSon(treeStack.get(k));
            }
          }
        
        /* for (CTreeNode node: treeStack){
                if(fGrammar.getGrammarContext().isNonTerminal(node.getLabel())){
                    topNode=node;
                    break;
                }
         }*/
           topSymbol=(String)Stack.peek();
           System.out.println(topSymbol);
           if(topSymbol.equals("$")&& getSymValue().equals("$"))
              action="Accept";
           else 
              action=getAction();
           
        }else{
            action="Error";
           
        }
       
        tempStack=new CParseStack();
        tempStack.pushAll(Stack);   
        Logs.add(new CParseLog(stringMatched,stringUnmatched,tempStack,action));
    }
  
    
    @Override
    public CParserResult parseNonTerminal(String aName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CParserResult parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
