  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LR.SLR.SLRGenerator;


import TableGenerators.LR.COutput;
import Analyzers.CGrammarAnalyzer;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.RegExpr.CRE_Char;
import GrammarNotions.RegExpr.CRE_Empty;
import GrammarNotions.RegExpr.CRE_Eps;
import GrammarNotions.RegExpr.CRE_String;
import GrammarNotions.SyntaxExpr.CNonTerminalDef;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSE_MultiDot;
import GrammarNotions.SyntaxExpr.CSE_MultiStick;
import GrammarNotions.SyntaxExpr.CSE_Sym;
import GrammarNotions.SyntaxExpr.CSymDec;
import GrammarNotions.SyntaxExpr.CTerminalDec;
import GrammarNotions.SyntaxExpr.CTerminalDef;
import Sets.IntAlphabet;
import Sets.StateSet;
import TableGenerators.LR.Accept;
import TableGenerators.LR.CGrammarToLRTable;
import TableGenerators.LR.Move;
import TableGenerators.LR.Reduce;
import TableGenerators.LR.Reject;
import TableGenerators.LR.SLR.SLRAutomata.CSLRDFA;
import TableGenerators.LR.SLR.SLRAutomata.CSLRNFA;
import TableGenerators.LR.SLR.SLRAutomata.CSLRTable;
import TableGenerators.LR.Shift;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author jssanyu
 */
public class CGrammarToSLRTable extends CGrammarToLRTable{

    /**
     *
     */
    protected CSLRNFA fSLRNFA;

    
    
    /**
     *
     */
    protected StateSet[] fNFAStateSets;

    /**
     *
     */
    protected StateSet[] fEpsClosure;

    /**
     *
     */
    protected CSLRDFA fDFA;
    
    /**
     *
     */
    protected ArrayList<Move> fSLRTable1[][] ;

    /**
     *
     */
    protected ArrayList<CReduceInfo> rs;   
   
    /**
     *
     */
    protected CSLRTable fSLRTable2=null;
    
    /**
     *
     */
    public CGrammarToSLRTable(){
        super();
   }
	
   
 
