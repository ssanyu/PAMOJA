/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Specifications.Presentation;

import javax.swing.table.AbstractTableModel;

/** 
 * An implementation of a table model for SymbolToCategory.
 *
 * @see SymbolCategory
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class SymbolToCategoryTableModel extends AbstractTableModel{

    CSymbolStyleCustomizerStructure SymbolStyleCustomizerStructure;

    /**
     *
     */
    protected String[] fColumnNames={"Symbol","Symbol Category"};

    /**
     *
     */
    protected Object [][] fData;
    private final Class[] types = new Class [] {
             String.class,String.class
    };

    SymbolToCategoryTableModel(){
         SymbolStyleCustomizerStructure=new CSymbolStyleCustomizerStructure();
         fData=new Object[2][2];
      
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
       return (String)fData[row][col];
    }

    /*
         * Don't need to implement this method unless your table's
         * editable.
    */

    @Override
    public boolean isCellEditable(int row, int col) {
       return (col!=0);
    }

  /*
     * Don't need to implement this method unless your table's
     * data can change.
  */
    @Override
    public void setValueAt(Object value, int row, int col) {
       fData[row][col] =(String)value;
       fireTableCellUpdated(row, col);
    }

    /**
     *
     * @param aSymbolStyleCustomizerStructure
     */
    public void updateCategoryTable(CSymbolStyleCustomizerStructure aSymbolStyleCustomizerStructure){
       
        SymbolStyleCustomizerStructure=aSymbolStyleCustomizerStructure;
        int vRows=SymbolStyleCustomizerStructure.getFSymbolToCategory().size();
        fData=new String [vRows][2];
        for(int i=0;i<vRows;i++){
            fData[i][0]=SymbolStyleCustomizerStructure.getFSymbolToCategory().get(i).FSymbolName;
            fData[i][1]=SymbolStyleCustomizerStructure.getFSymbolToCategory().get(i).FCategory;
            
        }
       
    }

}

