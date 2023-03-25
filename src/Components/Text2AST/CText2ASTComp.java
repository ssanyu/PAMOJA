/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Text2AST;

import Components.Abstract.Abstraction.CAbstractionComp;
import Components.CPAMOJAComponent;
import Components.Concrete.ParseTree.CParseTreeComp;
import Components.Concrete.Parser.InterpretingParser.Deterministic.CDeterministicParserComp;
import Components.Concrete.TreeBuilder.CECFGTreeBuilderComp;
import Components.INodeObject;
import Components.Lexical.ScanTables.CScanTableComp;
import Components.Lexical.Scanners.TableDriven.CDFAScannerComp;
import Components.Specifications.Language.ILanguageComp;
import Nodes.CNode;
import Nodes.CNodeFactory;
import Parsers.CParserResult;
import SymbolStream.CPosition;
import SymbolStream.CSymbolStream;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A composite component which scans and parses program text
 * to ensure that its lexically and syntactically well-formed and if successful
 * its mapped to an Abstract Syntax Tree (AST), other wise it generates
 * appropriate error messages. Text2AST component is composed of several
 * subcomponents: DFAScanner, ScanTables, DeterministicParser, ECFGTreeBuilder and Abstractor.
 * <p>
 * Text2AST observes a change in the lexical and concrete syntax of a language.
 * A change in a lexical syntax syntax causes a ScanTables subcomponent
 * to regenerate its DFA scan tables whereas a change in concrete
 * syntax makes a DeterministicParser component to reference an up to
 * date concrete syntax. It uses a node factory , a set of Java AST classes
 * generated from signature specifications of a language, to provide nodes
 * used in the construction of an AST.
 * 
 * @see Components.Lexical.Scanners.TableDriven.CDFAScannerComp
 * @see Components.Lexical.ScanTables.CScanTableComp
 * @see Components.Concrete.Parser.InterpretingParser.Deterministic.CDeterministicParserComp
 * @see Components.Concrete.TreeBuilder.CECFGTreeBuilderComp
 * @see Components.Abstract.Abstraction.CAbstractionComp
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CText2ASTComp extends CPAMOJAComponent implements IText2ASTComp,INodeObject,PropertyChangeListener {
    private String text;
    private String parserMsg;
    private CSymbolStream symbolStream;
    private CNode parseTree;
    private CNode ast;
    private String nonTerminal;
    
    private CDFAScannerComp scanner;
    private CScanTableComp scanTables;
    private CDeterministicParserComp parser;
    private CECFGTreeBuilderComp treeBuilder;
    private CParseTreeComp parseTreeComp;
    private CAbstractionComp abstractor;
   
    private CNodeFactory nodeFactory;
    private ILanguageComp language;
    
    /**
     * Creates a new Text2AST component.
     */
    public CText2ASTComp() {
        super();
        text=new String();
        parserMsg=new String();
        nonTerminal=new String();
        symbolStream=new CSymbolStream();
        parseTree=null;
        ast=null;
        scanner=new CDFAScannerComp();
        scanTables=new CScanTableComp();
        parser=new CDeterministicParserComp();
        treeBuilder=new CECFGTreeBuilderComp();
        parseTreeComp=new CParseTreeComp();
        abstractor=new CAbstractionComp();
        nodeFactory=null;
        
        scanner.setScanTables(scanTables);
        setTreeBuilderProperties();
        parser.setTreeBuilder(treeBuilder);
        parser.setTreeBuilding(true);
        parser.setParseWithNonTerminal(true);
        parseTreeComp.setParser(parser);
        abstractor.setParseTree(parseTreeComp);
    }
    
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("Text2AST",10, 10);
    } 

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    @Override
    public void setText(String text) {
        this.text = text;
        generateAST();
    }

    public String getParserMsg() {
        return parserMsg;
    }

    /**
     *
     * @param parserMsg
     */
    public void setParserMsg(String parserMsg) {
        this.parserMsg = parserMsg;
    }
    
    /**
     *
     * @return
     */
    public CNode getParseTree() {
        return parseTree;
    }

    /**
     *
     * @param parseTree
     */
    public void setParseTree(CNode parseTree) {
        this.parseTree = parseTree;
    }

    /**
     *
     * @return
     */
    @Override
    public CNode getAst() {
        return ast;
    }

    /**
     *
     * @param ast
     */
    public void setAst(CNode ast) {
         
        // keep the old value
        CNode oldAst=this.ast;
        // save the new value
        this.ast=ast;
        // fire the property change event, with a property that has changed
        
        support.firePropertyChange("ast",oldAst,this.ast);
        
    }

    /**
     *
     * @return
     */
    public String getNonTerminal() {
        return nonTerminal;
    }

    /**
     *
     * @param nonTerminal
     */
    public void setNonTerminal(String nonTerminal) {
        this.nonTerminal = nonTerminal;
    }
    
    /**
     *
     * @return
     */
    public ILanguageComp getLanguage() {
        return language;
    }
    
    /**
     *
     * @param language
     */
    @Override
    public void setLanguage(ILanguageComp language){
       if(this.language!=null){
              this.language.removePropertyChangeListener(this);
       }
       this.language=language;
       if(this.language!=null){
              this.language.addPropertyChangeListener(this);
             
       } else {
            this.language.removePropertyChangeListener(this);
       }
       
       updateLanguageEnabledComponents(this.language);
    }

    /**
     *
     * @return
     */
    @Override
    public CNodeFactory getNodeFactory() {
        return nodeFactory;
    }
    
    /**
     *
     * @param nodeFactory
     */
    @Override
    public void setNodeFactory(CNodeFactory nodeFactory) {
        this.nodeFactory = nodeFactory;
        abstractor.setNodeFactory(this.nodeFactory);
        setAst(abstractor.getAST());
    }
       
    /**
     *
     * @return
     */
    @Override
    public CNode getNode() {
        return ast;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       Object source=evt.getSource();
        if(source==language){
              updateLanguageEnabledComponents(language);
        }
    }
    private void updateLanguageEnabledComponents(ILanguageComp language){
        scanTables.setLanguage(language);
        parser.setGrammar(language.getGrammarComp());
        generateAST();
    }
   
    private void scanText(){
        if(scanTables.getDFATableStructure()!=null && !text.isEmpty()) {
            scanner.createSymbolStream(text);
            if(scanner.getSymbolStream()!=null)
                symbolStream=scanner.getSymbolStream();
        }
    }

   private void  buildAST(){
      CParserResult r=new CParserResult();
      if(symbolStream!=null && symbolStream.symbolCount()!=0 && language!=null){
            parser.getParser().setSymbolStream(symbolStream);
            parser.setGrammar(language.getGrammarComp());
            parser.setNonTerminal(nonTerminal);  
            if(parser.isParseWithNonTerminal()&& !nonTerminal.isEmpty()){
                r= parser.getParser().parseNonTerminal(parser.getNonTerminal());
            }else{
                r=parser.getParser().parse();
            }
            if(r.isSuccess() && r.getNode()!=null){
               parser.getParser().setParserResult(r);
               parseTreeComp.setParser(parser); 
               parseTree=parseTreeComp.getParseTreeStructure();
               setAst(abstractor.getAST());
                if(ast!=null){
                    setParserMsg("Parsing completed successfully ");
                }
            }else{
                CPosition v=parser.getParser().getSymPos();
                setParserMsg("Error: unexpected or cannot find symbol: "+ parser.getParser().getSymName()+" ["+parser.getParser().getSymValue()+"] at line:" +v.Line);//+", position:"+ cDeterministicParserComp1.getParser().getSymbolStream().getSymStart());
            }
      }
      }
   
   
    private void setTreeBuilderProperties(){
       treeBuilder.setMultiStick(true);
       treeBuilder.setNoDataTerminal(true);
       treeBuilder.setNonTerminal(true);
    }

    /**
     *
     * @return
     */
    @Override
    public CSymbolStream getSymbolStream() {
        return symbolStream;
    }

    /**
     *
     * @param symbolStream
     */
    public void setSymbolStream(CSymbolStream symbolStream) {
        this.symbolStream = symbolStream;
    }

    private void generateAST(){
        ast=null;
        scanText();
        buildAST();
    }
    
}
