/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LL.LLTable;

import Analyzers.CGrammarAnalyzer;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.SyntaxExpr.CNonTerminalDef;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSE_Eps;
import GrammarNotions.SyntaxExpr.CSE_MultiDot;
import GrammarNotions.SyntaxExpr.CSE_MultiStick;
import GrammarNotions.SyntaxExpr.CSE_Sym;
import Sets.IntAlphabet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author HP
 */
public class GrammartoLLTableGenerator {

    /**
     *
     */
    protected CGrammar grammarStructure;

    /**
     *
     */
    protected ArrayList<String> terminalAlphabet;

    /**
     *
     */
    protected ArrayList<String> nonTerminals;

    /**
     *
     */
    protected ArrayList<HashMap<String,ArrayList<COutput>>> productionOutputs;

    /**
     *
     */
    protected COutput output;

    /**
     *
     */
    protected CGrammarAnalyzer g;

    /**
     *
     */
    protected String LLTable[][];

    /**
     *
     * @param terminalAlphabet
     * @param productionOutputs
     */
    public GrammartoLLTableGenerator(ArrayList<String> terminalAlphabet, ArrayList<HashMap<String, ArrayList<COutput>>> productionOutputs) {
        this.terminalAlphabet = terminalAlphabet;
        this.productionOutputs = productionOutputs;
    }

    /**
     *
     */
    public GrammartoLLTableGenerator() {
        g=new CGrammarAnalyzer();
        terminalAlphabet=new ArrayList();
        productionOutputs=new ArrayList();
        nonTerminals=new ArrayList();
        LLTable=new String[productionOutputs.size()][terminalAlphabet.size()];
    }
    
    /**
     *
     * @param aGrammarStructure
     * @return
     */
    protected IntAlphabet GrammarToIntAlphabet(CGrammar aGrammarStructure){
		IntAlphabet vResult;
                int[] vSymbolSet=new int[aGrammarStructure.getGrammarContext().symbolCount()];
		for(int i=0;i<=aGrammarStructure.getGrammarContext().symbolCount()-1;i++){
		     vSymbolSet[i]=aGrammarStructure.getGrammarContext().getSymbol(i).getNumber();
		}
                vResult=new IntAlphabet(vSymbolSet);
                return vResult;
    }

    /**
     *
     * @param aGrammarStructure
     * @return
     */
    public ArrayList<String> grammartoTerminalAlphabet(CGrammar aGrammarStructure){
              ArrayList<String> strAlph=new ArrayList<String>();
              for(int i=0;i<aGrammarStructure.getGrammarContext().getTerminalDefs().count();i++){
                    strAlph.add(aGrammarStructure.getGrammarContext().getTerminalName(i));
                   
	      }   
              return strAlph;
        }  

    /**
     *
     * @param aGrammarStructure
     * @return
     */
    public ArrayList<String> grammartoNonTerminals(CGrammar aGrammarStructure){
              ArrayList<String> strAlph=new ArrayList<String>();
              for(int i=0;i<aGrammarStructure.getGrammarContext().getNonTerminalDefs().count();i++){
		     strAlph.add(aGrammarStructure.getGrammarContext().getNonterminalName(i));
		}
              
              return strAlph;
        }  
    
  /*  public ArrayList<String> grammartoTerminalAlphabet(CGrammar aGrammarStructure){
        ArrayList<String> strAlph = new ArrayList();
              String s;
              String vSymbol;
              CTerminalDef vTermDef;
              for(int i=0;i<=aGrammarStructure.getGrammarContext().getTerminalDefs().count()-1;i++){
                     vTermDef=aGrammarStructure.getGrammarContext().getTerminalDefs().getElt(i);
                     CRE vRE=vTermDef.getBody();
                     if(vTermDef.hasData()){
                         vSymbol=aGrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getName(); 
                     }else if(!(vRE instanceof CRE_Char || vRE instanceof CRE_Empty || vRE instanceof CRE_Eps || vRE instanceof CRE_String)){
                          vSymbol=aGrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getName(); 
                     }else                
                   
                         vSymbol=vTermDef.getBody().toString();
                   
                     strAlph.add(vSymbol);
	      }
              for(int i=0;i<=aGrammarStructure.getGrammarContext().getNonTerminalDefs().count()-1;i++){
		     strAlph.add(aGrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getName());
		}
              
              return strAlph; 
    }*/

    /**
     *
     * @param aNonTerminal
     * @return
     */
    
