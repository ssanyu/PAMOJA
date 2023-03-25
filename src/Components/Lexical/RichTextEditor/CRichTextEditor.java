/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.RichTextEditor;





import Components.CoreHybridEditor.ICoreHybridEditorComp;
import Components.IPAMOJAComponent;
import Components.Lexical.SymbolStyleCustomizer.ISymbolStyleCustomizerComp;
import Components.Lexical.Scanners.IScannerComp;
import Components.Lexical.Stream2Text.ISymbolstream2TextComp;
import Components.Specifications.Presentation.IPresentationComp;
import SymbolStream.CSymbolStream;
import SymbolStream.Symbol;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


/**
 * This component is a text editor with facilities for different font and color attributes. It uses a Scanner
 * component to scan its text and the SymbolStyleCustomizer component
 * to render the recognized symbols. When it observes changes in
 * scanner or SymbolStyleCustomizer it re-renders its text.
 * 
 * @see Components.Lexical.Scanners.TableDriven.CDFAScannerComp
 * @see Components.Lexical.SymbolStyleCustomizer.CSymbolStyleCustomizerComp
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CRichTextEditor extends JTextPane implements IRichTextEditor,IPAMOJAComponent,PropertyChangeListener,DocumentListener{
     private String Text=" ";
     private boolean Highlighting;
     private IScannerComp Scanner;
     private ISymbolStyleCustomizerComp SymbolStyleCustomizer;
     private StyledDocument doc;
     private ISymbolstream2TextComp StreamtoText;
     private ICoreHybridEditorComp hybridEditor;
     private IPresentationComp presentation;  

    /**
     * Creates an instance of a RichTextView.
     */
    public CRichTextEditor(){
            super();
            this.setBorder( BorderFactory.createLineBorder(new Color(204,204,255), 2 ) );
            doc=this.getStyledDocument();
            getDocument().addDocumentListener(this);
            this.setFont(new Font("Monospaced",Font.PLAIN,16));
            
    }

    @Override
    public String getText(){
           Text=super.getText();
           return Text ;
    }
    @Override
    public void setText(String aText){
            Text=aText;
            
            super.setText(Text);

            scanText();
            
    }
    
    /**
     *
     * @return
     */
    public boolean isHighlighting(){
        return Highlighting;
    }

    /**
     *
     * @param aHighlighting
     */
    public void setHighlighting(boolean aHighlighting){
        Highlighting=aHighlighting;
        scanText();
    }
    
    /**
     *
     * @return
     */
    public IScannerComp getScanner(){
            return Scanner;
        }

    /**
     *
     * @param aScanner
     */
    public void setScanner(IScannerComp aScanner){
            if(Scanner!=null){
                Scanner.removePropertyChangeListener(this);
            }
            Scanner=aScanner;
            if(Scanner!=null){
                 Scanner.addPropertyChangeListener(this);
                 
            }else{ // Scanner not connected, return empty
                
            }
            scanText();
   }    
            
    /**
     *
     * @return
     */
    public ISymbolStyleCustomizerComp getSymbolStyleCustomizer(){
        return SymbolStyleCustomizer;
    }        
   
    /**
     *
     * @param aSymbolStyleCustomizer
     */
    public void setSymbolStyleCustomizer(ISymbolStyleCustomizerComp aSymbolStyleCustomizer){
            if(SymbolStyleCustomizer!=null){
                SymbolStyleCustomizer.removePropertyChangeListener(this);
            }
            SymbolStyleCustomizer=aSymbolStyleCustomizer;
            if(SymbolStyleCustomizer!=null){
                SymbolStyleCustomizer.addPropertyChangeListener(this);
                 
            }else{ // SymbolStyleCustomizer not connected, return empty
                
            }
            scanText();
   } 
   
    /**
     *
     * @return
     */
    public IPresentationComp getPresentation() {
        return presentation;
    }

    /**
     *
     * @param aPresentation
     */
    public void setPresentation(IPresentationComp aPresentation) {
        if(presentation!=null){
              presentation.removePropertyChangeListener(this);
       }
      presentation=aPresentation;
       if(presentation!=null){
              presentation.addPropertyChangeListener(this);
             
       } else {
            presentation.removePropertyChangeListener(this);
       }
       
       scanText();
    }

    /**
     *
     * @return
     */
    public ICoreHybridEditorComp getHybridEditor(){
        return hybridEditor;
    }
 
    /**
     *
     * @param aHybridEditor
     */
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
       setText(text);
       
   }
   
    /**
     *
     * @return
     */
    public ISymbolstream2TextComp getStreamtoText(){
        return StreamtoText;
    } 

    /**
     *
     * @param aStreamtoText
     */
    public void setStreamtoText(ISymbolstream2TextComp aStreamtoText){
       String vText;
            if(StreamtoText!=null){
                StreamtoText.removePropertyChangeListener(this);
            }
            StreamtoText=aStreamtoText;
            if(StreamtoText!=null){
                StreamtoText.addPropertyChangeListener(this);
                vText=StreamtoText.getText();
            }else{ // StreamtoText not connected, return empty
                vText="";
            }
            setText(vText);
   }    
       
   /**
     * Handles property change events from Scanner, SymbolStyleCustomizer, Presentation, Stream2Text and CoreHybridEditor Components. If the property change is from:
     * <ul>
     * <li> <code>Scanner</code>, <code>SymbolStyleCustomizer</code> or <code>Presentation</code>   - (re)scans the input string.</li>
     * <li> <code>StreamText</code>, <code>CoreHybridEditor</code> - updates its text view</li>
     * </ul>
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        Object source=evt.getSource();
        String property=evt.getPropertyName();
        if((source==Scanner && property.equals("Scanner")) || source==SymbolStyleCustomizer || source==presentation){
           scanText();
        }else if(source==StreamtoText){
            setText(StreamtoText.getText());
        }else if(source==hybridEditor && property.equals("text")){
            setText(hybridEditor.getText());
        }
    }
       
    /**
     * Scans the input string.
     */
    public void scanText(){
       if(!getText().isEmpty() && Scanner!=null ){
           if(Highlighting==true && (SymbolStyleCustomizer!=null || presentation!=null)){
               scanTextWithHighlighting();
           }else{
              scanTextWithOutHighlighting();
             
           } 
       }
   }

    /**
     * Scans input string without highlighting.
     */
    public void scanTextWithOutHighlighting(){
    Scanner.createSymbolStream(getText());
    CSymbolStream vList=Scanner.getSymbolStream();
    Runnable doHighlight = new Runnable() {
        @Override
        public void run() {
            
            SimpleAttributeSet symAttributes;
            ArrayList<Symbol> vSymList;
            Symbol vSym;
            Color vColor;
            Font vFontStyles;
            int vSymStart;
            int vSymLength;

            for(int i=0;i<vList.getSymbolStreamSize();i++){
                vSymList=vList.getSymbolStream().get(i);
                for (Symbol vSymList1 : vSymList) {
                    symAttributes=new SimpleAttributeSet();
                    vSym = vSymList1;
                    vSymStart=vSym.symStart();
                    vSymLength=vSym.symLength();
                    vColor=Color.black;
                    //System.out.println(vSym.symName() +" "+ "("+symStart+","+symLength+") "+ vColor.toString());
                    vFontStyles=new Font("Monospaced",Font.PLAIN,13);
                    StyleConstants.setFontFamily(symAttributes, vFontStyles.getFamily());
                    StyleConstants.setFontSize(symAttributes,vFontStyles.getSize());
                    StyleConstants.setForeground(symAttributes, vColor);
                    //StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);
                    doc.setCharacterAttributes(vSymStart, vSymLength, symAttributes, true);
                   
                }
           }
   
    }
    };       
    SwingUtilities.invokeLater(doHighlight);
  }

    /**
     * Scans input string with highlighting.
     */
    public void scanTextWithHighlighting(){
     
    Scanner.createSymbolStream(getText());
    CSymbolStream vList=Scanner.getSymbolStream();
    Runnable doHighlight = new Runnable() {
        @Override
        public void run() {
           SimpleAttributeSet symAttributes;
           ArrayList<Symbol> vSymList;
           Symbol vSym;
           Color vColor;
           Font vFontStyles;
           int vSymStart;
           int vSymLength;
           if(SymbolStyleCustomizer!=null){
           for(int i=0;i<vList.getSymbolStreamSize();i++){
                vSymList=vList.getSymbolStream().get(i);
                for (Symbol symList1 : vSymList) {
                    symAttributes=new SimpleAttributeSet();
                    vSym = symList1;
                    vSymStart=vSym.symStart();
                    vSymLength=vSym.symLength();
                    vColor=SymbolStyleCustomizer.symbolNameToColor(vSym.symName());
                    //System.out.println(vSym.symName() +" "+ "("+symStart+","+symLength+") "+ vColor.toString());
                    vFontStyles=SymbolStyleCustomizer.symbolNameToFont(vSym.symName());
                    StyleConstants.setFontFamily(symAttributes, vFontStyles.getFamily());
                    StyleConstants.setFontSize(symAttributes,vFontStyles.getSize());
                    if(vFontStyles.getStyle()==1){
                        StyleConstants.setBold(symAttributes, true);
                    }else if(vFontStyles.getStyle()==2){
                        StyleConstants.setItalic(symAttributes, true); 
                    } else if(vFontStyles.getStyle()==3){
                        StyleConstants.setBold(symAttributes, true);
                        StyleConstants.setItalic(symAttributes, true);
                    }
                    StyleConstants.setForeground(symAttributes,vColor);
                    doc.setCharacterAttributes(vSymStart, vSymLength, symAttributes, false);
                }
           }
           } else if(presentation!=null){
               for(int i=0;i<vList.getSymbolStreamSize();i++){
                vSymList=vList.getSymbolStream().get(i);
                for (Symbol symList1 : vSymList) {
                    symAttributes=new SimpleAttributeSet();
                    vSym = symList1;
                    vSymStart=vSym.symStart();
                    vSymLength=vSym.symLength();
                    vColor=presentation.symbolNameToColor(vSym.symName());
                    vFontStyles=presentation.symbolNameToFont(vSym.symName());
                    StyleConstants.setFontFamily(symAttributes, vFontStyles.getFamily());
                    StyleConstants.setFontSize(symAttributes,vFontStyles.getSize());
                    if(vFontStyles.getStyle()==1){
                        StyleConstants.setBold(symAttributes, true);
                    }else if(vFontStyles.getStyle()==2){
                        StyleConstants.setItalic(symAttributes, true); 
                    } else if(vFontStyles.getStyle()==3){
                        StyleConstants.setBold(symAttributes, true);
                        StyleConstants.setItalic(symAttributes, true);
                    }
                    StyleConstants.setForeground(symAttributes,vColor);
                    doc.setCharacterAttributes(vSymStart, vSymLength, symAttributes, false);
                }
           }
           }
    }
    };       
    SwingUtilities.invokeLater(doHighlight);
  }
  @Override
  public void insertUpdate(DocumentEvent devt) {
    scanText();
    if(hybridEditor!=null)
     hybridEditor.updateText(Text);
  }

 
     @Override
   public void removeUpdate(DocumentEvent de) {
    scanText();
    if(hybridEditor!=null)
      hybridEditor.updateText(Text);
   }
     @Override
   public void changedUpdate(DocumentEvent de) {}
   
}
