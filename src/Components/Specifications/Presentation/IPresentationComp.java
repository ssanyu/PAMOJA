/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Specifications.Presentation;
import Components.Abstract.Patterns.CPatternsComp;
import Components.IPAMOJAComponent;
import Nodes.CNode;
import java.awt.*;
/**
 * Interface providing services for collaborating with other components.
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IPresentationComp extends IPAMOJAComponent {

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

    /**
     * Returns an instance of a Patterns subcomponent.
     * @return CPatternsComp object.
     */
    CPatternsComp getPatternsComp();

    /**
     * Returns a string representation of pattern layouts.
     * @return a string representation of pattern layouts.
     */
    String getPatternLayouts();

    /**
     * Returns a string representation of pattern layouts for menu items.
     * @return a string representation of pattern layouts for menu items.
     */
    String getMenuPatternLayouts();

    /**
     * Returns a patterns node object.
     * @return CNode object for patterns.
     */
    CNode getNode();
}
