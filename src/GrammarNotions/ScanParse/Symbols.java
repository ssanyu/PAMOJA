/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.ScanParse;

/**
 *
 * @author ssanyu
 */
public class Symbols {
    public static final int   // Symbol codes
            syError     = 49,

    /**
     *
     */
    syEndMarker = 50,

    /**
     *
     */
    syAttribute = 51, // @ , text attribute of a terminal

    /**
     *
     */
    syEmpty     = 52, // 0

    /**
     *
     */

    /**
     *
     */
    syEps       = 53, // 1

    /**
     *
     */
    syId        = 54, // abc

    /**
     *
     */
    syChar      = 55, // 'a'

    /**
     *
     */

    /**
     *
     */
    syString    = 56, // "abc"

    /**
     *
     */
    syUpto      = 57, //  -

    /**
     *
     */
    syDot       = 58, // .

    /**
     *
     */
    syStick     = 59, // |

    /**
     *
     */
    syAlt       = 60, // %

    /**
     *
     */
    syStar      = 61, // *

    /**
     *
     */
    syPlus      = 62, // +

    /**
     *
     */
    syOption    = 63, // ?

    /**
     *
     */
    syOpen      = 64, // (

    /**
     *
     */

    /**
     *
     */
    syClose     = 65, // )

    /**
     *
     */
    syAlt2      = 66; // &

  }