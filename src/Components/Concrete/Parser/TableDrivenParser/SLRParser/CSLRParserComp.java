/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.TableDrivenParser.SLRParser;


import Commands.Action;
import Commands.ActionHistory;
import Commands.reduceErrorAcceptAction;
import Commands.shiftAction;
import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.ParseTables.LRParseTables.SLRParseTables.ISLRParseTableComp;
import Components.Concrete.Parser.TableDrivenParser.LRParser.CLRParserComp;
import Components.Concrete.TreeBuilder.ITreeBuilderComp;
import Components.Lexical.SymbolStream.ISymbolStreamComp;
import GrammarNotions.ECFGNodes.CECFGNode;
import GrammarNotions.Grammar.CGrammar;
import Nodes.CNode;
import Parsers.CParseLog;
import Parsers.CParser;
import Parsers.CParserResult;
import Parsers.CSLRParser;
import Sets.IntAlphabet;
import TableGenerators.LR.Accept;
import TableGenerators.LR.CLRTable;
import TableGenerators.LR.Move;
import TableGenerators.LR.Reduce;
import TableGenerators.LR.Reject;
import TableGenerators.LR.SLR.SLRAutomata.CSLRDFA;
import TableGenerators.LR.SLR.SLRAutomata.CSLRNFA;
import TableGenerators.LR.SLR.SLRAutomata.CSLRTable;
import TableGenerators.LR.SLR.SLRGenerator.CGrammarToSLRTable;
import TableGenerators.LR.Shift;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * A component holding a SLR parser object. Its an observer of Grammar, SymbolStream and SLRParseTable components. When it receives a property change in any of these components, it (re)parses the input string according to the current value of the symbol stream. In addition it uses a TreeBuilder component to construct the parse tree.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSLRParserComp extends CLRParserComp implements ISLRParserComp {
    /**
     * SLR parser object.
     */
    private CSLRParser Parser;
    /**
     * Textual representation of SLRTable
     */
    private String SLRTableText=""; 
    /**
     * Internal representation of SLRTable
     */
    
    private ActionHistory history;
   
     /**
      * access to Parse table object
      */
    private ISLRParseTableComp ParseTable; 
    public int r,

    /**
     *
     */
    c=0;
    /**
     * Creates an instance of a SLRParser component.
     */
    public CSLRParserComp() {
       super();
       Parser=new CSLRParser();
       TableStructure=new CSLRTable(new CSLRDFA(new IntAlphabet()));
       Logs=new ArrayList();
       history=new ActionHistory();
    }
    
   @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("SLRParser",5, 10);
   }
   
    @Override
   public CSLRParser getParser(){
       return Parser;
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
     * @param aValue
     */
    @Override
    public void setTreeBuilding(boolean aValue){
       super.setTreeBuilding(aValue);
       Parser.setTreeBuilding(TreeBuilding);
       //parseText(); 
    }
    
    @Override
    public void setUseLookAhead(boolean aValue){
        super.setUseLookAhead(aValue);
        Parser.setUseLookAhead(UseLookAhead);
        //parseText(); 
        
    }
   
    @Override
    public void setTreeBuilder(ITreeBuilderComp aTreeBuilder) {
      super.setTreeBuilder(aTreeBuilder);
      Parser.setTreebuilder(TreeBuilder);
      //parseText();
       
    }
  
    @Override
    public void setSymbolStream(ISymbolStreamComp aSymbolStream) {
       super.setSymbolStream(aSymbolStream);
       Parser.setSymbolStream(SymbolStream.getSymbolStreamStructure());
       //parseText();
    }
    public CECFGNode getParseTree(){
        return ParseTree;
    }

    /**
     *
     */
    @Override
    public void start(){
      if(SymbolStream!=null ||SymbolStream.getSymbolStreamStructure().symbolCount()>0){
       Parser.reSet();
       history=new ActionHistory();
       Parser.start(); 
       r=-1;
       c=-1;
       setNodeStack(Parser.getNodeStack());
       setStateStack(Parser.getStack());
       setLogs(Parser.getLogs());
       setSymbolsUnmatched(Parser.getSymbolsUnmatched());
      }
    }
    
    /**
     *
     */
    @Override
    public void parseStep(){
       Move action=Parser.getAction();
       if(action instanceof Shift){ 
           executeAction(new shiftAction(Parser));
                
       }else if(action instanceof Reduce || action instanceof Reject || action instanceof Accept ){
                executeAction(new reduceErrorAcceptAction(Parser)); 
       }
      
        r=Parser.row;
        c=Parser.col-1;
        setNodeStack(Parser.getNodeStack());
        setStateStack(Parser.getStack());
        setLogs(Parser.getLogs());
        setSymbolsUnmatched(Parser.getSymbolsUnmatched());
    }
    /**
     * Parses input string as a symbol stream.
     */
    @Override
    public void parseText(){
       CParserResult vResult= new CParserResult();
       if(SymbolStream!=null && SymbolStream.getSymbolStreamStructure().symbolCount()!=0 && Grammar!=null && ParseTable!=null && !TableStructure.isEmpty()){
           vResult=lrParse();
       } 
       //}else if(SymbolStream!=null && Grammar!=null  ParseTables!=null) && !SLRTableStructure.isEmpty())
       Parser.setParserResult(vResult);
       setNodeStack(Parser.getNodeStack());
       setStateStack(Parser.getStack());
       setLogs(Parser.getLogs());
       setSymbolsUnmatched(Parser.getSymbolsUnmatched());
       setParseTree((CECFGNode)Parser.getParseTree());
    }
     
    /**
     *
     */
    public void parse(){
       CParserResult vResult= new CParserResult();
       if(SymbolStream!=null && SymbolStream.getSymbolStreamStructure().symbolCount()!=0 && Grammar!=null && (ParseTable!=null || !TableStructure.isEmpty())){
           vResult=lrParse();
       } 
       //System.out.println(vResult.getNode());
       //}else if(SymbolStream!=null && Grammar!=null  ParseTables!=null) && !SLRTableStructure.isEmpty())
       Parser.setParserResult(vResult);
       setLogs(Parser.getLogs());
       setParseTree((CECFGNode)Parser.getParseTree());
    }
     /**
     * Returns the string representation of SLR(1) parse tables.
     * @return the string representation of SLR(1) parse tables
     */
    public String getSLRTableText(){
       return SLRTableText;
   }
   
    /**
     * Sets the string representation of SLR(1) parse tables.
     * @param aSLRTableText the string representation of SLR(1) parse tables to set.
     */
    public void setSLRTableText(String aSLRTableText){
      //TO DO  
    }

  

    /**
     * Returns internal representation of SLR(1) parse tables.
     * @return the internal representation of SLR(1) parse tables
     */
    public CSLRTable getTableStructure(){
        return (CSLRTable) TableStructure;
    }

    /**
     * Sets the value of the internal structure of SLR(1) parse tables and notifies observers about <code>SLRTableStructure</code> property changes.
     * @param aSLRTableStructure the value of the internal structure of SLR(1) parse tables.
     */
    @Override
    public void setTableStructure(CLRTable aSLRTableStructure){
        // keep the old value
        CSLRTable oldSLRTableStructure=(CSLRTable)TableStructure;
        // save the new value
        TableStructure=aSLRTableStructure;
        Parser.setSLRTable((CSLRTable) TableStructure);
        // Update textual representation
        //SLRTableText=toText(); // Needs reconsideration
        // fire the property change event, with a property that has changed
        support.firePropertyChange("TableStructure",oldSLRTableStructure,TableStructure);
    }
    
    @Override
    public void setGrammar(IGrammarComp aGrammar){
         super.setGrammar(aGrammar);
         Parser.setGrammar(aGrammar.getGrammarStructure());
         //TO DO: Remember to disconnect ScanTables
         updateSLRTables(Grammar.getGrammarStructure());
         //parseText();
         //fire the property change event, with a property that has changed
         support.firePropertyChange("Grammar",null,Grammar);
    }
    
     
    /**
     * Returns a reference to SLRParseTable component.
     * @return the reference to SLRParseTable component.
     */
    public ISLRParseTableComp getParseTable(){
        return ParseTable;
    }
    
    /**
     * Connects to SLRParseTable component via the specified interface.
     * @param aParseTable
     * @param aParseTable an interface to SLRParseTable component.
     * @pre Parse Tables !=null
     * 
     */
    public void setParseTable(ISLRParseTableComp aParseTable){
         CSLRTable vSLRTableStructure;
         //Disconnect Grammar component if connected
         if(Grammar!=null){
             Grammar.removePropertyChangeListener(this);
         }
         Grammar=null;
         //remove old ParseTables
         if(ParseTable!=null){
              ParseTable.removePropertyChangeListener(this);
         }
         ParseTable=aParseTable;
         if(ParseTable!=null){
             ParseTable.addPropertyChangeListener(this);
             //obtain the SLRTableStructure 
             vSLRTableStructure=(CSLRTable) ParseTable.getTableStructure();
         }
         else { //ParseTable object is not connected, return empty structure
              vSLRTableStructure=new CSLRTable(new CSLRDFA(new IntAlphabet()));
         }
        updateParser(vSLRTableStructure);
       
    }

    /**
     * Update this SLRparser with the specified SLRTable structure.
     * 
     * @param aSLRTableStructure the specified SLRTable structure.
     */
    public void updateParser( CSLRTable aSLRTableStructure){
        CSLRParser vParser=new CSLRParser();
        vParser.setSLRTable(aSLRTableStructure);
        setParser(vParser);
        //parseText();
    }
   
    /**
     *
     * @param aLogs
     */
    public void setLogs(ArrayList<CParseLog> aLogs) {
        // keep the old value
        ArrayList<CParseLog> oldLogs=Logs;
        // save the new value
        Logs=new ArrayList();
        Logs.addAll(aLogs);
        support.firePropertyChange("Logs",oldLogs,Logs);
    }

    @Override
    public void setParseTree(CECFGNode tree){
        // keep the old value
       CECFGNode oldParseTree=ParseTree;
        // save the new value
        ParseTree=null;
        ParseTree=tree;
        //System.out.println(fStateStack.toString() + oldStateStack.toString());
        // fire the property change event, with a property that has changed
        support.firePropertyChange("ParseTree",oldParseTree,ParseTree);
    }

    /**
     *
     * @param aNodeStack
     */
    @Override
    public void setNodeStack(ArrayList<CNode> aNodeStack) {
        // keep the old value
        ArrayList<CNode> oldNodeStack=NodeStack;
        // save the new value
        NodeStack=new ArrayList();
        NodeStack.addAll(aNodeStack);
        support.firePropertyChange("NodeStack",null,NodeStack);
        
    }
           
    public void setParser(CParser parser){
       Parser= (CSLRParser) parser;
       
   }   

    /**
     * Updates SLR tables using the given grammar.
     * 
     * @param aGrammarStructure the grammar used to update SLR tables.
     */
    public void updateSLRTables(CGrammar aGrammarStructure){
            CSLRNFA vNFA;
            CSLRDFA vDFA;
            ArrayList<Move> vSLRTable1[][];
            CSLRTable vSLRTable2;
                       
            //create generator object;
            CGrammarToSLRTable vTool=new CGrammarToSLRTable();
            
            // get alphabet in String form
            strAlphabet=new ArrayList<>();
            strAlphabet=vTool.GrammarToStringAlphabet(aGrammarStructure); 
            
            symAlphabet=new ArrayList<>();
            symAlphabet=vTool.GrammarToStringAlphabet(aGrammarStructure); 
            
             // get alphabet in String form
            symAlphabet=new ArrayList<>();
            symAlphabet=vTool.GrammarToStringAlphabet(aGrammarStructure); 
            // create NFA
            vNFA=vTool.GrammarToSLRNFA(aGrammarStructure);
            
            //create DFA
            vTool.NFAtoDFA(vNFA);
            vDFA=vTool.getDFA();
            
            //create SLRTable1
           // vSLRTable1=new ArrayList[vDFA.stateCount()][vDFA.alphabet().cardinality()];
            vTool.DFAtoSLRTable1(vDFA);
            vSLRTable1=vTool.getSLRTable1();
            fTable1=vSLRTable1;
           
            //create SLRTable2
            vTool.SLRTable1toSLRTable2(vSLRTable1);
            vSLRTable2=vTool.getSLRTable2();
            // create gotos
            Move s;
            for(int vState=0; vState<=vSLRTable2.stateCount()-1;vState++){
              for(int i=1, j=vSLRTable2.alphabet().nextSetBit(0); j>=0;j=vSLRTable2.alphabet().nextSetBit(j+1),i++){
                  if(j>=Grammar.getGrammarStructure().getGrammarContext().firstNonTerminalIndex()){
                     s=vSLRTable2.move(vState, j);
                      if(s instanceof Shift)
                       ((Shift)s).NT=true;  
                  }
                  
              } 
          }
            setTableStructure(vSLRTable2);
            
       }

    /**
     * Resets the parser and parses the input string.
     * @return the result after parsing.
     */
    public CParserResult lrParse(){
        Parser.reSet();
        return Parser.parse();
        
    }
    
    /**
     * Returns an array of SLR parser log entries.
     * 
     * @return An array of log entries.
     */
    public ArrayList<CParseLog> getLogs(){
        return Parser.getLogs();
    }
    
    /**
     * Handle property change events from SLRParseTable, Grammar and SymbolStream components. If the property change is from:
     * <ul>
     * <li> <code>SLRParseTable</code> - update the SLR Parse tables for this parser and re-parse the text. 
     *  </li> 
     * <li> <code>Grammar</code> - update the grammar, regenerate SLR parse tables and re-parse the text.</li>
     * <li> <code>SymbolStream</code> - retrieve the symbolstream and re-parse the text. 
     *  </li> 
     *  </ul>
     * @param evt event object with the new value
     */
      
    @Override
    public void propertyChange(PropertyChangeEvent evt) { 
       Object source=evt.getSource();
       if(source==ParseTable){
             updateParser((CSLRTable) ParseTable.getTableStructure());
       }else if(source==Grammar){
              Parser.setGrammar(Grammar.getGrammarStructure());
              updateSLRTables(Grammar.getGrammarStructure());
              //parseText();
       }else if(source==SymbolStream){
              Parser.setSymbolStream(SymbolStream.getSymbolStreamStructure());
             // parseText();
       }
       
    }

    /**
     *
     * @return
     */
    @Override
    public CNode getNode() {
       return Parser.getParserResult().getNode();
    }

    @Override
    public CParserResult getParserResult() {
        return Parser.getParserResult();
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
        
        //Provide backup copy to the LR parser component
        setAction(Parser.getAction());
        setCurrentState(Parser.getCurrentState());
        setStateStack(Parser.getStack());
        setStringMatched(Parser.getStringMatched());
        setStringUnmatched(Parser.getStringUnmatched());
        setSymbolsMatched(Parser.getSymbolsMatched());
        setSymbolsUnmatched(Parser.getSymbolsUnmatched());
        setLogs(Parser.getLogs());
        setNodeStack(Parser.getNodeStack());
        }  
    }
}

