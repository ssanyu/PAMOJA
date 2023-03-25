/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.NPDA.NPDALR;

import Commands.Action;
import Commands.ActionHistory;
import Commands.acceptActionNPDA;
import Commands.errorActionNPDA;
import Commands.reduceActionNPDA;
import Commands.shiftActionNPDA;
import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.Parser.CParserComp;
import Components.Lexical.SymbolStream.ISymbolStreamComp;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSE_List;
import GrammarNotions.SyntaxExpr.CSE_MultiDot;
import GrammarNotions.SyntaxExpr.CSE_MultiStick;
import Nodes.CNode;
import Parsers.CNPDALR;
import Parsers.CParseLog;
import Parsers.CParser;
import Parsers.CParserResult;
import TreeLayout.CTreeNode;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class CNPDALRComp extends CParserComp implements INPDALRComp{
    private CNPDALR NPDA;
    private CTreeNode Tree;
    private ArrayList<CTreeNode>TreeStack;
    private ArrayList<CTreeNode> matchedNodes;
    private String action;
    private ArrayList<String> nonTerminals;
    private ActionHistory history;
   
    /**
     *
     */
    public CNPDALRComp() {
       super();
       NPDA=new CNPDALR();
       matchedNodes=new ArrayList();
       Tree=null;
       action="";
       nonTerminals=new ArrayList();
       history=new ActionHistory();
    }
    

    @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("LR(1)_NPDA",5, 10);
   }

    /**
     *
     * @param aLogs
     */
    @Override
    public void setLogs(ArrayList<CParseLog> aLogs) {
        // keep the old value
        ArrayList<CParseLog> oldLogs=Logs;
        // save the new value
        Logs=new ArrayList();
        Logs.addAll(aLogs);
        // fire the property change event, with a property that has changed
        support.firePropertyChange("Logs",oldLogs,Logs);
    }

    /**
     *
     * @return
     */
    public ArrayList<CTreeNode> getTreeStack() {
        return TreeStack;
    }

    /**
     *
     * @param aTreeStack
     */
    public void setTreeStack(ArrayList<CTreeNode> aTreeStack) {
        // keep the old value
        ArrayList<CTreeNode> oldTreeStack=TreeStack;
        // save the new value
        TreeStack=new ArrayList();
        TreeStack.addAll(aTreeStack);
        // TreeStack=aTreeStack;
        support.firePropertyChange("TreeStack",null,TreeStack);
        
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
        setSymbolsUnmatched(NPDA.getSymbolsUnmatched());
        setTreeStack(NPDA.getTreeStack());
        setMatchedNodes(NPDA.getMatchedNodes());
        setLogs(NPDA.getLogs());
    }
    
    /**
     *
     * @return
     */
    public String getAction(){
        action=new String();
        ArrayList<String> optionsList=new ArrayList();
        optionsList.addAll(getOptions());
        if(!optionsList.isEmpty()){
         String [] options=new String[20];
         for(int n=0;n<optionsList.size();n++)
             options[n]=optionsList.get(n);
         if(!NPDA.getSymValue().equals("$")){
         action =(String)JOptionPane.showInputDialog(this, "Choose a shift or \n a production rule to be applied.", "Input", JOptionPane.QUESTION_MESSAGE,
        null,options , options[0]);
         }else{
          action =(String)JOptionPane.showInputDialog(this, "Choose a production rule to be applied.", "Input", JOptionPane.QUESTION_MESSAGE,
        null,options , options[0]);  
         }
        NPDA.setAction(action); 
       }
        return action;
   }
   
    /**
     *
     */
    public void parseStep(){
       action=getAction();
       if(NPDA.getStack().isEmpty()){
            step();
       }else{
            if(NPDA.getSymValue().equals("$")&& NPDA.getStack().size()==1 && NPDA.getStack().peek().equals(getStartSymbol())){
                callEnd();
            }else{
                step();
                
            }
       }
   }
   
    /**
     *
     */
    public void step() {
       if(!action.isEmpty()){
           if(!NPDA.getAction().equals("Shift "+NPDA.getSymValue())){
               reduce();
               
           }else {
             shift();
           }
        }else{
           callEnd();
        }
    
    }
    
    /**
     *
     */
    public void shift(){
       executeAction(new shiftActionNPDA(NPDA));
       setSymbolsUnmatched(NPDA.getSymbolsUnmatched());
       setTreeStack(NPDA.getTreeStack());
       setMatchedNodes(NPDA.getMatchedNodes());
       setLogs(NPDA.getLogs());
       
    }

    /**
     *
     */
    public void reduce(){
       executeAction(new reduceActionNPDA(NPDA)); 
       setSymbolsUnmatched(NPDA.getSymbolsUnmatched());
       setTreeStack(NPDA.getTreeStack());
       setMatchedNodes(NPDA.getMatchedNodes());
       setLogs(NPDA.getLogs());
    }

    /**
     *
     */
    public void callEnd(){
       if(NPDA.getSymValue().equals("$")&& NPDA.getStack().size()==1 && NPDA.getStack().peek().equals(getStartSymbol())){
           accept();
         
       } else{
           error();
          
       }
    }
    
    /**
     *
     */
    public void accept(){
       executeAction(new acceptActionNPDA(NPDA)); 
       setSymbolsUnmatched(NPDA.getSymbolsUnmatched());
       setTreeStack(NPDA.getTreeStack());
       setMatchedNodes(NPDA.getMatchedNodes());
       setLogs(NPDA.getLogs());
    }

    /**
     *
     */
    public void error(){
       executeAction(new errorActionNPDA(NPDA)); 
       setSymbolsUnmatched(NPDA.getSymbolsUnmatched());
       setTreeStack(NPDA.getTreeStack());
       setMatchedNodes(NPDA.getMatchedNodes());
       setLogs(NPDA.getLogs());
    }
    
    @Override
    public CNPDALR getParser() {
        return NPDA;
    }

    /**
     *
     * @param parser
     */
    public void setParser(CNPDALR parser) {
       NPDA=parser;
    }

    @Override
    public void setGrammar(IGrammarComp aGrammar){
         super.setGrammar(aGrammar);
         NPDA.setGrammar(aGrammar.getGrammarStructure());
         nonTerminals=new ArrayList();
         nonTerminals=getNonTerminals();
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
            nonTerminals=new ArrayList();
            nonTerminals=getNonTerminals();
       }else if(source==SymbolStream){
              NPDA.setSymbolStream(SymbolStream.getSymbolStreamStructure());
             //parseText();
       } 
    }

    @Override
    public CParserResult getParserResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CNode getNode() {
        return null;
    }

    /**
     *
     * @return
     */
    public String getStartSymbol(){
        CSE_List vList;
        CSE vstart=Grammar.getGrammarStructure().getStartExpr();
        
        if(vstart instanceof CSE_MultiDot){
            CSE_MultiDot vDot=(CSE_MultiDot)vstart;
            vList=vDot.getList();
            return vList.getTerm(0).toString();
        }else{
            return vstart.toString();
        }
    }
    private ArrayList<String> getNonTerminals(){
       ArrayList<String> nonTermList=new ArrayList();
       String name;
       CSE body;
        
         int vSymbolCount=Grammar.getGrammarStructure().getGrammarContext().getNonTerminalDefs().count();
         for(int i=0;i<vSymbolCount;i++){
             name=Grammar.getGrammarStructure().getGrammarContext().getNonTerminalDefs().getElt(i).getName();
             body=Grammar.getGrammarStructure().getGrammarContext().getNonTerminalDefs().getElt(i).getBody();
             if(body instanceof CSE_MultiStick){
                 CSE_MultiStick m=(CSE_MultiStick)body;
                 for (int j=0;j<m.getList().count();j++){
                   nonTermList.add(name+"="+m.getList().getElt(j).toString());                 
                 }
             }else{          
                  nonTermList.add(name+"="+body.toString());
             }
         }  
       return nonTermList; 
    }
    private ArrayList<String> getOptions(){
        String stackSymbols=new String();
        ArrayList<String> list=new ArrayList();   
         int vSymbolCount=nonTerminals.size();
         for(int i=NPDA.getSymStackOptions().size()-1;i>=0;i--){
             stackSymbols=NPDA.getSymStackOptions().get(i)+stackSymbols;
             for(int j=0;j<vSymbolCount;j++){
                 String body=nonTerminals.get(j).split("=")[1];
                 String [] a=body.split("\\.");
                 body="";
                 for(int k=0;k<a.length;k++)
                  body=body+a[k];
                 if(stackSymbols.equals(body))
                     list.add(nonTerminals.get(j));
             }
         }
         if(!NPDA.getSymValue().equals("$")){
             list.add("Shift "+NPDA.getSymValue());
         }
         return list;
    }

    /**
     *
     */
    @Override
    public void parseText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
 public void setParser(CParser parser) {
         NPDA=(CNPDALR)parser;
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
        //Provide backup copy to the LR parser component
        setStateStack(NPDA.getStack());
        setStringMatched(NPDA.getStringMatched());
        setStringUnmatched(NPDA.getStringUnmatched());
        setSymbolsMatched(NPDA.getSymbolsMatched());
        setSymbolsUnmatched(NPDA.getSymbolsUnmatched());
        setLogs(NPDA.getLogs());
        setTreeStack(NPDA.getTreeStack());
       }
}
