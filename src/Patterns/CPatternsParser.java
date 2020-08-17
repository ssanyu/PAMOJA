/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns;

import Boxes.*;
import GrammarNotions.ScanParse.CScanException;
import Parsers.CParseError;
import Parsers.InvalidTerminalException;
import Sets.Alphabet;
import Sets.AlphabetOps;
import SymbolStream.CPosition;

/**
 *
 * @author jssanyu
 */
public class CPatternsParser{

    /**
     *
     */
    protected  int fSymStart;

    /**
     *
     */
    protected int fSymLength;

    /**
     *
     */
    protected int fCharPos;

    /**
     *
     */
    protected char fChar;

    /**
     *
     */
    protected String fSymValue;

    /**
     *
     */
    protected int fSymEnd;

    /**
     *
     */
    protected   int fSym;

    /**
     *
     */
    protected  String fText="";

    /**
     *
     */
    protected boolean fErrors;
   protected static Alphabet csLower,

    /**
     *
     */
    csUpper,

    /**
     *
     */
    csDigit,

    /**
     *
     */
    csLetter,

    /**
     *
     */

    /**
     *
     */
    csLetDig,csValidChars,

    /**
     *
     */
    csWhiteSpace,

    /**
     *
     */
    csSpecialChars,

    /**
     *
     */
    csAllChars;
    
    /**
     *
     */
    public CPatternDef fPatternDef;
   
    /**
     *
     */
    public CPatternsParser(){
       fPatternDef=null;
       initCharSets();
       reSet();
   }
    // Character set constants
   private void initCharSets(){

         // Lower set of characters
            csLower=AlphabetOps.range('a', 'z');

        // Upper set of characters
            csUpper=AlphabetOps.range('A','Z');

       //  Set of digits
            csDigit=AlphabetOps.range('0','9');

        // Set of Letters
            csLetter=AlphabetOps.union(csLower, csUpper);

        // Set of Letters and Digits
            csLetDig=AlphabetOps.union(csLetter,csDigit);
            
                        
        // Set of Special characters   
            char[] aSpecialChars={'\0','@','=','(',')','[',']'
                                  ,',',' '};
            csSpecialChars=AlphabetOps.enumerated(aSpecialChars);
       
        // Set of Valid characters
            csValidChars=AlphabetOps.union(csLetDig,csSpecialChars);
        // Whitespace characters
            char[] aWhiteSpace={' ','\n','\r','\t','\f'};
            csWhiteSpace=AlphabetOps.enumerated(aWhiteSpace);
        // Set of all Characters
             csAllChars=AlphabetOps.range(' ','~');
     }

    private void reSet(){
      fCharPos=0; 
      nextCh();
      nextSym();
      fErrors=false;  // Not used yet. 
    }
    
    /**
     *
     */
    protected void nextCh(){
    	try{
            if (fCharPos >= fText.length())
    		fChar ='\0';
            else fChar =fText.charAt(fCharPos);
        }catch(StringIndexOutOfBoundsException e){
            
        }
        fCharPos=fCharPos + 1;
    }
    
    /**
     *
     * @param aText
     */
    public void setText(String aText){
        fText=aText;
        reSet();
    }

    /**
     *
     * @return
     */
    public String getSymValue(){
        fSymValue= fText.substring(fSymStart,fCharPos-1);
       // System.out.println("From CPatternsParser.getSymValue\n"+fText +", "+fSymStart+","+(fCharPos-1)+ fSymValue);
    	return fSymValue;
    }

    /**
     *
     * @return
     */
    public boolean finished(){
     return fCharPos > fText.length();
    }

    /**
     *
     * @param aSymValue
     * @return
     */
    protected static int resolveLexeme(String aSymValue){
        Integer vSym;
        vSym=(Integer)CPatternsCodes.keywords.get(aSymValue);
        if(vSym!=null)
          return  vSym.intValue();
        else return CPatternsCodes.syName;
    }

    /**
     *
     * @return
     */
    public int getSym(){
      //check whether Identifier or Keyword
        if(fSym==CPatternsCodes.syName){
           getSymValue();
           return fSym=resolveLexeme(fSymValue);
        }else return fSym;
    }

    /**
     *
     * @param aChar
     * @throws CScanException
     */
    protected void checkChar(char aChar)throws CScanException{
     if(fChar==aChar)
         nextCh();
      else throw new CScanException();
    }
    
    /**
     *
     * @param aSym
     */
    protected void term(int aSym){
        if(fSym==aSym)
            nextSym();
        else fErrors=true; //needs reconsideration
    }
    
    /**
     *
     * @param aSym
     * @return
     */
    protected String termData(int aSym){
        String vData="";
        if(fSym==aSym){
            vData=getSymValue();
            nextSym();
        }else{
              vData="";
              fErrors=true;
        }
        return vData;
    }

