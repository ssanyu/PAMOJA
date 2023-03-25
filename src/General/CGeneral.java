
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package General;

import Sets.Alphabet;
import Sets.AlphabetOps;




/**
 *
 * @author ssanyu
 */
public class CGeneral {

    /**
     *
     */
    protected static final Alphabet csLower=AlphabetOps.range('a', 'z');

    /**
     *
     */
    protected static final Alphabet csUpper=AlphabetOps.range('A', 'Z');

    /**
     *
     */
    protected static final Alphabet csDigit=AlphabetOps.range('0', '9');

    /**
     *
     */
    public static final Alphabet csChars=AlphabetOps.union(csLower, csUpper);

    /**
     *
     */
    public static final Alphabet csUnd=AlphabetOps.singleton('_');

    /**
     *
     */
    public static final Alphabet csLetter=AlphabetOps.union(csChars, csUnd);

    /**
     *
     */
    public static final Alphabet csLetterDigit=AlphabetOps.union(csChars,csDigit);
    
    /**
     *
     * @param aString
     * @param aCsLetter
     * @param aCsName
     * @return
     */
    public static boolean isIdentifier(String aString, Alphabet aCsLetter, Alphabet aCsName){
         boolean vResult;
         if(aString.length()==0)
            vResult= false;
         else vResult=aCsLetter.has(aString.charAt(0));
         for(int i=1; i<aString.length();i++)
             vResult=vResult & aCsName.has(aString.charAt(i));
         return vResult;
    }
     
    /**
     *
     * @param aString
     * @return
     */
    public static boolean isIdentifier(String aString){
         boolean vResult;
         if(aString.length()==0)
            vResult= false;
         else vResult=csLetter.has(aString.charAt(0));
         for(int i=1; i<aString.length();i++)
             vResult=vResult & csLetterDigit.has(aString.charAt(i));
         return vResult;
    } 

    /**
     *
     * @param aString
     * @return
     */
    public static boolean isDigit(String aString){
         boolean vResult;
         if(aString.length()==0)
            vResult= false;
         else vResult=csDigit.has(aString.charAt(0));
         for(int i=1; i<aString.length();i++)
             vResult=vResult & csDigit.has(aString.charAt(i));
         return vResult;
    } 
 
    /**
     * Maps an integer (for instance, 3) to its string representation, prefixed with an underscore ( '_3' ).
     * Used for path expressions in trees.
     * 
     * @param aValue
     * @return
     */
        public static String indexStr(int aValue){
        return '_'+Integer.toString(aValue);
    }

}
