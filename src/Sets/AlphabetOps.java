package Sets;

/**
 *
 * @author HP
 */
public class AlphabetOps {
	
    /**
     *
     * @param aChar
     * @return
     */
    public static Alphabet singleton(char aChar){
		return new Alphabet(aChar);
	}
	
    /**
     *
     * @param aLow
     * @param aHigh
     * @return
     */
    public  static Alphabet range(char aLow, char aHigh){
		return new Alphabet(aLow, aHigh);
	}
	
    /**
     *
     * @param cArray
     * @return
     */
    public  static Alphabet enumerated(char[] cArray){
		return new Alphabet(cArray);
	}

    /**
     *
     * @param left
     * @param right
     * @return
     */
    public  static Alphabet union(Alphabet left, Alphabet right){
		Alphabet result = new Alphabet();
		result.bcUnion(left);
                result.bcUnion(right);
                return result;
	}
    
    /**
     *
     * @param left
     * @param right
     * @return
     */
    public  static Alphabet intersection(Alphabet left, Alphabet right){
		Alphabet result = new Alphabet();
		result.bcUnion(left);
                result.bcIntersection(right);
                return result;
	}
	
    /**
     *
     * @param charSet
     * @return
     */
    public  static Alphabet complement(Alphabet charSet){
		Alphabet result = new Alphabet();
		result.bcUnion(charSet);
                result.bcComplement();
		return result;
	}
	
    /**
     *
     * @return
     */
    public static int cardinality(){
		Alphabet result = new Alphabet();
		return result.cardinality();
	}

}

         