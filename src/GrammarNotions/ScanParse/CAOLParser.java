/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrammarNotions.ScanParse;

import GrammarNotions.RegExpr.CRE;
import GrammarNotions.RegExpr.CRE_Char;
import GrammarNotions.RegExpr.CRE_Empty;
import GrammarNotions.RegExpr.CRE_Eps;
import GrammarNotions.RegExpr.CRE_Lexeme;
import GrammarNotions.RegExpr.CRE_List;
import GrammarNotions.RegExpr.CRE_MultiDot;
import GrammarNotions.RegExpr.CRE_MultiStick;
import GrammarNotions.RegExpr.CRE_Option;
import GrammarNotions.RegExpr.CRE_Plus;
import GrammarNotions.RegExpr.CRE_Range;
import GrammarNotions.RegExpr.CRE_Star;
import GrammarNotions.RegExpr.CRE_String;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSE_Alt;
import GrammarNotions.SyntaxExpr.CSE_Alt2;
import GrammarNotions.SyntaxExpr.CSE_Empty;
import GrammarNotions.SyntaxExpr.CSE_Eps;
import GrammarNotions.SyntaxExpr.CSE_List;
import GrammarNotions.SyntaxExpr.CSE_MultiDot;
import GrammarNotions.SyntaxExpr.CSE_MultiStick;
import GrammarNotions.SyntaxExpr.CSE_Option;
import GrammarNotions.SyntaxExpr.CSE_Plus;
import GrammarNotions.SyntaxExpr.CSE_Star;
import GrammarNotions.SyntaxExpr.CSE_Sym;



/**
 *
 * @author jssanyu
 */
public class CAOLParser extends Parser {
      
    /**
     *
     */
    public CRE fRETree;

    /**
     *
     */
    public CSE fSETree;
   // public boolean fErrors;
    
    /**
     *
     */
    public CAOLParser(){
    	fRETree=null;
        fSETree=null;
    }

    /**
     *
     */
    @Override
    public void reSet(){
        super.reSet();
        fErrors=false;
    }
    
    /**
     *
     * @return
     */
    protected CRE p_Empty(){
        CRE_Empty aEmpty=null;
        switch(fScanner.getSym()){
            case Symbols.syEmpty:
                 nextSym();
                 aEmpty=new CRE_Empty();
                 break;
                default: fErrors=true;
                break;
         }
      
        return aEmpty;
    }

    /**
     *
     * @return
     */
    protected CRE_Eps p_Eps(){
        CRE_Eps aEps=null;
        switch(fScanner.getSym()){
           case Symbols.syEps:
                nextSym();
                aEps=new CRE_Eps();
                break;
        default: fErrors=true;
            break;
        }   
        return aEps;
    }

    /**
     *
     * @return
     */
    protected CRE p_Id(){
            String vName;
            CRE_Lexeme aId=null;
            switch(fScanner.getSym()){
               case Symbols.syId:
                   vName=fScanner.getSymValue();
                   nextSym();
                   aId=new CRE_Lexeme(null,vName);
                   break;
               default:  fErrors=true;
               break;
            }
            return aId;
    }

    /**
     *
     * @return
     */
    protected CRE p_CharOrRange(){
         char vChar1,vChar2=' ';
         CRE aNode=null;
         switch(fScanner.getSym()){
             case Symbols.syChar:
                  vChar1=fScanner.getSymValue().charAt(1);
                  nextSym();
                  if(fScanner.getSym()==Symbols.syUpto){
                      nextSym();
                      if(fScanner.getSym()==Symbols.syChar){
                          vChar2=fScanner.getSymValue().charAt(1);
                          nextSym();
                      }else{
                          fErrors=true;  
                      }
                  CRE_Range aRange =new CRE_Range(vChar1,vChar2);
                  aNode=aRange;
                  }else {
                      CRE_Char aChar=new CRE_Char(vChar1);
                      aNode=aChar;
                  }
                  break;
              default: fErrors=true;
                    break;
          }
         
         return aNode;
    }

    /**
     *
     * @return
     */
    protected CRE_String p_String(){
            String vName;
            CRE_String aString=null;
            switch(fScanner.getSym()){
                  case Symbols.syString:
                       vName=fScanner.getSymValue();
                       vName=vName.substring(1,vName.length()-1);
                       nextSym();
                       aString=new CRE_String(vName);
                       break;
                   default: fErrors=true;
                   break;
            }
            return aString;
    }

    /**
     *
     * @return
     */
    protected CRE p_Expr1(){
        CRE vRE;
        CRE_List vList = new CRE_List();
        
        vRE=p_Expr2();
        if(fScanner.getSym()==Symbols.syStick){
           vList.add(vRE);
           while(fScanner.getSym()==Symbols.syStick){
               fScanner.nextSym();
               vRE=p_Expr2();
               vList.add(vRE);
           }
           CRE_MultiStick aMStick=new  CRE_MultiStick(vList);
           return aMStick;
        }
        else
          return vRE;
    }

    /**
     *
     * @return
     */
    protected CRE p_Expr2(){
        CRE vRE;
        CRE_List vList = new CRE_List();

        vRE=p_Expr3();
        if(fScanner.getSym()==Symbols.syDot){
            vList.add(vRE);
            while(fScanner.getSym()==Symbols.syDot){
                fScanner.nextSym();
                vRE=p_Expr3();
                vList.add(vRE);
            }
            CRE_MultiDot aMDot=new CRE_MultiDot(vList);
            return aMDot;
        }
        else
           return vRE;
    }

