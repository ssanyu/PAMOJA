/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Concrete.GrammarView;

import GrammarNotions.Grammar.CGrammar;
import javax.swing.table.AbstractTableModel;



/** 
 * An implementation of a base class for the GrammarView table models.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public abstract class CGrammarViewTableModel extends AbstractTableModel{


    private Class[] types = new Class [] {
             String.class,String.class };

    /**
     *
     */
    protected CGrammar GrammarStructure;

    /**
     *
     */
    protected String[] fColumnNames={"Name","Value"};

    /**
     *
     */
    protected Object fData[][];
        
    CGrammarViewTableModel(){
       GrammarStructure=new CGrammar();
       
       
    }

    @Override
    public int getColumnCount() {
       return fColumnNames.length;
    }

    @Override
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

    @Override
    public Object getValueAt(int row, int col) {
          return fData[row][col];
    }
    /*
     * Don't need to implement this method unless your table's
     * editable.
    */
    @Override
    public boolean isCellEditable(int row, int col) {
       return true;
    }

  /*
   * Don't need to implement this method unless your table's
   * data can change.
  */
   @Override
    public void setValueAt(Object value, int row, int col) {
       fData[row][col]=(String)value;
       fireTableCellUpdated(row, col);
    }

  /*  public boolean hasEmptyRow(){
      if (fData.isEmpty()) return false;
       table=(SymbolTable)fData.get(fData.size()-1);
       if(table.getName().trim().equals("")&& table.getValue().trim().equals("")){
           return true;
       }else return false;
    }

    public void addEmptyRow(){
        fData.add(new SymbolTable());
        fireTableRowsInserted(fData.size()-1,fData.size()-1);
       
    }*/


}
