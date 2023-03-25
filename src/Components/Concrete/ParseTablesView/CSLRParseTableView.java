/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTablesView;


import Components.Concrete.ParseTables.LRParseTables.SLRParseTables.ISLRParseTableComp;
import Components.Concrete.Parser.TableDrivenParser.SLRParser.ISLRParserComp;
import TableGenerators.LR.Accept;
import TableGenerators.LR.Move;
import TableGenerators.LR.Reduce;
import TableGenerators.LR.Reject;
import TableGenerators.LR.SLR.SLRAutomata.CSLRTable;
import TableGenerators.LR.SLR.SLRGenerator.CGrammarToSLRTable;
import TableGenerators.LR.Shift;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
    int colCount=0;
    
    private DefaultTableModel tb;
    
    /**
     * Creates a new instance of SLRParseTableView.
     */
    public CSLRParseTableView(){
        super();
        setShowGrid(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);
        setCellColor(-1,-1);
        
    }

    /**
     * Updates this view with the specified <code>aSLRTableStructure</code>.
     * @param aSLRTableStructure the specified  SLR table structure.
     */
    public void updateSLRParseTableView(CSLRTable aSLRTableStructure){
        if(aSLRTableStructure!=null){
            buildSLRParseTableModel(aSLRTableStructure);
        }else if(SLRParseTable.getfTable1()!=null){
            buildSLRParseTable1Model(SLRParseTable.getfTable1());
        }
    } 
    
    /**
     * Builds a table model with the specified <code>aSLRTableStructure</code>.
     * @param aSLRTableStructure the specified  SLR table structure.
     */
    public void buildSLRParseTableModel(CSLRTable aSLRTableStructure){
        //Build a Table model
        //1. Get column names from the alphabet
         if(SLRParseTable!=null){
          colCount=SLRParseTable.getSymbolAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              columnNames[i]=SLRParseTable.getSymbolAlphabet().get(k);
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
     }

    /**
     * Builds a table model with the specified two-dimensional array of SLR table.
     * @param aTable1 the SLR table as a two-dimensional array.
     */
    public void buildSLRParseTable1Model( ArrayList<Move>[][] aTable1){
         CGrammarToSLRTable vTool=new CGrammarToSLRTable();
        //Build a Table1 model
        //1. Get column names from the alphabet
          colCount=SLRParseTable.getSymbolAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              columnNames[i]=SLRParseTable.getSymbolAlphabet().get(k);
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
          
          //TableCellEditor cellEditor = getCellEditor(3, 2);
         
         //4. Set the model just built as model of SLRTableView
          setModel(tb);
         // setRowHeight(this.getRowHeight()*2);
          //setDefaultRenderer(String.class,new TextAreaRenderer());
          
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
           vSLRTableStructure=(CSLRTable) SLRParseTable.getTableStructure();
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
            updateSLRParseTableView((CSLRTable) SLRParseTable.getTableStructure());
        }else if(source==SLRParser){
            updateSLRParserTableView(SLRParser.getTableStructure());
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
           vSLRTableStructure=SLRParser.getTableStructure();
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
        }else if(SLRParser.getLRTable1()!=null){
            buildSLRParserTable1Model(SLRParser.getLRTable1());
        }
    } 
    
   /**
     * Builds a table model with the specified <code>aSLRTableStructure</code> from the parser.
     * @param aSLRTableStructure the specified  SLR table structure.
     */
    public void buildSLRParserTableModel(CSLRTable aSLRTableStructure){
        //Build a Table model
        //1. Get column names from the alphabet
          colCount=SLRParser.getSymbolAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              columnNames[i]=SLRParser.getSymbolAlphabet().get(k);
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
          colCount=SLRParser.getSymbolAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              columnNames[i]=SLRParser.getSymbolAlphabet().get(k);
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
         // setDefaultRenderer(String.class,new TextAreaRenderer());
          
         // TableColumnModel tcm=this.getColumnModel();
         // tcm.getColumn(0).
    }
     //Implement table cell tool tips.
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);
                TableModel model = getModel();
                colCount=SLRParseTable.getSymbolAlphabet().size();
                if (realColumnIndex >=1 && realColumnIndex <=colCount){
                    Move action= (Move)model.getValueAt(rowIndex,colIndex);
                    if(action instanceof Reject || action instanceof Accept){
                       String vAction=action.toString();
                       if(vAction.equals(""))
                          tip="Error";
                       else if(vAction.equals("Acc"))
                          tip="Accept";
                    }else if(action instanceof Shift){
                        if(((Shift)action).NT==false)
                           tip="Shift current input and state "+ ((Shift)action).getState()+ " to stack";
                        else tip="Goto state "+ ((Shift)action).getState();
                        
                    }else if(action instanceof Reduce){
                        tip="Reduce by production "+ ((Reduce)action).getProdNo()+ ", index "+((Reduce)action).getIndex()+", "+((Reduce)action).getProd();
                    }else{
                    tip = super.getToolTipText(e);
                }
                        
            }
                return tip;
            }   
   
   
    
    public void setCellColor(int r,int col){
        
       setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
               if(column==0) {
                   c.setBackground(Color.LIGHT_GRAY);
                             
               }else if(row==r && column==col){
                   c.setBackground(Color.magenta);
               }else c.setBackground(Color.WHITE);
                 repaint();
                return c;
            }
      });
      
   }
    
    
    
   
    
}