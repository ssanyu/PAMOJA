/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Specifications.Presentation;

/**
 * Defines objects representing a symbol and its category.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class SymbolCategory {

    /**
     *
     */
    public String FSymbolName;

    /**
     *
     */
    public String FCategory;

    /**
     *
     * @param FSymbolName
     * @param FCategory
     */
    public SymbolCategory(String FSymbolName, String FCategory) {
        this.FSymbolName = FSymbolName;
        this.FCategory = FCategory;
    }

    @Override
    public String toString(){
         return FSymbolName + " = " + FCategory;
    }



}
