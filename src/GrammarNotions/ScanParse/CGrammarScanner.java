/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrammarNotions.ScanParse;

/**
 *
 * @author jssanyu
 */
public class CGrammarScanner extends CScannerBase {
    
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

    @Override
    public void nextSym() {
         while(csWhiteSpace.has(fChar)){
            nextCh();
       }
       fSymStart=fCharPos-1;
       try{
       switch(fChar){
            case '\0':
                setSym(Symbols.syEndMarker);
                break;
            case '@':
                nextCh();
                setSym(Symbols.syAttribute);
                break;
            case '$':
                nextCh();
                setSym(Symbols.syEmpty);
                break;     
             case '~':
                nextCh();
                setSym(Symbols.syEps);
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
            setSym(Symbols.syId);
            break; 
                
            case '\'':
                nextCh();
                if(fChar!='\''){
                    nextCh();
                }
                else throw new CScanException();
                checkChar('\'');
                setSym(Symbols.syChar);
                break;
                
            case '"':
            	nextCh();
            	while(fChar!='"'){
            	   nextCh();
            	}
            	checkChar('"');
                setSym(Symbols.syString);
               break;
            case '-':
                nextCh();
                setSym(Symbols.syUpto);
                break;
            case '.':
                nextCh();
                setSym(Symbols.syDot);
                break;
            case '|':
                nextCh();
                setSym(Symbols.syStick);
                break;
            case '*':
                nextCh();
                setSym(Symbols.syStar);
                break;
            case '+':
                nextCh();
                setSym(Symbols.syPlus);
                break;
            case '?':
                nextCh();
                setSym(Symbols.syOption);
                break;
            case '(':
                nextCh();
                setSym(Symbols.syOpen);
                break;
            case ')':
                nextCh();
                setSym(Symbols.syClose);
                break;    
            case '%':
                nextCh();
                setSym(Symbols.syAlt);
                break;  
            case '&':
                nextCh();
                setSym(Symbols.syAlt2);
                break;  
            default: 
                setSym(Symbols.syError);
                do{
                    nextCh();
                }while(csValidChars.has(fChar));    
                break;
            }
     }catch(CScanException e){
           System.err.println(e.handleScanError(ErrorMessages.seWrongChar,fChar,fCharPos));
     }
      fSymLength=fCharPos-fSymStart-1;
}
}
