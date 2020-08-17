/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.SourceCodeGeneratedParser.Deterministic;

import Components.IPAMOJAComponent;
import Java.JCompilationUnit;
import Java.JMethodDec;
import java.util.LinkedHashMap;

/**
 * A provided interface for interacting with other components ( like, Grammar and DeterministicParserSourceView).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ISourceCodeDeterministicParserComp extends IPAMOJAComponent{
       
    /**
     * Returns a textual representation of the source-code for a SourceCodeDeterministicParser
     * @return a textual representation of the source-code for a SourceCodeDeterministicParser
     */
        public String getParserSourceText();

   /**
     * Sets the given textual representation of the source-code to this SourceCodeDeterministicParser
     * @param ParserSourceText given textual representation of the source-code 
     */
    public void setParserSourceText(String ParserSourceText);


    /**
     * Returns a structural representation of the source-code for a SourceCodeDeterministicParser
     * @return a structural representation of the source-code for a SourceCodeDeterministicParser
     */
        public JCompilationUnit getParserSourceStructure();

    /**
     * Sets the given structural representation of the source-code to this SourceCodeDeterministicParser
     * @param aParserSourceStructure given structural representation of the source-code 
     */
    public void setParserSourceStructure(JCompilationUnit aParserSourceStructure);
    
    

    /**
     * Return a list of (production-rule,Method-Declaration) pair for the grammar
     * @return list of (production-rule,Method-Declaration) pair 
     */
     public LinkedHashMap<String,JMethodDec> getProdList();
    
   /**
     * Return the textual representation of Parser source
     * @return the textual representation of Parser source
     */
        public String toText();

}
