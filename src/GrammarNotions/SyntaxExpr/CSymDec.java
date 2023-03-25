/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.SyntaxExpr;

import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CItem;
import GrammarNotions.Grammar.CGrammarCodes;

/**
 *
 * @author ssanyu
 */
public class CSymDec extends CItem {

    /**
     *
     */
    protected int fNumber;

    /**
     *
     */
    public CSymDec(){
        super();
    }

    /**
     *
     * @param aName
     */
    public CSymDec(String aName){
        super(aName);
    }

    /**
     *
     * @return
     */
    public int getNumber(){
        return fNumber;
    }

    /**
     *
     * @param aNumber
     */
    public void setNumber(int aNumber){
        fNumber=aNumber;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scSymDec;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "SymDec";
    }

    /**
     *
     * @return
     */
    @Override
    public CContextKind contextKind() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   

}
