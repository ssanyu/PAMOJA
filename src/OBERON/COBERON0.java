/*====================================================
This file has been generated automatically

Date       : Fri Sep 18 17:34:03 EAT 2020
=====================================================*/

 
package OBERON; 

import Sets.IntAlphabet;
import Parsers.CGeneratedRecDescent;
import Parsers.CParseError;
import Parsers.CParserResult;
import Parsers.InvalidTerminalException;
import Nodes.CNode;
import java.util.ArrayList;
 
/*=================================================================
   A recursive descent parser with the following properties:
           Grammar kind = AOL
           Grammar conditions = ELL(1), No null(b) for B%b
           Grammar processing = Parser Generator
           Strategy = Deterministic
           Architecture = Recursive descent procedures with external scanner and tree builder
           Scanner operations = getSym(),nextSym(),term()
           Tree builder operations = bdEps(),bdTuple(),bdStar(),bdPlus(),bdOption(),bdAlt()
           Error handling = Throws an exception on failure, retuns a null tree
           Error recovery = None
 ==================================================================*/

/**
 *
 * @author HP
 */
public class COBERON0 extends CGeneratedRecDescent  {
    private IntAlphabet First_Condition_0 = new IntAlphabet ( new int[] {OBERON0_Codes.syopen,OBERON0_Codes.synumber,OBERON0_Codes.syunOp,OBERON0_Codes.syboolConst,OBERON0_Codes.syident});
    private IntAlphabet First_CasePart_0 = new IntAlphabet ( new int[] {OBERON0_Codes.syopen,OBERON0_Codes.synumber,OBERON0_Codes.syunOp,OBERON0_Codes.syboolConst,OBERON0_Codes.syident});
    private IntAlphabet First_Declaration_List1_0 = new IntAlphabet ( new int[] {OBERON0_Codes.syconst,OBERON0_Codes.syprocedure,OBERON0_Codes.syident});
    private IntAlphabet First_Declaration_List0_0 = new IntAlphabet ( new int[] {OBERON0_Codes.syconst,OBERON0_Codes.syprocedure,OBERON0_Codes.syident});
    private IntAlphabet First_Statement_List1_0 = new IntAlphabet ( new int[] {OBERON0_Codes.sycall,OBERON0_Codes.syif,OBERON0_Codes.sywhile,OBERON0_Codes.syread,OBERON0_Codes.sywrite,OBERON0_Codes.syrepeat,OBERON0_Codes.sycase,OBERON0_Codes.syfor,OBERON0_Codes.syident});
    private IntAlphabet First_Statement_List0_0 = new IntAlphabet ( new int[] {OBERON0_Codes.sycall,OBERON0_Codes.syif,OBERON0_Codes.sywhile,OBERON0_Codes.syread,OBERON0_Codes.sywrite,OBERON0_Codes.syrepeat,OBERON0_Codes.sycase,OBERON0_Codes.syfor,OBERON0_Codes.syident});
    private IntAlphabet First_Selector_List0_0 = new IntAlphabet ( new int[] {OBERON0_Codes.sysquareOpen,OBERON0_Codes.sydot});

    /**
     *
     */
    public COBERON0(){
        super();
    }


    public CParserResult parse(){
        CParserResult vResult=new CParserResult();
        reSet();
        try{
             CNode vNode=_Program();
             if(getSym()==OBERON0_Codes.syendmarker){
                  vResult.setNode(vNode);
                  vResult.setSuccess(true);
             }else{
                 vResult.setNode(null);
                 vResult.setSuccess(false);
                 fParseErrorList.add(new CParseError("Expected end of input. Failed to match a symbol",OBERON0_Codes.syendmarker,"endmarker","",getSymPos()) );
             }
        }catch(InvalidTerminalException e){
               fParseErrorList.add(e.fErrorObject);
         }
        return vResult;
    }


