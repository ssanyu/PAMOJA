/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Specifications.Presentation;
import Components.Abstract.Patterns.CPatternsComp;
import Components.CPAMOJAComponent;
import Components.INodeObject;
import Components.Specifications.Language.ILanguageComp;

import GrammarNotions.Grammar.CGrammar;
import Nodes.CNode;
import java.io.*;
import java.beans.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;




/**
 * A composite component containing facilities for customizing the presentation style of program text to user's preferences. It is
 * composed of a Patterns component and a SymbolStyleCustomizer component. 
 * Presentation component is observer/observable. It observes a Language component and 
 * maintains consistency between its own valid symbol domain and the symbols of the 
 * lexical-part of a language. It notifies its observers of changes in the layout 
 * specifications and changes in its symbol categories as well as their color and font attributes.
 * 
 * @see Components.Abstract.Patterns.CPatternsComp
 * @see Components.Lexical.SymbolStyleCustomizer.CSymbolStyleCustomizerComp
 * @see Components.Specifications.Language.CLanguageComp
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CPresentationComp extends CPAMOJAComponent implements IPresentationComp,INodeObject,Serializable,PropertyChangeListener {
    /**
     * The string representation of SymbolStyleCustomizer
     */
    private String SymbolStyleCustomizerText; 
    /**
     * The internal representation of SymbolStyleCustomizer
     */
    private CSymbolStyleCustomizerStructure SymbolStyleCustomizerStructure; 
    
    private CPatternsComp patternsComp;
    private String patternLayouts;
    private String menuPatternLayouts;

    
    
    
    /**
     * A reference to language object.
     */
   
    
    private ILanguageComp Language;
    
    /**
     * Creates a new instance of <code>CPresentationComp</code>.
     */
    
    public CPresentationComp(){
        super();
        SymbolStyleCustomizerStructure=new CSymbolStyleCustomizerStructure();
        SymbolStyleCustomizerText=SymbolStyleCustomizerStructure.toText();
        patternsComp=new CPatternsComp();
        patternLayouts=patternsComp.getPatternsText();
        menuPatternLayouts=patternsComp.getMenuPatternsText();
    }
    
        
     @Override
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("Presentation",5,10);
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
   
    public ILanguageComp getLanguage(){
        return Language;
    }
    
    /**
     *
     * @param aLanguage
     */
    public void setLanguage(ILanguageComp aLanguage){
         CGrammar vGrammarStructure;
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
         else { //LexicalGrammar object is not connected, return empty structure
              vGrammarStructure=new CGrammar();
              
         }
         updateSymbolStyleCustomizer(vGrammarStructure);
         
    }
    /**
     * Updates the customizer symbols according to this grammar symbols.
     * @param aGrammarStructure 
     */
    public void updateSymbolStyleCustomizer(CGrammar aGrammarStructure){
       CSymbolStyleCustomizerStructure vSymbolStyleCustomizerStructure=new CSymbolStyleCustomizerStructure();
       String vSymbolName;
       String vCategory;
       if(Language!=null && aGrammarStructure!=null){
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
  
   @Override
   public void propertyChange(PropertyChangeEvent evt){
         Object source=evt.getSource();
               if(source==Language){
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

    /**
     *
     * @return
     */
    public CPatternsComp getPatternsComp() {
        return patternsComp;
    }

    /**
     *
     * @param patternsComp
     */
    public void setPatternsComp(CPatternsComp patternsComp) {
        this.patternsComp = patternsComp;
    }

    /**
     *
     * @return
     */
    public String getPatternLayouts() {
        return patternLayouts;
    }

    /**
     *
     * @param patternLayouts
     */
    public void setPatternLayouts(String patternLayouts) {
          //keep the old value
        String oldPatternLayouts=this.patternLayouts;
        //get the new value
        this.patternLayouts=patternLayouts;
        this.patternsComp.setPatternsText(patternLayouts);
        support.firePropertyChange("patternLayouts",oldPatternLayouts,this.patternLayouts);
    }

    /**
     *
     * @return
     */
    public String getMenuPatternLayouts() {
        return menuPatternLayouts;
    }

    /**
     *
     * @param menuPatternLayouts
     */
    public void setMenuPatternLayouts(String menuPatternLayouts) {
          //keep the old value
        String oldMenuPatternLayouts=this.menuPatternLayouts;
        //get the new value
        this.menuPatternLayouts=menuPatternLayouts;
        this.patternsComp.setMenuPatternsText(menuPatternLayouts);
        support.firePropertyChange("menuPatternLayouts",oldMenuPatternLayouts,this.menuPatternLayouts);
    }

    /**
     *
     * @return
     */
    @Override
    public CNode getNode() {
         return patternsComp.getNode();
    }
    
}

     
