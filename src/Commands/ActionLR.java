/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Components.Concrete.Parser.TableDrivenParser.LRParser.CLRParserComp;
import Nodes.CNode;
import Parsers.CLRParser;
import Parsers.CParseLog;
import Parsers.CParseStack;
import SymbolStream.Symbol;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public abstract class ActionLR extends Action{

    /**
     *
     */
    public CLRParser parser;
       
    ActionLR(CLRParser parser){
        this.parser=parser;
    }
    
    /**
     *
     * @return
     */
    public boolean execute() {
        backup();
        parser.step();
        return true;
    }

    /**
     *
     */
    @Override
    public void backup(){
        ArrayList<CNode> treeStack=new ArrayList();
        ArrayList<Symbol> symbolsUnmacthed=new ArrayList();
        ArrayList<Symbol> symbolsMacthed=new ArrayList();
        ArrayList<CParseLog> logs=new ArrayList();
        CParseStack stack=new CParseStack();
       
        
       
        treeStack.addAll(parser.getNodeStack());
        symbolsUnmacthed.addAll(parser.getSymbolsUnmatched());
        symbolsMacthed.addAll(parser.getSymbolsMatched());
        logs.addAll(parser.getLogs());
        stack.pushAll(parser.getStack());
        backup=new Backup(parser.getAction(),parser.getCurrentState(),treeStack,symbolsUnmacthed, symbolsMacthed,parser.getStringMatched(),parser.getStringUnmatched(),logs,stack);
    }

    /**
     *
     */
    @Override
    public void undo() {
        //provide backup copy to the LR parser object
        parser.setAction(backup.getAction());
        parser.setCurrentState(backup.getCurrentState());
        parser.setStack(backup.getStack());
        parser.setStringMatched(backup.getStringMatched());
        parser.setStringUnmatched(backup.getStringUnmatched());
        parser.setSymbolsMatched(backup.getSymbolsMacthed());
        parser.setSymbolsUnmatched(backup.getSymbolsUnmacthed());
        parser.setLogs(backup.getLogs());
        parser.setNodeStack(backup.getNodeStack());
    }  
    
    
    
    
    
}
