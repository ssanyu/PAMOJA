/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SymbolStyleCustomizerCustomizer.java
 *
 * Created on Nov 2, 2010, 11:02:54 AM
 */

package Components.Lexical.SymbolStyleCustomizer;


import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.DefaultCellEditor;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


/**
 * A PropertyEditor which allows users to edit the internal structure of a symbolstylecustomizer. 
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class SymbolStyleCustomizerEditor extends javax.swing.JPanel {

    /**
     *
     */
    public  ListComboCategoryModel lcModel;
    /**
     * This is the list of all font families on the system
     */
    private String[] fontFamilies;

    /**
     *
     */
    public SymbolToCategoryTableModel tableModel;

    /**
     *
     */
    public CSymbolStyleCustomizerStructure SymbolStyleCustomizerStructure;
  
    
    /** Creates new form SymbolStyleCustomizerCustomizer */
    public SymbolStyleCustomizerEditor() {
       
        lcModel = new ListComboCategoryModel();
        tableModel = new SymbolToCategoryTableModel();
        // Figure out what fonts are available on the system
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontFamilies = env.getAvailableFontFamilyNames();
        initComponents();
        SymbolStyleCustomizerStructure=new CSymbolStyleCustomizerStructure();
        tblSymbolClassification.getModel().addTableModelListener(this::tblSymbolClassificationTableChanged);
        setUpCategoryColumn(tblSymbolClassification.getColumnModel().getColumn(1));
        
        /** tblSymbolClassification.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        tblSymbolClassification.setSurrendersFocusOnKeystroke(true);
        TableColumn hidden = tblSymbolClassification.getColumnModel().getColumn(SymbolToCategoryTableModel.HIDDEN_INDEX);
        hidden.setMinWidth(2);
        hidden.setPreferredWidth(2);
        hidden.setMaxWidth(2);**/
     }

    
     /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTPSymbolStyleCustomizer = new javax.swing.JTabbedPane();
        jPSymbolClassification = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSymbolClassification = new javax.swing.JTable();
        jPColorandFonts = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxtCategory = new javax.swing.JTextField();
        cmdAdd = new javax.swing.JButton();
        lblCategory = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbFontName = new javax.swing.JComboBox(fontFamilies);
        jLabel4 = new javax.swing.JLabel();
        cmbFontSize = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPreview = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        cmbEffects = new javax.swing.JComboBox();
        btnColor = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCategory = new javax.swing.JList(lcModel);
        cmbFontStyle = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();

        jTPSymbolStyleCustomizer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        tblSymbolClassification.setModel(tableModel);
        jScrollPane4.setViewportView(tblSymbolClassification);

        javax.swing.GroupLayout jPSymbolClassificationLayout = new javax.swing.GroupLayout(jPSymbolClassification);
        jPSymbolClassification.setLayout(jPSymbolClassificationLayout);
        jPSymbolClassificationLayout.setHorizontalGroup(
            jPSymbolClassificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );
        jPSymbolClassificationLayout.setVerticalGroup(
            jPSymbolClassificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
        );

        jTPSymbolStyleCustomizer.addTab("Symbol Classification", jPSymbolClassification);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("New Category:");

        cmdAdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmdAdd.setText("Add");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        lblCategory.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCategory.setText("Category:");

        btnRemove.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Preview:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Font:");

        cmbFontName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFontNameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Size:");

        cmbFontSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22" }));
        cmbFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFontSizeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Font Style:");

        txtPreview.setText("Sample Text   Sample Text");
        jScrollPane2.setViewportView(txtPreview);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Color:");

        cmbEffects.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "Underline", "Strikethrough", " " }));
        cmbEffects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEffectsActionPerformed(evt);
            }
        });

        btnColor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnColor.setText("Color");
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });

        lstCategory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstCategory.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstCategoryValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstCategory);

        cmbFontStyle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Plain", "Bold", "Italic", "Bold Italic" }));
        cmbFontStyle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFontStyleActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Effects:");

        txtColor.setEditable(false);

        javax.swing.GroupLayout jPColorandFontsLayout = new javax.swing.GroupLayout(jPColorandFonts);
        jPColorandFonts.setLayout(jPColorandFontsLayout);
        jPColorandFontsLayout.setHorizontalGroup(
            jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPColorandFontsLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCategory)
                    .addGroup(jPColorandFontsLayout.createSequentialGroup()
                        .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPColorandFontsLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPColorandFontsLayout.createSequentialGroup()
                                .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPColorandFontsLayout.createSequentialGroup()
                                .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPColorandFontsLayout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbEffects, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPColorandFontsLayout.createSequentialGroup()
                                        .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbFontStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPColorandFontsLayout.createSequentialGroup()
                                                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnColor))
                                                .addComponent(cmbFontName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(14, Short.MAX_VALUE))))))
            .addGroup(jPColorandFontsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(btnRemove)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPColorandFontsLayout.setVerticalGroup(
            jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPColorandFontsLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdAdd))
                .addGap(8, 8, 8)
                .addComponent(lblCategory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPColorandFontsLayout.createSequentialGroup()
                        .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbFontName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbFontStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnColor)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPColorandFontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cmbEffects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTPSymbolStyleCustomizer.addTab("Fonts and Colors", jPColorandFonts);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPSymbolStyleCustomizer, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPSymbolStyleCustomizer, javax.swing.GroupLayout.PREFERRED_SIZE, 360, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        // TODO add your handling code here:
        String vCategory=jtxtCategory.getText();
        //User didn't type in a unique category...
        if (vCategory.equals("") || lcModel.contains(vCategory)) {
                JOptionPane.showMessageDialog(new SymbolStyleCustomizerEditor(),"Category Exists or Empty.",
                                    "Error", JOptionPane.ERROR_MESSAGE);

                jtxtCategory.requestFocusInWindow();
                jtxtCategory.selectAll();
                
         }else{
            addCategoryToList(vCategory);
            //Reset the text field.
            jtxtCategory.requestFocusInWindow();
            jtxtCategory.setText("");
        }
       
    }//GEN-LAST:event_cmdAddActionPerformed
    
    private void addCategoryToList(String aCategory){
        lcModel.addElement((String)aCategory);
        SymbolStyleCustomizerStructure.addCategoryAndAttributes(aCategory,new Font("Monospaced",Font.PLAIN,12),Color.BLACK);
       //Select the new item and make it visible.
        int lstsize = lcModel.getSize();
        lstCategory.setSelectedIndex(lstsize-1);
        lstCategory.ensureIndexIsVisible(lstsize-1);
        
    }
    private void doSelection(){
        String vSelectedCategory=(String)lstCategory.getSelectedValue();
        Font vFont=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(SymbolStyleCustomizerStructure.indexOfCategory(vSelectedCategory)).FAttributes.fFont;
       cmbFontName.setSelectedItem(vFont.getFamily());
        cmbFontSize.setSelectedItem(""+vFont.getSize());
      if(vFont.getStyle()==0)
               cmbFontStyle.setSelectedIndex(0);
        else if(vFont.getStyle()==1)
            cmbFontStyle.setSelectedIndex(1);
        else if(vFont.getStyle()==2)
              cmbFontStyle.setSelectedIndex(2);
      else if(vFont.getStyle()==3)
             cmbFontStyle.setSelectedIndex(3);
        Color vColor=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(SymbolStyleCustomizerStructure.indexOfCategory(vSelectedCategory)).FAttributes.fColor;
       txtColor.setBackground(vColor);

       txtPreview.setFont(vFont);
       txtPreview.setForeground(vColor);
    }
    
    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
         //This method can be called only ifthere's a valid selection
            //so go ahead and remove whatever's selected.
            int index = lstCategory.getSelectedIndex();
            String vSelectedCategory=(String)lstCategory.getSelectedValue();
            SymbolStyleCustomizerStructure.getFCategoryToAttributes().remove(SymbolStyleCustomizerStructure.indexOfCategory(vSelectedCategory));
            lcModel.remove(index);
            int lstsize = lcModel.getSize();
            if (lstsize == 0) { //No item in the list, disable remove button
                btnRemove.setEnabled(false);

            } else { //Select an index.
                if (index == lcModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                lstCategory.setSelectedIndex(index);
                lstCategory.ensureIndexIsVisible(index);
            }

    }//GEN-LAST:event_btnRemoveActionPerformed

    private void cmbFontNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFontNameActionPerformed
        // TODO add your handling code here:
          if (lstCategory.getSelectedIndex() != -1) { //if there is a selection
              String vCategory=(String)lstCategory.getSelectedValue();
              Font vFont=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(SymbolStyleCustomizerStructure.indexOfCategory(vCategory)).FAttributes.fFont;
              int fontStyle=vFont.getStyle();
              int fontSize=vFont.getSize();
              String fontName=(String)cmbFontName.getSelectedItem();
              Color vColor=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(SymbolStyleCustomizerStructure.indexOfCategory(vCategory)).FAttributes.fColor;
              vFont = new Font(fontName,fontStyle,fontSize);
              SymbolStyleCustomizerStructure.getFCategoryToAttributes().remove( SymbolStyleCustomizerStructure.indexOfCategory(vCategory));
              SymbolStyleCustomizerStructure.addCategoryAndAttributes(vCategory,vFont,vColor);
              txtPreview.setFont(vFont);
          }
      
    }//GEN-LAST:event_cmbFontNameActionPerformed

    private void cmbFontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFontSizeActionPerformed
        // TODO add your handling code here:
        if (lstCategory.getSelectedIndex() != -1) { //if there is a selection
              String vCategory=(String)lstCategory.getSelectedValue();
              Font vFont=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(SymbolStyleCustomizerStructure.indexOfCategory(vCategory)).FAttributes.fFont;
              String fontName=vFont.getFamily();
              int fontStyle=vFont.getStyle();
              int fontSize= Integer.parseInt((String)cmbFontSize.getSelectedItem());
              Color vColor=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(SymbolStyleCustomizerStructure.indexOfCategory(vCategory)).FAttributes.fColor;
              vFont = new Font(fontName,fontStyle,fontSize);
              SymbolStyleCustomizerStructure.getFCategoryToAttributes().remove( SymbolStyleCustomizerStructure.indexOfCategory(vCategory));
              SymbolStyleCustomizerStructure.addCategoryAndAttributes(vCategory,vFont,vColor);
              txtPreview.setFont(vFont);
          }
 
    }//GEN-LAST:event_cmbFontSizeActionPerformed

    private void cmbFontStyleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFontStyleActionPerformed
        // TODO add your handling code here:
        if (lstCategory.getSelectedIndex() != -1) { //if there is a selection
              String vCategory=(String)lstCategory.getSelectedValue();
              Font vFont=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(SymbolStyleCustomizerStructure.indexOfCategory(vCategory)).FAttributes.fFont;
              String fontName=vFont.getFamily();
              int fontStyle=cmbFontStyle.getSelectedIndex();//vFont.getStyle();
              int fontSize= Integer.parseInt((String)cmbFontSize.getSelectedItem());
              Color vColor=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(SymbolStyleCustomizerStructure.indexOfCategory(vCategory)).FAttributes.fColor;
              vFont = new Font(fontName,fontStyle,fontSize);
              SymbolStyleCustomizerStructure.getFCategoryToAttributes().remove( SymbolStyleCustomizerStructure.indexOfCategory(vCategory));
              SymbolStyleCustomizerStructure.addCategoryAndAttributes(vCategory,vFont,vColor);
              txtPreview.setFont(vFont);
          }
   
    }//GEN-LAST:event_cmbFontStyleActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        // TODO add your handling code here:
        Color bgColor = JColorChooser.showDialog(this,"Choose Font Color",
                                 getForeground());
        if (bgColor != null && lstCategory.getSelectedIndex() != -1  ){
            String vCategory=(String)lstCategory.getSelectedValue();
            Font vFont=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(SymbolStyleCustomizerStructure.indexOfCategory(vCategory)).FAttributes.fFont;
            SymbolStyleCustomizerStructure.getFCategoryToAttributes().remove( SymbolStyleCustomizerStructure.indexOfCategory(vCategory));
            SymbolStyleCustomizerStructure.addCategoryAndAttributes(vCategory,vFont,bgColor);
            txtPreview.setForeground(bgColor);
            txtColor.setBackground(bgColor);
        }
        
        
    }//GEN-LAST:event_btnColorActionPerformed

    private void lstCategoryValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstCategoryValueChanged
        // TODO add your handling code here:
        if (evt.getValueIsAdjusting() == false) {

            if (lstCategory.getSelectedIndex() == -1) {
            //No selection, disable remove button.
                btnRemove.setEnabled(false);

            } else {
            //Selection, enable the remove button.
                btnRemove.setEnabled(true);
                doSelection();
            }
        }
   
    }//GEN-LAST:event_lstCategoryValueChanged

