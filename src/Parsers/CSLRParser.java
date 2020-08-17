/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;


import Nodes.CNode;
import SLR.SLRAutomata.Accept;
import SLR.SLRAutomata.CSLRDFA;
import SLR.SLRAutomata.CSLRTable;
import SLR.SLRAutomata.ErrorMsg;
import SLR.SLRAutomata.Move;
import SLR.SLRAutomata.Reduce;
import SLR.SLRAutomata.Shift;
import Sets.IntAlphabet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jssanyu
 */
public class CSLRParser extends CLRParser{

   private CSLRTable   fSLRTable; 
   private ArrayList<CParseLogEntry> fLogEntries=null;
    
    /**
     *
     */
    public CSLRParser() {
       super();
       fSLRTable=new CSLRTable(new CSLRDFA(new IntAlphabet()));
       fLogEntries = new ArrayList<>();
  } 
    
    /**
     *
     * @return
     */
    public CSLRTable getSLRTable(){
       return fSLRTable;
   }

    /**
     *
     * @param aSLRTable
     */
    public void setSLRTable(CSLRTable aSLRTable){
       fSLRTable=aSLRTable;
   }

    /**
     *
     * @return
     */
    public ArrayList<CParseLogEntry> getLogEntries(){
       return fLogEntries;
   }
    
    @Override
    public CParserResult parse() {
        List<Integer> vStateStack;   // Stack of States
        List<CNode> vTreeStack;    //Stack of trees
        int vCurrentState;                 // current state
        int vSym;  //input symbol last read
        CNode vNode=null;
        String vSymValue;
        Move vAction;
        CParserResult vResult; 
        ArrayList<CParseLogEntry> vLogEntries;
                        
        vResult=new CParserResult();
        vCurrentState=fSLRTable.startState();
        vStateStack=new ArrayList<>();
        vStateStack.add( vCurrentState);
        vTreeStack=new ArrayList<>();
        vLogEntries=new ArrayList<>();
        
        vAction=fSLRTable.move(vCurrentState,getSym());
        while(vAction instanceof Shift || vAction instanceof Reduce){
             
              if( vAction instanceof Shift ){  
                  //create a log entry 
                  vLogEntries.add(new CParseLogEntry(vStateStack.toString(),getSymName(),vAction));
                  Shift sft=(Shift)vAction;
                  vCurrentState=sft.state;
                  vStateStack.add(vCurrentState);
                  vSym=getSym();
                  if(hasData(vSym)){
                      vSymValue=getSymValue();
                      vNode=bdTermData("","",getSymName(vSym),vSymValue);
                  }else{
                      vNode=bdTerm("","",getSymName(vSym));
                  }
                  vTreeStack.add(vNode);
                  
                  nextSym();
                  vAction=fSLRTable.move(vCurrentState,getSym());
              }else if(vAction instanceof Reduce ){  
                    int vProdNo;
                   int vIndex;
                   int vLength;
                   List<CNode> vDotNode;
                  
                   
                   vLogEntries.add(new CParseLogEntry(vStateStack.toString(),getSymName(),vAction));
                   
                   Reduce r=(Reduce)vAction;
                   vProdNo=r.prod;
                   vIndex=r.index;
                   vLength=r.length;
                   //vStateStack=vStateStack.subList(0,vStateStack.size()-vLength);
                   for (int j=0; j<vLength; j++){
                       vStateStack.remove(vStateStack.size()-1);
                   }
                   
                   vAction=fSLRTable.move(vStateStack.get(vStateStack.size()-1),vProdNo);
                   if( vAction instanceof Shift){
                       Shift sft=(Shift)vAction;
                       vCurrentState=sft.state;
                   }
                   vStateStack.add(vCurrentState);
                   
                   if(vLength==0){
                       vNode=bdEps(getSymName(vProdNo),"");
                       vTreeStack.add(vNode);
                   }else {
                       vDotNode=new ArrayList<>();
//                       vDotNode=vTreeStack.subList(vTreeStack.size()-vLength,vTreeStack.size());
//                       vTreeStack=vTreeStack.subList(0,vTreeStack.size()-vLength);
                       for (int k = vTreeStack.size() - vLength; k < vTreeStack.size(); k++){
                           vDotNode.add(vTreeStack.get(k));
                       }
                       for (int j=0; j<vLength; j++){
                           vTreeStack.remove(vTreeStack.size()-1);
                       }
                       if(vDotNode.size()==1){
                           vNode=vDotNode.get(0);
                       }else{
                         // System.out.println(vProdNo);
                         // vNode=bdMultiDot(getSymName(vProdNo),"",ListToArrayList(vDotNode));
                         vNode=bdMultiDot(getNonTerminalName(vProdNo),"",ListToArrayList(vDotNode));
                       }
                       vNode=bdMultiStick(getNonTerminalName(vProdNo),"",vIndex,vNode); 
                      // System.out.println(getSymName(vProdNo));
                       vNode=bdNonTerm(getNonTerminalName(vProdNo),"",getNonTerminalName(vProdNo),vNode);
                       vTreeStack.add(vNode);
                   }
                   
                  // vLogEntries.add(new CParseLogEntry(vStateStack,getSymName(),vAction));
                   vAction=fSLRTable.move(vCurrentState,getSym());
              }
       }
       if(vAction instanceof Accept){
               vLogEntries.add(new CParseLogEntry(vStateStack.toString(),getSymName(),vAction));
               vResult.setSuccess(true); 
               vResult.setNode(vNode);
               
               fLogEntries = new ArrayList<>();
               fLogEntries.addAll(vLogEntries);
       }else if(vAction instanceof ErrorMsg){
               vLogEntries.add(new CParseLogEntry(vStateStack.toString(),getSymName(),vAction));
               vResult.setSuccess(false);
               
               fLogEntries = new ArrayList<>();
               fLogEntries.addAll(vLogEntries);
               fParseErrorList.add(new CParseError("Failed to match a symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
       }
        
       return vResult;
    }

    /**
     *
     * @param aList
     * @return
     */
    protected ArrayList<CNode> ListToArrayList(List<CNode> aList){
        ArrayList<CNode> vArrList=new ArrayList<>();
        vArrList.addAll(aList);
        return vArrList;
    }
    
     private String toText(){ // textual representation of SLRTable
             StringBuilder buffer=new StringBuilder();
             int vState;
             buffer.append("StateCount\n").append(fSLRTable.stateCount());
             
             if(fGrammar==null){
                 buffer.append("\nAlphabet\n");
             }else{
                 buffer.append("\nAlphabet\n"); 
                 String aAlphabet=IntAlphabet.setToString(fGrammar,fSLRTable.alphabet());
                 buffer.append(aAlphabet);
             }
  
	     buffer.append("\nStartState\n").append(fSLRTable.startState());
             
             buffer.append("\nSLR(1) Table \n");
	     for(vState=0;vState<=fSLRTable.stateCount()-1;vState++){
		  for(int j=fSLRTable.alphabet().nextSetBit(0); j>=0;j=fSLRTable.alphabet().nextSetBit(j+1)){
                     buffer.append(fSLRTable.move(vState,j)).append(",        ");
	          } 
                  buffer.deleteCharAt(buffer.length()-2);
		  buffer.append("\n");
	     }
             buffer.deleteCharAt(buffer.length()-2);
	     return buffer.toString();
	}
    
   


    @Override
    public CParserResult parseNonTerminal(String aName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
