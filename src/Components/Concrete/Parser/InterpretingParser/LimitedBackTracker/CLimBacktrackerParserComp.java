 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Concrete.Parser.InterpretingParser.LimitedBackTracker;


import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.Parser.InterpretingParser.CInterpretingParserComp;
import Components.Concrete.TreeBuilder.ITreeBuilderComp;
import Components.Lexical.SymbolStream.ISymbolStreamComp;
import GrammarNotions.ECFGNodes.CECFGNode;
import Nodes.CNode;
import Parsers.CLimBackTrackerParser;
import Parsers.CParser;
import Parsers.CParserResult;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;


/**
 * A component holding an interpreting limited backtracker parser object. It observes a Grammar and a SymbolStream components and (re)parses the input string basing on the current symbol stream value.  
 * In addition it uses a TreeBuilder component which is responsible for constructing the different kinds of parse tree nodes.
 * <p>
 * <table BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
 *  <tr>
 *       <th>Characteristic</th>
 *       <th>Value</th>
      </tr>
    <tr>
        <td>grammar kind </td>
        <td>ECFG</td>
    </tr>
    <tr>
        <td>grammar conditions</td>
        <td>no left recursion, no Null(f) for f* </td>
        
    </tr>
    <tr>
        <td>grammar processing</td>
        <td>interpreted </td>
        
    </tr>
    <tr>
        <td>collaborators </td>
        <td>grammar , symbol stream, tree builder </td>
        
    </tr>
    <tr>
        <td>strategy</td>
        <td>limited backtrack, success boolean</td>
        
    </tr>
    * <tr>
        <td>strategy options</td>
        <td>lookahead,treebuilding </td>
        
    </tr>
   <caption>LimBacktrackParser Characteristics</caption>
</table>
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CLimBacktrackerParserComp extends CInterpretingParserComp {
    /**
     *  A limited backtracker parser object.
     * 
     * @see Parsers.CLimBackTrackerParser
     */
    CLimBackTrackerParser Parser;
    
    /**
     * Creates an instance of a LimitedBacktrackerParser component.
     */
    
    public CLimBacktrackerParserComp(){
        super();
        Parser=new CLimBackTrackerParser();
        
    }

  public CLimBackTrackerParser getParser(){
       return Parser;
   }
    
    /**
     *
     * @param parser
     */
    public void setParser(CParser parser){
       Parser= (CLimBackTrackerParser) parser;
       
   } 
    
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("LBParser",5,10);
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

    /**
     *
     * @return
     */
    @Override
    public CNode getNode() {
        return Parser.getParserResult().getNode();
    }
    
    /**
     * Parses input string as a symbol stream.
     */
    public void parseText(){
       CParserResult vResult= new CParserResult();
       if(SymbolStream!=null && SymbolStream.getSymbolStreamStructure().symbolCount()!=0 && Grammar!=null)
           vResult=Parser.parse();
       Parser.setParserResult(vResult);
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
    
    /**
     *
     * @param aValue
     */
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
    @Override
    public void setGrammar(IGrammarComp aGrammar) {
      super.setGrammar(aGrammar);
      Parser.setGrammar(Grammar.getGrammarStructure());
      parseText();
    }
    @Override
    public CParserResult getParserResult() {
        return Parser.getParserResult();
    }

    /**
     * Returns a parse tree.
     * @return
     */
    @Override
    public CECFGNode getParseTree() {
        return (CECFGNode)Parser.getParseTree();
        
    }

    /**
     * Sets the specified parse tree and notifies its observers.
     * 
     * @param aParseTree the parse tree to set.
     */
    @Override
    public void setParseTree(CECFGNode aParseTree) {
         // keep the old value
      CECFGNode  oldParseTree=(CECFGNode)Parser.getParseTree();
       // get the new value
       Parser.setParseTree(aParseTree);
      // Parser.setParseTree(aParseTree);
       // fire the property change event, with a property that has changed
       support.firePropertyChange("ParseTree",oldParseTree,aParseTree);
    }
    

}
