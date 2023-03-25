/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.PanelTree;

import Components.CoreHybridEditor.ICoreHybridEditorComp;
import Components.IPAMOJAComponent;
import Components.Specifications.Language.ILanguageComp;
import Components.Specifications.Presentation.IPresentationComp;


/**
 * Defines services for interacting with other components ( like, Language, Presentation and CoreHybridEditor components).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IPanelTreeComp extends IPAMOJAComponent {
        
     /**
     * Access a Presentation component via its interface.
     *
     * @return the Presentation interface
     */
    IPresentationComp getPresentation();

    /**
     * Connect to a Presentation component via its interface.
     * Register for property change events, retrieve box patterns and symbol styles from the Presentation component and (re)create this panel tree.
     *
     * @param aPresentation IPresentationComp object.
     */
    void setPresentation(IPresentationComp aPresentation);
        
    /**
     * Access a CoreHybridEditor component via its interface.
     *
     * @return the CoreHybridEditor interface
     *
     */
    ICoreHybridEditorComp getHybridEditor();

   /**
     * Connect to a CoreHybridEditor component via its interface.
     * Register for property change events, retrieve the AST from the CoreHybridEditor component and (re)create this panel tree.
     *
     * @param hybridEditor ICoreHybridEditorComp object.
     */
    void setHybridEditor( ICoreHybridEditorComp hybridEditor);
    
    /**
     * Access a Language component via its interface.
     *
     * @return the Language interface
     *
     */
    ILanguageComp getLanguage();

     /**
     * Connect to a Language component via its interface.
     * Register for property change events, retrieve the lexeme and terminal definitions from the Language component and (re)create this panel tree.
     *
     * @param aLanguage ILanguageComp object.
     */
    void setLanguage(ILanguageComp aLanguage);
     //Add and remove listener methods

    /**
     * Determines if the Panel tree component is in editing mode.
     * 
     * @return <code>true</code> if the panel tree is in editing mode, otherwise <code>false</code>.
     */
    boolean isStructuralMode();

   /**
     * Sets the structuralMode of the panel tree.
     * 
     * @param structuralMode if the panel tree is in editing mode then <code>true</code>, if not in editing mode, <code>false</code>.
     */
    void setStructuralMode(boolean structuralMode);
}
