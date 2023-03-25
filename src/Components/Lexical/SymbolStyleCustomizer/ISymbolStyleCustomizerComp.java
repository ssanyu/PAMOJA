/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.SymbolStyleCustomizer;
import Components.IPAMOJAComponent;
import java.awt.*;
/**
 * Interface providing services for interacting with other components, like Grammar, Language and RichTextView.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ISymbolStyleCustomizerComp extends IPAMOJAComponent {

    /**
     * Get the string representation of this symbolStyle customizer object.
     * 
     * @return the string value of symbolStyle customizer
     */
        String getSymbolStyleCustomizerText();

    /**
     * Sets the value of symbolStyle customizer text, generates its corresponding internal representation and notifies observers about <code>SymbolStyleCustomizerText</code> property changes.
     * @pre well-formed color and font attributes. See <code>fromText(String aSymbolStyleCustomizer)</code>
     * @param aSymbolStyleCustomizerText the string value of symbolStyle customizer
     */
    void setSymbolStyleCustomizerText(String aSymbolStyleCustomizerText);

     /**
     * Get the internal structure of this symbolStyle customizer object.
     * 
     * @return the internal structure value of symbolStyle customizer
     */
    CSymbolStyleCustomizerStructure getSymbolStyleCustomizerStructure();

    /**
     * Sets the value of symbolStyle customizer internal structure, generates its corresponding string representation and notifies observers about <code>SymbolStyleCustomizerStructure</code> property changes.
     * @param aSymbolStyleCustomizerStructure
     */
    void setSymbolStyleCustomizerStructure(CSymbolStyleCustomizerStructure aSymbolStyleCustomizerStructure);

    /**
     * Returns the color for highlighting the specified symbol-name.
     * 
     * @param aSymbolName the name of a symbol to highlight.
     * @return the color for highlighting the symbol
     */
    public Color symbolNameToColor(String aSymbolName);

    /**
     * Returns the font for highlighting the specified symbol-name.
     * 
     * @param aSymbolName the name of a symbol to highlight.
     * @return the font for highlighting the symbol
     */
    public Font symbolNameToFont(String aSymbolName);

  /* Invariant 1: Either SymbolStyleCustomizerText,SymbolStyleCustomizerStructure= null or
   *              SymbolStyleCustomizerText is equivalent to SymbolStyleCustomizerStructure
   * Invariant 2: if a Grammar!=null, domain of symbols for the Grammar
   *              is equivalent to the domain of symbols recorginized by the SymbolStyleCustomizer.
   */
}
