/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Specifications.Presentation;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 * A PropertyEditor which allows users to edit the internal structure of a SymbolStyleCustomizer. 
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class SymbolStyleCustomizerPropertyEditor extends PropertyEditorSupport {
    private SymbolStyleCustomizerEditor editor = null;
    private CSymbolStyleCustomizerStructure SymbolStyleCustomizerStructure;

    /**
     *
     */
    public SymbolStyleCustomizerPropertyEditor() {
        this.editor = new SymbolStyleCustomizerEditor();
    }
     @Override
    public Component getCustomEditor() {
        return editor;
    }

    @Override
    public String[] getTags() {
        return null;
    }

     @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public String getAsText(){
        return "";
    }

    @Override
    public void setAsText(String text){   
    }

    @Override
    public Object getValue() {
        if(super.getValue()==null){
            setValue(null);
        }
       SymbolStyleCustomizerStructure = (CSymbolStyleCustomizerStructure) super.getValue();
       SymbolStyleCustomizerStructure=new CSymbolStyleCustomizerStructure(editor.SymbolStyleCustomizerStructure.getFCategoryToAttributes(),editor.SymbolStyleCustomizerStructure.getFSymbolToCategory());

      return SymbolStyleCustomizerStructure;
   }
    
    @Override
    public void setValue(Object aSymbolStyleCustomizerStructure) {
       if(aSymbolStyleCustomizerStructure==null){
          aSymbolStyleCustomizerStructure = new CSymbolStyleCustomizerStructure();
       }
      super.setValue(aSymbolStyleCustomizerStructure);
     
    editor.SymbolStyleCustomizerStructure=(CSymbolStyleCustomizerStructure)aSymbolStyleCustomizerStructure;

    //update listcombo model
    editor.lcModel.clear();
    for(int i=0;i< editor.SymbolStyleCustomizerStructure.getFCategoryToAttributes().size();i++){
        editor.lcModel.addElement( editor.SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(i).FCategory);
    }
    editor.lstCategory.setSelectedIndex(0);
    editor.lstCategory.ensureIndexIsVisible(0);
    //Reset the text field.
    editor.jtxtCategory.requestFocusInWindow();
    editor.jtxtCategory.setText("");
    editor.tableModel.updateCategoryTable(editor.SymbolStyleCustomizerStructure);
   }

    @Override
    public String getJavaInitializationString() {
        CSymbolStyleCustomizerStructure vSymbolStyleCustomizerStructure = (CSymbolStyleCustomizerStructure) getValue();
        StringBuilder ret = new StringBuilder();
        ret.append("new ");
        ret.append(CSymbolStyleCustomizerStructure.class.getName());
        ret.append("(\n");
       // ret.append(".createSymbolStyleCustomizerStructure(");
        ret.append("new java.util.ArrayList<");
        ret.append(CategoryAttributes.class.getName());
        ret.append(">(){\n{");
        for(int i=0;i<vSymbolStyleCustomizerStructure.getFCategoryToAttributes().size();i++){
                ret.append("\n\tadd(new ");
                ret.append(CategoryAttributes.class.getName());
                ret.append("(");
                ret.append("\"");
                ret.append(vSymbolStyleCustomizerStructure.getFCategoryToAttributes().get(i).FCategory);
                ret.append("\",");
                ret.append("new ");
                ret.append(FCAttributes.class.getName());
                ret.append("(");
                ret.append("new java.awt.Font(\"");
                ret.append(vSymbolStyleCustomizerStructure.getFCategoryToAttributes().get(i).FAttributes.fFont.getFamily());
                ret.append("\",");
                ret.append(vSymbolStyleCustomizerStructure.getFCategoryToAttributes().get(i).FAttributes.fFont.getStyle());
                ret.append(",");
                ret.append(vSymbolStyleCustomizerStructure.getFCategoryToAttributes().get(i).FAttributes.fFont.getSize());
                ret.append("),");
                ret.append("new java.awt.Color(");
                ret.append(vSymbolStyleCustomizerStructure.getFCategoryToAttributes().get(i).FAttributes.fColor.getRed());
                ret.append(",");
                ret.append(vSymbolStyleCustomizerStructure.getFCategoryToAttributes().get(i).FAttributes.fColor.getGreen());
                ret.append(",");
                ret.append(vSymbolStyleCustomizerStructure.getFCategoryToAttributes().get(i).FAttributes.fColor.getBlue());
                ret.append(")");
                ret.append(")));");
            }
        ret.append("\t\n}\n}\n");
        ret.append(",\n");
        ret.append("new java.util.ArrayList<");
        ret.append(SymbolCategory.class.getName());
        ret.append(">(){\n{");
        for(int i=0;i<vSymbolStyleCustomizerStructure.getFSymbolToCategory().size();i++){
                ret.append("\n\tadd(new ");
                ret.append(SymbolCategory.class.getName());
                ret.append("(");
                ret.append("\"");
                ret.append(vSymbolStyleCustomizerStructure.getFSymbolToCategory().get(i).FSymbolName);
                ret.append("\",");
                ret.append("\"");
                ret.append(vSymbolStyleCustomizerStructure.getFSymbolToCategory().get(i).FCategory);
                ret.append("\"));");
        }
        ret.append("\t\n}\n}");
        ret.append(")");
        return ret.toString();
    }
   
}



