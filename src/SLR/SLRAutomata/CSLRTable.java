/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SLR.SLRAutomata;


import Sets.IntAlphabet;


/**
 *
 * @author jssanyu
 */
public class CSLRTable {

    /**
     *
     */
    protected Move[][] fTable;

    /**
     *
     */
    protected int fStartState;

    /**
     *
     */
    protected IntAlphabet fAlphabet;

    /**
     *
     */
    protected int fStateCount;
   
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
    
    private void setStartState(int aStartState){
        fStartState=aStartState;
    }
    
    /**
     *
     * @return
     */
    public int startState(){
        return fStartState;
    }

    /**
     *
     * @return
     */
    public IntAlphabet alphabet(){
        return fAlphabet;
    }
    private void initAlphabet(IntAlphabet aAlphabet){
        fAlphabet=aAlphabet;
    }
    private void setStateCount(int aStateCount){
        fStateCount=aStateCount;
    }

    /**
     *
     * @return
     */
    public int stateCount(){
        return fStateCount;
    }

    /**
     *
     * @param aState
     * @param aSymbol
     * @return
     */
    public Move move(int aState,int aSymbol){
        return fTable[aState][aSymbol];
    }
    
    /**
     *
     * @param aState
     * @param aSymbol
     * @param aMove
     */
    public void addAction(int aState,int aSymbol,Move aMove){
		 fTable[aState][aSymbol]=aMove;
    }
    
    /**
     *
     * @return
     */
    public boolean isEmpty(){
        if(fTable.length==2)
            return true;
        else return false;
    }
    
}

