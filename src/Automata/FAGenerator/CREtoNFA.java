package Automata.FAGenerator;


import Automata.NFADFA.CNFA;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.RegExpr.CRE_Char;
import GrammarNotions.RegExpr.CRE_Lexeme;
import GrammarNotions.RegExpr.CRE_MultiDot;
import GrammarNotions.RegExpr.CRE_MultiStick;
import GrammarNotions.RegExpr.CRE_Option;
import GrammarNotions.RegExpr.CRE_Plus;
import GrammarNotions.RegExpr.CRE_Range;
import GrammarNotions.RegExpr.CRE_Star;
import GrammarNotions.RegExpr.CRE_String;
import GrammarNotions.SyntaxExpr.CTerminalDec;
import Sets.Alphabet;
import Sets.AlphabetOps;
import SymbolStream.CSymKind;
import java.util.ArrayList;

/**
 * A class which converts regular expressions to NFA.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CREtoNFA implements IREtoNFA {

    protected CNFA fNFA;

    protected CGrammar GrammarStructure=new CGrammar();
	

      /*  protected Alphabet ToAlphabet(){

            Alphabet vAlphabet=new Alphabet(' ','~');
            return vAlphabet;
        }*/

    /**
     * Returns a finite set of input symbols from a given regular expression.
     * 
     * @param aRE the CRE object
     * @return an instance of Alphabet representing a finite set of symbols. 
     */
    
        protected Alphabet REtoAlphabet(CRE aRE){
		CRE vRE;
		Alphabet result;
                int i;
		switch(aRE.sortCode()){
                case CGrammarCodes.scREEmpty:
                       result=new Alphabet();
                    break;
		case CGrammarCodes.scREEps:
			result=new Alphabet();
			break;
		case CGrammarCodes.scRELexeme:
			CRE_Lexeme aId=(CRE_Lexeme)aRE;
			vRE=aId.getDef().getBody();
			result=REtoAlphabet(vRE);
			break;
		case CGrammarCodes.scREChar:
			CRE_Char aChar=(CRE_Char)aRE;
			result=AlphabetOps.singleton(aChar.Char());
			break;
		case CGrammarCodes.scRERange:
			CRE_Range aRange=(CRE_Range)aRE;
			result=AlphabetOps.range(aRange.Low(),aRange.High());
			break;
		case CGrammarCodes.scREString:
			CRE_String aString=(CRE_String)aRE;
			result=new Alphabet();
			for(i=0;i<=aString.Str().length()-1;i++){
				result.bcUnion(AlphabetOps.singleton(aString.Str().charAt(i)));
			}
		    break;
		case CGrammarCodes.scREMultiDot:
			CRE_MultiDot aDot=(CRE_MultiDot)aRE;
			result=new Alphabet();
			for(i=0;i<=aDot.List().termCount()-1;i++){
				result.bcUnion(REtoAlphabet(aDot.List().getElt(i)));
			}
			break;
		case CGrammarCodes.scREMultiStick:
			CRE_MultiStick aStick=(CRE_MultiStick)aRE;
			result=new Alphabet();
			for(i=0;i<=aStick.List().termCount()-1;i++){
				result.bcUnion(REtoAlphabet(aStick.List().getElt(i)));
			}
			break;

		case CGrammarCodes.scREStar:  
			CRE_Star aStar=(CRE_Star)aRE;
			result=REtoAlphabet(aStar.Arg());
			break;
		case CGrammarCodes.scREPlus:
			CRE_Plus aPlus=(CRE_Plus)aRE;
			result=REtoAlphabet(aPlus.Arg());
			break;
		case CGrammarCodes.scREOPtion:
			CRE_Option aOption=(CRE_Option)aRE;
			result=REtoAlphabet(aOption.Arg());
			break;

		default:
			result=new Alphabet();
			break;
		}
		return result;
}

    /**
     * Returns a finite set of input symbols from a given grammar.
     * 
     * @param aGrammarStructure the CGrammar object.
     * @return an instance of Alphabet representing a finite set of symbols. 
     */
    protected Alphabet DefinitionsToAlphabet(CGrammar aGrammarStructure){
		Alphabet result=new Alphabet();
		for(int i=0;i<=aGrammarStructure.getGrammarContext().getTerminalDefs().count()-1;i++){
			result.bcUnion(REtoAlphabet(aGrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getBody()));
		}
                /*for(int i=0;i<=aGrammarStructure.keywordsCount()-1;i++){
			result.bcUnion(REtoAlphabet(aGrammarStructure.getKeywordBody(i)));
		}*/
		return result;
	}

    /**
     * Returns a list of terminals from a given grammar.
     * 
     * @param aGrammarStructure the CGrammar object.
     * @return a string list of terminal names.
     */
    public ArrayList<String> TerminalsToSymList(CGrammar aGrammarStructure){
           ArrayList<String> result=new ArrayList<>();
           for(int i=0;i<=aGrammarStructure.getGrammarContext().getTerminalDefs().count()-1;i++){
               result.add(i,aGrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getName());
           }
           return result;
       }
	
    /**
     * Converts regular expressions to NFA using Thompson’s algorithm.
     * 
     * @param aFrom the integer representation of from-state.
     * @param aRE the CRE object.
     * @param aTo the integer representation of end-state.
     */
    protected void NFA(int aFrom,CRE aRE,int aTo){
		int i,vI,vF;
		CRE vRE;
		 switch(aRE.sortCode()){
                 case CGrammarCodes.scREEmpty:
                         break;
		 case CGrammarCodes.scREEps:
			 fNFA.addEps(aFrom, aTo);
			 break;
		 case CGrammarCodes.scRELexeme:
			 CRE_Lexeme aId=(CRE_Lexeme)aRE;
			 vRE=aId.getDef().getBody();
			 NFA(aFrom,vRE,aTo);
			 break;
		 case CGrammarCodes.scREChar:
			 CRE_Char aChar=(CRE_Char)aRE;
			 fNFA.addEdge(aFrom, aChar.Char(), aTo);
			 break;
		 case CGrammarCodes.scRERange:
			 CRE_Range aRange=(CRE_Range)aRE;
			 for(char c=aRange.Low();c<=aRange.High();c++){
				fNFA.addEdge(aFrom, c, aTo);
			 }
			 break;
		 case CGrammarCodes.scREString:
			 CRE_String aString=(CRE_String)aRE;
			 int length=aString.Str().length();
			 vI=aFrom;
			 for(i=0; i<=length-2;i++){
				 vF=fNFA.newState();
				 fNFA.addEdge(vI,aString.Str().charAt(i),vF);
				 vI=vF;
			 }
			 fNFA.addEdge(vI,aString.Str().charAt(length-1),aTo);
			 break;
		 case CGrammarCodes.scREMultiDot:
			 CRE_MultiDot aMulti=(CRE_MultiDot)aRE;
			 vI=aFrom;
			 int count=aMulti.List().termCount();
			 for(i=0;i<=count-2;i++){
				 vF=fNFA.newState();
				 NFA(vI,aMulti.List().getElt(i),vF);
				 vI=vF;
			 }
			 NFA(vI,aMulti.List().getElt(count-1),aTo);
			break;
		 case CGrammarCodes.scREMultiStick:
			 CRE_MultiStick aStick=(CRE_MultiStick)aRE;
			 count=aStick.List().termCount();
			 for(i=0;i<=count-1;i++){
				 NFA(aFrom,aStick.List().getElt(i),aTo);
			 }
			 break;
		 case CGrammarCodes.scREStar:
			 CRE_Star aStar=(CRE_Star)aRE;
			 vI=fNFA.newState();
			 vF=fNFA.newState();
			 fNFA.addEps(aFrom, vI);
			 fNFA.addEps(aFrom, aTo);
			 fNFA.addEps(vF, vI);
			 fNFA.addEps(vF, aTo);
			 NFA(vI,aStar.Arg(),vF);
			 break;
		 case CGrammarCodes.scREPlus:
			 CRE_Plus aPlus=(CRE_Plus)aRE;
			 vI=fNFA.newState();
			 vF=fNFA.newState();
			 fNFA.addEps(aFrom, vI);
			 fNFA.addEps(vF, vI);
			 fNFA.addEps(vF, aTo);
			 NFA(vI,aPlus.Arg(),vF);
			 break;
		 case CGrammarCodes.scREOPtion:
			 CRE_Option aOption=(CRE_Option)aRE;
			 NFA(aFrom,aOption.Arg(),aTo);
			 fNFA.addEps(aFrom, aTo);
		}
		}
    

    /**
     * Creates the NFA for the given regular expression.
     * 
     * @param aRE the CRE object.
     * @return the CNFA object representing the NFA.
     */
            @Override
	public CNFA singleREtoNFA(CRE aRE){
		int vFrom,vTo;
		fNFA=new CNFA(REtoAlphabet(aRE));
                vFrom=fNFA.startState();
		vTo=fNFA.newState();
		NFA(vFrom,aRE,vTo);
                fNFA.addInitialState(vFrom);
		fNFA.addFinalState(vTo);
                fNFA.setOutput(vTo,0);
                return fNFA;
	}

    /**
     * Creates the NFA for the given regular expression.
     * 
     * @param aRE the CRE object.
     * @return the CNFA object representing the NFA.
     */
  @Override
	public CNFA REtoNFA(CRE aRE){
		int vFrom,vTo;
		fNFA=new CNFA(REtoAlphabet(aRE));
		vFrom=fNFA.newState();
		vTo=fNFA.newState();
		NFA(vFrom,aRE,vTo);
		fNFA.addInitialState(vFrom);
		fNFA.addFinalState(vTo);
                return fNFA;
	}
	
    /**
     * Creates the NFA for the given grammar.
     * 
     * @param aGrammarStructure the CGrammar object.
     * @return the CNFA object representing the NFA.
     */
    @Override
	public CNFA DefinitionsToNFA(CGrammar aGrammarStructure){
		int vStart,vFrom,vTo;
		CTerminalDec vTerminal;
                               
		GrammarStructure=aGrammarStructure;
		fNFA=new CNFA(DefinitionsToAlphabet(GrammarStructure));
		vStart=fNFA.startState();
		for(int i=0;i<=GrammarStructure.getGrammarContext().getTerminalDefs().count()-1;i++){
			vFrom=fNFA.newState();
			vTo=fNFA.newState();
			NFA(vFrom,GrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getBody(),vTo);
			fNFA.addEps(vStart, vFrom);
			fNFA.addFinalState(vTo);
			fNFA.setOutputSymName(vTo,GrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getName());
			fNFA.setOutput(vTo,i);
                        fNFA.setSymNames(i,GrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getName());
                        
                        vTerminal=(CTerminalDec)GrammarStructure.getGrammarContext().getTerminalDefs().getElt(i);
                        if(vTerminal.hasData()){
                            fNFA.setSymKinds(i, CSymKind.VARIABLE);
                            fNFA.setOutputSymKind(vTo, CSymKind.VARIABLE);
                        } else{
                            fNFA.setSymKinds(i, CSymKind.FIXED);
                            fNFA.setOutputSymKind(vTo, CSymKind.FIXED);
                        }
		}
        return fNFA;
	}
	
    	
}
