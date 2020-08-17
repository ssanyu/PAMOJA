/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTablesView;


import Components.Concrete.ParseTables.SLRParseTables.ISLRParseTableComp;
import Components.Concrete.Parser.TableDrivenParser.SLRParser.ISLRParserComp;
import SLR.SLRAutomata.CSLRTable;
import SLR.SLRAutomata.Move;
import SLR.SLRGenerator.CGrammarToSLRTable;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * A table view for the SLR(1) parse tables.
 * When linked to <code>SLRParseTableComp</code> or or <code>SLRParser</code>, it observes changes in the parse tables and updates
 * its view.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSLRParseTableView extends JTable implements PropertyChangeListener{
    private ISLRParseTableComp SLRParseTable=null;
    private ISLRParserComp SLRParser=null;
    
    private DefaultTableModel tb;
    
    /**
     * Creates a new instance of SLRParseTableView.
     */
    public CSLRParseTableView(){
        super();
    }

    /**
     * Updates this view with the specified <code>aSLRTableStructure</code>.
     * @param aSLRTableStructure the specified  SLR table structure.
     */
    public void updateSLRParseTableView(CSLRTable aSLRTableStructure){
        if(aSLRTableStructure!=null){
            buildSLRParseTableModel(aSLRTableStructure);
        }else if(SLRParseTable.getSLRTable1()!=null){
            buildSLRParseTable1Model(SLRParseTable.getSLRTable1());
        }
    } 
    
    /**
     * Builds a table model with the specified <code>aSLRTableStructure</code>.
     * @param aSLRTableStructure the specified  SLR table structure.
     */
    public void buildSLRParseTableModel(CSLRTable aSLRTableStructure){
        //Build a Table model
        //1. Get column names from the alphabet
          int colCount=SLRParseTable.getStringAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              columnNames[i]=SLRParseTable.getStringAlphabet().get(k);
          }
        //2. Get States
          Object rowData[][] =new Object[aSLRTableStructure.stateCount()][columnNames.length];
          for(int vState=0; vState<=aSLRTableStructure.stateCount()-1;vState++){
              rowData[vState][0]=vState;
          }
        //3. Get row data
         for(int vState=0; vState<=aSLRTableStructure.stateCount()-1;vState++){
              for(int i=1, j=aSLRTableStructure.alphabet().nextSetBit(0); j>=0;j=aSLRTableStructure.alphabet().nextSetBit(j+1),i++){
                  rowData[vState][i]=aSLRTableStructure.move(vState, j);
              } 
          }
          tb = new DefaultTableModel(rowData,columnNames);
        
         //4. Set the model just built as model of SLRTableView
          setModel(tb);
     }

    /**
     * Builds a table model with the specified two-dimensional array of SLR table.
     * @param aTable1 the SLR table as a two-dimensional array.
     */
    public void buildSLRParseTable1Model( ArrayList<Move>[][] aTable1){
         CGrammarToSLRTable vTool=new CGrammarToSLRTable();
        //Build a Table1 model
        //1. Get column names from the alphabet
          int colCount=SLRParseTable.getStringAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              columnNames[i]=SLRParseTable.getStringAlphabet().get(k);
          }
        //2. Get States
          Object rowData[][]=new Object[aTable1.length][columnNames.length];
          for(int vState=0; vState<=rowData.length-1;vState++){
              rowData[vState][0]=vState;
          }
        //3. Get row data
          for(int vState=0; vState<=aTable1.length-1;vState++){
              for(int j=0,i=1; j<columnNames.length-1; i++,j++){
                  rowData[vState][i]=vTool.ActionListtoString(aTable1[vState][j]);
              } 
          }
          tb = new DefaultTableModel(rowData,columnNames);
        
         //4. Set the model just built as model of SLRTableView
          setModel(tb);
         // setRowHeight(this.getRowHeight()*2);
          setDefaultRenderer(String.class,new TextAreaRenderer());
          
         // TableColumnModel tcm=this.getColumnModel();
         // tcm.getColumn(0).
    }

    /**
     * Get the value of SLRParseTable
     *
     * @return the value of SLRParseTable
     */
    public ISLRParseTableComp getSLRParseTable(){
        return SLRParseTable;
    }

   /**
  * Links to <code>SLRParseTable</code> component via its interface -- Set the value of <code>SLRParseTable</code>.
  * Register for property change events and retrieve current value of this SLRParseTable object and update the view.
  * 
  * @param aSLRParseTable new value of SLRParseTable
  */
    public void setSLRParseTable(ISLRParseTableComp aSLRParseTable){
       CSLRTable vSLRTableStructure=null;
       if(SLRParseTable!=null){
           SLRParseTable.removePropertyChangeListener(this);
       }
       SLRParseTable=aSLRParseTable;
       if(SLRParseTable!=null){
           SLRParseTable.addPropertyChangeListener(this);
           vSLRTableStructure=SLRParseTable.getSLRTableStructure();
       }
       updateSLRParseTableView(vSLRTableStructure);
    }
      /**
     * Receives property change events and handles them. If the property change is from the <code>SLRParseTable</code> or <code>SLRParser</code> component.
     * Retrieve the internal structure of its parse tables and update this view. 
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source= evt.getSource();
        if(source==SLRParseTable){
            updateSLRParseTableView(SLRParseTable.getSLRTableStructure());
        }else if(source==SLRParser){
            updateSLRParserTableView(SLRParser.getSLRTableStructure());
        }
    }
    
     /**
     * Get the value of SLRParser
     *
     * @return the value of SLRParser
     */
    public ISLRParserComp getSLRParser(){
        return SLRParser;
    }

     /**
  * Links to <code>SLRParser</code> component via its interface -- Set the value of <code>SLRParser</code>.
  * Register for property change events and retrieve current value of the parse tables to update the view.
  * 
  * @param aSLRParser new value of SLRParser
  */
    public void setSLRParser(ISLRParserComp aSLRParser){
       CSLRTable vSLRTableStructure=null;
       if(SLRParser!=null){
           SLRParser.removePropertyChangeListener(this);
       }
       SLRParser=aSLRParser;
       if(SLRParser!=null){
           SLRParser.addPropertyChangeListener(this);
           vSLRTableStructure=SLRParser.getSLRTableStructure();
       }
       updateSLRParserTableView(vSLRTableStructure);
    }
    
    /**
     * Updates this view with the specified <code>aSLRTableStructure</code> from the parser.
     * @param aSLRTableStructure the specified  SLR table structure.
     */
    public void updateSLRParserTableView(CSLRTable aSLRTableStructure){
        if(aSLRTableStructure!=null){
            buildSLRParserTableModel(aSLRTableStructure);
        }else if(SLRParser.getSLRTable1()!=null){
            buildSLRParserTable1Model(SLRParser.getSLRTable1());
        }
    } 
    
   /**
     * Builds a table model with the specified <code>aSLRTableStructure</code> from the parser.
     * @param aSLRTableStructure the specified  SLR table structure.
     */
    public void buildSLRParserTableModel(CSLRTable aSLRTableStructure){
        //Build a Table model
        //1. Get column names from the alphabet
          int colCount=SLRParser.getStringAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              columnNames[i]=SLRParser.getStringAlphabet().get(k);
          }
        //2. Get States
          Object rowData[][] =new Object[aSLRTableStructure.stateCount()][columnNames.length];
          for(int vState=0; vState<=aSLRTableStructure.stateCount()-1;vState++){
              rowData[vState][0]=vState;
          }
        //3. Get row data
         for(int vState=0; vState<=aSLRTableStructure.stateCount()-1;vState++){
              for(int i=1, j=aSLRTableStructure.alphabet().nextSetBit(0); j>=0;j=aSLRTableStructure.alphabet().nextSetBit(j+1),i++){
                  rowData[vState][i]=aSLRTableStructure.move(vState, j);
              } 
          }
          tb = new DefaultTableModel(rowData,columnNames);
        
         //4. Set the model just built as model of SLRTableView
          setModel(tb);
     }

    /**
     * Builds a table model with the specified two-dimensional array of SLR table from the parser.
     * @param aTable1 the SLR table as a two-dimensional array.
     */
    public void buildSLRParserTable1Model( ArrayList<Move>[][] aTable1){
         CGrammarToSLRTable vTool=new CGrammarToSLRTable();
        //Build a Table1 model
        //1. Get column names from the alphabet
          int colCount=SLRParser.getStringAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              columnNames[i]=SLRParser.getStringAlphabet().get(k);
          }
        //2. Get States
          Object rowData[][]=new Object[aTable1.length][columnNames.length];
          for(int vState=0; vState<=rowData.length-1;vState++){
              rowData[vState][0]=vState;
          }
        //3. Get row data
          for(int vState=0; vState<=aTable1.length-1;vState++){
              for(int j=0,i=1; j<columnNames.length-1; i++,j++){
                  rowData[vState][i]=vTool.ActionListtoString(aTable1[vState][j]);
              } 
          }
          tb = new DefaultTableModel(rowData,columnNames);
        
         //4. Set the model just built as model of SLRTableView
          setModel(tb);
         // setRowHeight(this.getRowHeight()*2);
          setDefaultRenderer(String.class,new TextAreaRenderer());
          
         // TableColumnModel tcm=this.getColumnModel();
         // tcm.getColumn(0).
    }
    
    /**
     *
     */
    public class TextAreaRenderer extends JTextArea implements TableCellRenderer {

        /**
         *
         */
        public TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
    }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value==null) ? "" : value.toString());//setText((String)value);
            return this;

        }
    }

    
}
