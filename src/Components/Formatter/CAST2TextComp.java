/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Formatter;

import Boxes.CBox;
import Components.Abstract.AST2BoxTree.CAST2BoxTreeComp;
import Components.Abstract.BoxTree2Stream.CBoxTree2StreamComp;
import Components.CPAMOJAComponent;
import Components.Lexical.Stream2Text.CSymbolstream2TextComp;
import Components.Specifications.Language.ILanguageComp;
import Components.Specifications.Presentation.IPresentationComp;
import Nodes.CNode;
import SymbolStream.CSymbolStream;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * A composite component with a mapping from AST to formatted text. 
 * <p>
 * A AST2Text component consists of an assembly of  AST2BoxTree, BoxTree2Stream and Stream2Text subcomponents.
 * It observes both a language and a presentation component, and maintains
 * consistency between its language specifications and that of the former, and between its presentation style and that of the later.
 * 
 * 
 * @see Components.Abstract.AST2BoxTree.CAST2BoxTreeComp
 * @see Components.Abstract.BoxTree2Stream.CBoxTree2StreamComp
 * @see Components.Lexical.Stream2Text.CSymbolstream2TextComp
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CAST2TextComp extends CPAMOJAComponent implements IAST2TextComp,PropertyChangeListener{
    private CAST2BoxTreeComp asttoBoxtreeComp;
    private CBoxTree2StreamComp boxTreetoStreamComp;
    private CSymbolstream2TextComp symbolstreamToTextComp;
    private ILanguageComp language;
    private IPresentationComp presentation;
    
    
    private CNode ast;
    private CBox boxTree;
    private String text; 
    private CSymbolStream symbolStream;
   
    /**
     * Creates an instance of AST2Text component.
     */
    public CAST2TextComp() {
        super();
        ast=null;
        boxTree=null;
        text=new String();
        symbolStream=new CSymbolStream();
        asttoBoxtreeComp=new CAST2BoxTreeComp();
        boxTreetoStreamComp = new CBoxTree2StreamComp();
        symbolstreamToTextComp=new CSymbolstream2TextComp();
        boxTreetoStreamComp.setASTtoBoxTree(asttoBoxtreeComp);
        symbolstreamToTextComp.setBoxTreetoSymbolStream(boxTreetoStreamComp);
        
    }

    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("AST2Text",10, 10);
    }

    /**
     *
     * @return
     */
    @Override
    public CNode getAst() {
        return ast;
    }

    /**
     *
     * @param ast
     */
    @Override
    public void setAst(CNode ast) {
        this.ast = ast;
        createFormattedText();
    }

    @Override
    public CSymbolStream getSymbolStream() {
        return symbolStream;
    }

    /**
     *
     * @param symbolStream
     */
    public void setSymbolStream(CSymbolStream symbolStream) {
        this.symbolStream = symbolStream;
    }

    /**
     *
     * @return
     */
    @Override
    public CBox getBoxTree() {
        return boxTree;
    }

    /**
     * Sets the specified boxtree to this Formatter component.
     * @param boxTree the specified boxtree
     */
    public void setBoxTree(CBox boxTree) {
        this.boxTree = boxTree;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * Sets the specified text to this formatter and notifies observers.
     * @param text the specified text.
     */
    public void setText(String text) {
         //keep the old value
        String oldText=this.text;
        //get the new value
        this.text=text;
       
        support.firePropertyChange("text",oldText,this.text);
                
    }
    
    /**
     *
     * @return
     */
    public ILanguageComp getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(ILanguageComp aLanguage) {
        if(language!=null){
              language.removePropertyChangeListener(this);
       }
       language=aLanguage;
       if(language!=null){
              language.addPropertyChangeListener(this);
             
       } else {
            language.removePropertyChangeListener(this);
       }
      updateLanguageEnabledComponents(language);
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
    @Override
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
      updatePresentationEnabledComponents(presentation);
    }
   /**
    * Helper method to pass a reference to the Language component to Stream2Text subcomponent.
    * @param language reference to the Language component 
    */
    private void updateLanguageEnabledComponents(ILanguageComp language){
        symbolstreamToTextComp.setGrammar(language.getGrammarComp());
        createFormattedText();
    }
    /**
    * Helper method to pass a reference to the Presentation component to AST2BoxTree subcomponent.
    * @param presentation reference to the Presentation component 
    */
    private void updatePresentationEnabledComponents(IPresentationComp presentation){
        asttoBoxtreeComp.setPatterns(presentation.getPatternsComp());
        createFormattedText();
    }
     
    /**
     * Maps an AST to formatted text.
     */
    private void createFormattedText(){
        if(ast!=null && presentation!=null && presentation.getPatternsComp().getPatternsStructure()!=null && language!=null &&  language.getGrammarComp().getGrammarStructure()!=null){
            asttoBoxtreeComp.setASTNode(ast);
            setBoxTree(asttoBoxtreeComp.getBoxTree());
            setSymbolStream(boxTreetoStreamComp.getSymbolStream());
            setText(symbolstreamToTextComp.getText());
           
        }
    }
    
    /**
     * Handle property change events from Language and Presentation Components. If the property change is from:
     * <ul>
     * <li> <code>Language</code> - retrieve the Language object and passes its value to Stream2Text subcomponent</li>
     * <li> <code>Presentation</code> - retrieve the Presentation object and passes its value to AST2BoxTree subcomponent</li>
     * </ul>
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        if(source==language){
              updateLanguageEnabledComponents(language);
        }else if (source==presentation){
              updatePresentationEnabledComponents(presentation);
            
        }
    }
}
