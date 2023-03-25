/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.GrammarView;

import Analyzers.CGrammarAnalyzer;
import Components.Concrete.Grammar.IGrammarComp;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.SyntaxExpr.CSE;
import Nodes.CNode;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * This is a view for the grammar with facilities for viewing grammar definitions and grammar analysis information.
 * Grammar definitions can be viewed in two different ways:
 * <ul>
 * <li> As key/value pairs in tabular form.
 * <li> As an abstract syntax tree in tree-like form.
 * </ul>
 * When linked to <code>GrammarComp</code>, it observes changes in the grammar and updates
 * its view facilities.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CGrammarView extends javax.swing.JPanel implements PropertyChangeListener {
     /**
     * A reference to grammar object.
     */
    private IGrammarComp Grammar=null;
     /**
       * A two dimensional data structure used to store lexeme definitions 
       * for displaying in a table.
       * 
       */
    private CLexemesTableModel LexemesTableModel;
    /**
      * A two dimensional data structure used to store terminal definitions 
      * for displaying in a table.
      * 
      */
    private CTerminalsTableModel TerminalsTableModel;
    /**
      * A two dimensional data structure used to store nonterminal definitions 
      * for displaying in a table.
      * 
      */
    private CNonterminalsTableModel NonterminalsTableModel;
    /**
     * A reference to a grammar analyzer object.
     */
    private CGrammarAnalyzer vAnalyzer=new CGrammarAnalyzer();
    
    ListSelectionModel termRowSelectionModel;
    ListSelectionModel nonTermRowSelectionModel;
    ListSelectionModel lexRowSelectionModel;
     /**
     * Creates a new instance of <code>CGrammarView</code>.
     */
    public CGrammarView() {
        LexemesTableModel=new CLexemesTableModel();
        TerminalsTableModel=new CTerminalsTableModel();
        NonterminalsTableModel=new CNonterminalsTableModel();
        initComponents();
        tblLexemes.setShowGrid(true);
        tblTerminals.setShowGrid(true);
        tblNonTerminals.setShowGrid(true);
    }
     /**
      * Receives notification when the row selection changes in lexemes table. 
      * It obtains the lexeme definition in the selected row and displays its analysis information.
      * 
      * @param e  the list selection event
      */  
     private void tblLexemesValueChanged(ListSelectionEvent e) {
        String vSelectedLexeme;
        int selectedRow;
        
        if(!lexRowSelectionModel.isSelectionEmpty()){
            selectedRow = tblLexemes.getSelectedRow();
            vSelectedLexeme = (String)tblLexemes.getValueAt(selectedRow, 0);
            int i=Grammar.getGrammarStructure().getGrammarContext().indexOfLexeme(vSelectedLexeme);
            CRE vBody=Grammar.getGrammarStructure().getGrammarContext().getLexemeBody(i);
            String s="";
            if(vAnalyzer.analysisOfRE(vBody)!=null){
                s=s+vAnalyzer.analysisOfRE(vBody).toString();
            }
            txtAnalysis.setText("RE Analysis for Lexeme : "+vSelectedLexeme+"="+vBody.toText()+"\n"+s);
        }
     }
      /**
      * Receives notification when the row selection changes in terminals table. 
      * It obtains the terminal definition in the selected row and displays its analysis information.
      * 
      * @param e  the list selection event
      */  
      private void tblTerminalsValueChanged(ListSelectionEvent e) {
          int vLength;
          int vSelectedRow;
          String vSelectedTerm;
         // vAnalyzer vAnalyzer=new vAnalyzer();
        
       if(!termRowSelectionModel.isSelectionEmpty()){
            vSelectedRow = tblTerminals.getSelectedRow();
            vSelectedTerm = (String)tblTerminals.getValueAt(vSelectedRow,0);
            vLength=vSelectedTerm.length();
            if(vSelectedTerm.charAt(vLength-1)=='@'){
                vSelectedTerm=vSelectedTerm.substring(0,vLength-1);
            }
            int i=Grammar.getGrammarStructure().getGrammarContext().indexOfTerminal(vSelectedTerm);
            CRE vBody=Grammar.getGrammarStructure().getGrammarContext().getTerminalBody(i);
            String s="";
            if(vAnalyzer.analysisOfRE(vBody)!=null){
                s=s+vAnalyzer.analysisOfRE(vBody).toString();
            }
            txtAnalysis.setText("RE analysis for Terminal : "+vSelectedTerm+"="+vBody.toText()+"\n"+s);
        }
       
      }
      /**
      * Receives notification when the row selection changes in nonterminals table. 
      * It obtains the nonterminal definition in the selected row and displays its analysis information.
      * 
      * @param e  the list selection event
      */  
     private void tblNonTerminalsValueChanged(ListSelectionEvent e) {
        int vSelectedRow;
        String vSelectedNonterm;
                
        if(!nonTermRowSelectionModel.isSelectionEmpty()){
            vSelectedRow= tblNonTerminals.getSelectedRow();
            vSelectedNonterm= (String)tblNonTerminals.getValueAt(vSelectedRow, 0);
            int i=Grammar.getGrammarStructure().getGrammarContext().indexOfNonterminal(vSelectedNonterm);
            CSE vBody =Grammar.getGrammarStructure().getGrammarContext().getNonTerminalDefs().getElt(i).getBody();
            String vAnalysisInfo="";
            vAnalysisInfo=vAnalysisInfo +
                   "\n   ECFG analysis for NonTerminal : "+vSelectedNonterm +
                   "\n\n" + "   "+"Det : " + vAnalyzer.analysisOfECFG(vBody).fELL1Det+
                   "\n   Nullable : "+vAnalyzer.analysisOfECFG(vBody).fNullable+
                   "\n   Empty : "+vAnalyzer.analysisOfECFG(vBody).fEmpty+
                   "\n   Reachable : "+vAnalyzer.analysisOfECFG(vBody).fReachable+
                   "\n   First : "+vAnalyzer.setToString(Grammar.getGrammarStructure(),vAnalyzer.analysisOfECFG(vBody).fFirst)+
                   "\n   Follow : "+vAnalyzer.setToString(Grammar.getGrammarStructure(),vAnalyzer.analysisOfECFG(vBody).fFollow)+
                   "\n   Last : "+vAnalyzer.setToString(Grammar.getGrammarStructure(),vAnalyzer.analysisOfECFG(vBody).fLast);
            txtAnalysis.setText(vAnalysisInfo);
        }   
      }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtStartExpr = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNonTerminals = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTerminals = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLexemes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        trGrammarView = new javax.swing.JTree();
        chkExpandAll = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAnalysis = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setText("Start Expression:");

        jLabel2.setText("Lexemes:");

        jLabel3.setText("Terminals:");

        tblNonTerminals.setModel(NonterminalsTableModel);
        tblNonTerminals.setGridColor(new java.awt.Color(153, 153, 153));
        jScrollPane3.setViewportView(tblNonTerminals);
        tblNonTerminals.setRowSelectionAllowed(true);
        nonTermRowSelectionModel = tblNonTerminals.getSelectionModel();
        nonTermRowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nonTermRowSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                tblNonTerminalsValueChanged(e);
            }
        });

        tblTerminals.setModel(TerminalsTableModel);
        tblTerminals.setGridColor(new java.awt.Color(153, 153, 153));
        jScrollPane2.setViewportView(tblTerminals);
        tblTerminals.setRowSelectionAllowed(true);
        termRowSelectionModel = tblTerminals.getSelectionModel();
        termRowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        termRowSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                tblTerminalsValueChanged(e);
            }
        });

        jLabel4.setText("NonTerminals:");

        tblLexemes.setModel(LexemesTableModel);
        tblLexemes.setGridColor(new java.awt.Color(153, 153, 153));
        //LexemesTableModel.fireTableDataChanged();
        jScrollPane4.setViewportView(tblLexemes);
        tblLexemes.setRowSelectionAllowed(true);
        lexRowSelectionModel = tblLexemes.getSelectionModel();
        lexRowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lexRowSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                tblLexemesValueChanged(e);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        trGrammarView.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                trGrammarViewValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(trGrammarView);

        chkExpandAll.setText("Expand all nodes");
        chkExpandAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkExpandAllActionPerformed(evt);
            }
        });

        jLabel6.setText("Grammar Graph:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(chkExpandAll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkExpandAll))
        );

        chkExpandAll.getAccessibleContext().setAccessibleParent(jPanel4);

        txtAnalysis.setColumns(20);
        txtAnalysis.setRows(5);
        jScrollPane5.setViewportView(txtAnalysis);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        jLabel5.setText("Grammar Analysis:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStartExpr, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(txtStartExpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(11, 11, 11)))
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkExpandAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkExpandAllActionPerformed
        // TODO add your handling code here:
        if(chkExpandAll.isSelected()){
            for (int i = 0; i < trGrammarView.getRowCount(); i++) {
                trGrammarView.expandRow(i);
            }
        }else{
            for (int i = trGrammarView.getRowCount() - 1; i >= 0; i--) {
                trGrammarView.collapseRow(i);
            }
        }
    }//GEN-LAST:event_chkExpandAllActionPerformed
  /**
 * Notifies when the tree selection changes.
 * It obtains the selected node and displays its analysis information.
 * 
 * @param evt the TreeSelectionEvent that describes the change
 */
    
    private void trGrammarViewValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_trGrammarViewValueChanged
        // TODO add your handling code here:
        //vAnalyzer vAnalyzer=new vAnalyzer();
        String s;       
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                       trGrammarView.getLastSelectedPathComponent();
        if (node == null)
               return;
        
        Object trNode = node.getUserObject();
        CNode vNode=(CNode)trNode;
        s=vNode.toString();
        if(vAnalyzer.analysisOfECFG(vNode)!=null){
            s=s+"\n\n"+"ECFG analysis";
            s=s+vAnalyzer.analysisOfECFG(vNode).toText(Grammar.getGrammarStructure());
        }
        if(vAnalyzer.analysisOfRE(vNode)!=null){
            s=s+"\n\n"+"RE analysis";
            s=s+vAnalyzer.analysisOfRE(vNode).toString();
        }
        
        txtAnalysis.setText(s);//vAnalyzer.analysisOfECFG(vNode).fEmpty+" ");
    }//GEN-LAST:event_trGrammarViewValueChanged
  
                                        
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkExpandAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tblLexemes;
    private javax.swing.JTable tblNonTerminals;
    private javax.swing.JTable tblTerminals;
    private javax.swing.JTree trGrammarView;
    private javax.swing.JTextArea txtAnalysis;
    private javax.swing.JTextField txtStartExpr;
    // End of variables declaration//GEN-END:variables
