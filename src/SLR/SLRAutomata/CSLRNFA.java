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
public class CSLRNFA extends CSLRFA{

    /**
     *
     */
    protected StateSet [] fEpsTable;

    /**
     *
     */
    protected StateSet [][] fTable;
	
    /**
     *
     */
    @Override
    protected void addNewState(){
    	fEpsTable[fStateCount]=new StateSet();
    	for(int i=0;i<=fAlphabetSize-1;i++){
    		fTable[fStateCount][i]=new StateSet();
    	}
    	fStateCount=fStateCount+1;
    }
     
    /**
     *
     * @return
     */
    public int newState(){
    	addNewState();
    	return fStateCount-1;
    }
	
    /**
     *
     * @param aAlphabet
     */
    public CSLRNFA(IntAlphabet aAlphabet){
    	super(aAlphabet);
    	fInitialStates=new StateSet();
    	fEpsTable=new StateSet[STATES];
    	fTable=new StateSet[STATES][fAlphabetSize];
    	fStateCount=0;
    	addNewState();
    	fStartState=0;
    	fInitialStates.addState(fStartState);
        fFinalStates=new StateSet();
        fOutput=new HashMap<Integer,COutput>();
    }
  
    /**
     *
     * @param aState
     * @param aSymbol
     * @return
     */
    public StateSet fwTransitions(int aState,int aSymbol){
    	return fTable[aState][aSymbol];
    }
    
    /**
     *
     * @param aState
     * @return
     */
    public StateSet fwEpsTransitions(int aState){
    	return fEpsTable[aState];
    }
     
    /**
     *
     * @param aFrom
     * @param aSymbol
     * @param aTo
     */
    public void addEdge(int aFrom,int aSymbol,int aTo){
    	fTable[aFrom][aSymbol].addState(aTo);
    }
   
    /**
     *
     * @param aFrom
     * @param aTo
     */
    public void addEps(int aFrom,int aTo){
    	fEpsTable[aFrom].addState(aTo);
    }
    
    /**
     *
     * @param aState
     * @return
     */
    public StateSet epsTable(int aState){
    	StateSet result=new StateSet();
    	if(fEpsTable.length!=0)
    	   result= fEpsTable[aState];
    	return result;
    }
    
    /**
     *
     * @return
     */
    public String toText(){ // textual representation of fNFA
             StringBuilder buffer=new StringBuilder();
             int vState;
             buffer.append("StateCount\n").append(stateCount());
             buffer.append("\nAlphabet\n");
             String aAlphabet="";
             for(int i=alphabet().nextSetBit(0); i>=0;i=alphabet().nextSetBit(i+1)){
		 aAlphabet=aAlphabet+','+i;
	     }
	     buffer.append(aAlphabet);
	     buffer.append("\nInitialStates\n").append(initialStates().toString());
	     buffer.append("\nStartState\n").append(startState());
	    // buffer.append("\nErrorState\n").append(errorState());
	     buffer.append("\nFinalStates\n").append(finalStates().toString());
	     buffer.append("\nSLRNFA Table \n");
	     for(vState=0;vState<=stateCount()-1;vState++){
		  for(int j=alphabet().nextSetBit(0); j>=0;j=alphabet().nextSetBit(j+1)){
                     buffer.append(fwTransitions(vState,j)).append(", ");
	          } 
                  buffer.deleteCharAt(buffer.length()-2);
		  buffer.append("\n");
	     }
            
	     buffer.append("Output Table\n");
             for(vState=0;vState<=stateCount()-1;vState++)
	         buffer.append(getOutput(vState).toString()).append(", ");
             buffer.deleteCharAt(buffer.length()-2);
             return buffer.toString();
	}
    
}
