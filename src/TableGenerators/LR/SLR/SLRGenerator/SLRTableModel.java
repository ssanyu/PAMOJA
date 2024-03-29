/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LR.SLR.SLRGenerator;






import TableGenerators.LR.Move;
import TableGenerators.LR.SLR.SLRAutomata.CSLRDFA;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jssanyu
 */
public class SLRTableModel extends AbstractTableModel {
     String[] fColumnNames={
                "A", "B", "C", "D", "E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "AA", "AB", "AC", "AD", "AE","AF","AG","AH","AI","AJ","AK","AL","AM","AN","AO","AP","AQ","AR","AS","AT","AU","AV","AW","AX","AY","AZ",
               // "BA", "BB", "BC", "BD", "BE","BF","BG","BH","BI","BJ","BK","BL","BM","BN","BO","BP","BQ","BR","BS","BT","BU","BV","BW","BX","BY","BZ"
            };
     Object[][] fData;
      
    
    /*  Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
     };*/

    /**
     *
     */
    

     public SLRTableModel(){
        
     }  
     
    /**
     *
     * @param aTable
     * @param aDFA
     * @param aAlph
     */
    public void updateSLRTable(ArrayList<Move>[][] aTable,CSLRDFA aDFA,ArrayList<String> aAlph){
        int rowCount=aDFA.stateCount();
        int colCount=aDFA.alphabet().cardinality(); 
        int i,vState,j;
        int y;
        CGrammarToSLRTable vDFATool=new CGrammarToSLRTable();
        
        fData=new Object[rowCount+2][fColumnNames.length];
        fData[0][0]="STATE";
        for(int row=0;row<rowCount;row++){
            fData[row+1][0]=row;
        }
        
        for(int col=0;col<aAlph.size();col++){
            fData[0][col+1]=aAlph.get(col);
        }
        
        for(vState=0,i=1; vState<=aDFA.stateCount()-1;vState++,i++){
             for(y=1, j=aDFA.alphabet().nextSetBit(0); j>=0;j=aDFA.alphabet().nextSetBit(j+1),y++){
                     
                  fData[i][y]=vDFATool.ActionListtoString(aTable[vState][j]);
             } 
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
            return String.class;
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
 //   @Override
   public void setValueAt(Object value, int row, int col) {
        fData[row][col] = value;
        fireTableCellUpdated(row, col);
    }


    
    
    
}