/**
     * Get the value of Grammar
     *
     * @return the value of Grammar
     */
 public IGrammarComp getGrammar(){
        return Grammar;
 }
 /**
  * Links to <code>GrammarComp</code> component via its interface -- Set the value of <code>Grammar</code>.
  * Register for property change events and retrieve current value of this Grammar object and update the view.
  * 
  * @param aGrammar new value of Grammar
  */
   public void setGrammar(IGrammarComp aGrammar){
       CGrammar vGrammarStructure;
       if(Grammar!=null){
              Grammar.removePropertyChangeListener(this);
       }
       Grammar=aGrammar;
       if(Grammar!=null){
              Grammar.addPropertyChangeListener(this);
              vGrammarStructure=Grammar.getGrammarStructure();

       } else {
            vGrammarStructure=new CGrammar();
       }
       updateGrammarView(vGrammarStructure);
    }
    /**
     * Updates the <code>GrammarView</code> with the new value of grammar's internal structure. 
     * @param aGrammarStructure the value of grammar's internal structure.
     */
    public void updateGrammarView(CGrammar aGrammarStructure){
         updateTables(aGrammarStructure);
         updateTreeView(aGrammarStructure);
         txtStartExpr.setText(aGrammarStructure.getStartExpr().toText());
    }
    /**
     * Updates the lexeme, terminals and nonterminals table models with the new value of grammar's internal structure. 
     * @param aGrammarStructure the value of grammar's internal structure.
     */
    public void updateTables(CGrammar aGrammarStructure){
         LexemesTableModel.updateLexicalTable( aGrammarStructure);
         TerminalsTableModel.updateTerminalsTable( aGrammarStructure);
         NonterminalsTableModel.updateNonterminalsTable( aGrammarStructure);
    }
     /**
     * Receives property change events and handles them. If the property change is from the <code>GrammarComp</code> component.
     * Retrieve the internal structure of this grammar and update this grammar view. 
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        Object source=evt.getSource();
        if(source==Grammar)
            updateGrammarView(Grammar.getGrammarStructure());
    }

    /**
     * Maps the specified node to a DefaultMutableTreeNode.
     * 
     * @param aNode the specified node.
     */
     private DefaultMutableTreeNode mapECFGTreeToTreeModel(CNode aNode){
        if (aNode == null){
            return new DefaultMutableTreeNode("nil");
        }else{  // anode != null
            DefaultMutableTreeNode result = new DefaultMutableTreeNode(aNode);
            // Recursively build subtrees
            for (int i = 0; i < aNode.count(); i++){
                result.add(mapECFGTreeToTreeModel(aNode.getNode(i)));
            }
   
            return result;
        }
     }
     
    /**
     * Updates the GrammarView with the specified grammar.
     * 
     * @param aGrammarStructure the specified grammar.
     */
    public void updateTreeView(CGrammar aGrammarStructure){
                // Build a TreeModel from vECFG
        DefaultMutableTreeNode root = mapECFGTreeToTreeModel(aGrammarStructure);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        // Set the model just built as model of jTree1
        trGrammarView.setModel(treeModel);
    }
    /**
     * Removes all elements from the grammarview component
     */
    public void clear(){
        
   }
}
