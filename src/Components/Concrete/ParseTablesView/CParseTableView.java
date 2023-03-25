/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTablesView;

import Components.Concrete.LLParser.ILLParserComp;
import Components.Concrete.ParseTables.IParseTableComp;
import Components.Concrete.ParseTables.LLParseTables.ILLParseTableComp;
import Components.Concrete.ParseTables.LRParseTables.ILRParseTableComp;
import Components.Concrete.Parser.IParserComp;
import Components.Concrete.Parser.TableDrivenParser.LRParser.ILRParserComp;
import Parsers.CLLParser;
import Parsers.CLRParser;
import TableGenerators.CParseTable;
import TableGenerators.LL.LLTable.CLLTable;
import TableGenerators.LR.Accept;
import TableGenerators.LR.CGrammarToLRTable;
import TableGenerators.LR.CLRTable;
import TableGenerators.LR.Move;
import TableGenerators.LR.Reduce;
import TableGenerators.LR.Reject;
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
 *
 * @author HP
 */
public class CParseTableView extends JTable implements PropertyChangeListener{

    /**
     *
     */
    protected IParseTableComp ParseTable;

    /**
     *
     */
    protected IParserComp Parser;

    /**
     *
     */
    protected int colCount=0;

    /**
     *
     */
    protected CParseTable TableStructure;
    
