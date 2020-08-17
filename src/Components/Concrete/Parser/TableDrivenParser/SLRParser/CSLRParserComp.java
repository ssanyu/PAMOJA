/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.TableDrivenParser.SLRParser;


import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.ParseTables.SLRParseTables.ISLRParseTableComp;
import Components.Concrete.Parser.TableDrivenParser.CLRParserComp;
import Components.Concrete.TreeBuilder.ITreeBuilderComp;
import Components.Lexical.SymbolStream.ISymbolStreamComp;
import GrammarNotions.ECFGNodes.CECFGNode;
import GrammarNotions.Grammar.CGrammar;
import Nodes.CNode;
import Parsers.CParseLogEntry;
import Parsers.CParser;
import Parsers.CParserResult;
import Parsers.CSLRParser;
import SLR.SLRAutomata.CSLRDFA;
import SLR.SLRAutomata.CSLRNFA;
import SLR.SLRAutomata.CSLRTable;
import SLR.SLRAutomata.Move;
import SLR.SLRGenerator.CGrammarToSLRTable;
import Sets.IntAlphabet;
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
    private CSLRTable   SLRTableStructure;  
    private ArrayList<String> strAlphabet;
    private ArrayList<Move> fSLRTable1[][]=null;
     /**
      * access to Parse table object
      */
    private ISLRParseTableComp ParseTables; 
    
    /**
     * Creates an instance of a SLRParser component.
     */
    public CSLRParserComp() {
       super();
       Parser=new CSLRParser();
       SLRTableStructure=new CSLRTable(new CSLRDFA(new IntAlphabet()));
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
     * @param aValue
     */
    @Override
    public void setTreeBuilding(boolean aValue){
       super.setTreeBuilding(aValue);
       Parser.setTreeBuilding(TreeBuilding);
       parseText(); 
    }
    
    @Override
    public void setUseLookAhead(boolean aValue){
        super.setUseLookAhead(aValue);
        Parser.setUseLookAhead(UseLookAhead);
        parseText(); 
        
    }
   
    @Override
    public void setTreeBuilder(ITreeBuilderComp aTreeBuilder) {
      super.setTreeBuilder(aTreeBuilder);
      Parser.setTreebuilder(TreeBuilder);
      parseText();
       
    }
  
    @Override
    public void setSymbolStream(ISymbolStreamComp aSymbolStream) {
       super.setSymbolStream(aSymbolStream);
       Parser.setSymbolStream(SymbolStream.getSymbolStreamStructure());
       parseText();
    }

    /**
     * Parses input string as a symbol stream.
     */
    public void parseText(){
       CParserResult vResult= new CParserResult();
       if(SymbolStream!=null && SymbolStream.getSymbolStreamStructure().symbolCount()!=0 && Grammar!=null && ParseTables!=null && !SLRTableStructure.isEmpty())
           vResult=lrParse();
       //}else if(SymbolStream!=null && Grammar!=null  ParseTables!=null) && !SLRTableStructure.isEmpty())
       Parser.setParserResult(vResult);
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
     * Returns SLR(1) parse-tables.
     * @return SLR(1) parse-tables
     */
    public ArrayList<Move>[][] getSLRTable1(){
        return fSLRTable1;
    }

    /**
     * Returns internal representation of SLR(1) parse tables.
     * @return the internal representation of SLR(1) parse tables
     */
    public CSLRTable getSLRTableStructure(){
        return SLRTableStructure;
    }

    /**
     * Sets the value of the internal structure of SLR(1) parse tables and notifies observers about <code>SLRTableStructure</code> property changes.
     * @param aSLRTableStructure the value of the internal structure of SLR(1) parse tables.
     */
    public void setSLRTableStructure(CSLRTable aSLRTableStructure){
        // keep the old value
        CSLRTable oldSLRTableStructure=SLRTableStructure;
        // save the new value
        SLRTableStructure=aSLRTableStructure;
        Parser.setSLRTable(SLRTableStructure);
        // Update textual representation
        //SLRTableText=toText(); // Needs reconsideration
        // fire the property change event, with a property that has changed
        support.firePropertyChange("SLRTableStructure",oldSLRTableStructure,SLRTableStructure);
    }
    
    @Override
    public void setGrammar(IGrammarComp aGrammar){
         super.setGrammar(aGrammar);
         Parser.setGrammar(aGrammar.getGrammarStructure());
         //TO DO: Remember to disconnect ScanTables
         updateSLRTables(Grammar.getGrammarStructure());
         parseText();
         //fire the property change event, with a property that has changed
         support.firePropertyChange("Grammar",null,Grammar);
    }
    
     /**
     *  Return the Grammar alphabet (terminals and Nonterminal names)
     *  @return Array of grammar alphabet.
     */
    public ArrayList<String> getStringAlphabet(){
        return strAlphabet;
    }
    
    /**
     * Returns a reference to SLRParseTable component.
     * @return the reference to SLRParseTable component.
     */
    public ISLRParseTableComp getParseTables(){
        return ParseTables;
    }
    
    /**
     * Connects to SLRParseTable component via the specified interface.
     * @param aParseTables an interface to SLRParseTable component.
     * @pre Parse Tables !=null
     * 
     */
    public void setParseTables(ISLRParseTableComp aParseTables){
         CSLRTable vSLRTableStructure;
         //Disconnect Grammar component if connected
         if(Grammar!=null){
             Grammar.removePropertyChangeListener(this);
         }
         Grammar=null;
         //remove old ParseTables
         if(ParseTables!=null){
              ParseTables.removePropertyChangeListener(this);
         }
         ParseTables=aParseTables;
         if(ParseTables!=null){
             ParseTables.addPropertyChangeListener(this);
             //obtain the SLRTableStructure 
              vSLRTableStructure=ParseTables.getSLRTableStructure();
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
        parseText();
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
            
            // create NFA
            vNFA=vTool.GrammarToSLRNFA(aGrammarStructure);
            
            //create DFA
            vTool.NFAtoDFA(vNFA);
            vDFA=vTool.getDFA();
            
            //create SLRTable1
           // vSLRTable1=new ArrayList[vDFA.stateCount()][vDFA.alphabet().cardinality()];
            vTool.DFAtoSLRTable1(vDFA);
            vSLRTable1=vTool.getSLRTable1();
            fSLRTable1=vSLRTable1;
           
            //create SLRTable2
            vTool.SLRTable1toSLRTable2(vSLRTable1);
            vSLRTable2=vTool.getSLRTable2();
            setSLRTableStructure(vSLRTable2);
            
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
    public ArrayList<CParseLogEntry> getLogEntries(){
        return Parser.getLogEntries();
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
       if(source==ParseTables){
           updateParser(ParseTables.getSLRTableStructure());
       }else if(source==Grammar){
              Parser.setGrammar(Grammar.getGrammarStructure());
              updateSLRTables(Grammar.getGrammarStructure());
       }else if(source==SymbolStream){
              Parser.setSymbolStream(SymbolStream.getSymbolStreamStructure());
              parseText();
           
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

    /**
     *
     * @return
     */
    @Override
    public CECFGNode getParseTree() {
        return (CECFGNode)Parser.getParseTree();
        
    }

    /**
     *
     * @param aParseTree
     */
    @Override
    public void setParseTree(CECFGNode aParseTree) {
         // keep the old value
      CECFGNode  oldParseTree=(CECFGNode)Parser.getParseTree();
       // get the new value
       Parser.setParseTree(aParseTree);
       // fire the property change event, with a property that has changed
       support.firePropertyChange("ParseTree",oldParseTree,aParseTree);
    }
    
}
