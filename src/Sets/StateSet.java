package Sets;

import java.util.BitSet;

/**
 *
 * @author HP
 */
public class StateSet {

    /**
     *
     */
    protected BitSet FStates;

    /**
     *
     */
    public StateSet(){
		FStates = new BitSet();    
	}

    /**
     *
     * @param aIntArray
     */
    public StateSet(int[] aIntArray){
	
	 	FStates=new BitSet();
	 	for(int i=0; i<aIntArray.length; i++){
	 		FStates.set(aIntArray[i]);
	 	}
	}

    /**
     *
     * @param aState
     * @return
     */
    public boolean has(int aState){
		return FStates.get(aState);
		
	}

    /**
     *
     * @param aSet
     * @return
     */
    public boolean equals(StateSet aSet){
		return FStates.equals(aSet.FStates);
	}
	
    /**
     *
     * @param aSet
     */
    public void bcUnion(StateSet aSet){
		 FStates.or(aSet.FStates);
	 }
	
    /**
     *
     * @param aSet
     */
    public void bcIntersection(StateSet aSet){
		 FStates.and(aSet.FStates);
	}
	
    /**
     *
     * @param left
     * @param right
     * @return
     */
    public static StateSet intersection(StateSet left, StateSet right){
		StateSet result = new StateSet();
		result.bcUnion(left);
                result.bcIntersection(right);
        return result;
	}

    /**
     *
     * @param aState
     */
    public void addState(int aState){
		FStates.set(aState);
	}

    @Override
        public Object clone(){
            return FStates.clone();
        }
	
    /**
     *
     * @return
     */
    public int size(){
		return FStates.cardinality();
	}

    /**
     *
     * @param afromIndex
     * @return
     */
    public int nextSetBit(int afromIndex){
			return FStates.nextSetBit(afromIndex);
		 }
    @Override
	public String toString(){
        StringBuffer result;
        if (FStates.length() == 0)
            //result =new StringBuffer("{}");
            result =new StringBuffer(" ");
        else{
           // result = new StringBuffer("{");
        	result=new StringBuffer();
            int i = FStates.nextSetBit(0);
            result.append("" + i);
            for(i = FStates.nextSetBit(i+1); i>=0; i = FStates.nextSetBit(i+1)){
                result.append(", "+ i);
            }
           // result.append("}");
        }
        
        return result.toString();
  }
}
