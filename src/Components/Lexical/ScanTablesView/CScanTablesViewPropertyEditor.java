/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.ScanTablesView;

import java.awt.Component;
import java.beans.PropertyEditorSupport;
import javax.swing.JComboBox;

/**
 * A PropertyEditor which allows users to specify the type of FA. 
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CScanTablesViewPropertyEditor extends PropertyEditorSupport {
    private JComboBox editor;
   

    /**
     *
     */
    public CScanTablesViewPropertyEditor() {
       editor=new JComboBox(new String[]{"NFA","DFA"});
       
    }
     @Override
    public Component getCustomEditor() {
        editor.setSelectedItem(0);
        return editor;
    }

    @Override
    public String[] getTags() {
        return new String[]{"NFA","DFA"};
    }

     @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public String getAsText(){
        String s = (String) getValue();
        if (s == null) {
            return "none";
        }
        return s;
    }

    @Override
    public void setAsText(String text){
        setValue(text);
    }

 
    @Override
   public Object getValue() {
           
       return editor.getSelectedItem();
    }
    
    @Override
    public void setValue(Object String) {
       super.setValue(String);
       this.editor.setSelectedItem(String);
       
       
    }

     
    
  @Override
   public String getJavaInitializationString() {
        
       return "\""+(String) getValue()+"\"";
  
}

}

