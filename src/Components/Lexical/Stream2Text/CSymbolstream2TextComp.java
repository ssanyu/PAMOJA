/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Lexical.Stream2Text;


import Components.Abstract.BoxTree2Stream.IBoxTree2StreamComp;
import Components.CPAMOJAComponent;
import Components.Concrete.Grammar.IGrammarComp;
import Components.Lexical.SymbolStream.ISymbolStreamComp;
import GrammarNotions.SyntaxExpr.CTerminalDef_List;
import SymbolStream.CSymbolStream;
import SymbolStream.Symbol;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


/**
 * An implementation of a component holding a mapping from a symbolstream to text.
 * Its an observer of a Grammar, SymbolStream and BoxTree2Stream components. When it receives a property change from these components, it (re)generates text from a symbolstream.
 *  
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSymbolstream2TextComp extends CPAMOJAComponent implements ISymbolstream2TextComp,PropertyChangeListener{

    private IGrammarComp Grammar;
    private IBoxTree2StreamComp BoxTreetoSymbolStream;
    private ISymbolStreamComp SymbolStream;
    private String Text;
    
    private CTerminalDef_List TerminalDefns;
    private String indentSpaces; //This is a temporary trick
    private CSymbolStream Stream;
    
    private int indentLevel;
    private int m;
    //private ArrayList<Symbol> vLine;
    private StringBuilder vText;

    /**
     * Get the value of a symbol stream
     *
     * @return the value of a symbol stream
     */
    public CSymbolStream getStream() {
        return Stream;
    }

    /**
     * Sets the given value of a symbol stream
     *
     * @param aStream the value of a symbol stream
     */
    public void setStream(CSymbolStream aStream) {
        Stream = aStream;
        createText();
    
    }


    /**
     * Get the value of TerminalDefns
     *
     * @return the value of TerminalDefns
     */
    public CTerminalDef_List getTerminalDefns() {
        return TerminalDefns;
    }

    /**
     * Set the value of TerminalDefns
     *
     * @param aTerminalDefns new value of TerminalDef list
     */
    public void setTerminalDefns(CTerminalDef_List aTerminalDefns) {
       TerminalDefns = aTerminalDefns;
    }

    /**
     * Creates a new Stream2Text component instance.
     */
    public CSymbolstream2TextComp() {
        super();
        Text=new String();
        indentLevel=0;
        indentSpaces=" ";
        m=0;
    }
    
     @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("SymbolstreamtoText",10, 10);
    } 

    
    public String getText() {
        return Text;
    }

   
    @Override
    public void setText(String aText){
       //Keep the old value
       String oldText=Text;
       //Get the new Value
       Text=aText; 
            
      //Fire a property change event
      support.firePropertyChange("Text",oldText,Text);
        
    }

   
    @Override
    public ISymbolStreamComp getSymbolStream() {
        return SymbolStream;
    }

   
    @Override
    public void setSymbolStream(ISymbolStreamComp aSymbolStream) {
        
        CSymbolStream vStream;
       if(SymbolStream!=null){
             SymbolStream.removePropertyChangeListener(this);
       }
       SymbolStream=aSymbolStream;
       if(SymbolStream!=null){
           SymbolStream.addPropertyChangeListener(this);
           vStream=SymbolStream.getSymbolStreamStructure();
           
       }else{
             vStream= new  CSymbolStream();
       }
       setStream(vStream);
       
    }


    
    @Override
    public IBoxTree2StreamComp getBoxTreetoSymbolStream() {
        return BoxTreetoSymbolStream;
    }

    
    @Override
    public void setBoxTreetoSymbolStream(IBoxTree2StreamComp aBoxTreetoSymbolStream) {
        CSymbolStream vStream;
       if(BoxTreetoSymbolStream!=null){
              BoxTreetoSymbolStream.removePropertyChangeListener(this);
       }
       BoxTreetoSymbolStream=aBoxTreetoSymbolStream;
       if(BoxTreetoSymbolStream!=null){
           BoxTreetoSymbolStream.addPropertyChangeListener(this);
           vStream=BoxTreetoSymbolStream.getSymbolStream();
       }else{
             vStream= new  CSymbolStream();
       }
       setStream(vStream);
       createText();
    }


    
    @Override
    public IGrammarComp getGrammar() {
        return Grammar;
    }

   
    @Override
    public void setGrammar(IGrammarComp aGrammar) {
         CTerminalDef_List vTerminalDefns;
       if(Grammar!=null){
              Grammar.removePropertyChangeListener(this);
       }
       Grammar=aGrammar;
       if(Grammar!=null){
           Grammar.addPropertyChangeListener(this);
           vTerminalDefns=Grammar.getGrammarStructure().getGrammarContext().getTerminalDefs();
       }else{
             vTerminalDefns= new  CTerminalDef_List();
       }
       setTerminalDefns(vTerminalDefns);
       createText();
    }

    /**
     * Verifies that the grammar and symbolstream are not null and maps a symbol stream to text.
     */
    private void createText(){
        String vText="";
        if(Grammar!=null && (BoxTreetoSymbolStream!=null || (SymbolStream!=null && SymbolStream.getSymbolStreamStructure().symbolCount()!=0))){
            vText=StreamtoText(Stream,TerminalDefns);
            setText(vText);
            
        }
    }