    /**
     *
     * @param aFrom
     * @param aSE
     * @param aTo
     * @param aOutput
     */
    protected void NFA(int aFrom,CSE aSE,int aTo,COutput aOutput){
		int i,vI,vF,vTo;
		CSymDec vDec;
		COutput vOutput;
                
                switch(aSE.sortCode()){
                 case CGrammarCodes.scSEEps:
			 fSLRNFA.addEps(aFrom, aTo);
			 break;
		 case CGrammarCodes.scSESym:
                        if(aOutput.getI()==-1 && aOutput.getL()==-1){
                            aOutput.setI(0);
                            aOutput.setL(1);
                            fSLRNFA.getOutputTable().put(aTo,aOutput);
                        }
                        if(aOutput.getI()!=-1 && aOutput.getL()==-1){
                            aOutput.setL(1);
                            fSLRNFA.getOutputTable().put(aTo,aOutput);
                        }
                                          
                         CSE_Sym aSym=(CSE_Sym)aSE;
                         vDec=aSym.getDec();
                         fSLRNFA.addEdge(aFrom,vDec.getNumber(),aTo);
			 if(vDec instanceof CNonTerminalDef){
                            fSLRNFA.addEps(aFrom,fInitStateperRule.get(vDec.getNumber()));
                        }
                        break;
		 case CGrammarCodes.scSEMultiDot:
			 CSE_MultiDot aMulti=(CSE_MultiDot)aSE;
			 vI=aFrom;
			 int count=aMulti.getList().count();
                         if(aOutput.getI()==-1 && aOutput.getL()==-1){
                            aOutput.setI(0);
                         }
                         aOutput.setL(count);
                         fSLRNFA.getOutputTable().put(aTo,aOutput);
                         for(i=0;i<=count-2;i++){
				vF=fSLRNFA.newState();
				NFA(vI,aMulti.getList().getElt(i),vF,aOutput);
				vI=vF;
			 }
                         NFA(vI,aMulti.getList().getElt(count-1),aTo,aOutput);
                         
                         break;
		 case CGrammarCodes.scSEMultiStick:
			 CSE_MultiStick aStick=(CSE_MultiStick)aSE;
			 count=aStick.getList().count();
                         aOutput.setI(0);
                         fSLRNFA.getOutputTable().put(aTo, aOutput);                  
                         NFA(aFrom,aStick.getList().getElt(0),aTo,aOutput);
                         for(i=1;i<=count-1;i++){
                             vOutput=new COutput();
                             vOutput.setA(aOutput.getA());
                             vTo=fSLRNFA.newState();
                             vOutput.setI(i);
                             NFA(aFrom,aStick.getList().getElt(i),vTo,vOutput);
                             fSLRNFA.addFinalState(vTo);
                             fSLRNFA.getOutputTable().put(vTo, vOutput);
                         }
                       
			 break;
		 }
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
    public CSLRNFA GrammarToSLRNFA(CGrammar aGrammarStructure){
            int vFrom,vTo;
            CNonTerminalDef vNonTermDef;
            CSE vStart=aGrammarStructure.getStartExpr();
            assert(vStart instanceof CSE_MultiDot):"CGrammartoSLRTable.GrammarToSLRNFA() failed:The grammar is not augmented."; 
        
            GrammarStructure=aGrammarStructure;
	    fSLRNFA=new CSLRNFA(GrammarToIntAlphabet(GrammarStructure));
            fInitStateperRule=new HashMap<Integer,Integer>();
	    
            //Create initial state for each production rule A=e
            for(int i=0;i<=GrammarStructure.getGrammarContext().getNonTerminalDefs().count()-1;i++){
		vFrom=fSLRNFA.newState();
                fInitStateperRule.put(GrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getNumber(),vFrom);
                fSLRNFA.addInitialState(vFrom);
            }
                     
            // For each production rule A=e, construct an NFA for e
            for(int i=0;i<=GrammarStructure.getGrammarContext().getNonTerminalDefs().count()-1;i++){
                fOutput=new COutput();
                vNonTermDef=GrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i);
                vFrom=fInitStateperRule.get(vNonTermDef.getNumber());
                vTo=fSLRNFA.newState();
                // get production no.
                int n=vNonTermDef.getNumber();
                fOutput.setA(n);
               
                NFA(vFrom,vNonTermDef.getBody(),vTo,fOutput);
                
                fSLRNFA.addFinalState(vTo);
                
                }
              // set productions for reduce in output table
                String vProd;
                for (COutput op : fSLRNFA.getOutputTable().values()){
                    String name=getProdName(op.getA());
                    int ind=GrammarStructure.getGrammarContext().indexofNonTerminal(name);
                    CSE se=GrammarStructure.getGrammarContext().getNonterminalBody(ind);
                    if(se instanceof CSE_MultiStick){
                      CSE seI=(CSE)se.getTerm(op.getI());
                      vProd=name+"="+seI.toText();
                    }else{
                       vProd=name+"="+se.toText();
                    }
                    op.setP(vProd);
                }
                       
             //   System.out.println(fOu);
            // Construct an NFA for the start expression
               fOutput=new COutput();
               vTo=fSLRNFA.newState();
               NFA(fSLRNFA.startState(),GrammarStructure.getStartExpr(),vTo,fOutput);
               //fSLRNFA.addFinalState(vTo);         
            return fSLRNFA;
	}
   
    /**
     *
     * @param aNFA
     */
    protected void computeEpsClosure(CSLRNFA aNFA){
		int k,i;
		fEpsClosure=new StateSet[aNFA.stateCount()];
		//initialize FEpsClosure to fNFA.fwEpsTransitions
		for(int h=0;h<=aNFA.stateCount()-1;h++)
			fEpsClosure[h]=aNFA.fwEpsTransitions(h);
		// transitive closure with Warshall's algorithm
		for (k=0;k<=fEpsClosure.length-1;k++ )
			for(i=0;i<=fEpsClosure.length-1;i++)
				if(fEpsClosure[i].has(k))
					fEpsClosure[i].bcUnion(fEpsClosure[k]);
		// add reflexive closure
		for (k=0;k<=fEpsClosure.length-1;k++ ){
			StateSet sK=new StateSet();
			sK.addState(k);
			fEpsClosure[k].bcUnion(sK);
		}
	 }
	 
