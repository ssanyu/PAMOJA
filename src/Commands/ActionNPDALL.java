/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Parsers.CNPDALL;
import Parsers.CParseLog;
import Parsers.CParseStack;
import SymbolStream.Symbol;
import TreeLayout.CTreeNode;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public abstract class ActionNPDALL extends Action{

    /**
     *
     */
    public CNPDALL parser;
       
    ActionNPDALL(CNPDALL parser){
        this.parser=parser;
    }

    /**
     *
     */
    @Override
   public void backup(){
        ArrayList<CTreeNode> stackPointers=new ArrayList();
        ArrayList<Symbol> symbolsUnmacthed=new ArrayList();
        ArrayList<Symbol> symbolsMacthed=new ArrayList();
        ArrayList<CParseLog> logs=new ArrayList();
        CParseStack stack=new CParseStack();
        String top=new String();
        ArrayList<CTreeNode> matchedNodes=new ArrayList();
        CTreeNode tree = null;
        CTreeNode topNode=null;
       
        
        symbolsUnmacthed.addAll(parser.getSymbolsUnmatched());
        symbolsMacthed.addAll(parser.getSymbolsMatched());
        logs.addAll(parser.getLogs());
        stack.pushAll(parser.getStack());
        top=parser.getTopSymbol();
        matchedNodes.addAll(parser.getMatchedNodes());
        tree=parser.getfTree().copyTree();
        stackPointers=tree.Leaves(tree);
        topNode=new CTreeNode(parser.getTopNode());
        backup=new Backup(topNode,tree,top,stackPointers,symbolsUnmacthed, symbolsMacthed,parser.getStringMatched(),parser.getStringUnmatched(),logs,stack,matchedNodes);
       
       
    }

    /**
     *
     */
    @Override
    public void undo() {
        parser.setTopSymbol(backup.getTopSymbol());
        parser.setStack(backup.getStack());
        parser.setStringMatched(backup.getStringMatched());
        parser.setStringUnmatched(backup.getStringUnmatched());
        parser.setSymbolsMatched(backup.getSymbolsMacthed());
        parser.setSymbolsUnmatched(backup.getSymbolsUnmacthed());
        parser.setLogs(backup.getLogs());
        parser.setTreeStack(backup.getTreeStack());
        parser.setfTree(backup.getTree());
        parser.setMatchedNodes(backup.getMatchedNodes());
        parser.setTopNode(backup.getTopNode());
   
    }   
}
