/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.NPDA.NPDALL;

import Commands.Action;
import Commands.ActionHistory;
import Commands.nonterminalAction;
import Commands.terminalAction;
import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.Parser.CParserComp;
import Components.Lexical.SymbolStream.ISymbolStreamComp;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSE_MultiStick;
import Nodes.CNode;
import Parsers.CNPDALL;
import Parsers.CParseLog;
import Parsers.CParser;
import Parsers.CParserResult;
import SymbolStream.Symbol;
import TreeLayout.CTreeNode;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class CNPDALLComp extends CParserComp implements INPDALLComp{
    private CNPDALL NPDA;
    private CTreeNode Tree;
    private ArrayList<Symbol> unMatchedSymbols;
    private ArrayList<CTreeNode> treeStack;
    private ArrayList<CTreeNode> matchedNodes;
    private ActionHistory history;
    
    /**
     *
     */
    public CNPDALLComp() {
       super();
       NPDA=new CNPDALL();
       treeStack=new ArrayList();
       matchedNodes=new ArrayList();
       Tree=null;
       history=new ActionHistory();
    }
    

    @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("NPDALL",5, 10);
   }
   
    /**
     *
     * @param Tree
     */
    public void setTree(CTreeNode Tree) {
        CTreeNode oldTree=null;
        // keep the old value
        if(this.Tree!=null)
           oldTree=new CTreeNode(this.Tree);
        // save the new value
        //this.Tree=null;
        this.Tree=Tree;
        // fire the property change event, with a property that has changed
        support.firePropertyChange("Tree",oldTree,this.Tree);
    }

    /**
     *
     * @param aLog
     */
    public void setLogs(ArrayList<CParseLog> aLog) {
        // keep the old value
        ArrayList<CParseLog> oldLog=Logs;
        // save the new value
        Logs=new ArrayList();
        Logs.addAll(aLog);
        // fire the property change event, with a property that has changed
        support.firePropertyChange("Logs",oldLog,Logs);
    }

    /**
     *
     * @return
     */
    public ArrayList<CTreeNode> getTreeStack() {
        return treeStack;
    }

    /**
     *
     * @param treeStack
     */
    public void setTreeStack(ArrayList<CTreeNode> treeStack) {
        this.treeStack = treeStack;
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
     */
    public void start(){
        NPDA.reSet();
        history=new ActionHistory();
        NPDA.start();
        setUnMatchedSymbols(NPDA.getSymbolsUnmatched());
        setTreeStack(NPDA.getTreeStack());
        setMatchedNodes(NPDA.getMatchedNodes());
        setLogs(NPDA.getLogs());
        setTree(NPDA.getfTree());
    }
    
    /**
     *
     */
    public void parseStep(){
            String s="";
            int l;
            NPDA.setTopSymbol((String)NPDA.getStack().peek());
            if(Grammar.getGrammarStructure().getGrammarContext().isTerminal(NPDA.getTopSymbol())){
                matchTerminal();
            }else if(Grammar.getGrammarStructure().getGrammarContext().isNonTerminal(NPDA.getTopSymbol())){
                String [] options=getProductions(NPDA.getTopSymbol());
                String result =(String)JOptionPane.showInputDialog(this, "Choose a production", "Input", JOptionPane.QUESTION_MESSAGE,
        null,options , options[0]);
               if(result!=null){
                String [] r=result.split("=");
                String [] RHS=r[1].split("\\.");
                NPDA.setAction(result);
                NPDA.setRHSSyms(RHS);
                replaceNonterminal();
               }
            }
    }
    
    /**
     *
     */
    public void replaceNonterminal(){
       executeAction(new nonterminalAction(NPDA));
       setUnMatchedSymbols(NPDA.getSymbolsUnmatched());
       setTreeStack(NPDA.getTreeStack());
       setMatchedNodes(NPDA.getMatchedNodes());
       setLogs(NPDA.getLogs());
       setTree(NPDA.getfTree());
       
    }

    /**
     *
     */
    public void matchTerminal(){
       executeAction(new terminalAction(NPDA));
           
       setUnMatchedSymbols(NPDA.getSymbolsUnmatched());
       setTreeStack(NPDA.getTreeStack());
       setMatchedNodes(NPDA.getMatchedNodes());
       setLogs(NPDA.getLogs());
       setTree(NPDA.getfTree());
       
    }
    @Override
    public CNPDALL getParser() {
        return NPDA;
    }

    /**
     *
     * @param parser
     */
    public void setParser(CNPDALL parser) {
       NPDA=parser;
    }

    @Override
    public void setGrammar(IGrammarComp aGrammar){
         super.setGrammar(aGrammar);
         NPDA.setGrammar(aGrammar.getGrammarStructure());
         //fire the property change event, with a property that has changed
         support.firePropertyChange("Grammar",null,Grammar);
    }
    
    @Override
    public void setSymbolStream(ISymbolStreamComp aSymbolStream) {
       super.setSymbolStream(aSymbolStream);
       NPDA.setSymbolStream(SymbolStream.getSymbolStreamStructure());
       //parseText();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
      Object source=evt.getSource();
       if(source==Grammar){
              NPDA.setGrammar(Grammar.getGrammarStructure());
             
       }else if(source==SymbolStream){
              NPDA.setSymbolStream(SymbolStream.getSymbolStreamStructure());
             // parseText();
       } 
    }

    @Override
    public CParserResult getParserResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    public ArrayList<Symbol> getUnMatchedSymbols() {
        return unMatchedSymbols;
    }

    /**
     *
     * @param unMatchedSymbols
     */
    public void setUnMatchedSymbols(ArrayList<Symbol> unMatchedSymbols) {
        this.unMatchedSymbols = unMatchedSymbols;
    }

    @Override
    public CNode getNode() {
        return null;
    }

    @Override
    public void setParser(CParser parser) {
         NPDA=(CNPDALL)parser;
    }

    private String[] getProductions(String aName){
        String [] nonTermList=new String[20];
        CSE e=Grammar.getGrammarStructure().getGrammarContext().getNonterminalBody(Grammar.getGrammarStructure().getGrammarContext().indexOfNonterminal(aName));
        if(e instanceof CSE_MultiStick){
                 CSE_MultiStick m=(CSE_MultiStick)e;
                 for (int j=0;j<m.getList().count();j++){
                   nonTermList[j]=aName+"="+m.getList().getElt(j);
                 }
             }else{          
                  nonTermList[0]=aName+"="+e.toString();
             }
        return nonTermList;
    }

    /**
     *
     */
    @Override
    public void parseText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void executeAction(Action action) {
        if (action.execute()) {
            history.push(action);
        }
        
 }

    /**
     *
     * @return
     */
    public ActionHistory getHistory() {
        return history;
    }

    /**
     *
     */
    public void undo() {
        if (history.isEmpty() ) return;
        Action action = history.pop();
        if (action != null) {
            action.undo();
        }
        
        //Provide backup copy to the LL parser component
        setStateStack(NPDA.getStack());
        setStringMatched(NPDA.getStringMatched());
        setStringUnmatched(NPDA.getStringUnmatched());
        setSymbolsMatched(NPDA.getSymbolsMatched());
        setSymbolsUnmatched(NPDA.getSymbolsUnmatched());
        setLogs(NPDA.getLogs());
        setTreeStack(NPDA.getTreeStack());
        setMatchedNodes(NPDA.getMatchedNodes());
        setTree(NPDA.getfTree());
        
        
    }
    
}