private void cmbEffectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEffectsActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_cmbEffectsActionPerformed
 
    /**
     *
     * @param categoryColumn
     */
    public final void setUpCategoryColumn(TableColumn categoryColumn) {
       //Set up the editor for the category cells. 
        JComboBox comboBox = new JComboBox(lcModel);
        categoryColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }

    /**
     *
     * @param e
     */
    public void tblSymbolClassificationTableChanged(TableModelEvent e) {
       String vSymbol=" ";
        String vCategory=" ";
        int row = e.getFirstRow();
        TableModel model = (TableModel)e.getSource();
        vSymbol = (String)model.getValueAt(row, 0);
        vCategory=(String)model.getValueAt(row,1);
        int vIndex=SymbolStyleCustomizerStructure.indexOfSymbol(vSymbol);
        if(vIndex!=-1){
           SymbolStyleCustomizerStructure.getFSymbolToCategory().remove(vIndex);
           SymbolStyleCustomizerStructure.getFSymbolToCategory().add(vIndex,new SymbolCategory(vSymbol,vCategory));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox cmbEffects;
    private javax.swing.JComboBox cmbFontName;
    private javax.swing.JComboBox cmbFontSize;
    private javax.swing.JComboBox cmbFontStyle;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPColorandFonts;
    private javax.swing.JPanel jPSymbolClassification;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTPSymbolStyleCustomizer;
    public javax.swing.JTextField jtxtCategory;
    private javax.swing.JLabel lblCategory;
    public javax.swing.JList lstCategory;
    private javax.swing.JTable tblSymbolClassification;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextPane txtPreview;
    // End of variables declaration//GEN-END:variables

}
