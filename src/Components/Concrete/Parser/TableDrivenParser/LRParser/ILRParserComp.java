/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.TableDrivenParser.LRParser;

import Components.Concrete.Parser.IParseStep;
import Components.Concrete.Parser.IParserCompExt;
import Parsers.CLRParser;
import TableGenerators.LR.CLRTable;
import TableGenerators.LR.Move;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface ILRParserComp extends IParserCompExt,IParseStep {
    public CLRParser getParser();

    /**
     *
     * @param Parser
     */
    public void setParser(CLRParser Parser);

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
    public ArrayList<Move>[][] getLRTable1();
    /**
     *  Return the Grammar alphabet (terminals and Nonterminal names)
     *  @return Array of grammar alphabet.
     */
    public ArrayList<String> getStringAlphabet();

    /**
     *  Return the Grammar alphabet (terminal values and Nonterminal names)
     *  @return Array of grammar alphabet.
     */
    public ArrayList<String> getSymbolAlphabet();
    
}
