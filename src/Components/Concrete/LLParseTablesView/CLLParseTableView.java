/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.LLParseTablesView;


import Components.Concrete.ParseTables.LLParseTables.ILLParseTableComp;
import TableGenerators.LL.LLTable.CLLTable;
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * A table view for the LL(1) parse tables.
 * When linked to <code>LLParseTableComp</code> or or <code>LLParser</code>, it observes changes in the parse tables and updates
 * its view.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CLLParseTableView extends JTable implements PropertyChangeListener{
    private ILLParseTableComp LLParseTable=null;
    
    int colCount=0;
    
    private DefaultTableModel tb;
    
    /**
     * Creates a new instance of LLParseTableView.
     */
    public CLLParseTableView(){
        super();
        setShowGrid(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);
        setEnabled(false);
        setCellColor(-1,-1);
    }

    /**
     * Updates this view with the specified <code>aLLTableStructure</code>.
     * @param aLLTableStructure the specified  LL table structure.
     */
    public void updateLLParseTableView(CLLTable aLLTableStructure){
        if(aLLTableStructure!=null){
            buildLLParseTableModel(aLLTableStructure);
        }
    } 
    
    /**
     * Builds a table model with the specified <code>aLLTableStructure</code>.
     * @param aLLTableStructure the specified  LL table structure.
     */
    public void buildLLParseTableModel(CLLTable aLLTableStructure){
        //Build a Table model
        String vEntry="";
        //1. Get column names from the alphabet
         if(LLParseTable!=null){
          colCount=LLParseTable.getTermAlphabet().size();
          Object[] columnNames=new Object[colCount+1];
          columnNames[0]=" ";
          for(int i=1,k=0;i<columnNames.length;i++,k++){
              columnNames[i]=LLParseTable.getTermAlphabet().get(k);
          }
        //2. Get nonterminals
          Object rowData[][] =new Object[aLLTableStructure.getNonTerminals().size()][columnNames.length];
          for(int k=0; k<aLLTableStructure.getNonTerminals().size();k++){
              rowData[k][0]=aLLTableStructure.getNonTerminals().get(k);
          }
        //3. Get row data
          
          for(int n=0; n<aLLTableStructure.getNonTerminals().size();n++){
              for(int i=1,k=0; i<=aLLTableStructure.getTerminals().size()&& k<=aLLTableStructure.getTerminals().size()-1;i++,k++){
                  vEntry=aLLTableStructure.getTable()[n][k];
                  if(vEntry.equals("Error"))
                     rowData[n][i]="";
                  else rowData[n][i]=vEntry;
              } 
          }
                 
          tb = new DefaultTableModel(rowData,columnNames);
        
         //4. Set the model just built as model of LLTableView
          setModel(tb);
         }
     }

    

    /**
     * Get the value of LLParseTable
     *
     * @return the value of LLParseTable
     */
    public ILLParseTableComp getLLParseTable(){
        return LLParseTable;
    }

   /**
  * Links to <code>LLParseTable</code> component via its interface -- Set the value of <code>LLParseTable</code>.
  * Register for property change events and retrieve current value of this LLParseTable object and update the view.
  * 
  * @param aLLParseTable new value of LLParseTable
  */
    public void setLLParseTable(ILLParseTableComp aLLParseTable){
       CLLTable vLLTableStructure=null;
       if(LLParseTable!=null){
           LLParseTable.removePropertyChangeListener(this);
       }
       LLParseTable=aLLParseTable;
       if(LLParseTable!=null){
           LLParseTable.addPropertyChangeListener(this);
           vLLTableStructure=LLParseTable.getTableStructure();
       }
       updateLLParseTableView(vLLTableStructure);
    }
      /**
     * Receives property change events and handles them. If the property change is from the <code>LLParseTable</code> or <code>LLParser</code> component.
     * Retrieve the internal structure of its parse tables and update this view. 
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source= evt.getSource();
        if(source==LLParseTable){
            updateLLParseTableView(LLParseTable.getTableStructure());
        }
    }
    
    public void setCellColor(int r,int col){
       setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
               if(column==0) {
                   c.setBackground(Color.LIGHT_GRAY);
                             
               }else if(row==r && column==col+1){
                   c.setBackground(Color.magenta);
               }else c.setBackground(Color.WHITE);
                 repaint();
                return c;
            }
      });
      
   }
   
  /*  @Override
     public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component result = super.prepareRenderer(renderer, row, column);
        if (column==0) {
            result.setBackground(Color.LIGHT_GRAY);
        }else  
            result.setBackground(Color.WHITE);
        return result;
    }*/
    
  
  
 




  

    
}
