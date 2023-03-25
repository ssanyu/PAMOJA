package Components.Lexical.ScanTablesView.NFATableView;


import Automata.NFADFA.CNFA;
import Components.Lexical.ScanTables.IScanTableComp;
import Sets.Alphabet;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class CNFATablesView extends JTable implements PropertyChangeListener{
     private IScanTableComp ScanTables=null;
    
    private DefaultTableModel tb;
    
    public CNFATablesView(){
        super();
        setShowGrid(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);

    }

   public void updateNFATableView(CNFA aNFATableStructure){
          buildNFATableModel(aNFATableStructure);
   } 
   public void buildNFATableModel(CNFA aNFATableStructure){
        //Build a Table model
        //1. Get column names from the alphabet
          int colCount=aNFATableStructure.alphabet().cardinality();
          Object[] columnNames=new Object[colCount+3];
          
          columnNames[0]="STATES";
          columnNames[1]="Accept";
          columnNames[2]="Eps";
                   
          for(int i=3, j=aNFATableStructure.alphabet().nextSetBit(0); j>=0;j=aNFATableStructure.alphabet().nextSetBit(j+1),i++){
			columnNames[i]=(char)j;
          }
          
         //2. Get States
          Object rowData[][] =new Object[aNFATableStructure.stateCount()][columnNames.length];
          for(int vState=0; vState<=aNFATableStructure.stateCount()-1;vState++){
              rowData[vState][0]=vState;
          }
         //3. Get Accepting States
          for(int vState=0;vState<aNFATableStructure.stateCount();vState++){
	      rowData[vState][1]=aNFATableStructure.getOutput(vState);
	  }
          
         //4. Get Epslon data
          for(int vState=0;vState<=aNFATableStructure.stateCount()-1;vState++){
    		if(aNFATableStructure.fwEpsTransitions(vState).size()!=0){
                  rowData[vState][2]=aNFATableStructure.fwEpsTransitions(vState).toString();
                }
          }
         //5. Get row data
         for(int vState=0; vState<=aNFATableStructure.stateCount()-1;vState++){
              for(int i=3, j=aNFATableStructure.alphabet().nextSetBit(0); j>=0;j=aNFATableStructure.alphabet().nextSetBit(j+1),i++){
                  rowData[vState][i]=aNFATableStructure.fwTransitions(vState,(char)j);
              } 
         }
         
          tb = new DefaultTableModel(rowData,columnNames);
        
         //6. Set the model just built as model of NFATableView
          setModel(tb);
     }
  
    public IScanTableComp getScanTables(){
        return ScanTables;
    }

    public void setScanTables(IScanTableComp aScanTables){
        CNFA   vNFATableStructure;
       if(ScanTables!=null){
              ScanTables.removePropertyChangeListener(this);
       }
      ScanTables=aScanTables;
       if(ScanTables!=null){
              ScanTables.addPropertyChangeListener(this);
              vNFATableStructure=ScanTables.getNFATableStructure();
       }else{
              vNFATableStructure= new CNFA(new Alphabet());
       }
       updateNFATableView(vNFATableStructure);
    }    
        
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        if(source==ScanTables){
            updateNFATableView(ScanTables.getNFATableStructure());
        }
    }
    
    public void clear(){
        CNFA vNFAStructure=new CNFA(new Alphabet());
        updateNFATableView(vNFAStructure);
}
}