    /**
     *
     * @param aNFA
     */
    public void NFAtoDFA(CSLRNFA aNFA) {
		int p,q,r,vDummy;
		fSLRNFA=aNFA;
		computeEpsClosure(fSLRNFA);
		//fNFAStateSets=new StateSet[fNFA.stateCount()];
                fNFAStateSets=new StateSet[fSLRNFA.STATES];
		fDFA=new CSLRDFA(fSLRNFA.alphabet());
		fNFAStateSets[0]= new StateSet();
               	fNFAStateSets[1]=fEpsClosure[fSLRNFA.startState()];
					
		p=1;
		q=2;
		while(p!=q){
			for(int i=fSLRNFA.alphabet().nextSetBit(0); i>=0;i=fSLRNFA.alphabet().nextSetBit(i+1)){
					fNFAStateSets[q]=new StateSet();
					for(int j=fNFAStateSets[p].nextSetBit(0); j>=0;j=fNFAStateSets[p].nextSetBit(j+1)){
		    		for(int vT=0;vT<=fSLRNFA.stateCount()-1;vT++){
								if(fSLRNFA.fwTransitions(j,i).has(vT))
									fNFAStateSets[q].bcUnion(fEpsClosure[vT]);
						}
					}
				// linear search with sentinel
				r=0;
				while(!(fNFAStateSets[r].equals(fNFAStateSets[q]))){
					r=r+1;
		        }
				if(r==q){
					q=q+1;
		        	vDummy=fDFA.newState(); 
		        }
		        fDFA.fwTransitions(p,i).addState(r); 
		       	fDFA.addEdge(p, i, r);	
			}//end for-loop 
			p=p+1;
		}//end while-loop
		output(fDFA);
     	}
	
    /**
     *
     * @param aDFA
     */
    public void output(CSLRDFA aDFA){
            fDFA=aDFA;
		for(int i=0;i<=fDFA.stateCount()-1;i++){
			fNFAStateSets[i].bcIntersection(fSLRNFA.finalStates());
			if(fNFAStateSets[i].size()!=0){
				fDFA.addFinalState(i);
				int s=0;
				while(!(fNFAStateSets[i].has(s))){
					s=s+1;
				}
				fDFA.setOutput(i,fSLRNFA.getOutput(s));
                               
			}
		}
	}
	
    /**
     *
     * @return
     */
    public CSLRDFA getDFA() {
		return fDFA;
	}

    /**
     *
     * @param aState
     * @return
     */
    public StateSet getNFAStateSet(int aState) {
            if(fNFAStateSets[aState]!=null)
		return fNFAStateSets[aState];
            else return new StateSet();
	}
        
    /**
     *
     * @param aDFA
     */
    public void DFAtoSLRTable1(CSLRDFA aDFA){
             
              ArrayList<Move> vActionList;
             //clear SLR Table
              fSLRTable1=new ArrayList[aDFA.stateCount()][aDFA.alphabet().size()];
              for(int vState=0;vState<=aDFA.stateCount()-1;vState++){
		  for(int j=aDFA.alphabet().nextSetBit(0); j>=0;j=aDFA.alphabet().nextSetBit(j+1)){
                      vActionList=new ArrayList<Move>();
                      fSLRTable1[vState][j]=vActionList;
	          } 
              }
              
             //Add Accept and Shift Actions
             for(int vState=0;vState<=aDFA.stateCount()-1;vState++){
		  for(int j=aDFA.alphabet().nextSetBit(0); j>=0;j=aDFA.alphabet().nextSetBit(j+1)){
                     if(aDFA.dTransition(vState,j)!=0){
                        if(GrammarStructure.getGrammarContext().getSymbol(j).getName().equals("endmarker")){
                            fSLRTable1[vState][j].add(new Accept());   
                        } else{
                           fSLRTable1[vState][j].add(new Shift(aDFA.dTransition(vState,j)));                           
	                } 
                     }
                  }
             }
              //Add reduce actions
             reduce(aDFA);
             for(int i=0;i<rs.size();i++){  
                 if(fDFA.finalStates().has(rs.get(i).s)){
                      fSLRTable1[rs.get(i).s][rs.get(i).a].add(rs.get(i).r);
                 }
             }
             
             //Add Error Actions
              for(int vState=0;vState<=aDFA.stateCount()-1;vState++){
		  for(int j=aDFA.alphabet().nextSetBit(0); j>=0;j=aDFA.alphabet().nextSetBit(j+1)){
                       if(fSLRTable1[vState][j].isEmpty()){
                           fSLRTable1[vState][j].add(new Reject()); 
                       }
                  }
              }
              
        }

