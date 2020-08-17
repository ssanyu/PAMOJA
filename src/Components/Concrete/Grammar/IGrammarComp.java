/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Concrete.Grammar;

import Components.IPAMOJAComponent;
import GrammarNotions.Grammar.CGrammar;

/**
 * Defines services for interacting with all grammar-dependent components of the framework.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IGrammarComp extends IPAMOJAComponent{
    
   /**
    * Returns the internal structure of this grammar.
    * 
    * @return the internal structure of this grammar
    */
    public CGrammar getGrammarStructure();

    
   /**
     * Sets the value of the internal structure of this grammar, generates its corresponding string representation, performs grammar analysis and notifies observers about <code>GrammarStructure</code> property changes.
     * @param aGrammarStructure
     */
    public void setGrammarStructure(CGrammar aGrammarStructure);
   
   
   /**
     * Returns the string representation of this grammar.
     * 
     * @return the string representation of this grammar
     */
   public String getGrammarText();

    /* 
    * Set the string representation of this grammar.
    * Check well-formedness of the string representation of the grammar.
    * Signal an error if the string representation is invalid else compute the
    * internal representation of the grammar. Also generate analysis
    * information and fire a property change.
    *
    * @param aGrammarText the text representation of the grammar to set.
    * @pre:  Grammar is well-formed(See <code>toText(String aGrammarText)</code>)
    *
    */

    public void setGrammarText(String aGrammarText);
   
   /**
  * Returns <code>true</code> if this grammar is annotated with endmarker symbol, <code>false</code>
  * otherwise.
  *
  * @return <code>true</code> if this grammar is annotated with endmarker symbol, <code>false</code>
  *         otherwise
 */
   public boolean isAugment();
   /**
     * Set the value of Augment and notify observers about <code>Augment</code> property changes.
     *
     * 
     * @param aValue new value of Augment
     */
   public void setAugment(boolean aValue);
   
    /**
     * Returns <code>true</code> if a terminal symbol of this grammar has data, <code>false</code>
     * otherwise.
     * 
     * @param aSym the code of this terminal symbol
     * @return <code>true</code> if this terminal symbol has data, <code>false</code>
     *         otherwise
     */
    public boolean hasData(int aSym);
  
  /**
    * Returns structure representation of RE text.
     * @param aString
     * @return 
    */
 // public CRE RETextToTree(String aString);
  /**
   * Removes all elements from the grammar component
   */
  public void clear();
}
