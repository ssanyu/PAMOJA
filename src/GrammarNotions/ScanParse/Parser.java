/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.ScanParse;

/**
 *
 * @author ssanyu
 */
public abstract class Parser {

    /**
     *
     */
    public CScannerBase fScanner;

    /**
     *
     */
    public boolean fErrors;
    
    /**
     *
     */
    protected void nextSym(){
        fScanner.nextSym();
    }

    /**
     *
     * @return
     */
    protected int getSym(){
        return fScanner.fSym;
    }

    /**
     *
     * @return
     */
    protected int getSymPos(){
        return fScanner.fSymStart;
    }

    /**
     *
     */
    public Parser(){ }

    /**
     *
     */
    public void reSet(){
      fScanner.reSet();
      nextSym();
    }

    /**
     *
     * @param aSym
     */
    protected void term(int aSym){

        if(getSym()==aSym)
            fScanner.nextSym();
        else fErrors=true; //needs reconsideration
    }

    /**
     *
     * @param aSym
     * @return
     */
    protected String termData(int aSym){
        String vData="";
        if(getSym()==aSym){
            vData=fScanner.getSymValue();
            fScanner.nextSym();
        }else{
              vData="";
              fErrors=true;
        }
        return vData;
    }
   
    /**
     *
     */
    public  abstract void parse();

    

}
