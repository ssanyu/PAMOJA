/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Concrete.Grammar;


import Analyzers.CGrammarAnalyzer;
import Components.CPAMOJAComponent;
import Components.INodeObject;
import GrammarNotions.Grammar.CGrammar;
import GrammarNotions.Grammar.CGrammarCodes;
import GrammarNotions.Grammar.CGrammarContext;
import GrammarNotions.RegExpr.*;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;
import GrammarNotions.SyntaxExpr.*;
import Nodes.CNode;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * A component which holds lexical and context-free grammar definitions, and relations
 * between them. It maintains consistency of the grammar definitions and consistency
 * between its textual representation and structural representation. It contains an analyzer
 * tool which computes the following analysis information: <code>First</code>,<code>Last</code>, <code>Follow</code> and
 * <code>lookahead</code> sets, and predicates such as <code>Null</code>, <code>Empty</code> and <code>Reachable</code>. 
 * The component notifies other grammar-dependent components (its observers) of changes in
 * the grammar.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CGrammarComp extends CPAMOJAComponent implements IGrammarComp,INodeObject {
   /**
    * The string representation of a grammar
    */
   private String GrammarText; 
    /**
     * The internal representation of a grammar
     */
   private CGrammar GrammarStructure; 
   /**
    * An option to indicate whether a grammar should be annotated with an endmarker symbol or not.
    */
   private boolean Augment;
   
   /**
    * The list which stores errors obtained when loading the grammar.
    */
   private ArrayList<String> fErrorList;
   
   Image img=null;
   
   private CGrammarAnalyzer fAnalyzer;
   private ArrayList<Boolean> LL1CondList;
   /**
    * Creates a new instance of <code> CGrammarComp</code>.
    */
   public CGrammarComp(){
       super();
       fErrorList=new ArrayList<>();
       LL1CondList=new ArrayList();
       GrammarStructure=new CGrammar(new CGrammarContext(),new CSE_Empty());
       GrammarText=GrammarStructure.toText();
       fAnalyzer=new CGrammarAnalyzer();
       
   }

    /**
     * Paints the UI of the Grammar component object. 
     * 
     * @param g the Grammar component object to paint.
     */
   @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       if(img!=null)  {
         g.drawImage(img, 3, 3, this);
       }else{
         g.drawString("Grammar",5,15);
   }
   }
 /**
  * Returns <code>true</code> if this grammar is annotated with endmarker symbol, <code>false</code>
  * otherwise.
  *
  * @return <code>true</code> if this grammar is annotated with endmarker symbol, <code>false</code>
  *         otherwise
 */
    @Override
   public boolean isAugment(){
        return Augment;
   }
    /**
     * Set the value of Augment and notify observers about <code>Augment</code> property changes.
     *
     * 
     * @param aValue new value of Augment
     */
    @Override
    public void setAugment(boolean aValue){
        //keep the old value
        boolean oldAugment=Augment;
        //get the new value
        Augment=aValue;
        GrammarStructure.setAugmented(Augment);
        setGrammarStructure(GrammarStructure);
        support.firePropertyChange("Augment",oldAugment,Augment);
    }
    /**
     * Returns the string representation of this grammar.
     * 
     * @return the string representation of this grammar
     */
   
    @Override
   public String getGrammarText(){
      return GrammarText;
   }
   
   /* 
    * Set the string representation of this grammar.
    * Check well-formedness of the string representation of the grammar.
    * Signal an error if the string representation is invalid else compute the
    * internal representation of the grammar. Also generate analysis
    * information and fire a property change.
    *
    * @param aGrammarText the text representation of the grammar to set.
    * @pre:  Grammar is well-formed(See <code>toText(String aGrammarText)</code>)
    *
    */

    /**
     *
     * @param aGrammarText
     */
    

    @Override
    public void setGrammarText(String aGrammarText){
       CGrammar vGrammarStructure;
      if(!aGrammarText.isEmpty()){
       //keep the old value
       String oldGrammarText=GrammarText;
                 
       //Get the new Value
       GrammarText=aGrammarText; 
       //create a grammar object from the text
       
       vGrammarStructure=CGrammar.fromText(GrammarText);
             
       if(vGrammarStructure!=null){
           //check if the grammar is Augmented
           checkAugment(vGrammarStructure); 
           
          //Restore internal numbering
          vGrammarStructure.getGrammarContext().reNumber();
          
          //Update references in Grammar
           updateAllReferences(vGrammarStructure);
          
           // check if there are any Errors
           if(!fErrorList.isEmpty()){
                String vErrors="";
                for(int i=0;i<=fErrorList.size()-1;i++){
                    vErrors=vErrors+"\n"+(i+1)+":Symbol "+"\""+fErrorList.get(i)+"\""+" is not declared";
                }
                //Signal error and keep oldValue
                JOptionPane.showMessageDialog(null,vErrors);
                GrammarText=oldGrammarText;
           }else{
                GrammarStructure=vGrammarStructure;
                GrammarText=GrammarStructure.toText();
                // Analyze the Grammar
                analyzeGrammar(GrammarStructure);
           }

      }else{
           GrammarText=oldGrammarText;
      }

      updateProperties();
     
      //Fire a property change event
      support.firePropertyChange("GrammarText",oldGrammarText,GrammarText);
      }
     }
    
    /**
     * Determines if this grammar is augmented with endmarker symbol.
     * 
     * @param aGrammarStructure the internal structure of this grammar
     */
    public void checkAugment(CGrammar aGrammarStructure){
        if(aGrammarStructure.getStartExpr() instanceof CSE_MultiDot){
            Augment=true;
        }else {
                 int n = JOptionPane.showConfirmDialog(null,"The grammar is not augmented.\n Do you want to augment it? ","Grammar Component",JOptionPane.YES_NO_OPTION);
                 if (n == JOptionPane.YES_OPTION) { 
                     Augment=true;
                     aGrammarStructure.setAugmented(Augment);  
                     
                     
                 } else if (n == JOptionPane.NO_OPTION) { 
                      Augment=false;
                      aGrammarStructure.setAugmented(Augment);                  
                 } 
         } 
    }
    
    /**
     * Returns the Analyzer object associated with this Grammar.
     * 
     * @return the Analyzer component.
     */
    public CGrammarAnalyzer getAnalyzer(){
    return fAnalyzer;
    }
    /**
     * Updates the grammar after it has been augmented with endmarker symbol.
     */
    public void updateProperties(){
       CSE vStart=GrammarStructure.getStartExpr();
       if(vStart instanceof CSE_MultiDot){
           //if(!isAugment()){
               Augment=true;
          // }
       }else{
          // if(isAugment()){
              Augment=false; 
           //}
       }
       GrammarStructure.setAugmented(Augment);
       //Restore internal numbering
       GrammarStructure.getGrammarContext().reNumber();
       //Update references in Grammar
       updateAllReferences(GrammarStructure);
       GrammarText=GrammarStructure.toText();
    }
   /**
    * Returns the internal structure of this grammar.
    * 
    * @return the internal structure of this grammar
    */
   @Override
   public CGrammar getGrammarStructure(){
        return GrammarStructure;
   }
    /**
     * Sets the value of the internal structure of this grammar, generates its corresponding string representation, performs grammar analysis and notifies observers about <code>GrammarStructure</code> property changes.
     * @param aGrammarStructure
     */
   @Override
   public void setGrammarStructure(CGrammar aGrammarStructure){
                    
        // keep the old value
        CGrammar oldGrammarStructure=GrammarStructure;
        // save the new value
        GrammarStructure=aGrammarStructure;
        //Restore internal numbering
        GrammarStructure.getGrammarContext().reNumber();
        //Update symbol references
        updateAllReferences(GrammarStructure);
        // Update textual representation of Grammar
        GrammarText=GrammarStructure.toText();
        // Analyze the Grammar
        analyzeGrammar(GrammarStructure);
        // fire the property change event, with a property that has changed
        support.firePropertyChange("GrammarStructure",oldGrammarStructure,GrammarStructure);
   }
   /**
    * Updates internal references of this grammar.
    * @param aGrammarStructure the internal structure of the grammar to be updated
    */
   private void updateAllReferences(CGrammar aGrammarStructure){
       fErrorList=new ArrayList<>();
       updateSymReferences(aGrammarStructure);
       updateLexemeReferences(aGrammarStructure);
   }
  /**
   * Updates lexeme and terminal definitions of this grammar object.
   * @param aGrammar the grammar object having lexeme and terminal definitions to update.
   */
   private void updateLexemeReferences(CGrammar aGrammar){
        // update references in body of lexemedefs
        for(int i=0;i<=aGrammar.getGrammarContext().getLexemeDefs().count()-1;i++){
            updateREReferences(aGrammar,((CLexemeDef)aGrammar.getGrammarContext().getLexemeDefs().getElt(i)).getBody());
        }
        // update references in body of terminaldefs
        for(int i=0;i<=aGrammar.getGrammarContext().getTerminalDefs().count()-1;i++){
            updateREReferences(aGrammar,((CTerminalDef)aGrammar.getGrammarContext().getTerminalDefs().getElt(i)).getBody());
        }
        
   }
 /**
   * Updates nonterminal definitions of this grammar object.
   * @param aGrammar the grammar object having nonterminal definitions to update.
   */
   private void updateSymReferences(CGrammar aGrammar){
        // update references in body of nonterminaldefs
        for(int i=0;i<=aGrammar.getGrammarContext().getNonTerminalDefs().count()-1;i++){
            updateReferences(aGrammar,((CNonTerminalDef)aGrammar.getGrammarContext().getNonTerminalDefs().getElt(i)).getBody());
        }
        // update references in start expression
        updateReferences(aGrammar,aGrammar.getStartExpr());
   }
   
   /**
   * Updates the references of a given syntax expression in the given grammar.
   * 
   * @param aSE the syntax expression.
   * @param aGrammar the grammar object containing the syntax expression.
   */
   private void updateReferences(CGrammar aGrammar,CSE aSE){
        CSymDec vSymDec;
        boolean vErrors=false;
        int i;
        CGrammar vGrammar=aGrammar;
        
        switch(aSE.sortCode()){
            case CGrammarCodes.scSESym:
                CSE_Sym aSym=(CSE_Sym)aSE;
                //find ref to corresponding declaration;assign it to Dec property
                vSymDec= findSymDec(aSym.getName(), vGrammar);
                vErrors = vErrors |(vSymDec == null);
                if(vErrors==true){
                    updateErrorList(aSym.getName());
                }else{
                    aSym.setDec(vSymDec);
                }
                break;
             case CGrammarCodes.scSEMultiDot:   
                 CSE_MultiDot aDot=(CSE_MultiDot)aSE;
                 for(i=0;i<=aDot.getList().count()-1;i++){
                     updateReferences(vGrammar,aDot.getList().getElt(i));
                 }
                 break;
             case CGrammarCodes.scSEMultiStick:   
                 CSE_MultiStick aMulti=(CSE_MultiStick)aSE;
                 for(i=0;i<=aMulti.getList().count()-1;i++){
                     updateReferences(vGrammar,aMulti.getList().getElt(i));
                 }
                 break;
             default: // call recursively for all subexpressions
                 for( i=0;i<=aSE.termCount()-1;i++){
                     updateReferences(vGrammar,(CSE)aSE.getTerm(i));
                 }
             break;
        }
     
     }
    /**
   * Updates the references of a given regular expression in the given grammar.
   * 
   * @param aRE the regular expression.
   * @param aGrammar the grammar object containing the regular expression.
   */
   private void updateREReferences(CGrammar aGrammar,CRE aRE){
        CLexemeDef vLexemeDef;
        boolean vErrors=false;
        int i;
        CGrammar vGrammar=aGrammar;
        
        switch(aRE.sortCode()){
            case CGrammarCodes.scRELexeme:
                CRE_Lexeme aLex=(CRE_Lexeme)aRE;
                //find ref to corresponding declaration;assign it to Dec property
                vLexemeDef=findLexDef(aLex.getName(),vGrammar);
                vErrors = vErrors |(vLexemeDef == null);
                if(vErrors==true){
                    updateErrorList(aLex.getName());
                }else{
                    aLex.setDef(vLexemeDef);
                }
                break;
             case CGrammarCodes.scREMultiDot:   
                 CRE_MultiDot aDot=(CRE_MultiDot)aRE;
                 for(i=0;i<=aDot.List().count()-1;i++){
                     updateREReferences(vGrammar,aDot.List().getElt(i));
                 }
                 break;
             case CGrammarCodes.scREMultiStick:   
                 CRE_MultiStick aMulti=(CRE_MultiStick)aRE;
                 for(i=0;i<=aMulti.List().count()-1;i++){
                     updateREReferences(vGrammar,aMulti.List().getElt(i));
                 }
                 break;
             default: // call recursively for all subexpressions
                 for( i=0;i<=aRE.termCount()-1;i++){
                     updateREReferences(vGrammar,(CRE)aRE.getTerm(i));
                 }
             break;
        }
     
     }
   /**
    * Returns a lexeme definition from this grammar object.
    * 
    * @param aName the name of the lexeme symbol
    * @param aGrammar the grammar object which contains the lexeme definition
    * @return the lexeme definition for this specified name
    */
   private CLexemeDef findLexDef(String aName, CGrammar aGrammar){
       CLexemeDef vResult=null;
       for(int i=0;i<=aGrammar.getGrammarContext().getLexemeDefs().count()-1;i++){
            if(((CLexemeDef)aGrammar.getGrammarContext().getLexemeDefs().getElt(i)).getName().equals(aName)){
                vResult=(CLexemeDef)aGrammar.getGrammarContext().getLexemeDefs().getElt(i);
            }
       }
       return vResult;
   }
   /**
    * Returns a terminal or nonterminal definition from this grammar object. 
    * 
    * @param aName the name of the terminal or nonterminal symbol
    * @param aGrammar the grammar object which contains the terminal or nonterminal definition
    * @return the terminal or nonterminal definition this specified name
    */
   private CSymDec findSymDec(String aName, CGrammar aGrammar){
        CSymDec vResult=null;
        // search terminals
        for(int i=0;i<=aGrammar.getGrammarContext().getTerminalDefs().contextCount()-1;i++){
            if(((CSymDec)aGrammar.getGrammarContext().getTerminalDefs().getElt(i)).getName().equals(aName)){
                vResult=(CSymDec)aGrammar.getGrammarContext().getTerminalDefs().getElt(i);
            }
        }
        if(vResult!=null){
             return vResult;
        }
        // search nonterminals
        for(int i=0;i<=aGrammar.getGrammarContext().getNonTerminalDefs().contextCount()-1;i++){
            if(((CSymDec)aGrammar.getGrammarContext().getNonTerminalDefs().getElt(i)).getName().equals(aName)){
                vResult=(CSymDec)aGrammar.getGrammarContext().getNonTerminalDefs().getElt(i);
            }
        }
        return vResult;
    }
  /**
   * Performs standard analysis on this grammar by checking whether it is well-formed and computes analysis information.
   * @param aGrammar the grammar object 
   */
   private void analyzeGrammar(CGrammar aGrammar){
         
       fAnalyzer=new CGrammarAnalyzer();
       fAnalyzer.setGrammar(aGrammar);
       fAnalyzer.analyzeGrammar();
   }
   /**
    * Adds an error message to the list of error messages for this grammar object.
    * @param aMsg the error message to add to the list
    */
   private void updateErrorList(String aMsg){
       if(!(fErrorList.contains(aMsg))){
               fErrorList.add(aMsg);
       }
   }

    /**
     *
     * @return
     */
    @Override
   public boolean ELL1(){
       boolean ELL1=true;
       LL1CondList=new ArrayList();
       CSE vBody;
       for(int i=0;i<GrammarStructure.getGrammarContext().getNonTerminalDefs().count();i++){
                vBody=GrammarStructure.getGrammarContext().getNonTerminalDefs().getElt(i).getBody();
                LL1CondList.add(fAnalyzer.analysisOfECFG(vBody).fELL1Det);
       }
       
        if(!LL1CondList.isEmpty()){
           for(int i=0;i<LL1CondList.size();i++){
               if(LL1CondList.get(i)==false){
                   ELL1=false;
                   break;
               }       
           }}
        return ELL1;
   }
     /**
     * Returns the internal structure of the grammar.
     *
     * @return the internal structure of this grammar as a node object
     */
    @Override
    public CNode getNode() {
        return (CNode)GrammarStructure;
    }
    /**
     * Returns <code>true</code> if a terminal symbol of this grammar has data, <code>false</code>
     * otherwise.
     * 
     * @param aSym the code of this terminal symbol
     * @return <code>true</code> if this terminal symbol has data, <code>false</code>
     *         otherwise
     */
     @Override
    public boolean hasData(int aSym){
        CTerminalDec vResult;
        vResult=(CTerminalDec)GrammarStructure.getGrammarContext().getSymbol(aSym);
        if(vResult.hasData())
            return true;
        else return false; 
   } 
    
    /**
     * Converts a textual representation of a RE to the corresponding structural representation.
     * 
     * @param aString the String of a RE.
     * @return the CRE object.
     */
    public CRE RETextToTree(String aString){
        boolean vSuccess;
        CRE vRE;
        CGrammarScanner vScanner=new CGrammarScanner();
        CGrammarParser vParser=new CGrammarParser();
        
        vParser.fScanner=vScanner;
        vParser.fScanner.setText(aString);
        vParser.reSet();
        
        vSuccess=vParser.parseRE();
        if(vSuccess){
            vRE=vParser.fRETree;
        }else{
          JOptionPane.showMessageDialog(null,"Error in parsing Regular Expression:"+aString);
          vRE=new CRE_Empty();  
        }
        return vRE;
    }
    
   /**
   * Removes all elements from the grammar component
   */
   @Override
  public void clear(){
       fErrorList=new ArrayList<>();
       fAnalyzer=new CGrammarAnalyzer();
       setGrammarStructure(new CGrammar(new CGrammarContext(),new CSE_Empty()));
      // setGrammarText(GrammarStructure.toText());
  }
}
