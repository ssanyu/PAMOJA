package Automata.NFADFA;


import Sets.Alphabet;
import Sets.StateSet;
import SymbolStream.CSymKind;
import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class is used to provide methods for building finite automata.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public abstract class CFA {

    protected Alphabet fAlphabet=new Alphabet();

    protected int fAlphabetSize;

    protected StateSet fInitialStates=new StateSet();;

    protected StateSet fFinalStates=new StateSet();

    protected ArrayList<String> fSymNames=new ArrayList<>();

    protected ArrayList<CSymKind> fSymKinds=new ArrayList<>();

    protected ArrayList<Integer> fOutput=new ArrayList<>();

    protected ArrayList<String> fOutputSymName=new ArrayList<>();
   
    protected ArrayList<CSymKind> fOutputSymKind=new ArrayList<>();
   
    protected int fStateCount;

    protected int fStartState;
    /** An array with all the characters currently in the alphabet */
    protected List<Character> fCharToIndexList=new ArrayList<>();

    public int STATES=1000;
    
    /**
     * Creates an array of characters from the given alphabet.
     * 
     * @param aAlphabet the input alphabet.
     */
    protected void initAlphabet(Alphabet aAlphabet){

	fAlphabet=aAlphabet;
	fAlphabetSize=aAlphabet.cardinality();
	
	for(int i=aAlphabet.nextSetBit(0); i>=0;i=aAlphabet.nextSetBit(i+1)){
		fCharToIndexList.add((char)i);
	}
    }
	
    /**
     * Adds a new state to a finite automata.
     */
    protected abstract void addNewState();

            
     /** Constructs a FA with specified components.
      * 
     * @param aStartState - the start state
     * @param aInitialStates - the set of initial states.
     * @param aAlphabet - the input alphabet.
     * @param aStateCount - the total number of states
     * @param aOutput 
     * @param aFinalStates - the set of final states.
     */
        public CFA(int aStartState,Alphabet aAlphabet,StateSet aFinalStates,StateSet aInitialStates,ArrayList<Integer> aOutput,int aStateCount){
            fStartState=aStartState;
            fAlphabet=aAlphabet;
            fFinalStates=aFinalStates;
            fInitialStates=aInitialStates;
            fOutput=aOutput;
            fStateCount=aStateCount;
        }

        /** Constructs FA with a given alphabet.
         *
         * @param aAlphabet the input alphabet.
         */

	public CFA(Alphabet aAlphabet){
		initAlphabet(aAlphabet);
        }
	
    /**
     * Returns a list of characters for the input alphabet.
     * 
     * @return the list of characters.
     */
    public List<Character> charToIndexList(){
		return fCharToIndexList;
	}
	
    /**
     * Returns the input Alphabet.
     * 
     * @return the Alphabet.
     */
    public Alphabet alphabet(){
		return fAlphabet;
	}

    /**
     * Returns the start state of the FA.
     * 
     * @return the number representing the start state.
     */
    public int startState(){
    	return fStartState;
        }
	
    /**
     * Returns the set of initial states.
     * 
     * @return the StateState.
     */
    public StateSet initialStates(){
    	return fInitialStates;
         }

    /**
     * Returns the total number of states of the FA.
     * 
     * @return the total number of states.
     */
    public int stateCount(){
    	return fStateCount;
    }

    /**
     * Returns the set of final states of the FA.
     * 
     * @return StateSet object.
     */
    public StateSet finalStates(){
    	return fFinalStates;
    }

    /**
     *
     * @param aState
     * @param aValue
     */
    public void setOutput(int aState, int aValue){
	  	if(fFinalStates.has(aState))
		fOutput.add(aState,aValue);
	
    }

   /**
     * Inserts a given name of a symbol into the symbol-name list at the ith position.
     * 
     * @param i the ith position.
     * @param aName the symbol name to be inserted.
     */
    public void setSymNames(int i,String aName){
        fSymNames.add(i,aName);
    }

    /**
     * Returns the name of a symbol with the given integer code.
     * 
     * @param aSym the integer code.
     * @return the name of the symbol.
     */
    public String getNameOfSym(int aSym){
        return fSymNames.get(aSym);
    }
    
   
    /**
     * Returns a list of symbol names.
     * 
     * @return the string-list of symbol names.
     */
    public ArrayList<String> getSymNames(){
        return fSymNames;
    }
    
    /**
     * Inserts a given kind of a symbol into the symbol-kind list at the ith position.
     * 
     * @param i the ith position.
     * @param aKind the symbol kind to be inserted.
     */
    public void setSymKinds(int i,CSymKind aKind){
        fSymKinds.add(i,aKind);
    }

    /**
     * Returns a list of symbol kinds.
     * 
     * @return ArrayList of symbol kinds.
     */
    public ArrayList<CSymKind> getSymKinds(){
        return fSymKinds;
    }
    
   /**
     * Inserts a given name of a symbol into the Output-symbol-name list at the position specified in <code>aState</code>.
     * 
     * @param aState the state-number representing the list position.
     * @param aValue the symbol name to be inserted.
     */
    public void setOutputSymName(int aState,String aValue){
        if(fFinalStates.has(aState))
            fOutputSymName.add(aState,aValue);
    }
    
    /**
     * Inserts a given kind of symbol into the Output-symbol-kind list at the position specified in <code>aState</code>.
     * 
     * @param aState the state-number representing the list position.
     * @param aValue the symbol kind to be inserted.
     */
    public void setOutputSymKind(int aState,CSymKind aValue){
        if(fFinalStates.has(aState))
            fOutputSymKind.add(aState,aValue);
    }

    /**
     *
     * @param aState
     * @return
     */
    public int getOutput(int aState){
    	int result=-1;
    	if(fFinalStates.has(aState))
    	  result= fOutput.get(aState);
    	return result;
    }

    /**
     * Returns the name of a symbol accepted at a given state.
     * 
     * @param aState the integer state.
     * @return the name of the symbol accepted.
     */
    public String getOutputSymName(int aState){
        String result=" ";
        if(fFinalStates.has(aState))
            result=fOutputSymName.get(aState);
        return result;
    }
    
    /**
     * Returns the kind of symbol accepted at the given state.
     * 
     * @param aState the integer state.
     * @return the CSymKind object.
     */
    public CSymKind getOutputSymKind(int aState){
        CSymKind result=CSymKind.FIXED;
        if(fFinalStates.has(aState)){
            result=fOutputSymKind.get(aState);
        }
        return result;
    }

    /**
     * Inserts the specified state in the list of initial states.
     * 
     * @param aState the integer state to be inserted in the list.
     */
    public void addInitialState(int aState){
   	    fInitialStates.addState(aState);
   }
	
    /**
     * Inserts the specified state in the list of final states.
     * 
     * @param aState the integer state to be inserted into the list..
     */
    public void addFinalState(int aState){
        fFinalStates.addState(aState);

    }

    /**
     * 
     * @return
     */
    public  ArrayList<Integer> getOutputTable(){
        return fOutput;
    }

}
