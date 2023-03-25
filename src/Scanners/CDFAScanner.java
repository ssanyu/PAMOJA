/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scanners;

import Automata.NFADFA.CDFA;
import Sets.Alphabet;
import SymbolStream.CSymKind;



/** Base class for DFA-driven scanners (i.e. scanners 
 * using a CDFA object)
 *
 * @author jssanyu
 */
public class CDFAScanner extends CTableDrivenScanner {
    
    private CDFA   fDFA; 
   
    /**
     *
     */
    public CDFAScanner(){
       super();
       fDFA=new CDFA(new Alphabet());
    }
 
    /**
     *
     * @param aDFA the CDFA object
     */
    public void setDFA(CDFA aDFA){
       fDFA=aDFA;
    }

    /**
     *
     * @return the DFA object.
     */
    public CDFA getDFA(){
        return fDFA;
    }
    @Override
    protected int startState(){
       	return fDFA.startState();
    }

    /**
     *
     * @return
     */
    @Override
    protected int errorState(){
	return fDFA.errorState();
    }

    @Override
    protected int nextState(int aState,char aChar){
        return fDFA.dTransition(aState,aChar);
    }

    /**
     *
     * @param aState
     * @return
     */
    @Override
    protected boolean isAcceptingState(int aState){
    	return fDFA.finalStates().has(aState);
    }

    /**
     *
     * @param aState
     * @return
     */
    @Override
    protected int getOutput(int aState){
    	return fDFA.getOutput(aState);
    }

    /**
     *
     * @param aState
     * @return
     */
    @Override
    public String getOutputSymName(int aState){
      return fDFA.getOutputSymName(aState);
    }

    /**
     *
     * @return
     */
    @Override
   public String getSymValue(){
     return fSymValue;
   }

    /**
     * Returns the name of the specified symbol code. 
     * @param aSym the integer symbol code
     * @return the name of a symbol.
     */
    @Override
   protected String getNameOfSym(int aSym) { // Not sure about the return value.
     return "";//fDFA.getNameOfSym(aSym);
   }

    /**
     *
     * @param aState
     * @return
     */
    @Override
    protected CSymKind getOutputSymKind(int aState) {
       return fDFA.getOutputSymKind(aState);
    }
  
}
