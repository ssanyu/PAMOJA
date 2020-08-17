/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.Patterns;


import Components.CPAMOJAComponent;
import Components.INodeObject;
import Nodes.CNode;
import Patterns.CPatterns;
import java.awt.Graphics;

/** Holds box layout specifications concerned with producing nicely formatted text.
 *<p>
 * The Box specifications describe how text elements should be laid out, for instance, horizontally, vertically, indented or aligned and how adjacent
 * the text elements should be spaced relative to one another. It notifies its observers of changes in
the box layout specifications.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CPatternsComp extends CPAMOJAComponent implements IPatternsComp,INodeObject {
    
    private CPatterns PatternsStructure;
    private String PatternsText="";
    
    private CPatterns MenuPatternsStructure;
    private String MenuPatternsText="";

    /**
     * Creates a new Patterns component object.
     */
    public CPatternsComp() {
       super();
       PatternsStructure=new CPatterns();
       MenuPatternsStructure=new CPatterns();
      // PatternsText=PatternsStructure.toText();
    }

    /**
     * Paints the UI of the Patterns component object. 
     * 
     * @param g the Patterns component object to paint.
     */  
   @Override
   public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Patterns",5,10);
   }
    
    /**
     * Get the value of PatternsText
     *
     * @return the value of PatternsText
     */
    public String getPatternsText() {
        return PatternsText;
    }

    
    /**
     * Sets the value of PatternsText and notifies all observers about <code>PatternsText</code> property change.
     *
     * @param aPatternsText the patterns in text form.
     */
    @Override
    public void setPatternsText(String aPatternsText) {
       CPatterns vPatternsStructure;
       //keep the old value
       String oldPatternsText=PatternsText;
       //Get the new Value
       PatternsText=aPatternsText; 
       
       //Create internal structure
       vPatternsStructure=CPatterns.fromText(PatternsText);
       
       PatternsStructure=vPatternsStructure;
       //Fire a property change event
       support.firePropertyChange("PatternsText",
                                   oldPatternsText,
                                   PatternsText);
    }

   
    /**
     * Get the value of PatternsStructure
     *
     * @return the value of PatternsStructure
     */
    public CPatterns getPatternsStructure() {
        return PatternsStructure;
    }

    /**
     * Set the value of PatternsStructure
     *
     * @param PatternsStructure new value of PatternsStructure
     */
    public void setPatternsStructure(CPatterns PatternsStructure) {
        this.PatternsStructure = PatternsStructure;
    }

    /**
     * Get the menu patterns.
     * 
     * @return a string representation of menu patterns.
     */
    public String getMenuPatternsText() {
        return MenuPatternsText;
    }

    /**
     * Sets the value of MenuPatternsText and notifies all observers about <code>MenuPatternsText</code> property change.
     *
     * 
     * @param aMenuPatternsText the menu patterns in text form.
     */
    public void setMenuPatternsText(String aMenuPatternsText) {
       CPatterns vMenuPatternsStructure;
       //keep the old value
       String oldMenuPatternsText=MenuPatternsText;
       //Get the new Value
       MenuPatternsText=aMenuPatternsText; 
       
       //Create internal structure
       vMenuPatternsStructure=CPatterns.fromText(MenuPatternsText);
       
       MenuPatternsStructure=vMenuPatternsStructure;
       //Fire a property change event
       support.firePropertyChange("MenuPatternsText",
                                   oldMenuPatternsText,
                                   MenuPatternsText);
    }

    /**
     * Get the value of MenuPatternsStructure
     *
     * @return the value of MenuPatternsStructure
     */
    public CPatterns getMenuPatternsStructure() {
        return MenuPatternsStructure;
    }

     /**
     * Set the value of MenuPatternsStructure
     *
     * @param MenuPatternsStructure new value of MenuPatternsStructure
     */
    public void setMenuPatternsStructure(CPatterns MenuPatternsStructure) {
        this.MenuPatternsStructure = MenuPatternsStructure;
    }

    /**
     *  Return the patterns node object.
     * 
     * @return the node object for the patterns.
     */
    @Override
    public CNode getNode() {
         return PatternsStructure.getfPatternDefs();
    }
    
}
