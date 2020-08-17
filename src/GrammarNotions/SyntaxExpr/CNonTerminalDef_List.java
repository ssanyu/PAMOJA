/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.SyntaxExpr;

import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CContextList;
import GrammarNotions.Grammar.CGrammarCodes;

/**
 *
 * @author ssanyu
 */
public class CNonTerminalDef_List extends CContextList {

    /**
     *
     */
    public CNonTerminalDef_List(){
        super();
    }

    /**
     *
     * @param i
     * @return
     */
    public CNonTerminalDef getElt(int i){
        return (CNonTerminalDef)getContext(i);
     }

    /**
     *
     * @param i
     * @param aNonTerminalDef
     */
    protected void setElt(int i, CNonTerminalDef aNonTerminalDef){
        setContext(i,aNonTerminalDef);
    }
    @Override
    public String eltSortLabel() {
        return "NonTerminalDef";
    }

    @Override
    public int eltSortCode() {
        return CGrammarCodes.scNonTerminalDef;
    }

    /**
     *
     * @return
     */
    public int sortCode() {
        return CGrammarCodes.scNonTerminalDefList;
    }

    /**
     *
     * @return
     */
    public String sortLabel() {
        return "NonTerminalDefList";
    }

    /**
     *
     * @return
     */
    @Override
    public Class eltClass() {
        return CNonTerminalDef.class;
    }

    /**
     *
     * @return
     */
    public CContextKind contextKind() {
        return CContextKind.ckRecursive;
    }
    
    
}
