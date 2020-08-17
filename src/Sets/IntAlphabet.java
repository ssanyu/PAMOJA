/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sets;

import GrammarNotions.Grammar.CGrammar;
import java.util.BitSet;

/**
 *
 * @author ssanyu
 */
public class IntAlphabet  {

    /**
     *
     */
    protected BitSet FMembers;
    /**
	  * Constructs an empty set of Integers.
	  *
	  */
	 public IntAlphabet()
	 {
		FMembers = new BitSet();
	 }

	 /**
	  * Constructs a set of Integers in a given range
	  * @param low The lower boundary of the set
	  * @param high The upper boundary of the set
          * @post  'this' represents the set {low..high}
	  */
	 public IntAlphabet(int low,int high)
	 {
		FMembers = new BitSet();
                FMembers.set(low,high);
                // add the character high
                FMembers.set(high);
	 }

         /**
          * Constructs a singleton set containing a given character
          * @param c  the character to be contained in the set
          * @post  'this' represents set {c}
          */
	 public IntAlphabet(int c){
             FMembers = new BitSet();
		 FMembers.set(c);
	 }

	 /**
	  * Constructs a set from an array of integers
          * 
          * @param aIntArray the array to be added to the set
	  * 
	  */
	  public IntAlphabet(int[] aIntArray) {
              FMembers = new BitSet();
	      for(int i=0; i<aIntArray.length; i++){
	 	FMembers.set(aIntArray[i]);
	      }
	  }
	
	  /**
	   * Detects whether i is a member of this set.
	   * @param  i The int sought.
	   * @return True iff i is a member of this set.
	   */
	  public boolean has(int i){
               if(!(i<0))
	 	return FMembers.get(i);
               else  return false;
          }

    /**
     *
     * @return
     */
    public int size(){
              return FMembers.length();
          }

    /**
     *
     * @param aAlphabet
     * @return
     */
    public boolean equals(IntAlphabet aAlphabet){
			return FMembers.equals(aAlphabet.FMembers);
	  }
	  
	  /**
	   * Constructs a character set that is the union of this and aCharset.
	   *
     * @param aIntset
	   * @pre aCharset is not null
	   * @post returns new set with characters from this or aCharset
	   */
	 public void bcUnion(IntAlphabet aIntset){
		 FMembers.or(aIntset.FMembers);
	 }

	 /**
	  * Computes the intersection of this character set and aIntset.
	  *
	  * @pre other is not null
	  * @post returns new set with characters from this and aIntset
	  *
	  * @param aIntset The other character set.
	  */
	 public void bcIntersection(IntAlphabet aIntset){
		 FMembers.and(aIntset.FMembers);
	 }

	 /**
	  * Computes the complement of a character set.
	  *
	  */
	 public void bcComplement(){
		 FMembers.flip(0,255);
	 }

	 /*
      * Next Bit Set
      *
      */
	 public int nextSetBit(int afromIndex){
		return FMembers.nextSetBit(afromIndex);
	 }
	 /*
      * Computes the number of bits set to true in the set of characters.
      *
      */

    /**
     *
     * @return
     */
    
	 public int cardinality(){
		return FMembers.cardinality();
	 }
         /*
          * Computes a string representation of the set of Integers.
          *
          */
               // Auxiliary routine to map IntAlphabet objects to strings
 
    /**
     *
     * @param aGrammar
     * @param aAlphabet
     * @return
     */
        public static String setToString(CGrammar aGrammar, IntAlphabet aAlphabet){ 
        StringBuffer vResult=new StringBuffer();
        if (aAlphabet.size()==0){
              vResult.append("{}");
        }else{
       
        vResult= vResult.append('{');
        if(aAlphabet.has(0)){
            vResult=vResult.append(aGrammar.getGrammarContext().getSymbol(0).getName());
            for(int i=1;i<=aGrammar.getGrammarContext().symbolCount()-1;i++){
                if(aAlphabet.has(i))
                    vResult=vResult.append(", ").append(aGrammar.getGrammarContext().getSymbol(i).getName());
            }
        }else{
            for(int i=1;i<=aGrammar.getGrammarContext().symbolCount()-1;i++){
                if(aAlphabet.has(i))
                    vResult=vResult.append(aGrammar.getGrammarContext().getSymbol(i).getName()).append(", ");
            }
            vResult.deleteCharAt(vResult.length()-1);
        }
        vResult=vResult.append('}');
        }
        return vResult.toString();
    }
 }
