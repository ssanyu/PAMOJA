/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;

/**
 * Serial (sequential): expressions are defined one after the other. e.g Lexeme Defs.
 * Collateral (Parallel): terminal defs. are defined w.r.t. to the same context of lexeme Defs,
 *  but rhs may not contain other terminal names.
 * Recursive: Each of the rhs of non terminal defs may contain any of the non terminals.
 * 
 * 
 * 
 * @author ssanyu
 */
public enum CContextKind {

    /**
     *
     */
    ckSerial,

    /**
     *
     */
    ckCollateral,

    /**
     *
     */
    ckRecursive,

    /**
     *
     */
    ckItem,

    /**
     *
     */
    ckRecursiveItem

}
