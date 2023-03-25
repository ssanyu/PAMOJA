/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Analyzers;

import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.RegExpr.*;
import GrammarNotions.SyntaxExpr.*;
import Nodes.CNode;
import Sets.Alphabet;
import Sets.AlphabetOps;
import Sets.IntAlphabet;
import Sets.IntAlphabetOps;
import java.util.ArrayList;
/**
 * A class which contains operations for checking a grammar for well-formedness, 
 * and to perform additional analysis tasks. 
 * <p>
 * I.e, Sets: <code>First</code>, <code>Follow</code> and <code>Last</code> and predicates: <code>null</code>, <code>reach</code>
 * and <code>empty</code>.
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CGrammarAnalyzer {

    /**
     *
     */
    protected CGrammar fGrammar;

    /**
     * Sets the fGrammar object to be analysed.
     * 
     * @param aGrammar the CGrammar object.
     */
    public void setGrammar(CGrammar aGrammar){
        fGrammar=aGrammar;
    }
    /**
      * Computes analysis info. of a single RE.
      *
      * 
      * @param aRE the CRE object.
    */
    public void analyzeSingleRE(CRE aRE){
        recClearREAnalysis(aRE);
        analyzeRE(aRE);
    }
   
    /**
     * See to it that every node of the ECFG tree is provided with a cleared CREAnalysis object
     * 
     * @param aRE the CRE object.
     */
        protected void recClearREAnalysis(CRE aRE){
        int i;
        if(analysisOfRE(aRE)==null)
            initREAnalysis(aRE);
        else analysisOfRE(aRE).clear();
        
        switch(aRE.sortCode()){
            case CGrammarCodes.scREMultiDot:
                CRE_MultiDot aMultiDot=(CRE_MultiDot)aRE;
                for(i=0; i < aMultiDot.List().count(); i++){
                   recClearREAnalysis(aMultiDot.List().getElt(i));
                }
            break;
            
            case CGrammarCodes.scREMultiStick:
                CRE_MultiStick aMultiStick=(CRE_MultiStick)aRE;
                for(i=0; i < aMultiStick.List().count(); i++){
                    recClearREAnalysis(aMultiStick.List().getElt(i));
                }
            break;
                
            default: // recursively traverse subtrees
            for(i=0;i<=aRE.termCount()-1;i++){
                recClearREAnalysis((CRE)aRE.getTerm(i));
            }
            break;
            
        }
        
    }
 

    /**
     * See to it that every node of the ECFG tree is provided with a cleared CECFGAnalysis object
     * 
     * @param aSE the CSE object.
     */
        protected void recClearECFGAnalysis(CSE aSE){
        int i;
        if(analysisOfECFG(aSE)==null)
            initECFGAnalysis(aSE);
        else analysisOfRE(aSE).clear();
        
        switch(aSE.sortCode()){
            case CGrammarCodes.scSEMultiDot:
                CSE_MultiDot aMultiDot=(CSE_MultiDot)aSE;
                for(i=0; i < aMultiDot.getList().count(); i++){
                    recClearECFGAnalysis(aMultiDot.getList().getElt(i));
                }
            break;
            
            case CGrammarCodes.scSEMultiStick:
                CSE_MultiStick aMultiStick=(CSE_MultiStick)aSE;
                for(i=0; i < aMultiStick.getList().count(); i++){
                    recClearECFGAnalysis(aMultiStick.getList().getElt(i));
                }
            break;
                
            default: // recursively traverse subtrees
            for(i=0;i<=aSE.termCount()-1;i++){
                recClearECFGAnalysis((CSE)aSE.getTerm(i));
            }
            break;
        }
      }

    /**
     * Determine reachability of every node of the syntax expression tree.
     * 
     * @param aSE the CSE object.
     */
    protected void recReachable(CSE aSE){
            CSymDec vSym;

            analysisOfECFG(aSE).fReachable=true;
            switch(aSE.sortCode()){
            case CGrammarCodes.scSEEmpty:
                 break;
            case CGrammarCodes.scSEEps:
                 break;
            case CGrammarCodes.scSESym:
                CSE_Sym sym=(CSE_Sym)aSE;
                vSym=sym.getDec();
                if( (vSym.sortCode()==CGrammarCodes.scTerminalDec) ||
                    (vSym.sortCode()==CGrammarCodes.scTerminalDef))    
                    analysisOfECFG(vSym).fReachable=true;
                else if(vSym.sortCode()==CGrammarCodes.scNonTerminalDef){
                    if(!analysisOfECFG(vSym).fReachable){
                        analysisOfECFG(vSym).fReachable=true;
                        recReachable(((CNonTerminalDef)vSym).getBody());
                    }
                }
                break;
            case CGrammarCodes.scSEMultiDot:
                CSE_MultiDot vDot=(CSE_MultiDot)aSE;
                for(int i=0;i<=vDot.getList().count()-1;i++){
                    recReachable((CSE)vDot.getList().getElt(i));
                }
                break;
            case CGrammarCodes.scSEMultiStick:
                CSE_MultiStick vStick=(CSE_MultiStick)aSE;
                for(int i=0;i<=vStick.getList().count()-1;i++){
                    recReachable((CSE)vStick.getList().getElt(i));
                }
                break;
            case CGrammarCodes.scSEAlt:
                CSE_Alt vAlt=(CSE_Alt)aSE;
                recReachable(vAlt.getLeft());
                recReachable(vAlt.getRight());
                break;
            case CGrammarCodes.scSEAlt2:
                CSE_Alt2 vAlt2=(CSE_Alt2)aSE;
                recReachable(vAlt2.getLeft());
                recReachable(vAlt2.getRight());
                break;
            case CGrammarCodes.scSEStar:
               CSE_Star vStar=(CSE_Star)aSE;
               recReachable(vStar.getArg());
               break;
            case CGrammarCodes.scSEPlus:
               CSE_Plus vPlus=(CSE_Plus)aSE;
               recReachable(vPlus.getArg());
               break;
            case CGrammarCodes.scSEOption:
               CSE_Option vOption=(CSE_Option)aSE;
               recReachable(vOption.getArg());
               break;
            default:
                 assert false: String.format("Illegal SortCode=%d in CGrammarAnalyzer.recReachable", aSE.sortCode());
                 break;
        }
    }

    /**
     * Determine nullability of every node of the syntax expression tree.
     * 
     * @param aSE the CSE object.
     */
    protected void recNullable(CSE aSE){
        CSymDec vSym;
        boolean vB;
         switch(aSE.sortCode()){
            case CGrammarCodes.scSEEmpty:
                analysisOfECFG(aSE).fNullable=false;
                break;
            case CGrammarCodes.scSEEps:
                analysisOfECFG(aSE).fNullable=true;
                break;
            case CGrammarCodes.scSESym:
                CSE_Sym sym=(CSE_Sym)aSE; 
                vSym=sym.getDec();
                analysisOfECFG(aSE).fNullable=analysisOfECFG(vSym).fNullable;
                break;
            case CGrammarCodes.scSEMultiDot:
                CSE_MultiDot vDot=(CSE_MultiDot)aSE; 
                vB=true;
                for(int i=0;i<=vDot.getList().count()-1;i++){
                    recNullable(vDot.getList().getElt(i));
                    if(!analysisOfECFG(vDot.getList().getElt(i)).fNullable){
                        vB=false;
                    }
                }
                analysisOfECFG(aSE).fNullable=vB;
                break;
            case CGrammarCodes.scSEMultiStick:
                CSE_MultiStick vStick=(CSE_MultiStick)aSE;
                vB=false;
                for(int i=0;i<=vStick.getList().count()-1;i++){
                    recNullable((CSE)vStick.getList().getElt(i));
                    if(analysisOfECFG(vStick.getList().getElt(i)).fNullable){
                        vB=true;
                    }
                 }
                analysisOfECFG(aSE).fNullable=vB;
                break;
           case CGrammarCodes.scSEAlt:
                CSE_Alt vAlt=(CSE_Alt)aSE;
                recNullable(vAlt.getLeft());
                recNullable(vAlt.getRight());
                analysisOfECFG(aSE).fNullable=analysisOfECFG(vAlt.getLeft()).fNullable;
                break;
            case CGrammarCodes.scSEAlt2:
                CSE_Alt2 vAlt2=(CSE_Alt2)aSE;
                recNullable(vAlt2.getLeft());
                recNullable(vAlt2.getRight());
                analysisOfECFG(aSE).fNullable=analysisOfECFG(vAlt2.getLeft()).fNullable;
                break;
           case CGrammarCodes.scSEStar:
               CSE_Star vStar=(CSE_Star)aSE;
               recNullable(vStar.getArg());
               analysisOfECFG(aSE).fNullable=true;
               break;
           case CGrammarCodes.scSEPlus:
               CSE_Plus vPlus=(CSE_Plus)aSE;
               recNullable(vPlus.getArg());
               analysisOfECFG(aSE).fNullable=analysisOfECFG(vPlus.getArg()).fNullable;
               break;
           case CGrammarCodes.scSEOption:
               CSE_Option vOption=(CSE_Option)aSE;
               recNullable(vOption.getArg());
               analysisOfECFG(aSE).fNullable=true;
               break;
           default:
                 assert false: String.format("Illegal SortCode=%d in CGrammarAnalyzer.recNullable", aSE.sortCode());
                 break;
        }
    }
    
    /**
     * Recursively determine the root and path of aNode.
     * 
     * @param aRoot the string representation of the root for aNode.
     * @param aPath the string representation of the path for aNode.
     * @param aNode the CNode object.
     */
    protected void recRootPath(String aRoot, String aPath, CNode aNode){
      analysisOfECFG(aNode).fRoot = aRoot;
      analysisOfECFG(aNode).fPath = aPath;
      for(int i = 0; i < aNode.count(); i++){
          recRootPath(aRoot, aPath + '_' + i, aNode.getNode(i));
      }
    }
    
    /**
     * Determine empty of every node of the syntax expression tree.
     * 
     * @param aSE the CSE object.
     */
    protected void recEmpty(CSE aSE){
        CSymDec vSym;
        boolean vB;
        switch(aSE.sortCode()){
            case CGrammarCodes.scSEEmpty:
                analysisOfECFG(aSE).fEmpty=true;
                break;
            case CGrammarCodes.scSEEps:
                analysisOfECFG(aSE).fEmpty=false;
                break;
            case CGrammarCodes.scSESym:
                CSE_Sym aSym=(CSE_Sym)aSE;
                vSym=aSym.getDec();
                analysisOfECFG(aSE).fEmpty=analysisOfECFG(vSym).fEmpty;
                break;
            case CGrammarCodes.scSEMultiDot:
                CSE_MultiDot aDot=(CSE_MultiDot)aSE;
                vB=false;
                for(int i=0;i<=aDot.getList().count()-1;i++){
                    recEmpty((CSE)aDot.getList().getElt(i));  
                    if(analysisOfECFG(aDot.getList().getElt(i)).fEmpty)
                      vB=true;
                }
                analysisOfECFG(aSE).fEmpty=vB;
                break;
            case CGrammarCodes.scSEMultiStick:
                CSE_MultiStick aStick=(CSE_MultiStick)aSE;
                vB=true;
                for(int i=0;i<=aStick.getList().count()-1;i++){
                   recEmpty((CSE)aStick.getList().getElt(i)); 
                   if(!analysisOfECFG(aStick.getList().getElt(i)).fEmpty)
                        vB=false;
                }
                    
                analysisOfECFG(aSE).fEmpty=vB;
                break;
             case CGrammarCodes.scSEAlt:
                 CSE_Alt vAlt=(CSE_Alt)aSE;
                 recEmpty((CSE)vAlt.getLeft());
                 analysisOfECFG(aSE).fEmpty=analysisOfECFG(vAlt.getLeft()).fEmpty;
                 break;
             case CGrammarCodes.scSEAlt2:
                 CSE_Alt2 vAlt2=(CSE_Alt2)aSE;
                 recEmpty((CSE)vAlt2.getLeft());
                 analysisOfECFG(aSE).fEmpty=analysisOfECFG(vAlt2.getLeft()).fEmpty;
                 break;
             case CGrammarCodes.scSEStar:
                 analysisOfECFG(aSE).fEmpty=false;
                 break;
             case CGrammarCodes.scSEPlus:
                 CSE_Plus aPlus=(CSE_Plus)aSE;
                 recEmpty((CSE)aPlus.getArg());
                 analysisOfECFG(aSE).fEmpty=analysisOfECFG(aPlus.getArg()).fEmpty;
                 break;
             case CGrammarCodes.scSEOption:
                 analysisOfECFG(aSE).fEmpty=false;
                 break;
             default:
                 assert false: String.format("Illegal SortCode=%d in CGrammarAnalyzer.recEmpty", aSE.sortCode());
                  break;
        }
    }

    /**
     * Determine <code>First</code> set of every node of the syntax expression tree.
     * 
     * @param aSE the CSE object.
     */
    protected void recFirst(CSE aSE){
        CSymDec vSym;
        CECFGAnalysis vAnaLeft,vAnaRight,vAna;
        IntAlphabet vFirst;
        boolean vB;

        switch(aSE.sortCode()){
            case CGrammarCodes.scSEEmpty:
                analysisOfECFG(aSE).fFirst=new IntAlphabet();
                break;
            case CGrammarCodes.scSEEps:
                analysisOfECFG(aSE).fFirst=new IntAlphabet();
                break;
            case CGrammarCodes.scSESym:
                CSE_Sym sym=(CSE_Sym)aSE;
                vSym=sym.getDec();
                analysisOfECFG(aSE).fFirst=analysisOfECFG(vSym).fFirst;
                break;
            case CGrammarCodes.scSEMultiDot:
                CSE_MultiDot vDot=(CSE_MultiDot)aSE;
                vFirst=new IntAlphabet();
                vB=true;
                for(int i=0;i<=vDot.getList().count()-1;i++){
                    recFirst(vDot.getList().getElt(i));
                    vAna=analysisOfECFG(vDot.getList().getElt(i));
                    if(vB){
                        vFirst.bcUnion(vAna.fFirst);
                    }
                    vB=vB & vAna.fNullable;
                }
                analysisOfECFG(aSE).fFirst=vFirst;
                break;
           case CGrammarCodes.scSEMultiStick:
                CSE_MultiStick vStick=(CSE_MultiStick)aSE;
                vFirst=new IntAlphabet();
                for(int i=0;i<=vStick.getList().count()-1;i++){
                    recFirst(vStick.getList().getElt(i));
                    vFirst.bcUnion(analysisOfECFG(vStick.getList().getElt(i)).fFirst);
                }
                analysisOfECFG(aSE).fFirst=vFirst;
                break;
           case CGrammarCodes.scSEAlt:
                CSE_Alt vAlt=(CSE_Alt)aSE;
                recFirst(vAlt.getLeft());
                recFirst(vAlt.getRight());
                vAnaLeft=analysisOfECFG(vAlt.getLeft());
                vAnaRight=analysisOfECFG(vAlt.getRight());
                if(vAnaLeft.fNullable)
                    analysisOfECFG(aSE).fFirst=IntAlphabetOps.union(vAnaLeft.fFirst,vAnaRight.fFirst);
                else analysisOfECFG(aSE).fFirst=vAnaLeft.fFirst;
                break;
           case CGrammarCodes.scSEAlt2:
                CSE_Alt2 vAlt2=(CSE_Alt2)aSE;
                recFirst(vAlt2.getLeft());
                recFirst(vAlt2.getRight());
                vAnaLeft=analysisOfECFG(vAlt2.getLeft());
                vAnaRight=analysisOfECFG(vAlt2.getRight());
                if(vAnaLeft.fNullable)
                    analysisOfECFG(aSE).fFirst=IntAlphabetOps.union(vAnaLeft.fFirst,vAnaRight.fFirst);
                else analysisOfECFG(aSE).fFirst=vAnaLeft.fFirst;
                break;
           case CGrammarCodes.scSEStar:
               CSE_Star vStar=(CSE_Star)aSE;
               recFirst(vStar.getArg());
               analysisOfECFG(aSE).fFirst=analysisOfECFG(vStar.getArg()).fFirst;
               break;
           case CGrammarCodes.scSEPlus:
               CSE_Plus vPlus=(CSE_Plus)aSE;
               recFirst(vPlus.getArg());
               analysisOfECFG(aSE).fFirst=analysisOfECFG(vPlus.getArg()).fFirst;
               break;
           case CGrammarCodes.scSEOption:
               CSE_Option vOption=(CSE_Option)aSE;
               recFirst(vOption.getArg());
               analysisOfECFG(aSE).fFirst=analysisOfECFG(vOption.getArg()).fFirst;
               break;
           default:
                 assert false: String.format("Illegal SortCode=%d in CGrammarAnalyzer.recFirst", aSE.sortCode());
                 break;
       }
    }

    /**
     * Determine <code>Last</code> set of every node of the syntax expression tree.
     * 
     * @param aSE the CSE object.
     */
    protected void recLast(CSE aSE){
        CSymDec vSym;
        CECFGAnalysis vAna,vAnaLeft,vAnaRight;
        IntAlphabet vLast;
        boolean vB;
        switch(aSE.sortCode()){
            case CGrammarCodes.scSEEmpty:
                analysisOfECFG(aSE).fLast=new IntAlphabet();
                break;
            case CGrammarCodes.scSEEps:
                analysisOfECFG(aSE).fLast=new IntAlphabet();
                break;
            case CGrammarCodes.scSESym:
                CSE_Sym sym=(CSE_Sym)aSE;
                vSym=sym.getDec();
                analysisOfECFG(aSE).fLast=analysisOfECFG(vSym).fLast;
                break;
            case CGrammarCodes.scSEMultiDot:
                CSE_MultiDot vDot=(CSE_MultiDot)aSE;
                vLast=new IntAlphabet();
                vB=true;
                 for(int i=vDot.getList().count()-1;i>=0;i--){
                    recLast(vDot.getList().getElt(i));
                    vAna=analysisOfECFG(vDot.getList().getElt(i));
                    if(vB){
                        vLast.bcUnion(vAna.fLast);
                    }
                    vB=vB & vAna.fNullable;
                }
                analysisOfECFG(aSE).fLast=vLast;
                break;
             case CGrammarCodes.scSEMultiStick:
                CSE_MultiStick vStick=(CSE_MultiStick)aSE;
                vLast=new IntAlphabet();
                 for(int i=0;i<=vStick.getList().count()-1;i++){
                     recLast(vStick.getList().getElt(i));
                     vLast.bcUnion(analysisOfECFG(vStick.getList().getElt(i)).fLast);
                 }
                 analysisOfECFG(aSE).fLast=vLast;
                break;
             case CGrammarCodes.scSEAlt:
                CSE_Alt vAlt=(CSE_Alt)aSE;
                recLast(vAlt.getLeft());
                recLast(vAlt.getRight());
                vAnaLeft=analysisOfECFG(vAlt.getLeft());
                vAnaRight=analysisOfECFG(vAlt.getRight());
                if(vAnaLeft.fNullable)
                    analysisOfECFG(aSE).fLast=IntAlphabetOps.union(vAnaLeft.fLast,vAnaRight.fLast);
                else analysisOfECFG(aSE).fLast=vAnaLeft.fLast;
                break;
             case CGrammarCodes.scSEAlt2:
                CSE_Alt2 vAlt2=(CSE_Alt2)aSE;
                recLast(vAlt2.getLeft());
                recLast(vAlt2.getRight());
                vAnaLeft=analysisOfECFG(vAlt2.getLeft());
                vAnaRight=analysisOfECFG(vAlt2.getRight());
                if(vAnaLeft.fNullable)
                    analysisOfECFG(aSE).fLast=IntAlphabetOps.union(vAnaLeft.fLast,vAnaRight.fLast);
                else analysisOfECFG(aSE).fLast=vAnaLeft.fLast;
                break;
           case CGrammarCodes.scSEStar:
               CSE_Star vStar=(CSE_Star)aSE;
               recLast(vStar.getArg());
               analysisOfECFG(aSE).fLast=analysisOfECFG(vStar.getArg()).fLast;
               break;
           case CGrammarCodes.scSEPlus:
               CSE_Plus vPlus=(CSE_Plus)aSE;
               recLast(vPlus.getArg());
               analysisOfECFG(aSE).fLast=analysisOfECFG(vPlus.getArg()).fLast;
               break;
           case CGrammarCodes.scSEOption:
               CSE_Option vOption=(CSE_Option)aSE;
               recLast(vOption.getArg());
               analysisOfECFG(aSE).fLast=analysisOfECFG(vOption.getArg()).fLast;
               break;
           default:
                 assert false: String.format("Illegal SortCode=%d in CGrammarAnalyzer.recLast", aSE.sortCode());
                 break;
       }
    }

   /**
     * Determine <code>Follow</code> set of every node of the syntax expression tree.
     * 
     * @param aSE the CSE object.
     * @param aFollow the <code>Follow</code> set of aSE.
     * @param Changed returns <code>true</code> if the <code>Follow</code> set is changed, otherwise <code>false</code>.
     *
     */
    protected void recFollow(CSE aSE, IntAlphabet aFollow, CBoolean Changed)                                                                                                                                                                                                                                                                                                                                                                                      {
        CSE vTerm;
        CSymDec vSym;
        IntAlphabet vNewFollow,vFollow;
        vFollow=new IntAlphabet();
        
        CECFGAnalysis vAnaLeft,vAnaRight;
        switch(aSE.sortCode()){
            case CGrammarCodes.scSEEmpty:
                  //analysisOfECFG(aSE).fFollow.;   
                analysisOfECFG(aSE).fFollow=aFollow;
                break;
            case CGrammarCodes.scSEEps:
                analysisOfECFG(aSE).fFollow=aFollow;
                break;
            case CGrammarCodes.scSESym:
                 CSE_Sym sym=(CSE_Sym)aSE;
                 analysisOfECFG(aSE).fFollow=aFollow;
                 vSym=sym.getDec();
                 vNewFollow=IntAlphabetOps.union(analysisOfECFG(vSym).fFollow,aFollow);
                 if(!vNewFollow.equals(analysisOfECFG(vSym).fFollow)){
                    analysisOfECFG(vSym).fFollow=vNewFollow;
                    Changed.b=true;
                 }
                 break;
            case CGrammarCodes.scSEMultiDot:
                 analysisOfECFG(aSE).fFollow=aFollow;
                 CSE_MultiDot vDot=(CSE_MultiDot)aSE;
                 vFollow.bcUnion(aFollow);
                 for(int i=vDot.getList().count()-1;i>=0;i--){
                     //DOUBLE CHECK this (see Gerard's notes)
                     vTerm=vDot.getList().getElt(i);
                     recFollow(vTerm,vFollow,Changed);
                     if(analysisOfECFG(vTerm).fNullable)
                         vFollow.bcUnion(analysisOfECFG(vTerm).fFirst);
                     else vFollow=analysisOfECFG(vTerm).fFirst;
                 }
                 break;
             case CGrammarCodes.scSEMultiStick:
                analysisOfECFG(aSE).fFollow=aFollow;
                CSE_MultiStick vStick=(CSE_MultiStick)aSE;
                for(int i=0;i<=vStick.getList().count()-1;i++){
                    recFollow(vStick.getList().getElt(i),aFollow,Changed);
                }
                break;
            case CGrammarCodes.scSEAlt:
                analysisOfECFG(aSE).fFollow=aFollow;
                CSE_Alt vAlt=(CSE_Alt)aSE;
                
                vAnaLeft=analysisOfECFG(vAlt.getLeft());
                vAnaRight=analysisOfECFG(vAlt.getRight());
                
                recFollow(vAlt.getRight(),vAnaLeft.fFirst,Changed);
                if(vAnaRight.fNullable){
                   vAnaRight.fFirst.bcUnion(aFollow);
                   IntAlphabet vAlph1=IntAlphabetOps.union(vAnaRight.fFirst,vAnaLeft.fFirst);
                   IntAlphabet vAlph2=IntAlphabetOps.union(vAlph1, aFollow);
                   recFollow(vAlt.getLeft(), vAlph2, Changed);
                }else{
                    IntAlphabet vAlph1=IntAlphabetOps.union(vAnaRight.fFirst,aFollow);
                    recFollow(vAlt.getLeft(),vAlph1,Changed);
                }
                break;
            case CGrammarCodes.scSEAlt2:
                analysisOfECFG(aSE).fFollow=aFollow;
                CSE_Alt2 vAlt2=(CSE_Alt2)aSE;
                
                vAnaLeft=analysisOfECFG(vAlt2.getLeft());
                vAnaRight=analysisOfECFG(vAlt2.getRight());
                
                recFollow(vAlt2.getRight(),vAnaLeft.fFirst,Changed);
                if(vAnaRight.fNullable){
                   vAnaRight.fFirst.bcUnion(aFollow);
                   IntAlphabet vAlph1=IntAlphabetOps.union(vAnaRight.fFirst,vAnaLeft.fFirst);
                   IntAlphabet vAlph2=IntAlphabetOps.union(vAlph1, aFollow);
                   recFollow(vAlt2.getLeft(), vAlph2, Changed);
                }else{
                    IntAlphabet vAlph1=IntAlphabetOps.union(vAnaRight.fFirst,aFollow);
                    recFollow(vAlt2.getLeft(),vAlph1,Changed);
                }
                break;
            case CGrammarCodes.scSEStar:
                analysisOfECFG(aSE).fFollow=aFollow;
                CSE_Star vStar=(CSE_Star)aSE;
                IntAlphabet vAlph1=IntAlphabetOps.union(aFollow,analysisOfECFG(vStar.getArg()).fFirst);
                recFollow(vStar.getArg(),vAlph1,Changed);
                break;
            case CGrammarCodes.scSEPlus:
                analysisOfECFG(aSE).fFollow=aFollow;
                CSE_Plus vPlus=(CSE_Plus)aSE;
                IntAlphabet vAlph=IntAlphabetOps.union(aFollow,analysisOfECFG(vPlus.getArg()).fFirst);
                recFollow(vPlus.getArg(),vAlph,Changed);
                break;
            case CGrammarCodes.scSEOption:
                analysisOfECFG(aSE).fFollow=aFollow;
                CSE_Option vOption=(CSE_Option)aSE;
                recFollow(vOption.getArg(),aFollow,Changed);
                break;
             default:
                 assert false: String.format("Illegal SortCode=%d in CGrammarAnalyzer.recFollow", aSE.sortCode());
                 break;
        }
       }

    /**
     * Check if a CSE object is ELL(1).
     * @param aSE
     */
    protected void recDet(CSE aSE){
        CSE vTerm;
        CECFGAnalysis vAna,vAnaLeft,vAnaRight,vAnaI;
        boolean vDet;
        switch(aSE.sortCode()){
            case CGrammarCodes.scSEEmpty:
                analysisOfECFG(aSE).fELL1Det=true;
                break;
            case CGrammarCodes.scSEEps:
                analysisOfECFG(aSE).fELL1Det=true;
                break;
            case CGrammarCodes.scSESym:
                analysisOfECFG(aSE).fELL1Det=true;
                break;
            case CGrammarCodes.scSEAlt:
                CSE_Alt vAlt= (CSE_Alt)aSE;
                recDet(vAlt.getLeft());
                recDet(vAlt.getRight());
                vAnaLeft=analysisOfECFG(vAlt.getLeft());
                vAnaRight=analysisOfECFG(vAlt.getRight());
                analysisOfECFG(aSE).fELL1Det=vAnaLeft.fELL1Det & vAnaRight.fELL1Det & !vAnaRight.fNullable & (IntAlphabetOps.intersection(vAnaRight.fFirst, analysisOfECFG(aSE).fFollow))==new IntAlphabet();
                break;
           case CGrammarCodes.scSEAlt2:
                CSE_Alt2 vAlt2= (CSE_Alt2)aSE;
                recDet(vAlt2.getLeft());
                recDet(vAlt2.getRight());
                vAnaLeft=analysisOfECFG(vAlt2.getLeft());
                vAnaRight=analysisOfECFG(vAlt2.getRight());
                analysisOfECFG(aSE).fELL1Det=vAnaLeft.fELL1Det & vAnaRight.fELL1Det & !vAnaRight.fNullable & (IntAlphabetOps.intersection(vAnaRight.fFirst, analysisOfECFG(aSE).fFollow))==new IntAlphabet();
                break;
           case CGrammarCodes.scSEMultiDot:
                CSE_MultiDot vDot=(CSE_MultiDot)aSE;
                vDet=true;
                for(int i=vDot.getList().count()-1;i>=0;i--){
                    vTerm=vDot.getList().getElt(i);
                    recDet(vTerm);
                    if(!analysisOfECFG(vTerm).fELL1Det)
                        vDet=false;
                }
                analysisOfECFG(aSE).fELL1Det=vDet;
                break;
            case CGrammarCodes.scSEMultiStick:
                 CSE_MultiStick vStick=(CSE_MultiStick)aSE;
                 vDet=true;
                 for(int i=0; i<=vStick.getList().count()-1;i++){
                        recDet(vStick.getList().getElt(i));
                        if(!analysisOfECFG(vStick.getList().getElt(i)).fELL1Det)
                            vDet=false;
                 }
                // pairwise disjointness of lookaheadsets
                 for(int i=0;i<=vStick.count()-1;i++){
                     vAnaI=analysisOfECFG(vStick.getList().getElt(i));
                     for(int j=i+1;j<=vStick.getList().count()-1;j++)
                         if(!(IntAlphabetOps.intersection(h(vAnaI),h(analysisOfECFG(vStick.getList().getElt(j)))).equals(new IntAlphabet()))){
                             vDet=false;
                         }
                 }
                 analysisOfECFG(aSE).fELL1Det=vDet;
                 break;
            case CGrammarCodes.scSEStar:
                 CSE_Star vStar=(CSE_Star)aSE;
                 recDet(vStar.getArg());
                 vAna=analysisOfECFG(vStar.getArg());
                 analysisOfECFG(aSE).fELL1Det=vAna.fELL1Det & !vAna.fNullable & (IntAlphabetOps.intersection(vAna.fFirst, analysisOfECFG(aSE).fFollow)==new IntAlphabet());
                 break;
            case CGrammarCodes.scSEPlus:
                 CSE_Plus vPlus=(CSE_Plus)aSE;
                 recDet(vPlus.getArg());
                 vAna=analysisOfECFG(vPlus.getArg());
                 analysisOfECFG(aSE).fELL1Det=vAna.fELL1Det & !vAna.fNullable & (IntAlphabetOps.intersection(vAna.fFirst, analysisOfECFG(aSE).fFollow)==new IntAlphabet());
                 break;

            case CGrammarCodes.scSEOption:
                 CSE_Option vOption=(CSE_Option)aSE;
                 recDet(vOption.getArg());
                 vAna=analysisOfECFG(vOption.getArg());
                 analysisOfECFG(aSE).fELL1Det=vAna.fELL1Det & !vAna.fNullable & (IntAlphabetOps.intersection(vAna.fFirst, analysisOfECFG(aSE).fFollow)==new IntAlphabet());
                 break;
            default:
                  assert false: String.format("Illegal SortCode=%d in CGrammarAnalyzer.recFollow", aSE.sortCode());
                  break;
        }
      }

    /**
     * Creates a new <code> CGrammarAnalyzer </code> with a new <code>CGrammar</code> object.
     */
    public CGrammarAnalyzer(){
        fGrammar=new CGrammar();
    }
    
    /**
     * Check the grammar for well-formedness and determine all its analysis information. 
     */
    public void analyzeGrammar(){
        clearAnalysis();
        
        analyzeLexemeDefs();
        analyzeTerminalDefs();
        
        compRootPath();
        compEmpty();
        compNullable();
        compReachable();
        compFirst();
        compLast();
        
       // analyzeStartExpr();
        CSE vStart;
        vStart=fGrammar.getStartExpr();
        //Reachable is taken care of by compReachable
        recEmpty(vStart);
        recNullable(vStart);
        recFirst(vStart);
        recLast(vStart);
        //recFollow(vStart,new IntAlphabet(),vBool);
        recDet(vStart);
        
        compFollow();
        compDet();//deterministic
        
    }
    
    /**
     *  Check lexeme definitions of a grammar for well-formedness and determine all their analysis information. 
     */
    protected void analyzeLexemeDefs(){
        CREAnalysis vLeft,vRight;
        CLexemeDef vLexemeDef;
        for(int i=0;i<=fGrammar.getGrammarContext().getLexemeDefs().count()-1;i++){
            vLexemeDef=fGrammar.getGrammarContext().getLexemeDefs().getElt(i);
            analyzeRE(vLexemeDef.getBody());
            vRight=analysisOfRE(vLexemeDef.getBody());
            vLeft=analysisOfRE(vLexemeDef);
            vLeft.fNullable=vRight.fNullable;
            vLeft.fFirst=vRight.fFirst;
            
        }
    }
    
    /**
     * Check terminal definitions of a grammar for well-formedness and determine all their analysis information. 
     */
    protected void analyzeTerminalDefs(){
       
        CREAnalysis vLeft,vRight;
        CTerminalDef vTerminalDef;
        for(int i=0;i<=fGrammar.getGrammarContext().getTerminalDefs().count()-1;i++){
            vTerminalDef=fGrammar.getGrammarContext().getTerminalDefs().getElt(i);
            analyzeRE(vTerminalDef.getBody());
            vRight=analysisOfRE(vTerminalDef.getBody());
            vLeft=analysisOfRE(vTerminalDef);
            vLeft.fNullable=vRight.fNullable;
            vLeft.fFirst=vRight.fFirst;
            
        }
    }
  /*  protected void analyzeStartExpr(){
        MyBoolean vBool=new MyBoolean();
        vBool.b=false;
        CSE vStart;
        
        vStart=fGrammar.getStartExpr();
        //Reachable is taken care of by compReachable
        recEmpty(vStart);
        recNullable(vStart);
        recFirst(vStart);
        recLast(vStart);
        recFollow(vStart,new IntAlphabet(),vBool);
        recDet(vStart);
            
    }*/
    

    /**
     * Reset analysis info. of the lexical and context free grammar parts.
     */
        public void clearAnalysis(){
        clearREAnalysis();
        clearECFGAnalysis();
    }
    
    /**
     *  Reset the analysis info. of the lexical part of a grammar.
     */
    protected void clearREAnalysis(){
         int i;
         CLexemeDef vLexemeDef;
         CTerminalDef vTerminalDef;
         
         //clear all lexemes and their rhs 
        for(i=0;i<=fGrammar.getGrammarContext().getLexemeDefs().count()-1;i++){
            vLexemeDef=fGrammar.getGrammarContext().getLexemeDefs().getElt(i);
            if(analysisOfECFG(vLexemeDef)==null)
                initREAnalysis(vLexemeDef);
            else analysisOfRE(vLexemeDef).clear();
            recClearREAnalysis(vLexemeDef.getBody());
        }
         
         //clear all terminals
        for(i=0;i<=fGrammar.getGrammarContext().getTerminalDefs().count()-1;i++){
            vTerminalDef=fGrammar.getGrammarContext().getTerminalDefs().getElt(i);
            if(analysisOfECFG(vTerminalDef)==null)
                initREAnalysis(vTerminalDef);
            else analysisOfRE(vTerminalDef).clear();
            recClearREAnalysis(vTerminalDef.getBody());
        }
    }
    
    /**
     * Reset the analysis info. of the context free part of a grammar.
     */
    protected void clearECFGAnalysis(){
        int i;
        CTerminalDec vTermDec;
        CNonTerminalDef vNonTerminalDef;
        
        //clear all terminals
        for(i=0;i<=fGrammar.getGrammarContext().getTerminalDefs().count()-1;i++){
            vTermDec=fGrammar.getGrammarContext().getTerminalDefs().getElt(i);
            if(analysisOfECFG(vTermDec)==null)
                initECFGAnalysis(vTermDec);
            else analysisOfECFG(vTermDec).clear();
        }
        
        //clear all nonTerminals
        for(i=0;i<=fGrammar.getGrammarContext().getNonTerminalDefs().count()-1;i++){
            vNonTerminalDef=fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i);
            if(analysisOfECFG(vNonTerminalDef)==null)
                initECFGAnalysis(vNonTerminalDef);
            else analysisOfECFG(vNonTerminalDef).clear();
            recClearECFGAnalysis(vNonTerminalDef.getBody());
        }
        
         // clear analysis of start expression
         recClearECFGAnalysis(fGrammar.getStartExpr());
    }

    /**
     * Compute reachability of every terminal and nonterminal of the grammar.
     */
    public void compReachable(){
     for(int i=0;i<=fGrammar.getGrammarContext().getTerminalDefs().count()-1;i++){
         analysisOfECFG(fGrammar.getGrammarContext().getTerminalDefs().getElt(i)).fReachable=false;
     }
     for(int i=0;i<=fGrammar.getGrammarContext().getNonTerminalDefs().count()-1;i++){
         analysisOfECFG(fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i)).fReachable=false;
     }
     recReachable(fGrammar.getStartExpr());
    }

    /**
     * Compute nullability of every terminal and nonterminal of the grammar.
     */
    public void compNullable(){
        boolean vModified;
        CNonTerminalDef vNonTerminalDef;
        for(int i=0;i<=fGrammar.getGrammarContext().getTerminalDefs().count()-1;i++){
            analysisOfECFG(fGrammar.getGrammarContext().getTerminalDefs().getElt(i)).fNullable=false;
        }
        for(int i=0;i<=fGrammar.getGrammarContext().getNonTerminalDefs().count()-1;i++){
            analysisOfECFG(fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i)).fNullable=false;
        }
        // iterate until fixpoint reached
        do{
            vModified=false;
            for(int i=0;i<=fGrammar.getGrammarContext().getNonTerminalDefs().count()-1;i++){
               vNonTerminalDef=(CNonTerminalDef)fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i);
               if(!analysisOfECFG(vNonTerminalDef).fNullable){
                   recNullable(vNonTerminalDef.getBody());
                   if(analysisOfECFG(vNonTerminalDef.getBody()).fNullable){
                      analysisOfECFG(vNonTerminalDef).fNullable=true;
                      vModified=true;
                   }
               }
            }
        }while(vModified);
     }
    
    /**
     * Determine the path of every nonterminal of the grammar.
     */
    public void compRootPath(){
      for(int i=0;i<=fGrammar.getGrammarContext().getNonTerminalDefs().count()-1;i++){
            recRootPath(
                    fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i).getName(),
                    "",
                    fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i).getBody());
      }
   }
    
    /**
     * Compute empty of every terminal and nonterminal of the grammar.
     */
    public void compEmpty(){
        boolean vModified;
        CNonTerminalDef vNonTerminalDef;
        for(int i=0;i<=fGrammar.getGrammarContext().getTerminalDefs().count()-1;i++){
            analysisOfECFG(fGrammar.getGrammarContext().getTerminalDefs().getElt(i)).fEmpty=false;
        }
        for(int i=0;i<=fGrammar.getGrammarContext().getNonTerminalDefs().count()-1;i++){
            analysisOfECFG(fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i)).fEmpty=true;
        }
        do{
            vModified=false;
            for(int i=0;i<=fGrammar.getGrammarContext().getNonTerminalDefs().count()-1;i++){
                vNonTerminalDef=fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i);
                if(analysisOfECFG(vNonTerminalDef).fEmpty){
                    recEmpty(vNonTerminalDef.getBody()); 
                    if(!analysisOfECFG(vNonTerminalDef.getBody()).fEmpty){
                        analysisOfECFG(vNonTerminalDef).fEmpty=false;
                        vModified=true;
                    }
                }
            }
            recEmpty(fGrammar.getStartExpr());
          }while(vModified);
 }

   /**
     * Compute <code>First</code> set of every nonterminal of the grammar.
     */
    public void compFirst(){
        boolean changed;
        CNonTerminalDef vNonTerminalDef;
        IntAlphabet vNewFirst;
        // initialize FFirst for all symbols (reflexive closure)
        for(int i=0;i<=fGrammar.getGrammarContext().symbolCount()-1;i++){
            analysisOfECFG(fGrammar.getGrammarContext().getSymbol(i)).fFirst=IntAlphabetOps.singleton(i);
        }
        do{
            changed=false;
            for(int i=0;i<fGrammar.getGrammarContext().getNonTerminalDefs().count();i++){
                vNonTerminalDef=(CNonTerminalDef)fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i);
                recFirst(vNonTerminalDef.getBody());
                vNewFirst=IntAlphabetOps.union(analysisOfECFG(vNonTerminalDef).fFirst,analysisOfECFG(vNonTerminalDef.getBody()).fFirst);
                if(!(analysisOfECFG(vNonTerminalDef).fFirst.equals(vNewFirst))){
                    analysisOfECFG(vNonTerminalDef).fFirst=vNewFirst;
                    changed=true;
                }
            }
        }while(changed);
  }

     /**
     * Compute <code>Last</code> set of every nonterminal of the grammar.
     */
    public void compLast(){
        boolean changed;
        CNonTerminalDef vNonTerminalDef;
        IntAlphabet vNewLast;
        // initialize FLast for all symbols (reflexive closure)
        for(int i=0;i<=fGrammar.getGrammarContext().symbolCount()-1;i++){
            analysisOfECFG(fGrammar.getGrammarContext().getSymbol(i)).fLast=IntAlphabetOps.singleton(i);
        }

        // iteratively compute FLast until no more changes
         do{
            changed=false;
            for(int i=0;i<fGrammar.getGrammarContext().getNonTerminalDefs().count();i++){
                vNonTerminalDef=(CNonTerminalDef)fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i);
                recLast(vNonTerminalDef.getBody());
                vNewLast=IntAlphabetOps.union(analysisOfECFG(vNonTerminalDef).fLast,analysisOfECFG(vNonTerminalDef.getBody()).fLast);
                if(!(analysisOfECFG(vNonTerminalDef).fLast.equals(vNewLast))){
                    analysisOfECFG(vNonTerminalDef).fLast=vNewLast;
                    changed=true;
                }
            }
        }while(changed);
    }

     /**
     * Compute <code>Follow</code> set of every nonterminal of the grammar.
     */
    public void compFollow(){
        CBoolean changed=new CBoolean();
        CNonTerminalDef vNonTerminalDef;
         // initialize FFollow for all symbols
        for(int i=0;i<=fGrammar.getGrammarContext().symbolCount()-1;i++){
            analysisOfECFG(fGrammar.getGrammarContext().getSymbol(i)).fFollow=new IntAlphabet();
        }
        
        CBoolean vDummy=new CBoolean();
        recFollow(fGrammar.getStartExpr(),new IntAlphabet(),vDummy);
        // iteratively compute FFollow until no more changes
        do{
            changed.b=false;
            for(int i=0;i<fGrammar.getGrammarContext().getNonTerminalDefs().count();i++){
                vNonTerminalDef=(CNonTerminalDef)fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i);
                recFollow(vNonTerminalDef.getBody(),analysisOfECFG(vNonTerminalDef).fFollow,changed);
            }
        }while(changed.b);
    }

     /**
     * Determine if the grammar is ELL(1).
     */
    public void compDet(){
        int i;
        CNonTerminalDef vNonTerminalDef;
        
        for(i=0;i<fGrammar.getGrammarContext().getTerminalDefs().count();i++){
            analysisOfECFG(fGrammar.getGrammarContext().getTerminalDefs().getElt(i)).fELL1Det=true;
        }
        
        for(i=0;i<fGrammar.getGrammarContext().getNonTerminalDefs().count();i++){
            vNonTerminalDef=(CNonTerminalDef)fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i);
            recDet(vNonTerminalDef.getBody());
            analysisOfECFG(vNonTerminalDef).fELL1Det=analysisOfECFG(vNonTerminalDef.getBody()).fELL1Det;
        }
     }
    
 

    /**
     * Auxiliary routine to facilitate access to REanalysis annotation of node
     * 
     * @param aNode the RE object.
     */
        public void initREAnalysis(CNode aNode){
        aNode.setAnno("REAna", new CREAnalysis());
    }

    /**
     * Returns analysis information of a given RE of type CNode.
     * 
     * @param aNode the RE object.
     * @return an instance of CREAnalysis containing analysis information of a RE.
     */
    public CREAnalysis analysisOfRE(CNode aNode){
        
        if (aNode.hasAnno("REAna")){
            return (CREAnalysis)aNode.getAnno("REAna");
        } else return null;
    }


    /**
     * Auxiliary routine to facilitate access to ECFGanalysis annotation of node.
     * 
     * @param aNode
     */
        public void initECFGAnalysis(CNode aNode){
        aNode.setAnno("ECFGAna", new CECFGAnalysis());
    }
    
    /**
     * Returns analysis information of a given syntax expression (SE) of type CNode.
     * 
     * @param aNode the SE object.
     * @return an instance of CECFGAnalysis containing analysis information of a SE.
     */
    public CECFGAnalysis analysisOfECFG(CNode aNode){
        
        if (aNode.hasAnno("ECFGAna")){
            return (CECFGAnalysis)aNode.getAnno("ECFGAna");
        } else return null;
    }
    
    /**
     *
     * @param aAnalysis
     * @return
     */
    public static IntAlphabet h(CECFGAnalysis aAnalysis){
       if(aAnalysis.fNullable){
            return IntAlphabetOps.union(aAnalysis.fFirst,aAnalysis.fFollow);
       } else return aAnalysis.fFirst;
    }
    
    /**
     *
     * @param aSE
     * @return
     */
    public IntAlphabet LA(CSE aSE){
        return h(analysisOfECFG(aSE));
    }

    /**
     *
     * @param aGrammar
     * @param aAlphabet
     * @return
     */
    public ArrayList<String> setToStringList(CGrammar aGrammar, IntAlphabet aAlphabet){ 
       ArrayList<String> vResult=new ArrayList();
        if (aAlphabet.size()==0){
              vResult=new ArrayList();
        }else{
       
        if(aAlphabet.has(0)){
            vResult.add(aGrammar.getGrammarContext().getSymbol(0).getName());
            for(int i=1;i<=aGrammar.getGrammarContext().symbolCount()-1;i++){
                if(aAlphabet.has(i))
                    vResult.add(aGrammar.getGrammarContext().getSymbol(i).getName());
            }
        }else{
            for(int i=1;i<=aGrammar.getGrammarContext().symbolCount()-1;i++){
                if(aAlphabet.has(i))
                    vResult.add(aGrammar.getGrammarContext().getSymbol(i).getName());
            }
          
        }
       
        }
        return vResult;
    }
    
    /**
     * Auxiliary routine to map IntAlphabet objects to strings
     * 
     * @param aGrammar
     * @param aAlphabet
     * @return
     */
        public String setToString(CGrammar aGrammar, IntAlphabet aAlphabet){ 
        StringBuffer vResult=new StringBuffer();
        if (aAlphabet.size()==0){
              vResult.append("{}");
        }else{
       
        vResult= vResult.append('{');
        if(aAlphabet.has(0)){
            if(aGrammar.getGrammarContext().getSymbol(0) instanceof CTerminalDec){
              vResult=vResult.append(aGrammar.getGrammarContext().getSymbol(0).getName());
            }
            for(int i=1;i<=aGrammar.getGrammarContext().symbolCount()-1;i++){
                if(aAlphabet.has(i)){
                   if(aGrammar.getGrammarContext().getSymbol(i) instanceof CTerminalDec){
                    vResult=vResult.append(",").append(" ").append(aGrammar.getGrammarContext().getSymbol(i).getName());
                   }
                
                }
                }
        }else{
            for(int i=1;i<=aGrammar.getGrammarContext().symbolCount()-1;i++){
                if(aAlphabet.has(i)){
                    if(aGrammar.getGrammarContext().getSymbol(i) instanceof CTerminalDec){
                    vResult=vResult.append(aGrammar.getGrammarContext().getSymbol(i).getName()).append(",").append(" ");
                }}
            }
            if(vResult.length()>=2)
               vResult.deleteCharAt(vResult.length()-2);
        }
        vResult=vResult.append('}');
        }
        return vResult.toString();
    }
    
    /**
     * Check the given RE for well-formedness and generate its analysis information. 
     * 
     * @param aRE the RE to analyze.
     */
    public  void analyzeRE(CRE aRE){
       int i;
       CREAnalysis vREAnalysis;
       CREAnalysis vREA;
       
       vREAnalysis=analysisOfRE(aRE);
	
       switch(aRE.sortCode()){
	   
	   case CGrammarCodes.scREEps: 
                vREAnalysis.fNullable = true;
                vREAnalysis.fFirst = new Alphabet();
	   break;
	   case CGrammarCodes.scREEmpty: 
                vREAnalysis.fNullable = false;
                vREAnalysis.fFirst = new Alphabet();
	   break;
	            
           case CGrammarCodes.scRELexeme:
                CRE_Lexeme vLex=(CRE_Lexeme)aRE;
                vREA=analysisOfRE(vLex.getDef());
                vREAnalysis.fNullable=vREA.fNullable;
                vREAnalysis.fFirst=vREA.fFirst;
                break;
	   case CGrammarCodes.scREChar:{
                CRE_Char aChar = (CRE_Char)aRE;
             	vREAnalysis.fNullable = false;
		vREAnalysis.fFirst = AlphabetOps.singleton(aChar.Char());
	        break;
           }

	   case CGrammarCodes.scRERange:{
		CRE_Range aRange=(CRE_Range)aRE;
		vREAnalysis.fNullable = false;
		vREAnalysis.fFirst = AlphabetOps.range(aRange.Low(), aRange.High());
                break;
           }
	   
	   case CGrammarCodes.scREString:{
		CRE_String aString = (CRE_String)aRE;
		vREAnalysis.fNullable = aString.Str().length()==0;
		   if(aString.Str().length()>0){
		       vREAnalysis.fFirst = AlphabetOps.singleton(aString.Str().charAt(0));
		   }
		   else vREAnalysis.fFirst = new Alphabet();
	        break;
           }
	   
	   case CGrammarCodes.scREMultiDot:{
	        CRE_MultiDot aMultiDot=(CRE_MultiDot)aRE;
		
                vREAnalysis.fNullable=true;
		vREAnalysis.fFirst=new Alphabet();
		for(i=0; i < aMultiDot.List().count(); i++){
                    analyzeRE(aMultiDot.List().getElt(i));
                    if(vREAnalysis.fNullable){
                        vREAnalysis.fFirst.bcUnion(analysisOfRE(aMultiDot.List().getElt(i)).fFirst);
                    }
                    vREAnalysis.fNullable = vREAnalysis.fNullable & analysisOfRE(aMultiDot.List().getElt(i)).fNullable;
		}
	        break;
           }
	    
	   case CGrammarCodes.scREMultiStick:{
		CRE_MultiStick aMultiStick = (CRE_MultiStick)aRE;
		vREAnalysis.fNullable=false;
		vREAnalysis.fFirst=new Alphabet();
		for(i = 0; i <= aMultiStick.List().count()-1; i++){
                    analyzeRE(aMultiStick.List().getElt(i));
		    vREAnalysis.fNullable = vREAnalysis.fNullable | analysisOfRE(aMultiStick.List().getElt(i)).fNullable;
		    vREAnalysis.fFirst.bcUnion(analysisOfRE(aMultiStick.List().getElt(i)).fFirst);
		}
	        break;
           }
	   
	   case CGrammarCodes.scREStar:{
		CRE_Star aStar = (CRE_Star)aRE;
		analyzeRE(aStar.Arg());
		vREAnalysis.fNullable = true;
		vREAnalysis.fFirst = analysisOfRE(aStar.Arg()).fFirst;
	        break;
           }
	   
	   case CGrammarCodes.scREPlus:{
		CRE_Plus aPlus = (CRE_Plus)aRE;
		analyzeRE(aPlus.Arg());
		vREAnalysis.fNullable = analysisOfRE(aPlus.Arg()).fNullable;
		vREAnalysis.fFirst = analysisOfRE(aPlus.Arg()).fFirst;
	        break;
           }
	   
	   case CGrammarCodes.scREOPtion:{
		CRE_Option aOption = (CRE_Option)aRE;
		analyzeRE(aOption.Arg());
		vREAnalysis.fNullable = true;
		vREAnalysis.fFirst = analysisOfRE(aOption.Arg()).fFirst;
	        break;
           }
	   
	   default :  break;
	   }
   }

}