    public CParserResult parseNonTerminal(String aName){
        CParserResult vResult=new CParserResult();
        CNode vNode=null;
        reSet();
        try{

             if(getSym()==OBERON0_Codes.syendmarker){
                  vResult.setNode(vNode);
                  vResult.setSuccess(true);
             }else{
                 vResult.setNode(null);
                 vResult.setSuccess(false);
                 fParseErrorList.add(new CParseError("Expected end of input. Failed to match a symbol",OBERON0_Codes.syendmarker,"endmarker","",getSymPos()) );
             }
        }catch(InvalidTerminalException e){
               fParseErrorList.add(e.fErrorObject);
         }
        return vResult;
    }

    //Program=( Module )

    /**
     *
     * @return
     */
        protected CNode _Program(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.symodule == getSym()){
             vNode = _Module();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" Program cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("Program","",k,vNode);
    }

    //Var=( VariableId )

    /**
     *
     * @return
     */
        protected CNode _Var(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.syident == getSym()){
             vNode = _VariableId();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" Var cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("Var","",k,vNode);
    }

    //FParameters=( FormalParameters )

    /**
     *
     * @return
     */
        protected CNode _FParameters(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.syopen == getSym()){
             vNode = _FormalParameters();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" FParameters cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("FParameters","",k,vNode);
    }

    //AParameters=( ActualParameters )

    /**
     *
     * @return
     */
        protected CNode _AParameters(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.syopen == getSym()){
             vNode = _ActualParameters();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" AParameters cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("AParameters","",k,vNode);
    }

    //FParameter=( FormalParameter )

    /**
     *
     * @return
     */
        protected CNode _FParameter(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.syident == getSym()){
             vNode = _FormalParameter();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" FParameter cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("FParameter","",k,vNode);
    }

    //ReturnExp=( Return )

    /**
     *
     * @return
     */
        protected CNode _ReturnExp(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.syreturn == getSym()){
             vNode = _Return();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" ReturnExp cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("ReturnExp","",k,vNode);
    }

    //FSequence=( FieldSequence )

    /**
     *
     * @return
     */
        protected CNode _FSequence(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.syident == getSym()){
             vNode = _FieldSequence();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" FSequence cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("FSequence","",k,vNode);
    }

    //Condition=( RelationalCondition )

