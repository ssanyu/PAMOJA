/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Concrete.Grammar;

import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CTerminalDef;
import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 * A PropertyEditor which allows users to edit the internal structure of a grammar. 
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CGrammarPropertyEditor extends PropertyEditorSupport {
    private CGrammarEditor editor = null;

    /**
     *
     */
    public CGrammarPropertyEditor() {
        this.editor = new CGrammarEditor();
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
        throw new IllegalArgumentException("GrammarStructure can not be set this way. Please use the editor.");
    }

 
    @Override
   public Object getValue() {
      if(super.getValue()==null){
         setValue(null);
       }
     // CGrammar vGrammarStructure = (CGrammar) super.getValue();
     CGrammar vGrammarStructure=new CGrammar(editor.GrammarStructure.getGrammarContext(),editor.GrammarStructure.getStartExpr());
      return vGrammarStructure;
    }
    
    @Override
    public void setValue(Object aGrammarStructure) {
        if(aGrammarStructure==null){
          aGrammarStructure = new CGrammar();
      }
    super.setValue(aGrammarStructure);
    editor.GrammarStructure=(CGrammar)aGrammarStructure;
    editor.LexemeTableModel.updateLexemeTable(editor.GrammarStructure.getGrammarContext().getLexemeDefs());
    editor.NonterminalsTableModel.updateNonTerminalsTable(editor.GrammarStructure.getGrammarContext().getNonTerminalDefs());
    editor.TerminalsTableModel.updateTerminalsTable(editor.GrammarStructure.getGrammarContext().getTerminalDefs());
    editor.txtStartExpr.setText(editor.GrammarStructure.getStartExpr().toText()); 
    // if(!editor.lexmodel.hasEmptyRow()){
     //  editor.lexmodel.addEmptyRow();
   // }
   
    }

    /**
     *
     * @param aString
     * @return
     */
    public String replaceAll(String aString){
       String s="";
       for(int i=0;i<aString.length();i++){
           if(aString.charAt(i)=='"'){
              s=s+"\\"+"\""; 
           }else{
               s=s+aString.charAt(i);
           }
       }
       return s;
           
   } 
  @Override
   public String getJavaInitializationString() {
        CGrammar vGrammarStructure = (CGrammar) getValue();
        StringBuilder ret = new StringBuilder();
        ret.append(CGrammar.class.getName());
        ret.append(".fromText(");
        ret.append("\"[Lexemes]"+'\\'+"n");  
        for(int i=0;i<vGrammarStructure.getGrammarContext().lexemeCount();i++){
          ret.append(vGrammarStructure.getGrammarContext().getLexemeDefs().getElt(i).getName());
          ret.append("=");
          CRE vBody=vGrammarStructure.getGrammarContext().getLexemeDefs().getElt(i).getBody();
          String s=vBody.toText();
          ret.append(replaceAll(s));
          ret.append('\\'+"n");
          
        }
        ret.append('\\'+"n");
        
        ret.append("[Terminals]"+'\\'+"n");  
        for(int i=0;i<vGrammarStructure.getGrammarContext().terminalCount();i++){
           CTerminalDef vTerminalDef=vGrammarStructure.getGrammarContext().getTerminalDefs().getElt(i);
            if(vTerminalDef.hasData()){
                   ret.append(vTerminalDef.getName()).append('@');
            }else{
                   ret.append(vTerminalDef.getName());
            } 
          
          ret.append("=");
          CRE vBody=vTerminalDef.getBody();
          String s=vBody.toText();
          ret.append(replaceAll(s));
          ret.append('\\'+"n");
          
        }
        ret.append('\\'+"n");
        
        ret.append("[Nonterminals]"+'\\'+"n");  
        for(int i=0;i<vGrammarStructure.getGrammarContext().nonTerminalCount();i++){
          ret.append(vGrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getName());
          ret.append("=");
          CSE vBody=vGrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getBody();
          ret.append(vBody.toText());
          ret.append('\\'+"n");
          
        }
        ret.append('\\'+"n");
        
        ret.append("[Start]"+'\\'+"n"); 
        ret.append("startexpr");
        ret.append("=");
        ret.append(vGrammarStructure.getStartExpr().toText());
        
        ret.append("\"");
        ret.append(")");
        
        
        
        
        
        
        
        
        
     /*   ret.append("new ");
        ret.append(CGrammar.class.getName());
        ret.append("(new ");
        ret.append(CGrammarContext.class.getName());
        ret.append("(\n");
        ret.append("new ");
        ret.append(CLexemeDef_List.class.getName());
        ret.append("(){\n{");
        for(int i=0;i<vGrammarStructure.getGrammarContext().lexemeCount();i++){
                ret.append("\n\tadd(new ");
                ret.append(CLexemeDef.class.getName());
                ret.append("(");
                ret.append("\"");
                ret.append(vGrammarStructure.getGrammarContext().getLexemeDefs().getElt(i).getName());
                ret.append("\",");
               // ret.append("\"");
                ret.append(vGrammarStructure.getGrammarContext().getLexemeDefs().getElt(i).getClass());//.getName()..getBody());
               // ret.append("\"));");
        }
        ret.append("\t\n}\n}\n");
        ret.append(",\n");
        ret.append("new ");
        ret.append(CTerminalDef_List.class.getName());
        ret.append("(){\n{");
        for(int i=0;i<vGrammarStructure.getGrammarContext().terminalCount();i++){
                ret.append("\n\tadd(new ");
                ret.append(CTerminalDef.class.getName());
                ret.append("(");
                ret.append("\"");
                ret.append(vGrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getName());
                ret.append("\",");
                ret.append("true");
                ret.append("\",");
                //ret.append("\"");
                ret.append(vGrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getBody().getClass());
              //  ret.append("\"));");
        }
        ret.append("\t\n}\n}\n");
        ret.append(",\n");
        ret.append("new ");
        ret.append(CNonTerminalDef_List.class.getName());
        ret.append("(){\n{");
        for(int i=0;i<vGrammarStructure.getGrammarContext().nonTerminalCount();i++){
                ret.append("\n\tadd(new ");
                ret.append(CNonTerminalDef.class.getName());
                ret.append("(");
                ret.append("\"");
                ret.append(vGrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getName());
                ret.append("\",");
                ret.append("\"");
                ret.append(vGrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getBody());
                ret.append("\"));");
        }
        ret.append("\t\n}\n}\n");
        ret.append(")");
        
        */
       return ret.toString();
  
}

}

