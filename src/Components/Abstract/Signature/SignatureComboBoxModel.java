/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Abstract.Signature;

import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;

/** 
 * An implementation of a combo box model.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class SignatureComboBoxModel extends DefaultListModel implements ComboBoxModel {

    /**
     *
     */
    public ArrayList<String> strings;

    /**
     *
     */
    public Object currentValue;


     SignatureComboBoxModel(){
       strings=new ArrayList<>();
       
     }

    public void setSelectedItem(Object anItem) {
        currentValue = anItem;
    }

    public Object getSelectedItem() {
        return currentValue;

    }

}

