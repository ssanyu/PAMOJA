/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.LLParser;

import Commands.Action;
import Commands.ActionHistory;
import Commands.nonTermAction;
import Commands.termAction;
import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.ParseTables.LLParseTables.ILLParseTableComp;
import Components.Concrete.Parser.CParserCompExt;
import Components.Lexical.SymbolStream.ISymbolStreamComp;
import GrammarNotions.ECFGNodes.CECFGNode;
import GrammarNotions.Grammar.CGrammar;
import Nodes.CNode;
import Parsers.CLLParser;
import Parsers.CParseLog;
import Parsers.CParser;
import Parsers.CParserResult;
import TableGenerators.LL.LLTable.CLLTable;
import TableGenerators.LL.LLTable.GrammartoLLTableGenerator;
import TreeLayout.CTreeNode;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


/**
 * A component holding a LL(1) parser object. Its an observer of Grammar, SymbolStream and LLParseTable components. When it receives a property change in any of these components, it (re)parses the input string according to the current value of the symbol stream. Unlike other parser components, this component does not use a TreeBuilder component to construct the parse tree. Instead it constructs a tree
 * using the CTreeNode class.
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CLLParserComp extends CParserCompExt implements ILLParserComp{
    private CLLParser parser;
    private CTreeNode Tree;
    private ArrayList<CTreeNode> treeStack;
    private ArrayList<CTreeNode> matchedNodes;
    private CLLTable LLParseTableStructure;
    private ILLParseTableComp parseTable;
    private String LLTable[][];
    private ActionHistory history;
     /**
     * A list of alphabet symbols (terminals) used to construct LL(1) tables.
     */
    private ArrayList<String> termAlphabet;
    /**
     * A list of alphabet symbols (terminals and nonterminals) used to construct LL(1) tables.
     */
    private ArrayList<String> nontermAlphabet;
    public int r,

    /**
     *
     */
    c=0;
   
    /**
     *
     */
    public CLLParserComp() {
       super();
       parser=new CLLParser();
       LLTable=new String[0][0];
       LLParseTableStructure=new CLLTable(new String [0][0],new ArrayList(),new ArrayList());
       treeStack=new ArrayList();
       matchedNodes=new ArrayList();
       Tree=null;
       history=new ActionHistory();
       termAlphabet=new ArrayList();
       nontermAlphabet=new ArrayList();
       
    }
   @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("LL(1)Parser",5, 10);
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
    @Override
    public ILLParseTableComp getParseTable() {
        return parseTable;
    }

    public ArrayList<String> getTermAlphabet() {
        return termAlphabet;
    }

    /**
     *
     * @param termAlphabet
     */
    public void setTermAlphabet(ArrayList<String> termAlphabet) {
        this.termAlphabet = termAlphabet;
    }

    public ArrayList<String> getNontermAlphabet() {
        return nontermAlphabet;
    }

    /**
     *
     * @param nontermAlphabet
     */
    public void setNontermAlphabet(ArrayList<String> nontermAlphabet) {
        this.nontermAlphabet = nontermAlphabet;
    }
    
    /**
     *
     * @param aParseTable
     */
    @Override
    public void setParseTable(ILLParseTableComp aParseTable) {
         CLLTable vLLTableStructure;
         //Disconnect Grammar component if connected
         if(Grammar!=null){
             Grammar.removePropertyChangeListener(this);
         }
         Grammar=null;
         //remove old ParseTables
         if(parseTable!=null){
              parseTable.removePropertyChangeListener(this);
         }
         parseTable=aParseTable;
         if(parseTable!=null){
             parseTable.addPropertyChangeListener(this);
             //obtain the SLRTableStructure 
             vLLTableStructure=parseTable.getTableStructure();
         }
         else { //ParseTable object is not connected, return empty structure
             vLLTableStructure=new CLLTable(new String[0][0],new ArrayList(), new ArrayList());
         }
        updateParser(vLLTableStructure);
       
    }

    /**
     *
     * @return
     */
    public CTreeNode getTree() {
        return Tree;
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
     * @return
     */
    public CLLTable getLLParseTableStructure() {
        return LLParseTableStructure;
    }

    /**
     *
     * @param aTableStructure
     */
    public void setLLParseTableStructure(CLLTable aTableStructure) {
        // keep the old value
        CLLTable oldTableStructure=LLParseTableStructure;
        // save the new value
        LLParseTableStructure=aTableStructure;
        parser.setLLtable(LLParseTableStructure);
        // fire the property change event, with a property that has changed
        support.firePropertyChange("LLParseTableStructure",oldTableStructure,LLParseTableStructure);
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
    public ActionHistory getHistory() {
        return history;
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
    @Override
    public void setSymbolStream(ISymbolStreamComp aSymbolStream) {
       super.setSymbolStream(aSymbolStream);
       parser.setSymbolStream(SymbolStream.getSymbolStreamStructure());
       //parseText();
    }
   
    @Override
    public void setGrammar(IGrammarComp aGrammar){
         super.setGrammar(aGrammar);
         parser.setGrammar(aGrammar.getGrammarStructure());
         //TO DO: Remember to disconnect ScanTables
         updateLLTables(Grammar.getGrammarStructure());
         //parseText();
         //fire the property change event, with a property that has changed
         support.firePropertyChange("Grammar",null,Grammar);
    }

    /**
     *
     * @param aGrammarStructure
     */
    public void updateLLTables(CGrammar aGrammarStructure){
           CLLTable vLLTable;
           GrammartoLLTableGenerator vTool=new GrammartoLLTableGenerator();
           termAlphabet=new ArrayList();
           termAlphabet=vTool.grammartoTerminalAlphabet(aGrammarStructure);
           nontermAlphabet=new ArrayList();
           nontermAlphabet=vTool.grammartoNonTerminals(aGrammarStructure);
           vLLTable=vTool.grammartoLLTable(aGrammarStructure);
           setLLParseTableStructure(vLLTable);
            
       }
        
    @Override
    public void propertyChange(PropertyChangeEvent evt) { 
       Object source=evt.getSource();
       if(source==parseTable){
             updateParser(parseTable.getTableStructure());
       }else if(source==Grammar){
              parser.setGrammar(Grammar.getGrammarStructure());
              updateLLTables(Grammar.getGrammarStructure());
              //parseText();
       }else if(source==SymbolStream){
              parser.setSymbolStream(SymbolStream.getSymbolStreamStructure());
             // parseText();
       }
       
    }

    /**
     *
     */
    public void parseText(){
      if(SymbolStream!=null || SymbolStream.getSymbolStreamStructure().symbolCount()>0){
        parser.parse1();
      }
        if(!parser.getLogs().isEmpty()){
           setSymbolsUnmatched(parser.getSymbolsUnmatched());
           setTreeStack(parser.getTreeStack());
           setMatchedNodes(parser.getMatchedNodes());
           setTree(parser.getTree());
           setLogs(parser.getLogs());
        
        }
        
    }

    /**
     *
     */
    public void start(){
        if(SymbolStream!=null ||SymbolStream.getSymbolStreamStructure().symbolCount()>0){
            r=-1;
            c=-1;
            parser.reSet();
            history=new ActionHistory();
            parser.start();
        }
            if(!parser.getLogs().isEmpty()){
                setSymbolsUnmatched(parser.getSymbolsUnmatched());
                setTreeStack(parser.getTreeStack());
                setMatchedNodes(parser.getMatchedNodes());
                setLogs(parser.getLogs());
                setTree(parser.getTree());
            }
        
    }
    
    /**
     *
     */
    @Override
    public void parseStep(){
      if(SymbolStream!=null ||SymbolStream.getSymbolStreamStructure().symbolCount()>0){
       if(parser.getTopSymbol().equals("$")&& !parser.getSymValue().equals("$")){
                
       }else{
         if(Grammar.getGrammarStructure().getGrammarContext().isTerminal(parser.getTopSymbol())){
                    executeAction(new termAction(parser));
         }else if(Grammar.getGrammarStructure().getGrammarContext().isNonTerminal(parser.getTopSymbol())){
                    executeAction(new nonTermAction(parser));
           }
       }
       r=parser.row;
       c=parser.col;
       
      }
       if(!parser.getLogs().isEmpty()){
        setSymbolsUnmatched(parser.getSymbolsUnmatched());
        setTreeStack(parser.getTreeStack());
        setStateStack(parser.getStack());
        setMatchedNodes(parser.getMatchedNodes()); 
        setSymbolsUnmatched(parser.getSymbolsUnmatched());
        setLogs(parser.getLogs());
        setTree(parser.getTree());
        }
      
      }
    
    @Override
    public CLLParser getParser() {
        return parser;
    }

    /**
     *
     * @param parser
     */
    public void setParser(CLLParser parser) {
       this.parser=parser;
    }

    /**
     *
     * @param aLLTableStructure
     */
    public void updateParser( CLLTable aLLTableStructure){
        CLLParser vParser=new CLLParser();
        vParser.setLLtable(aLLTableStructure);
        setParser(vParser);
        //parseText();
    }

    @Override
    public CParserResult getParserResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public CNode getNode() {
        return null;
    }

    @Override
    public void setParser(CParser parser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParseTree(CECFGNode aParseTree) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CECFGNode getParseTree() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 private void executeAction(Action action) {
        if (action.execute()) {
            history.push(action);
        }
        
 }

    /**
     *
     */
    public void undo() {
        if (history.size()>0 ){
        Action action = history.pop();
        if (action != null) {
            action.undo();
        }
        
        //Provide backup copy to the LL parser component
        setStateStack(parser.getStack());
        setStringMatched(parser.getStringMatched());
        setStringUnmatched(parser.getStringUnmatched());
        setSymbolsMatched(parser.getSymbolsMatched());
        setSymbolsUnmatched(parser.getSymbolsUnmatched());
        setLogs(parser.getLogs());
        setTreeStack(parser.getTreeStack());
        setMatchedNodes(parser.getMatchedNodes());
        setTree(parser.getTree());
        }    
    }
    
}
