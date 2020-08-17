package Sets;
import java.util.BitSet;

/**
 *
 * @author HP
 */
public class Alphabet {

    /**
     *
     */
    protected BitSet FMembers;

	/**
	  * Constructs an empty set of characters.
	  *
	  * @post 'this' represents the empty set of characters
	  */
	 public Alphabet()
	 {
		FMembers = new BitSet();     
	 }

	 /**
	  * Constructs a set of characters in a given range
	  * @param low The lower boundary of the set
	  * @param high The upper boundary of the set
          * @post  'this' represents the set {low..high}
	  */
	 public Alphabet(char low,char high)
	 {
		FMembers=new BitSet();

                // add the characters with index c s.t. low <= c < high
		FMembers.set(low,high); 
                // add the character high
                FMembers.set(high);
	 }

         /**
          * Constructs a singleton set containing a given character
          * @param c  the character to be contained in the set
          * @post  'this' represents set {c}
          */
	 public Alphabet(char c){
		 FMembers=new BitSet();
		 FMembers.set(c);
	 }

	 /**
	  * Constructs a set from an array of characters
	  * @param aCharArray The array to be added to the set
	  */
	  public Alphabet(char[] aCharArray)
	  {
	 	FMembers=new BitSet();
	 	for(int i=0; i<aCharArray.length; i++){
	 		FMembers.set(aCharArray[i]);
	 	}
	  }

    /**
     *
     * @param aAlphabet
     * @return
     */
    public boolean equals(Alphabet aAlphabet){
			return FMembers.equals(aAlphabet.FMembers);
		}
	  /**
	   * Detects whether aChar is a member of this set.
	   * @param  aChar The character sought.
	   * @return True iff aChar is a member of this set.
	   */
	  public boolean has(char aChar)
	  {
	 	return FMembers.get(aChar);
          }

	  /**
	   * Constructs a character set that is the union of this and aCharset.
	   *
	   * @pre aCharset is not null
	   * @post returns new set with characters from this or aCharset
	   *
	   * @param aCharset The other character set.
	   */
	 public void bcUnion(Alphabet aCharset){
             FMembers.or(aCharset.FMembers);
	 }

	 /**
	  * Computes the intersection of this character set and aCharset.
	  *
	  * @pre other is not null
	  * @post returns new set with characters from this and aCharset
	  *
	  * @param aCharset The other character set.
	  */
	 public void bcIntersection(Alphabet aCharset){
		 FMembers.and(aCharset.FMembers);
	 }

	 /**
	  * Computes the complement of a character set.
	  *
	  * @post returns complement of a set
	  */
	 public void bcComplement(){
		 FMembers.flip(0,255);
	 }
	 
	
    /**
     * Next Bit Set
     * @param afromIndex
     * @return
     */
    
	 public int nextSetBit(int afromIndex){
		return FMembers.nextSetBit(afromIndex);
	 }
	

    /**
     *Computes the number of bits set to true in the set of characters.
     * @return
     */
    
	 public int cardinality(){
		return FMembers.cardinality();
	 }
         
         
         /**
          * Computes a string representation of the set of characters.
          *
          */
	 
    @Override
	 public String toString(){
             String result;
             if (FMembers.length() == 0)
                 result = " ";// "{}";
             else{
                 StringBuilder sb = new StringBuilder();
                // sb.append("{'");
                 sb.append("'");
                 int i = FMembers.nextSetBit(0);
                 sb.append((char)i);
                 for(i = FMembers.nextSetBit(i+1); i>=0; i = FMembers.nextSetBit(i+1)){
                     sb.append("', '");
                     sb.append((char)i);
                 }
                 sb.append("'");
                // sb.append("'}");
                 result = sb.toString();
             }
             
             return result;
	 }


}
