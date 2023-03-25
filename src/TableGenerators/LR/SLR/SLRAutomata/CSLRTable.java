/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LR.SLR.SLRAutomata;

import TableGenerators.LR.CLRTable;
import TableGenerators.LR.Move;

/**
 *
 * @author jssanyu
 */
public class CSLRTable extends CLRTable{
    

      
    /**
     *
     * @param aDFA
     */
    public CSLRTable(CSLRDFA aDFA){
        fTable=new Move[aDFA.stateCount()][aDFA.alphabet().cardinality()];
        setStartState(aDFA.startState());
        initAlphabet(aDFA.alphabet());
        setStateCount(aDFA.stateCount());
    }
    
   
   
    
}

