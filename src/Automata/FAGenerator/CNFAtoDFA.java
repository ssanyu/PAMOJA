package Automata.FAGenerator;


import Automata.NFADFA.CDFA;
import Automata.NFADFA.CNFA;
import Sets.StateSet;

/**
 * A class which converts an NFA to a DFA.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CNFAtoDFA implements INFAtoDFA{

   
    protected StateSet[] fNFAStateSets;
    protected StateSet[] fEpsClosure;
    protected CDFA fDFA;
    protected CNFA fNFA;
         
    /**
     * Computes epsilon closure of a given NFA.
     * 
     * @param aNFA the CNFA object.
     */
    protected void computeEpsClosure(CNFA aNFA){
		int k,i;
		fEpsClosure=new StateSet[aNFA.stateCount()];
		//initialize FEpsClosure to fNFA.fwEpsTransitions
		for(int h=0;h<=aNFA.stateCount()-1;h++)
			fEpsClosure[h]=aNFA.fwEpsTransitions(h);
		// transitive closure with Warshall's algorithm
		for (k=0;k<=fEpsClosure.length-1;k++ )
			for(i=0;i<=fEpsClosure.length-1;i++)
				if(fEpsClosure[i].has(k))
					fEpsClosure[i].bcUnion(fEpsClosure[k]);
		// add reflexive closure
		for (k=0;k<=fEpsClosure.length-1;k++ ){
			StateSet sK=new StateSet();
			sK.addState(k);
			fEpsClosure[k].bcUnion(sK);
		}
	 }
	 
    /**
     * Creates the DFA for the given NFA, using subset-construction algorithm.
     * 
     * @param aNFA the CNFA object.
     */
    @Override
	public void NFAtoDFA(CNFA aNFA) {
		int p,q,r,vDummy;
		fNFA=aNFA;
		computeEpsClosure(fNFA);
		//fNFAStateSets=new StateSet[fNFA.stateCount()];
                fNFAStateSets=new StateSet[fNFA.STATES];
		fDFA=new CDFA(fNFA.alphabet());
		fNFAStateSets[0]= new StateSet();
               	fNFAStateSets[1]=fEpsClosure[fNFA.startState()];
					
		p=1;
		q=2;
		while(p!=q){
			for(int i=fNFA.alphabet().nextSetBit(0); i>=0;i=fNFA.alphabet().nextSetBit(i+1)){
					fNFAStateSets[q]=new StateSet();
					for(int j=fNFAStateSets[p].nextSetBit(0); j>=0;j=fNFAStateSets[p].nextSetBit(j+1)){
			    		for(int vT=0;vT<=fNFA.stateCount()-1;vT++){
								if(fNFA.fwTransitions(j,(char)i).has(vT))
									fNFAStateSets[q].bcUnion(fEpsClosure[vT]);
						}
					}
				// linear search with sentinel
				r=0;
				while(!(fNFAStateSets[r].equals(fNFAStateSets[q]))){
					r=r+1;
		        }
				if(r==q){
					q=q+1;
		        	vDummy=fDFA.newState(); 
		        }
		        fDFA.fwTransitions(p,(char)i).addState(r); 
		       	fDFA.addEdge(p, (char)i, r);
                        }//end for-loop 
			p=p+1;
		}//end while-loop
                
		output(fDFA);
	}
	
    /**
     * Maps each DFA-accepting state to the token recognized in that state.
     * 
     * @param aDFA the CDFA object.
     */
    @Override
	public void output(CDFA aDFA){
                
		for(int i=0;i<=aDFA.stateCount()-1;i++){
                   //get the list of NFA states for each DFA state
                     aDFA.setNFAStateSet(i,fNFAStateSets[i]);
                   //get DFA output
			fNFAStateSets[i].bcIntersection(fNFA.finalStates());
			if(fNFAStateSets[i].size()!=0){
				
				aDFA.addFinalState(i);
				int s=0;
				while(!(fNFAStateSets[i].has(s))){
					s=s+1;
				}
				aDFA.setOutput(i,fNFA.getOutput(s));
                                aDFA.setOutputSymName(i, fNFA.getOutputSymName(s));
                                aDFA.setOutputSymKind(i, fNFA.getOutputSymKind(s));
                               
                                
			}
			
		}
              			
	}
	
    /**
     * Returns a DFA obtained from an NFA.
     * 
     * @return an instance of CDFA.
     */
    @Override
    public CDFA getDFA() {
		return fDFA;
    }
 
    /**
     * Returns a set of NFA states corresponding to a given DFA set.
     * 
     * @return an instance of StateSet.
     */
    @Override
	public StateSet getNFAStateSet(int aState) {
		return fNFAStateSets[aState];
	}
	 
	

}
	   

