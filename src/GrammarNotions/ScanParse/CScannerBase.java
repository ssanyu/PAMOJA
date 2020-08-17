/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.ScanParse;

import Sets.Alphabet;
import Sets.AlphabetOps;



/**
 *
 * @author ssanyu
 */
public abstract  class CScannerBase implements IScannerBase {

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
    public char fChar;

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
    csLetter,

    /**
     *
     */
    csLetDig,

    /**
     *
     */
    csValidChars,

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
            char[] aSpecialChars={'\0','@','0',',','.','|','%','&','*','+','?','(',')','_','-',' '};
            csSpecialChars=AlphabetOps.enumerated(aSpecialChars);
      // Set of Valid characters
            csValidChars=AlphabetOps.union(csLetDig,csSpecialChars);
     // Whitespace characters
            char[] aWhiteSpace={' ','\n','\r','\t','\f'};
            csWhiteSpace=AlphabetOps.enumerated(aWhiteSpace);
      // Set of all Characters
             csAllChars=AlphabetOps.range(' ','~');
     }

    /**
     *
     */
    public CScannerBase(){
       initCharSets();
        reSet();
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
    	return fSymValue;
    }

    /**
     *
     */
    public void reSet(){
       fCharPos=0; 
        nextCh();
    }
        
    /**
     *
     */
    @Override
    public abstract void nextSym();

    /**
     *
     * @return
     */
    @Override
    public boolean finished(){
     return fCharPos > fText.length();
    }
    /**
     * Sets the integer representation of a symbol.
     * 
     * @param aSym the integer representation of aSym to set.
    */
    public void setSym(int aSym) {
	fSym = aSym;
    }

    /**
      * @return the integer representation of fSym.
     */
   public int getSym() {
	return fSym;
   }

}