/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.InterpretingParser.Deterministic;


import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.Parser.InterpretingParser.CInterpretingParserComp;
import Components.Concrete.TreeBuilder.ITreeBuilderComp;
import Components.Lexical.SymbolStream.ISymbolStreamComp;
import GrammarNotions.ECFGNodes.CECFGNode;
import Nodes.CNode;
import Parsers.CDeterministicParser;
import Parsers.CParser;
import Parsers.CParserResult;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;

/**
 * A component holding an interpreting deterministic parser object. It observes a Grammar and a SymbolStream components and (re)parses the input string basing on the current symbol stream value.  
 * In addition it uses a TreeBuilder component which is responsible for constructing the different kinds of parse tree nodes.
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CDeterministicParserComp extends CInterpretingParserComp{
    /**
     * The deterministic parser object.
     * 
     * @see Parsers.CDeterministicParser
     */
    private CDeterministicParser Parser;
    
    /**
     * Creates an instance of a DeterministicParser component.
     */
    public CDeterministicParserComp(){
        super();
        Parser=new CDeterministicParser();
    }

    @Override
    public CDeterministicParser getParser(){
       return Parser;
    }
    
    @Override
    public void setParser(CParser parser){
       Parser=(CDeterministicParser) parser;
   } 
    
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("DetParser",5,10);
    }
     /**
     * Handle property change events from SymbolStream and Grammar Components. If the property change is from:
     * <ul>
     * <li> <code>SymbolStream</code> - retrieve the symbolstream and re-parse the text. 
     *  </li> 
     * <li> <code>Grammar</code> - retrieve the grammar and re-parse the text.</li>
     * 
     * </ul>
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String property=evt.getPropertyName();
        switch (property) {
            case "SymbolStreamText":
            case "SymbolStreamStructure":
                Parser.setSymbolStream(SymbolStream.getSymbolStreamStructure());
                break;
            case "GrammarStructure":
            case "GrammarText":
                Parser.setGrammar(Grammar.getGrammarStructure());
                break;
        }
        parseText();
    }

   
    @Override
    public CNode getNode() {
        return getParseTree();
    }

    /**
     * Determines if the parser is set to start parsing at any NonTerminal.
     * @return <code>true</code> if the parser is set to start parsing at any NonTerminal, otherwise <code>false</code>.
     */
    public boolean isParseWithNonTerminal() {
        return parseWithNonTerminal;
    }

    /**
     * Sets the parseWithNonTerminal mode of this parser to the specified value.
     * @param parseWithNonTerminal if parseWithNonTerminal mode is allowed for this parser then <code>true</code>, else <code>false</code>.
     */
    public void setParseWithNonTerminal(boolean parseWithNonTerminal) {
        this.parseWithNonTerminal = parseWithNonTerminal;
        Parser.setParseWithNonTerminal(parseWithNonTerminal);
        parseText(); 
    }

    /**
     * Returns the name of a NonTerminal to be used as the start-expression for parsing.
     * @return the name of a NonTerminal to be used as the start-expression.
     */
    public String getNonTerminal() {
        return nonTerminal;
    }

    /**
     * Sets the the given name of a NonTerminal to be used as the start-expression.
     * @param nonTerminal the name of a NonTerminal.
     */
    public void setNonTerminal(String nonTerminal) {
        this.nonTerminal = nonTerminal;
        Parser.setNonTerminal(nonTerminal);
        parseText(); 
    }
    
    /**
     * Parses input string as a symbol stream.
     */
    public void parseText(){
       CParserResult vResult= new CParserResult();
       if(SymbolStream!=null && SymbolStream.getSymbolStreamStructure().symbolCount()!=0 && Grammar!=null)
           if(isParseWithNonTerminal()){
               
             vResult=Parser.parseNonTerminal(nonTerminal);
           }else{
               vResult=Parser.parse();
           }
       
       Parser.setParserResult(vResult);
       Parser.setParseTree((CECFGNode)vResult.getNode());
       setParseTree((CECFGNode)vResult.getNode());
       
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
    public ISymbolStreamComp getSymbolStream() {
        return SymbolStream;
    }
    @Override
    public void setSymbolStream(ISymbolStreamComp aSymbolStream) {
       super.setSymbolStream(aSymbolStream);
       Parser.setSymbolStream(SymbolStream.getSymbolStreamStructure());
       parseText();
    }
    
    @Override
    public void setGrammar(IGrammarComp aGrammar) {
      super.setGrammar(aGrammar);
      Parser.setGrammar(Grammar.getGrammarStructure());
      parseText();
    }

    /**
     * Returns a parse tree.
     * @return
     */
    @Override
    public CECFGNode getParseTree() {
        return Parser.getParseTree();
    }

    /**
     * Sets the specified parse tree and notifies its observers.
     * 
     * @param aParseTree the parse tree to set.
     */
    @Override
    public void setParseTree(CECFGNode aParseTree) {
       //Keep the old value
       CECFGNode oldParseTree=ParseTree;
       //Get the new one 
       ParseTree=aParseTree;
       Parser.setParseTree(ParseTree);
      // System.out.println("hjdfkfd" + ParseTree);
       support.firePropertyChange("ParseTree",oldParseTree,ParseTree);
    }

    @Override
    public CParserResult getParserResult() {
        return Parser.getParserResult();
    }
    
    
}
