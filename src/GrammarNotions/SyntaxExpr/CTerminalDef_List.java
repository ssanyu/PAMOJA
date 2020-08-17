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
public class CTerminalDef_List extends CContextList {
    
    /**
     *
     */
    public CTerminalDef_List(){
        super();
    }

    /**
     *
     * @param i
     * @return
     */
    public CTerminalDef getElt(int i){
        return (CTerminalDef)getContext(i);
     }

    /**
     *
     * @param i
     * @param aTerminalDef
     */
    protected void setElt(int i, CTerminalDef aTerminalDef){
        setContext(i,aTerminalDef);
    }
    @Override
    public String eltSortLabel() {
        return "TerminalDef";
    }

    @Override
    public int eltSortCode() {
        return CGrammarCodes.scTerminalDef;
    }

    /**
     *
     * @return
     */
    public int sortCode() {
        return CGrammarCodes.scTerminalDefList;
    }

    /**
     *
     * @return
     */
    public String sortLabel() {
        return "TerminalDefList";
    }

    /**
     *
     * @return
     */
    @Override
    public Class eltClass() {
        return CTerminalDef.class;
    }

    /**
     *
     * @return
     */
    public CContextKind contextKind() {
        return CContextKind.ckCollateral;
    }

   

}