    public HashMap<String,ArrayList<COutput>> productiontoOutputs(CNonTerminalDef aNonTerminal){
        HashMap<String,ArrayList<COutput>> result=new HashMap();
        COutput o;
        ArrayList<COutput> vList=new ArrayList();
        String vName=aNonTerminal.getName();
        CSE body=aNonTerminal.getBody();
        switch(body.sortCode()){
                 case CGrammarCodes.scSEEps:
			 CSE_Eps aEps=(CSE_Eps)body;
			 o=new COutput();
                         o.setProdName(vName);
                         o.setI(0);
                         o.setFirst(g.setToStringList(grammarStructure,g.analysisOfECFG(aEps).fFirst));
                         o.setFollow(g.setToStringList(grammarStructure,g.analysisOfECFG(aEps).fFollow));
                         o.setProd(vName+"="+aEps.toString());
                         vList.add(o);
                         result.put(vName,vList);
			 break;
		 case CGrammarCodes.scSESym:           
                         CSE_Sym aSym=(CSE_Sym)body;
                         o=new COutput();
                         o.setProdName(vName);
                         o.setI(0);
                         o.setFirst(g.setToStringList(grammarStructure,g.analysisOfECFG(aSym).fFirst));
                         o.setFollow(g.setToStringList(grammarStructure,g.analysisOfECFG(aSym).fFollow));
                         o.setProd(vName+"="+aSym.toString());
                         vList.add(o);
                         result.put(vName,vList);
                        break;
		 case CGrammarCodes.scSEMultiDot:
			 CSE_MultiDot aMulti=(CSE_MultiDot)body;
			 o=new COutput();
                         o.setProdName(vName);
                         o.setI(0);
                         o.setFirst(g.setToStringList(grammarStructure,g.analysisOfECFG(aMulti).fFirst));
                         o.setFollow(g.setToStringList(grammarStructure,g.analysisOfECFG(aMulti).fFollow));
                         o.setProd(vName+"="+aMulti.toString());
                         vList.add(o);
                         result.put(vName,vList);
                         break;
		 case CGrammarCodes.scSEMultiStick:
                         CSE e;
			 CSE_MultiStick aStick=(CSE_MultiStick)body;
			 for(int i=0;i<aStick.getList().count();i++){
                             o=new COutput();
                             e=aStick.getList().getElt(i);
                             o.setProdName(vName);
                             o.setI(i);
                             if(e instanceof CSE_Eps)
                                 o.setEpsProd(true);
                             o.setFirst(g.setToStringList(grammarStructure,g.analysisOfECFG(e).fFirst));
                             o.setFollow(g.setToStringList(grammarStructure,g.analysisOfECFG(e).fFollow));
                             o.setProd(vName+"="+e.toString());
                             vList.add(o);
                         }
                         result.put(vName,vList);
			 break;
		 }
      
        return result;
    }

    /**
     *
     * @param aGrammarStructure
     * @return
     */
    public HashMap<String,ArrayList<COutput>> GrammartoProductionOutputs(CGrammar aGrammarStructure){
        HashMap<String,ArrayList<COutput>> result=new HashMap();
        CNonTerminalDef vNonTermDef;
        CSE vStart=aGrammarStructure.getStartExpr();
        assert(vStart instanceof CSE_MultiDot):"CGrammartoLLTableGenerator.GrammartoProductionOutputs() failed:The grammar is not augmented."; 
        for(int i=0;i<=grammarStructure.getGrammarContext().getNonTerminalDefs().count()-1;i++){
                //fOutput=new SLR.SLRGenerator.COutput();
                vNonTermDef=grammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i);
                result.putAll(productiontoOutputs(vNonTermDef));
        }
        
       return result; 
    }
    
    /**
     *
     * @param terminals
     * @param nt
     * @param nonTerminalsOutput
     * @return
     */
    public String[][] productionOutputsToLLTable(ArrayList<String> terminals,ArrayList<String> nt,HashMap<String, ArrayList<COutput>> nonTerminalsOutput){
        LLTable=new String[nonTerminalsOutput.size()][terminals.size()];
        ArrayList<COutput> aList;
        COutput o;
        for(int n=0;n<nt.size();n++){
            aList=nonTerminalsOutput.get(nt.get(n));
            if(aList!=null){
            for(int k=0;k<aList.size();k++){
                o=aList.get(k);
                for(int j=0;j<terminals.size();j++){
                   if(isMember(terminals.get(j),o.getFirst())){
                        LLTable[n][j]=o.getProd();
                   }
                   if(o.isEpsProd() && isMember(terminals.get(j),o.getFollow())){
                        LLTable[n][j]=o.getProd();
                   }
                }
            }}
         }
        
        for(int i=0;i<nonTerminals.size();i++){
            for(int j=0;j<terminalAlphabet.size();j++){
                if(LLTable[i][j]==null){
                    LLTable[i][j]="Error";
                }
            }
        }
        return LLTable;
    }
    
    /**
     *
     * @param aGrammar
     * @return
     */
    public CLLTable grammartoLLTable(CGrammar aGrammar){
        grammarStructure=aGrammar;
        terminalAlphabet=grammartoTerminalAlphabet(grammarStructure);
        nonTerminals=grammartoNonTerminals(grammarStructure);
        HashMap<String,ArrayList<COutput>> map=GrammartoProductionOutputs(grammarStructure);
        String[][] LL=productionOutputsToLLTable(terminalAlphabet,nonTerminals,map);
        return new CLLTable(LL, terminalAlphabet,nonTerminals);
    }
    
    /**
     *
     * @param s
     * @param set
     * @return
     */
    public boolean isMember(String s, ArrayList<String> set){
        boolean result=false;
        for(int i=0;i<set.size();i++){
            if(s.equals(set.get(i)))
                return true;
        }
            
        
        return result;
    }
}
