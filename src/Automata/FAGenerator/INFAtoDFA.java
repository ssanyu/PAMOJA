package Automata.FAGenerator;


import Automata.NFADFA.CDFA;
import Automata.NFADFA.CNFA;
import Sets.StateSet;

/**
 * Interface describing the conversion of an NFA to DFA.
 *  * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface INFAtoDFA {
	
    /**
     * Converts the given NFA to a DFA, using subset-construction algorithm.
     * 
     * @param aNFA the CNFA object.
     */
    void NFAtoDFA(CNFA aNFA);

     /**
     * Maps each DFA-accepting state to the token recognized in that state.
     * 
     * @param aDFA the CDFA object.
     */
    void output(CDFA aDFA);

    /**
     * Returns a DFA obtained from an NFA.
     * 
     * @return an instance of CDFA.
     */
    CDFA getDFA();

     /**
     * Returns a set of NFA states corresponding to a given DFA set.
     * 
     * @return an instance of StateSet.
     */
    StateSet getNFAStateSet(int aState);
	
	
}
