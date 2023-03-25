/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Grammar;

import GrammarNotions.Context_Terms.CContext;
import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CContextTuple;
import GrammarNotions.RegExpr.CLexemeDef_List;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.SyntaxExpr.CNonTerminalDef_List;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSymDec;
import GrammarNotions.SyntaxExpr.CTerminalDef_List;

/**
 *
 * @author ssanyu
 */
public class CGrammarContext extends CContextTuple {
    private CLexemeDef_List fLexemeDefs;
    private CTerminalDef_List fTerminalDefs;
    private CNonTerminalDef_List fNonTerminalDefs;

    /**
     *
     * @param aLexemeDefs
     */
    protected void setLexemeDefs(CLexemeDef_List aLexemeDefs){
            fLexemeDefs=aLexemeDefs;
    }

    /**
     *
     * @param aTerminalDefs
     */
    protected void setTerminalDefs( CTerminalDef_List aTerminalDefs){
            fTerminalDefs=aTerminalDefs;
    }

    /**
     *
     * @param aNonTerminalDefs
     */
    protected void setNonTerminalDefs(CNonTerminalDef_List aNonTerminalDefs){
            fNonTerminalDefs=aNonTerminalDefs;
    }
    
    /**
     *
     */
    public CGrammarContext(){
        fLexemeDefs=new CLexemeDef_List();
        fTerminalDefs=new CTerminalDef_List();
        fNonTerminalDefs=new CNonTerminalDef_List();
    }
    
    /**
     *
     * @param aLexemeDefs
     * @param aTerminalDefs
     * @param aNonTerminalDefs
     */
    public CGrammarContext(CLexemeDef_List aLexemeDefs, CTerminalDef_List aTerminalDefs,CNonTerminalDef_List aNonTerminalDefs){
            fLexemeDefs=aLexemeDefs;
            fTerminalDefs= aTerminalDefs;
            fNonTerminalDefs=aNonTerminalDefs;
    }

    /**
     *
     * @return
     */
    public CTerminalDef_List getTerminalDefs(){
        return fTerminalDefs;
    }

    /**
     *
     * @return
     */
    public CLexemeDef_List getLexemeDefs(){
        return fLexemeDefs;
    }

    /**
     *
     * @return
     */
    public CNonTerminalDef_List getNonTerminalDefs(){
        return fNonTerminalDefs;
    }

    /**
     *
     * @return
     */
    @Override
    public CContextKind contextKind() {
        return CContextKind.ckSerial;
    }

    @Override
    public int contextCount() {
        return 3;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CContext getContext(int i) {
        if(i==0)
            return fLexemeDefs;
        else if(i==1)
            return fTerminalDefs;
        else if(i==2)
            return fNonTerminalDefs;
        else return super.getContext(i);
    }

    /**
     *
     * @param i
     * @param aContext
     */
    @Override
    public void setContext(int i, CContext aContext) {
        if(i==0)
            fLexemeDefs=(CLexemeDef_List)aContext;
        else if(i==1)
            fTerminalDefs=(CTerminalDef_List)aContext;
        else if(i==2)
            fNonTerminalDefs=(CNonTerminalDef_List)aContext;
        else  super.setContext(i,aContext);
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scGrammarContext;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "GrammarContext";
    }

    //Auxilliary methods

    /**
     *
     * @return
     */
        public int firstNonTerminalIndex(){
        return lastTerminalIndex()+1;
    }

    /**
     *
     * @return
     */
    public int lastTerminalIndex(){
        return terminalCount()-1;
    }

    /**
     *
     * @return
     */
    public int firstTerminalIndex(){
        return 0;
    }

    /**
     *
     * @return
     */
    public int lastNonTerminalIndex(){
        return symbolCount()-1;
    }

    /**
     *
     * @param i
     * @return
     */
    public CSymDec getSymbol(int i){
        if(i<=lastTerminalIndex())
          return (CSymDec)fTerminalDefs.getElt(i);
        else return (CSymDec)fNonTerminalDefs.getElt(i-terminalCount());
    }
    
  
    
    /**
     *
     * @param aTerminal
     * @return
     */
    public int indexofTerminal(String aTerminal){
        int result=-1;
        for(int i=0;i<=fTerminalDefs.contextCount()-1;i++){
            if(((CSymDec) fTerminalDefs.getElt(i)).getName().equals(aTerminal))
                result=i;
        }
        return result;
    }

    /**
     *
     */
    public void reNumber(){
        CSymDec vDec;
        for(int i=0;i<=symbolCount()-1;i++){
           vDec=getSymbol(i);
           vDec.setNumber(i);
        }
    }
        
   /**
     *
     * @param aNonTerminal
     * @return
     */
    public int indexofNonTerminal(String aNonTerminal){
        int result=-1;
        for(int i=0;i<=fNonTerminalDefs.contextCount()-1;i++){
            if(((CSymDec) fNonTerminalDefs.getElt(i)).getName().equals(aNonTerminal))
                result=i;
        }
        return result;
    }
    
    /**
     *
     * @return
     */
    public int terminalCount(){
        return fTerminalDefs.contextCount();
    }

    /**
     *
     * @return
     */
    public int nonTerminalCount(){
        return fNonTerminalDefs.contextCount();
    }

    /**
     *
     * @return
     */
    public int symbolCount(){
        return terminalCount() + nonTerminalCount();
    }

    /**
     *
     * @return
     */
    public int lexemeCount(){
        return fLexemeDefs.contextCount();
    }
    
    /**
     *
     * @param i
     * @return
     */
    public String getLexemeName(int i){
        return fLexemeDefs.getElt(i).getName();
    }

    /**
     *
     * @param i
     * @return
     */
    public String getTerminalName(int i){
        return fTerminalDefs.getElt(i).getName();
    }

    /**
     *
     * @param i
     * @return
     */
    public String getNonterminalName(int i){
        return fNonTerminalDefs.getElt(i).getName();
    }

    /**
     *
     * @param i
     * @return
     */
    public CRE getLexemeBody(int i){
        return fLexemeDefs.getElt(i).getBody();
    }

    /**
     *
     * @param i
     * @return
     */
    public CRE getTerminalBody(int i){
        return fTerminalDefs.getElt(i).getBody();
    }

    /**
     *
     * @param i
     * @return
     */
    public CSE getNonterminalBody(int i){
        return fNonTerminalDefs.getElt(i).getBody();
    }
    
    /**
     *
     * @param aName
     * @return
     */
    public int indexOfLexeme(String aName){
        int i = fLexemeDefs.count()-1;
        while ( (i != -1) && !aName.equals(fLexemeDefs.getElt(i).getName()) ){
            i--;
        }
        return i;
    }
    
    /**
     *
     * @param aName
     * @return
     */
    public int indexOfTerminal(String aName){
        int i = fTerminalDefs.count()-1;
        while ( (i != -1) && !aName.equals(fTerminalDefs.getElt(i).getName()) ){
            i--;
        }
        return i;
    }

    /**
     *
     * @param aName
     * @return
     */
    public int indexOfNonterminal(String aName){
        
        int i = fNonTerminalDefs.count()-1;
        
        while ( (i != -1) && !aName.equals(fNonTerminalDefs.getElt(i).getName()) ){
            i--;
        }
          return i;
    }
    
    /**
     *
     * @param aName
     * @return
     */
    public boolean isTerminal(String aName){
        if(indexofTerminal(aName)==-1)
            return false;
        else return true;
    }
    
    /**
     *
     * @param aName
     * @return
     */
    public boolean isNonTerminal(String aName){
        if(indexofNonTerminal(aName)==-1)
            return false;
        else return true;
    }
}
