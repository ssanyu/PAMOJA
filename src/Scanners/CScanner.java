/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scanners;

import Sets.Alphabet;
import Sets.AlphabetOps;
import SymbolStream.CPosition;
import SymbolStream.CSymKind;
import SymbolStream.CSymbolStream;



/**
 *
 * @author jssanyu
 */
public abstract class CScanner implements IScanner {
    
    /**
     *
     */
    protected String Text="";  //text to be scanned

    /**
     *
     */
    protected int fSymStart;

    /**
     *
     */
    protected int fSymLength;

    /**
     *
     */
    protected int fCharPos; // position of a character in the string

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
    protected String fSymName;

    /**
     *
     */
    protected CSymKind fSymKind;
   //protected int fRow;
   //protected int fCol;

    /**
     *
     */
       protected CPosition fPosition;
   public static Alphabet csLower,

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
    csLetter,csLetDig,

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

    /**
     *
     */
    csAllChars;
   
    /**
     *
     */
    public final char EndMarker='\0';
   
    /**
     *
     */
    protected CSymbolStream fSymbolStream=new CSymbolStream();  // Symbol stream
   
   // Character set constants

    /**
     *
     */
       public static void initCharSets(){

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
            char[] aSpecialChars={'@','.','|','%','&','*','+','?','(','(','_'};
            csSpecialChars=AlphabetOps.enumerated(aSpecialChars);
     // Whitespace characters
            char[] aWhiteSpace={' ','\n','\r','\t','\f'};
            csWhiteSpace=AlphabetOps.enumerated(aWhiteSpace);
      // Set of all Characters
             csAllChars=AlphabetOps.range(' ','~');
     }

    /**
     *
     */
    public CScanner(){
       initCharSets();
       fPosition=new CPosition();
       reSet();
  }

    /**
     *
     */
    protected void nextCh(){
    	if (fCharPos >Text.length()-1)
    	    fChar =EndMarker;
    	else fChar =Text.charAt(fCharPos);
        fPosition.Col++;
        fCharPos++;
        if (fChar=='\n'){
            fPosition.Line++;
            fPosition.Col=0;
        }
  }
 
    /**
     *
     */
    @Override
  public abstract void nextSym();

    /**
     *
     */
    @Override
  public abstract void recover();

    /**
     *
     * @param aText
     */
    @Override
  public void setText(String aText){
        Text=aText;
        reSet();
  }

    /**
     *
     * @return
     */
    public String getText(){
        return Text;
  }
 
    /**
     *
     * @return
     */
    public int getCharPos(){
        return fCharPos;
  }

    /**
     *
     * @return
     */
    @Override
  public String getSymValue(){
    	fSymValue= Text.substring(fSymStart,fSymEnd);
    	return fSymValue;
  }

    /**
     *
     * @return
     */
    @Override
    public String getSymName(){
        return fSymName;
    }
    
    /**
     *
     * @return
     */
    @Override
    public CSymKind getSymKind(){
        return fSymKind;
    }

    /**
     *
     * @return
     */
    @Override
    public int getSymStart(){
        return fSymStart;
    }

    /**
     *
     * @return
     */
    @Override
    public int getSymLength(){
        return fSymLength;
    }

    /**
     *
     * @param aSymStart
     */
    public void setSymStart(int aSymStart){
        fSymStart=aSymStart;
    }
    
    /**
     *
     */
    @Override
    public void reSet(){
       fCharPos=0;
       fPosition.Line=1;
       fPosition.Col=0;
       nextCh();
    }

    /**
     *
     * @param aMark
     */
    @Override
    public void reCallPosition(int aMark){
       fCharPos = aMark;
       nextCh();
       nextSym();
    }

    /**
     *
     * @return
     */
    @Override
    public int markPosition(){
        return fSymStart;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean finished(){
     return fChar==EndMarker;//fChar==EndMarkerfCharPos > Text.length();
    }
    /**
     * @return the fSym
     */
    @Override
    public int getSym() {
        return fSym;
    }

    /**
     *
     * @return
     */
    public char getfChar(){
        return fChar;
    }
    
    /**
     *
     * @return
     */
    @Override
    public CSymbolStream getSymbolStream(){
        return fSymbolStream;
    }
    
    /**
     *
     * @param aSymbolStream
     */
    @Override
    public void setSymbolStream(CSymbolStream aSymbolStream){
        fSymbolStream=aSymbolStream;
    }
   
}
