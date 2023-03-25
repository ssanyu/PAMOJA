/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Parsers.CNPDALR;
import Parsers.CParseLog;
import Parsers.CParseStack;
import SymbolStream.Symbol;
import TreeLayout.CTreeNode;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public abstract class ActionNPDALR extends Action{

    /**
     *
     */
    public CNPDALR parser;
       
    ActionNPDALR(CNPDALR parser){
        this.parser=parser;
    }
    
    /**
     *
     */
    @Override
    public void backup(){
        ArrayList<CTreeNode> treeStack=new ArrayList();
        ArrayList<Symbol> symbolsUnmacthed=new ArrayList();
        ArrayList<Symbol> symbolsMacthed=new ArrayList();
        ArrayList<CParseLog> logs=new ArrayList();
        CParseStack stack=new CParseStack();
        ArrayList<String> stackOptions;
        stackOptions = new ArrayList();
        
        treeStack.addAll(parser.getTreeStack());
        symbolsUnmacthed.addAll(parser.getSymbolsUnmatched());
        symbolsMacthed.addAll(parser.getSymbolsMatched());
        logs.addAll(parser.getLogs());
        stack.pushAll(parser.getStack());
        stackOptions.addAll(parser.getSymStackOptions());
        backup=new Backup(treeStack,symbolsUnmacthed, symbolsMacthed,parser.getStringMatched(),parser.getStringUnmatched(),logs,stack,stackOptions);
    }

    /**
     *
     */
    public void undo() {
        parser.setStack(backup.getStack());
        parser.setStringMatched(backup.getStringMatched());
        parser.setStringUnmatched(backup.getStringUnmatched());
        parser.setSymbolsMatched(backup.getSymbolsMacthed());
        parser.setSymbolsUnmatched(backup.getSymbolsUnmacthed());
        parser.setLogs(backup.getLogs());
        parser.setTreeStack(backup.getTreeStack());
        parser.setSymStackOptions(backup.getStackOptions());
        
        
    }
    
}
