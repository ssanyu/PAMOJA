/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Lexical.ScanTables;


import Automata.FAGenerator.CNFAtoDFA;
import Automata.FAGenerator.CREtoNFA;
import Automata.NFADFA.CDFA;
import Automata.NFADFA.CNFA;
import Components.CPAMOJAComponent;
import Components.Concrete.Grammar.IGrammarComp;
import Components.Specifications.Language.ILanguageComp;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.RegExpr.CRE;
import Sets.Alphabet;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * An implementation of a component which holds scan tables (NFA and DFA) and maintains consistency between the textual representation of the scan tables
 * tables and their corresponding structural representation. The component has a hidden 
 * scan-tables generator which produces the tables. When the
 * ScanTable component observes a change in the GrammarComp or Language component, it
 * invokes its hidden scan-tables generator to regenerate its tables and notifies its
 * observers.
 * 
 *  @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CScanTableComp extends CPAMOJAComponent implements IScanTableComp,PropertyChangeListener {
       
    private String DFATableText; //textual representation of DFATable
    private CDFA   DFATableStructure; // internal representation of DFATable
    private CNFA   NFATableStructure; // internal representation of NFATable
    private IGrammarComp Grammar; // access to Grammar object
    private ILanguageComp language;
    
   
    /**
     * Creates a new ScanTable component.
     */
       
    
    public CScanTableComp() {
       super();
       NFATableStructure=new CNFA(new Alphabet());
       DFATableStructure=new CDFA(new Alphabet());
       DFATableText=DFATableStructure.toText();
    }
    
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawString("ScanTables",5,10);
    }
    
    /**
     * Returns a reference to the Grammar component.
     * @return a reference to the Grammar component.
     */
    public IGrammarComp getGrammar() {
        return Grammar;
    }
       
   /**
    * Connects to the Grammar component with the specified reference.
    * @param aGrammar the specified reference to Grammar component.
    */
    public void setGrammar(IGrammarComp aGrammar){
         CGrammar vGrammarStructure;
         //remove old Grammar
         if(Grammar!=null){
              Grammar.removePropertyChangeListener(this);
         }
         Grammar=aGrammar;
         if(Grammar!=null){
              Grammar.addPropertyChangeListener(this);
             //obtain the GrammarStructure 
              vGrammarStructure=Grammar.getGrammarStructure();
         }
         else { //LexicalGrammar object is not connected, return empty structure
              vGrammarStructure=new CGrammar();
         }
         updateScanTables(vGrammarStructure);
     
    }

     /**
     * Returns a reference to the Language component.
     * @return a reference to the Language component.
     */
    public ILanguageComp getLanguage() {
        return language;
    }
   
    /**
    * Connects to the Language component with the specified reference.
    * @param aLanguage the specified reference to Language component.
    */  
    public void setLanguage(ILanguageComp aLanguage){
         CGrammar vGrammarStructure;
         //remove old Grammar
         if(language!=null){
              language.removePropertyChangeListener(this);
         }
         language=aLanguage;
         if(language!=null){
              language.addPropertyChangeListener(this);
             //obtain the GrammarStructure 
              vGrammarStructure=language.getGrammarComp().getGrammarStructure();
         }
         else { //LexicalGrammar object is not connected, return empty structure
              vGrammarStructure=new CGrammar();
         }
         updateScanTables(vGrammarStructure);
     
    }
    
    /**
     * Creates scan tables for the specified regular expression.
     * @param aRE the regular expression.
     */
    public void updateScanTables(CRE aRE){
       CNFA vNFA;
       CDFA vDFA;
          
       // create NFA
       CREtoNFA vTool=new CREtoNFA();
       vNFA=vTool.singleREtoNFA(aRE);
       setNFATableStructure(vNFA);
       
       //create DFA
            CNFAtoDFA vDFATool=new CNFAtoDFA();
            vDFATool.NFAtoDFA(vNFA);
            vDFA=vDFATool.getDFA();
           // for(int i=0;i<vSymbolList.size();i++)
           //     vDFA.setSymbols(i, vSymbolList.get(i));
            setDFATableStructure(vDFA);
    }

    /**
     * Creates scan tables for the given grammar.
     * @param aGrammarStructure the given grammar.
     */
    public void updateScanTables(CGrammar aGrammarStructure){
            CNFA vNFA;
            CDFA vDFA;
            ArrayList<String> vSymbolList;
            
            // create NFA
            CREtoNFA vTool=new CREtoNFA();
            vNFA=vTool.DefinitionsToNFA(aGrammarStructure);
            setNFATableStructure(vNFA);
            vSymbolList=vTool.TerminalsToSymList(aGrammarStructure);
            
            //create DFA
            CNFAtoDFA vDFATool=new CNFAtoDFA();
            vDFATool.NFAtoDFA(vNFA);
            vDFA=vDFATool.getDFA();
            for(int i=0;i<vSymbolList.size();i++)
                vDFA.setSymbols(i, vSymbolList.get(i));
            setDFATableStructure(vDFA);
        
   }
   
    /**
     *
     * @return
     */
    @Override
    public String getDFATableText(){
        return DFATableText;
    }

    /**
     *
     * @param aDFATableText
     */
    @Override
    public void setDFATableText(String aDFATableText){
        if(Grammar!=null){
              Grammar.removePropertyChangeListener(this);
        }
        String oldDFATableText=DFATableText;
        //Read Textual representation and update internal representation
        DFATableText=aDFATableText;
        DFATableStructure=CDFA.fromText(DFATableText); 
      //  Scanner.setDFA(DFATableStructure);
        // fire the property change event, with a property that has changed
        support.firePropertyChange("DFATableText",oldDFATableText,DFATableText);
    }

    /**
     *
     * @return
     */
    @Override
    public CNFA getNFATableStructure() {
        return NFATableStructure;
    }
    
    /**
     *
     * @param aNFATableStructure
     */
    @Override
    public void setNFATableStructure(CNFA aNFATableStructure) {
         // keep the old value
        CNFA oldNFATableStructure=NFATableStructure;
        // save the new value
        NFATableStructure=aNFATableStructure;
       // fire the property change event, with a property that has changed
        support.firePropertyChange("NFATableStructure",oldNFATableStructure,NFATableStructure);
    }
    
    @Override
    public CDFA getDFATableStructure() {
        return DFATableStructure;
    }

    /**
     *
     * @param aDFATableStructure
     */
    @Override
    public void setDFATableStructure(CDFA aDFATableStructure) {
         // keep the old value
        CDFA oldDFATableStructure=DFATableStructure;
        // save the new value
        DFATableStructure=aDFATableStructure;
        // Update textual representation of scan tables
        DFATableText=DFATableStructure.toText();
        // fire the property change event, with a property that has changed
        support.firePropertyChange("DFATableStructure",oldDFATableStructure,DFATableStructure);
    }
    /**
     * Handles property change events from the Grammar or Language Components and regenerates the scan tables.
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       Object source=evt.getSource();
          if(source==Grammar) 
            updateScanTables(Grammar.getGrammarStructure());
          else if (source==language)
             updateScanTables(language.getGrammarComp().getGrammarStructure()); 
    }
}
