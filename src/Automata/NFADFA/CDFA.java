package Automata.NFADFA;


import Sets.Alphabet;
import Sets.StateSet;
import SymbolStream.CSymKind;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * The CDFA class is an implementation of the abstract CFA class. It provides methods for building a DFA.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CDFA extends CFA implements IDFA {

   protected int fErrorState;
   protected int[][] fTable;
   protected ArrayList<String> fNFAStates=new ArrayList<>();
   protected ArrayList<String> fSymbols=new ArrayList<>();

   /**
     * Adds a new state to a DFA.
     * 
     */
    @Override
	protected final void addNewState(){
		for(int i=0;i<=fAlphabetSize-1;i++){
			fTable[fStateCount][i]=fErrorState;
    	}
       	fOutput.add(fStateCount,-1);
        fOutputSymName.add(fStateCount," ");
        fOutputSymKind.add(fStateCount,CSymKind.FIXED);
       	fStateCount=fStateCount+1;
    }
			
    /**
     * Returns the integer representation of the newly added state.
     * 
     * @return the newly added state.
     */
    @Override
	public int newState(){
	    addNewState();
	    return fStateCount-1;
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
    	StateSet result=new StateSet();
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
		 fTable[aFrom][fCharToIndexList.indexOf(aSymbol)]=aTo;
	}

    /**
     * Creates a new DFA with the specified components.
     * 
     * @param aStartState the start state
     * @param aAlphabet the set of input Alphabet
     * @param aFinalStates the set of final states
     * @param aInitialStates the set of initial states.
     * @param aOutput 
     * @param aStateCount the total number of states
     * @param aTable A transition table which is a mapping from states and input symbols to other states.
     * @param aErrorState the error state
     * @param aNFAStates the set of NFA-states 
     * @param aSymbols a string-list of input symbols.
     */
    	public CDFA(int aStartState,Alphabet aAlphabet,StateSet aFinalStates,StateSet aInitialStates,ArrayList<Integer> aOutput,int aStateCount,int[][] aTable, int aErrorState, ArrayList<String> aNFAStates, ArrayList<String> aSymbols){
            super(aStartState,aAlphabet,aFinalStates,aInitialStates,aOutput,aStateCount);
            fTable=aTable;
            fErrorState=aErrorState;
            fNFAStates=aNFAStates;
            fSymbols=aSymbols;
        }

     

    /**
     * Creates a new DFA with the given input Alphabet.
     * 
     * @param aAlphabet the set of input Alphabet
     */
    	public CDFA(Alphabet aAlphabet){
		 super(aAlphabet);
	     fTable=new int[STATES][fAlphabetSize];
	     fOutput=new ArrayList<>();
             fOutputSymName=new ArrayList<>();
             fOutputSymKind=new ArrayList<>();
	     fInitialStates=new StateSet();
             fSymbols=new ArrayList<>();
	    	
	     fStateCount=0;
		 fErrorState=0;
		 addNewState();
		 fOutput.add(fErrorState,-1);
		 fOutputSymName.add(fErrorState," ");
                 fOutputSymKind.add(fErrorState,CSymKind.FIXED);
                 addNewState();
		 fStartState=1;
		 fInitialStates.addState(fStartState);
		 fOutput.add(fStartState,-1);
		 fOutputSymName.add(fStartState," ");
                 fOutputSymKind.add(fStartState,CSymKind.FIXED);
                 fFinalStates=new StateSet();
                 
	}
    /**
     * Represents a transition function for a given state on a given input symbol.
     * 
     * @param aState the from state.
     * @param aSymbol the symbol from the set of input symbols (Alphabet).
     * @return the number of the reachable state, over aSymbol.
     */
    @Override
	 public int dTransition(int aState,char aSymbol){
           if(!fCharToIndexList.isEmpty() && fCharToIndexList.indexOf(aSymbol)!=-1 )
                return fTable[aState][fCharToIndexList.indexOf(aSymbol)];
           else return fErrorState;
	 }
    /**
     * Returns the error state of the DFA.
     * 
     * @return the number of the error state.
     */
    @Override
	 public int errorState(){
		 return fErrorState;
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
		 StateSet result=new StateSet();
		 result.addState(dTransition(aState,aSymbol));
		 return result;
	 }
	 
    /**
     * Sets the error state of the DFA to the given number.
     * 
     * @param aState the number representing the state.
     */
    public void setErrorState(int aState){
		 fErrorState=aState;
	 }
	
    /**
     * Sets the start state of the DFA to the given number.
     * 
     * @param aState the number representing the state.
     */
    public void setStartState(int aState){
		 fStartState=aState;
	 }
         
    /**
     * Returns the set of NFA states.
     * 
     * @return the list of NFA States.
     */
    public ArrayList<String> getNFAStateSet(){
             return fNFAStates;
         }
     
    /**
     * Maps a given set of NFA-states to a given DFA state.
     * 
     * @param aState the DFA state
     * @param aNFAStates the set of NFAStates associated with a DFA State.
     */
    public void setNFAStateSet(int aState,StateSet aNFAStates){
             String x=aNFAStates.toString();
             
             fNFAStates.add(aState,x);
                    
    }
         
    /**
     * Creates a string list of input symbols.
     * 
     * @param i the list position
     * @param aName the name of the symbol.
     */
    public void setSymbols(int i, String aName){
             fSymbols.add(i,aName);
    }

    /**
     * Returns a string list of input symbols.
     * 
     * @return the String list of input symbols.
     */
    public ArrayList<String> getSymbols(){
             return fSymbols;
         }

    /**
     * Returns the textual representation of a DFA.
     * 
     * @return DFA string representation.
     */
    public String toText(){ // textual representation of fDFA
             StringBuilder buffer=new StringBuilder();
             int vState;
             buffer.append("StateCount\n").append(stateCount());
             buffer.append("\nAlphabet\n");
             String aAlphabet="";
             for(int i=alphabet().nextSetBit(0); i>=0;i=alphabet().nextSetBit(i+1)){
		 aAlphabet=aAlphabet+(char)i;
	     }
	     buffer.append(aAlphabet);
	     buffer.append("\nInitialStates\n").append(initialStates().toString());
	     buffer.append("\nStartState\n").append(startState());
	     buffer.append("\nErrorState\n").append(errorState());
	     buffer.append("\nFinalStates\n").append(finalStates().toString());
	     buffer.append("\nDFA Table \n");
	     for(vState=0;vState<=stateCount()-1;vState++){
		  for(int j=alphabet().nextSetBit(0); j>=0;j=alphabet().nextSetBit(j+1)){
                     buffer.append(dTransition(vState,(char) j)).append(", ");
	          } 
                  buffer.deleteCharAt(buffer.length()-2);
		  buffer.append("\n");
	     }
            
	     buffer.append("Output Table\n");
             buffer.append(getOutputTable().get(0));
             for(vState=1;vState<=stateCount()-1;vState++)
	         buffer.append(", ").append(getOutputTable().get(vState));
             buffer.append("\n");
             buffer.append("Symbols\n").append(getSymbols().toString());
            // buffer.deleteCharAt(buffer.length()-1);
             return buffer.toString();
	}

    /**
     * Returns a structural representation of a DFA from a given textual representation.
     * 
     * @param aDFATableText the string representation of a DFA.
     * @return the CDFA object representing the DFA structure.
     */
    public static CDFA fromText(String aDFATableText){
            CDFA vDFATableStructure;
            int vStartState;
            Alphabet vAlphabet;
            StateSet vFinalStates;
            StateSet vInitialStates;
            ArrayList<Integer> vOutput;
            int vStateCount;
            int [][] vTable;
            int vErrorState;

          BufferedReader br;
	  String line;
	  try{
                br=new BufferedReader(new StringReader(aDFATableText));
                line=br.readLine();   //skip StateCount
                line=br.readLine();
                vStateCount=Integer.parseInt(line);
                line=br.readLine();	//skip Alphabet
                line=br.readLine();
                //create an array of characters
                char [] vCharArray=new char[line.length()];
                for(int i=0;i<line.length();i++)
                     vCharArray[i]=line.charAt(i);
                vAlphabet=new Alphabet(vCharArray);
                line=br.readLine(); //skip InitialStates
                line=br.readLine();
                String[] arInitialStates=line.split(", ");
                vInitialStates=new StateSet();
                for(int i=0;i<arInitialStates.length;i++)
                    vInitialStates.addState(Integer.parseInt(arInitialStates[i]));
                line=br.readLine(); //Skip StartState
                line=br.readLine();
                vStartState=Integer.parseInt(line);
                line=br.readLine(); //Skip StartState
                line=br.readLine();
                vErrorState=Integer.parseInt(line);
                line=br.readLine(); //Skip FinalStates
                line=br.readLine();
                String[] arFinalStates=line.split(", ");
                vFinalStates=new StateSet();
                for(int i=0;i<arFinalStates.length;i++)
                    vFinalStates.addState(Integer.parseInt(arFinalStates[i]));
                ArrayList<String> row = new ArrayList<>();
                line=br.readLine();// Skip DFA Table
                line=br.readLine();
                while(line!=null && !line.equals("Output Table")){
                    row.add(line);
                    line=br.readLine();
                }
                vTable=new int[row.size()][];
                for (int i=0;i<vTable.length;i++){
                    line= (String)row.get(i);
                    String[] rowOfTrans=line.split(",");
                    //convert rowOfTrans from String[] to int[]
                    int[] intarray=new int[rowOfTrans.length];
                    for(int h=0;h<rowOfTrans.length;h++)
        		intarray[h]=Integer.parseInt(rowOfTrans[h]);
                    vTable[i]=intarray;
                }
                line=br.readLine(); //read values of output Table
                vOutput=new ArrayList<>();
                String[] aArrayOutput=line.split(", ");
                for(int i=0;i<aArrayOutput.length;i++)
                    vOutput.add(Integer.parseInt(aArrayOutput[i]));
                br.close();
         } catch (IOException e) {
 	        System.out.println("Could not read from String");
	        throw new Error();
         }
        vDFATableStructure=new CDFA(vStartState,vAlphabet,vFinalStates,vInitialStates,vOutput,vStateCount,vTable,vErrorState,new ArrayList<String>(), new ArrayList<String>());
        return vDFATableStructure;
}

}
