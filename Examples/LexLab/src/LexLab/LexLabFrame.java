/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LexLab;


import GrammarNotions.RegExpr.CRE;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class LexLabFrame extends javax.swing.JFrame {
   
   
   CRE vRE;
   
   
   
    /**
     * Creates new form ParserVisualizerFrame
     */
    public LexLabFrame() {
        initComponents();
        Grammar.setVisible(false);
        Scanner.setVisible(false);
        setTitle("Lexical Analysis Lab");
        //txtInput.setText(" ");
        //programEditor.setText(" ");
        disableButtons();
             
    }

    private void disableButtons(){
        cmdScan.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPane = new javax.swing.JSplitPane();
        viewPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        GrammarView = new Components.Concrete.GrammarViewer.CGrammarViewer();
        jPanel6 = new javax.swing.JPanel();
        TreeView = new Components.GraphView.CGraphView();
        jPanel10 = new javax.swing.JPanel();
        FAGraph = new Components.GraphView.CGraphView();
        jPanel11 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        NFA = new Components.Lexical.ScanTablesView.CScanTablesView();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DFA = new Components.Lexical.ScanTablesView.CScanTablesView();
        jPanel4 = new javax.swing.JPanel();
        SymbolTable = new Components.Lexical.SymbolStreamView.CSymbolStreamView();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtInput = new javax.swing.JTextArea();
        cmbInput = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        programEditor = new Components.Lexical.RichTextEditor.CRichTextEditor();
        Grammar = new Components.Concrete.Grammar.CGrammarComp();
        Scanner = new Components.Lexical.Scanners.TableDriven.CDFAScannerComp();
        jPanel9 = new javax.swing.JPanel();
        cmdAnalyze = new javax.swing.JButton();
        cmdScan = new javax.swing.JButton();
        cmdDoAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPane.setDividerLocation(200);
        MainPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("LexLab is a simple application which provides insight in regular expressions (REs) and their analysis (Nullable, First, etc.), as well as their mapping to equivalent NFAs and DFAs by means of Thompson’s and subset construction algorithms.\n\nUsage:\n1. From the Input menu, either select Regular Expression (to practice with a single RE) or Lexical Grammar (to practice with a set of regular expressions).\n2. Use the Parse button to validate the RE or lexical grammar. For the lexical grammar,  a set of lexemes and terminal definitions appear in the tabsheet Grammar. You may inspect the First of each terminal.\n3. Click button NFA. An NFA is constructed. Its transition table and the corresponding graph appears in tabsheet NFA (use scrollbars to see all entries).\n4. Click button DFA. A DFA equivalent to the NFA is constructed. Its transition table appears in tabsheet DFA.\n5. Entera string to scan in the text editor (bottom-left).\n6. Click button Scan. The scanner reads the string. Using the DFA table it identifies the symbols in the input string. A Symbol table which shows the recorgnized symbols appears in tabsheet Symbol Table.\n7. By clicking button DoAll. the steps 2 to 6 are performed in sequence.\n\nWARNING: This version of the program only serves to illustrate the Thompson’s and subset construction algorithms. It is not yet a fully functional application, it may produce unfore seen errors.");
        jScrollPane5.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        );

        viewPane.addTab("Help", jPanel1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GrammarView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(GrammarView, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        viewPane.addTab("Grammar", jPanel7);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(TreeView, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TreeView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        );

        viewPane.addTab("Syntax tree", jPanel6);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 641, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FAGraph, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FAGraph, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
        );

        viewPane.addTab("NFA Graph", jPanel10);

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jLabel2.setText("NFA:");

        NFA.setFAType("NFA");
        NFA.setScanner(Scanner);
        jScrollPane1.setViewportView(NFA);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
        );

        jSplitPane2.setTopComponent(jPanel2);

        jLabel4.setText("DFA:");

        DFA.setFAType("DFA");
        DFA.setScanner(Scanner);
        jScrollPane2.setViewportView(DFA);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(jPanel12);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jSplitPane2)
                .addGap(0, 0, 0))
        );

        viewPane.addTab("FA Tables", jPanel11);

        SymbolTable.setScanner(Scanner);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SymbolTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SymbolTable, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        );

        viewPane.addTab("Symbol Table", jPanel4);

        MainPane.setRightComponent(viewPane);

        jSplitPane1.setDividerLocation(350);

        txtInput.setColumns(20);
        txtInput.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        txtInput.setRows(5);
        jScrollPane6.setViewportView(txtInput);

        cmbInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Regular Expression", "Lexical Grammar" }));
        cmbInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInputActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Input:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbInput, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel5);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Input String:");

        jScrollPane7.setViewportView(programEditor);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel8);

        MainPane.setLeftComponent(jSplitPane1);

        Scanner.setGrammar(Grammar);

        cmdAnalyze.setText("Analyze");
        cmdAnalyze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAnalyzeActionPerformed(evt);
            }
        });

        cmdScan.setText("Scan");
        cmdScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdScanActionPerformed(evt);
            }
        });

        cmdDoAll.setText("DoAll");
        cmdDoAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDoAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(cmdAnalyze)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdScan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDoAll)
                .addContainerGap(223, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdAnalyze, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
            .addComponent(cmdScan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cmdDoAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        cmdAnalyze.getAccessibleContext().setAccessibleParent(jPanel9);
        cmdScan.getAccessibleContext().setAccessibleParent(jPanel9);
        cmdDoAll.getAccessibleContext().setAccessibleParent(jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Scanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Grammar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(556, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(MainPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 462, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Scanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Grammar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(MainPane)
                    .addGap(26, 26, 26)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 457, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel9.getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAnalyzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAnalyzeActionPerformed
        // TODO add your handling code here:
         int i=cmbInput.getSelectedIndex();
        if(i==0){
           parseRE();
           fa();
                   
        }else if(i==1){
            parseLexGrammar();
            viewPane.setEnabledAt(1,true);
            viewPane.setEnabledAt(2,false);
            viewPane.setSelectedIndex(1);
        }
        
    }//GEN-LAST:event_cmdAnalyzeActionPerformed

    private void cmdScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdScanActionPerformed
        // TODO add your handling code here:
        scan();
        viewPane.setSelectedIndex(5);
    }//GEN-LAST:event_cmdScanActionPerformed

    private void cmdDoAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDoAllActionPerformed
        // TODO add your handling code here:
         int i=cmbInput.getSelectedIndex();
        if(i==0){
           parseRE();
                     
        }else if(i==1){
            parseLexGrammar();
           
        }
        fa();
        scan();
    }//GEN-LAST:event_cmdDoAllActionPerformed

    private void cmbInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInputActionPerformed
        // TODO add your handling code here:
         clearViews();
         disableButtons();
         int i=cmbInput.getSelectedIndex();
         if(i==1){
             txtInput.setText("[Lexemes]\n" +
"Lower='a'-'z'\n" +
"Upper='A'-'Z'\n" +
"Letter=Lower|Upper\n" +
"Digit='0'-'9'\n" +
"RealDot='.'\n" +
"Digits=Digit+.(RealDot.Digit+)?\n" +
"Name=Letter.(Letter|Digit)*\n" +
"PrimType=\"int\"|\"char\"\n" +
"RelOp= '<'|'>'|\"<=\"|\">=\"|\"==\"|\"!=\"\n" +
"BoolConst=\"true\"|\"false\"\n" +
"Aop= '+'|'-'\n" +
"Mop='*'|'/'\n" +
"\n" +
"[Terminals]\n" +
"class=\"class\"\n" +
"copen='{'\n" +
"cend='}'\n" +
"brkts=\"()\"\n" +
"while=\"while\"\n" +
"do=\"do\"\n" +
"if=\"if\"\n" +
"then=\"then\"\n" +
"else=\"else\"\n" +
"do=\"do\"\n" +
"equal='='\n" +
"scolon=';'\n" +
"type=PrimType\n" +
"num=Digits\n" +
"void=\"void\"\n" +
"open='('\n" +
"close=')'\n" +
"ass=\":=\"\n" +
"begin=\"begin\"\n" +
"end=\"end\"\n" +
"bool=BoolConst\n" +
"rel=RelOp\n" +
"Aop=Aop\n" +
"Mop=Mop\n" +
"id=Name\n" +
"endmarker=$\n" +
"\n" +
"[Start]\n" +
"startexpr=.endmarker\n" +
"");
programEditor.setText("class pay{\n" +
"  int items;\n" +
"  int pay;\n" +
"  void computePay(){\n" +
"    if(item<10)\n" +
"      pay=1000;\n" +
"    else\n" +
"      pay=items * 10;\n" +
"  }\n" +
"}");
         }else if(i==0){
             txtInput.setText(" ");
             programEditor.setText(" ");
         }
    }//GEN-LAST:event_cmbInputActionPerformed
    private void parseRE(){
            viewPane.setEnabledAt(1,false);
            viewPane.setEnabledAt(2,true);
            String reStr=txtInput.getText();
            if(reStr.isEmpty()){
                  JOptionPane.showMessageDialog(null, "Please enter a Regular Expression","LexLab warning",
                  JOptionPane.WARNING_MESSAGE);
            }else{
                viewPane.setSelectedIndex(2);
                vRE=Grammar.RETextToTree(reStr);
                if(vRE!=null){
                    TreeView.updateParserVisualizer(vRE);
               }
                cmdScan.setEnabled(false);
        
           }
    }
    private void parseLexGrammar(){
        String s=txtInput.getText();
            if(!s.isEmpty() || s!=null){
                Grammar.setGrammarText(s);
                GrammarView.setGrammar(Grammar);
                cmdScan.setEnabled(true);
            }else{
                  JOptionPane.showMessageDialog(null, "Please enter a Lexical Grammar","LexLab warning",
                  JOptionPane.WARNING_MESSAGE);
            }
    }
    private void fa(){
         int i=cmbInput.getSelectedIndex();
        if(i==0){
            Scanner.updateScanTables(vRE);
            FAGraph.setRegularExpresion(vRE);
            cmdScan.setEnabled(true);
        }else if(i==1){
           
            cmdScan.setEnabled(true);
        }
        
    }
    
    private void scan(){
        String s="";
         int i=cmbInput.getSelectedIndex();
       if(i==0 || i==1){
           programEditor.setScanner(Scanner);
           s=programEditor.getText();
           if(s.length()==0){
               JOptionPane.showMessageDialog(null, "Please enter a String to scan","LexLab warning",
                  JOptionPane.WARNING_MESSAGE);
           }else Scanner.createSymbolStream(s);
        }else if(i==1){
           programEditor.setScanner(Scanner);
           s=programEditor.getText();
           if(s.length()==0){
              JOptionPane.showMessageDialog(null, "Please enter a String to scan","LexLab warning",
                  JOptionPane.WARNING_MESSAGE);
           }else Scanner.createSymbolStream(s);
          
        } 
    }
    private void clearViews(){
         SymbolTable.clear();
         NFA.clear();
         DFA.clear();
         TreeView.setRegularExpresion(null);
         GrammarView.clear();
         //programEditor.setText(" ");
         //txtInput.setText(" ");
         viewPane.setSelectedIndex(0);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LexLabFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LexLabFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LexLabFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LexLabFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LexLabFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.Lexical.ScanTablesView.CScanTablesView DFA;
    private Components.GraphView.CGraphView FAGraph;
    private Components.Concrete.Grammar.CGrammarComp Grammar;
    private Components.Concrete.GrammarViewer.CGrammarViewer GrammarView;
    private javax.swing.JSplitPane MainPane;
    private Components.Lexical.ScanTablesView.CScanTablesView NFA;
    public Components.Lexical.Scanners.TableDriven.CDFAScannerComp Scanner;
    private Components.Lexical.SymbolStreamView.CSymbolStreamView SymbolTable;
    private Components.GraphView.CGraphView TreeView;
    private javax.swing.JComboBox cmbInput;
    private javax.swing.JButton cmdAnalyze;
    private javax.swing.JButton cmdDoAll;
    private javax.swing.JButton cmdScan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTextArea jTextArea1;
    public Components.Lexical.RichTextEditor.CRichTextEditor programEditor;
    public javax.swing.JTextArea txtInput;
    private javax.swing.JTabbedPane viewPane;
    // End of variables declaration//GEN-END:variables
     
}
