/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.PanelTree;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A utility class for color shades. It is used to shade the background color of a panel depending on its state.
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class ColorShades {
    private ArrayList<Color> colorShades;

    /**
     * Creates a new ColoShade object.
     */
    public ColorShades() {
        colorShades=new ArrayList<>();
        addColors();
    }
    
    /**
     * Adds color shades to a List
     */
    private void addColors(){
        colorShades.add(new Color(240,240,240));
        colorShades.add(new Color(204,204,204));
        colorShades.add(new Color(153,153,153));
        colorShades.add(new Color(102, 102,102));
        
    }
    
    /**
     * Returns a color at the specified index.
     * 
     * @param colorIndex the index of the color.
     * @return the color at the given index.
     */
    public Color getColor(int colorIndex){
        return colorShades.get(colorIndex-1);
       
    }
    
}
