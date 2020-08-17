package Parsers;


import GrammarNotions.ECFGNodes.CECFGNode;
import GrammarNotions.Grammar.CGrammar;
import SymbolStream.CPosition;
import SymbolStream.CSymbolStream;
import java.util.ArrayList;
/**
  * {@code CParser} is the base class for all parsers.
  * Every parser class has {@code CParser} as a superclass. All parsers
  * implement the methods of this class.
  * 
  * @author jssanyu
 */


public abstract class CParser implements IParser{
    /**
     * Reference to an instance of {@code CSymbolStream}.
     */
    protected CSymbolStream fSymbolStream=null;
    /**
     * Stores output of the parser.
     * @see CParserResult
     */
    protected CParserResult fParserResult;
    
    /**
     *
     */
    protected CECFGNode fParseTree;

    /**
     *
     */
    protected boolean parseWithNonTerminal;

    /**
     *
     */
    protected String nonTerminal;

    /**
     *
     * @return
     */
    public boolean isParseWithNonTerminal() {
        return parseWithNonTerminal;
    }

    /**
     *
     * @param parseWithNonTerminal
     */
    public void setParseWithNonTerminal(boolean parseWithNonTerminal) {
        this.parseWithNonTerminal = parseWithNonTerminal;
    }

    /**
     *
     * @return
     */
    public String getNonTerminal() {
        return nonTerminal;
    }

    /**
     *
     * @param nonTerminal
     */
    public void setNonTerminal(String nonTerminal) {
        this.nonTerminal = nonTerminal;
    }

   
    /**
     * Get the value of fParseTree
     *
     * @return the value of fParseTree
     */
    @Override
    public CECFGNode getParseTree() {
        return fParseTree;
    }

    /**
     * Set the value of fParseTree
     *
     * @param fParseTree new value of fParseTree
     */
    @Override
    public void setParseTree(CECFGNode fParseTree) {
        this.fParseTree = fParseTree;
    }

    /**
     * Stores error messages generated during the parsing process.
     * <p>
     * The error list is empty if the parser didn't find any errors.
     */   
    protected ArrayList<CParseError> fParseErrorList;
     /**
     *  Reference to an instance of {@code CGrammar}.
     */
    protected CGrammar fGrammar=null;
    
    /**
     *
     * @return
     */
    public CGrammar getGrammar(){
        return fGrammar;
    }
    
    /**
     *
     * @param aGrammar
     */
    public void setGrammar(CGrammar aGrammar){
        fGrammar=aGrammar;
    } 
    /**
     * Default {@code CParser} constructor.
     */
   
    public CParser(){
        fSymbolStream=new CSymbolStream();
        fParserResult=new CParserResult(false,null);
        fParseErrorList=new ArrayList<>();
        fGrammar=new CGrammar();
    }
        
    //Setters and Getters
    @Override
    public void setParserResult(CParserResult aParserResult){
        fParserResult=aParserResult;
    }

    /**
     *
     * @return
     */
    @Override
    public CParserResult getParserResult() {
        return fParserResult;
    }

    /**
     *
     * @return
     */
    public CSymbolStream getSymbolStream(){
        return fSymbolStream;
    }
    
    /**
     *
     * @param aSymbolStream
     */
    public void setSymbolStream(CSymbolStream aSymbolStream){
        fSymbolStream=aSymbolStream;
    }
    
    /**
     *
     * @return
     */
    @Override
    public ArrayList<CParseError> getParseErrorList(){
        return fParseErrorList;
    }

    /**
     *
     * @param aParseErrorList
     */
    @Override
    public void setParseErrorList(ArrayList<CParseError> aParseErrorList){
        fParseErrorList=aParseErrorList;
    }
    
    @Override
    public void reSet(){
        fParseErrorList.clear();
        fSymbolStream.reSet();
        fParserResult.setNode(null);
        fParserResult.clear();
    }
    @Override
    public void nextSym(){
        fSymbolStream.nextSym();
    }
    
    /**
     *
     * @return
     */
    public int getSym(){
       // if(fSymbolStream.finished()){
         //return -2;
        //}else{
       // assert fSymbolStream!= null && fSymbolStream.symbolCount()> 0 : "SymbolStream is null or empty";
       return fSymbolStream.getSymCode();
         
      }
    
    /**
     *
     * @return
     */
    public String getSymName(){
      // if(fSymbolStream.finished()){
        //  return "endmarker"; 
       //}else 
     return fSymbolStream.getSymName();
    }
    
    /**
     *
     * @param aSym
     * @return
     */
    public String getSymName(int aSym){
       // if(fSymbolStream.finished()){
       //   return "endmarker"; 
     //  }else 
      return fSymbolStream.getSymName(aSym);
    }
    
    /**
     *
     * @return
     */
    public String getSymValue(){
      //  if(fSymbolStream.finished()){
       //   return "0"; 
      // }else 
        
        return fSymbolStream.getSymValue();
    }

    /**
     *
     * @param aMark
     */
    public void reCall(CPosition aMark){
        fSymbolStream.reCall(aMark);
    }
    
    /**
     *
     * @return
     */
    public CPosition getSymPos(){
        return fSymbolStream.getPosition();
    }
  
    /**
     *
     * @param aSym
     * @return
     */
    public boolean hasData(int aSym){
        return fSymbolStream.hasData(aSym);
    }
    
    
    @Override
    public void term(int aSym) throws InvalidTerminalException{
        if(getSym()==aSym){
            nextSym();
        }else{
            throw new InvalidTerminalException(new CParseError("Failed to match a terminal",getSym(),getSymName(),getSymValue(),getSymPos()));
        }
    }
    
    @Override
    public String termData(int aSym) throws InvalidTerminalException{
        String vData;
        if(getSym()==aSym){
            vData=getSymValue();
            nextSym();
        }else throw new InvalidTerminalException(new CParseError("Failed to match a terminal",getSym(),getSymName(),getSymValue(),getSymPos()));
        return vData;
    }
    
    /**
     *
     * @param i
     * @return
     */
    public String getNonTerminalName(int i){
        return fGrammar.getGrammarContext().getNonterminalName(i-fGrammar.getGrammarContext().terminalCount());
    }
    @Override
    public abstract CParserResult parse();
        

    @Override
    public abstract CParserResult parseNonTerminal(String aName);
    
}