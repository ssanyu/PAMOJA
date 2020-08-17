package Parsers;


import General.CGeneral;
import GrammarNotions.SyntaxExpr.*;
import Nodes.CNode;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CDeterministicParser extends CInterpretingRecDescent{
        
    /**
     *
     */
    public CDeterministicParser(){
        super();
    } 
   
    /**
     *
     * @param aSE
     * @param aRoot
     * @param aPath
     * @return
     */
    @Override
    protected CParserResult parseMultiStick(CSE_MultiStick aSE,String aRoot,String aPath){
        CParserResult vResult;
        
        int i,k;
        CSE vSE;
                
        vResult=new CParserResult();
        k=-1;
        for(i=0; i<aSE.termCount();i++){
            if(vAnalyzer.LA((CSE)aSE.getTerm(i)).has(getSym())){
                k=i;
                break;
            }   
        }
        if(k==-1){
            vResult.setSuccess(false);
            vResult.setNode(null);
        }else{
            vSE=(CSE)aSE.getTerm(i);
            vResult=parseSE(vSE,aRoot,aPath+CGeneral.indexStr(i));
        }
        if(vResult.isSuccess()==true){
           vResult.setNode(bdMultiStick(aRoot,aPath,k,vResult.getNode()));
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
    @Override
    protected CParserResult parseStar(CSE_Star aSE,String aRoot,String aPath){
        CParserResult vResult;
        ArrayList<CNode> vNodeArray=null;
                
        vResult=new CParserResult();
        vResult.setSuccess(true);
        if(TreeBuilding==true){
            vNodeArray=new ArrayList<>();
        }
        while(vResult.isSuccess()==true && vAnalyzer.analysisOfECFG(aSE).fFirst.has(getSym()) ){
            vResult=parseSE(aSE.getArg(),aRoot,aPath+CGeneral.indexStr(0));
            if(vResult.isSuccess()==true && TreeBuilding==true){
                    vNodeArray.add(vResult.getNode());
            }
        } 
        if(vResult.isSuccess()==true)
            vResult.setNode(bdStar(aRoot,aPath,vNodeArray));
        else
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
    @Override
    protected CParserResult parsePlus(CSE_Plus aSE,String aRoot,String aPath){
        CParserResult vResult;
        ArrayList<CNode> vNodeArray=null;
             
        vResult=new CParserResult();
        if(TreeBuilding){
            vNodeArray=new ArrayList<>();
        }
        do{
            vResult=parseSE(aSE.getArg(),aRoot,aPath+CGeneral.indexStr(0));
            if(vResult.isSuccess()==true && TreeBuilding==true){
                    vNodeArray.add(vResult.getNode());
            }
              
        }while(vResult.isSuccess()&& vAnalyzer.analysisOfECFG(aSE).fFirst.has(getSym()));
        if(vResult.isSuccess()==true)
            vResult.setNode(bdPlus(aRoot,aPath,vNodeArray));
        else
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
    @Override
    protected CParserResult parseOption(CSE_Option aSE,String aRoot,String aPath){
         CParserResult vResult;
         
         boolean present;
         
         vResult=new CParserResult();
         if(vAnalyzer.analysisOfECFG(aSE).fFirst.has(getSym())){
             vResult=parseSE(aSE.getArg(),aRoot,aPath+CGeneral.indexStr(0));
             present=true;
         }else{
             vResult.setSuccess(true);
             vResult.setNode(null);
             present=false;
         }
         if(vResult.isSuccess()==true){
             vResult.setNode(bdOption(aRoot,aPath,present,vResult.getNode()));
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
    @Override
    protected CParserResult parseAlt(CSE_Alt aSE, String aRoot, String aPath) {
        CParserResult vResult,vIResult;
        ArrayList<CNode> vNodeArray=null;
        CSymDec vLeft,vRight;       
        
        //Check preconditions
        assert aSE.getLeft() instanceof CSE_Sym: String.format("CDeterministicParser.parseAlt() failed: left argument should be a symbol, found: %s .",aSE.getLeft().sortLabel());
        vLeft=((CSE_Sym)aSE.getLeft()).getDec();
        
        assert vLeft instanceof CNonTerminalDef: String.format("CDeterministicParser.parseAlt() failed: left symbol should be a Nonterminal, found: %s .",vLeft.getName());
        
        assert aSE.getRight() instanceof CSE_Sym: String.format("CDeterministicParser.parseAlt() failed: right argument should be a symbol, found: %s .",aSE.getRight().sortLabel());
        vRight=((CSE_Sym)aSE.getRight()).getDec();
        
        assert vRight instanceof CTerminalDec: String.format("CDeterministicParser.parseAlt() failed: right symbol should be a Terminal, found: %s .",vRight.getName());
        
        if(TreeBuilding){
            vNodeArray=new ArrayList<>();
        }
        vResult=parseNonTerminal((CNonTerminalDef)vLeft,aRoot,aPath+CGeneral.indexStr(0));

        if(vResult.isSuccess() && TreeBuilding){
            vNodeArray.add(vResult.getNode());
        }
        
        while(vResult.isSuccess()&& vAnalyzer.analysisOfECFG(aSE.getRight()).fFirst.has(getSym()) ){
            vIResult=parseTerminal((CTerminalDec)vRight,aRoot,aPath);
            if(vIResult.isSuccess()){
                vResult=parseNonTerminal((CNonTerminalDef)vLeft,aRoot,aPath+CGeneral.indexStr(0));    
            }else{
                vResult.setSuccess(false);
            }
            if(vResult.isSuccess() && TreeBuilding){
                
                if(((CTerminalDec)vRight).hasData()){
                    vNodeArray.add(vIResult.getNode());
                }
                vNodeArray.add(vResult.getNode());
            }
        }
        if(vResult.isSuccess() && TreeBuilding){
            vResult.setNode(bdAlt(aRoot,aPath,vNodeArray));
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
    @Override
    protected CParserResult parseAlt2(CSE_Alt2 aSE, String aRoot, String aPath) {
        CParserResult vResult,vIResult;
        ArrayList<CNode> vNodeArray=null;
        CSymDec vLeft,vRight;       
        
        //Check preconditions
        assert aSE.getLeft() instanceof CSE_Sym: String.format("CDeterministicParser.parseAlt2() failed: left argument should be a symbol, found: %s .",aSE.getLeft().sortLabel());
        vLeft=((CSE_Sym)aSE.getLeft()).getDec();
        
        assert vLeft instanceof CNonTerminalDef: String.format("CDeterministicParser.parseAlt2() failed: left symbol should be a Nonterminal, found: %s .",vLeft.getName());
        
        assert aSE.getRight() instanceof CSE_Sym: String.format("CDeterministicParser.parseAlt2() failed: right argument should be a symbol, found: %s .",aSE.getRight().sortLabel());
        vRight=((CSE_Sym)aSE.getRight()).getDec();
        
        assert vRight instanceof CTerminalDec: String.format("CDeterministicParser.parseAlt2() failed: right symbol should be a Terminal, found: %s .",vRight.getName());
        if(TreeBuilding){
            vNodeArray=new ArrayList<>();
        }
          
        vResult=parseNonTerminal((CNonTerminalDef)vLeft,aRoot,aPath+CGeneral.indexStr(0));

        if(vResult.isSuccess() && TreeBuilding){
            
            vNodeArray.add(vResult.getNode());
        }
        while(vResult.isSuccess()&& vAnalyzer.analysisOfECFG(aSE.getRight()).fFirst.has(getSym()) ){
            vIResult=parseTerminal((CTerminalDec)vRight,aRoot,aPath);
            if(vIResult.isSuccess()==true){
                vResult=parseNonTerminal((CNonTerminalDef)vLeft,aRoot,aPath+CGeneral.indexStr(0));    
            }else{
                vResult.setSuccess(false);
            }
            if(vResult.isSuccess() && TreeBuilding){
                if(((CTerminalDec)vRight).hasData()){
                    vNodeArray.add(vIResult.getNode());
                }
                vNodeArray.add(vResult.getNode());
            }
        }
        
        if(vResult.isSuccess() && TreeBuilding){
           if(vNodeArray.size()==1){
              vResult.setNode(vNodeArray.get(0));
           }else{
                vResult.setNode(bdAlt(aRoot,aPath,vNodeArray));
           }
        }else{
            vResult.setNode(null);
        }
        return vResult;
    }
    
    @Override
    public CParserResult parse() {
       // CSymDec vDec;
        CParserResult vResult;
        CSE vStartExpr;
       // CGrammarAnalyzer vAnalyzer=new CGrammarAnalyzer();
        vStartExpr=fGrammar.getStartExpr();
        //check for ELL(1) condition... (Not implemented yet)
       //Not correct: assert((vAnalyzer.analysisOfECFG(vStartExpr).fELL1Det!=false)): "CDeterministicParser.parse() failed:Grammar is not deterministic";
       
        assert(fGrammar!=null): "CDeterministicParser.parse() failed:Grammar property not assigned";
        
        assert((vAnalyzer.analysisOfECFG(vStartExpr)!=null)): "CDeterministicParser.parse() failed:Grammar has no"+ "ana"+" annotation";
        assert(fSymbolStream!=null): "CDeterministicParser.parse() failed:SymbolStream property not assigned";
        assert(fSymbolStream.symbolCount()!=0): "CDeterministicParser.parse() failed:SymbolStream is empty";
        assert(!(TreeBuilding && (TreeBuilder==null))): "CDeterministicParser.parse() failed:TreeBuilder needed for TreeBuilding option"; 
        assert(vStartExpr instanceof CSE):"CDeterministicParser.parse() failed:Start Expression should be a syntax expression"; 
        CSE_MultiDot vDot=(CSE_MultiDot)vStartExpr;
        assert(vDot instanceof CSE_MultiDot): "Grammar should be augumented"; 
        // Reset Parser
        reSet();
        // call parsing procedure for start expression
        vResult=parseSE(vStartExpr,"","_");
        //check whether input is exhausted
        if(vResult.isSuccess()==true && !(fSymbolStream.finished())){// getSym()==CParserCodes.symEndMarker)
           vResult.setSuccess(false);
           vResult.setNode(null);
        }
        return vResult;
    }

   @Override
    public CParserResult parseNonTerminal(String aNonTerm) {
        int i;
        CNonTerminalDef vNonTermDef;
        CParserResult vResult;
        
        assert(fGrammar!=null): "CDeterministicParser.parseNonTerminal() failed:Grammar property not assigned";
        
        //assert((vAnalyzer.analysisOfECFG(vStartExpr)!=null)): "CDeterministicParser.parseNonTerminal() failed:Grammar has no"+ "ana"+" annotation";
        assert(fSymbolStream!=null): "CDeterministicParser.parseNonTerminal() failed:SymbolStream property not assigned";
        assert(fSymbolStream.symbolCount()!=0): "CDeterministicParser.parseNonTerminal() failed:SymbolStream is empty";
        assert(!(TreeBuilding && (TreeBuilder==null))): "CDeterministicParser.parseNonTerminal() failed:TreeBuilder needed for TreeBuilding option"; 
        assert(aNonTerm!=null): "CDeterministicParser.parseNonTerminal() failed:SymbolStream property not assigned";
        
        i=fGrammar.getGrammarContext().indexOfNonterminal(aNonTerm);
        assert(i!=-1): "CDeterministicParser.parseNonTerminal()failed:";
        
        vNonTermDef=fGrammar.getGrammarContext().getNonTerminalDefs().getElt(i);
        
        reSet();
        // call parsing procedure for NonTerminal expression
        vResult=parseNonTerminal(vNonTermDef,"","_");
        
        //check whether input is exhausted
        if(vResult.isSuccess()==true && !(fSymbolStream.finished())){// getSym()==CParserCodes.symEndMarker)
           vResult.setSuccess(false);
           vResult.setNode(null);
        }
        return vResult;
    }

}
