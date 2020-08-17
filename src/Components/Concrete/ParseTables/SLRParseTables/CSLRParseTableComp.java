/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables.SLRParseTables;


import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.ParseTables.CParseTableComp;
import GrammarNotions.Grammar.CGrammar;
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
 * An implementation of a component which holds SLR(1)-based parse tables and maintains consistency between the textual representation of SLR(1)
 * tables and their corresponding structural representation. The component has a hidden 
 * SLR(1) parse-tables generator which produces the parse tables. When the
 * SLRTablesComp component observes a change in the GrammarComp component, it
 * invokes its hidden parse-tables generator to regenerate its SLR(1) tables and notifies its
 * observers.
 * 
 *  @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSLRParseTableComp extends CParseTableComp implements ISLRParseTableComp {
    /**
     * The string representation of SLR(1) tables.
     */
    private String SLRTableText=""; 
    /**
     * The internal representation of SLR(1) tables.
     */
    private CSLRTable   SLRTableStructure; 
    
    /**
     * A list of alphabet symbols (terminals and nonterminals) used to construct SLR(1) tables.
     */
    private ArrayList<String> strAlphabet;
    /**
     * A data structure for holding SLR(1)parse-tables in tabular form.
     */
    private ArrayList<Move> fSLRTable1[][]=null;

    /**
     * Creates a new instance of <code>CSLRParseTable</code>.
     */
    public CSLRParseTableComp() {
       super();
       SLRTableStructure=new CSLRTable(new CSLRDFA(new IntAlphabet()));
    }
    /**
     * Paints the UI of the SLRParseTable component object. 
     * 
     * @param g the SLRParseTable component object to paint.
     */
    @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("SLRParseTable",5, 10);
   }
    /**
     * Returns the string representation of SLR(1) parse tables.
     * @return the string representation of SLR(1) parse tables
     */
    @Override
   public String getSLRTableText(){
       return SLRTableText;
   }
   
    /**
     * Sets the string representation of SLR(1) parse tables.
     * @param aSLRTableText the string representation of SLR(1) parse tables to set.
     */
    @Override
    public void setSLRTableText(String aSLRTableText){
      //TO DO  
    }
    
    /**
     * Returns SLR(1) parse-tables.
     * @return SLR(1) parse-tables
     */
    @Override
    public ArrayList<Move>[][] getSLRTable1(){
        return fSLRTable1;
    }
    /**
     * Returns internal representation of SLR(1) parse tables.
     * @return the internal representation of SLR(1) parse tables
     */
    @Override
    public CSLRTable getSLRTableStructure(){
        return SLRTableStructure;
    }
    /**
     * Sets the value of the internal structure of SLR(1) parse tables and notifies observers about <code>SLRTableStructure</code> property changes.
     * @param aSLRTableStructure the value of the internal structure of SLR(1) parse tables.
     */
    @Override
    public void setSLRTableStructure(CSLRTable aSLRTableStructure){
        // keep the old value
        CSLRTable oldSLRTableStructure=SLRTableStructure;
        // save the new value
        SLRTableStructure=aSLRTableStructure;
       // Parser.setSLRTable(SLRTableStructure);
        // Update textual representation
        //SLRTableText=toText(); // Needs reconsideration
        // fire the property change event, with a property that has changed
        support.firePropertyChange("SLRTableStructure",oldSLRTableStructure,SLRTableStructure);
    }
    /**
    *  Links to the given grammar object, regenerates the SLR(1) parse tables and notifies its observers.
    * 
    * @param aGrammar new value of Grammar
   */
    @Override
    public void setGrammar(IGrammarComp aGrammar){
         super.setGrammar(aGrammar);
         updateSLRTables(Grammar.getGrammarStructure());
         
        //fire the property change event, with a property that has changed
         support.firePropertyChange("Grammar",null,Grammar);
    }
    /**
     * Returns a list of terminal symbols used in constructing the parse tables.
     * @return the list of terminal symbols 
     */
     @Override
    public ArrayList<String> getStringAlphabet(){
        return strAlphabet;
    }
    /**
     * Updates SLR parse tables of this component.
     * @param aGrammarStructure the internal structure of the grammar used to update SLR parse tables
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
     * Receives property change events and handles them. 
     * If the property change is from the <code>GrammarComp</code> component's <code>GrammarText</code>, <code>GrammarStructure</code> or <code>Augment</code>,
     * retrieves the internal structure of this grammar and updates its SLR parse tables. 
     * 
     * @param evt event object with the new value
     */
     @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String property=evt.getPropertyName();
        if(property.equals("GrammarText")||property.equals("GrammarStructure")||property.equals("Augment")){
              updateSLRTables(Grammar.getGrammarStructure());
        }
    }
    /**
     * 
     * reset the parse tables to default
     */
    public void clear(){
    SLRTableStructure=new CSLRTable(new CSLRDFA(new IntAlphabet()));
    }
}
