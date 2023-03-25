/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.RegExpr;

import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;


/**
 *
 * @author ssanyu
 */
public class CRE_MultiStick extends CRE {
    private CRE_List fList;

    /**
     *
     * @param aList
     */
    public CRE_MultiStick(CRE_List aList ){
	fList = aList;
    }

    /**
     *
     * @param aList
     */
    protected void setList(CRE_List aList ){
        fList=aList;

    }

    /**
     *
     * @return
     */
    public CRE_List List(){
        return fList;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
          return CGrammarCodes.scREMultiStick;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "REMultiStick";
    }

    /**
     *
     * @return
     */
    @Override
    public int termCount(){
       return 1;
    }
    @Override
    public CTerm getTerm(int i){
       if(i==0)
          return fList;
       else return super.getTerm(i);
    }
    @Override
    public void setTerm(int i,CTerm aTerm){
       if(i==0)
           fList=(CRE_List)aTerm;
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
    public static CRE fromText(String aMultiStick){
        CGrammarScanner vScanner;
        CGrammarParser vParser;
       
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

        //scan and parse char
        vParser.fScanner.setText(aMultiStick);
        vParser.reSet();
        vParser.parseRE();
        return vParser.fRETree;
    }
}
