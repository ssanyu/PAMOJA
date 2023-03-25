/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.TableDrivenParser.LRParser;


import Components.Concrete.Parser.CParserCompExt;
import Parsers.CLRParser;
import TableGenerators.LR.CLRTable;
import TableGenerators.LR.Move;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public abstract class CLRParserComp extends CParserCompExt implements ILRParserComp{

    /**
     *
     */
    protected CLRParser Parser;
   
    /**
     *
     */
    protected  CLRTable   TableStructure;  

    /**
     *
     */
    protected int currentState;

    /**
     *
     */
    protected Move action;

    /**
     *
     */
    protected ArrayList<Move> fTable1[][]=null;

    /**
     *
     */
    protected ArrayList<String> strAlphabet;

    /**
     *
     */
    protected ArrayList<String> symAlphabet;

    /**
     *
     */
    public CLRParserComp() {
       super();
       
    }

    public CLRParser getParser() {
        return Parser;
    }

    /**
     *
     * @param Parser
     */
    public void setParser(CLRParser Parser) {
        this.Parser = Parser;
    }

    /**
     *
     * @return
     */
    public Move getAction() {
        return action;
    }

    /**
     *
     * @param action
     */
    public void setAction(Move action) {
        this.action = action;
    }

    /**
     *
     * @return
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     *
     * @param currentState
     */
    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    /**
     *
     */
    public void parseText(){}

    /**
     *
     * @return
     */
    public CLRTable getTableStructure() {
        return TableStructure;
    }

    /**
     *
     * @param TableStructure
     */
    public void setTableStructure(CLRTable TableStructure) {
        this.TableStructure = TableStructure;
    }
    
      /**
     * Returns LR(1) parse-tables.
     * @return LR(1) parse-tables
     */
    public ArrayList<Move>[][] getLRTable1(){
        return fTable1;
    }
    
    /**
     *  Return the Grammar alphabet (terminal and Nonterminal names)
     *  @return Array of grammar alphabet.
     */
    public ArrayList<String> getStringAlphabet(){
        return strAlphabet;
    }
    
     /**
     *  Return the Grammar alphabet (terminal values and Nonterminal names)
     *  @return Array of grammar alphabet.
     */
    public ArrayList<String> getSymbolAlphabet(){
        return symAlphabet;
    }
}
