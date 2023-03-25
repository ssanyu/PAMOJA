/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.SymbolStyleCustomizer;
import Components.CPAMOJAComponent;
import Components.Concrete.Grammar.IGrammarComp;
import Components.Specifications.Language.ILanguageComp;

import GrammarNotions.Grammar.CGrammar;
import java.io.*;
import java.beans.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;



/**
 * This component holds a mapping from grammar symbols to symbol categories and symbol categories to font and color attributes.
 * It observes the Grammar or Language component and maintains consistency between its own valid symbol domain
 * and the symbols defined in the grammar.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSymbolStyleCustomizerComp extends CPAMOJAComponent implements ISymbolStyleCustomizerComp,Serializable,PropertyChangeListener {
    /**
     * The string representation of SymbolStyleCustomizer
     */
    private String SymbolStyleCustomizerText; 
    /**
     * The internal representation of SymbolStyleCustomizer
     */
    private CSymbolStyleCustomizerStructure SymbolStyleCustomizerStructure; 
    /**
     * A reference to grammar object.
     */
    private IGrammarComp Grammar; 
    /**
     * A reference to Language object.
     */
    private ILanguageComp Language;
    
    /**
     * Creates a new instance of <code>CSymbolStyleCustomizer</code>.
     */
    
    public CSymbolStyleCustomizerComp(){
        super();
        SymbolStyleCustomizerStructure=new CSymbolStyleCustomizerStructure();
        SymbolStyleCustomizerText=SymbolStyleCustomizerStructure.toText();
    }
    
     @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("SymbolStyleCustomizer",5,15);
   }
    
    @Override
    public String getSymbolStyleCustomizerText(){
        return SymbolStyleCustomizerText;
    }
    
    @Override
    public void setSymbolStyleCustomizerText(String aSymbolStyleCustomizerText){
        // keep old value
        String oldSymbolStyleCustomizerText=SymbolStyleCustomizerText;
        //get new value
        SymbolStyleCustomizerText=aSymbolStyleCustomizerText;
        SymbolStyleCustomizerStructure=CSymbolStyleCustomizerStructure.fromText(SymbolStyleCustomizerText);
        
        // fire the property change event, with a property that has changed
        support.firePropertyChange("SymbolStyleCustomizerText",oldSymbolStyleCustomizerText,SymbolStyleCustomizerText);
    }
   
    @Override
    public CSymbolStyleCustomizerStructure getSymbolStyleCustomizerStructure(){
        return SymbolStyleCustomizerStructure;
    }
    
    
    @Override
    public void setSymbolStyleCustomizerStructure(CSymbolStyleCustomizerStructure aSymbolStyleCustomizerStructure){
        //keep old value
        CSymbolStyleCustomizerStructure oldSymbolStyleCustomizerStructure=SymbolStyleCustomizerStructure;
        //get new value
        SymbolStyleCustomizerStructure=aSymbolStyleCustomizerStructure;
        SymbolStyleCustomizerText=SymbolStyleCustomizerStructure.toText();
         // fire the property change event, with a property that has changed
        support.firePropertyChange("SymbolStyleCustomizerStructure",oldSymbolStyleCustomizerStructure, SymbolStyleCustomizerStructure);
    }
    
   /**
    * Returns a reference to the Language component.
    * @return a Language component reference
    */
    public ILanguageComp getLanguage(){
        return Language;
    }
    /**
    * Returns a reference to the Grammar component.
    * @return a Grammar component reference
    */
    public IGrammarComp getGrammar(){
        return Grammar;
    }
    /**
     * Link to <code>Grammar</code> component via its interface -- Set the value of <code>Grammar</code>.
     * Register for property change events and retrieve current internal structure of grammar object, update this symbolstyle customizer structure
     * and notify observers about <code>Grammar</code> property changes.
     * 
     * @param aGrammar new value of grammar
     */
    public void setGrammar(IGrammarComp aGrammar){
         CGrammar vGrammarStructure;
         //remove old Grammar
         if(Language!=null){
            
             Language.removePropertyChangeListener(this);
         }
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
         updateSymbolStyleCustomizer(vGrammarStructure);
         
    }
 
    /**
     * Link to <code>Language</code> component via its interface -- Set the value of <code>Language</code>.
     * Register for property change events and retrieve current internal structure of grammar object, update this symbolstyle customizer structure
     * and notify observers about grammar property changes.
     * 
     * @param aLanguage new value of Language
     */
    public void setLanguage(ILanguageComp aLanguage){
         CGrammar vGrammarStructure;
        if(Grammar!=null){
            Grammar.removePropertyChangeListener(this);
        }
         //remove old Language
         if(Language!=null){
              Language.removePropertyChangeListener(this);
         }
         Language=aLanguage;
         if(Language!=null){
              Language.addPropertyChangeListener(this);
             //obtain the GrammarStructure 
              vGrammarStructure=Language.getGrammarComp().getGrammarStructure();
         }
         else { //Grammar object is not connected, return empty structure
              vGrammarStructure=new CGrammar();
              
         }
         updateSymbolStyleCustomizer(vGrammarStructure);
         
    }
    /**
     * Updates the customizer symbols according to the given grammar.
     * @param aGrammarStructure the CGrammar object
     */
    public void updateSymbolStyleCustomizer(CGrammar aGrammarStructure){
       CSymbolStyleCustomizerStructure vSymbolStyleCustomizerStructure=new CSymbolStyleCustomizerStructure();
       String vSymbolName;
       String vCategory;
       if((Grammar!=null || Language!=null) && aGrammarStructure!=null){
            for(int i=0;i<aGrammarStructure.getGrammarContext().getTerminalDefs().count()-1;i++){
                vSymbolName=aGrammarStructure.getGrammarContext().getTerminalDefs().getElt(i).getName();  // check for '@'
                if(!vSymbolName.isEmpty() && SymbolStyleCustomizerStructure.indexOfSymbol(vSymbolName)!=-1){ // Error, check if it has symbols in the code
                    vCategory=SymbolStyleCustomizerStructure.getFSymbolToCategory().get(SymbolStyleCustomizerStructure.indexOfSymbol(vSymbolName)).FCategory;
                    vSymbolStyleCustomizerStructure.getFSymbolToCategory().add(new SymbolCategory(vSymbolName,vCategory));
                }else if(!vSymbolName.isEmpty() && SymbolStyleCustomizerStructure.indexOfSymbol(vSymbolName)==-1){
                    vSymbolStyleCustomizerStructure.getFSymbolToCategory().add(new SymbolCategory(vSymbolName,"Default"));
                }
            }
       }
        vSymbolStyleCustomizerStructure.setFCategoryToAttributes(SymbolStyleCustomizerStructure.getFCategoryToAttributes());
        setSymbolStyleCustomizerStructure(vSymbolStyleCustomizerStructure);
   }
   /**
     * Receives property change events and handles them. If the property change is from the <code>Grammar</code> or <code>Language</code> component,
     * retrieve the internal structure of the grammar, update the symbolstyle customizer and notify observers. 
     * 
     * @param evt event object with the new value
     */
   @Override
   public void propertyChange(PropertyChangeEvent evt){
         Object source=evt.getSource();
              if(source==Grammar){
                    updateSymbolStyleCustomizer(Grammar.getGrammarStructure());
              }else if(source==Language){
                    updateSymbolStyleCustomizer(Language.getGrammarComp().getGrammarStructure());
              }
   }
   
   @Override
   public Color symbolNameToColor(String aSymbolName){
        String vCategory=null;
        Color vColor=Color.BLACK;
        int vIndex=-1;
        vIndex=SymbolStyleCustomizerStructure.indexOfSymbol(aSymbolName);
        if(vIndex>=0){
           vCategory=SymbolStyleCustomizerStructure.getFSymbolToCategory().get(vIndex).FCategory;
        } 
        
        if(vCategory!=null){
           vIndex=SymbolStyleCustomizerStructure.indexOfCategory(vCategory);
           if(vIndex>=0){
            vColor=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(vIndex).FAttributes.fColor;
           }
           }
           
        return vColor;
   }
  
    @Override
    public Font symbolNameToFont(String aSymbolName){
        String vCategory=null;
        Font vFont=new Font("Monospaced",Font.PLAIN,13);
        int vIndex=-1;
       
        if(aSymbolName!=null){ 
            vIndex=SymbolStyleCustomizerStructure.indexOfSymbol(aSymbolName);
            if(vIndex>=0){
             vCategory=SymbolStyleCustomizerStructure.getFSymbolToCategory().get(vIndex).FCategory;
            }
        } 
        
        if(vCategory!=null){
           vIndex=SymbolStyleCustomizerStructure.indexOfCategory(vCategory);
           if(vIndex>=0){ 
             vFont=SymbolStyleCustomizerStructure.getFCategoryToAttributes().get(vIndex).FAttributes.fFont;
           }
        }
       return vFont;
    }

   
     
}