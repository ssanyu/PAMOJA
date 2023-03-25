/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import java.lang.reflect.Modifier;
import java.util.Date;



/**
 *
 * @author jssanyu
 */
public class JFlattener {
    
    /**
     *
     */
    public final String fmtCompilationUnit="%s%s \n%s %s \n%s %s";

    /**
     *
     */
    public final String fmtImportDeclaration="%s%s;";

    /**
     *
     */
    public final String blankSpace=" ";
   // public final String fmtClassDeclaration=
    //      "%s class %s extends %s{\n%s}";

    /**
     *
     */
        public final String fmtClassDeclaration=
        "%s class %s %s %s %s %s{\n%s}";

    /**
     *
     */
    public final String fmtFieldDeclaration="%s%s %s %s;"; 

    /**
     *
     */
    public final String fmtConstructorDeclaration=
            "\n%s%s %s(%s){"+
              "%s"+
            "%s}";

    /**
     *
     */
    public final String fmtMethodDeclaration=
          "\n%s%s\n%s%s %s %s(%s){"+
            "%s"+
          "%s}";

    /**
     *
     */
    public final String fmtParameter="%s %s";

    /**
     *
     */
    public final String fmtBlockStatement="%s";

    /**
     *
     */
    public final String fmtReturnStmt="%sreturn %s;";

    /**
     *
     */
    public final String fmtSwitchStmt=
            "%sswitch(%s){"+
            "%s"+
            "%s}";

    /**
     *
     */
    public final String fmtIfStmt=
            "%sif(%s){"+
            "%s"+
            "%s}%s";

    /**
     *
     */
    public final String fmtIfElseStmt=
            "%sif(%s){"+
            "%s"+
            "%s}else{"+
            "%s"+
            "%s}";

    /**
     *
     */
    public final String fmtWhileStmt=
            "%swhile(%s){"+
            "%s"+
            "%s}";

    /**
     *
     */
    public final String fmtDoWhileStmt=
            "%sdo{"+
            "%s"+
            "%s}while(%s);";
    
    /**
     *
     */
    public final String fmtSwitchEntryStmt=
            "%s %s:"+
            "%s";

    /**
     *
     */
    public final String fmtTryStmt=
            "%stry{"+
            "%s"+
            "%s}%s";

    /**
     *
     */
    public final String fmtLabeledStmt="%s%s:%s";        

    /**
     *
     */
    public final String fmtAssertStmt="%sassert %s : %s;"; 

    /**
     *
     */
    public final String fmtBinOrAssignExpr="%s %s %s";

    /**
     *
     */
    public final String fmtInstanceOfExpr="%s instanceof %s";

    /**
     *
     */
    public final String fmtCastExpr="(%s)%s";

    /**
     *
     */
    public final String fmtClassExpr="%s";
    private final String comments=
            "/*====================================================\n"+
       	        "This file has been generated automatically\n\n"+
                "Date       : "+new Date()+"\n"+
             "=====================================================*/\n\n";  
       
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_CompilationUnit(JCompilationUnit a, int ind){
        String vSpace=dupeString(blankSpace,ind);
        if(a.types==null && a.fText.length()!=0){
            return vSpace+a.fText;
        }else if(a.types.count()!=0 && a.fText.length()!=0){
             return String.format(fmtCompilationUnit,vSpace,f_generateComments(),a.pakage,f_ImportDeclarationsList(a.imports,ind),a.fText,f_TypeDeclarationsList(a.types,ind));
        }else return String.format(fmtCompilationUnit,vSpace,f_generateComments(),a.pakage,f_ImportDeclarationsList(a.imports,ind),"",f_TypeDeclarationsList(a.types,ind));
    }
   
    //Generate Header Section

    /**
     *
     * @return
     */
        public String f_generateComments(){
            return comments;
    }
    
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_ImportDeclarationsList(ImportDec_List a,int ind){
      String result="\n\n";
      for(int i=0;i<=a.count()-1;i++){
          result=result+f_ImportDeclaration(a.getNode(i),ind)+"\n";
      }
      return result;
    }
    
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_ImportDeclaration(JImportDec a,int ind){
        String vSpace=dupeString(blankSpace,ind);
        return String.format(fmtImportDeclaration,vSpace,a.name); 
    }
    
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_TypeDeclarationsList(TypeDec_List a,int ind){
      String result="\n";
      for(int i=0;i<=a.count()-1;i++){
          result=result+f_TypeDeclaration(a.getNode(i),ind)+"\n";
      }
      return result;
    }
    
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_TypeDeclaration(JTypeDec a,int ind){
		  
		  String result="";
		  if(a instanceof JClassDeclaration){
			  JClassDeclaration cd=(JClassDeclaration)a;
			  result=f_ClassDeclaration(cd,ind+4);
		  
                  }else if(a instanceof JComments){
                      JComments cm=(JComments)a;
                      result=f_Comments(cm,ind+4);
                  }
		  return result;
    }
    
