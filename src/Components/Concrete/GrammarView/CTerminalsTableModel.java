/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Concrete.GrammarView;

import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.SyntaxExpr.CTerminalDef;


/** 
 * An implementation of a table model for terminal definitions of a grammar.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CTerminalsTableModel extends CGrammarViewTableModel{

    /**
     *
     */
    public CTerminalsTableModel(){
        super();
    }

    @Override
    public String toString(){

      String vSymbols="\n[Terminals]";
      String vrow=null;
      String vKey=null,vValue=null;
      for (int j = 0; j < getRowCount(); j++){
        //  vKey=(String)fData[j][0];
       //   vValue=(String)fData[j][1];
          if(vKey!=null && vValue!=null)
              vrow="\n"+vKey+"="+vValue;
          vSymbols=vSymbols+ vrow;
      }
      return vSymbols;
    }

    /**
     *
     * @param aGrammarStructure
     */
    public void updateTerminalsTable(CGrammar aGrammarStructure){
         String vName;
         CTerminalDef vTermDef;
         GrammarStructure=aGrammarStructure;
         int vSymbolCount=GrammarStructure.getGrammarContext().getTerminalDefs().count();
        // int vKeywordsCount=LexicalGrammarStructure.keywordsCount();
        // int rowCount=vSymbolCount+vKeywordsCount;
         fData=new Object[vSymbolCount][2];
         for(int i=0;i<vSymbolCount;i++){
             vTermDef=GrammarStructure.getGrammarContext().getTerminalDefs().getElt(i);
            if(vTermDef.hasData()){
                vName=GrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getName()+'@';
            }else{
                vName=GrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getName();
            }   
         
             fData[i][0]=vName;
             fData[i][1]=GrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getBody().toText();
         }
         fireTableDataChanged();


    }
}