/**
     * Handles property change events from BoxTree2Stream, Grammar and SymbolStream component and (re)creates text from a symbolstream.
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
        CSymbolStream vStream;
         CTerminalDef_List vTerminalDefns;
        if (source==BoxTreetoSymbolStream) {
            vStream = BoxTreetoSymbolStream.getSymbolStream();
            setStream(vStream);
        } else if (source==Grammar) {
            vTerminalDefns=Grammar.getGrammarStructure().getGrammarContext().getTerminalDefs();
            setTerminalDefns(vTerminalDefns);
            
        }else if(source==SymbolStream){
            vStream=SymbolStream.getSymbolStreamStructure();
            setStream(vStream);
        }
       createText();
       //System.out.println(Text);
    }
    
    /**
     * A mapping from the specified symbolstream to text using the specified list of grammar terminals.
     * 
     * @param aStream the specified symbolstream
     * @param aTerminals the specified list of grammar terminals
     * @return the text after mapping.
     */
    public String StreamtoText(CSymbolStream aStream, CTerminalDef_List aTerminals){
        vText=new StringBuilder();
        Symbol vSymbol;
        ArrayList<Symbol> vLine;
              
        for(int i=0; i<aStream.getSymbolStream().size()-1;i++){
           
            // process line with index i
            vLine=aStream.getSymbolStream().get(i);
           
            //process vLine
            if(!vLine.isEmpty()){
              vSymbol=vLine.get(0);
              indentText(vSymbol);
              processSymbols(vLine, aTerminals);
              vText.append("\n");
            }
        }
        //process last line of symbols in the symbol stream
         vLine=aStream.getSymbolStream().get(aStream.getSymbolStream().size()-1);
         if(!vLine.isEmpty()){
              vSymbol=vLine.get(0);
              indentText(vSymbol);
              processSymbols(vLine, aTerminals);
         }
         return vText.toString();
       
   }
    /**
     * Indents symbols.
     * @param aSymbol 
     */
   private void indentText(Symbol aSymbol){
       switch(aSymbol.symKind()){
                  case INDENT:
                      indentLevel++;
                      m=1;
                      break;
                  default:
                      m=0;
                      break;
              }
              // produce blanks corresponding to indent level
              for(int k=0;k<indentLevel;k++){
                vText.append(indentSpaces);
              }
   }
   
   private void processSymbols(ArrayList<Symbol> aLine,CTerminalDef_List aTerminals){
       Symbol vSymbol;
      
       for(int j=m;j<aLine.size();j++){
           vSymbol=aLine.get(j);
           switch(vSymbol.symKind()){
               case FIXED:
                  if(vSymbol.symName().equals("endmarker")){
                              
                  }else{
                    vText.append(getSymbol(vSymbol.symName(),aTerminals));
                  }
                  break;
                      
               case VARIABLE:
               case INVALID:
                  vText.append(vSymbol.symValue());
                  break;
               case FILLER:
               String vFiller=" ";
               for(int f=0;f<vSymbol.symLength();f++){
                  vText.append(vFiller);
               }
               break;
               case UNINDENT:
                  indentLevel--;
                  break;
               default:  break;
            }
       }
   }
  /**
   * Returns a terminal symbol with the specified name from the specified list of terminals.
   * @param aName name of a terminal symbol
   * @param aTerminals the list of terminals
   * @return the String representation of a symbol.
   */ 
  private String getSymbol(String aName,CTerminalDef_List aTerminals){
      String vSym="";
          for(int l=0;l<aTerminals.contextCount();l++){
            if(aTerminals.getElt(l).getName().equals(aName)){
              vSym=aTerminals.getElt(l).getBody().toText();
              vSym=vSym.substring(1,vSym.length()-1);
            }
          }     
      return vSym;
  }
}

    
    

