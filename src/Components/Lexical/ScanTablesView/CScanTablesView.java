package Components.Lexical.ScanTablesView;


import Automata.NFADFA.CDFA;
import Automata.NFADFA.CNFA;
import Components.Lexical.ScanTables.IScanTableComp;
import Components.Lexical.Scanners.TableDriven.IDFAScannerComp;
import Sets.Alphabet;
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class CScanTablesView extends JTable implements PropertyChangeListener{
     private IScanTableComp ScanTables=null;
     private IDFAScannerComp Scanner=null;
     private String FAType;
    
    private DefaultTableModel tb;
    
    /**
     *
     */
    public CScanTablesView(){
        super();
        setShowGrid(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        getTableHeader().setReorderingAllowed(false);
        setColumnColor(0);
        FAType=new String();

    }

    /**
     *
     * @return
     */
    public String getFAType() {
        return FAType;
    }

    /**
     *
     * @param FAType
     */
    public void setFAType(String FAType) {
        this.FAType = FAType;
        
    }
   
    /**
     *
     * @param aNFATableStructure
     */
    public void updateNFATableView(CNFA aNFATableStructure){
       if(aNFATableStructure!=null)
          buildNFATableModel(aNFATableStructure);
   } 

    /**
     *
     * @param aNFATableStructure
     */
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

    /**
     *
     * @return
     */
    public IDFAScannerComp getScanner() {
        return Scanner;
    }

    /**
     *
     * @param aScanner
     */
    public void setScanner(IDFAScannerComp aScanner) {
        CNFA   vNFATableStructure;
        CDFA  vDFATableStructure;
       if(Scanner!=null){
              Scanner.removePropertyChangeListener(this);
       }
       Scanner=aScanner;
       if(Scanner!=null){
              Scanner.addPropertyChangeListener(this);
              vNFATableStructure=Scanner.getNFA();
              vDFATableStructure=Scanner.getDFA();
       }else{
              vNFATableStructure= new CNFA(new Alphabet());
              vDFATableStructure= new CDFA(new Alphabet());
       }
      
       if(FAType.equals("NFA"))
          updateNFATableView(vNFATableStructure);
       else if(FAType.equals("DFA"))
          updateDFAScannerTableView(vDFATableStructure);
       
    }
  
    /**
     *
     * @return
     */
    public IScanTableComp getScanTables(){
        return ScanTables;
    }

    /**
     *
     * @param aScanTables
     */
    public void setScanTables(IScanTableComp aScanTables){
        CNFA   vNFATableStructure;
        CDFA  vDFATableStructure;
       if(ScanTables!=null){
              ScanTables.removePropertyChangeListener(this);
       }
       ScanTables=aScanTables;
       if(ScanTables!=null){
              ScanTables.addPropertyChangeListener(this);
              vNFATableStructure=ScanTables.getNFATableStructure();
              vDFATableStructure=ScanTables.getDFATableStructure();
       }else{
              vNFATableStructure= new CNFA(new Alphabet());
              vDFATableStructure= new CDFA(new Alphabet());
       }
       
       if(FAType.equals("NFA"))
          updateNFATableView(vNFATableStructure);
       else if(FAType.equals("DFA"))
               updateDFAScannerTableView(vDFATableStructure);
       
    }    
        
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        if(source==ScanTables){
            if(FAType.equals("NFA"))
               updateNFATableView(ScanTables.getNFATableStructure());
            else if(FAType.equals("DFA"))
               updateDFAScannerTableView(ScanTables.getDFATableStructure());
         }else if(source==Scanner){
             if(FAType.equals("NFA"))
               updateNFATableView(Scanner.getNFA());
            else if(FAType.equals("DFA"))
               updateDFAScannerTableView(Scanner.getDFA());
         }   
       
    }
    
    /**
     *
     * @param aDFATableStructure
     */
    public void updateDFAScannerTableView(CDFA aDFATableStructure){
        if(aDFATableStructure!=null)
          buildDFATableModel(aDFATableStructure);
   } 

    /**
     *
     * @param aDFATableStructure
     */
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
  
    /**
     *
     */
    public void clear(){
        
        if(FAType.equals("NFA")){
        CNFA vNFAStructure=new CNFA(new Alphabet());
        updateNFATableView(vNFAStructure);
        }else if(FAType.equals("DFA")){
          CDFA vDFAStructure=new CDFA(new Alphabet());
          updateDFAScannerTableView(vDFAStructure);
        }
    }
    private void setColumnColor(int col){
        
       setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
               if(column==0) {
                   c.setBackground(Color.LIGHT_GRAY);
                             
               }else c.setBackground(Color.WHITE);
                 repaint();
                return c;
            }
      });
      
   }
}
