/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables.LRParseTables;

import Components.Concrete.ParseTables.CParseTableComp;
import TableGenerators.LR.CLRTable;
import TableGenerators.LR.Move;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public abstract class CLRParseTableComp extends CParseTableComp implements ILRParseTableComp{
     /**
     * The internal representation of LR(1) tables.
     */
    protected CLRTable   TableStructure; 
    
    
    /**
     * A data structure for holding LR(1)parse-tables in tabular form.
     */
    protected ArrayList<Move> fTable1[][]=null;

    /**
     * A list of alphabet symbols (terminals and nonterminals) used to construct LR(1) tables.
     */
    protected ArrayList<String> symAlphabet;
    /**
     * A list of alphabet symbols (terminals and nonterminals) used to construct LR(1) tables.
     */
    protected ArrayList<String> strAlphabet;
    
    /**
     *
     */
    public CLRParseTableComp() {
        super();
    }
   
    
    /**
     * Returns a list of terminal and non-terminal symbol-names used in constructing the parse tables.
     * @return the list of terminal and non-terminal symbol-names 
     */
    public ArrayList<String> getStringAlphabet(){
        return strAlphabet;
    }
    /**
    * Returns a list of terminal symbol values and non-terminal symbol-names to be used in constructing the parse tables.
     * @return the list of terminal symbol values and non-terminal symbol-names
    */
    public ArrayList<String> getSymbolAlphabet(){
           return symAlphabet; 
    }

    /**
     *
     * @return
     */
    public CLRTable getTableStructure(){
        return TableStructure;
    }

    /**
     *
     * @param TableStructure
     */
    public void setTableStructure(CLRTable TableStructure){
        this.TableStructure=TableStructure;
    }

    /**
     *
     * @return
     */
    public ArrayList<Move>[][] getfTable1() {
        return fTable1;
    }

    /**
     *
     * @param fTable1
     */
    public void setfTable1(ArrayList<Move>[][] fTable1) {
        this.fTable1 = fTable1;
    }
    
    
}
