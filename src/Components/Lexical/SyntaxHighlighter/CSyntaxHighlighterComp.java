/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Lexical.SyntaxHighlighter;

import Automata.NFADFA.CDFA;
import Automata.NFADFA.CNFA;
import Components.CoreHybridEditor.ICoreHybridEditorComp;
import Components.IPAMOJAComponent;
import Components.Lexical.RichTextView.CRichTextView;
import Components.Lexical.Scanners.CScannerComp;
import Components.Lexical.Scanners.IScannerComp;
import Components.Lexical.SymbolStyleCustomizer.ISymbolStyleCustomizerComp;
import Components.Specifications.Language.ILanguageComp;
import Components.Specifications.Presentation.IPresentationComp;
import SymbolStream.CSymbolStream;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A text-based program editor component with support for syntax highlighting. It is composed of three subcomponents: RichTextEditor, 
 * DFAScanner and ScanTables. It observes a change in the lexical grammar-part of a Language component and scans its text and symbol attributes of a Presentation component to
 * render the recorganized symbols.
 * 
 * @see Components.Lexical.Scanners.TableDriven.CDFAScannerComp
 * @see Components.Lexical.ScanTables.CScanTableComp
 * @see Components.Lexical.RichTextView.CRichTextView
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSyntaxHighlighterComp extends javax.swing.JPanel implements Serializable,IPAMOJAComponent,ISyntaxHighlighterComp,PropertyChangeListener{
    
    private String text;
    private boolean highlighting;
    private CNFA NFATables;
    private CDFA DFATables;
    private CSymbolStream symbolStream;
    
    private ILanguageComp language;
    private ISymbolStyleCustomizerComp symbolStyleCustomizer;
    private IPresentationComp presentation;
    private ICoreHybridEditorComp hybridEditor;
    
    
    private PropertyChangeSupport support; 
    
    /**
     * Creates new form CSyntaxHighlighterComp
     */
    public CSyntaxHighlighterComp() {
        support= new PropertyChangeSupport(this); 
        initComponents();
        text=new String();
        highlighting=true;
        NFATables=null;
        DFATables=null;
        symbolStream=new CSymbolStream();
        symbolStream=cDFAScannerComp1.getSymbolStream();
        cDFAScannerComp1.setVisible(false);
        cScanTableComp1.setVisible(false);
        
        RichTextView.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
            }

            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                setSymbolStream(cDFAScannerComp1.getSymbolStream());
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                setSymbolStream(cDFAScannerComp1.getSymbolStream());}
         });   
    }

   /**
     * Determines if text highlighting is enabled.
     * @return <code>true</code> if text highlighting is enabled, else <code>false</code>.
     */
    public boolean isHighlighting() {
        this.highlighting=this.RichTextView.isHighlighting();
        return this.highlighting;
    }

    /**
     * Sets the text highlighting mode to the specified value.
     * 
     * @param highlighting  <code>true</code> if text highlighting, else <code>false</code>.
     */
    public void setHighlighting(boolean highlighting) {
        this.highlighting = highlighting;
        RichTextView.setHighlighting(highlighting);
        setSymbolStream(cDFAScannerComp1.getSymbolStream());
       
    }

   
    public CScannerComp getScanner(){
        if(((IScannerComp)cDFAScannerComp1)!=null){
            return cDFAScannerComp1;
        }else{
            return null;
        }
    }
    
   
    public CNFA getNFATables() {
        NFATables=cScanTableComp1.getNFATableStructure();
        return NFATables;
    }

   
    @Override
    public CDFA getDFATables() {
        DFATables=cScanTableComp1.getDFATableStructure();
        return DFATables;
    }
    
     @Override
    public String getText() {
        this.text=this.RichTextView.getText();
        return this.text;
    }

    
    @Override
    public void setText(String text) {
        this.text = text;
        RichTextView.setText(this.text);
        setSymbolStream(cDFAScannerComp1.getSymbolStream());
       
    }

    @Override
    public CSymbolStream getSymbolStream() {
        if(((IScannerComp)cDFAScannerComp1)!=null){
            symbolStream=cDFAScannerComp1.getSymbolStream();
        }else{
            symbolStream= new CSymbolStream();
        }
        return symbolStream;
    }

   
    @Override
    public void setSymbolStream(CSymbolStream symbolStream){
        // keep the old value
        CSymbolStream oldSymbolStream=this.symbolStream;
        // save the new value
        this.symbolStream=symbolStream;
        // fire the property change event, with a property that has changed
        support.firePropertyChange("symbolStream",oldSymbolStream,this.symbolStream);
    }

   
    @Override
    public void setSymbolStyleCustomizer(ISymbolStyleCustomizerComp aSymbolStyleCustomizer){
            if(symbolStyleCustomizer!=null){
                symbolStyleCustomizer.removePropertyChangeListener(this);
            }
            symbolStyleCustomizer=aSymbolStyleCustomizer;
            if(symbolStyleCustomizer!=null){
                symbolStyleCustomizer.addPropertyChangeListener(this);
                 
            }else{ // SymbolStyleCustomizer not connected, return empty
                
            }
            updateSymbolStyleCustomizerEnabledComponents(symbolStyleCustomizer);
            setSymbolStream(cDFAScannerComp1.getSymbolStream());
   } 
    
  
    @Override
    public void setPresentation(IPresentationComp aPresentation){
            if(presentation!=null){
                presentation.removePropertyChangeListener(this);
            }
            presentation=aPresentation;
            if(presentation!=null){
                presentation.addPropertyChangeListener(this);
                 
            }else{ // SymbolStyleCustomizer not connected, return empty
                
            }
            updatePresentationEnabledComponents(presentation);
            setSymbolStream(cDFAScannerComp1.getSymbolStream());
    } 

    
    @Override
   public void setLanguage(ILanguageComp language){
       if(this.language!=null){
              this.language.removePropertyChangeListener(this);
       }
       this.language=language;
       if(this.language!=null){
              this.language.addPropertyChangeListener(this);
             
       } else {
            this.language.removePropertyChangeListener(this);
       }
       updateLanguageEnabledComponents(this.language);
       setSymbolStream(cDFAScannerComp1.getSymbolStream());
       
    }

   
    public ICoreHybridEditorComp getHybridEditor(){
        return hybridEditor;
    }
 
  
    public void setHybridEditor(ICoreHybridEditorComp aHybridEditor){
       String text=null;
       if(hybridEditor!=null){
              hybridEditor.removePropertyChangeListener(this);
       }
       hybridEditor=aHybridEditor;
       if(hybridEditor!=null){
              hybridEditor.addPropertyChangeListener(this);
              text=hybridEditor.getText();
       } 
       RichTextView.setHybridEditor(aHybridEditor);
       setText(text);
       
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        RichTextView = new Components.Lexical.RichTextView.CRichTextView();
        cScanTableComp1 = new Components.Lexical.ScanTables.CScanTableComp();
        cDFAScannerComp1 = new Components.Lexical.Scanners.TableDriven.CDFAScannerComp();

        jScrollPane1.setViewportView(RichTextView);

        cDFAScannerComp1.setScanTables(cScanTableComp1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cScanTableComp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                .addComponent(cDFAScannerComp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cScanTableComp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cDFAScannerComp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.Lexical.RichTextView.CRichTextView RichTextView;
    private Components.Lexical.Scanners.TableDriven.CDFAScannerComp cDFAScannerComp1;
    private Components.Lexical.ScanTables.CScanTableComp cScanTableComp1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

   
    public CRichTextView getRichTextView() {
        return RichTextView;
    }

   
    public void setRichTextView(CRichTextView RichTextView) {
        this.RichTextView = RichTextView;
    }

    private void updateSymbolStyleCustomizerEnabledComponents(ISymbolStyleCustomizerComp symbolStyleCustomizer){
         RichTextView.setSymbolStyleCustomizer(symbolStyleCustomizer);
         RichTextView.setHighlighting(true);
          
   }
    
   private void updatePresentationEnabledComponents(IPresentationComp presentation){
         RichTextView.setPresentation(presentation);
         RichTextView.setHighlighting(true);
          
   }
   private void updateLanguageEnabledComponents(ILanguageComp aLanguage){
       
         cScanTableComp1.setGrammar(aLanguage.getGrammarComp());
         RichTextView.setScanner(cDFAScannerComp1);
   }
    /**
     * Handles property change events from Language, DFAScanner, Presentation, SymbolStyleCustomizer and HybridEditor.
     * @param evt PropertyChangeEvent
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        String property=evt.getPropertyName();
        if(source==language){
              updateLanguageEnabledComponents(language);
              setSymbolStream(cDFAScannerComp1.getSymbolStream());
        }else if (source==symbolStyleCustomizer){
              updateSymbolStyleCustomizerEnabledComponents(symbolStyleCustomizer);
              setSymbolStream(cDFAScannerComp1.getSymbolStream());
        }else if(source==presentation){
              updatePresentationEnabledComponents(presentation);
              setSymbolStream(cDFAScannerComp1.getSymbolStream());
        }else if(source==hybridEditor && property.equals("text")){
                RichTextView.setHybridEditor(hybridEditor);
                RichTextView.setCaretColor(Color.black);
                setText(hybridEditor.getText());
            
            }
        }
    
    
    // Property change listeners
   @Override
   public synchronized void addPropertyChangeListener(PropertyChangeListener l){
       if(support!=null){
          support.addPropertyChangeListener(l);
       }
   }
   @Override
   public synchronized void removePropertyChangeListener(PropertyChangeListener l){
       if(support!=null){
         support.removePropertyChangeListener(l);
       }
   }

    
}