    /**
     *
     */
    public void nextSym() {
       while(csWhiteSpace.has(fChar)){
            nextCh();
       }
       fSymStart=fCharPos-1;
       switch(fChar){
            case '\0':
                fSym=CPatternsCodes.syEndmarker;
                break;
            case '@':
                nextCh();
                fSym=CPatternsCodes.syAttribute;
                break;
            case 'a': case 'b': case 'c': case 'd': 
       	    case 'e': case 'f': case 'g': case 'h': 
       	    case 'i': case 'j': case 'k': case 'l': 
       	    case 'm': case 'n': case 'o': case 'p': 
       	    case 'q': case 'r': case 's': case 't': 
       	    case 'u': case 'v': case 'w': case 'x': 
       	    case 'y': case 'z': case 'A': case 'B': 
       	    case 'C': case 'D': case 'E': case 'F':
       	    case 'G': case 'H': case 'I': case 'J': 
       	    case 'K': case 'L': case 'M': case 'N': 
       	    case 'O': case 'P': case 'Q': case 'R': 
       	    case 'S': case 'T': case 'U': case 'V': 
       	    case 'W': case 'X': case 'Y': case 'Z': 
            case '_': case '<': 
            nextCh();
            while (csLetDig.has(fChar) || fChar=='_' || fChar=='>'){
                nextCh();
            }
            fSym=CPatternsCodes.syName;
            break; 
                
            case '0': case '1': case '2': case '3': 
       	    case '4': case '5': case '6': case '7':
       	    case '8': case '9': 
            nextCh();
            while (csDigit.has(fChar)) {
                 nextCh();
            }
            fSym=CPatternsCodes.syNumber;
            break; 

            case '(':
                nextCh();
                fSym=CPatternsCodes.syOpen;
                break;
            case ')':
                nextCh();
                fSym=CPatternsCodes.syClose;
                break;    
            case '[':
                nextCh();
                fSym=CPatternsCodes.sySqOpen;
                break;
            case ']':
                nextCh();
                fSym=CPatternsCodes.sySqClose;
                break;    
            case '=':
                nextCh();
                fSym=CPatternsCodes.syEquals;
                break;  
            case ',':
                nextCh();
                fSym=CPatternsCodes.syComma;
                break;  
            default: 
                fSym=CPatternsCodes.syError;
                do{
                    nextCh();
                }while(csValidChars.has(fChar));    
                break;
            }
     
      fSymLength=fCharPos-fSymStart-1;
      
      
}
    //PatternDef=Name@.Equals.Box

    /**
     *
     * @return
     */
         protected CPatternDef PatternDef(){
          String vData;
          CBox vBox;
          vData=termData(CPatternsCodes.syName);
          term(CPatternsCodes.syEquals);
          vBox=Box();
          
          return new CPatternDef(vData,vBox);
    }
    
    /**
     *
     * @return
     */
    protected CBox Box(){
        CBox vBox=null;
        
        if(CPatternsCodes.syTerm == getSym()){
             vBox = BoxTerm();
           
        }else if(CPatternsCodes.syTermData == getSym()){
             vBox = BoxTermData();
           
        }else if(CPatternsCodes.sySel == getSym()){
             vBox = BoxSel();
             
        }else if(CPatternsCodes.syInd == getSym()){
             vBox = BoxInd();
            
        }else if(CPatternsCodes.syNode == getSym()){
             vBox = BoxNode();
             
        }else if(CPatternsCodes.syData == getSym()){
             vBox = BoxData();
             
        }else if(CPatternsCodes.syHor == getSym()){
             vBox = BoxHor();
        }else if(CPatternsCodes.syVer == getSym()){
             vBox = BoxVer();
           
        }else if(CPatternsCodes.syHorSep == getSym()){
             vBox = BoxHorSep();
        }else if(CPatternsCodes.syVerSep == getSym()){
             vBox = BoxVerSep();
         
        }else{ 
            throw new InvalidTerminalException(new CParseError(" Box cannot begin with symbol",getSym(),"",getSymValue(),new CPosition()));
        }
        return vBox;
    }
     
     //BoxTerm=( Term . Open . Name@ . Close )

    /**
     *
     * @return
     */
        protected CBox BoxTerm(){
        String vData;
        term(CPatternsCodes.syTerm);
        term(CPatternsCodes.syOpen);
        vData=termData(CPatternsCodes.syName);
        term(CPatternsCodes.syClose);
        return new CTermBox(vData);
    }
    
     //BoxSel=( Sel . Open . Box . Close )

    /**
     *
     * @return
     */
        protected CBox BoxSel(){
        CBox vBox;
        term(CPatternsCodes.sySel);
        term(CPatternsCodes.syOpen);
        vBox = Box();
        term(CPatternsCodes.syClose);
        return new CSelBox(vBox);
    }
    
    //BoxInd=( Ind . Open . Box . Close )

    /**
     *
     * @return
     */
        protected CBox BoxInd(){
        CBox vBox;
        term(CPatternsCodes.syInd);
        term(CPatternsCodes.syOpen);
        vBox = Box();
        term(CPatternsCodes.syClose);
        return new CIndBox(vBox);
    }
    
