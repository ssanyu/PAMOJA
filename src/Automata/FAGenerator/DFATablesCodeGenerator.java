/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Automata.FAGenerator;


import Automata.NFADFA.CDFA;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.SyntaxExpr.CTerminalDef_List;
import Java.BodyDec_List;
import Java.ImportDec_List;
import Java.JClassDeclaration;
import Java.JCompilationUnit;
import Java.JFieldDec;
import Java.JImportDec;
import Java.JMethodDec;
import Java.JModifiers;
import Java.TypeDec_List;
import Sets.Alphabet;
import java.util.Date;

/**
 * A class which generates DFA tables as constant definitions in source-code file.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class DFATablesCodeGenerator {
    private CDFA fDFA;
    private CGrammar fGrammar;
    private String fGrammarName;
    private String fPackage;
    private final String fmSymcodeName="sy%s";
    private final String fmGrammarNameToClassName="CScannerDFA_%s";
    private final String comments=
            "/*====================================================\n"+
       	        "This file has been generated automatically\n\n"+
                "Generator : ScanTables Source code generator\n"+
                "Date       : "+new Date()+"\n"+
             "=====================================================*/\n\n";
    private final String fmTerminalDefListSymbolNames=
            "public static final String symbolNames[]={"+
            "       %s\n"+
            "     };";
    private final String fmstartStateMethod=
            "public int startState() {\n"+
            "         return fStartState;\n"+
            "     }";
    private final String fmerrorStateMethod=
            "public int errorState() {\n"+
            "         return fErrorState;\n"+
            "     }";
    private final String fmstateCountMethod=
            "public int stateCount() {\n"+
            "         return fStateCount;\n"+
            "     }";
    private final String fmisAcceptMethod=
            "public boolean isAcceptingState(int aState) {\n"+
            "         return fFinalStates.has(aState);\n"+
            "     }";
    
    private final String fmSymNameMethod=
            "public String getNameOfSym(int aSym) {\n"+
            "         return symbolNames[aSym];\n"+
            "     }";
    
    private final String fmOutputMethod=
            "public int getOutput(int aState) {\n"+
            "         return fAccepted[aState];\n"+
            "     }";
    private final String fmNextStateMethod=
            "public int nextState(int aState,char aChar) {\n"+
            "         return fTransitionTable[aState][fCharToIndexList.indexOf(aChar)];\n"+
            "     }";
    private final String fmGetOutputSymNameMethod=
            "public String getOutputSymName(int aState) {\n"+
            "         return fOutputSymbolNames[aState];\n"+
            "     }";
    private final String fmGetOutputSymKindMethod=
            "public String getOutputSymKind(int aState) {\n"+
            "         return fOutputSymbolKinds[aState];\n"+
            "     }";
    
    /**
     *  Creates a new <code> DFATablesCodeGenerator </code>.
     */
    public DFATablesCodeGenerator(){
        fDFA=new CDFA(new Alphabet());
        fGrammar=new CGrammar();
    }
    
    /**
     * Sets the Grammar object.
     * 
     * @param aGrammar the CGrammar object.
     */
    public void setGrammar(CGrammar aGrammar){
        fGrammar=aGrammar;
    }
   
    /**
     * Sets the DFA object.
     * 
     * @param aDFA the CDFA object.
     */
    public void setDFA(CDFA aDFA){
        fDFA=aDFA;
    }
   
    /**
     * Sets the name of the grammar.
     * 
     * @param aGrammarName the grammar's name.
     */
    public void setGrammarName(String aGrammarName){
        fGrammarName=aGrammarName;
    }
   
    /**
     * Returns the name of a grammar.
     * 
     * @return the grammar's name.
     */
    public String getGrammarName(){
        return fGrammarName;
    }

    /**
     * Sets the name of the package for storing the generated Java source files for the DFA.
     * 
     * @param aPackage the string used to set the Package's name.
     */
    public void setPackage(String aPackage){
        fPackage=aPackage;
    }
   
    /**
     * Returns the package name for storing the generated Java source files for the DFA.
     * 
     * @return the string for the package name.
     */
    public String getPackage(){
        return fPackage;
    }
   
    /**
     * Generates DFA tables, form a given Grammar, as constant definitions in a Java compilation unit.
     * 
     * @param aGrammar the CGrammar object used in the generation of DFA tables.
     * @return the Java compilation unit with constant definitions of the DFA tables.
     */
    public JCompilationUnit ECFGtoCompilationUnit(CGrammar aGrammar ) {
      
       String vPackage="package "+fPackage+';';
       ImportDec_List vImports=new ImportDec_List();
       TypeDec_List vList=new TypeDec_List(); 
       fGrammar=aGrammar;
       
       vImports.add(new JImportDec("import GrammarNotions.Sets.StateSet"));
       vImports.add(new JImportDec("import Scanners.CScanner_DFABase"));  
       vImports.add(new JImportDec("import java.util.Arrays"));
       vImports.add(new JImportDec("import java.util.List"));     

       //generate scan tables class
       vList.add(DFAToClassDeclaration());
            
       JCompilationUnit cu=new JCompilationUnit(vPackage,
                                                vImports,
                                                f_generateComments(),
                                                vList);
       return cu;
 
   }
   
    /**
     * Creates a Java class declaration for the DFA tables.
     * 
     * @return the Java class declaration for the DFA tables.
     */
    public JClassDeclaration DFAToClassDeclaration(){
       JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
	
        BodyDec_List members=new BodyDec_List();
        
        //Generate Constants
        CTerminalDef_List vList=fGrammar.getGrammarContext().getTerminalDefs();
        int i;
        for(i=0;i<vList.count();i++){
           members.add(new JFieldDec("public static final int "+makeSymCodeName(vList.getElt(i).getName())+" = "+i+';')); 
        }
        members.add(new JFieldDec("public static final int syerror =  "+i++ +";")); 
        
        members.add(new JFieldDec(terminalsToArrayofSymbolNames(vList)));
       
        members.add(new JFieldDec("private int fStartState = "+ fDFA.startState()+";"));
        members.add(new JFieldDec("private int fErrorState = "+ fDFA.errorState()+";"));
        members.add(new JFieldDec("private int fStateCount = "+ fDFA.stateCount()+";"));
        members.add(new JFieldDec("private List<Character> fCharToIndexList = Arrays.asList("+fDFA.alphabet()+");"));
        members.add(new JFieldDec("private int[] arrAacceptingStates = {"+ fDFA.finalStates()+"};")); 
        members.add(new JFieldDec("private StateSet fFinalStates = new StateSet(arrAacceptingStates);"));
        members.add(new JFieldDec("private int[][] fTransitionTable = "+ "new int[][]{"+printDFAtable()+"\n		};\n"));
        
        String vOutput="{"+fDFA.getOutput(0);
        for(int vState=1;vState<=fDFA.stateCount()-1;vState++){
			   vOutput=vOutput+", "+fDFA.getOutput(vState);
	}
	vOutput=vOutput+"}";
		
        members.add(new JFieldDec("private int[] fAccepted = new int[]"+vOutput+";"));
        
        // output symbolnames
        String vNames="{"+"\""+fDFA.getOutputSymName(0)+"\"";
        for(int vState=1;vState<=fDFA.stateCount()-1;vState++){
			   vNames=vNames+", "+"\""+fDFA.getOutputSymName(vState)+"\"";
	}
	vNames=vNames+"}";
        members.add(new JFieldDec("private String[] fOutputSymbolNames = "+vNames+";"));
        
        
        // output symbolkinds
        String vKinds="{"+"\""+fDFA.getOutputSymKind(0)+"\"";
        for(int vState=1;vState<=fDFA.stateCount()-1;vState++){
			   vKinds=vKinds+", "+"\""+fDFA.getOutputSymKind(vState)+"\"";
	}
	vKinds=vKinds+"}";
        members.add(new JFieldDec("private CSymKind[] fOutputSymbolKinds = "+vKinds+";"));
        
        
        members.add(new JMethodDec(fmstartStateMethod));
        members.add(new JMethodDec(fmerrorStateMethod));
        members.add(new JMethodDec(fmstateCountMethod));
        members.add(new JMethodDec(fmisAcceptMethod));
        members.add(new JMethodDec(fmSymNameMethod));
        members.add(new JMethodDec(fmOutputMethod));
        members.add(new JMethodDec(fmNextStateMethod));
        members.add(new JMethodDec(fmGetOutputSymNameMethod));
        members.add(new JMethodDec(fmGetOutputSymKindMethod));
        
        //JClassDeclaration(List<JModifier>modifiers, String name, String baseClass,ClassOrInterfaceType_List interfaces,BodyDec_List members)
        JClassDeclaration cd=new JClassDeclaration(vModifiers,
                                                   grammarNameToClassName(fGrammarName),
                                                   "CTableDrivenScanner",
                                                   null,
                                                   members);
    	return cd;
       
   }
  
  /**
     * Returns the name given to the grammar as the name of the Java class for the DFA tables.
     * 
     * @return a string for the name of a Java class storing the DFA tables.
     * @param aName the name for the Java class.
     */
  private String grammarNameToClassName(String aName){
         return String.format(fmGrammarNameToClassName,aName);
  }  
  
  /**
     * Returns a field-name for storing the integer representation of an input symbol.
     * 
     * @return the field-name.
     * @param aName the name of the input symbol.
     */
   private String makeSymCodeName(String aName){
        return String.format(fmSymcodeName,aName);
  }
  /**
     * Returns the comments describing the generated DFA-table constants.
     * 
     * @return the string representation of the comments.
     */
  private String f_generateComments(){
      return generateRelevantGrammarParts();
     //   return comments+ generateRelevantGrammarParts();
  }
  /**
     * Returns a StringBuffer containing the string representation of the DFA-table constants.
     * 
     * @return the string representation of the DFA-table constants.
     */
  private String terminalsToArrayofSymbolNames(CTerminalDef_List aList){
      String vNames="";
     
      for(int k=0;k<aList.contextCount();k++){
  	    vNames=vNames+"\n        \""+aList.getElt(k).getName()+"\""+",";
      }
      vNames=vNames+"\n        \"error\"";
      return String.format(fmTerminalDefListSymbolNames,vNames);
  }
  
  /**
     * Returns a StringBuffer containing the string representation of the DFA-table constants.
     * 
     * @return the string representation of the DFA-table constants.
     */
   private String generateRelevantGrammarParts(){
            String vLexemes="",vSymbols="";
            
            for(int i=0;i<fGrammar.getGrammarContext().lexemeCount();i++)
                vLexemes=vLexemes+fGrammar.getGrammarContext().getLexemeName(i)+"="+fGrammar.getGrammarContext().getLexemeBody(i).toText()+"\n";
            for(int i=0;i<fGrammar.getGrammarContext().terminalCount();i++)
                vSymbols=vSymbols+fGrammar.getGrammarContext().getTerminalName(i)+"="+fGrammar.getGrammarContext().getTerminalBody(i).toText()+"\n";
            

            return "/*Relevant Grammar Parts:\n[Lexemes]\n"+vLexemes+"\n[Terminals]\n"+vSymbols+"*/\n\n";

    }

    /**
     * Returns a StringBuffer containing the string representation of the DFA-table constants.
     * 
     * @return the string representation of the DFA-table constants.
     */
    public StringBuffer printDFAtable(){
       StringBuffer result=new StringBuffer();
       for(int i=0;i<=fDFA.stateCount()-1;i++){
           result.append("\n");
	   result.append("		{");
	   int j = fDFA.alphabet().nextSetBit(0);
	   result.append(fDFA.dTransition(i,(char)j));
	   for(j=fDFA.alphabet().nextSetBit(j+1); j>=0;j=fDFA.alphabet().nextSetBit(j+1)){
		result.append(",").append(fDFA.dTransition(i,(char)j));
	   }
	   result.append('}');
	   result.append(',');
       }
       result=result.deleteCharAt(result.length()-1);
       return result;	
  }
      
}
