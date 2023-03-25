/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables.LLParseTables;


import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.ParseTables.CParseTableComp;
import GrammarNotions.Grammar.CGrammar;
import TableGenerators.CParseTable;
import TableGenerators.LL.LLTable.CLLTable;
import TableGenerators.LL.LLTable.GrammartoLLTableGenerator;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * An implementation of a component which holds LL(1)-based parse tables and maintains consistency between the textual representation of LL(1)
 * tables and their corresponding structural representation. The component has a hidden 
 * LL(1) parse-tables generator which produces the parse tables. When the
 * LLTablesComp component observes a change in the GrammarComp component, it
 * invokes its hidden parse-tables generator to regenerate its LL(1) tables and notifies its
 * observers.
 * 
 *  @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CLLParseTableComp extends CParseTableComp implements ILLParseTableComp {
   
    /**
     * The internal representation of LL(1) tables.
     */
    private CLLTable   TableStructure; 
    
    /**
     * A list of alphabet symbols (terminals) used to construct LL(1) tables.
     */
    private ArrayList<String> termAlphabet;
    /**
     * A list of alphabet symbols (terminals and nonterminals) used to construct LL(1) tables.
     */
    private ArrayList<String> nontermAlphabet;
    
    /**
     * Creates a new instance of <code>CLLParseTable</code>.
     */
    public CLLParseTableComp() {
       super();
       TableStructure=new CLLTable();
    }
    /**
     * Paints the UI of the LLParseTable component object. 
     * 
     * @param g the LLParseTable component object to paint.
     */
    @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("LLParseTable",5, 10);
   }
   
    
    /**
     * Returns internal representation of LL(1) parse tables.
     * @return the internal representation of LL(1) parse tables
     */
    @Override
    public CLLTable getTableStructure(){
        return TableStructure;
    }
    /**
     * Sets the value of the internal structure of LL(1) parse tables and notifies observers about <code>LLTableStructure</code> property changes.
     * @param aTableStructure the value of the internal structure of LL(1) parse tables.
     */
    @Override
    public void setTableStructure(CParseTable aTableStructure){
        // keep the old value
        CLLTable oldTableStructure=TableStructure;
        // save the new value
        TableStructure=(CLLTable) aTableStructure;
       // Parser.setLLTable(LLTableStructure);
        // Update textual representation
        //LLTableText=toText(); // Needs reconsideration
        // fire the property change event, with a property that has changed
        support.firePropertyChange("TableStructure",oldTableStructure,TableStructure);
    }
    /**
    *  Links to the given grammar object, regenerates the LL(1) parse tables and notifies its observers.
    * 
    * @param aGrammar new value of Grammar
   */
    @Override
    public void setGrammar(IGrammarComp aGrammar){
         super.setGrammar(aGrammar);
         updateTables(Grammar.getGrammarStructure());
         
        //fire the property change event, with a property that has changed
         support.firePropertyChange("Grammar",null,Grammar);
    }
    /**
     * Returns a list of terminal symbol-names used in constructing the parse tables.
     * @return the list of terminal and non-terminal symbol-names 
     */
     @Override
    public ArrayList<String> getTermAlphabet(){
        return termAlphabet;
    }
    /**
    * Returns a list of terminal symbol values and non-terminal symbol-names to be used in constructing the parse tables.
     * @return the list of terminal symbol values and non-terminal symbol-names
    */
    public ArrayList<String> getNontermAlphabet(){
           return nontermAlphabet; 
    }
    /**
     * Updates LL parse tables of this component.
     * @param aGrammarStructure the internal structure of the grammar used to update LL parse tables
     */
    public void updateTables(CGrammar aGrammarStructure){
            //create generator object;
            GrammartoLLTableGenerator vTool=new GrammartoLLTableGenerator();
            termAlphabet=new ArrayList();
            termAlphabet=vTool.grammartoTerminalAlphabet(aGrammarStructure);
            nontermAlphabet=new ArrayList();
            nontermAlphabet=vTool.grammartoNonTerminals(aGrammarStructure);
            TableStructure=vTool.grammartoLLTable(aGrammarStructure);
            
            setTableStructure(TableStructure);
            
       }
     /**
     * Receives property change events and handles them. 
     * If the property change is from the <code>GrammarComp</code> component's <code>GrammarText</code>, <code>GrammarStructure</code> or <code>Augment</code>,
     * retrieves the internal structure of this grammar and updates its LL parse tables. 
     * 
     * @param evt event object with the new value
     */
     @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String property=evt.getPropertyName();
        if(property.equals("GrammarText")||property.equals("GrammarStructure")||property.equals("Augment")){
              updateTables(Grammar.getGrammarStructure());
        }
    }
    /**
     * 
     * reset the parse tables to default
     */
    public void clear(){
    TableStructure=new CLLTable();
    }

    
}