    /**
     *
     */
    protected DefaultTableModel tb;
     /**
     * Creates a new instance of ParseTableView.
     */
    public CParseTableView(){
        super();
        setShowGrid(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        getTableHeader().setReorderingAllowed(false);
        setCellColor(-1,-1);
        
    }
 
      /**
     * Get the value of ParseTable
     *
     * @return the value of ParseTable
     */
    
    public IParseTableComp getParseTable(){
        return ParseTable;
    }
    /**
  * Links to <code>ParseTable</code> component via its interface -- Set the value of <code>ParseTable</code>.
  * Register for property change events and retrieve current value of this ParseTable object and update the view.
  * 
  * @param aParseTable new value of LRParseTable
  */
    public void setParseTable(IParseTableComp aParseTable){
     
       if(ParseTable!=null){
           ParseTable.removePropertyChangeListener(this);
       }
       ParseTable=(IParseTableComp) aParseTable;
       if(ParseTable!=null){
           ParseTable.addPropertyChangeListener(this);
           if(ParseTable instanceof ILRParseTableComp){
                TableStructure=(CLRTable)ParseTable.getTableStructure();
                updateParseTableView(((ILRParseTableComp)ParseTable));
           }else if(ParseTable instanceof ILLParseTableComp){
                TableStructure=(CLLTable)ParseTable.getTableStructure();
                updateLLParseTableView(((ILLParseTableComp)ParseTable));
           }
          
       }
      
    }
     /**
     * Get the value of SLRParser
     *
     * @return the value of SLRParser
     */
    public IParserComp getParser(){
        return Parser;
    }

     /**
  * Links to <code>LRParser</code> component via its interface -- Set the value of <code>LRParser</code>.
  * Register for property change events and retrieve current value of the parse tables to update the view.
  * 
     * @param aParser
  * 
  */
    public void setParser(IParserComp aParser){
       if(Parser!=null){
           Parser.removePropertyChangeListener(this);
       }
       Parser=aParser;
       if(Parser!=null){
          Parser.addPropertyChangeListener(this);
          if(Parser instanceof ILRParserComp){
                TableStructure=((ILRParserComp)Parser).getTableStructure();
                updateParserTableView(((ILRParserComp)Parser));
          }else if(Parser instanceof ILLParserComp){
                TableStructure=((ILLParserComp)Parser).getLLParseTableStructure();
                updateParserTableView(((ILLParserComp)Parser));
           }
       }
    }
    /**
     * Receives property change events and handles them. If the property change is from the <code>SLRParseTable</code> or <code>SLRParser</code> component.
     * Retrieve the internal structure of its parse tables and update this view. 
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        Object source= evt.getSource();
        if(source==ParseTable){
            TableStructure=ParseTable.getTableStructure();
            if(TableStructure instanceof CLRTable)
                 updateParseTableView(((ILRParseTableComp)ParseTable));
            else if(TableStructure instanceof CLLTable)
                 updateLLParseTableView(((ILLParseTableComp)ParseTable));
        }else if(source==Parser){
            if(Parser.getParser() instanceof CLRParser)
                updateParserTableView(((ILRParserComp)Parser));
            else if(Parser.getParser() instanceof CLLParser)
                updateParserTableView(((ILLParserComp)Parser));  
                    
        }
    }
    
    
      /**
     * Updates this view with the specified <code>aTableStructure</code>.
     * @param aTableComp
     */
    public void updateParseTableView(ILRParseTableComp aTableComp){
        setCellColor(-1,-1);
        if(TableStructure!=null){
            buildParseTableModel(aTableComp);
        }else if(aTableComp.getfTable1()!=null){
            buildParseTable1Model(aTableComp,aTableComp.getfTable1());
        }
    }  
    /**
     * Updates this view with the specified <code>aLLTableStructure</code>.
     * @param aTableComp the specified  LL table structure.
     */
    public void updateLLParseTableView(ILLParseTableComp aTableComp){
        setCellColor(-1,-1);
        TableStructure=aTableComp.getTableStructure();
        if(TableStructure!=null){
            buildLLParseTableModel(aTableComp);
        }
    } 
     /**
     * Updates this view with the specified <code>aLLTableStructure</code> from the parser.
     * @param aParserComp
     */
    public void updateParserTableView(ILLParserComp aParserComp){
        setCellColor(-1,-1);
        TableStructure=aParserComp.getLLParseTableStructure();
        if(TableStructure!=null){
            buildLLParserTableModel(aParserComp);
        }
    }
    
    /**
     * Builds a table model with the specified <code>aParserComp</code>.
     * @param aParserComp
     */
    public void buildLLParserTableModel(ILLParserComp aParserComp){
        //Build a Table model
        String vEntry="";
        String s;
        //1. Get column names from the alphabet
         if(aParserComp!=null){
         
          colCount=aParserComp.getTermAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]=" ";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              s=aParserComp.getTermAlphabet().get(k);
              if(s.equals("endmarker"))
                  columnNames[i]="$";
              else columnNames[i]=s;
          }
        //2. Get nonterminals
          Object rowData[][] =new Object[((CLLTable)TableStructure).getNonTerminals().size()][columnNames.length];
          for(int k=0; k<((CLLTable)TableStructure).getNonTerminals().size();k++){
              rowData[k][0]=((CLLTable)TableStructure).getNonTerminals().get(k);
          }
        //3. Get row data
          
          for(int n=0; n<((CLLTable)TableStructure).getNonTerminals().size();n++){
              for(int i=1,k=0; i<=((CLLTable)TableStructure).getTerminals().size()&& k<=((CLLTable)TableStructure).getTerminals().size()-1;i++,k++){
                  vEntry=((CLLTable)TableStructure).getTable()[n][k];
                  if(vEntry.equals("Error"))
                     rowData[n][i]="";
                  else rowData[n][i]=vEntry;
              } 
          }
        
          tb = new DefaultTableModel(rowData,columnNames);
        
         //4. Set the model just built as model of LLTableView
          setModel(tb);
         }
     }

    /**
     * Builds a table model with the specified <code>aLLTableStructure</code>.
     * @param aTableComp the specified  LL table structure.
     */
    public void buildLLParseTableModel(ILLParseTableComp aTableComp){
        //Build a Table model
        String vEntry="";
        String s;
        //1. Get column names from the alphabet
         if(aTableComp!=null){
          colCount=aTableComp.getTermAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]=" ";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              s=aTableComp.getTermAlphabet().get(k);
              if(s.equals("endmarker"))
                  columnNames[i]="$";
              else columnNames[i]=s;
          }
        //2. Get nonterminals
          Object rowData[][] =new Object[((CLLTable)TableStructure).getNonTerminals().size()][columnNames.length];
          for(int k=0; k<((CLLTable)TableStructure).getNonTerminals().size();k++){
              rowData[k][0]=((CLLTable)TableStructure).getNonTerminals().get(k);
          }
        //3. Get row data
          
          for(int n=0; n<((CLLTable)TableStructure).getNonTerminals().size();n++){
              for(int i=1,k=0; i<=((CLLTable)TableStructure).getTerminals().size()&& k<=((CLLTable)TableStructure).getTerminals().size()-1;i++,k++){
                  vEntry=((CLLTable)TableStructure).getTable()[n][k];
                  if(vEntry.equals("Error"))
                     rowData[n][i]="";
                  else rowData[n][i]=vEntry;
              } 
          }
        
          tb = new DefaultTableModel(rowData,columnNames);
        
         //4. Set the model just built as model of LLTableView
          setModel(tb);
         }
     }

    

     /**
     * Updates this view with the specified <code>aLRTableStructure</code> from the parser.
     * @param aParserComp
     */
    public void updateParserTableView(ILRParserComp aParserComp){
        setCellColor(-1,-1);
        TableStructure=aParserComp.getTableStructure();
        if(TableStructure!=null){
            buildParserTableModel(aParserComp);
        }else if(aParserComp.getLRTable1()!=null){
            buildParserTable1Model(aParserComp,aParserComp.getLRTable1());
        }
    } 
    
        
   /**
     * Builds a table model with the specified <code>aTableStructure</code> from the parser.
     * @param aParserComp
     */
    public void buildParserTableModel(ILRParserComp aParserComp){
        String s;
         //Build a Table model
        //1. Get column names from the alphabet
          if(aParserComp.getSymbolAlphabet()!=null){
          colCount=aParserComp.getSymbolAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              s=aParserComp.getSymbolAlphabet().get(k);
              if(s.equals("endmarker"))
                  columnNames[i]="$";
              else columnNames[i]=s;
          }
        //2. Get States
          Object rowData[][] =new Object[((CLRTable)TableStructure).stateCount()][columnNames.length];
          for(int vState=0; vState<=((CLRTable)TableStructure).stateCount()-1;vState++){
              rowData[vState][0]=vState;
          }
        //3. Get row data
         for(int vState=0; vState<=((CLRTable)TableStructure).stateCount()-1;vState++){
              for(int i=1, j=((CLRTable)TableStructure).alphabet().nextSetBit(0); j>=0;j=((CLRTable)TableStructure).alphabet().nextSetBit(j+1),i++){
                  rowData[vState][i]=((CLRTable)TableStructure).move(vState, j);
              } 
          }
          tb = new DefaultTableModel(rowData,columnNames);
          
         //4. Set the model just built as model of SLRTableView
          setModel(tb);
          }
     }

    /**
     * Builds a table model with the specified two-dimensional array of SLR table from the parser.
     * @param aParserComp
     * @param aTable1 the SLR table as a two-dimensional array.
     */
    public void buildParserTable1Model(ILRParserComp aParserComp, ArrayList<Move>[][] aTable1){
         CGrammarToLRTable vTool=new CGrammarToLRTable();
         String s;
        //Build a Table1 model
        //1. Get column names from the alphabet
          colCount=aParserComp.getSymbolAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              s=aParserComp.getSymbolAlphabet().get(k);
              if(s.equals("endmarker"))
                  columnNames[i]="$";
              else columnNames[i]=s;
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
    /**
     * Builds a table model with the specified <code>aTableStructure</code>.
     * @param aTableComp
     */
    public void buildParseTableModel(ILRParseTableComp aTableComp){
        String s;
        //Build a Table model
        //1. Get column names from the alphabet
         if(ParseTable!=null){
          colCount=aTableComp.getSymbolAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              s=aTableComp.getSymbolAlphabet().get(k);
              if(s.equals("endmarker"))
                  columnNames[i]="$";
              else columnNames[i]=s;
          }
        //2. Get States
          Object rowData[][] =new Object[((CLRTable)TableStructure).stateCount()][columnNames.length];
          for(int vState=0; vState<=((CLRTable)TableStructure).stateCount()-1;vState++){
              rowData[vState][0]=vState;
          }
        //3. Get row data
         for(int vState=0; vState<=((CLRTable)TableStructure).stateCount()-1;vState++){
              for(int i=1, j=((CLRTable)TableStructure).alphabet().nextSetBit(0); j>=0;j=((CLRTable)TableStructure).alphabet().nextSetBit(j+1),i++){
                  rowData[vState][i]=((CLRTable)TableStructure).move(vState, j);
                  
              } 
          }
          tb = new DefaultTableModel(rowData,columnNames);
         
         //4. Set the model just built as model of SLRTableView
          setModel(tb);
         }
     }
    
     /**
     * Builds a table model with the specified two-dimensional array of LR table.
     * @param aTableComp
     * @param aTable1 the LR table as a two-dimensional array.
     */
    public void buildParseTable1Model(ILRParseTableComp aTableComp, ArrayList<Move>[][] aTable1){
         CGrammarToLRTable vTool=new CGrammarToLRTable();
         String s;
        //Build a Table1 model
        //1. Get column names from the alphabet
          colCount=aTableComp.getSymbolAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              s=aTableComp.getSymbolAlphabet().get(k);
              if(s.equals("endmarker"))
                  columnNames[i]="$";
              else columnNames[i]=s;
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
     *
     * @param r
     * @param col
     */
    public void setCellColor(int r,int col){
      setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
               if(column==0) {
                   c.setBackground(Color.LIGHT_GRAY);
                             
               }else if(row==r && column==col+1){
                   c.setBackground(new Color(255,153,255));
               }else c.setBackground(Color.WHITE);
                 repaint();
                return c;
            }
      });
      
   }
    
   //Implement table cell tool tips.
          public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);
                TableModel model = getModel();
                if(TableStructure!=null && TableStructure instanceof CLRTable){
                      if(ParseTable!=null)
                       colCount=((ILRParseTableComp)ParseTable).getSymbolAlphabet().size();
                      else if(Parser!=null)
                       colCount=((ILRParserComp)Parser).getSymbolAlphabet().size();   
              
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
                         tip =super.getToolTipText(e);
                }
                  
            } }
                return tip; 
               
            }   
}
