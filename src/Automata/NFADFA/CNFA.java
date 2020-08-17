package Automata.NFADFA;


import Sets.Alphabet;
import Sets.StateSet;
import SymbolStream.CSymKind;
import java.util.ArrayList;

/**
 * The CNFA class is an implementation of the abstract CFA class. It provides methods for building the NFA.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CNFA extends CFA implements INFA{

    protected StateSet [] fEpsTable;
    protected StateSet [][] fTable;
	
    /**
     * Adds a new state to the NFA.
     * 
     */
    @Override
    protected void addNewState(){
    	fEpsTable[fStateCount]=new StateSet();
    	for(int i=0;i<=fAlphabetSize-1;i++){
    		fTable[fStateCount][i]=new StateSet();
    	}
    	fOutput.add(fStateCount,-1);
        fOutputSymName.add(fStateCount," ");
        fOutputSymKind.add(fStateCount,CSymKind.FIXED);
    	fStateCount=fStateCount+1;
    }
   	
    /**
     * Returns an integer representation of a new state.
     * 
     * @return integer state
     */
    @Override
    public int newState(){
    	addNewState();
    	return fStateCount-1;
    }
	
     /**
     * Creates a new NFA with the given input Alphabet.
     * 
     * @param aAlphabet the set of input Alphabet
     */
    public CNFA(Alphabet aAlphabet){
    	super(aAlphabet);
    	fInitialStates=new StateSet();
    	fEpsTable=new StateSet[STATES];
    	fTable=new StateSet[STATES][fAlphabetSize];
    	fOutput=new ArrayList<>();
        fOutputSymName=new ArrayList<>();
        fOutputSymKind=new ArrayList<>();
        fStateCount=0;
    	addNewState();
    	fStartState=0;
    	fInitialStates.addState(fStartState);
        fOutput.add(0,-1);
        fOutputSymName.add(0," ");
        fOutputSymKind.add(0,CSymKind.FIXED);
        fFinalStates=new StateSet();
    }
  
    /**
     * Represents a forward transition function for a given state on a given input symbol.
     * 
     * @param aState the from state.
     * @param aSymbol the symbol from the set of input symbols (Alphabet).
     * @return the set of states reachable from aState, over aSymbol.
     */
    @Override
    public StateSet fwTransitions(int aState,char aSymbol){
    	return fTable[aState][fCharToIndexList.indexOf(aSymbol)];
    }
    
    /**
     * Represents a forward transition function for a given state on Epsilon.
     * 
     * @param aState the from state.
     * @return the set of states reachable from aState, over Epsilon.
     */
    public StateSet fwEpsTransitions(int aState){
    	return fEpsTable[aState];
    }
    
    /**
     * Represents a backward transition function for a given state on a given input symbol.
     * 
     * @param aState the from state.
     * @param aSymbol the symbol from the set of input symbols (Alphabet).
     * @return the set of states reachable from aState, over aSymbol.
     */
    @Override
    public StateSet bwTransitions(int aState,char aSymbol){
    	int vState;
    	StateSet result;
    	result=new StateSet();
        for(vState=0;vState<=fStateCount-1; vState++){
        	if(fwTransitions(vState,aSymbol).has(aState)){
        		result.addState(vState);
        	}
        }
        return result;

    }

     /**
     * Represents a backward transition function for a given state on Epsilon.
     * 
     * @param aState the from state.
     * @return the set of states reachable from aState, over Epsilon.
     */
    public StateSet bwEpsTransitions(int aState){
    	int vState;
    	StateSet result;
    	
    	result=new StateSet();
        for(vState=0;vState<=fStateCount-1; vState++){
        	if(fwEpsTransitions(vState).has(aState)){
        		result.addState(vState);
        	}
        }
        return result;
    }
   
    /**
     * Creates an edge from the given from-state and to-state over a given symbol.
     * 
     * @param aFrom the starting point of the edge.
     * @param aSymbol the input symbol for the edge.
     * @param aTo the end point of the edge.
     */
    @Override
    public void addEdge(int aFrom,char aSymbol,int aTo){
    	fTable[aFrom][fCharToIndexList.indexOf(aSymbol)].addState(aTo);
    }
   
    /**
     * Creates an edge from the given from-state and to-state over epsilon.
     * @param aFrom
     * @param aTo
     */
    public void addEps(int aFrom,int aTo){
    	fEpsTable[aFrom].addState(aTo);
    }
    
    /**
     * Returns a set of states reachable from a given state over epsilon.
     * 
     * @param aState the integer state
     * @return a set of states.
     */
    public StateSet epsTable(int aState){
    	StateSet result=new StateSet();
    	if(fEpsTable.length!=0)
    	   result= fEpsTable[aState];
    	return result;
    }
}
