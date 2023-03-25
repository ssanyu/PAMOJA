/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.SyntaxExpr;

import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Grammar.CGrammarCodes;

/**
 *
 * @author ssanyu
 */
public class CNonTerminalDef extends CSymDec {
    private CSE fBody;
    

    /**
     *
     * @return
     */
    public CSE getBody(){
        return fBody;
    }

    /**
     *
     * @param aBody
     */
    protected void setBody(CSE aBody){
        fBody=aBody;
    }

    
    /**
     *
     * @param aName
     * @param aBody
     */
    public CNonTerminalDef(String aName,CSE aBody){
        super(aName);
        fBody=aBody;
    }

    /**
     *
     * @return
     */
    @Override
    public CContextKind contextKind(){
        return CContextKind.ckItem;
    }

    /**
     *
     * @return
     */
    @Override
    public int termCount(){
        return 1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CTerm getTerm(int i){
        if(i==0)
            return fBody;
        else return super.getTerm(i);
    }

    /**
     *
     * @param i
     * @param aTerm
     */
    @Override
    public void setTerm(int i, CTerm aTerm){
        if(i==0)
            fBody=(CSE)aTerm;
        else super.setTerm(i,aTerm);
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scNonTerminalDef;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
       return "NonTerminalDef";
    }

}
