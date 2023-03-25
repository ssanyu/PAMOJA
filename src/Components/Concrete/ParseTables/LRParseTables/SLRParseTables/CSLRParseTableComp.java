/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables.LRParseTables.SLRParseTables;


import Components.Concrete.Grammar.IGrammarComp;
import Components.Concrete.ParseTables.LRParseTables.CLRParseTableComp;
import GrammarNotions.Grammar.CGrammar;
import Sets.IntAlphabet;
import TableGenerators.CParseTable;
import TableGenerators.LR.Move;
import TableGenerators.LR.SLR.SLRAutomata.CSLRDFA;
import TableGenerators.LR.SLR.SLRAutomata.CSLRNFA;
import TableGenerators.LR.SLR.SLRAutomata.CSLRTable;
import TableGenerators.LR.SLR.SLRGenerator.CGrammarToSLRTable;
import TableGenerators.LR.Shift;
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
public class CSLRParseTableComp extends CLRParseTableComp implements ISLRParseTableComp {
    
    
    /**
     * Creates a new instance of <code>CSLRParseTable</code>.
     */
    public CSLRParseTableComp() {
       super();
       TableStructure=new CSLRTable(new CSLRDFA(new IntAlphabet()));
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
     * Returns internal representation of SLR(1) parse tables.
     * @return the internal representation of SLR(1) parse tables
     */
    @Override
    public CSLRTable getTableStructure(){
        return (CSLRTable)TableStructure;
    }
    /**
     * Sets the value of the internal structure of SLR(1) parse tables and notifies observers about <code>SLRTableStructure</code> property changes.
     * @param aSLRTableStructure the value of the internal structure of SLR(1) parse tables.
     */
 
    /**
     * Sets the value of the internal structure of SLR(1) parse tables and notifies observers about <code>SLRTableStructure</code> property changes.
     * @param aTableStructure the value of the internal structure of SLR(1) parse tables.
     */
    @Override
    public void setTableStructure(CParseTable aTableStructure){
        // keep the old value
        CSLRTable oldTableStructure=(CSLRTable)TableStructure;
        // save the new value
        TableStructure=(CSLRTable) aTableStructure;
       // Parser.setSLRTable(SLRTableStructure);
        // Update textual representation
        //SLRTableText=toText(); // Needs reconsideration
        // fire the property change event, with a property that has changed
        support.firePropertyChange("TableStructure",oldTableStructure,TableStructure);
    }
    /**
    *  Links to the given grammar object, regenerates the SLR(1) parse tables and notifies its observers.
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
     * Updates SLR parse tables of this component.
     * @param aGrammarStructure the internal structure of the grammar used to update SLR parse tables
     */
    @Override
    public void updateTables(CGrammar aGrammarStructure){
            CSLRNFA vNFA;
            CSLRDFA vDFA;
            ArrayList<Move> vSLRTable1[][];
            CSLRTable vSLRTable2;
             
            //create generator object;
            CGrammarToSLRTable vTool=new CGrammarToSLRTable();
            
            // get alphabet in String/Sym form
            strAlphabet=new ArrayList<>();
            strAlphabet=vTool.GrammarToStringAlphabet(aGrammarStructure); 
            
            symAlphabet=new ArrayList<>();
            symAlphabet=vTool.GrammarToSymbolAlphabet(aGrammarStructure); 
            
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
              updateTables(Grammar.getGrammarStructure());
        }
    }
    /**
     * 
     * reset the parse tables to default
     */
    @Override
    public void clear(){
    TableStructure=new CSLRTable(new CSLRDFA(new IntAlphabet()));
    }

    

    

    
}
