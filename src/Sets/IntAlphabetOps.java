/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Sets;

/**
 *
 * @author ssanyu
 */
public class IntAlphabetOps {

    /**
     *
     * @param i
     * @return
     */
    public static IntAlphabet singleton(int i){
		return new IntAlphabet(i);
	}

    /**
     *
     * @param aLow
     * @param aHigh
     * @return
     */
    public  static IntAlphabet range(int aLow, int aHigh){
		return new IntAlphabet(aLow, aHigh);
	}

    /**
     *
     * @param cArray
     * @return
     */
    public  static IntAlphabet enumerated(int[] cArray){
		return new IntAlphabet(cArray);
	}

    /**
     *
     * @param left
     * @param right
     * @return
     */
    public  static IntAlphabet union(IntAlphabet left, IntAlphabet right){
		IntAlphabet result = new IntAlphabet();
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
    public  static IntAlphabet intersection(IntAlphabet left, IntAlphabet right){
		IntAlphabet result = new IntAlphabet();
		result.bcUnion(left);
                result.bcIntersection(right);
                return result;
	}

    /**
     *
     * @param charSet
     * @return
     */
    public  static IntAlphabet complement(IntAlphabet charSet){
		IntAlphabet result = new IntAlphabet();
		result.bcUnion(charSet);
                result.bcComplement();
		return result;
	}

    /**
     *
     * @return
     */
    public static int cardinality(){
		IntAlphabet result = new IntAlphabet();
		return result.cardinality();
	}


}