  /*  public String f_ClassDeclaration(JClassDeclaration aClass,int ind){
        if(aClass.members==null)
            return String.format(fmtClassDeclaration,f_ModifierList(aClass.modifiers),aClass.name,aClass.baseClass,aClass.fText);
        else return String.format(fmtClassDeclaration,f_ModifierList(aClass.modifiers),aClass.name,aClass.baseClass,f_BodyDeclarationList(aClass.members,ind));
    }*/

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    
      public String f_Comments(JComments a,int ind){
          String vSpace=dupeString(blankSpace,ind);
          return vSpace+a.name;
          
      }

    /**
     *
     * @param aClass
     * @param ind
     * @return
     */
    public String f_ClassDeclaration(JClassDeclaration aClass,int ind){
        String s="";
        if(aClass.fText.isEmpty()){
            if(aClass.baseClass!=null && !aClass.baseClass.isEmpty() && aClass.interfaces!=null && aClass.interfaces.count()!=0){
                    s=String.format(fmtClassDeclaration,f_ModifierList(aClass.modifiers),aClass.name,"extends",aClass.baseClass,"implements","",f_BodyDeclarationList(aClass.members,ind));
            } 
            if(aClass.baseClass!=null && !aClass.baseClass.isEmpty() && (aClass.interfaces==null || aClass.interfaces.count()==0)){
                   s= String.format(fmtClassDeclaration,f_ModifierList(aClass.modifiers),aClass.name,"extends",aClass.baseClass,"","",f_BodyDeclarationList(aClass.members,ind));
            } 
            if((aClass.baseClass==null || aClass.baseClass.isEmpty()) && aClass.interfaces!=null && aClass.interfaces.count()!=0){
                   s=String.format(fmtClassDeclaration,f_ModifierList(aClass.modifiers),aClass.name,"","","implements","",f_BodyDeclarationList(aClass.members,ind));
            }
            if(aClass.baseClass==null || aClass.baseClass.isEmpty() && (aClass.interfaces==null || aClass.interfaces.count()==0)){
                    s= String.format(fmtClassDeclaration,f_ModifierList(aClass.modifiers),aClass.name,"","","","",f_BodyDeclarationList(aClass.members,ind));
            } 
        }else if(!aClass.fText.isEmpty()){
            if(aClass.baseClass!=null && !aClass.baseClass.isEmpty() && aClass.interfaces!=null && aClass.interfaces.count()!=0){
                    s= String.format(fmtClassDeclaration,f_ModifierList(aClass.modifiers),aClass.name,"extends",aClass.baseClass,"implements","",aClass.fText);
            } 
            if(aClass.baseClass!=null && !aClass.baseClass.isEmpty() && (aClass.interfaces==null || aClass.interfaces.count()==0)){
                    s= String.format(fmtClassDeclaration,f_ModifierList(aClass.modifiers),aClass.name,"extends",aClass.baseClass,"","",aClass.fText);
            }
            if((aClass.baseClass==null || aClass.baseClass.isEmpty()) && aClass.interfaces!=null && aClass.interfaces.count()!=0){
                    s= String.format(fmtClassDeclaration,f_ModifierList(aClass.modifiers),aClass.name,"","","implements","",aClass.fText);
            }
            if(aClass.baseClass==null || aClass.baseClass.isEmpty() && (aClass.interfaces==null || aClass.interfaces.count()==0)){
                    s= String.format(fmtClassDeclaration,f_ModifierList(aClass.modifiers),aClass.name,"","","","",aClass.fText);
            } 
        }  
        return s;
      }
      
    /**
     *
     * @param a
     * @return
     */
    public String f_Modifier(int a){
		String result;
		switch(a){
		case Modifier.PRIVATE:
			result="private";
			break;
		case Modifier.PROTECTED:
			result="protected";
			break;
		case Modifier.PUBLIC:
			result="public";
			break;
		case Modifier.ABSTRACT:
		    result="abstract";
		    break;
		case Modifier.STATIC:
			result="static";
			break;
		case Modifier.FINAL:
			result="final";
			break;
		default:
			result="";
			break;
		}
	return result;
  }
    
