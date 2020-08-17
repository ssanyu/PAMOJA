package Parsers;


import General.CGeneral;
import GrammarNotions.SyntaxExpr.*;
import Nodes.CNode;
import SymbolStream.CPosition;
import java.util.ArrayList;



/* CLimBacktrackParser is a parser with the following characteristics:
 * ----------------------------------------------------------------------------
 * grammar kind         = ECFG
 * grammar conditions   = no left recursion, no Null(f) for f* 
 * grammar processing   = interpreted
 * collaborators        = grammar , symbol stream, tree builder
 * strategy             = limited backtrack, success boolean
 * strategy options     = lookahead,treebuilding
 * ----------------------------------------------------------------------------
 * @author ssanyu
 */

/**
 *
 * @author HP
 */

public class CLimBackTrackerParser extends CInterpretingRecDescent{
    private CPosition fMaxSymPos;
   
    /**
     *
     */
    public CLimBackTrackerParser(){
        super();
        fMaxSymPos=new CPosition(-1,-1);
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
        int i;
        CPosition vMark;
        CSE vSE;
                
        vResult=new CParserResult();
        vMark=getSymPos();
        vResult.setNode(null);
        vResult.setSuccess(false);
        i=0;
        while(!vResult.isSuccess() && i<aSE.termCount()){
            vSE=(CSE)aSE.getTerm(i);
            if(!UseLookAhead || (vAnalyzer.LA(vSE).has(getSym()))){
                vResult=parseSE(vSE,aRoot,aPath+CGeneral.indexStr(i));
                if(!vResult.isSuccess()){
                    reCall(vMark);
                }
            }
            i=i+1;
        }
        if(vResult.isSuccess()==true){
           vResult.setNode(bdMultiStick(aRoot,aPath,i,vResult.getNode()));
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
        CPosition vMark=null;
        
        vResult=new CParserResult();
        vResult.setSuccess(true);
        if(TreeBuilding){
            vNodeArray=new ArrayList<CNode>();
        }
        while(vResult.isSuccess()){
            vMark=getSymPos();
            vResult=parseSE(aSE.getArg(),aRoot,aPath+CGeneral.indexStr(0));
            if(vResult.isSuccess()){
                if(TreeBuilding==true){
                    vNodeArray.add(vResult.getNode());
                }
            }else{
                   reCall(vMark);
            }    
            
        } 
        vResult.setNode(bdStar(aRoot,aPath,vNodeArray));
        vResult.setSuccess(true);
          
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
        CPosition vMark=null;
        
        vResult=new CParserResult();
        vResult.setSuccess(false);
        if(TreeBuilding){
            vNodeArray=new ArrayList<CNode>();
        }
        do{
            vMark=getSymPos();
            vResult=parseSE(aSE.getArg(),aRoot,aPath+CGeneral.indexStr(0));
            if(vResult.isSuccess()){
                if(TreeBuilding==true){
                    vNodeArray.add(vResult.getNode());
                }
            }else{
                   reCall(vMark);
            }   
        }while(!vResult.isSuccess());
        if(vResult.isSuccess())
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
        return null;
    }
    @Override
    public CParserResult parse() {
              
        CParserResult vResult=new CParserResult();
        CSE vStartExpr;
          
        vStartExpr=fGrammar.getStartExpr();
        assert(fGrammar!=null): "CLimBackTrackerParser.parse() failed:Grammar property not assigned";
        assert((vAnalyzer.analysisOfECFG(vStartExpr)!=null)): "CLimBacktrackerParser.parse() failed:Grammar has no"+ "ana"+" annotation";
        assert(fSymbolStream!=null): "CLimBacktrackerParser.parse() failed:SymbolStream property not assigned";
        assert(!(TreeBuilding && (TreeBuilder==null))): "CLimBackTrackerParser.parse() failed:TreeBuilder needed for TreeBuilding option"; 
        assert(vStartExpr instanceof CSE):"CLimBackTrackerParser.parse() failed:Start Expression should be a syntax expression"; 
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
    public void reSet(){
       super.reSet();
       fMaxSymPos=new CPosition(-1,-1);
    }
    
    @Override
    public void nextSym(){
       CPosition vSymPos;
       
       super.nextSym();
       vSymPos=fSymbolStream.getPosition();
       //maxSymPos < SymPos
       if((fMaxSymPos.Line<vSymPos.Line) || ((fMaxSymPos.Line==vSymPos.Line)&&(fMaxSymPos.Col<vSymPos.Col))){
          fMaxSymPos.Line=vSymPos.Line;
          fMaxSymPos.Col=vSymPos.Col;
       }
      
    }

    /**
     *
     * @return
     */
    public CPosition getMaxSymPos(){
        return fMaxSymPos; 
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
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CParserResult parseNonTerminal(String aName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
 
}