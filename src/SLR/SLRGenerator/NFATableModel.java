/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SLR.SLRGenerator;


import SLR.SLRAutomata.CSLRNFA;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jssanyu
 */
public class NFATableModel extends AbstractTableModel {
    
     String[] fColumnNames={
                "A", "B", "C", "D", "E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "AA", "AB", "AC", "AD", "AE","AF","AG","AH","AI","AJ","AK","AL","AM","AN","AO","AP","AQ","AR","AS","AT","AU","AV","AW","AX","AY","AZ",
                //"BA", "BB", "BC", "BD", "BE","BF","BG","BH","BI","BJ","BK","BL","BM","BN","BO","BP","BQ","BR","BS","BT","BU","BV","BW","BX","BY","BZ"
            };
     Object[][] fData;
      
    
    /*  Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
     };*/

    /**
     *
     */
    

     public NFATableModel(){
        
     }  
     
    /**
     *
     * @param aNFA
     * @param aAlph
     */
    public void updateNFATable(CSLRNFA aNFA,ArrayList<String> aAlph){
        int rowCount=aNFA.stateCount();
        int colCount=aNFA.alphabet().cardinality(); 
        int i,vState,j;
        int y;
        
        
        
        fData=new Object[rowCount+2][fColumnNames.length];
        fData[0][0]="STATES";
        for(int row=0;row<rowCount;row++){
            fData[row+1][0]=row;
        }
        //Generate Epsilons
        fData[0][1]="Eps.";
        for(vState=0,i=1;vState<rowCount;vState++,i++){
           fData[i][1]=aNFA.epsTable(vState).toString();
        }
        for(int col=0;col<aAlph.size();col++){
            fData[0][col+2]=aAlph.get(col);
        }
        
        for(vState=0,i=1; vState<=aNFA.stateCount()-1;vState++,i++){
            
	     for(y=2, j=aNFA.alphabet().nextSetBit(0); j>=0;j=aNFA.alphabet().nextSetBit(j+1),y++){
                     
                  fData[i][y]=(aNFA.fwTransitions(vState,j));
             
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
