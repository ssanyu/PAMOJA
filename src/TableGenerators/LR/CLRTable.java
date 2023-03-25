/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LR;

import Sets.IntAlphabet;
import TableGenerators.CParseTable;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CLRTable extends CParseTable {
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
    protected ArrayList<String> SymbolAlphabet;

    /**
     *
     */
    protected int fStateCount;
    
    /**
     *
     * @param aStartState
     */
    protected void setStartState(int aStartState){
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

    /**
     *
     * @param aAlphabet
     */
    protected void initAlphabet(IntAlphabet aAlphabet){
        fAlphabet=aAlphabet;
    }

    /**
     *
     * @param aStateCount
     */
    protected void setStateCount(int aStateCount){
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
     * @return
     */
    public ArrayList<String> getSymbolAlphabet() {
        return SymbolAlphabet;
    }

    /**
     *
     * @param SymbolAlphabet
     */
    public void setSymbolAlphabet(ArrayList<String> SymbolAlphabet) {
        this.SymbolAlphabet = SymbolAlphabet;
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
     * @return
     */
    public boolean isEmpty(){
        if(fTable.length==2)
            return true;
        else return false;
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
    
}