    /**
     *
     * @param aDFA
     */
    public void reduce(CSLRDFA aDFA){
            int j,vState;
            CSymDec vDec=null;
            CSE vSE;
            String vName;
            int vIndex;
            IntAlphabet vAlph=new IntAlphabet();
            CReduceInfo vI;
            rs=new ArrayList<CReduceInfo>();
                        
            fDFA=aDFA;
            CGrammarAnalyzer vAnalyzer=new CGrammarAnalyzer();
            
            for(j=fDFA.finalStates().nextSetBit(0); j>=0;j=fDFA.finalStates().nextSetBit(j+1)){
                vState=j;
                vDec=GrammarStructure.getGrammarContext().getSymbol(fDFA.getOutput(vState).getA());
                vName=vDec.getName();
                vIndex=GrammarStructure.getGrammarContext().indexOfNonterminal(vName);
                vSE=GrammarStructure.getGrammarContext().getNonterminalBody(vIndex);
                vAlph=vAnalyzer.analysisOfECFG(vSE).fFollow;
                for(int i=vAlph.nextSetBit(0); i>=0;i=vAlph.nextSetBit(i+1)){
                     vDec=GrammarStructure.getGrammarContext().getSymbol(i);
                     if(vDec instanceof CTerminalDef || vDec instanceof CTerminalDec){
                           vI=new CReduceInfo();
                           vI.s=vState;
                           vI.a=i;
                           vI.r=new Reduce(fDFA.getOutput(vState).getA(),
                                           fDFA.getOutput(vState).getI(),
                                           fDFA.getOutput(vState).getL(),
                                           fDFA.getOutput(vState).getP());
                           rs.add(vI);
                           
                     }
                     
                } 
           }
        }
	
    /**
     *
     * @param aSLRTable1
     */
    public void SLRTable1toSLRTable2(ArrayList<Move> aSLRTable1[][]){
            ArrayList<Conflict> vConflictsList=new ArrayList<Conflict>();
            //check for shift/reduce conflicts
            for(int vState=0;vState<=fDFA.stateCount()-1;vState++){
		  for(int j=fDFA.alphabet().nextSetBit(0); j>=0;j=fDFA.alphabet().nextSetBit(j+1)){
                      if(fSLRTable1[vState][j].size()>1){
                          vConflictsList.add(new Conflict(vState,GrammarStructure.getGrammarContext().getSymbol(j).getName()));
                      }
	          } 
            }
            if(!vConflictsList.isEmpty()){
                String vErrors="There are conflicts in the following cells:";
                for(int i=0;i<=vConflictsList.size()-1;i++){
                    vErrors=vErrors+"\n"+(i+1)+": "+vConflictsList.get(i).toString();
                }
                vErrors=vErrors+"\n"+"SLR(1) table constructed with errors.";
                //Signal error and keep oldValue
                JOptionPane.showMessageDialog(null,vErrors);
            }else { 
                fSLRTable2=new CSLRTable(fDFA);
                for(int vState=0;vState<=fDFA.stateCount()-1;vState++){
		  for(int j=fDFA.alphabet().nextSetBit(0); j>=0;j=fDFA.alphabet().nextSetBit(j+1)){
                      fSLRTable2.move(vState, j);
                      fSLRTable2.addAction(vState, j,fSLRTable1[vState][j].get(0));
                  }
                }
                fSLRTable2.setSymbolAlphabet(GrammarToStringAlphabet(GrammarStructure));
            }
            
            
         }     
      
    /**
     *
     * @return
     */
    public CSLRTable getSLRTable2(){
            return fSLRTable2;
        }
        
    /**
     *
     * @return
     */
    public ArrayList<Move>[][] getSLRTable1() {
            return fSLRTable1;
	}
        
    /**
     *
     * @return
     */
    public int getSLRTableStartState(){
            return fDFA.startState();
        }

    
    
}


