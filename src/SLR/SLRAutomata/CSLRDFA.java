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
public class CSLRDFA extends CSLRFA{

    /**
     *
     */
    protected int fErrorState;

    /**
     *
     */
    protected int[][] fTable;
	
    /**
     *
     */
    @Override
    protected final void addNewState(){
	    for(int i=0;i<=fAlphabetSize-1;i++){
                fTable[fStateCount][i]=fErrorState;
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
     * @param aFrom
     * @param aSymbol
     * @param aTo
     */
    public void addEdge(int aFrom,int aSymbol,int aTo){
		 fTable[aFrom][aSymbol]=aTo;
	}

        //Constructor for DFA elements
/*	public CSLRDFA(int aStartState,IntAlphabet aAlphabet,StateSet aFinalStates,StateSet aInitialStates,ArrayList<Integer> aOutput,int aStateCount,int[][] aTable, int aErrorState){
            super(aStartState,aAlphabet,aFinalStates,aInitialStates,aOutput,aStateCount);
            fTable=aTable;
            fErrorState=aErrorState;
        }*/

        //Constructor for DFA 

    /**
     *
     * @param aAlphabet
     */
    	public CSLRDFA(IntAlphabet aAlphabet){
	     super(aAlphabet);
	     fTable=new int[STATES][fAlphabetSize];
	     fOutput=new HashMap<Integer,COutput>();
             fInitialStates=new StateSet();
	     fStateCount=0;
	     fErrorState=0;
	     addNewState();
	     addNewState();
	     fStartState=1;
	     fInitialStates.addState(fStartState);
	     fFinalStates=new StateSet();
	}
 
    /**
     *
     * @param aState
     * @param aSymbol
     * @return
     */
    public int dTransition(int aState,int aSymbol){
              return fTable[aState][aSymbol];
           
	 }
   
    /**
     *
     * @return
     */
    public int errorState(){
		 return fErrorState;
	 }
	 
    /**
     *
     * @param aState
     * @param aSymbol
     * @return
     */
    public StateSet fwTransitions(int aState,int aSymbol){
		 StateSet result=new StateSet();
		 result.addState(dTransition(aState,aSymbol));
		 return result;
	 }
	 
    /**
     *
     * @param aState
     */
    public void setErrorState(int aState){
		 fErrorState=aState;
	 }
	
    /**
     *
     * @param aState
     */
    public void setStartState(int aState){
		 fStartState=aState;
	 }

    /**
     *
     * @return
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
             for(vState=0;vState<=stateCount()-1;vState++)
	         buffer.append(getOutputTable().get(vState)).append(", ");
             buffer.deleteCharAt(buffer.length()-2);
             return buffer.toString();
	}

    /*    public static CSLRDFA fromText(String aDFATableText){
            CSLRDFA vDFATableStructure;
            int vStartState;
            IntAlphabet vAlphabet;
            StateSet vFinalStates;
            StateSet vInitialStates;
            ArrayList<Integer> vOutput;
            int vStateCount;
            int [][] vTable;
            int vErrorState;

          BufferedReader br=null;
	  String line=null;
	  try{
                br=new BufferedReader(new StringReader(aDFATableText));
                line=br.readLine();   //skip StateCount
                line=br.readLine();
                vStateCount=Integer.parseInt(line);
                line=br.readLine();	//skip Alphabet
                line=br.readLine();
                //create an array of characters
                int [] vCharArray=new int[line.length()];
                for(int i=0;i<line.length();i++)
                     vCharArray[i]=line.charAt(i);
                vAlphabet=new IntAlphabet(vCharArray);
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
                ArrayList<String> row = new ArrayList<String>();
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
                vOutput=new ArrayList<Integer>();
                String[] aArrayOutput=line.split(", ");
                for(int i=0;i<aArrayOutput.length;i++)
                    vOutput.add(Integer.parseInt(aArrayOutput[i]));
                br.close();
         } catch (IOException e) {
 	        System.out.println("Could not read from String");
	        throw new Error();
         }
        vDFATableStructure=new CSLRDFA(vStartState,vAlphabet,vFinalStates,vInitialStates,vOutput,vStateCount,vTable,vErrorState);
        return vDFATableStructure;
        } */
}
