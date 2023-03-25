/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.Patterns;

import Components.IPAMOJAComponent;
import Patterns.CPatterns;

/**
 * Defines services for interacting with other components ( like, AST2BoxTree component).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IPatternsComp extends IPAMOJAComponent {
      /**
      *  Returns the internal representation of  pattern definitions
      * 
      * @return the CPatterns object.
      */
   public CPatterns getPatternsStructure();

    /**
     * Sets the internal representation of  pattern definitions
     * 
     * @param aPatternsStructure the patterns to set.
     */
    public void setPatternsStructure(CPatterns aPatternsStructure);
   
   
   /**
    * Returns the textual representation of pattern definitions
    * 
     * @return a String of patterns.
    */
   public String getPatternsText();

    /**
     * Sets the value of PatternsText and notifies all observers about <code>PatternsText</code> property change.
     *
     * @param aPatternsText the patterns in text form.
     */
    public void setPatternsText(String aPatternsText);
   
     /**
     * Get the value of MenuPatternsStructure
     *
     * @return the value of MenuPatternsStructure
     */
    public CPatterns getMenuPatternsStructure();

      /**
     * Set the value of MenuPatternsStructure
     *
     * @param aMenuPatternsStructure new value of MenuPatternsStructure
     */
    public void setMenuPatternsStructure(CPatterns aMenuPatternsStructure);
   
    /**
     * Get the text representation of menu patterns.
     * 
     * @return a string representation of menu patterns.
     */
    public String getMenuPatternsText();

    /**
     * Sets the value of MenuPatternsText and notifies all observers about <code>MenuPatternsText</code> property change.
     *
     * 
     * @param aMenuPatternsText the menu patterns in text form.
     */
    public void setMenuPatternsText(String aMenuPatternsText);
    
}
