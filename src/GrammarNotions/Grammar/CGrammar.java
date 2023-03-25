/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Grammar;

import GrammarNotions.Context_Terms.CContext;
import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Context_Terms.CTermTuple;
import GrammarNotions.RegExpr.CLexemeDef;
import GrammarNotions.RegExpr.CLexemeDef_List;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.RegExpr.CRE_Empty;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;
import GrammarNotions.SyntaxExpr.*;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author ssanyu
 */
public class CGrammar extends CTermTuple{

    /**
     *
     */
    protected CGrammarContext fSymbolDefs;
    /**
     *
     */
    protected CSE fStart;

    /**
     *
     */
    protected boolean fAugmented;
   
    /**
     *
     * @param aSymbolDefs
     */
    protected void setGrammarContext(CGrammarContext aSymbolDefs){
        fSymbolDefs=aSymbolDefs;
    }

    /**
     *
     * @param aStart
     */
    protected void setStartExpr(CSE aStart){
        fStart=aStart;
    }

    /**
     *
     * @param Aaug
     */
    public void setAugmented(boolean Aaug){
        if(Aaug==true){
            if(!fAugmented){
                augmentGrammar();
            }
        }else {
            if(fAugmented){
               unAugmentGrammar(); 
            }
        }
    }

    /**
     *
     * @return
     */
    protected boolean isAugmented(){
        return fAugmented;
    }
    
    /**
     *
     * @return
     */
    public CSE getStartExpr(){
        return fStart;
    }
    
    //smallest ECFG

    /**
     *
     */
        public CGrammar(){
        fSymbolDefs=new CGrammarContext();
        fStart=new CSE_Empty();
       }
    
    /**
     *
     * @param aSymbolDefs
     * @param aStart
     */
    public CGrammar(CGrammarContext aSymbolDefs,CSE aStart){
        fSymbolDefs=aSymbolDefs;
        fStart=aStart;
        
    }
    
