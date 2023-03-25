/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseTables.LRParseTables;

import Components.Concrete.ParseTables.IParseTableComp;
import TableGenerators.LR.CLRTable;
import TableGenerators.LR.Move;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface ILRParseTableComp extends IParseTableComp{
     /**
     * Returns a list of terminal and non-terminal symbol-names used in constructing the parse tables.
     * @return the list of terminal and non-terminal symbol-names 
     */
        public ArrayList<String> getStringAlphabet();

        /**
     * Returns a list of terminal symbol values and non-terminal symbol-names to be used in constructing the parse tables.
     * @return the list of terminal symbol values and non-terminal symbol-names
     */
        public ArrayList<String> getSymbolAlphabet();
        
    /**
     *
     * @return
     */
    public CLRTable getTableStructure();

    /**
     *
     * @param TableStructure
     */
    public void setTableStructure(CLRTable TableStructure);

    /**
     *
     * @return
     */
    public ArrayList<Move>[][] getfTable1();

    
}