    /**
     *
     * @param a
     * @return
     */
    public String f_ModifierList(JModifiers a){
      String result="";
      if(a.size()==0){
          return "";
      }else{
            result=result+f_Modifier(a.get(0));
            for(int i=1;i<=a.size()-1;i++){
                result=result+" "+f_Modifier(a.get(i));
            }
            return result;
      }
  }  
    
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_FieldDeclaration(JFieldDec a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      if(!a.fText.isEmpty())
          return vSpace+a.fText;
      else return String.format(fmtFieldDeclaration,vSpace,f_ModifierList(a.modifiers),f_Type(a.type),a.name);
  }  
    
 /* public String f_VariableDeclarationList(List<JVariableDeclarator> a){
   	 String result="";
   	 result=result+a.get(0).toString();
   	 for(int i=1;i<=a.size()-1;i++){
             result=result+','+a.get(i).toString();
   	 }
   	 return result;
  } */

    /**
     *
     * @param a
     * @return
     */
    
    
  public String f_Type(JType a){
      if(a instanceof JVoidType){
          return new JVoidType().type;
      }else if(a instanceof JClassOrInterfaceType){
          JClassOrInterfaceType ct=(JClassOrInterfaceType)a;
          return ct.name;
      }else if(a instanceof JReferenceType){
          JReferenceType rt=(JReferenceType)a;
          if(rt.type!=null)
               return rt.type.toString();
          else return rt.fText;
      }else if(a instanceof JPrimitiveType){
          JPrimitiveType pt=(JPrimitiveType)a;
          String result="";
          switch(pt.type){
              case Int:
                   result= "int";
                   break;
              case Null:
                   result= "null";
                   break;
              case Boolean:
                   result= "boolean";
                   break;
              case Char:
                   result= "char";
                   break;
              case Byte:
                   result= "byte";
                   break;
              case Short:
                   result= "short";
                   break;
              case Long:
                   result= "long";
                   break;
              case Float:
                   result= "float";
                   break;
              case Double:
                   result= "double";
                  break;
          }
          return result;
      } else return "";
  }  
    
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_BodyDeclaration(JBodyDec a,int ind){
      String result="";
      if(a instanceof JFieldDec){
          JFieldDec fd=(JFieldDec)a;
	  result=f_FieldDeclaration(fd,ind);
      }else if(a instanceof JConstructorDec){
          JConstructorDec cd=(JConstructorDec)a;
	  result=f_ConstructorDeclaration(cd,ind);
      }else if(a instanceof JMethodDec){
          JMethodDec md=(JMethodDec)a;
	  result=f_MethodDeclaration(md,ind);
      }
      return result;
  }  
    
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_BodyDeclarationList(BodyDec_List a,int ind){
       String result="";
       
       for(int i=0;i<=a.count()-1;i++){
           if(a.getNode(i)instanceof JFieldDec){
               result=result+f_BodyDeclaration(a.getNode(i),ind)+"\n";
	   }
       }
       for(int i=0;i<=a.count()-1;i++){
           if(a.getNode(i)instanceof JConstructorDec){
               result=result+f_BodyDeclaration(a.getNode(i),ind)+"\n";
	   }
       }
       for(int i=0;i<=a.count()-1;i++){
           if(a.getNode(i)instanceof JMethodDec){
               result=result+f_BodyDeclaration(a.getNode(i),ind)+"\n";
	   }
       }
       return result;
  } 

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_ConstructorDeclaration(JConstructorDec a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      return String.format(fmtConstructorDeclaration,vSpace,f_ModifierList(a.modifiers),a.name,f_ParameterList(a.parameters),f_StatementList(a.statements,ind),vSpace);
  }

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_MethodDeclaration(JMethodDec a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      if(a.name==null && a.fText.length()!=0){
          return vSpace+a.fText;
      }else if(a.name.length()!=0 && a.fText.length()!=0){
          return String.format(fmtMethodDeclaration,vSpace,a.fText,vSpace,f_ModifierList(a.modifiers),f_Type(a.returnType),a.name,f_ParameterList(a.parameters),f_StatementList(a.statements,ind),vSpace);
      
      }else return String.format(fmtMethodDeclaration,"","",vSpace,f_ModifierList(a.modifiers),f_Type(a.returnType),a.name,f_ParameterList(a.parameters),f_StatementList(a.statements,ind),vSpace);
  }
 
