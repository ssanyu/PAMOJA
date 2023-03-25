/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTablesView;

import Components.Concrete.ParseTables.LRParseTables.ILRParseTableComp;
import Components.Concrete.Parser.TableDrivenParser.LRParser.ILRParserComp;
import TableGenerators.CParseTable;
import TableGenerators.LR.Accept;
import TableGenerators.LR.CGrammarToLRTable;
import TableGenerators.LR.CLRTable;
import TableGenerators.LR.Move;
import TableGenerators.LR.Reduce;
import TableGenerators.LR.Reject;
import TableGenerators.LR.Shift;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author HP
 */
public class CLRParseTableView extends CParseTableView{
    private ILRParseTableComp LRParseTable=null;
    private ILRParserComp LRParser=null;

    public CLRParseTableView() {
        super();
    }
    
   
    
    
    
     
    
    
   
  
    /**
     * Builds a table model with the specified two-dimensional array of SLR table from the parser.
     * @param aTable1 the SLR table as a two-dimensional array.
     */
    public void buildParserTable1Model( ArrayList<Move>[][] aTable1){
         CGrammarToLRTable vTool=new CGrammarToLRTable();
        //Build a Table1 model
        //1. Get column names from the alphabet
          colCount=LRParser.getSymbolAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]="STATES";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              columnNames[i]=LRParser.getSymbolAlphabet().get(k);
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
                colCount=LRParseTable.getSymbolAlphabet().size();
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
                         tip ="";// super.getToolTipText(e);
                }
                        
            }
                return tip;
            }   
   
   
}
