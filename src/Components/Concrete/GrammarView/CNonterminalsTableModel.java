/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.GrammarView;

import GrammarNotions.Grammar.CGrammar;

/** 
 * An implementation of a table model for nonTerminal definitions of a grammar.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CNonterminalsTableModel extends CGrammarViewTableModel{

    /**
     *
     */
    public CNonterminalsTableModel(){
        super();
    }

    @Override
    public String toString(){

      String vSymbols="\n[NonTerminals]";
      String vrow=null;
      String vKey=null,vValue=null;
      for (int j = 0; j < getRowCount(); j++){
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
    public void updateNonterminalsTable(CGrammar aGrammarStructure){
         GrammarStructure=aGrammarStructure;
         int vSymbolCount=GrammarStructure.getGrammarContext().getNonTerminalDefs().count();
         fData=new Object[vSymbolCount][2];
         for(int i=0;i<vSymbolCount;i++){
             fData[i][0]=GrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getName();
             fData[i][1]=GrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getBody().toText();
         }
         fireTableDataChanged();


    }
}

