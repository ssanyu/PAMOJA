/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SymbolStream;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 *
 * @author ssanyu
 */
public class CSymbolStream implements ISymbolStream {
    // ArrayList of symbols on each line
    private ArrayList<ArrayList<Symbol>> fSymbols;
    private CPosition fPosition;


    //empty stream of tokens

    /**
     *
     */
        public CSymbolStream(){
        fSymbols=new ArrayList<ArrayList<Symbol>>();
        fPosition=new CPosition();
    }

    @Override
    public CPosition getPosition() {
        return fPosition;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<ArrayList<Symbol>> getSymbolStream(){
        return fSymbols;
    }

    @Override
    public int getSymCode() {
       // assert(!fSymbols.isEmpty()):"getSymCode() failed";
        return fSymbols.get(fPosition.Line).get(fPosition.Col).symCode();
    }

    @Override
    public String getSymName() {
      // assert(!finished()):"getSymName() failed";
       return fSymbols.get(fPosition.Line).get(fPosition.Col).symName();
    }
    
    @Override
    public String getSymName(int aSym){
        ArrayList<Symbol> s; 
        Symbol vSymbol;
        String vName="";
        for(int i=0;i<=fSymbols.size()-1;i++){
           s=fSymbols.get(i); 
           for(int j=0;j<=s.size()-1;j++){
               vSymbol=s.get(j);
               if(vSymbol.symCode()==aSym){
                   vName=vSymbol.symName();
               }
           }
        }
       
        return vName;
    }

    @Override
    public String getSymValue() {
      // assert(!finished()):"getSymValue() failed";
       return fSymbols.get(fPosition.Line).get(fPosition.Col).symValue();
    }

    @Override
    public void nextSym() {
      //  assert(!finished()):"Not end-of-stream."+"Cursor at ("+fPosition.Col +","+fPosition.Line+")";
        if(!finished()){
            fPosition.Col++;
            if(fPosition.Col==fSymbols.get(fPosition.Line).size()){
                fPosition.Line++;
                fPosition.Col=0;
            }
        }
        
    }
    @Override
    public void reCall(CPosition aPosition) {
        fPosition=aPosition;
    }
    
    @Override
    public void reSet() {
        fPosition.reSet();
        //System.out.println(fPosition.toText());
    }

    @Override
    public boolean finished() {
       /*int x=fSymbols.get(fSymbols.size()-1).size()-1; //size()-1 of last list
       int y=fSymbols.size()-1;*/
      // return ((fPosition.Line==fSymbols.size()-1)&&(fPosition.Col==((fSymbols.get(fPosition.Line)).size()-1))); 
        return "endmarker".equals(getSymName());
      }

    @Override
    public int symbolCount() {
        int vSymCount=0;
        for(int i=0;i<=fSymbols.size()-1;i++){
           vSymCount=vSymCount + fSymbols.get(i).size(); 
        }
        return vSymCount;    
    }
    
    @Override
    public void addNewRow(){
        fSymbols.add(new ArrayList<Symbol>());
    }
    @Override
    public void addSymbol(Symbol aSymbol){
        fSymbols.get(fSymbols.size()-1).add(aSymbol);
    }
 
    /**
     *
     * @param f
     */
    @Override
    public void addFiller(int f){
        addSymbol(new Symbol(0,0," ","fill",f,CSymKind.FILLER));
    }
    
    /**
     *
     */
    @Override
     public void indent(){
       addSymbol( new Symbol(0,0,"","ind",0,CSymKind.INDENT));
     }
    
    /**
     *
     */
    @Override
     public void unIndent(){
       addSymbol( new Symbol(0,0,"","unInd",0,CSymKind.UNINDENT));
     }
    
    /**
     *
     * @return
     */
    public String toText(){
        String vSymbolStreamText="";
        String vSymName;
        String vSymValue;
        for(  int i=0; i<fSymbols.size();i++){
               for(int j=0;j<fSymbols.get(i).size();j++){
                vSymName=fSymbols.get(i).get(j).symName();
                vSymValue=fSymbols.get(i).get(j).symValue();
                if(vSymValue.isEmpty()){
                    vSymbolStreamText=vSymbolStreamText+"  "+vSymName;
                }else{
                    vSymbolStreamText=vSymbolStreamText+"  "+vSymName+" ["+vSymValue+"]"; 
                }
            }
            vSymbolStreamText=vSymbolStreamText+"\n";
        }
        return vSymbolStreamText;
    }
    
    /**
     *
     * @param aSymbolStreamText
     * @return
     */
    public static CSymbolStream fromText(String aSymbolStreamText){ // Not implemented yet
            CSymbolStream vSymbolStreamStructure=new CSymbolStream();
            BufferedReader br=null;
	    String line=null;
            return vSymbolStreamStructure;
    }

    @Override
    public int getSymLength() {
        //  assert(!finished()):"getSymLength() failed";
          return fSymbols.get(fPosition.Line).get(fPosition.Col).symLength();
    }
   
    @Override
    public int getSymStart() {
         // assert(!finished()):"getSymStart() failed";
          return fSymbols.get(fPosition.Line).get(fPosition.Col).symStart();
    }

    @Override
    public boolean hasData(int aSym) {
     //   assert(!finished()):"hasData() failed";
       return fSymbols.get(fPosition.Line).get(fPosition.Col).hasData();
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getSymbolStreamSize(){
        return fSymbols.size();
    }
}
