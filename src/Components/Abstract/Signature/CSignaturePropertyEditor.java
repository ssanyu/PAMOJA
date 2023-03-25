package Components.Abstract.Signature;

import Signatures.CSignature;
import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 * A PropertyEditor which allows users to edit the internal structure of a signature. 
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSignaturePropertyEditor extends PropertyEditorSupport{
    private CSignatureEditor editor = null;

    /**
     * Creates an instance of a SIgnaturePropertyEditor.
     */
    public CSignaturePropertyEditor() {
        this.editor = new CSignatureEditor();
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
        return null;
    }

    @Override
    public void setAsText(String text){
       // throw new IllegalArgumentException("SignatureStructure can not be set this way. Please use the editor.");
    }

   @Override
   public Object getValue() {
      if(super.getValue()==null){
         setValue(null);
      }
      CSignature vSignatureStructure = (CSignature) super.getValue();
    //vSignatureStructure=new CSignature(editor.SignatureStructure.getSignatureContext(),editor.SignatureStructure.getStartExpr());
      return vSignatureStructure;
   }
    
    @Override
    public void setValue(Object aSignatureStructure) {
        if(aSignatureStructure==null){
             aSignatureStructure = new CSignature();
        }
        super.setValue(aSignatureStructure);
        editor.initEditorComponents((CSignature)aSignatureStructure);
        
   }
    
  @Override
   public String getJavaInitializationString() {
       CSignature vSignatureStructure = (CSignature) getValue();
        StringBuilder ret = new StringBuilder();
    
       return ret.toString();
  }
}