    /**
     *
     * @return
     */
    public CGrammarContext getGrammarContext(){
        return fSymbolDefs;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int contextCount() {
        return 1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CContext getContext(int i) {
        if(i==0)
            return fSymbolDefs;
        else return super.getContext(i);
    }
    @Override
    public void setContext(int i, CContext aContext) {
       if(i==0)
           fSymbolDefs=(CGrammarContext)aContext;
       else super.setContext(i,aContext);
    }

    /**
     *
     * @return
     */
    @Override
      public int termCount() {
        return 1;
    }
    @Override
    public CTerm getTerm(int i) {
        if(i==0)
            return fStart;
        else return super.getTerm(i);
    }

    @Override
    public void setTerm(int i, CTerm aTerm) {
       if(i==0)
           fStart=(CSE)aTerm;
       else super.setTerm(i,aTerm);
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
           return CGrammarCodes.scGrammar;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "Grammar";
    }

    /**
     *
     * @param aLanguageText
     * @return
     */
    public static CGrammar fromText(String aLanguageText){
        CGrammar vLanguageStructure=null;
        String vStartExpr;
        CSE vStart;
        boolean vSuccess;
        boolean vValidGrammarText=true;
        BufferedReader vReader;
        CGrammarScanner vScanner;
        CGrammarParser vParser;

        try{
             vReader=new BufferedReader(new StringReader(aLanguageText));
             GrammarReader.readIniSections(vReader);
        }catch (Exception e) {
  	     JOptionPane.showMessageDialog(null, "Not a valid grammar specification");
             vValidGrammarText=false;
        } 
        
        if(vValidGrammarText){
            vScanner=new CGrammarScanner();
            vParser=new CGrammarParser();
            vParser.fScanner=vScanner;

            CGrammarContext vGrammarContext=readGrammarContext();
            //read start expr 
            vStart=new CSE();
            
            HashMap<String,String> map=GrammarReader.getSection("Start");
            if(map!=null){
                vStartExpr=(String)map.get("startexpr");
                //scan and parse start expr
                vParser.fScanner.setText(vStartExpr);
                vParser.reSet();
                vSuccess=vParser.parseXSE();
                if(vSuccess){
                    vStart=vParser.fSETree;
                }else{
                    JOptionPane.showMessageDialog(null,"Error in parsing the definition of Start expression.");
                    vStart=new CSE_Empty();
                }
             }
            vLanguageStructure=new CGrammar(vGrammarContext,vStart);
        }
        return vLanguageStructure;
        
    }

    /**
     *
     * @return
     */
    public static CGrammarContext readGrammarContext(){

        CGrammarContext vGrammarContext=new CGrammarContext(readLexemeDefs(),readTerminalDefs(),readNonTerminalDefs());
        return vGrammarContext;
    }

    /**
     *
     * @return
     */
    public static CLexemeDef_List readLexemeDefs(){
       CLexemeDef_List vList = new CLexemeDef_List();
       HashMap<String,String> vMap;
       String vValue;
       boolean vSuccess;
       CRE vRE;
       CGrammarScanner vScanner;
       CGrammarParser vParser;

       vScanner=new CGrammarScanner();
       vParser=new CGrammarParser();
       vParser.fScanner=vScanner;
       
      // vMap=new HashMap<String,String>();
       vMap=GrammarReader.getSection("Lexemes");
       if(vMap!=null){
            Object [] values = vMap.keySet().toArray();
            for (int i = 0; i < values.length; i++) {
                    vValue=vMap.get((String)values[i]);
                    vParser.fScanner.setText(vValue);
                    vParser.reSet();
                    vSuccess=vParser.parseRE();
                    if(vSuccess){
                       vRE=vParser.fRETree;
                    }else{
                       JOptionPane.showMessageDialog(null,"Error in parsing the definition of lexeme:"+(String)values[i]);
                       vRE=new CRE_Empty();
                    }
                    vList.add(new CLexemeDef((String)values[i],vRE));
            }
            
       }
       return vList;
    }

    /**
     *
     * @return
     */
    public static CTerminalDef_List readTerminalDefs(){
       boolean vHasData;   // for terminal names of the form abc@
       boolean vSuccess;
       CRE vRE;
       CTerminalDef_List vList = new CTerminalDef_List();
       HashMap<String,String> vMap;
       String vName,vValue;
       CGrammarScanner vScanner;
       CGrammarParser vParser;

        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

       vMap=GrammarReader.getSection("Terminals");
       if(vMap!=null){  //read the name part, check for '@' ("has data") and remove
            Object [] values = vMap.keySet().toArray();
            for (int i = 0; i < values.length; i++) {
                vName=(String)values[i];
                vHasData=vName.charAt(vName.length()-1)=='@';
                if(vHasData){
                    vName=vName.substring(0,vName.length()-1);
                }
                vValue=vMap.get((String)values[i]);
                vParser.fScanner.setText(vValue);
                vParser.reSet();
                vSuccess=vParser.parseRE();
                if(vSuccess){
                       vRE=vParser.fRETree;
                       
                }else{
                       JOptionPane.showMessageDialog(null,"Error in parsing the definition of terminal:"+vName);
                       vRE=new CRE_Empty();
                }
                vList.add(new CTerminalDef(vName,vHasData,vRE));
            }
       }
       return vList;
    }

    /**
     *
     * @return
     */
    public static CNonTerminalDef_List readNonTerminalDefs(){
       CNonTerminalDef_List vList = new CNonTerminalDef_List();
       HashMap<String,String> vMap;
       String vValue;
       CGrammarScanner vScanner;
       CGrammarParser vParser;
       boolean vSuccess;
       CSE vSE;
       
        vScanner=new CGrammarScanner();
        vParser=new CGrammarParser();
        vParser.fScanner=vScanner;

       vMap=GrammarReader.getSection("Nonterminals");
       if(vMap!=null){
            Object [] values = vMap.keySet().toArray();
            for (int i = 0; i < values.length; i++) {
                vValue=vMap.get((String)values[i]);
                vParser.fScanner.setText(vValue);
                vParser.reSet();
                vSuccess=vParser.parseXSE();
                if(vSuccess){
                       vSE=vParser.fSETree;
                }else{
                       JOptionPane.showMessageDialog(null,"Error in parsing the definition of nonterminal:"+(String)values[i]);
                       vSE=new CSE_Empty();
                }
                vList.add(new CNonTerminalDef((String)values[i],vSE));
                
            }
       }
       return vList;
    }
    
    /**
     *
     * @return
     */
    public String toText(){
        CTerminalDef vTerminalDef ;
        StringBuilder vString=new StringBuilder();
        vString.append("[Lexemes]\n");
        for(int i=0;i<=fSymbolDefs.getLexemeDefs().count()-1;i++){
            vString.append(fSymbolDefs.getLexemeDefs().getElt(i).getName()).append('=').append(fSymbolDefs.getLexemeDefs().getElt(i).getBody().toText()).append("\n");
        }
        vString.append("\n[Terminals]\n");
        for(int i=0;i<=fSymbolDefs.getTerminalDefs().count()-1;i++){
            vTerminalDef=fSymbolDefs.getTerminalDefs().getElt(i);
            if(vTerminalDef.hasData()){
                   vString.append(vTerminalDef.getName()).append('@').append('=').append(vTerminalDef.getBody().toText()).append("\n");
            }else{
                   vString.append(vTerminalDef.getName()).append('=').append(vTerminalDef.getBody().toText()).append("\n");
            }
        }
        vString.append("\n[Nonterminals]\n");
        for(int i=0;i<=fSymbolDefs.getNonTerminalDefs().count()-1;i++){
            vString.append(fSymbolDefs.getNonTerminalDefs().getElt(i).getName()).append('=').append(fSymbolDefs.getNonTerminalDefs().getElt(i).getBody().toText()).append("\n");
        }
        vString.append("\n[Start]\n");
        vString.append("startexpr").append("=").append(fStart.toText());
      
        return vString.toString();
    }

    /**
     *
     */
    public void augmentGrammar(){
      CTerminalDef vEndmarker;
      CSE_List vList; 
      
      if(!isAugmented()){
            if(!(fStart instanceof CSE_MultiDot)){
                //add "EndMarker" to the set of terminals
                vEndmarker=new CTerminalDef("endmarker",false,new CRE_Empty());
                fSymbolDefs.getTerminalDefs().addContext(vEndmarker);
                //Modify Start Expression to "$.EndMarker"
                vList=new CSE_List();
                vList.add(fStart);
                vList.add(new CSE_Sym(vEndmarker.getName(),vEndmarker));
                fStart=new CSE_MultiDot(vList);
            }
            fAugmented=true;
        }
    }
   
    /**
     *
     */
    public void unAugmentGrammar(){
       int vIndex;
       CSE_List vList;
       CSE vExpr;
       
       if(isAugmented() && fStart instanceof CSE_MultiDot ){
            // remove the terminal "Endmarker"
            vIndex=fSymbolDefs.getTerminalDefs().count()-1;
            fSymbolDefs.getTerminalDefs().delete(vIndex);
            fSymbolDefs.reNumber();
            
            //take first subexpression of old fStart: CSE_MultiDot as new fStart
            CSE_MultiDot vDot=(CSE_MultiDot)fStart;
            vList=vDot.getList();
            vExpr=(CSE)vList.getTerm(0);
            vList.delete(0);
            fStart=vExpr;
            fAugmented=false;
       
       }
   }
   
    /**
     *
     * @param aSE
     * @return
     */
    public String getName(CSE aSE){
       CSE_Sym vSym;
       vSym=(CSE_Sym)aSE;
       String name=vSym.getName();
       return name;
   }
   
}
