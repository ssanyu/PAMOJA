/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LR;

import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.RegExpr.CRE_Char;
import GrammarNotions.RegExpr.CRE_Empty;
import GrammarNotions.RegExpr.CRE_Eps;
import GrammarNotions.RegExpr.CRE_String;
import GrammarNotions.SyntaxExpr.CSymDec;
import GrammarNotions.SyntaxExpr.CTerminalDef;
import Sets.IntAlphabet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author HP
 */
public class CGrammarToLRTable {
    /**
     *
     */
    protected CGrammar GrammarStructure;

    /**
     *
     */
    protected HashMap<Integer,Integer> fInitStateperRule;

    /**
     *
     */
    protected COutput fOutput;

    /**
     *
     */
    public CGrammarToLRTable() {
        GrammarStructure=new CGrammar();
        fInitStateperRule=new HashMap<Integer,Integer>();
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
    public ArrayList<String> GrammarToSymbolAlphabet(CGrammar aGrammarStructure){
              ArrayList<String> strAlph=new ArrayList<String>();
              String s;
              String vSymbol=new String();
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
        }  
    /**
     *
     * @param aGrammarStructure
     * @return
     */
    public ArrayList<String> GrammarToStringAlphabet(CGrammar aGrammarStructure){
              ArrayList<String> strAlph=new ArrayList<String>();
              for(int i=0;i<=aGrammarStructure.getGrammarContext().symbolCount()-1;i++){
		     strAlph.add(aGrammarStructure.getGrammarContext().getSymbol(i).getName());
		}
              
              return strAlph;
    }  

    /**
     *
     * @param n
     * @return
     */
    public String getProdName(int n){
         CSymDec vDec;
         vDec=GrammarStructure.getGrammarContext().getSymbol(n);
         String name=vDec.getName();
         return name;
    } 

    /**
     *
     * @param aActionList
     * @return
     */
    public String ActionListtoString(ArrayList<Move> aActionList){
             Move aAction;
             String s="";
             if(aActionList.isEmpty()){
                 return s="";
             }else{
                for(int i=0;i<aActionList.size();i++){
                    aAction=aActionList.get(i);
                    if(aAction instanceof Shift){
                        Shift sft=(Shift)aAction;
                        s=s+"\n"+sft.toString();
                    }else if(aAction instanceof Reduce){
                        Reduce rc=(Reduce)aAction;
                        s=s+"\n"+rc.toString();
                    }else if(aAction instanceof Accept){
                        Accept acc=(Accept)aAction;
                        s=s+'\n'+acc.toString();
                    }else{
                        Reject msg=(Reject)aAction;
                        s=s+'\n'+msg.toString();
                    }
                }
             }
             return s;  //.substring(0, s.length()-1);
         }
        
}
