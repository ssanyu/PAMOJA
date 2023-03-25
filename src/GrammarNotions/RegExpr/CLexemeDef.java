/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.RegExpr;

import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.SyntaxExpr.CSymDec;

/**
 *
 * @author ssanyu
 */
public class CLexemeDef extends CSymDec{
    private CRE fBody;

    /**
     *
     * @param aBody
     */
    protected void setBody(CRE aBody){
            fBody=aBody;
    }

    /**
     *
     * @param aName
     * @param aBody
     */
    public CLexemeDef(String aName,CRE aBody){
        super(aName);
        fBody=aBody;
    }

    /**
     *
     * @return
     */
    public CRE getBody(){
        return fBody;
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
            fBody=(CRE)aTerm;
        else super.setTerm(i,aTerm);
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scLexemeDef;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "LexemeDef";
    }

   
}
