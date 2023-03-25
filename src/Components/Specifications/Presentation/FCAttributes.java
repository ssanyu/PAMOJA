/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Specifications.Presentation;

import java.awt.Color;
import java.awt.Font;

/** 
 * This class defines the font and color attributes of a symbol.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class FCAttributes {

    /**
     *
     */
    public Font fFont;

    /**
     *
     */
    public Color fColor;

    /**
     *
     * @param fFont
     * @param fColor
     */
    public FCAttributes(Font fFont, Color fColor) {
        this.fFont = fFont;
        this.fColor = fColor;
    }

   
    @Override
    public String toString(){
       StringBuilder ret = new StringBuilder();
       ret.append("Font= ");
       ret.append(fFont.getFamily());
       ret.append(",");
       ret.append(fFont.getSize());
       ret.append(",");
       int fontStyle=fFont.getStyle();
       if(fontStyle==0)
           ret.append("Plain");
       else if(fontStyle==1)
           ret.append("Bold");
       else if(fontStyle==2)
           ret.append("Italic");
       else if(fontStyle==3)
           ret.append("Bold Italic");
       ret.append("\n");
       ret.append("Color = r=");
       ret.append(fColor.getRed());
       ret.append(",g=");
       ret.append(fColor.getGreen());
       ret.append(",b=");
       ret.append(fColor.getBlue());
      
       return ret.toString();

    }

}
