/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.SyntaxExpr;

import GrammarNotions.Grammar.CGrammarCodes;

/**
 *
 * @author ssanyu
 */
public class CTerminalDec extends CSymDec {
    private boolean fHasData;

    /**
     *
     * @param aHasData
     */
    protected void setHasData(boolean aHasData){
            fHasData=aHasData;
    }

    /**
     *
     * @param aName
     * @param aHasData
     */
    public CTerminalDec(String aName,boolean aHasData){
            super(aName);
            fHasData=aHasData;
    }

    /**
     *
     * @return
     */
    public boolean hasData(){
        return fHasData;
    }

    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 2;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i){
    if(i==0)
       return fName;
    else if(i==1)
        return String.valueOf(fHasData); //new Boolean(fHasData).toString();
    else return super.getData(i);
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i,String aData){
        if(i==0)
            fName=aData;
        else if(i==1)
            fHasData= Boolean.parseBoolean(aData);
        else super.setData(i,aData);
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CGrammarCodes.scTerminalDec;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "TerminalDec";
    }


}
