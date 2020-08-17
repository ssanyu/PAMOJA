/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.BasicHybridEditor;

import Components.Specifications.Language.ILanguageComp;
import Components.Specifications.Presentation.IPresentationComp;
import Nodes.CNode;
import Nodes.CNodeFactory;

/**
 * Defines services for interacting with other components and classes ( like, Language and presentation components and NodeFactory class).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IBasicHybridEditorComp{

    /**
     * Sets the specified AST to this hybrid editor component, and notifies its observers.
     * 
     * @param ast the AST to set.
     */
    void setAst(CNode ast);

   /**
     * Returns the AST associated with this hybrid editor component.
     * 
     * @return the AST for this hybrid editor. 
     */
    CNode getAst();
    
    /**
     * Access a Language component via its interface.
     *
     * @return the Language interface
     *
     */
    ILanguageComp getLanguage();

    /**
     * Connects to a Language component via its interface. Registers for
     * property change events, retrieves current instance of a Language object and passes its value onto its language-dependent subcomponents.
     * 
     * @param language an instance of the Language component.
     */
    void setLanguage(ILanguageComp language);
    
    /**
     * Returns a NodeFactory object associated with this hybrid editor.
     * 
     * @return CNodeFactory object.
     */
    CNodeFactory getNodeFactory();

    /**
     * Sets the NodeFactory to be used by this hybrid editor. In addition it passes on a nodeFactory
     * instance to CoreHybridEditor and Text2AST subcomponents.
     * 
     * @param nodeFactory the nodefactory to be set.
     */
    void setNodeFactory(CNodeFactory nodeFactory);
    
    /**
     * Access a Presentation component via its interface.
     *
     * @return the Presentation interface
     *
     */
    IPresentationComp getPresentation();

    /**
     * Connects to a Presentation component via its interface. Registers for
     * property change events, retrieves current instance of a Presentation object and passes its value onto its presentation-dependent subcomponents.
     * 
     * @param presentation an instance of the Presentation component.
     */
    void setPresentation(IPresentationComp presentation);
    
   
    
}