    /**
     *
     * @return
     */
    protected CRE p_Expr3(){
         CRE aNode;
         aNode=p_Expr4();
           switch(fScanner.getSym()){
                case Symbols.syStar:
                    nextSym();
                    CRE_Star aStar =new CRE_Star(aNode);
                    aNode=aStar;
                    break;
                case Symbols.syPlus:
                    nextSym();
                    CRE_Plus aPlus =new CRE_Plus(aNode);
                    aNode=aPlus;
                    break;
                case Symbols.syOption:
                    nextSym();
                    CRE_Option aOption=new CRE_Option(aNode);
                    aNode=aOption;
                break;
                
             default: break;
           }
         return aNode;
    }

    /**
     *
     * @return
     */
    protected CRE p_Expr4(){
        CRE aNode=null;
        switch(fScanner.getSym()){
                case Symbols.syEmpty:
                     aNode=p_Empty();
                     break;
                case Symbols.syEps:
                     aNode=p_Eps();
                     break;
                case Symbols.syId:
                     aNode=p_Id();
                     break;
                case Symbols.syChar:
                     aNode=p_CharOrRange();
                     break;
                case Symbols.syString:
                     aNode=p_String();
                     break;
                case Symbols.syOpen:
                     nextSym();
                     aNode=p_Expr1();
                     term(Symbols.syClose);
                     break;
            default: fErrors=true;
            break;
          }
          return aNode;
    }

    /**
     *
     * @return
     */
    public boolean parseRE(){
        CRE vRE=null;
        vRE=p_Expr1();
        if(fErrors){
           fRETree=null;
           return false;
        }else{
            fRETree=vRE;
            return true;
        }
       
      }
    
    // procedures for parsing syntax expressions

    /**
     *
     * @return
     */
        protected CSE ps_Empty(){
        CSE aEmpty=null;
        switch(fScanner.getSym()){
              case Symbols.syEmpty:
                  nextSym();
                  aEmpty=new CSE_Empty();
                  break;
                default: fErrors=true;
                  break;
        }
        return aEmpty;
    }

    /**
     *
     * @return
     */
    protected CSE ps_Eps(){
        CSE aEps=null;
        switch(fScanner.getSym()){
                case Symbols.syEps:
                    nextSym();
                    aEps=new CSE_Eps();
                     break;
                default: fErrors=true;
                 break;
        }
        return aEps;
    }

    /**
     *
     * @return
     */
    protected CSE ps_Id(){
            String vName;
            CSE aId=null;
            switch(fScanner.getSym()){
                    case Symbols.syId:
                	vName=fScanner.getSymValue();
                	nextSym();
                        aId=new CSE_Sym(vName,null);
                    break;
                    default:  fErrors=true;
                    break;
            }
            return aId;
    }
    
    // Expr_1=(stick.Expr_2)+

    /**
     *
     * @return
     */
        protected CSE ps_Expr1(){
        CSE vSE;
        CSE_List vList;

	term(Symbols.syStick);
        vSE=ps_Expr2();
        //vSE=ps_Expr4();
	vList = new CSE_List();
	vList.addTerm(vSE);
	while(fScanner.getSym()==Symbols.syStick){
		fScanner.nextSym();
		vSE=ps_Expr2();
                // vSE=ps_Expr4();
		vList.addTerm(vSE);
	}
	return new CSE_MultiStick(vList);
        
	}

   

    // Expr_2=(dot.Expr_3)+

    /**
     *
     * @return
     */
        protected CSE ps_Expr2(){
        CSE vSE;
        CSE_List vList;

		term(Symbols.syDot);
		vSE=ps_Expr3();
               //  vSE=ps_Expr4();
		vList = new CSE_List();
		vList.addTerm(vSE);
		while(fScanner.getSym()==Symbols.syDot){
			fScanner.nextSym();
			vSE=ps_Expr3();
                        // vSE=ps_Expr4();
			vList.addTerm(vSE);
		}
		return new CSE_MultiDot(vList);
	}

    /**
     *
     * @return
     */
    protected CSE ps_Expr3(){
         CSE aSE;
         CSE vSE;
         aSE=ps_Expr4();
           switch(fScanner.getSym()){
                case Symbols.syStar:
                    nextSym();
                    aSE =new CSE_Star(aSE);
                    break;
                case Symbols.syPlus:
                    nextSym();
                    aSE =new CSE_Plus(aSE);
                    break;
                case Symbols.syOption:
                    nextSym();
                    aSE=new CSE_Option(aSE);
                    break;
                case Symbols.syAlt:
                    nextSym();
                    vSE=ps_Expr4();
                    aSE=new CSE_Alt(aSE,vSE);
                    break;
                case Symbols.syAlt2:
                    nextSym();
                    vSE=ps_Expr4();
                    aSE=new CSE_Alt2(aSE,vSE);
                    break;
                default: break;
           }
         return aSE;
    }

    /**
     *
     * @return
     */
    protected CSE ps_Expr4(){
        CSE aSE=null;
        switch(fScanner.getSym()){
                case Symbols.syEmpty:
                     aSE=ps_Empty();
                     break;
                case Symbols.syEps:
                     aSE=ps_Eps();
                     break;
                case Symbols.syId:
                     aSE=ps_Id();
                     if(fScanner.fSym==Symbols.syAttribute){
                         nextSym();
                     }
                     break;
                case Symbols.syOpen:
                     nextSym();
                     aSE=ps_Expr1();
                     term(Symbols.syClose);
                     break;
            default: fErrors=true;
            break;
       }
        return aSE;
    }
   
    /**
     *
     * @return
     */
    public boolean parseSE(){
        CSE vSE=null;
        vSE=ps_Expr1();
        if(fErrors){
           fSETree=null;
           return false;
        }else{
           fSETree=vSE;
           return true;
        }
        
    }

    /**
     *
     */
    @Override
    public void parse() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
