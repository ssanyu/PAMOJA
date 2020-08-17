package Java;



import Analyzers.CGrammarAnalyzer;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.Grammar.CGrammarCodes;

import GrammarNotions.SyntaxExpr.*;
import Sets.IntAlphabet;
import java.util.LinkedHashMap;

/**
 *
 * @author HP
 */
public class CRecDescentParserGenerator{
    
private LinkedHashMap<String,JMethodDec> methodMap;
    
   private CGrammar fGrammar;
   private CGrammarAnalyzer vAnalyzer=new CGrammarAnalyzer();
   private String fGrammarName;
   private String fPackage=" ";
   private CSE_List fList;  // List of SEs whose Analysis information needs to be computed.
   private boolean fTreeBuilding;
   private final String fmSymDecNameToMethodDecName="_%s";
   private final String fmGrammarNameToClassName="C%s";
   private final String fmSymcodeName="sy%s";
   private final String fmGrammarNameToSymbolCodesClassName="%s_Codes";
   private final String fmSymName="%s.%s";
   private final String fmCatchBlock=
            "catch(InvalidTerminalException e){\n"+
            "               fParseErrorList.add(e.fErrorObject);\n"+
            "         }";
      
   private final String basicProperties=
            "/*=============================================================\n"+
       	    "    A recursive descent parser with the following properties:\n"+
            "           Grammar kind = AOL\n"+
            "           Grammar conditions = ELL(1), No null(b) for B%b\n"+
            "           Grammar processing = Parser Generator\n"+
            "           Strategy = Deterministic\n"+
            "           Architecture = Recursive descent procedures with external scanner\n"+
            "           Scanner operations = getSym(),nextSym(),term()\n"+
            "           Error handling = Throws an exception on failure\n"+
            "           Error recovery = None\n"+
            "           Tree building = None\n"+
            "==================================================================*/\n\n";
   private final String treeproperties=
            "/*=================================================================\n"+
       	    "   A recursive descent parser with the following properties:\n"+
            "           Grammar kind = AOL\n"+
            "           Grammar conditions = ELL(1), No null(b) for B%b\n"+
            "           Grammar processing = Parser Generator\n"+
            "           Strategy = Deterministic\n"+
            "           Architecture = Recursive descent procedures with external scanner and tree builder\n"+
            "           Scanner operations = getSym(),nextSym(),term()\n"+
            "           Tree builder operations = bdEps(),bdTuple(),bdStar(),bdPlus(),bdOption(),bdAlt()\n"+
            "           Error handling = Throws an exception on failure, retuns a null tree\n"+
            "           Error recovery = None\n"+
            " ==================================================================*/\n\n";

    /**
     *
     */
    public CRecDescentParserGenerator(){
       fGrammar=new CGrammar();
       fList=new CSE_List();
   }
    
    /**
     *
     * @param aGrammar
     */
    public void setGrammar(CGrammar aGrammar){
        fGrammar=aGrammar;
   }
    
    /**
     *
     * @param aGrammarName
     */
    public void setGrammarName(String aGrammarName){
        fGrammarName=aGrammarName;
   }
   
    /**
     *
     * @return
     */
    public String getGrammarName(){
        return fGrammarName;
   }

    /**
     *
     * @param aPackage
     */
    public void setPackage(String aPackage){
        fPackage=aPackage;
   }
   
    /**
     *
     * @return
     */
    public String getPackage(){
        return fPackage;
   }
   
    /**
     *
     * @return
     */
    public boolean isTreeBuilding(){
       return fTreeBuilding;
   }
   
    /**
     *
     * @param aBool
     */
    public void setTreeBuilding(boolean aBool){
       fTreeBuilding=aBool;
   }
   
    /**
     *
     * @param aGrammar
     * @return
     */
    public CompilationUnit_List GrammarToCompilationUnitList(CGrammar aGrammar ){
       CompilationUnit_List vList=new CompilationUnit_List();
       vList.add(ECFGtoCompilationUnit(aGrammar));
       vList.add(TerminalsToSymbolCodes(aGrammar.getGrammarContext().getTerminalDefs()));
       return vList;
   }
   
   private JCompilationUnit TerminalsToSymbolCodes(CTerminalDef_List aList){
       String vPackage="package "+fPackage+';';
       ImportDec_List vImports=new ImportDec_List();
       TypeDec_List vList=new TypeDec_List(); 
             
       vList.add(terminalDefListToSymbolCodes(aList));
      
       JCompilationUnit cu=new JCompilationUnit(vPackage,
                                                vImports,
                                                vList);
       return cu;
    } 
       
