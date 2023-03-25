/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;


import Nodes.CNode;

import SymbolStream.Symbol;
import TableGenerators.LR.Move;
import java.util.ArrayList;



/**
 *
 * @author jssanyu
 */
public class CParseLogEntry {

    /**
     * Stack contents
     */
    public ArrayList<Integer> stateStack;

    /**
     *
     */
    public ArrayList<CNode> treeStack;

    /**
     *
     */
    public ArrayList<Symbol> remainingSymbols;

    /**
     *
     */
    public ArrayList<Symbol> matchedSymbols;

    /**
     *
     */
    public String inputString;

    /**
     *
     */
    public String production;
    /**
     *
     */
    public Move action;

    /**
     *
     * @param stateStack
     * @param treeStack
     * @param inputString
     * @param production
     * @param action
     * @param remainingSymbols
     */
    public CParseLogEntry(ArrayList<Integer> stateStack, ArrayList<CNode> treeStack,String inputString, String production, Move action,ArrayList<Symbol> remainingSymbols) {
        this.stateStack = stateStack;
        this.treeStack=treeStack;
        this.inputString = inputString;
        this.production = production;
        this.action = action;
        this.remainingSymbols=remainingSymbols;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getStateStack() {
        return stateStack;
    }

    /**
     *
     * @param stateStack
     */
    public void setStateStack(ArrayList<Integer> stateStack) {
        this.stateStack = stateStack;
    }

    /**
     *
     * @return
     */
    public ArrayList<CNode> getTreeStack() {
        return treeStack;
    }

    /**
     *
     * @param treeStack
     */
    public void setTreeStack(ArrayList<CNode> treeStack) {
        this.treeStack = treeStack;
    }

    /**
     *
     * @return
     */
    public String getInputString() {
        return inputString;
    }

    /**
     *
     * @param inputString
     */
    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    /**
     *
     * @return
     */
    public String getProduction() {
        return production;
    }

    /**
     *
     * @param production
     */
    public void setProduction(String production) {
        this.production = production;
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
    public ArrayList<Symbol> getRemainingSymbols() {
        return remainingSymbols;
    }

    /**
     *
     * @param remainingSymbols
     */
    public void setRemainingSymbols(ArrayList<Symbol> remainingSymbols) {
        this.remainingSymbols = remainingSymbols;
    }
    
    public String toString(){
       return stateStack.toString()+"  "+production+"  "+action.toString();
    }
    

    
}
