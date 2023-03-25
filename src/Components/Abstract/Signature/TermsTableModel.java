/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Abstract.Signature;

import Signatures.CMemberDefBody;
import Signatures.CMemberDefinition_List;
import javax.swing.table.AbstractTableModel;

/** 
 * An implementation of a table model for term items of a signature.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class TermsTableModel extends AbstractTableModel{

    /**
     *
     */
    protected String[] fColumnNames={"Name","Type","Modifier"};

    /**
     *
     */
    protected String [][] fData;
    private Class[] types = new Class [] {
             String.class,String.class,String.class
    };

    TermsTableModel(){
       fData=new String[3][fColumnNames.length];
      
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
       return true;
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
     * @param aList
     */
    public void updateTermsTable(CMemberDefinition_List aList){
       
        fData =new String[aList.contextCount()][fColumnNames.length];
          
          CMemberDefBody vBody;
          for(int i=0; i<aList.contextCount();i++){
             fData[i][0]=aList.getElt(i).getName();
             vBody=aList.getElt(i).getBody();
             fData[i][1]=vBody.getType().toText();
             fData[i][2]=Character.toString(vBody.getModifier());
          } 
        fireTableDataChanged();
    }

}