   private JClassDeclaration terminalDefListToSymbolCodes(CTerminalDef_List aList){
        JModifiers vModifiers;
               
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        BodyDec_List members=new BodyDec_List();
        
        for(int i=0;i<aList.count();i++){
           members.add(new JFieldDec("public static final int "+makeSymCodeName(aList.getElt(i).getName())+"="+i+';')); 
        }
        
        JClassDeclaration cd=new JClassDeclaration(vModifiers,
                                                   makeSymbolCodesClassName(fGrammarName),
                                                   "",
                                                   null,
                                                   members);
    	return cd;  
        
    }
   
   
   private JCompilationUnit ECFGtoCompilationUnit(CGrammar aGrammar ) {
       String vPackage="package "+fPackage+';';
       ImportDec_List vImports=new ImportDec_List();
       TypeDec_List vList=new TypeDec_List(); 
 
       vImports.add(new JImportDec("import Sets.IntAlphabet;"));
       vImports.add(new JImportDec("import Parsers.CGeneratedRecDescent")); 
       vImports.add(new JImportDec("import Parsers.CParseError"));
       vImports.add(new JImportDec("import Parsers.CParserResult"));
       vImports.add(new JImportDec("import Parsers.InvalidTerminalException"));
       String vProperties=basicProperties;
       if(fTreeBuilding){
           vImports.add(new JImportDec("import Nodes.CNode"));
           vImports.add(new JImportDec("import java.util.ArrayList"));
           vProperties=treeproperties;
       }
       
       vList.add(GrammarToClassDeclaration(aGrammar));
      
       JCompilationUnit cu=new JCompilationUnit(vPackage,
                                                vImports,
                                                vProperties,
                                                vList);
       return cu;
   }
  
   private JClassDeclaration GrammarToClassDeclaration(CGrammar aGrammar){
        String vName;
        String vBody;
        CNonTerminalDef vNonTerminal;
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
	
        BodyDec_List members=new BodyDec_List();
        
        // Create a constructor
        members.add(createConstructor(grammarNameToClassName(fGrammarName)));
        
        //Override parse() function
        members.add(createParseMethod(aGrammar.getStartExpr()));
        
        //Override parseNonTerminal() function
        members.add(createParseNonTerminalMethod(aGrammar.getStartExpr()));
        //Generate parsing Methods    
        methodMap=new LinkedHashMap<>();
        for(int i=0;i<aGrammar.getGrammarContext().getNonTerminalDefs().contextCount();i++){
            vNonTerminal=aGrammar.getGrammarContext().getNonTerminalDefs().getElt(i);
            members.add(productionToMethodDeclaration(vNonTerminal));
            vName=vNonTerminal.getName();
            vBody=vNonTerminal.getBody().toText();
            methodMap.put(vName+'='+vBody, productionToMethodDeclaration(vNonTerminal));
        }
        
        //Generate set constants
         
        if(fList.count()!=0){
           for(int i=0;i<fList.count();i++){
              String  vCName=makeNameFrom("First_",fList.getElt(i));
                IntAlphabet vAlph=makeFirstofTerminals(fList.getElt(i));
                 if(vAlph.cardinality()>1)  
                     members.add(new JFieldDec("private IntAlphabet "+vCName+" = new IntAlphabet ( new int[] "+intSetToSymCodes(fGrammar,vAlph)+");"));
           }
        }
        
        //JClassDeclaration(List<JModifier>modifiers, String name, String baseClass,ClassOrInterfaceType_List interfaces,BodyDec_List members)
        JClassDeclaration cd=new JClassDeclaration(vModifiers,
                                                   grammarNameToClassName(fGrammarName),
                                                   "CGeneratedRecDescent",
                                                   null,
                                                   members);
    	return cd;
   }
   
   private JConstructorDec createConstructor(String aName){
       JModifiers vModifiers;
       Statement_List vStatList;
       
       vModifiers=new JModifiers();
       vModifiers.add(JModifiers.PUBLIC); 
       
       vStatList=new Statement_List();   
       vStatList.add(new JExpressionStmt("super()"));
       
       JConstructorDec cd=new JConstructorDec(vModifiers,
                                          aName,
                                          new Parameter_List(),
                                          vStatList);
       return cd;
       
   }
   