    //BoxNode=Node.Open.Number@.Close

    /**
     *
     * @return
     */
         protected CBox BoxNode(){
        String vData;
        term(CPatternsCodes.syNode);
        term(CPatternsCodes.syOpen);
        vData=termData(CPatternsCodes.syNumber);
        term(CPatternsCodes.syClose);
        return new CArgNodeBox(Integer.parseInt(vData));
    }
    //BoxTermData=TermData.Open.Name@.Comma.Name@.Close 

    /**
     *
     * @return
     */
          protected CBox BoxTermData(){
        String vData1,vData2;
        term(CPatternsCodes.syTermData);
        term(CPatternsCodes.syOpen);
        vData1=termData(CPatternsCodes.syName);
        term(CPatternsCodes.syComma);
        vData2=termData(CPatternsCodes.syName);
        term(CPatternsCodes.syClose);
        return new CTermDataBox(vData1,vData2);
    }
     
    //BoxData=( Data . Open . Name@ . Comma . Number@ . Close )

    /**
     *
     * @return
     */
        protected CBox BoxData(){
        String vData1,vData2;
        term(CPatternsCodes.syData);
        term(CPatternsCodes.syOpen);
        vData1=termData(CPatternsCodes.syName);
        term(CPatternsCodes.syComma);
        vData2=termData(CPatternsCodes.syNumber);
        term(CPatternsCodes.syClose);
        return new CArgDataBox(vData1,Integer.parseInt(vData2));
    }
    
    //BoxHor=( Hor . Open . SqOpen . Box_List . SqClose . Comma . Number@ . Close )

    /**
     *
     * @return
     */
        protected CBox BoxHor(){
        CBox_List vBoxList;
        String vData;
        term(CPatternsCodes.syHor);
        term(CPatternsCodes.syOpen);
        term(CPatternsCodes.sySqOpen);
        vBoxList = Box_List();
        term(CPatternsCodes.sySqClose);
        term(CPatternsCodes.syComma);
        vData=termData(CPatternsCodes.syNumber);
        term(CPatternsCodes.syClose);
        return new CHorBox(vBoxList,Integer.parseInt(vData));
    }
    //BoxVer=( Ver . Open . SqOpen . Box_List . SqClose . Comma . Number@ . Close )

    /**
     *
     * @return
     */
        protected CBox BoxVer(){
        CBox_List vBoxList;
        String vData;
        term(CPatternsCodes.syVer);
        term(CPatternsCodes.syOpen);
        term(CPatternsCodes.sySqOpen);
        vBoxList = Box_List();
        term(CPatternsCodes.sySqClose);
        term(CPatternsCodes.syComma);
        vData=termData(CPatternsCodes.syNumber);
        term(CPatternsCodes.syClose);
        return new CVerBox(vBoxList,Integer.parseInt(vData));
    }

    //BoxHorSep=( HorSep . Open . SSS . Comma . SSS . Comma . SSS . Close )

    /**
     *
     * @return
     */
        protected CBox BoxHorSep(){
        CBox  vBox1, vBox2,vBox3;
        
        term(CPatternsCodes.syHorSep);
        term(CPatternsCodes.syOpen);
        vBox1 =  SSS();
        term(CPatternsCodes.syComma);
        vBox2 =  SSS();
        term(CPatternsCodes.syComma);
        vBox3 =  SSS();
        term(CPatternsCodes.syClose);
        return new CHorSepListBox((CTermBox)vBox1,(CTermBox)vBox2,(CTermBox)vBox3);
    }
      

    //BoxVerSep=( VerSep . Open . SSS . Comma . SSS . Comma . SSS . Close )

    /**
     *
     * @return
     */
        protected CBox BoxVerSep(){
        CBox vBox1, vBox2,vBox3;
        term(CPatternsCodes.syVerSep);
        term(CPatternsCodes.syOpen);
        vBox1 = SSS();
        term(CPatternsCodes.syComma);
        vBox2 = SSS();
        term(CPatternsCodes.syComma);
        vBox3 = SSS();
        term(CPatternsCodes.syClose);
        return new CVerSepListBox((CTermBox)vBox1,(CTermBox)vBox2,(CTermBox)vBox3);
    }
    
    //SSS=BoxTerm?
 
    /**
     *
     * @return
     */
         protected CBox SSS(){ 
         CBox vBox;
         if(CPatternsCodes.syTerm == getSym()){
              vBox = BoxTerm();
              return vBox;
         }else{
             return new CTermBox("");
         }
     }
    //Box_List=(Box%Comma)

    /**
     *
     * @return
     */
        protected CBox_List Box_List(){
        CBox vBox;
        CBox_List vBoxList = new CBox_List();
        vBox = Box();
        vBoxList.add(vBox);
        while(getSym() == CPatternsCodes.syComma){
             nextSym();
             vBox = Box();
             vBoxList.add(vBox);
        }
        return vBoxList;
    }
    
    /**
     *
     */
    public void parse() {
       fPatternDef=PatternDef();
    } 
}
