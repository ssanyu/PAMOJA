/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LL.LLTable;

import TableGenerators.CParseTable;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CLLTable extends CParseTable {

    /**
     *
     */
    protected String[][] table;

    /**
     *
     */
    protected ArrayList<String> terminals;

    /**
     *
     */
    protected ArrayList<String>nonTerminals;

    /**
     *
     * @return
     */
    public ArrayList<String> getTerminals() {
        return terminals;
    }

    /**
     *
     * @param terminals
     */
    public void setTerminals(ArrayList<String> terminals) {
        this.terminals = terminals;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getNonTerminals() {
        return nonTerminals;
    }

    /**
     *
     * @param nonTerminals
     */
    public void setNonTerminals(ArrayList<String> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    /**
     *
     * @return
     */
    public String[][] getTable() {
        return table;
    }

    /**
     *
     * @param table
     */
    public void setTable(String[][] table) {
        this.table = table;
    }

    /**
     *
     * @param table
     * @param terminals
     * @param nonTerminals
     */
    public CLLTable(String[][] table, ArrayList<String> terminals, ArrayList<String> nonTerminals) {
        this.table = table;
        this.terminals = terminals;
        this.nonTerminals = nonTerminals;
    }

    /**
     *
     */
    public CLLTable() {
        table=null;
        terminals=new ArrayList();
        nonTerminals=new ArrayList();
    }
    
    /**
     *
     * @param aNonTerm
     * @param aSymbol
     * @return
     */
    public String getRHS(String aNonTerm,String aSymbol){
         if(!aSymbol.equals("Error"))
             return table[nonTerminals.indexOf(aNonTerm)][terminals.indexOf(aSymbol)];
         else return "Error";
     }
    

    
    
    
    
}