      // To DO.. check if aSE is MultiDot
   private JMethodDec createParseMethod(CSE aSE){ 
        JModifiers vModifiers;
        Statement_List vStatList;
        CSE vSE;
        CSE_Sym vSym=null;
        CSE_MultiDot vDot=null;
        CSE_MultiStick vStick;
        
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        //JTryStmt block
        Statement_List vTryStatList=new Statement_List();
        
        vStatList=new Statement_List();   
        vStatList.add(new JExpressionStmt("CParserResult vResult=new CParserResult()"));
        vStatList.add(new JExpressionStmt("reSet()"));
        
        
        if(aSE instanceof CSE_MultiDot){
            vDot=(CSE_MultiDot)aSE;
            vSE=vDot.getList().getElt(0);
            if(vSE instanceof CSE_Sym){
                vSym=(CSE_Sym)vDot.getList().getElt(0);
            }else if(vSE instanceof CSE_MultiStick){
                vStick=(CSE_MultiStick)vSE;
                vSym=(CSE_Sym)vStick.getList().getElt(0);
            }
        }
        
        Statement_List vThen=new Statement_List();
        Statement_List vElse=new Statement_List();
            
        if(fTreeBuilding){
           vTryStatList.add(new JExpressionStmt("CNode vNode="+symDecNameToMethodDecName(vSym.getName()+"()"))); 
           vThen.add(new JExpressionStmt("vResult.setNode(vNode)"));
           vThen.add(new JExpressionStmt("vResult.setSuccess(true)"));
           vElse.add(new JExpressionStmt("vResult.setNode(null)"));
           vElse.add(new JExpressionStmt("vResult.setSuccess(false)"));
        }else{
           vTryStatList.add(new JExpressionStmt(symDecNameToMethodDecName(vSym.getName()+"()"))); 
           vThen.add(new JExpressionStmt("vResult.setSuccess(true)"));
           vElse.add(new JExpressionStmt("vResult.setSuccess(false)"));
        }
        vElse.add(new JExpressionStmt("fParseErrorList.add(new CParseError"+
                        "(\"Expected end of input. Failed to match a symbol\","+
                         makeSymbolCodesClassName(fGrammarName)+".syendmarker,"+
                          "\"endmarker\",\"\",getSymPos()) )"));
        
        CSE_Sym vEndMarker=(CSE_Sym)vDot.getList().getElt(1);
        vTryStatList.add(new JIfStmt(new JNameExpr("getSym()=="+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vEndMarker.getName()))),
				       vThen,vElse));
        
       
        //Catch block
        Catch_List vCatchList=new Catch_List();
        vCatchList.add(new JCatchBlock(fmCatchBlock));
        
        JTryStmt vTry=new JTryStmt(vTryStatList,vCatchList);
        
        vStatList.add(vTry);
        vStatList.add(new JReturnStmt("vResult"));
        
        JMethodDec md=new JMethodDec(vModifiers,
                                     new JClassOrInterfaceType("CParserResult"),
                                     "parse",
                                     new Parameter_List(),
                                     vStatList);
        return md;
   } 
   
   private JMethodDec createParseNonTerminalMethod(CSE aSE){ // To Do, check if aSE is Multi Dot
        JModifiers vModifiers;
        Statement_List vStatList;
        CSE vSE;
        CSE_Sym vSym;
        CSE_MultiDot vDot=null;
        CSE_MultiStick vStick;
        JSwitchStmt vSwitch=null;
        Parameter_List vPars;
        
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        vPars=new Parameter_List();
        vPars.add(new JParameter(new JReferenceType("String"),
                                 "aName"));
        
        //JTryStmt block
        Statement_List vTryStatList=new Statement_List();
        
        vStatList=new Statement_List();   
        vStatList.add(new JExpressionStmt("CParserResult vResult=new CParserResult()"));
        if(fTreeBuilding){
           vStatList.add(new JExpressionStmt("CNode vNode=null")); 
        }
        vStatList.add(new JExpressionStmt("reSet()"));
       
        if(aSE instanceof CSE_MultiDot){
            vDot=(CSE_MultiDot)aSE;
            vSE=vDot.getList().getElt(0);
            if(vSE instanceof CSE_MultiStick){
                vStick=(CSE_MultiStick)vSE;
                SwitchEntryStmt_List vSwitchEntryList=new SwitchEntryStmt_List();
                Statement_List vStmtsListforSwitch;
        
                for(int i=0;i<vStick.getList().count();i++){
                    vSym=(CSE_Sym)vStick.getList().getElt(i);
                    vStmtsListforSwitch=new Statement_List();
                    if(fTreeBuilding){
                        vStmtsListforSwitch.add(new JExpressionStmt("vNode="+symDecNameToMethodDecName(vSym.getName()+"()")));
                    }else{
                        vStmtsListforSwitch.add(new JExpressionStmt(symDecNameToMethodDecName(vSym.getName()+"()")));
                    }
                    vStmtsListforSwitch.add(new JExpressionStmt("break"));
                    vSwitchEntryList.add(new JSwitchEntryStmt("case \""+vSym.getName()+"\"",vStmtsListforSwitch));
                }
                //Add default
                vStmtsListforSwitch=new Statement_List();
                
                vStmtsListforSwitch.add(new JExpressionStmt("fParseErrorList.add(new CParseError"+
                        "(\"Invalid start expression\","+
                         "-1,"+ "aName"+",\"\",null))"));
                vStmtsListforSwitch.add(new JExpressionStmt("break"));
                vSwitchEntryList.add(new JSwitchEntryStmt("default",vStmtsListforSwitch));
        
                vSwitch=new JSwitchStmt("aName",vSwitchEntryList);
          }
        }
        
        Statement_List vThen=new Statement_List();
        Statement_List vElse=new Statement_List();
            
        if(fTreeBuilding){
           vTryStatList.add(vSwitch); 
           vThen.add(new JExpressionStmt("vResult.setNode(vNode)"));
           vThen.add(new JExpressionStmt("vResult.setSuccess(true)"));
           vElse.add(new JExpressionStmt("vResult.setNode(null)"));
           vElse.add(new JExpressionStmt("vResult.setSuccess(false)"));
        }else{
           vTryStatList.add(vSwitch);
           vThen.add(new JExpressionStmt("vResult.setSuccess(true)"));
           vElse.add(new JExpressionStmt("vResult.setSuccess(false)"));
        }
        vElse.add(new JExpressionStmt("fParseErrorList.add(new CParseError"+
                        "(\"Expected end of input. Failed to match a symbol\","+
                         makeSymbolCodesClassName(fGrammarName)+".syendmarker,"+
                          "\"endmarker\",\"\",getSymPos()) )"));
        
        CSE_Sym vEndMarker=(CSE_Sym)vDot.getList().getElt(1);
        vTryStatList.add(new JIfStmt(new JNameExpr("getSym()=="+makeSymbolCodesClassName(fGrammarName)+'.'+makeSymCodeName(vEndMarker.getName())),
				       vThen,vElse));
        
       
        //Catch block
        Catch_List vCatchList=new Catch_List();
        vCatchList.add(new JCatchBlock(fmCatchBlock));
        
        JTryStmt vTry=new JTryStmt(vTryStatList,vCatchList);
        
        vStatList.add(vTry);
        vStatList.add(new JReturnStmt("vResult"));
        
        JMethodDec md=new JMethodDec(vModifiers,
                                     new JClassOrInterfaceType("CParserResult"),
                                     "parseNonTerminal",
                                     vPars,
                                     vStatList);
        return md;
   }

    /**
     *
     * @param aDef
     * @return
     */
    public JMethodDec productionToMethodDeclaration(CNonTerminalDef aDef){
        JType vType;
        Statement_List vStatList;
        JModifiers vModifiers;
        JStatement rs;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PROTECTED); 
        
        vStatList=new Statement_List();       
        if(fTreeBuilding){
            vType=new JClassOrInterfaceType("CNode");
        }else{
            vType=new JVoidType();
        }
        
        rs=SEToStatement(aDef.getBody());
        vStatList.add(rs);    
        JMethodDec md=new JMethodDec(vModifiers,
                                     vType,
                                     symDecNameToMethodDecName(aDef.getName()),
                                     new Parameter_List(),
                                     vStatList,
                                     "//"+aDef.getName()+'='+aDef.getBody().toText());
         return md;
   }
   
   private  JStatement SEToStatement(CSE aSE){
        Statement_List vStatList;
        JStatement vString=null;
      
        switch(aSE.sortCode()){
            case CGrammarCodes.scSEEmpty:
               if(fTreeBuilding){
                  vStatList=new Statement_List();
                  vStatList.add(new JExpressionStmt("fParseErrorList.add(new CParseError"+
                        "(\"Empty expression not allowed.\","+
                         "\"\","+"\"\""+",\"\"))"));
                  vStatList.add(new JExpressionStmt("return null"));
                  vString=new JBlockStatement(vStatList);
                }else{
                  vString=new JExpressionStmt("fParseErrorList.add(new CParseError"+
                        "(\"Empty expression not allowed.\","+
                         "\"\","+"\"\""+",\"\"");
                }
               break;
        
            case CGrammarCodes.scSEEps: 
                CSE_Eps aEps=(CSE_Eps)aSE;
                if(fTreeBuilding){
                   vString= new JExpressionStmt("return bdEps(\""+vAnalyzer.analysisOfECFG(aEps).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aEps).fPath+"\")" );
                }else{
                   vString=new JExpressionStmt("");
                }
                break;
                
            case CGrammarCodes.scSESym:
                CSymDec vSymDec;
                
                CSE_Sym vSym=(CSE_Sym)aSE;
                vSymDec=vSym.getDec();
                switch(vSymDec.sortCode()){
                        case CGrammarCodes.scTerminalDef:
                            if(fTreeBuilding){
                                vStatList=new Statement_List();
                                if(((CTerminalDec)vSymDec).hasData()){
                                    vStatList.add(new JExpressionStmt("String vData=termData("+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vSymDec.getName()))+")"));
                                    vStatList.add(new JExpressionStmt("return bdTermData(\""+vAnalyzer.analysisOfECFG(aSE).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aSE).fPath+"\",\""+vSymDec.getName()+"\",vData)"));
                                    vString=new JBlockStatement(vStatList);
                                }else{
                                    vStatList.add(new JExpressionStmt("term("+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vSymDec.getName())+")")));
                                    vStatList.add(new JExpressionStmt("return bdTerm(\""+vAnalyzer.analysisOfECFG(aSE).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aSE).fPath+"\",\""+vSymDec.getName()+"\")"));
                                    vString=new JBlockStatement(vStatList);
                                } 
                         }else{
                                vString=new JExpressionStmt("term("+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vSymDec.getName())+")"));
                            } 
                           break;
                        case CGrammarCodes.scNonTerminalDef:
                            if(fTreeBuilding){
                               vStatList=new Statement_List();
                               vStatList.add(new JExpressionStmt("CNode vNode = "+symDecNameToMethodDecName(vSym.getName()+"()")));
                               vStatList.add(new JExpressionStmt("return vNode"));
                               vString=new JBlockStatement(vStatList);
                            }else{
                               vString=new JExpressionStmt(symDecNameToMethodDecName(vSym.getName()+"()")); 
                            }
                        break;
                        default: assert false: String.format("Illegal SortCode=%d in CRecDescentParserGenerator.SEToStatement()", aSE.sortCode());
                        break;
                 }
               break;
                
            case  CGrammarCodes.scSEMultiDot:
                      
                CSE_MultiDot aDot=(CSE_MultiDot)aSE;
                
                vStatList=new Statement_List();
		if(fTreeBuilding){
                   vStatList.add(new JExpressionStmt("CNode vNode"));
                   vStatList.add(new JExpressionStmt("String vData"));
                   vStatList.add(new JExpressionStmt("ArrayList<CNode> vNodes = new ArrayList<CNode>()"));
                   vStatList.add(new JExpressionStmt("ArrayList<String> vDataList = new ArrayList<String>()"));
                    
                   for(int i=0;i<aDot.getList().count();i++){
                      CSE vSE= aDot.getList().getElt(i);
                      CSE_Sym vSymbol=(CSE_Sym)vSE;
                      CSymDec vSymbolDec=vSymbol.getDec();
                          
                      if(isTerminal(vSE)){
                          if(((CTerminalDec)vSymbolDec).hasData()){
                              vStatList.add(new JExpressionStmt("vData=termData("+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vSymbolDec.getName()))+")"));
                              vStatList.add(new JExpressionStmt("vDataList.add(vData)"));
                          }else{
                              vStatList.add(new JExpressionStmt("term("+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vSymbolDec.getName()))+")"));
                          } 
                      }else if(isNonTerminal(vSE)){
                          vStatList.add(new JExpressionStmt("vNode = "+symDecNameToMethodDecName(vSymbolDec.getName()+"()")));
                          vStatList.add(new JExpressionStmt("vNodes.add(vNode)"));
                      }
                    }
                    vStatList.add(new JExpressionStmt("return bdTuple(\""+vAnalyzer.analysisOfECFG(aDot).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aDot).fPath+"\",vNodes,vDataList)"));
                    
                }else{
                    for(int i=0;i<aDot.getList().count();i++){
                        CSE vSE= aDot.getList().getElt(i);
                        CSE_Sym vSymbol=(CSE_Sym)vSE;
                        CSymDec vSymbolDec=vSymbol.getDec();
                      
                        if(isTerminal(vSE)){
                            vStatList.add(new JExpressionStmt("term("+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vSymbolDec.getName()))+")"));
                        }else if(isNonTerminal(vSE)){
                            vStatList.add(new JExpressionStmt(symDecNameToMethodDecName(vSymbolDec.getName()+"()")));
                        }
                     }
                }
                vString=new JBlockStatement(vStatList);
                break;
            //JIfStmt(JExpression condition, Statement_List thenStmt, Statement_List elseStmt)
            case CGrammarCodes.scSEMultiStick:
                 CSE_MultiStick aStick=(CSE_MultiStick)aSE;
                 Statement_List vThen;
                 Statement_List vElse=new Statement_List();
                 JIfStmt vIfStmt;
                  //System.out.println(aStick.toText()); //(A|B)
                  //System.out.println(aStick.getList().count()); //2
                 for(int i=1;i<aStick.getList().count();i++){
                   // System.out.println(aStick.getList().getElt(i).toText()); 
                    CSE vSE= aStick.getList().getElt(i);
                   
                    CSE_Sym vSymbol=(CSE_Sym)vSE; 
                  // System.out.println(vSymbol.toText());
                    CSymDec vSymbolDec=vSymbol.getDec();
                  //  System.out.println(vSymbolDec.toString());
                    vThen=new Statement_List();
                    if(fTreeBuilding){
                          vThen.add(new JExpressionStmt("vNode = "+symDecNameToMethodDecName(vSymbolDec.getName()+"()")));
                          vThen.add(new JExpressionStmt("k="+i));
                    }else{
                         // System.out.println(vSymbolDec.getName());
                          vThen.add(new JExpressionStmt(symDecNameToMethodDecName(vSymbolDec.getName()+"()")));
                    }             
                    vIfStmt=new JIfStmt(new JNameExpr(makeSetExpr(aStick.getList().getElt(i))),
				       vThen,new Statement_List());
                    vElse.add(vIfStmt);
                    
                    //add a SE whose set needs to be computed
                    if(vAnalyzer.analysisOfECFG(aStick.getList().getElt(i)).fFirst.cardinality()>1){
                        fList.add(aStick.getList().getElt(i));
                    }
                 }
                 
                 //create default 
                 Statement_List vDefault=new Statement_List();
                 vDefault.add(new JExpressionStmt("throw new InvalidTerminalException(new CParseError" +
                 "(\" "+ vAnalyzer.analysisOfECFG(aStick).fRoot+ " cannot begin with symbol\",getSym(),getSymName(),"+
                "getSymValue(),getSymPos()))" ));
                 
                 vThen=new Statement_List();
                 CSE vSE= aStick.getList().getElt(0);
                 CSE_Sym vSymbol=(CSE_Sym)vSE;
                 CSymDec vSymbolDec=vSymbol.getDec();
                 if(fTreeBuilding){
                    vThen.add(new JExpressionStmt("vNode = "+symDecNameToMethodDecName(vSymbolDec.getName()+"()")));
                    vThen.add(new JExpressionStmt("k=0"));
                 }else{
                    vThen.add(new JExpressionStmt(symDecNameToMethodDecName(vSymbolDec.getName()+"()")));
                 }   
                
                 vString=new JIfStmt(new JNameExpr(makeSetExpr(aStick.getList().getElt(0))),
				       vThen, vElse,vDefault);
                
                 //add a SE whose set needs to be computed
                 if(vAnalyzer.analysisOfECFG(aStick.getList().getElt(0)).fFirst.cardinality()>1){
                      fList.add(aStick.getList().getElt(0));
                 }
                 if(fTreeBuilding){
                   Statement_List vList=new Statement_List();
                   vList.add(new JExpressionStmt("CNode vNode=null"));
                   vList.add(new JExpressionStmt("int k"));
                   vList.add(vString);
                   vList.add(new JExpressionStmt("return bdMultiStick(\""+vAnalyzer.analysisOfECFG(aStick).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aStick).fPath+"\",k,vNode)"));
                   vString=new JBlockStatement(vList);
                 }
               break;
            case CGrammarCodes.scSEStar: 
                CSE_Star aStar=(CSE_Star)aSE;
                vSymbol=(CSE_Sym)aStar.getArg();
                vSymbolDec=vSymbol.getDec();
                
                vStatList=new Statement_List();
                Statement_List vList=new Statement_List();
               // vStatList.add(SEToStatement(aStar.getArg()));
                if(fTreeBuilding){
                   vList.add(new JExpressionStmt("CNode vNode"));
                   vList.add(new JExpressionStmt("ArrayList<CNode> vNodes = new ArrayList<CNode>()")); 
                   vStatList.add(new JExpressionStmt("vNode = "+symDecNameToMethodDecName(vSymbolDec.getName()+"()"))); 
                   vStatList.add(new JExpressionStmt("vNodes.add(vNode)"));
                   vString=new JWhileStmt(new JNameExpr(makeSetExpr(aStar.getArg())),
				       vStatList);
                   vList.add(vString);
                   vList.add(new JExpressionStmt("return bdStar(\""+vAnalyzer.analysisOfECFG(aStar.getArg()).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aStar.getArg()).fPath+"\",vNodes)"));
                
                }else{
                   vStatList.add(new JExpressionStmt(symDecNameToMethodDecName(vSymbolDec.getName()+"()"))); 
                   vString=new JWhileStmt(new JNameExpr(makeSetExpr(aStar.getArg())),
				       vStatList);
                   vList.add(vString);
                }
                               
                vString=new JBlockStatement(vList);
                
                //add a SE whose set needs to be computed
                if(vAnalyzer.analysisOfECFG(aStar.getArg()).fFirst.cardinality()>1){
                     fList.add(aStar.getArg());
                }
                break;
            case CGrammarCodes.scSEPlus:
                CSE_Plus aPlus=(CSE_Plus)aSE;
                vSymbol=(CSE_Sym)aPlus.getArg();
                vSymbolDec=vSymbol.getDec();
                
                vStatList=new Statement_List();
                vList=new Statement_List();
                if(fTreeBuilding){
                   vList.add(new JExpressionStmt("CNode vNode"));
                   vList.add(new JExpressionStmt("ArrayList<CNode> vNodes = new ArrayList<CNode>()")); 
                   vStatList.add(new JExpressionStmt("vNode = "+symDecNameToMethodDecName(vSymbolDec.getName()+"()"))); 
                   vStatList.add(new JExpressionStmt("vNodes.add(vNode)"));
                   vString=new JDoStmt(new JNameExpr(makeSetExpr(aPlus.getArg())),
				       vStatList);
                   vList.add(vString);
                   vList.add(new JExpressionStmt("return bdPlus(\""+vAnalyzer.analysisOfECFG(aPlus.getArg()).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aPlus.getArg()).fPath+"\",vNodes)"));
                
                }else{
                   vStatList.add(new JExpressionStmt(symDecNameToMethodDecName(vSymbolDec.getName()+"()"))); 
                   vString=new JDoStmt(new JNameExpr(makeSetExpr(aPlus.getArg())),
				       vStatList);
                   vList.add(vString);
                }
                               
                vString=new JBlockStatement(vList);
                
                //add a SE whose set needs to be computed
                if(vAnalyzer.analysisOfECFG(aPlus.getArg()).fFirst.cardinality()>1){
                     fList.add(aPlus.getArg());
                }
                break;
            case CGrammarCodes.scSEOption:
                CSE_Option aOption=(CSE_Option)aSE;
                vSymbol=(CSE_Sym)aOption.getArg();
                vSymbolDec=vSymbol.getDec();
                vStatList=new Statement_List();
               
                Statement_List vStatList2=new Statement_List();
                if(fTreeBuilding){
                   vStatList.add(new JExpressionStmt("CNode vNode = "+symDecNameToMethodDecName(vSymbolDec.getName()+"()"))); 
                   vStatList.add(new JExpressionStmt("return bdOption(\""+vAnalyzer.analysisOfECFG(aOption.getArg()).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aOption.getArg()).fPath+"\",true,vNode)"));
                   vStatList2.add(new JExpressionStmt("return bdOption(\""+vAnalyzer.analysisOfECFG(aOption.getArg()).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aOption.getArg()).fPath+"\",false,null)"));
                }else{
                   vStatList.add(new JExpressionStmt(symDecNameToMethodDecName(vSymbolDec.getName()+"()")));  
                }
               
                vString=new JIfStmt(new JNameExpr(makeSetExpr(aOption.getArg())),
				       vStatList, vStatList2);
                //add a SE whose set needs to be computed
                if(vAnalyzer.analysisOfECFG(aOption.getArg()).fFirst.cardinality()>1){
                      fList.add(aOption.getArg());
                }
                break;
            case CGrammarCodes.scSEAlt:
                 CSE_Alt aAlt=(CSE_Alt)aSE;
                 CSE_Sym vRight=(CSE_Sym)aAlt.getRight();
                 CSymDec vDecr=vRight.getDec();
                 
                 vList=new Statement_List();
                 vStatList=new Statement_List();
                 CSE_Sym vLeft=(CSE_Sym)aAlt.getLeft();
                 CSymDec vDec=vLeft.getDec();
                 if(fTreeBuilding){
                   vList.add(new JExpressionStmt("CNode vNode"));
                   vList.add(new JExpressionStmt("ArrayList<CNode> vNodes = new ArrayList<CNode>()")); 
                   vList.add(new JExpressionStmt("vNode = "+symDecNameToMethodDecName(vDec.getName()+"()"))); 
                   vList.add(new JExpressionStmt("vNodes.add(vNode)"));
                  
                   if(((CTerminalDec)vDecr).hasData()){
                       vStatList.add(new JExpressionStmt("String vData = getSymValue()"));
                       vStatList.add(new JExpressionStmt("vNode=bdTermData(\""+vAnalyzer.analysisOfECFG(vRight).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(vRight).fPath+"\","+"\""+vDecr.getName()+"\",vData)"));
                       vStatList.add(new JExpressionStmt("vNodes.add(vNode)"));
                   }
                   vStatList.add(new JExpressionStmt("nextSym()"));
                   vStatList.add(new JExpressionStmt("vNode = "+symDecNameToMethodDecName(vDec.getName()+"()"))); 
                   vStatList.add(new JExpressionStmt("vNodes.add(vNode)"));
                   vList.add(new JWhileStmt(new JNameExpr("getSym() == "+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vDecr.getName()))),
				       vStatList));
                   vList.add(new JExpressionStmt("return bdAlt(\""+vAnalyzer.analysisOfECFG(aAlt).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aAlt).fPath+"\",vNodes)"));
                 }else{
                   vList.add(new JExpressionStmt(symDecNameToMethodDecName(vDec.getName()+"()"))); 
                   
                   vStatList.add(new JExpressionStmt("nextSym()"));
                   vStatList.add(new JExpressionStmt(symDecNameToMethodDecName(vDec.getName()+"()"))); 
                   vList.add(new JWhileStmt(new JNameExpr("getSym() == "+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vDecr.getName()))),
				       vStatList));
                 }
                              
                 vString= new JBlockStatement(vList);
                 break;
             case CGrammarCodes.scSEAlt2:
                 CSE_Alt2 aAlt2=(CSE_Alt2)aSE;
                 vRight=(CSE_Sym)aAlt2.getRight();
                 vDecr=vRight.getDec();
                 
                 vList=new Statement_List();
                 vStatList=new Statement_List();
                 vLeft=(CSE_Sym)aAlt2.getLeft();
                 vDec=vLeft.getDec();
                 if(fTreeBuilding){
                   vList.add(new JExpressionStmt("CNode vNode"));
                   vList.add(new JExpressionStmt("ArrayList<CNode> vNodes = new ArrayList<CNode>()")); 
                   vList.add(new JExpressionStmt("vNode = "+symDecNameToMethodDecName(vDec.getName()+"()"))); 
                   vList.add(new JExpressionStmt("vNodes.add(vNode)"));
                   
                   if(((CTerminalDec)vDecr).hasData()){
                       vStatList.add(new JExpressionStmt("String vData = getSymValue()"));
                       vStatList.add(new JExpressionStmt("vNode=bdTermData(\""+vAnalyzer.analysisOfECFG(vRight).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(vRight).fPath+"\","+"\""+vDecr.getName()+"\",vData)"));
                       vStatList.add(new JExpressionStmt("vNodes.add(vNode)"));
                   }
                   vStatList.add(new JExpressionStmt("nextSym()"));
                   vStatList.add(new JExpressionStmt("vNode = "+symDecNameToMethodDecName(vDec.getName()+"()"))); 
                   vStatList.add(new JExpressionStmt("vNodes.add(vNode)"));
                   vList.add(new JWhileStmt(new JNameExpr("getSym() == "+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vDecr.getName()))),
				       vStatList));
                   vThen=new Statement_List();
                   vElse=new Statement_List();
                   vThen.add(new JExpressionStmt("return vNodes.get(0)"));
                   vElse.add(new JExpressionStmt("return bdAlt(\""+vAnalyzer.analysisOfECFG(aAlt2).fRoot+"\"," + "\""+vAnalyzer.analysisOfECFG(aAlt2).fPath+"\",vNodes)"));
                   vList.add(new JIfStmt(new JNameExpr("vNodes.size()==1"),vThen,vElse));
                 
                 }else{
                   vList.add(new JExpressionStmt(symDecNameToMethodDecName(vDec.getName()+"()"))); 
                   
                   vStatList.add(new JExpressionStmt("nextSym()"));
                   vStatList.add(new JExpressionStmt(symDecNameToMethodDecName(vDec.getName()+"()"))); 
                   vList.add(new JWhileStmt(new JNameExpr("getSym() == "+makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vDecr.getName()))),
				       vStatList));
                 }
                              
                 vString= new JBlockStatement(vList);
                 break;
            
            default: 
		vString=new JExpressionStmt("");   
		break;
            }
        return vString;
    }
   private boolean isTerminal(CSE aSE){
        CSE_Sym vSym=(CSE_Sym)aSE;
        CSymDec vSymDec=vSym.getDec();
        if(vSymDec instanceof CTerminalDef)
            return true;
        else return false;
                    
    }
    private boolean isNonTerminal(CSE aSE){
        CSE_Sym vSym=(CSE_Sym)aSE;
        CSymDec vSymDec=vSym.getDec();
        if(vSymDec instanceof CNonTerminalDef)
            return true;
        else return false;
                    
    }
     
    private String makeSetExpr(CSE aSE){
        String vName="";
       
        IntAlphabet vAlph=makeFirstofTerminals(aSE);
        if(vAlph.cardinality()==1){
            for(int i=0;i<fGrammar.getGrammarContext().symbolCount();i++){
                if(vAlph.has(i))
                    vName=fGrammar.getGrammarContext().getSymbol(i).getName();
            }
            return makeSymName(makeSymbolCodesClassName(fGrammarName),makeSymCodeName(vName))+" == getSym()";
        }else
            return makeNameFrom("First_",aSE) + ".has(getSym())";
    }
    
    private IntAlphabet makeFirstofTerminals(CSE aSE){
         IntAlphabet vAlph=vAnalyzer.analysisOfECFG(aSE).fFirst;
         IntAlphabet vTeminals=new IntAlphabet(fGrammar.getGrammarContext().firstTerminalIndex(),fGrammar.getGrammarContext().lastTerminalIndex());
         vAlph.bcIntersection(vTeminals);
         return vAlph;
    }
    
    
    private String symDecNameToMethodDecName(String aName){
         return String.format(fmSymDecNameToMethodDecName,aName);
    }
    
    private String grammarNameToClassName(String aName){
         return String.format(fmGrammarNameToClassName,aName);
    } 
    
    private String makeNameFrom(String prefix, CSE e){
        return prefix + vAnalyzer.analysisOfECFG(e).fRoot + vAnalyzer.analysisOfECFG(e).fPath;
    }
    private String makeSymName(String aName1,String aName2){
        return String.format(fmSymName,aName1,aName2);
    }
    private String makeSymCodeName(String aName){
        return String.format(fmSymcodeName,aName);
    }
    
    private String makeSymbolCodesClassName(String aName){
         return String.format(fmGrammarNameToSymbolCodesClassName,aName);
    }
    
    private String intSetToSymCodes(CGrammar aGrammar, IntAlphabet aAlph){
        StringBuffer vResult=new StringBuffer();
        
        vResult= vResult.append('{');
        for(int i=0;i<aGrammar.getGrammarContext().symbolCount();i++){
                if(aAlph.has(i)){
                    String vName=makeSymbolCodesClassName(fGrammarName)+'.'+makeSymCodeName(aGrammar.getGrammarContext().getSymbol(i).getName());
                    vResult=vResult.append(vName).append(",");
                }
        }
        vResult.deleteCharAt(vResult.length()-1);
        vResult=vResult.append('}');
        
        return vResult.toString();
        
    }

    /**
     *
     * @return
     */
    public LinkedHashMap<String, JMethodDec> getMethodMap() {
        return methodMap;
    }
    
    
            
}