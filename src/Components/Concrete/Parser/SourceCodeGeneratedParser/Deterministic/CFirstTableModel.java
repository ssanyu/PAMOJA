/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.SourceCodeGeneratedParser.Deterministic;

import GrammarNotions.Grammar.CGrammar;
import java.util.ArrayList;

/** 
 * An implementation of a table model for nonTerminal definitions of a grammar.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CFirstTableModel extends CViewTableModel{

    /**
     *
     */
    public CFirstTableModel(){
        super();
    }

    
    /**
     *
     * @param aSet
     */
    public void updateNonterminalsTable(ArrayList<CNTermSet> aSet){
         firstSets=aSet;
        
         fData=new Object[firstSets.size()][2];
         for(int i=0;i<firstSets.size();i++){
             fData[i][0]=firstSets.get(i).getName();
             fData[i][1]=firstSets.get(i).getFirst();
         }
         fireTableDataChanged();


    }
}

