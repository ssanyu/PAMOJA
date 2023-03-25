package Automata.FAGenerator;

import Automata.NFADFA.CNFA;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.RegExpr.CRE;

/**
 * Interface describing the conversion of an regular expressions to NFA.
 *   
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IREtoNFA {
	
    /**
     * Converts a single regular expression to NFA.
     * 
     * @param aRE the CRE object.
     * @return the CNFA object representing the NFA.
     */
   CNFA REtoNFA(CRE aRE);

    /**
     * Converts a single regular expression to NFA.
     * 
     * @param aRE the CRE object.
     * @return the CNFA object representing the NFA.
     */
    CNFA singleREtoNFA(CRE aRE);

    /**
     * Converts an entire grammar to NFA.
     * 
     * @param aDefinitions the CGrammar object.
     * @return the CNFA object representing the NFA.
     */
    CNFA DefinitionsToNFA(CGrammar aDefinitions);
	
}
