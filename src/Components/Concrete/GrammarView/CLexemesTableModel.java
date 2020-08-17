/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Concrete.GrammarView;

import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.RegExpr.CLexemeDef;

/** 
 * An implementation of a table model for lexeme definitions of a grammar.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CLexemesTableModel extends CGrammarViewTableModel {
    
    /**
     *
     */
    public CLexemesTableModel(){
       super();
    }
   
    @Override
    public String toString(){

      String vLexemes="[Lexemes]";
      String vrow=null;
      String vKey=null,vValue=null;
      for (int j = 0; j < getRowCount(); j++){
        //  vKey=(String)fData[j][0];
        //  vValue=(String)fData[j][1];
          if(vKey!=null && vValue!=null)
              vrow="\n"+vKey+"="+vValue;
          vLexemes=vLexemes+ vrow;
      }
       return vLexemes;
    }

    /**
     *
     * @param aGrammarStructure
     */
    public void updateLexicalTable(CGrammar aGrammarStructure){
         GrammarStructure=aGrammarStructure;
         int vRowCount=GrammarStructure.getGrammarContext().getLexemeDefs().count();
         fData=new Object[vRowCount][2];
         for(int i=0;i<vRowCount;i++){
             CLexemeDef vLexeme=GrammarStructure.getGrammarContext().getLexemeDefs().getElt(i);
             fData[i][0]=vLexeme.getName();
             fData[i][1]=vLexeme.getBody().toText();
             
         }
        fireTableDataChanged();
    }
    
}
