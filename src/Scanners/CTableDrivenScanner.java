/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scanners;

import SymbolStream.CSymKind;



/** CScanner_DFABase is an abstract base class which descends from CScanner
   and intended to be a base class for both DFA-driven scanners (i.e. scanners 
   using a CDFA object) and generated DFA scanners (i.e. scanners where the 
   tables have been generated as constant definitions in a Java compilation unit).
   @author jssanyu
 */
public abstract class CTableDrivenScanner extends CScanner{
    
    /**
     *
     * @return
     */
    protected abstract int startState();

    /**
     *
     * @return
     */
    protected  abstract int errorState();

    /**
     *
     * @param aState
     * @param aChar
     * @return
     */
    protected  abstract int nextState(int aState,char aChar);

    /**
     *
     * @param aState
     * @return
     */
    protected  abstract boolean isAcceptingState(int aState);

    /**
     *
     * @param aState
     * @return
     */
    protected  abstract int getOutput(int aState);

    /**
     *
     * @param aState
     * @return
     */
    protected  abstract String getOutputSymName(int aState);

    /**
     *
     * @param aState
     * @return
     */
    protected  abstract CSymKind getOutputSymKind(int aState);

    /**
     *
     * @param aSym
     * @return
     */
    protected  abstract String getNameOfSym(int aSym);
   
  
 /*  public String getSymName(){
    	return getNameOfSym(fSym);
   }*/

    /**
     *
     * @param aState
     * @return
     */
    
   
   public  String getOutputName(int aState){
       return getOutputSymName(aState);
   }

    /**
     *
     * @param aState
     * @return
     */
    public CSymKind getOutputKind(int aState){
       return getOutputSymKind(aState);
   }
   
    /**
     *
     */
    @Override
   public void nextSym(){
    	int vState;
    	int vSymLength;
    	int vSym=0;
        String vSymName="";
        CSymKind vSymKind=CSymKind.FIXED;
       //skip whitespace characters
    	while(csWhiteSpace.has(fChar)){
            nextCh();
        }
        if(fChar==EndMarker){}  
        vState=startState();
        vSymLength=0;
        fSymStart=fCharPos-1;
    	while(fChar!=EndMarker && !(csWhiteSpace.has(fChar)) && nextState(vState,fChar)!=errorState()){
            vState=nextState(vState,fChar);
    	    if(isAcceptingState(vState)){
    		fSymEnd=fCharPos;
    		vSymLength=fSymEnd-fSymStart;
    		vSym=getOutput(vState);
                vSymName=getOutputName(vState);
                vSymKind=getOutputKind(vState);
            }
    	    nextCh();
    	}
    	if(vSymLength==0){
    	       recover();
        }else{
    		fSymLength=vSymLength;
    		fSym=vSym;
                fSymName=vSymName;
                fSymKind=vSymKind;
                fSymValue= Text.substring(fSymStart,fSymEnd);
            //  fSymbol =new Symbol(fSym,fSymStart,fSymValue,fSymName,fSymLength);
                fCharPos=fSymEnd+1;
    	}
  
    }

    @Override
    public void recover(){
        while(!finished() && !(csWhiteSpace.has(fChar))&& nextState(startState(),fChar)==errorState()){
          nextCh();
        }
        fSym=-1;//Symbols.syError;
        fSymName="Error";
        fSymValue= Text.substring(fSymStart,fCharPos-1);
        fSymEnd=fCharPos-1;
        fSymLength=fSymEnd-fSymStart;//fSymValue.length();
        fSymKind=CSymKind.INVALID;
        //fSymbol=new Symbol(fSym,fSymStart,fSymValue,fSymName,fSymLength);
    }

    
}
