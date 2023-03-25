package Automata.NFADFA;

import Sets.StateSet;

/**
 * Defines an interface for classes that can be used to create an NFA.
 *   
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface INFA {

    /**
     * Returns the integer representation of the newly added state.
     * 
     * @return the newly added state.
     */
    int newState();

    /**
     * Represents a forward transition function for a given state on a given input symbol.
     * 
     * @param aState the from state.
     * @param aSymbol the symbol from the set of input symbols (Alphabet).
     * @return the set of states reachable from aState, over aSymbol.
     */
    StateSet fwTransitions(int aState,char aSymbol);

    /**
     * Represents a backward transition function for a given state on a given input symbol.
     * 
     * @param aState the from state.
     * @param aSymbol the symbol from the set of input symbols (Alphabet).
     * @return the set of states reachable from aState, over aSymbol.
     */
    StateSet bwTransitions(int aState,char aSymbol);

    /**
     * Creates an edge from the given from-state and to-state over a given symbol.
     * 
     * @param aFrom the starting point of the edge.
     * @param aSymbol the input symbol for the edge.
     * @param aTo the end point of the edge.
     */
    void addEdge(int aFrom,char aSymbol,int aTo);
}
