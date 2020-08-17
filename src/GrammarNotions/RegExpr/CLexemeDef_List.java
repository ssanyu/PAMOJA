/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.RegExpr;

import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CContextList;

/**
 *
 * @author ssanyu
 */
public class CLexemeDef_List extends CContextList {
    
    /**
     *
     */
    public CLexemeDef_List(){
        super();
    }

    /**
     *
     * @param i
     * @return
     */
    public CLexemeDef getElt(int i){
        return (CLexemeDef)getContext(i);
    }

    /**
     *
     * @param i
     * @param aLexemeDef
     */
    protected void setElt(int i, CLexemeDef aLexemeDef){
        setContext(i,aLexemeDef);

    }
    @Override
    public String eltSortLabel() {
        return "LexemeDef";
    }

    @Override
    public int eltSortCode() {
        return CGrammarCodes.scLexemeDef;
    }

    /**
     *
     * @return
     */
    public int sortCode() {
        return CGrammarCodes.scLexemeDefList;
    }

    /**
     *
     * @return
     */
    public String sortLabel() {
        return "LexemeDefList";
    }
    
    /**
     *
     * @return
     */
    @Override
    public Class eltClass() {
        return CLexemeDef.class;
    }

    /**
     *
     * @return
     */
    public CContextKind contextKind() {
        return CContextKind.ckSerial;
    }
    
    

}
