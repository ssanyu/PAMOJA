/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package Components.Concrete.Grammar;

import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.Grammar.CGrammarContext;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSE_Empty;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * An editor for editing a Grammar in a structure preserving way.
 *
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CGrammarEditor extends javax.swing.JPanel{
    
    /**
     *
     */
    public CGrammar GrammarStructure;
     
    /**
     *
     */
    public CLexemeTableModel LexemeTableModel;

    /**
     *
     */
    public CTerminalTableModel TerminalsTableModel;

    /**
     *
     */
    public CNonTerminalsTableModel NonterminalsTableModel;


    /** Creates new form CGrammarView */
    public CGrammarEditor() {
        GrammarStructure=new CGrammar();
        LexemeTableModel=new CLexemeTableModel();
        TerminalsTableModel=new CTerminalTableModel();
        NonterminalsTableModel=new CNonTerminalsTableModel();
        initComponents();
       
        tblLexemes.setSurrendersFocusOnKeystroke(true);
        TableColumn hidden = tblLexemes.getColumnModel().getColumn(CLexemeTableModel.HIDDEN_INDEX);
        hidden.setMinWidth(2);
        hidden.setPreferredWidth(2);
        hidden.setMaxWidth(2);
        hidden.setCellRenderer(new LexemeInteractiveRenderer(CLexemeTableModel.HIDDEN_INDEX));

        tblTerminals.setSurrendersFocusOnKeystroke(true);
        TableColumn hidden2 = tblTerminals.getColumnModel().getColumn(CTerminalTableModel.HIDDEN_INDEX);
        hidden2.setMinWidth(2);
        hidden2.setPreferredWidth(2);
        hidden2.setMaxWidth(2);
        hidden2.setCellRenderer(new TerminalInteractiveRenderer(CTerminalTableModel.HIDDEN_INDEX));
        
        
        tblNonTerminals.setSurrendersFocusOnKeystroke(true);
        TableColumn hidden3= tblNonTerminals.getColumnModel().getColumn( CNonTerminalsTableModel.HIDDEN_INDEX);
        hidden3.setMinWidth(2);
        hidden3.setPreferredWidth(2);
        hidden3.setMaxWidth(2);
        hidden3.setCellRenderer(new NonTerminalInteractiveRenderer(CNonTerminalsTableModel.HIDDEN_INDEX));
    }

    
    class LexemeInteractiveRenderer extends DefaultTableCellRenderer {
         protected int interactiveColumn;

         public LexemeInteractiveRenderer(int interactiveColumn) {
             this.interactiveColumn = interactiveColumn;
         }
         public void highlightLastRow(int row) {
             int lastrow = LexemeTableModel.getRowCount();
             if (row == lastrow - 1) {
                    tblLexemes.setRowSelectionInterval(lastrow - 1, lastrow - 1);
             } else {
                    tblLexemes.setRowSelectionInterval(row + 1, row + 1);
             }

             tblLexemes.setColumnSelectionInterval(0, 0);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column){
             Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
             if (column == interactiveColumn && hasFocus) {
                 if ((CGrammarEditor.this.LexemeTableModel.getRowCount() - 1) == row &&
                    !CGrammarEditor.this.LexemeTableModel.hasEmptyRow())
                 {
                    CGrammarEditor.this.LexemeTableModel.addEmptyRow();
                 }

                 highlightLastRow(row);
             }

             return c;
         }
     }

    class TerminalInteractiveRenderer extends DefaultTableCellRenderer {
         protected int interactiveColumn;

         public TerminalInteractiveRenderer(int interactiveColumn) {
             this.interactiveColumn = interactiveColumn;
         }
         public void highlightLastRow(int row) {
             int lastrow = TerminalsTableModel.getRowCount();
             if (row == lastrow - 1) {
                    tblTerminals.setRowSelectionInterval(lastrow - 1, lastrow - 1);
             } else {
                    tblTerminals.setRowSelectionInterval(row + 1, row + 1);
             }

             tblTerminals.setColumnSelectionInterval(0, 0);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column){
             Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
             if (column == interactiveColumn && hasFocus) {
                 if ((CGrammarEditor.this.TerminalsTableModel.getRowCount() - 1) == row &&
                    !CGrammarEditor.this.TerminalsTableModel.hasEmptyRow())
                 {
                    CGrammarEditor.this.TerminalsTableModel.addEmptyRow();
                 }

                 highlightLastRow(row);
             }

             return c;
         }
     }

    
    class NonTerminalInteractiveRenderer extends DefaultTableCellRenderer {
         protected int interactiveColumn;

         public NonTerminalInteractiveRenderer(int interactiveColumn) {
             this.interactiveColumn = interactiveColumn;
         }
         public void highlightLastRow(int row) {
             int lastrow = NonterminalsTableModel.getRowCount();
             if (row == lastrow - 1) {
                    tblNonTerminals.setRowSelectionInterval(lastrow - 1, lastrow - 1);
             } else {
                    tblNonTerminals.setRowSelectionInterval(row + 1, row + 1);
             }

             tblNonTerminals.setColumnSelectionInterval(0, 0);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column){
             Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
             if (column == interactiveColumn && hasFocus) {
                 if ((CGrammarEditor.this.NonterminalsTableModel.getRowCount() - 1) == row &&
                    !CGrammarEditor.this.NonterminalsTableModel.hasEmptyRow())
                 {
                    CGrammarEditor.this.NonterminalsTableModel.addEmptyRow();
                 }

                 highlightLastRow(row);
             }

             return c;
         }
     }

    
    
    
    
    
    
    
    
    
    /*
      //Table selection Events
     private void tblLexemesValueChanged(ListSelectionEvent e) {
        String vSelectedLexeme= null;
        int selectedRow = tblLexemes.getSelectedRow();
        vSelectedLexeme = (String)tblLexemes.getValueAt(selectedRow, 0);
        int i=Grammar.getGrammarStructure().getGrammarContext().indexOfLexeme(vSelectedLexeme);
        CRE vBody=Grammar.getGrammarStructure().getGrammarContext().getLexemeBody(i);
        //boolean isNull=vBody.analysis.fNullable;
       // String vFirst=vBody.analysis.fFirst.toString();
      //  txtAnalysis.setText(vSelectedLexeme+"="+vBody.toString()+"\n"+"    Nullable: "+isNull+"\n    First: "+vFirst);
      }*/

    /*  private void tblTerminalsValueChanged(ListSelectionEvent e) {
        int selectedRow = tblTerminals.getSelectedRow();
        String vSelectedTerm = (String)tblTerminals.getValueAt(selectedRow, 0);
        int i=Grammar.getGrammarStructure().getGrammarContext().indexOfTerminal(vSelectedTerm);
        CRE vBody=Grammar.getGrammarStructure().getGrammarContext().getTerminalBody(i);
       
        // boolean isNull=vBody..analysis.fNullable;
       // String vFirst=vBody.analysis.fFirst.toString();
       // txtAnalysis.setText(vSelectedSym+"="+vBody.toString()+"\n"+"    Nullable: "+isNull+"\n    First: "+vFirst);
      }*/
      
   /*  private void tblNonTerminalsValueChanged(ListSelectionEvent e) {
        CGrammarAnalyzer vAnalyzer=new CGrammarAnalyzer();

        int selectedRow = tblNonTerminals.getSelectedRow();
        
        String vSelectedNonterm= (String)tblNonTerminals.getValueAt(selectedRow, 0);
        int i=Grammar.getGrammarStructure().getGrammarContext().indexOfNonterminal(vSelectedNonterm);
        CSE vBody =Grammar.getGrammarStructure().getGrammarContext().getNonTerminalDefs().getElt(i).getBody();
        String vAnalysisInfo="";
        vAnalysisInfo=vAnalysisInfo +
                   "\n   Nullable:"+vAnalyzer.anaECFG(vBody).fNullable+
                   "\n   Empty:"+vAnalyzer.anaECFG(vBody).fEmpty+
                   "\n   Reachable:"+vAnalyzer.anaECFG(vBody).fReachable+
                   "\n   First:"+vAnalyzer.setToString(Grammar.getGrammarStructure(),vAnalyzer.anaECFG(vBody).fFirst)+
                   "\n   Follow:"+vAnalyzer.setToString(Grammar.getGrammarStructure(),vAnalyzer.anaECFG(vBody).fFollow)+
                   "\n   Last:"+vAnalyzer.setToString(Grammar.getGrammarStructure(),vAnalyzer.anaECFG(vBody).fLast);
        txtAnalysis.setText(vAnalysisInfo);
          
      }*/


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTerminals = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNonTerminals = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtStartExpr = new javax.swing.JTextField();
        chkAugment = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLexemes = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();

        jLabel1.setText("Lexemes:");

        jLabel2.setText("Terminals:");

        tblTerminals.setModel(TerminalsTableModel);
        jScrollPane2.setViewportView(tblTerminals);

        tblNonTerminals.setModel(NonterminalsTableModel);
        jScrollPane3.setViewportView(tblNonTerminals);

        jLabel3.setText("NonTerminals:");

        jLabel4.setText("StartExpr:");

        txtStartExpr.setEditable(false);

        chkAugment.setText("Augment Grammar");
        chkAugment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAugmentActionPerformed(evt);
            }
        });

        tblLexemes.setModel(LexemeTableModel);
        jScrollPane1.setViewportView(tblLexemes);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStartExpr, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkAugment)
                        .addGap(12, 12, 12)
                        .addComponent(txt1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, 0, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStartExpr, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAugment, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkAugmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAugmentActionPerformed
        // TODO add your handling code here:
        if(chkAugment.isSelected()){
            GrammarStructure.augmentGrammar();
        }else{
           GrammarStructure.unAugmentGrammar();
        }
        LexemeTableModel.updateLexemeTable(GrammarStructure.getGrammarContext().getLexemeDefs());
        TerminalsTableModel.updateTerminalsTable(GrammarStructure.getGrammarContext().getTerminalDefs());
        NonterminalsTableModel.updateNonTerminalsTable(GrammarStructure.getGrammarContext().getNonTerminalDefs());
        txtStartExpr.setText(GrammarStructure.getStartExpr().toText());
        //GrammarStructure.setGrammarStructure(Grammar.getGrammarStructure());
        //updateGrammarView(Grammar.getGrammarStructure());
    }//GEN-LAST:event_chkAugmentActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //txt1.setText(LexemeTableModel.fData.capacity() +""); 
        ArrayList<Symbols> vData=new ArrayList<>();
        
        //update lexemes
         for(int i=0;i<tblLexemes.getModel().getRowCount();i++){
             vData.add(new Symbols(((String)tblLexemes.getModel().getValueAt(i,0)),((String)tblLexemes.getModel().getValueAt(i,1))));
        }
        LexemeTableModel.updateLexemeDefList(vData);
        
        //update terminals  
        vData=new ArrayList<>();
        for(int i=0;i<tblTerminals.getModel().getRowCount();i++){
             vData.add(new Symbols(((String)tblTerminals.getModel().getValueAt(i,0)),((String)tblTerminals.getModel().getValueAt(i,1))));
        }
        TerminalsTableModel.updateTerminalDefList(vData);
       
        //update Nonterminals  
        vData=new ArrayList<>();
        for(int i=0;i<tblNonTerminals.getModel().getRowCount();i++){
             vData.add(new Symbols(((String)tblNonTerminals.getModel().getValueAt(i,0)),((String)tblNonTerminals.getModel().getValueAt(i,1))));
        }
        NonterminalsTableModel.updateNonterminalsDefList(vData);
        
        //update startExpression
        CGrammarScanner vScanner=new CGrammarScanner();
        CGrammarParser  vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        CSE vStart;
            
        //scan and parse start expr
        vParser.fScanner.setText(txtStartExpr.getText());
        vParser.reSet();
        boolean vSuccess=vParser.parseXSE();
        if(vSuccess){
           vStart=vParser.fSETree;
        }else{
           JOptionPane.showMessageDialog(null,"Error in parsing the definition of Start expression.");
           vStart=new CSE_Empty();
        }
        GrammarStructure=new CGrammar(new CGrammarContext(LexemeTableModel.fDefs,TerminalsTableModel.fDefs, NonterminalsTableModel.fDefs),
               vStart);
        //txt1.setText(TerminalsTableModel.getRowCount()+"");
    }//GEN-LAST:event_jButton1ActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkAugment;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblLexemes;
    private javax.swing.JTable tblNonTerminals;
    private javax.swing.JTable tblTerminals;
    private javax.swing.JTextField txt1;
    public javax.swing.JTextField txtStartExpr;
    // End of variables declaration//GEN-END:variables

 }
