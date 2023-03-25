/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

import GrammarNotions.SyntaxExpr.CSymDec;
import TableGenerators.LR.Move;




/**
 *
 * @author jssanyu
 */
public abstract class  CLRParser extends CParserExt implements ILRParser {

    /**
     *
     */
    protected Move fAction;

    /**
     *
     */
    protected int fCurrentState; // current state
   
  
  
    /**
     *
     */
    public CLRParser(){
       super();
       Stack=new CParseStack();
       
    }
    
    /**
     *
     * @return
     */
    public int getCurrentState() {
        return fCurrentState;
    }

    /**
     *
     * @param fCurrentState
     */
    public void setCurrentState(int fCurrentState) {
        this.fCurrentState = fCurrentState;
    }

   

    public Move getAction() {
        return fAction;
    }

    /**
     *
     * @param fAction
     */
    public void setAction(Move fAction) {
        this.fAction = fAction;
    }

    /**
     *
     * @param n
     * @return
     */
    public String getProdName(int n){
         CSymDec vDec;
         vDec=fGrammar.getGrammarContext().getSymbol(n);
         String name=vDec.getName();
         return name;
    } 
    
    @Override
     public CParserResult parse(){throw new UnsupportedOperationException("Not supported yet.");}; 

   
     public CParserResult parseNonTerminal(String aName){throw new UnsupportedOperationException("Not supported yet.");}; 

    /**
     *
     */
    abstract  public void step();

}
