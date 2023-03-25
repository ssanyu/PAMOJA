/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.SymbolStreamView;



import SymbolStream.CSymbolStream;
import SymbolStream.Symbol;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


/** 
 * An implementation of a table model for the symbol stream.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSymbolStreamViewModel extends AbstractTableModel{
 
     String[] fColumnNames={
                "SymCode", "SymName", "SymValue", "SymStart", "SymLength"
            };
     Object[][] fData;
      Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
     };

     CSymbolStreamViewModel(){}  
     void updateRows(CSymbolStream aSymbolStream){
        int rowCount=aSymbolStream.symbolCount();
        fData=new Object[rowCount][fColumnNames.length];
        ArrayList<Symbol> vList =new ArrayList<Symbol>();
         
        for(  int i=0; i<aSymbolStream.getSymbolStream().size();i++){
           vList.addAll(aSymbolStream.getSymbolStream().get(i));
        }   
        for(int i=0;i<rowCount;i++){
            fData[i][0]=vList.get(i).symCode();
            fData[i][1]=vList.get(i).symName();
            fData[i][2]=vList.get(i).symValue();
            fData[i][3]=vList.get(i).symStart();
            fData[i][4]=vList.get(i).symLength();
        }
         fireTableDataChanged();
     }
    @Override
     public int getColumnCount() {
            return fColumnNames.length;
     }

   public int getRowCount() {
       return fData==null ? 0 : fData.length;
   }

    @Override
    public String getColumnName(int col) {
            return fColumnNames[col];
    }
    @Override
    public Class getColumnClass(int c) {
            return types[c];
        }

    public Object getValueAt(int row, int col) {
        //it seems retrieve() should be here
            return fData[row][col];

    }
    /*
         * Don't need to implement this method unless your table's
         * editable.
    */

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
   
  /*
     * Don't need to implement this method unless your table's
     * data can change.
  */
    @Override
    public void setValueAt(Object value, int row, int col) {
        fData[row][col] = value;
        fireTableCellUpdated(row, col);
    }


}



