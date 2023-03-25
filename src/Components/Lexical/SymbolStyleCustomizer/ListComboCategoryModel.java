/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.SymbolStyleCustomizer;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;

/** 
 * An implementation of a table model for Category attributes.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class ListComboCategoryModel extends DefaultListModel implements ComboBoxModel {

    /**
     *
     */
    public Object currentValue;


    @Override
    public void setSelectedItem(Object anItem) {
        currentValue = anItem;
        fireContentsChanged(this, -1, -1);
    }

    @Override
    public Object getSelectedItem() {
        return currentValue;

    }

}