    /**
     *
     * @return
     */
        protected CNode _Condition(){
        CNode vNode=null;
        int k;
        if(First_Condition_0.has(getSym())){
             vNode = _RelationalCondition();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" Condition cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("Condition","",k,vNode);
    }

    //ElseIf=( ElseIfPart )

    /**
     *
     * @return
     */
        protected CNode _ElseIf(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.syelsif == getSym()){
             vNode = _ElseIfPart();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" ElseIf cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("ElseIf","",k,vNode);
    }

    //CasePart=( CaseStat )

    /**
     *
     * @return
     */
        protected CNode _CasePart(){
        CNode vNode=null;
        int k;
        if(First_CasePart_0.has(getSym())){
             vNode = _CaseStat();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" CasePart cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("CasePart","",k,vNode);
    }

    //ReturnExpType=( ReturnType )

    /**
     *
     * @return
     */
        protected CNode _ReturnExpType(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.sycolon == getSym()){
             vNode = _ReturnType();
             k=0;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" ReturnExpType cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("ReturnExpType","",k,vNode);
    }

    //Type=( ArrayType | BasicType | RecordType )

    /**
     *
     * @return
     */
        protected CNode _Type(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.syarray == getSym()){
             vNode = _ArrayType();
             k=0;
        }else if(OBERON0_Codes.sybtype == getSym()){
             vNode = _BasicType();
             k=1;
        }else if(OBERON0_Codes.syrecord == getSym()){
             vNode = _RecordType();
             k=2;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" Type cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("Type","",k,vNode);
    }

    //Selector=( FieldSelector | ArraySelector )

    /**
     *
     * @return
     */
        protected CNode _Selector(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.sydot == getSym()){
             vNode = _FieldSelector();
             k=0;
        }else if(OBERON0_Codes.sysquareOpen == getSym()){
             vNode = _ArraySelector();
             k=1;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" Selector cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("Selector","",k,vNode);
    }

    //Declaration=( ConstantDeclaration | VariableDeclaration | ProcedureDeclaration )

    /**
     *
     * @return
     */
        protected CNode _Declaration(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.syconst == getSym()){
             vNode = _ConstantDeclaration();
             k=0;
        }else if(OBERON0_Codes.syident == getSym()){
             vNode = _VariableDeclaration();
             k=1;
        }else if(OBERON0_Codes.syprocedure == getSym()){
             vNode = _ProcedureDeclaration();
             k=2;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" Declaration cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("Declaration","",k,vNode);
    }

    //Statement=( Assignment | If | While | Repeat | Read | Write | For | Case | ProcedureCall )

    /**
     *
     * @return
     */
        protected CNode _Statement(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.syident == getSym()){
             vNode = _Assignment();
             k=0;
        }else if(OBERON0_Codes.syif == getSym()){
             vNode = _If();
             k=1;
        }else if(OBERON0_Codes.sywhile == getSym()){
             vNode = _While();
             k=2;
        }else if(OBERON0_Codes.syrepeat == getSym()){
             vNode = _Repeat();
             k=3;
        }else if(OBERON0_Codes.syread == getSym()){
             vNode = _Read();
             k=4;
        }else if(OBERON0_Codes.sywrite == getSym()){
             vNode = _Write();
             k=5;
        }else if(OBERON0_Codes.syfor == getSym()){
             vNode = _For();
             k=6;
        }else if(OBERON0_Codes.sycase == getSym()){
             vNode = _Case();
             k=7;
        }else if(OBERON0_Codes.sycall == getSym()){
             vNode = _ProcedureCall();
             k=8;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" Statement cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("Statement","",k,vNode);
    }

    //Expression=( Constant | Variable | Negation | BracketExpression | BoolExpression )

    /**
     *
     * @return
     */
        protected CNode _Expression(){
        CNode vNode=null;
        int k;
        if(OBERON0_Codes.synumber == getSym()){
             vNode = _Constant();
             k=0;
        }else if(OBERON0_Codes.syident == getSym()){
             vNode = _Variable();
             k=1;
        }else if(OBERON0_Codes.syunOp == getSym()){
             vNode = _Negation();
             k=2;
        }else if(OBERON0_Codes.syopen == getSym()){
             vNode = _BracketExpression();
             k=3;
        }else if(OBERON0_Codes.syboolConst == getSym()){
             vNode = _BoolExpression();
             k=4;
        }else{ 
            throw new InvalidTerminalException(new CParseError(" Expression cannot begin with symbol",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
        return bdMultiStick("Expression","",k,vNode);
    }

    //Module=( module . Var . semicolon . Declaration_List1 . begin . Statement_List0 . end . Var )

    /**
     *
     * @return
     */
        protected CNode _Module(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.symodule);
        vNode = _Var();
        vNodes.add(vNode);
        term(OBERON0_Codes.sysemicolon);
        vNode = _Declaration_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.sybegin);
        vNode = _Statement_List0();
        vNodes.add(vNode);
        term(OBERON0_Codes.syend);
        vNode = _Var();
        vNodes.add(vNode);
        return bdTuple("Module","",vNodes,vDataList);
    }

    //VariableId=( ident@ )

    /**
     *
     * @return
     */
        protected CNode _VariableId(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vData=termData(OBERON0_Codes.syident);
        vDataList.add(vData);
        return bdTuple("VariableId","",vNodes,vDataList);
    }

    //ConstantDeclaration=( const . Var . equals . Expression )

    /**
     *
     * @return
     */
        protected CNode _ConstantDeclaration(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syconst);
        vNode = _Var();
        vNodes.add(vNode);
        term(OBERON0_Codes.syequals);
        vNode = _Expression();
        vNodes.add(vNode);
        return bdTuple("ConstantDeclaration","",vNodes,vDataList);
    }

    //VariableDeclaration=( Var_List1 . colon . Type )

    /**
     *
     * @return
     */
        protected CNode _VariableDeclaration(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vNode = _Var_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.sycolon);
        vNode = _Type();
        vNodes.add(vNode);
        return bdTuple("VariableDeclaration","",vNodes,vDataList);
    }

    //ProcedureDeclaration=( procedure . Var . FParameters_Option . ReturnExpType_Option . semicolon . Declaration_List0 . begin . Statement_List0 . ReturnExp_Option . end . Var )

    /**
     *
     * @return
     */
        protected CNode _ProcedureDeclaration(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syprocedure);
        vNode = _Var();
        vNodes.add(vNode);
        vNode = _FParameters_Option();
        vNodes.add(vNode);
        vNode = _ReturnExpType_Option();
        vNodes.add(vNode);
        term(OBERON0_Codes.sysemicolon);
        vNode = _Declaration_List0();
        vNodes.add(vNode);
        term(OBERON0_Codes.sybegin);
        vNode = _Statement_List0();
        vNodes.add(vNode);
        vNode = _ReturnExp_Option();
        vNodes.add(vNode);
        term(OBERON0_Codes.syend);
        vNode = _Var();
        vNodes.add(vNode);
        return bdTuple("ProcedureDeclaration","",vNodes,vDataList);
    }

