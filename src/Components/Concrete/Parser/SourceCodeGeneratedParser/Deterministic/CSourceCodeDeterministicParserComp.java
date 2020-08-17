/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.SourceCodeGeneratedParser.Deterministic;

import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.Parser.SourceCodeGeneratedParser.CSourceCodeGeneratedParserComp;
import GrammarNotions.ECFGNodes.CECFGNode;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.SyntaxExpr.CSE_Empty;
import Java.CRecDescentParserGenerator;
import Java.CompilationUnit_List;
import Java.JCompilationUnit;
import Java.JFlattener;
import Java.JMethodDec;
import Nodes.CNode;
import Parsers.CParser;
import Parsers.CParserResult;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.util.LinkedHashMap;

/**
 * A component generating the source-code of a recursive-descent parser from a given grammar.
 *
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */

public class CSourceCodeDeterministicParserComp extends CSourceCodeGeneratedParserComp implements ISourceCodeDeterministicParserComp{
    /**
     * The textual representation of the source-code for a SourceCodeDeterministicParser.
     */
    private String ParserSourceText=" ";
    /**
     * The structural representation of the source-code for a SourceCodeDeterministicParser.
     */
    private JCompilationUnit ParserSourceStructure;
    private String ParserName=" ";
    
    private JFlattener javaFlatener;
    private CRecDescentParserGenerator fGen;
    private CompilationUnit_List CompUnitList;
    private LinkedHashMap<String,JMethodDec> prodList;
    
    /**
     * Creates an instance of a SourceCodeDeterministicParser component.
     */
    public CSourceCodeDeterministicParserComp() {
       super();
       javaFlatener = new JFlattener();
       fGen = new CRecDescentParserGenerator();
       prodList=new LinkedHashMap<>();
    }
    
    @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("RecursiveParser",5, 10);
   }

    /**
     * Returns a textual representation of the source-code for a SourceCodeDeterministicParser
     * @return a textual representation of the source-code for a SourceCodeDeterministicParser
     */
    @Override
    public String getParserSourceText() {
        return ParserSourceText;
    }
    /**
     * Sets the given textual representation of the source-code to this SourceCodeDeterministicParser
     * @param ParserSourceText given textual representation of the source-code 
     */
    @Override
    public void setParserSourceText(String ParserSourceText) {
        this.ParserSourceText = ParserSourceText;
    }

    /**
     * Return a list of (production-rule,Method-Declaration) pair for the grammar
     * @return list of (production-rule,Method-Declaration) pair 
     */
    @Override
    public LinkedHashMap<String, JMethodDec> getProdList() {
        return prodList;
    }

    /**
     * Set the given list of (production-rule,Method-Declaration) pair for the grammar
     * @param prodList a list of (production-rule,Method-Declaration) pair 
     */
    public void setProdList(LinkedHashMap<String, JMethodDec> prodList) {
        this.prodList = prodList;
    }
    
    /**
     *
     * @param aValue
     */
    @Override
   public void setTreeBuilding(boolean aValue){
       super.setTreeBuilding(aValue);
       fGen.setTreeBuilding(aValue);
       updateParserSource(Grammar.getGrammarStructure());
    }

    
    /**
     * Returns a structural representation of the source-code for a SourceCodeDeterministicParser
     * @return a structural representation of the source-code for a SourceCodeDeterministicParser
     */
    @Override
   public JCompilationUnit getParserSourceStructure(){
        return ParserSourceStructure;
    }

    /**
     * Sets the given structural representation of the source-code to this SourceCodeDeterministicParser
     * @param aParserSourceStructure given structural representation of the source-code 
     */
    @Override
   public void setParserSourceStructure(JCompilationUnit aParserSourceStructure){
        // keep the old value
        JCompilationUnit oldParserSourceStructure=ParserSourceStructure;
        // save the new value
        ParserSourceStructure=aParserSourceStructure;
        
        // Update textual representation
        ParserSourceText=toText(); 
        // fire the property change event, with a property that has changed
        support.firePropertyChange("ParserSourceStructure",oldParserSourceStructure,ParserSourceStructure);
    } 

    /**
     *
     * @return
     */
    public String getParserName() {
        return ParserName;
    }

    /**
     *
     * @param ParserName
     */
    public void setParserName(String ParserName) {
        this.ParserName = ParserName;
        updateParserSource(Grammar.getGrammarStructure());
    }

        
    @Override
    public void setGrammar(IGrammarComp aGrammar){
         super.setGrammar(aGrammar);
         updateParserSource(Grammar.getGrammarStructure());
         //parseText();
         //fire the property change event, with a property that has changed
         support.firePropertyChange("Grammar",null,Grammar);
    }
    
    /**
     *
     * @param aGrammarStructure
     */
    public void updateParserSource(CGrammar aGrammarStructure){
        //CGrammar vGrammarStructure=Grammar.getGrammarStructure();
       if(!(aGrammarStructure.getStartExpr()instanceof CSE_Empty)){
           //aGrammarStructure=vGrammarStructure;
           fGen.setGrammar(aGrammarStructure);
           fGen.setGrammarName(ParserName);
           //fGen.setPackage("Hello");
           CompUnitList = fGen.GrammarToCompilationUnitList(aGrammarStructure);
           ParserSourceStructure= CompUnitList.getNode(0);
           // Update textual representation
           ParserSourceText=toText(); 
           setProdList(fGen.getMethodMap());
       } 
        
    }
    
   /**
     * Return the textual representation of Parser source
     * @return the textual representation of Parser source
     */
    @Override
    public String toText(){ // 
             StringBuilder buffer=new StringBuilder();
                     
             buffer.append(javaFlatener.f_CompilationUnit(ParserSourceStructure,1));
                         
            // buffer.deleteCharAt(buffer.length()-2);
	     return buffer.toString();
	}
    
    /**
     *
     * @return
     */
    @Override
    public CECFGNode getParseTree() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param aParseTree
     */
    @Override
    public void setParseTree(CECFGNode aParseTree) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CParser getParser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CParserResult getParserResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public CNode getNode() {
        return (CNode)CompUnitList;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) { 
       Object source=evt.getSource();
       if(source==Grammar){
           updateParserSource(Grammar.getGrammarStructure());
       }
       
    }

    /**
     *
     * @param parser
     */
    @Override
    public void setParser(CParser parser) {
        
    }
    
}
