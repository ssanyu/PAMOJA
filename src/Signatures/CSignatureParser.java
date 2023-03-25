package Signatures;

import GrammarNotions.ScanParse.CScanException;
import Sets.Alphabet;
import Sets.AlphabetOps;

/**
 *
 * @author HP
 */
public class CSignatureParser {

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

    /**
     *
     */
    csUpper,

    /**
     *
     */

    /**
     *
     */
    csDigit,

    /**
     *
     */

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
    csLetDig,

    /**
     *
     */

    /**
     *
     */
    csValidChars,

    /**
     *
     */

    /**
     *
     */
    csWhiteSpace,

    /**
     *
     */

    /**
     *
     */
    csSpecialChars,

    /**
     *
     */

    /**
     *
     */

    /**
     *
     */
    csAllChars;
    
    /**
     *
     */
    public CSortDefinition fSortDefn;
   
    /**
     *
     */
    public CSignatureParser(){
       fSortDefn=null;
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
            char[] aSpecialChars={'\0','@','=','<','C','T','D','R','[',']'
                                  ,':',',','*','?','+',' '};
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
        //System.out.println(fText +", "+fSymStart+","+(fCharPos-1));
    	fSymValue= fText.substring(fSymStart,fCharPos-1);
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
     * @return
     */
    public int getSym() {
	return fSym;
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
        String vData;
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
                fSym=CSignatureCodes.syEndmarker;
                break;
            case '@':
                nextCh();
                fSym=CSignatureCodes.syAttribute;
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
            case '_':
            nextCh();
            while (csLetDig.has(fChar) || fChar=='_'){
                nextCh();
            }
         fSym=CSignatureCodes.syId;
            break; 
            case '*':
                nextCh();
                fSym=CSignatureCodes.syStar;
                break;
            case '+':
                nextCh();
                fSym=CSignatureCodes.syPlus;
                break;
            case '?':
                nextCh();
                fSym=CSignatureCodes.syOption;
                break;
            case '[':
                nextCh();
                fSym=CSignatureCodes.syOpen;
                break;
            case ']':
                nextCh();
                fSym=CSignatureCodes.syClose;
                break;    
            case '=':
                nextCh();
                fSym=CSignatureCodes.syEqual;
                break;  
            case '<':
                nextCh();
                fSym=CSignatureCodes.syAncestor;
                break;  
             case ':':
                nextCh();
                fSym=CSignatureCodes.syColon;
                break;  
             case ',':
                nextCh();
                fSym=CSignatureCodes.syComma;
                break;  
            default: 
                fSym=CSignatureCodes.syError;
                do{
                    nextCh();
                }while(csValidChars.has(fChar));    
                break;
            }
     
      fSymLength=fCharPos-fSymStart-1;
}
   
    //Parser procedures

    /**
     *
     * @return
     */
        protected CSortCtxt signature(){
        CSortDefinition_List vSDList;
        CSortDefinition vSD;
        
        vSDList=new CSortDefinition_List();
        while(getSym()==CSignatureCodes.syId){
             vSD=sortDefinition();
             vSDList.addContext(vSD);
        }
        return new CSortCtxt(vSDList);
    }

    /**
     *
     * @return
     */
    protected CSortDefinition sortDefinition(){
      String vSortName;
      CMemberDefinition_List vMDList;
      String vAncesitorName;
      
      vSortName=termData(CSignatureCodes.syId);
      term(CSignatureCodes.syEqual);
      vMDList=memberPart();
      term(CSignatureCodes.syAncestor);
      vAncesitorName=termData(CSignatureCodes.syId);
      
      return new CSortDefinition(vSortName,
                                 new CSortDefBody(vMDList,
                                                  new CSortUse(vAncesitorName,
                                                               null
                                                              )
                                                 )
                                );
  }
     
    /**
     *
     * @return
     */
    protected CMemberDefinition_List memberPart(){  
       CMemberDefinition_List vResult=new CMemberDefinition_List();
       CMemberDefinition_List vMDList=new CMemberDefinition_List();
       vMDList=memberGroup('C');
       for(int i=0;i<vMDList.contextCount();i++)
               vResult.addContext(vMDList.getElt(i));
       vMDList=memberGroup('T');
       for(int i=0;i<vMDList.contextCount();i++)
               vResult.addContext(vMDList.getElt(i));
       vMDList=memberGroup('D');
       for(int i=0;i<vMDList.contextCount();i++)
               vResult.addContext(vMDList.getElt(i));
       vMDList=memberGroup('R');
       for(int i=0;i<vMDList.contextCount();i++)
               vResult.addContext(vMDList.getElt(i));
       return vResult;
   }
    
    /**
     *
     * @param aRole
     * @return
     */
    protected CMemberDefinition_List memberGroup(char aRole){
       CMemberDefinition_List vMDList;
       CMemberDefinition vMD;
       
       if(getSym()==CSignatureCodes.syId && 
                    getSymValue().equals(Character.toString(aRole))){
           nextSym();
           term(CSignatureCodes.syOpen);
           vMD=memberDef(aRole);
           vMDList=new CMemberDefinition_List();
           vMDList.addContext(vMD);
           while(getSym()==CSignatureCodes.syComma){
               nextSym();
               vMD=memberDef(aRole);
               vMDList.addContext(vMD);
           }
           term(CSignatureCodes.syClose);
       } else vMDList=new CMemberDefinition_List();
       return vMDList;
   }
    
    /**
     *
     * @param aRole
     * @return
     */
    protected CMemberDefinition memberDef(char aRole){
        String vMemberName;
        String vSortName;
        char vModifier;
                
        vMemberName=termData(CSignatureCodes.syId);
        term(CSignatureCodes.syColon);
        vSortName=termData(CSignatureCodes.syId);
        vModifier=modifierOption();
        
        return new CMemberDefinition(vMemberName,
                                     new CMemberDefBody(new CSortUse(vSortName,null),
                                                        vModifier,
                                                        aRole
                                                       )
                                    );
    }

    /**
     *
     * @return
     */
    protected char modifierOption(){
        char vOption;
        int vSym=getSym();
        if(vSym==CSignatureCodes.syStar){
            vOption='*';
            nextSym();
        }else if (vSym==CSignatureCodes.syPlus){
            vOption='+';
            nextSym();
        }else if(vSym==CSignatureCodes.syOption){
            vOption='?';
            nextSym();
        }else vOption=' ';
        return vOption;       
    }
   
    /**
     *
     */
    public void parse() {
       fSortDefn=sortDefinition();
    }

    
    
}