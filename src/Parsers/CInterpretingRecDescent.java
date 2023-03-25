package Parsers;


import Analyzers.CGrammarAnalyzer;
import General.CGeneral;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.SyntaxExpr.*;
import Nodes.CNode;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public abstract class CInterpretingRecDescent extends CParserExt{
    /**
     * Reference to an instance of {@code CGrammarAnalyzer}
     */
    CGrammarAnalyzer vAnalyzer;
    
    /**
     *
     */
    public CInterpretingRecDescent(){
        super();
        vAnalyzer=new CGrammarAnalyzer();
    }
    
   /**
    * Compares the current symbol of the sentence with the specified terminal 
    * symbol aSE. 
    * @param aSE The terminal symbol to be matched.
    * @return <code>true</code> if aSE symbol of the grammar matches with the 
    * current symbol in the sentence.
    *                  <code>false</code> otherwise.
    */
    public boolean matchTerm(CTerminalDec aSE){
        int vSym=getSym();
        if(aSE.getNumber()==vSym){
            return true;
        }else return false;
    }
    
    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected CParserResult parseSE(CSE aSE, String aRoot,String aPath){
       
        CParserResult vResult=new CParserResult();

         switch(aSE.sortCode()){
            case CGrammarCodes.scSEEmpty:
                vResult=parseEmpty((CSE_Empty)aSE);
                break;
            case CGrammarCodes.scSEEps:
                vResult=parseEps((CSE_Eps)aSE, aRoot,aPath);
                break;
            case CGrammarCodes.scSESym:
                 vResult=parseSym((CSE_Sym)aSE, aRoot,aPath);
                 break;
            case CGrammarCodes.scSEMultiDot:
                 vResult=parseMultiDot((CSE_MultiDot)aSE, aRoot,aPath);
                 break;
             case CGrammarCodes.scSEMultiStick:
                 vResult=parseMultiStick((CSE_MultiStick)aSE, aRoot,aPath);
                 break;
            case CGrammarCodes.scSEStar:
                 vResult=parseStar((CSE_Star)aSE, aRoot,aPath);
                break;
            case CGrammarCodes.scSEPlus:
                 vResult=parsePlus((CSE_Plus)aSE,aRoot,aPath);
                break;
            case CGrammarCodes.scSEOption:
                 vResult=parseOption((CSE_Option)aSE,aRoot,aPath);
                break; 
            case CGrammarCodes.scSEAlt:
                vResult=parseAlt((CSE_Alt)aSE,aRoot,aPath);
                break;
            case CGrammarCodes.scSEAlt2:
                vResult=parseAlt2((CSE_Alt2)aSE,aRoot,aPath);
                break;
            default:
                 assert false: String.format("Illegal SortCode=%d in CInterpretingParser", aSE.sortCode());
                 break;
        }
         return vResult;

    }

    /**
     *
     * @param aSE
     * @return
     */
    protected CParserResult parseEmpty(CSE_Empty aSE){
         CParserResult vResult=new CParserResult();
         vResult.setSuccess(false);
         vResult.setNode(null);
         return vResult;
    }

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected CParserResult parseEps(CSE_Eps aSE,String aRoot,String aPath){
          CParserResult vResult=new CParserResult();
          vResult.setSuccess(true);
          vResult.setNode(bdEps(aRoot,aPath));  
          return vResult;
    }

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected CParserResult parseTerminal(CTerminalDec aSE,String aRoot,String aPath){
        CParserResult vResult;   
                        
        vResult=new CParserResult();
        
        if(matchTerm(aSE)==true){ 
            vResult.setSuccess(true); 
            if(aSE.hasData()){
                vResult.setNode(bdTermData(aRoot,aPath,aSE.getName(),getSymValue())); 
            }else{
                vResult.setNode(bdTerm(aRoot,aPath,aSE.getName()));
            }
            nextSym();
         }else{
            vResult.setSuccess(false); 
            vResult.setNode(null);
            fParseErrorList.add(new CParseError("Failed to match a terminal",getSym(),getSymName(),getSymValue(),getSymPos()));
         }
         return vResult;
    }

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected CParserResult parseNonTerminal(CNonTerminalDef aSE,String aRoot,String aPath){
         CParserResult vResult;
         vResult=parseSE(aSE.getBody(),aSE.getName(),"_");
         if(vResult.isSuccess()){
         vResult.setNode(bdNonTerm(aRoot,aPath,aSE.getName(),vResult.getNode()));  
         }else{
             vResult.setNode(null);
         }
         return vResult;
         
    }

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected CParserResult parseSym(CSE_Sym aSE,String aRoot, String aPath){
       CParserResult vResult=new CParserResult();
        CSymDec vSym;

        vSym=aSE.getDec();
        switch(vSym.sortCode()){
            case CGrammarCodes.scTerminalDef:
                vResult=parseTerminal((CTerminalDec)vSym,aRoot,aPath);
                break;
            case CGrammarCodes.scNonTerminalDef:
                vResult=parseNonTerminal((CNonTerminalDef)vSym,aRoot,aPath);
                break;
            default:  assert false: String.format("Illegal SortCode=%d in CInterpretingRecDescent.parseSym()", aSE.sortCode());
                 break;
        }
        return vResult;
    }

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected CParserResult parseMultiDot(CSE_MultiDot aSE,String aRoot,String aPath){
        ArrayList<CNode> vNodeArray;
        CParserResult vResult; 
        int i=0;
        
        vResult=new CParserResult();       
        vNodeArray=new ArrayList<>();
        vResult.setSuccess(true);
        
        while(i<aSE.termCount() && vResult.isSuccess()){
            vResult=parseSE((CSE)aSE.getTerm(i),aRoot,aPath+CGeneral.indexStr(i));
            vNodeArray.add(vResult.getNode());
            i=i+1;
        }
        if(vResult.isSuccess()==true){
           vResult.setNode(bdMultiDot(aRoot,aPath,vNodeArray));
        }else{ 
           vResult.setNode(null);
        }
        return vResult;
        
    }
       
    @Override
    public abstract CParserResult parse(); 

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected abstract CParserResult parseStar(CSE_Star aSE,String aRoot,String aPath);

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected abstract CParserResult parseMultiStick(CSE_MultiStick aSE,String aRoot,String aPath);

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected abstract CParserResult parsePlus(CSE_Plus aSE,String aRoot,String aPath);

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected abstract CParserResult parseOption(CSE_Option aSE,String aRoot,String aPath);

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected abstract CParserResult parseAlt(CSE_Alt aSE,String aRoot,String aPath);

    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    protected abstract CParserResult parseAlt2(CSE_Alt2 aSE,String aRoot,String aPath);
}

    
    
    
    
    
    
    
    
