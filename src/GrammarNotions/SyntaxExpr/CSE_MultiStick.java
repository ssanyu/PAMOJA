/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.SyntaxExpr;

import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;

/**
 *
 * @author ssanyu
 */
public class CSE_MultiStick extends CSE {
    private CSE_List fList;

    /**
     *
     * @param aList
     */
    public CSE_MultiStick(CSE_List  aList ){
	fList = aList;
    }

    /**
     *
     * @param aList
     */
    protected void setList(CSE_List  aList ){
        fList=aList;

    }

    /**
     *
     * @return
     */
    public CSE_List  getList(){
        return fList;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scSEMultiStick;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
            return "SEMultiStick";
    }

    /**
     *
     * @return
     */
    @Override
    public int termCount(){
        return fList.termCount();
    }
     @Override
    public CTerm getTerm(int i){
      return fList.getElt(i);
    }
    @Override
    public void setTerm(int i,CTerm aTerm){
       if(i==0)
           fList=(CSE_List)aTerm;
       else super.setTerm(i,aTerm);
    }
   
    /**
     *
     * @return
     */
    @Override
    public String toText(){
        String result = "( " + fList.getElt(0).toText();
        for (int i = 1; i <= fList.termCount()-1; i++){
            result = result + " | " + fList.getElt(i).toText();
        }
        result = result + " )";
        return result;
    }
  
    public String toString(){
        String result = fList.getElt(0).toString();
        for (int i = 1; i <= fList.termCount()-1; i++){
            result = result + " | " + fList.getElt(i).toString();
        }
       
        return result;
    }
    /**
     *
     * @param aMultiStick
     * @return
     */
    public static CSE fromText(String aMultiStick){
       CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aMultiStick);
        vParser.reSet();
        vParser.parseXSE();
        return vParser.fSETree;
    }
}
