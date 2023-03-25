/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;



import Nodes.CNode;
import Sets.IntAlphabet;
import TableGenerators.LR.Accept;
import TableGenerators.LR.Move;
import TableGenerators.LR.Reduce;
import TableGenerators.LR.Reject;
import TableGenerators.LR.SLR.SLRAutomata.CSLRDFA;
import TableGenerators.LR.SLR.SLRAutomata.CSLRTable;
import TableGenerators.LR.Shift;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jssanyu
 */
public class CSLRParser extends CLRParser implements IStep{

   private CSLRTable   fSLRTable; 
   private String fSymValue;
   private CNode fNode=null;
   private CParseStack vStateStack;
   
   public int row,

    /**
     *
     */
    col=0;
   
    /**
     *
     */
    public CSLRParser() {
       super();
       fSLRTable=new CSLRTable(new CSLRDFA(new IntAlphabet()));
       fParserResult=new CParserResult();
       
       
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
    
    private Move getAction(int vCurrentState, int vSym){
        Move vAction;
        if(getSym()!=-1){
            vAction=fSLRTable.move(vCurrentState,vSym);
        }else{
            vAction=new Reject();
        }
        return vAction;
    }
    
    /**
     *
     */
    @Override
    public void start(){
        fCurrentState=fSLRTable.startState();
        Stack=new CParseStack();
        Stack.setRightAlign(false);
        Stack.push(fCurrentState);
        fNodeStack=new ArrayList<>();
        Logs=new ArrayList();
        symbolsUnmatched=new ArrayList();
        symbolsUnmatched.addAll(getInputSymbolStream());
        stringUnmatched=new String();
        stringUnmatched=getSymbolStreamValues();
        symbolsMatched=new ArrayList();
        stringMatched=new String();
        fAction=getAction(fCurrentState, getSym());
        vStateStack=new CParseStack();
        vStateStack.pushAll(Stack);
        Logs.add(new CParseLog(stringMatched,stringUnmatched,vStateStack,fAction.toString()));
       
    }
    
    public CParserResult parse() {
        start();
        while(fAction instanceof Shift || fAction instanceof Reduce){
            doStep();
        }
        callEnd();
        return fParserResult;
    }

    /**
     *
     */
    public void doStep(){
         if(fAction instanceof Shift){
            shift();
         }else if(fAction instanceof Reduce){           
            reduce();
         }         
     }

    /**
     *
     */
    public void shift(){
                  Shift sft=(Shift)fAction;
                  fCurrentState=sft.state;
                  //row=fCurrentState;
                 // col=fSLRTable.getSymbolAlphabet().indexOf(getSymName())+1;
                  Stack.push(fCurrentState);
                  fSymCode=getSym();
                  //if(hasData(fSym)){
                     fSymValue=getSymValue();
                     fNode=bdTermData("","",getSymName(fSymCode),fSymValue);
                     fNode.setAnno(getSymName(fSymCode), Integer.toString(sft.state));
                     //fNode.s
                  //}else{
                  //  fNode=bdTerm("","",getSymName(fSym));
                 // }
                  fNodeStack.add(fNode);
                  symbolsMatched.add(symbolsUnmatched.get(0));
                  stringMatched="";
                  for(int k=0;k<symbolsMatched.size();k++)
                      stringMatched=stringMatched+symbolsMatched.get(k).symValue();
                  symbolsUnmatched.remove(0);
                  stringUnmatched=stringUnmatched.substring(getSymValue().length());
                  nextSym();
                  fAction=getAction(fCurrentState, getSym());
                  vStateStack=new CParseStack();
                  vStateStack.pushAll(Stack);
                  if(fAction instanceof Reduce)
                   Logs.add(new CParseLog(stringMatched,stringUnmatched,vStateStack,fAction.toString()+ "   "+((Reduce)fAction).getProd()));
                  else if(fAction instanceof Shift) 
                   Logs.add(new CParseLog(stringMatched,stringUnmatched,vStateStack,fAction.toString()));
                  
       
    }             

    /**
     *
     */
    public void reduce(){
                   int vProdNo;
                   int vIndex;
                   int vLength;
                   
                   List<CNode> vDotNode;
                   Reduce r=(Reduce)fAction;
                  // row=fCurrentState;
                  // col=fSLRTable.getSymbolAlphabet().indexOf(getSymName())+1;
                   vProdNo=r.prodNo;
                   vIndex=r.index;
                   vLength=r.length;
                            
                   for (int j=0; j<vLength; j++){
                       Stack.pop(Stack.size()-1);
                   }
                   fAction=getAction((int)Stack.peek(Stack.size()-1),vProdNo);
                   if( fAction instanceof Shift){
                       Shift sft=(Shift)fAction;
                       fCurrentState=sft.state;
                   }
                   Stack.push(fCurrentState);
                   
                   if(vLength==0){
                       fNode=bdEps(getProdName(vProdNo),"");
                       fNodeStack.add(fNode);
                   }else {
                       vDotNode=new ArrayList<>();
                  
                       for (int k = fNodeStack.size() - vLength; k < fNodeStack.size(); k++){
                           vDotNode.add(fNodeStack.get(k));
                       }
                       for (int j=0; j<vLength; j++){
                           fNodeStack.remove(fNodeStack.size()-1);
                       }
                       if(vDotNode.size()==1){
                           fNode=vDotNode.get(0);
                       }else{
                           fNode=bdMultiDot(getProdName(vProdNo),"",ListToArrayList(vDotNode));
                       }
                       fNode=bdMultiStick(getProdName(vProdNo),"",vIndex,fNode); 
                       fNode=bdNonTerm(getProdName(vProdNo),"",getSymName(vProdNo),fNode);
                       fNode.setAnno(getProdName(vProdNo), Integer.toString(fCurrentState));
                       fNodeStack.add(fNode);
                   }
                   fAction=getAction(fCurrentState, getSym());
                   vStateStack=new CParseStack();
                   vStateStack.pushAll(Stack);
                   if(fAction instanceof Reduce)
                     Logs.add(new CParseLog(stringMatched,stringUnmatched,vStateStack,fAction.toString()+ "   "+((Reduce)fAction).getProd()));
                   else if(fAction instanceof Shift) 
                     Logs.add(new CParseLog(stringMatched,stringUnmatched,vStateStack,fAction.toString()));
                     
    
    }

    /**
     *
     */
    @Override
    public void step(){
         row=fCurrentState;
         col=fSLRTable.getSymbolAlphabet().indexOf(getSymName())+1;
         if(fAction instanceof Shift || fAction instanceof Reduce){
            doStep(); 
         }else{ 
            callEnd();
         }
         
    }
     
    /**
     *
     */
    public void accept(){
       Logs.add(new CParseLog(stringMatched,stringUnmatched,vStateStack,fAction.toString()));
       fParserResult.setSuccess(true); 
       fParserResult.setNode(fNode);
    }
    
    /**
     *
     */
    public void reject(){
               Logs.add(new CParseLog(stringMatched,stringUnmatched,vStateStack,"error"));
               fParserResult.setSuccess(false);
               fParseErrorList.add(new CParseError("Failed to match a symbol",getSym(),getSymName(),getSymValue(),getSymPos())); 
    }
   
   
    
    
    private void callEnd(){
        if(fAction instanceof Accept){
               accept();
       }else if(fAction instanceof Reject){
               reject();
       }
    }
    
  /*
    public CParserResult parse1() {
        //List<Integer> fStateStack;   // Stack of States
        //List<CNode> fTreeStack;    //Stack of trees
        //int fCurrentState;                 // current state
        //int fSym;  //input symbol last read
        //CNode fNode=null;
        //String fSymValue;
       // Move fAction;
        //CParserResult fParserResult; 
       // ArrayList<CParseLogEntry> vLogEntries;
                        
        //fParserResult=new CParserResult();
        fCurrentState=fSLRTable.startState();
        fStateStack=new ArrayList<>();
        fStateStack.add( fCurrentState);
        fTreeStack=new ArrayList<>();
        vLogEntries=new ArrayList<>();
        
        fAction=getAction(fCurrentState, getSym());
        while(fAction instanceof Shift || fAction instanceof Reduce){
            if( fAction instanceof Shift ){  
                  //create a log entry 
                  vLogEntries.add(new CParseLogEntry(fStateStack,"","",fAction));
                  Shift sft=(Shift)fAction;
                  fCurrentState=sft.state;
                  fStateStack.add(fCurrentState);
                  fSym=getSym();
                  if(hasData(fSym)){
                      fSymValue=getSymValue();
                      fNode=bdTermData("","",getSymName(fSym),fSymValue);
                  }else{
                      fNode=bdTerm("","",getSymName(fSym));
                  }
                  fTreeStack.add(fNode);
                  nextSym();
                  fAction=getAction(fCurrentState, getSym());
             }else if(fAction instanceof Reduce ){  
                   int vProdNo;
                   int vIndex;
                   int vLength;
                   List<CNode> vDotNode;
                                   
                   vLogEntries.add(new CParseLogEntry(fStateStack,"","",fAction));
                   Reduce r=(Reduce)fAction;
                   vProdNo=r.prodNo;
                   vIndex=r.index;
                   vLength=r.length;
                   
                   //fStateStack=fStateStack.subList(0,fStateStack.size()-vLength);
                   for (int j=0; j<vLength; j++){
                       fStateStack.remove(fStateStack.size()-1);
                   }
                   fAction=getAction(fStateStack.get(fStateStack.size()-1), vProdNo);
                   if( fAction instanceof Shift){
                       Shift sft=(Shift)fAction;
                       fCurrentState=sft.state;
                   }
                   fStateStack.add(fCurrentState);
                   
                   if(vLength==0){
                       fNode=bdEps(getSymName(vProdNo),"");
                       fTreeStack.add(fNode);
                   }else {
                       vDotNode=new ArrayList<>();
//                       vDotNode=fTreeStack.subList(fTreeStack.size()-vLength,fTreeStack.size());
//                       fTreeStack=fTreeStack.subList(0,fTreeStack.size()-vLength);
                       for (int k = fTreeStack.size() - vLength; k < fTreeStack.size(); k++){
                           vDotNode.add(fTreeStack.get(k));
                       }
                       for (int j=0; j<vLength; j++){
                           fTreeStack.remove(fTreeStack.size()-1);
                       }
                       if(vDotNode.size()==1){
                           fNode=vDotNode.get(0);
                       }else{
                           fNode=bdMultiDot(getSymName(vProdNo),"",ListToArrayList(vDotNode));
                       }
                       fNode=bdMultiStick(getSymName(vProdNo),"",vIndex,fNode); 
                       fNode=bdNonTerm(getSymName(vProdNo),"",getSymName(vProdNo),fNode);
                       fTreeStack.add(fNode);
                   }
                   
                  // vLogEntries.add(new CParseLogEntry(fStateStack,getSymName(),fAction));
                   fAction=getAction(fCurrentState, getSym());
              }
       }
       if(fAction instanceof Accept){
               vLogEntries.add(new CParseLogEntry(fStateStack,"","",fAction));
               fParserResult.setSuccess(true); 
               fParserResult.setNode(fNode);
               
               Logs = new ArrayList<>();
               Logs.addAll(vLogEntries);
       }else if(fAction instanceof Reject){
               vLogEntries.add(new CParseLogEntry(fStateStack,"","",fAction));
               fParserResult.setSuccess(false);
               
               Logs = new ArrayList<>();
               Logs.addAll(vLogEntries);
               fParseErrorList.add(new CParseError("Failed to match a symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
       }
        
       return fParserResult;
    }
*/


}
