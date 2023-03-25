/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Components.Concrete.Parser.NPDA.NPDALR.CNPDALRComp;
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
public abstract class ActionNPDA extends Action{
    public CNPDALRComp parser;
       
    ActionNPDA(CNPDALRComp parser){
        this.parser=parser;
    }
    
    @Override
    public void backup(){
        ArrayList<CTreeNode> treeStack=new ArrayList();
        ArrayList<Symbol> symbolsUnmacthed=new ArrayList();
        ArrayList<Symbol> symbolsMacthed=new ArrayList();
        ArrayList<CParseLog> logs=new ArrayList();
        CParseStack stack=new CParseStack();
        ArrayList<String> stackOptions=new ArrayList();
        CNPDALR p;
        
        p=parser.getParser();
        treeStack.addAll(p.getTreeStack());
        symbolsUnmacthed.addAll(p.getSymbolsUnmatched());
        symbolsMacthed.addAll(p.getSymbolsMatched());
        logs.addAll(p.getLogs());
        stack.pushAll(p.getStack());
        stackOptions.addAll(p.getSymStackOptions());
        backup=new Backup(treeStack,symbolsUnmacthed, symbolsMacthed,p.getStringMatched(),p.getStringUnmatched(),logs,stack,stackOptions);
    }

    
    
}