    //Constant=( number@ )

    /**
     *
     * @return
     */
        protected CNode _Constant(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vData=termData(OBERON0_Codes.synumber);
        vDataList.add(vData);
        return bdTuple("Constant","",vNodes,vDataList);
    }

    //BracketExpression=( open . Expression . allOps@ . Expression . close )

    /**
     *
     * @return
     */
        protected CNode _BracketExpression(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syopen);
        vNode = _Expression();
        vNodes.add(vNode);
        vData=termData(OBERON0_Codes.syallOps);
        vDataList.add(vData);
        vNode = _Expression();
        vNodes.add(vNode);
        term(OBERON0_Codes.syclose);
        return bdTuple("BracketExpression","",vNodes,vDataList);
    }

    //Negation=( unOp@ . Expression )

    /**
     *
     * @return
     */
        protected CNode _Negation(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vData=termData(OBERON0_Codes.syunOp);
        vDataList.add(vData);
        vNode = _Expression();
        vNodes.add(vNode);
        return bdTuple("Negation","",vNodes,vDataList);
    }

    //Variable=( Var . Selector_List0 . AParameters_Option )

    /**
     *
     * @return
     */
        protected CNode _Variable(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vNode = _Var();
        vNodes.add(vNode);
        vNode = _Selector_List0();
        vNodes.add(vNode);
        vNode = _AParameters_Option();
        vNodes.add(vNode);
        return bdTuple("Variable","",vNodes,vDataList);
    }

    //BoolExpression=boolConst@

    /**
     *
     * @return
     */
        protected CNode _BoolExpression(){
        String vData=termData(OBERON0_Codes.syboolConst);
        return bdTermData("BoolExpression","","boolConst",vData);
    }

    //Assignment=( Var . Selector_List0 . becomes . Expression . semicolon )

    /**
     *
     * @return
     */
        protected CNode _Assignment(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vNode = _Var();
        vNodes.add(vNode);
        vNode = _Selector_List0();
        vNodes.add(vNode);
        term(OBERON0_Codes.sybecomes);
        vNode = _Expression();
        vNodes.add(vNode);
        term(OBERON0_Codes.sysemicolon);
        return bdTuple("Assignment","",vNodes,vDataList);
    }

    //ProcedureCall=( call . Var . Selector_List0 . AParameters_Option . semicolon )

    /**
     *
     * @return
     */
        protected CNode _ProcedureCall(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.sycall);
        vNode = _Var();
        vNodes.add(vNode);
        vNode = _Selector_List0();
        vNodes.add(vNode);
        vNode = _AParameters_Option();
        vNodes.add(vNode);
        term(OBERON0_Codes.sysemicolon);
        return bdTuple("ProcedureCall","",vNodes,vDataList);
    }

    //If=( if . Condition . then . Statement_List1 . ElseIf_List0 . else . Statement_List0 . end )

    /**
     *
     * @return
     */
        protected CNode _If(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syif);
        vNode = _Condition();
        vNodes.add(vNode);
        term(OBERON0_Codes.sythen);
        vNode = _Statement_List1();
        vNodes.add(vNode);
        vNode = _ElseIf_List0();
        vNodes.add(vNode);
        term(OBERON0_Codes.syelse);
        vNode = _Statement_List0();
        vNodes.add(vNode);
        term(OBERON0_Codes.syend);
        return bdTuple("If","",vNodes,vDataList);
    }

    //ElseIfPart=( elsif . Condition . then . Statement_List1 )

    /**
     *
     * @return
     */
        protected CNode _ElseIfPart(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syelsif);
        vNode = _Condition();
        vNodes.add(vNode);
        term(OBERON0_Codes.sythen);
        vNode = _Statement_List1();
        vNodes.add(vNode);
        return bdTuple("ElseIfPart","",vNodes,vDataList);
    }

    //Repeat=( repeat . Statement_List1 . until . Condition )

    /**
     *
     * @return
     */
        protected CNode _Repeat(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syrepeat);
        vNode = _Statement_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.syuntil);
        vNode = _Condition();
        vNodes.add(vNode);
        return bdTuple("Repeat","",vNodes,vDataList);
    }

    //While=( while . Condition . do . Statement_List1 . end )

    /**
     *
     * @return
     */
        protected CNode _While(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.sywhile);
        vNode = _Condition();
        vNodes.add(vNode);
        term(OBERON0_Codes.sydo);
        vNode = _Statement_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.syend);
        return bdTuple("While","",vNodes,vDataList);
    }

    //For=( for . Var . becomes . Expression . to . Expression . by . Expression . do . Statement_List1 . end )

    /**
     *
     * @return
     */
        protected CNode _For(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syfor);
        vNode = _Var();
        vNodes.add(vNode);
        term(OBERON0_Codes.sybecomes);
        vNode = _Expression();
        vNodes.add(vNode);
        term(OBERON0_Codes.syto);
        vNode = _Expression();
        vNodes.add(vNode);
        term(OBERON0_Codes.syby);
        vNode = _Expression();
        vNodes.add(vNode);
        term(OBERON0_Codes.sydo);
        vNode = _Statement_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.syend);
        return bdTuple("For","",vNodes,vDataList);
    }

    //Case=( case . Expression . of . CasePart_List1 . else . Statement_List0 . end )

    /**
     *
     * @return
     */
        protected CNode _Case(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.sycase);
        vNode = _Expression();
        vNodes.add(vNode);
        term(OBERON0_Codes.syof);
        vNode = _CasePart_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.syelse);
        vNode = _Statement_List0();
        vNodes.add(vNode);
        term(OBERON0_Codes.syend);
        return bdTuple("Case","",vNodes,vDataList);
    }

    //CaseStat=( Expression_List1 . colon . Statement_List1 )

    /**
     *
     * @return
     */
        protected CNode _CaseStat(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vNode = _Expression_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.sycolon);
        vNode = _Statement_List1();
        vNodes.add(vNode);
        return bdTuple("CaseStat","",vNodes,vDataList);
    }

    //Read=( read . Var . semicolon )

    /**
     *
     * @return
     */
        protected CNode _Read(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syread);
        vNode = _Var();
        vNodes.add(vNode);
        term(OBERON0_Codes.sysemicolon);
        return bdTuple("Read","",vNodes,vDataList);
    }

    //Write=( write . Expression . semicolon )

    /**
     *
     * @return
     */
        protected CNode _Write(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.sywrite);
        vNode = _Expression();
        vNodes.add(vNode);
        term(OBERON0_Codes.sysemicolon);
        return bdTuple("Write","",vNodes,vDataList);
    }

    //FormalParameters=( open . FParameter_List1 . close )

    /**
     *
     * @return
     */
        protected CNode _FormalParameters(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syopen);
        vNode = _FParameter_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.syclose);
        return bdTuple("FormalParameters","",vNodes,vDataList);
    }

    //ActualParameters=( open . Expression_List1 . close )

    /**
     *
     * @return
     */
        protected CNode _ActualParameters(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syopen);
        vNode = _Expression_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.syclose);
        return bdTuple("ActualParameters","",vNodes,vDataList);
    }

    //FormalParameter=( Var_List1 . colon . Type )

    /**
     *
     * @return
     */
        protected CNode _FormalParameter(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vNode = _Var_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.sycolon);
        vNode = _Type();
        vNodes.add(vNode);
        return bdTuple("FormalParameter","",vNodes,vDataList);
    }

    //FieldSequence=( Var_List1 . colon . Type )

    /**
     *
     * @return
     */
        protected CNode _FieldSequence(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vNode = _Var_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.sycolon);
        vNode = _Type();
        vNodes.add(vNode);
        return bdTuple("FieldSequence","",vNodes,vDataList);
    }

    //RelationalCondition=( Expression . relOp@ . Expression )

    /**
     *
     * @return
     */
        protected CNode _RelationalCondition(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vNode = _Expression();
        vNodes.add(vNode);
        vData=termData(OBERON0_Codes.syrelOp);
        vDataList.add(vData);
        vNode = _Expression();
        vNodes.add(vNode);
        return bdTuple("RelationalCondition","",vNodes,vDataList);
    }

    //BasicType=( btype@ )

    /**
     *
     * @return
     */
        protected CNode _BasicType(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        vData=termData(OBERON0_Codes.sybtype);
        vDataList.add(vData);
        return bdTuple("BasicType","",vNodes,vDataList);
    }

    //ArrayType=( array . Expression_List1 . of . Type )

    /**
     *
     * @return
     */
        protected CNode _ArrayType(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syarray);
        vNode = _Expression_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.syof);
        vNode = _Type();
        vNodes.add(vNode);
        return bdTuple("ArrayType","",vNodes,vDataList);
    }

    //RecordType=( record . FSequence_List1 . end )

    /**
     *
     * @return
     */
        protected CNode _RecordType(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syrecord);
        vNode = _FSequence_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.syend);
        return bdTuple("RecordType","",vNodes,vDataList);
    }

    //FieldSelector=( dot . Var )

    /**
     *
     * @return
     */
        protected CNode _FieldSelector(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.sydot);
        vNode = _Var();
        vNodes.add(vNode);
        return bdTuple("FieldSelector","",vNodes,vDataList);
    }

    //ArraySelector=( squareOpen . Expression_List1 . squareClose )

    /**
     *
     * @return
     */
        protected CNode _ArraySelector(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.sysquareOpen);
        vNode = _Expression_List1();
        vNodes.add(vNode);
        term(OBERON0_Codes.sysquareClose);
        return bdTuple("ArraySelector","",vNodes,vDataList);
    }

    //Return=( return . Expression . semicolon )

    /**
     *
     * @return
     */
        protected CNode _Return(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.syreturn);
        vNode = _Expression();
        vNodes.add(vNode);
        term(OBERON0_Codes.sysemicolon);
        return bdTuple("Return","",vNodes,vDataList);
    }

    //ReturnType=( colon . Type )

    /**
     *
     * @return
     */
        protected CNode _ReturnType(){
        CNode vNode;
        String vData;
        ArrayList<CNode> vNodes = new ArrayList();
        ArrayList<String> vDataList = new ArrayList();
        term(OBERON0_Codes.sycolon);
        vNode = _Type();
        vNodes.add(vNode);
        return bdTuple("ReturnType","",vNodes,vDataList);
    }

    //Declaration_List1=Declaration+

    /**
     *
     * @return
     */
        protected CNode _Declaration_List1(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        do{
             vNode = _Declaration();
             vNodes.add(vNode);
        }while(First_Declaration_List1_0.has(getSym()));
        return bdPlus("Declaration_List1","_0",vNodes);
    }

    //Declaration_List0=Declaration*

    /**
     *
     * @return
     */
        protected CNode _Declaration_List0(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        while(First_Declaration_List0_0.has(getSym())){
             vNode = _Declaration();
             vNodes.add(vNode);
        }
        return bdStar("Declaration_List0","_0",vNodes);
    }

    //Statement_List1=Statement+

    /**
     *
     * @return
     */
        protected CNode _Statement_List1(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        do{
             vNode = _Statement();
             vNodes.add(vNode);
        }while(First_Statement_List1_0.has(getSym()));
        return bdPlus("Statement_List1","_0",vNodes);
    }

    //Statement_List0=Statement*

    /**
     *
     * @return
     */
        protected CNode _Statement_List0(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        while(First_Statement_List0_0.has(getSym())){
             vNode = _Statement();
             vNodes.add(vNode);
        }
        return bdStar("Statement_List0","_0",vNodes);
    }

    //CasePart_List1=(CasePart%bar)

    /**
     *
     * @return
     */
        protected CNode _CasePart_List1(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        vNode = _CasePart();
        vNodes.add(vNode);
        while(getSym() == OBERON0_Codes.sybar){
             nextSym();
             vNode = _CasePart();
             vNodes.add(vNode);
        }
        return bdAlt("CasePart_List1","",vNodes);
    }

    //Expression_List1=(Expression%comma)

    /**
     *
     * @return
     */
        protected CNode _Expression_List1(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        vNode = _Expression();
        vNodes.add(vNode);
        while(getSym() == OBERON0_Codes.sycomma){
             nextSym();
             vNode = _Expression();
             vNodes.add(vNode);
        }
        return bdAlt("Expression_List1","",vNodes);
    }

    //Var_List1=(Var%comma)

    /**
     *
     * @return
     */
        protected CNode _Var_List1(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        vNode = _Var();
        vNodes.add(vNode);
        while(getSym() == OBERON0_Codes.sycomma){
             nextSym();
             vNode = _Var();
             vNodes.add(vNode);
        }
        return bdAlt("Var_List1","",vNodes);
    }

    //ElseIf_List0=ElseIf*

    /**
     *
     * @return
     */
        protected CNode _ElseIf_List0(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        while(OBERON0_Codes.syelsif == getSym()){
             vNode = _ElseIf();
             vNodes.add(vNode);
        }
        return bdStar("ElseIf_List0","_0",vNodes);
    }

    //FParameter_List1=(FParameter%semicolon)

    /**
     *
     * @return
     */
        protected CNode _FParameter_List1(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        vNode = _FParameter();
        vNodes.add(vNode);
        while(getSym() == OBERON0_Codes.sysemicolon){
             nextSym();
             vNode = _FParameter();
             vNodes.add(vNode);
        }
        return bdAlt("FParameter_List1","",vNodes);
    }

    //FSequence_List1=(FSequence%semicolon)

    /**
     *
     * @return
     */
        protected CNode _FSequence_List1(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        vNode = _FSequence();
        vNodes.add(vNode);
        while(getSym() == OBERON0_Codes.sysemicolon){
             nextSym();
             vNode = _FSequence();
             vNodes.add(vNode);
        }
        return bdAlt("FSequence_List1","",vNodes);
    }

    //Selector_List0=Selector*

    /**
     *
     * @return
     */
        protected CNode _Selector_List0(){
        CNode vNode;
        ArrayList<CNode> vNodes = new ArrayList();
        while(First_Selector_List0_0.has(getSym())){
             vNode = _Selector();
             vNodes.add(vNode);
        }
        return bdStar("Selector_List0","_0",vNodes);
    }

    //FParameters_Option=FParameters?

    /**
     *
     * @return
     */
        protected CNode _FParameters_Option(){
        if(OBERON0_Codes.syopen == getSym()){
             CNode vNode = _FParameters();
             return bdOption("FParameters_Option","_0",true,vNode);
        }else{
            return bdOption("FParameters_Option","_0",false,null);
        }
    }

    //AParameters_Option=AParameters?

    /**
     *
     * @return
     */
        protected CNode _AParameters_Option(){
        if(OBERON0_Codes.syopen == getSym()){
             CNode vNode = _AParameters();
             return bdOption("AParameters_Option","_0",true,vNode);
        }else{
            return bdOption("AParameters_Option","_0",false,null);
        }
    }

    //ReturnExp_Option=ReturnExp?

    /**
     *
     * @return
     */
        protected CNode _ReturnExp_Option(){
        if(OBERON0_Codes.syreturn == getSym()){
             CNode vNode = _ReturnExp();
             return bdOption("ReturnExp_Option","_0",true,vNode);
        }else{
            return bdOption("ReturnExp_Option","_0",false,null);
        }
    }

    //ReturnExpType_Option=ReturnExpType?

    /**
     *
     * @return
     */
        protected CNode _ReturnExpType_Option(){
        if(OBERON0_Codes.sycolon == getSym()){
             CNode vNode = _ReturnExpType();
             return bdOption("ReturnExpType_Option","_0",true,vNode);
        }else{
            return bdOption("ReturnExpType_Option","_0",false,null);
        }
    }
}
