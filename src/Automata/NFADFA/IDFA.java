package Automata.NFADFA;

/**
 * Interface <code>IDFA</code> extends <code>INFA</code> with methods which can be used to create a DFA.
 *   
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IDFA extends INFA {

     /**
     * Returns the error state of the DFA.
     * 
     * @return the number of the error state.
     */
    int errorState();

    /**
     * Represents a transition function for a given state on a given input symbol.
     * 
     * @param aState the from state.
     * @param aSymbol the symbol from the set of input symbols (Alphabet).
     * @return the number of the reachable state, over aSymbol.
     */
    int dTransition(int aState,char aSymbol);
   
}
