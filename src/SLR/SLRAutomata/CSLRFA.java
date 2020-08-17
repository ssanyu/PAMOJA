/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SLR.SLRAutomata;



import SLR.SLRGenerator.COutput;
import Sets.IntAlphabet;
import Sets.StateSet;
import java.util.HashMap;

/**
 *
 * @author jssanyu
 */
public abstract class CSLRFA {

    /**
     *
     */
    protected IntAlphabet fAlphabet=new IntAlphabet();

    /**
     *
     */
    protected int fAlphabetSize;

    /**
     *
     */
    protected StateSet fInitialStates=new StateSet();

    /**
     *
     */
    protected StateSet fFinalStates=new StateSet();

    /**
     *
     */
    protected HashMap<Integer,COutput> fOutput=new HashMap<Integer,COutput>();

    /**
     *
     */
    protected int fStateCount;

    /**
     *
     */
    protected int fStartState;

    /**
     *
     */
    public int STATES=1000;
    
    /**
     *
     * @param aAlphabet
     */
    protected void initAlphabet(IntAlphabet aAlphabet){
       	fAlphabet=aAlphabet;
	fAlphabetSize=aAlphabet.cardinality();
    }
	
    /**
     *
     */
    protected abstract void addNewState();

            
   /** Constructs a FA with specified components.
     * @param aStartState
     * @param aAlphabet
     * @param aFinalStates
     * @param aInitialStates
     * @param aOutput
     * @param aStateCount */
   public CSLRFA(int aStartState,IntAlphabet aAlphabet,StateSet aFinalStates,StateSet aInitialStates,HashMap<Integer,COutput> aOutput,int aStateCount){
         fStartState=aStartState;
         fAlphabet=aAlphabet;
         fFinalStates=aFinalStates;
         fInitialStates=aInitialStates;
         fOutput=aOutput;
         fStateCount=aStateCount;
   }
   /** Constructs FA with a given alphabet
     * @param aAlphabet */
   public CSLRFA(IntAlphabet aAlphabet){
            initAlphabet(aAlphabet);
   }

    /**
     *
     * @return
     */
    public IntAlphabet alphabet(){
	return fAlphabet;
   }

    /**
     *
     * @return
     */
    public int startState(){
    	return fStartState;
   }

    /**
     *
     * @return
     */
    public StateSet initialStates(){
        return fInitialStates;
   }

    /**
     *
     * @return
     */
    public int stateCount(){
    	return fStateCount;
   }

    /**
     *
     * @return
     */
    public StateSet finalStates(){
    	return fFinalStates;
   }

    /**
     *
     * @param aState
     * @param aValue
     */
    public void setOutput(int aState, COutput aValue){
	if(fFinalStates.has(aState))
	   fOutput.put(aState,aValue);
   }

    /**
     *
     * @param aState
     * @return
     */
    public COutput getOutput(int aState){
    	//int result=-1;
    	if(fFinalStates.has(aState))
    	  return fOutput.get(aState);
    	return new COutput();
   }

    /**
     *
     * @param aState
     */
    public void addInitialState(int aState){
   	    fInitialStates.addState(aState);
   }
	
    /**
     *
     * @param aState
     */
    public void addFinalState(int aState){
        fFinalStates.addState(aState);
   }

    /**
     *
     * @return
     */
    public  HashMap<Integer,COutput> getOutputTable(){
        return fOutput;
    }

    
}
