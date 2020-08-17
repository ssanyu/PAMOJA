/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Specifications.Presentation;

/**
 * This class defines a symbol category with its corresponding font and color attributes.
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CategoryAttributes {

    /**
     *
     */
    public String FCategory;

    /**
     *
     */
    public FCAttributes FAttributes;

    /**
     *
     * @param aCategory
     * @param aAttributes
     */
    public CategoryAttributes(String aCategory,FCAttributes aAttributes){
        FCategory=aCategory;
        FAttributes=aAttributes;
    }

    @Override
    public String toString(){
        return "["+FCategory+"]\n"+FAttributes.toString()+"\n";
    }

}