    /**
     *
     * @param a
     * @return
     */
    public String f_Parameter(JParameter a){
      return String.format(fmtParameter,f_Type(a.type),a.paraName);
  }
 
    /**
     *
     * @param a
     * @return
     */
    public String f_ParameterList(Parameter_List a){
   	 String result="";
   	 if(a.count()==0)
             result="";
   	 else{
             result=result+f_Parameter(a.getNode(0));
   	     for(int i=1;i<=a.count()-1;i++){
                 result=result+','+f_Parameter(a.getNode(i));
   	     }
   	 }
   	 return result;
   }
  
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_Statement(JStatement a,int ind){
      if(a instanceof JBlockStatement){
          JBlockStatement blockStat=(JBlockStatement)a;
	  return f_BlockStatement(blockStat,ind);
      }else if(a instanceof JReturnStmt){
          JReturnStmt aRet=(JReturnStmt)a;
          return f_ReturnStmt(aRet,ind+4);
      }else if(a instanceof JExpressionStmt){
          JExpressionStmt aExpr=(JExpressionStmt)a;
          return f_ExpressionStmt(aExpr,ind+4);
      }else if(a instanceof JBreakStmt){
          JBreakStmt aBreak=(JBreakStmt)a;
          return f_BreakStmt(aBreak,ind+4);
      }else if(a instanceof JSwitchStmt){
          JSwitchStmt aSwitch=(JSwitchStmt)a;
          return f_SwitchStmt(aSwitch,ind+4);
      }else if(a instanceof JSwitchEntryStmt){
          JSwitchEntryStmt aSwitchEntry=(JSwitchEntryStmt)a;
          return f_SwitchEntryStmt(aSwitchEntry,ind+4);
      }else if(a instanceof JLabeledStmt){
          JLabeledStmt aLbl=(JLabeledStmt)a;
          return f_LabeledStmt(aLbl,ind+4);
      }else if(a instanceof JAssertStmt){
          JAssertStmt aAssert=(JAssertStmt)a;
          return f_AssertStmt(aAssert,ind+4);
      }else if(a instanceof JConstructorInvocationStmt){
          JConstructorInvocationStmt aInvocation=(JConstructorInvocationStmt)a;
          return f_ConstructorInvocationStmt(aInvocation,ind+4);
      }else if(a instanceof JIfStmt){
          JIfStmt aIf=(JIfStmt)a;
          return f_IfElseStmt(aIf,ind+4);
      }else if(a instanceof JWhileStmt){
          JWhileStmt aWhile=(JWhileStmt)a;
          return f_WhileStmt(aWhile,ind+4);
      }else if(a instanceof JDoStmt){
          JDoStmt aDo=(JDoStmt)a;
          return f_DoWhileStmt(aDo,ind+4);
      }else if(a instanceof JTryStmt){
          JTryStmt aTry=(JTryStmt)a;
          return f_TryStmt(aTry,ind+4);
      }
      
      else return "";
  }
  
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_BlockStatement(JBlockStatement a,int ind){
       String result="";
      result=result+f_Statement(a.stmts.getNode(0),ind); 
      for(int i=1;i<a.stmts.count();i++){
          result=result+"\n"+f_Statement(a.stmts.getNode(i),ind);//+"\n";
      }
      return result;
  }
  
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_TryStmt(JTryStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      return String.format(fmtTryStmt,vSpace,f_StatementList(a.stmts,ind+1),vSpace,f_CatchList(a.catches));//f_Expression(a.condition)); 
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_CatchBlock(JCatchBlock a){ // Should be revised 
      if(!a.fText.isEmpty()){
          return a.fText;
      }else return " ";
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_CatchList(Catch_List a){
      String result="";
      result=result+f_CatchBlock(a.getNode(0)); 
      for(int i=1;i<a.count();i++){
          result=result+"\n"+f_CatchBlock(a.getNode(i));//+"\n";
      }
      return result;
  }

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_DoWhileStmt(JDoStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      return String.format(fmtDoWhileStmt,vSpace,f_StatementList(a.body,ind+1),vSpace,f_Expression(a.condition)); 
  }

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_WhileStmt(JWhileStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      return String.format(fmtWhileStmt,vSpace,f_Expression(a.condition),f_StatementList(a.body,ind+1),vSpace); 
  }
  
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_IfElseStmt(JIfStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      String vResult;
      if(a.elseStmt.count()==0){
           vResult= String.format(fmtIfStmt,vSpace,f_Expression(a.condition),f_StatementList(a.thenStmt,ind+1),vSpace,""); 
      }else{
          if(a.elseStmt.getNode(0) instanceof JIfStmt)       
             vResult= String.format(fmtIfStmt,vSpace,f_Expression(a.condition),f_StatementList(a.thenStmt,ind+1),vSpace,f_ElseStatementList(a.elseStmt,ind));
          else vResult= String.format(fmtIfElseStmt,vSpace,f_Expression(a.condition),f_StatementList(a.thenStmt,ind+1),vSpace,f_StatementList(a.elseStmt,ind),vSpace);
      } 
      if(a.defaultStmt!=null){
          vResult=vResult+"else{ "+f_StatementList(a.defaultStmt,ind)+vSpace+'}';
      }
      return vResult;
  }
  
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_IfElseStmt2(JIfStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      String vSpace1=dupeString(blankSpace,1);
      return String.format(fmtIfStmt,vSpace1,f_Expression(a.condition),f_StatementList(a.thenStmt,ind+1),vSpace,f_ElseStatementList(a.elseStmt,ind));
      
  }

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_ConstructorInvocationStmt(JConstructorInvocationStmt a, int ind){
      String vSpace=dupeString(blankSpace,ind);
      return vSpace+a.invocation;
  }

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_AssertStmt(JAssertStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      if(!a.fText.isEmpty())
          return vSpace+a.fText;
      else return String.format(fmtAssertStmt,vSpace,f_Expression(a.check),a.msg); 
  }
  
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_LabeledStmt(JLabeledStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      return String.format(fmtLabeledStmt,vSpace,a.label,f_Statement(a.stmt,ind+4)); 
  }
  
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_SwitchEntryStmtList(SwitchEntryStmt_List a,int ind){
      String result="\n";
      for(int i=0;i<=a.count()-1;i++){
          result=result+f_SwitchEntryStmt(a.getNode(i),ind)+"\n";
      }
      return result;
  }

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_SwitchEntryStmt(JSwitchEntryStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      if(a.label!=null)
         return String.format(fmtSwitchEntryStmt,vSpace,f_Expression(a.label),f_StatementList(a.stmts,ind+1)); 
      else return String.format(fmtSwitchEntryStmt,vSpace,a.fText,f_StatementList(a.stmts,ind+1)); 
  }
  
    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_SwitchStmt(JSwitchStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      if(a.selector!=null)
          return String.format(fmtSwitchStmt,vSpace,f_Expression(a.selector),f_SwitchEntryStmtList(a.entries,ind+4),vSpace); 
      else return String.format(fmtSwitchStmt,vSpace,a.fText,f_SwitchEntryStmtList(a.entries,ind+4),vSpace); 
  }

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_BreakStmt(JBreakStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      return vSpace+a.id+";";
  }

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_ExpressionStmt(JExpressionStmt a,int ind){
      String vSpace=dupeString(blankSpace,ind);
      if(!a.fText.isEmpty())
          return vSpace+a.fText+";";
      else return vSpace+f_Expression(a.expr)+";";
  }

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_ElseStatementList(Statement_List a,int ind){
      String result="";
      for(int i=0;i<=a.count()-1;i++){
          result=result+"else"+f_IfElseStmt2((JIfStmt)a.getNode(i),ind);
      }
      return result;
  }
 /* public String f_SimpleElseList(Statement_List a,int ind){
      String result="\n";
      result=result+"else";
      for(int i=0;i<=a.count()-1;i++){
          result=result+f_Statement(a.getNode(i),ind)+"\n";
      }
      return result;
  }*/

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    
  public String f_StatementList(Statement_List a,int ind){
      String result="\n";
      for(int i=0;i<=a.count()-1;i++){
          result=result+f_Statement(a.getNode(i),ind)+"\n";
      }
      return result;
  }

    /**
     *
     * @param a
     * @param ind
     * @return
     */
    public String f_ReturnStmt(JReturnStmt a, int ind){
      String vSpace=dupeString(blankSpace,ind);
      if(!a.fText.isEmpty())
          return String.format(fmtReturnStmt,vSpace,a.fText); 
      else
      return String.format(fmtReturnStmt,vSpace,f_Expression(a.expr)); 
  }	
    
    /**
     *
     * @param a
     * @return
     */
    public String f_Expression(JExpression a){
      if(a instanceof JNameExpr){
         JNameExpr aName=(JNameExpr)a;
         return f_NameExpr(aName);
      }  if (a instanceof JBinaryExpr){
         JBinaryExpr aBinExpr=(JBinaryExpr)a;
         return f_BinaryExpr(aBinExpr);
      }else if (a instanceof JAssignExpr){
         JAssignExpr aAssignExpr=(JAssignExpr)a;
	 return f_AssignExpr(aAssignExpr);
      }else if(a instanceof JInstanceOfExpr){
         JInstanceOfExpr aInsExpr=(JInstanceOfExpr)a;
         return f_InstanceOfExpr(aInsExpr);
      }else if(a instanceof JCastExpr){
         JCastExpr aCast=(JCastExpr)a;
         return f_CastExpr(aCast);
      }else if(a instanceof JClassExpr){
         JClassExpr aClass=(JClassExpr)a;
         return f_ClassExpr(aClass);
      }else if(a instanceof JFieldAccessExpr){
          JFieldAccessExpr aAcc=(JFieldAccessExpr)a;
          return f_FieldAccessExpr(aAcc);
      }
      
      else return "";
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_FieldAccessExpr(JFieldAccessExpr a){
      return f_Expression(a.object)+"."+a.field;
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_NameExpr(JNameExpr a){
      return a.name;
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_ClassExpr(JClassExpr a){
      return String.format(fmtClassExpr,f_Type(a.type));
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_CastExpr(JCastExpr a){
      return String.format(fmtCastExpr,f_Type(a.type),f_Expression(a.expr));
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_InstanceOfExpr(JInstanceOfExpr a){
      if(!a.fText.isEmpty())
          return a.fText;
      else return String.format(fmtInstanceOfExpr,f_Expression(a.expr),f_Type(a.type));
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_BinaryExpr(JBinaryExpr a){
      if(!a.fText.isEmpty())
          return a.fText;
      else
      return String.format(fmtBinOrAssignExpr,f_Expression(a.left),f_BinaryOperator(a.op),f_Expression(a.right));
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_AssignExpr(JAssignExpr a){
      if(!a.fText.isEmpty())
          return a.fText;
      else return String.format(fmtBinOrAssignExpr,f_Expression(a.target),f_AssignOperator(a.op),f_Expression(a.value));
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_AssignOperator(JAssignExpr.Operator a){
      String result="";
      switch(a){
          case assign:
              result="=";
	      break;
          case plus:
              result="+=";
              break;
          case minus:
              result="-=";
              break;
          case star:
              result="*=";
              break;
          case slash:
              result="/=";
              break;
          case and:
              result="&=";
              break;
          case or:
              result="|=";
              break;
          case xor:
              result="^=";
              break;
          case rem:
              result="%=";
              break;
          case lShift:
              result="<<=";
              break;
          case rSignedShift:
              result=">>=";
              break;
          case rUnsignedShift:
              result=">>>=";
              break;
      } 
      return result;
  }

    /**
     *
     * @param a
     * @return
     */
    public String f_BinaryOperator(JBinaryExpr.Operator a){
		String result="";
		switch(a){
		case and:
                    result="&&";
		    break;
                case binOr:
                    result="|";
                    break;
                case or:
                    result="||";
                    break;        
                case binAnd:
                    result="&";
                    break;
                case xor:
                    result="^";
                    break;
                case equals:
                    result="==";
                    break;
                case notEquals:
                    result="!=";
                    break;
                case less:
                    result="<";
                    break;
                case greater:
                    result=">";
                    break;
                case lessEquals:
                    result="<=";
                    break;
                case greaterEquals:
                    result=">=";
                    break;
                case lShift:
                    result="<<";
                    break;
                case rSignedShift:
                    result=">>";
                    break;
                case rUnsignedShift:
                    result=">>>";
                    break;
                case plus:
                    result="+";
                    break;
                case minus:
                    result="-";
                    break;
                case times:
                    result="*";
                    break;
                case divide:
                    result="/";
                    break;
                case remainder:
                    result="%";
                    break;
                
		}
	return result;
  }
    
    /**
     *
     * @param aBlankSpace
     * @param aNumOfSpaces
     * @return
     */
    public String dupeString(String aBlankSpace,int aNumOfSpaces){
      String vSpace="";
      for(int i=0;i<aNumOfSpaces;i++){
          vSpace=vSpace+aBlankSpace;
      }
      return vSpace;
  }
    
}
