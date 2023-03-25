/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Lexical.Stream2Text;

import Components.Abstract.BoxTree2Stream.IBoxTree2StreamComp;
import Components.Concrete.Grammar.IGrammarComp;
import Components.IPAMOJAComponent;
import Components.Lexical.SymbolStream.ISymbolStreamComp;


/**
 * An interface for Stream2Text component.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ISymbolstream2TextComp extends IPAMOJAComponent {

    /**
     * Returns text.
     * @return the text 
     */
    String getText();

    /**
     * Sets the specified text to this Stream2Text component and notifies observers.
     * @param aText the specified text
     */
    void setText(String aText);
    
    /**
     * Returns a reference to a SymbolStream component.
     * 
     * @return a reference to a SymbolStream component.
     */
    ISymbolStreamComp getSymbolStream();

    /**
     * COnnects to a SymbolStream component via the specified interface.
     * 
     * @param aSymbolStream the specified interface.
     */
    void setSymbolStream(ISymbolStreamComp aSymbolStream);
    
    /**
     * Returns a reference to BoxTree2Stream component.
     * @return a reference to BoxTree2Stream component.
     */
    IBoxTree2StreamComp getBoxTreetoSymbolStream();

    /**
     * Connects to a BoxTree2Stream component via the specified interface.
     * @param aBoxTreetoSymbolStream the specified interface.
     */
    void setBoxTreetoSymbolStream(IBoxTree2StreamComp aBoxTreetoSymbolStream);
    
    /**
     * Returns a reference to Grammar component.
     * @return a reference to Grammar component.
     */
    IGrammarComp getGrammar();

    /**
     * Connects to a Grammar component via the specified interface.
     * @param aGrammar the specified interface.
     */
    void setGrammar(IGrammarComp aGrammar);
   
   
    
}
