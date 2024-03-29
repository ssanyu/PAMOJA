/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.SourceCodeGeneratedParser.Deterministic;

import Java.JCompilationUnit;
import Java.JFlattener;
import Java.JMethodDec;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import javax.swing.DefaultListModel;

/**
 * A view for the source-code generated for a deterministic parser.
 * When linked to <code>DeterministicParser</code> component , it observes changes in the generated source-code and updates
 * its view.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CDeterministicParserSourceView extends javax.swing.JPanel implements PropertyChangeListener{
     /**
     * A reference to SourceCodeDeterministicParserComp.
     */
    private ISourceCodeDeterministicParserComp DeterministicParser=null;
    
    private DefaultListModel<String> listModel;
    private JFlattener javaFlatener;
    private CFirstTableModel Nt;
    /**
     * Creates new DeterministicParserSourceView.
     */
    public CDeterministicParserSourceView() {
        listModel = new DefaultListModel<String>();
        Nt=new CFirstTableModel();
        initComponents();
        javaFlatener = new JFlattener();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAllCode = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSym = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblNonTerminals = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstProductions = new javax.swing.JList(listModel);
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtProdCode = new javax.swing.JTextArea();

        jSplitPane2.setDividerLocation(200);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Parser Source");

        txtAllCode.setColumns(20);
        txtAllCode.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        txtAllCode.setRows(5);
        jScrollPane3.setViewportView(txtAllCode);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 158, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(jPanel5);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Symbol Codes");

        txtSym.setColumns(20);
        txtSym.setRows(5);
        jScrollPane4.setViewportView(txtSym);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(jPanel8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.addTab("All code", jPanel1);

        jSplitPane4.setDividerLocation(150);
        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        tblNonTerminals.setModel(Nt);
        tblNonTerminals.setGridColor(new java.awt.Color(153, 153, 153));
        jScrollPane5.setViewportView(tblNonTerminals);

        jSplitPane4.setRightComponent(jScrollPane5);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Production rules:");

        lstProductions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstProductions.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstProductionsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstProductions);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
        );

        jSplitPane4.setLeftComponent(jPanel6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jSplitPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jSplitPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jSplitPane1.setLeftComponent(jPanel3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Generated code:");

        txtProdCode.setColumns(20);
        txtProdCode.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        txtProdCode.setRows(5);
        jScrollPane2.setViewportView(txtProdCode);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        jTabbedPane1.addTab("Code per Rule", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lstProductionsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstProductionsValueChanged
        // TODO add your handling code here:
        if((lstProductions.getModel().getSize()==DeterministicParser.getProdList().size()) && lstProductions.getSelectedIndex()>=0){ 
             if(!evt.getValueIsAdjusting()) { 
                String prod=(String)lstProductions.getSelectedValue();
                DeterministicParser.getProdList().entrySet().stream().forEach((_item) -> {
                    txtProdCode.setText(javaFlatener.f_MethodDeclaration(DeterministicParser.getProdList().get(prod),0));
                });
            }
    }//GEN-LAST:event_lstProductionsValueChanged
    }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList lstProductions;
    private javax.swing.JTable tblNonTerminals;
    private javax.swing.JTextArea txtAllCode;
    private javax.swing.JTextArea txtProdCode;
    private javax.swing.JTextArea txtSym;
    // End of variables declaration//GEN-END:variables

    /**
     * Get the SourceCodeDeterministicParser object.
     * @return the value of the SourceCodeDeterministicParser object
     */
    public ISourceCodeDeterministicParserComp getDeterministicParser() {
        return DeterministicParser;
    }
     
 /**
  * Links to <code>SourceCodeDeterministicParserComp</code> component via its interface -- Set the value of <code>DeterministicParser</code>.
  * Register for property change events and retrieve currently generated source-code of this Deterministic parser object and update the view.
  * 
  * @param aDeterministicParser source-code of the Deterministic parser
  */
   public void setDeterministicParser(ISourceCodeDeterministicParserComp aDeterministicParser){
       JCompilationUnit vParserSourceStructure;
       if(DeterministicParser!=null){
              DeterministicParser.removePropertyChangeListener(this);
       }
       DeterministicParser=aDeterministicParser;
       if(DeterministicParser!=null){
              DeterministicParser.addPropertyChangeListener(this);
              vParserSourceStructure=DeterministicParser.getParserSourceStructure();
              updateSourceCodeView(vParserSourceStructure);
              updateProductionsList(DeterministicParser.getProdList());
              Nt.updateNonterminalsTable(DeterministicParser.getFirstSets());
       }else{
            vParserSourceStructure=new JCompilationUnit("");
            updateSourceCodeView(vParserSourceStructure);
       }
     
    }
     /**
     * Receives property change events and handles them. If the property change is from the <code>SourceCodeDeterministicParser</code> component.
     * Retrieve the source-code and update this view. 
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        if(source==DeterministicParser){
           updateSourceCodeView(DeterministicParser.getParserSourceStructure());
           
           updateProductionsList(DeterministicParser.getProdList());
           Nt.updateNonterminalsTable(DeterministicParser.getFirstSets());
        }
    }
    
    /**
     * Updates this view with the given parser source.
     * 
     * @param aParserSourceStructure the JCompilation unit for this parser.
     */
    public void updateSourceCodeView(JCompilationUnit aParserSourceStructure){
        if(aParserSourceStructure!=null){
           txtAllCode.setText(javaFlatener.f_CompilationUnit(aParserSourceStructure,1));
            txtSym.setText(DeterministicParser.getParserSymCodes());
        }else{
            txtAllCode.setText("");
            txtSym.setText("");
        }
    }
    
    /**
     *
     * @param aProdcutionsList
     */
    public void updateProductionsList(LinkedHashMap<String,JMethodDec> aProdcutionsList){
        listModel.clear();
        lstProductions.setSelectedIndex(-1);
        aProdcutionsList.entrySet().stream().forEach((me) -> {
            listModel.addElement(me.getKey());
        });
       lstProductions.setSelectedIndex(0); 
    }
    
    /**
     * Resets the view.
     */
    public void clear(){
        txtAllCode.setText(" ");
        listModel.clear();
        lstProductions.setSelectedIndex(-1);
        txtProdCode.setText(" ");
    }
    
}
