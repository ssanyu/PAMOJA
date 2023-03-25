package Components.Lexical.ScanTablesView.DFATableView;



import Automata.NFADFA.CDFA;
import Components.Lexical.ScanTables.IScanTableComp;
import Sets.Alphabet;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class CDFATablesView extends JTable implements PropertyChangeListener{
     private IScanTableComp ScanTables=null;
    
    private DefaultTableModel tb;
    
    public CDFATablesView(){
        super();
        setShowGrid(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);
    }

   public void updateDFAScannerTableView(CDFA aDFATableStructure){
          buildDFATableModel(aDFATableStructure);
   } 
   public void buildDFATableModel(CDFA aDFATableStructure){
        //Build a Table model
        //1. Get column names from the alphabet
          int colCount=aDFATableStructure.alphabet().cardinality();
          Object[] columnNames=new Object[colCount+3];
          
          columnNames[0]="STATES";
          columnNames[1]="Accept";
          columnNames[2]="NFA STATES";
          
          for(int i=3, j=aDFATableStructure.alphabet().nextSetBit(0); j>=0;j=aDFATableStructure.alphabet().nextSetBit(j+1),i++){
			columnNames[i]=(char)j;
          }
          
         //2. Get States
          Object rowData[][] =new Object[aDFATableStructure.stateCount()][columnNames.length];
          for(int vState=0; vState<=aDFATableStructure.stateCount()-1;vState++){
              rowData[vState][0]=vState;
          }
         //3. Get Accepting States
          for(int vState=0;vState<aDFATableStructure.stateCount();vState++){
	      rowData[vState][1]=aDFATableStructure.getOutput(vState);
	  }
          
         //3. Get NFA States
          for(int vState=0;vState<aDFATableStructure.stateCount();vState++){
                if(!aDFATableStructure.getNFAStateSet().isEmpty()){
                  rowData[vState][2]=aDFATableStructure.getNFAStateSet().get(vState);
                }
	  } 
        //4. Get row data
         for(int vState=0; vState<=aDFATableStructure.stateCount()-1;vState++){
              for(int i=3, j=aDFATableStructure.alphabet().nextSetBit(0); j>=0;j=aDFATableStructure.alphabet().nextSetBit(j+1),i++){
                  rowData[vState][i]=aDFATableStructure.dTransition(vState, (char)j);
              } 
         }
         
          tb = new DefaultTableModel(rowData,columnNames);
        
         //5. Set the model just built as model of DFATableView
          setModel(tb);
     }
  
    public IScanTableComp getScanTables(){
        return ScanTables;
    }

    public void setScanTables(IScanTableComp aScanTables){
        CDFA   vDFATableStructure;
       if(ScanTables!=null){
              ScanTables.removePropertyChangeListener(this);
       }
      ScanTables=aScanTables;
       if(ScanTables!=null){
              ScanTables.addPropertyChangeListener(this);
              vDFATableStructure=ScanTables.getDFATableStructure();
       }else{
              vDFATableStructure= new CDFA(new Alphabet());
       }
       updateDFAScannerTableView(vDFATableStructure);
    }    
        
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        if(source==ScanTables){
            updateDFAScannerTableView(ScanTables.getDFATableStructure());
        }
    }
    
    public void clear(){
        CDFA vDFAStructure=new CDFA(new Alphabet());
        updateDFAScannerTableView(vDFAStructure);
}  
}
