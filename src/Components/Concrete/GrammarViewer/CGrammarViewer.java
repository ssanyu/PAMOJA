/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.GrammarViewer;

import Analyzers.CGrammarAnalyzer;
import Components.Concrete.Grammar.IGrammarComp;
import General.SimpleHeaderRenderer;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.SyntaxExpr.CSE;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
public class CGrammarViewer extends javax.swing.JPanel implements PropertyChangeListener {
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
    public CGrammarViewer() {
        LexemesTableModel=new CLexemesTableModel();
        TerminalsTableModel=new CTerminalsTableModel();
        NonterminalsTableModel=new CNonterminalsTableModel();
        initComponents();
        tblLexemes.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        tblLexemes.setShowGrid(true);
        tblTerminals.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        tblTerminals.setShowGrid(true);
        tblNonTerminals.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
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
                   "\n   Properties for NonTerminal : "+vSelectedNonterm +
                   "\n\n" + "   "+"LL(1) : " + vAnalyzer.analysisOfECFG(vBody).fELL1Det+
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
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTerminals = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNonTerminals = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLexemes = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAnalysis = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Start Expression:");

        jSplitPane1.setDividerLocation(250);

        jSplitPane2.setDividerLocation(100);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Terminals:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
        );

        jSplitPane3.setTopComponent(jPanel2);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("NonTerminals:");

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
        );

        jSplitPane3.setRightComponent(jPanel6);

        jSplitPane2.setBottomComponent(jSplitPane3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Lexemes:");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setLeftComponent(jPanel1);

        jSplitPane1.setLeftComponent(jSplitPane2);

        txtAnalysis.setColumns(20);
        txtAnalysis.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtAnalysis.setRows(5);
        jScrollPane5.setViewportView(txtAnalysis);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Grammar Analysis:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(108, 108, 108))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStartExpr))
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStartExpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1))
        );
    }// </editor-fold>//GEN-END:initComponents
        
                                        
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTable tblLexemes;
    private javax.swing.JTable tblNonTerminals;
    private javax.swing.JTable tblTerminals;
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
         txtStartExpr.setText(aGrammarStructure.getStartExpr().toText());
         txtAnalysis.setText(" ");
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
     *
     */
    public void clear(){
        updateGrammarView(new CGrammar());
        txtStartExpr.setText("");
    }
}
