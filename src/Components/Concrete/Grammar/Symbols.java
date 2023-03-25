/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Grammar;

/**
 * Defines a symbol object. The symbol may be a lexeme, terminal or nonterminal.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class Symbols {

    /**
     *
     */
    protected String name;

    /**
     *
     */
    protected String value;

    /**
     * Creates a new symbol.
     */
    public Symbols() {
         name = "";
         value = "";

     }

    /**
     * Creates a new symbol with a given name and value. 
     * 
     * @param aname the name of the symbol.
     * @param avalue the value of the symbol.
     */
    public Symbols(String aname,String avalue){
         name=aname;
         value=avalue;
     }

    /**
     * Returns the name of this symbol.
     * @return name of a symbol,
     */
    public String getName() {
         return name;
     }

    /**
     * Sets the name of a symbol to <code>aName</code>.
     * 
     * @param name the specified name.
     */
    public void setName(String name) {
         this.name = name;
     }

    /**
     * Returns the value of this symbol.
     * 
     * @return the string representation of the symbol's value.
     */
    public String getValue() {
         return value;
     }

   /**
     * Sets the value of this symbol to <code>value</code>.
     * 
     * @param value the string representation of the symbol's value.
     */
    public void setValue(String value) {
         this.value = value;
     }


}
