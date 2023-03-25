/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.GrammarDisplay;

import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSE_MultiStick;
import java.util.ArrayList;

/** 
 * An implementation of a table model for nonTerminal definitions of a grammar.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CNonterminalsTableModel extends CGrammarDisplayTableModel{

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
         String name;
         CSE body;
         ArrayList<Term> t=new ArrayList();
         int vSymbolCount=GrammarStructure.getGrammarContext().getNonTerminalDefs().count();
         for(int i=0;i<vSymbolCount;i++){
             name=GrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getName();
             body=GrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getBody();
             if(body instanceof CSE_MultiStick){
                 CSE_MultiStick m=(CSE_MultiStick)body;
                 for (int j=0;j<m.getList().count();j++){
                   t.add(new Term(name,m.getList().getElt(j).toString()));
                 }
             }else{          
                  t.add(new Term(name,body.toString()));
             }
         }
         
         fData=new Object[t.size()][2];
         for (int i = 0; i < t.size(); i++) {
          fData[i][0] = t.get(i).getName();
          fData[i][1] = t.get(i).getBody();
          
          }
         fireTableDataChanged();


    }
}

