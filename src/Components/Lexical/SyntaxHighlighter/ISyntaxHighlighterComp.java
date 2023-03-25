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
import Components.Lexical.RichTextEditor.CRichTextEditor;
import Components.Lexical.Scanners.CScannerComp;
import Components.Lexical.SymbolStyleCustomizer.ISymbolStyleCustomizerComp;
import Components.Specifications.Language.ILanguageComp;
import Components.Specifications.Presentation.IPresentationComp;
import SymbolStream.CSymbolStream;

/**
 * An interface providing services for interacting with other components like, the Language, Presentation and CoreHybridEditor components.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ISyntaxHighlighterComp extends IPAMOJAComponent {

    /**
     * Returns the text of this SyntaxHighlighter.
     * @return String object.
     */
    String getText();

    /**
     * Sets the given text to this SyntaxHighlighter.
     * @param aText the given String object.
     */
    void setText(String aText);
   
   

    /**
     * Returns the symbolstream.
     * @return  CSymbolStream object.
     */
       CSymbolStream getSymbolStream();

    /**
     * Sets the given symbolstream.
     * @param aSymbolStream the symbolstream to set.
     */
    void setSymbolStream(CSymbolStream aSymbolStream);
    
    /**
     * Connects to the SymbolStyleCustomizer component.
     * @param aSymbolStyleCustomizer ISymbolStyleCustomizerComp
     */
    void setSymbolStyleCustomizer(ISymbolStyleCustomizerComp aSymbolStyleCustomizer);
   
    /**
     * Connects to the Language component.
     * @param aLanguage ILanguageComp
     */
    void setLanguage(ILanguageComp aLanguage);
   
    /**
     * COnnects to the Presentation component.
     * @param presentation IPresentationComp
     */
    void setPresentation(IPresentationComp presentation);
   
    /**
     * Connects to a CoreHybridEditor component.
     * @param aHybridEditor ICoreHybridEditorComp
     */
    void setHybridEditor(ICoreHybridEditorComp aHybridEditor);

    /**
     * Returns a reference to a CoreHybridEditor component.
     * @return a reference to a CoreHybridEditor component.
     */
    ICoreHybridEditorComp getHybridEditor();
   
     /**
     * Returns the NFA tables for this SyntaxHighlihter.
     * @return CNFA object.
     */
    CNFA getNFATables();

    /**
     *Returns the DFA tables for this SyntaxHighlihter.
     * @return DNFA object.
     */
    CDFA getDFATables();
   
     /**
     * Returns the scanner component for this SyntaxHighlihter.
     * @return CScannerComp object.
     */
    CScannerComp getScanner();

    /**
     * Returns a RichTextView object.
     * @return CRichTextEditor
     */
    CRichTextEditor getRichTextView();

    /**
     * Sets the specified RichTextView object to this SyntaxHighlighter.
     * @param RichTextView CRichTextEditor object
     */
    void setRichTextView(CRichTextEditor RichTextView);
}
