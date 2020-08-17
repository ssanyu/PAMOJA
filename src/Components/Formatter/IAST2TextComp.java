/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Formatter;


import Components.IPAMOJAComponent;
import Components.Specifications.Language.ILanguageComp;
import Components.Specifications.Presentation.IPresentationComp;
import Nodes.CNode;
import SymbolStream.CSymbolStream;

/**
 * Defines services for interacting with other components like, Language and Presentation.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IAST2TextComp extends IPAMOJAComponent{
    
    /**
     * Returns the text corresponding to the AST.
     * @return text corresponding to the AST.
     */
    String getText();

    /**
     * Returns the symbol stream obtained from the AST.
     * @return symbol stream obtained from the AST.
     */
    CSymbolStream getSymbolStream();

    /**
     * Returns the AST.
     * @return the AST node.
     */
    CNode getAst();

    /**
     * Sets the specified AST to this Formatter component.
     * @param AST the specified AST.
     */
    void setAst(CNode AST);

    /**
     * Returns a boxtree obtained from the AST
     * @return a boxtree obtained from the AST
     */
    CNode getBoxTree();

    /**
     * Returns a reference to the Language component.
     * @return  interface to the Language component.
     */
    ILanguageComp getLanguage();

    /**
     * Connects to the Language component via the given reference.
     * @param aLanguage a reference to the Language component.
     */
    void setLanguage(ILanguageComp aLanguage);

     /**
     * Returns a reference to the Presentation component.
     * @return  interface to the Presentation component.
     */
    IPresentationComp getPresentation();

   /**
     * Connects to the Presentation component via the given reference.
     * @param aPresentation a reference to the Presentation component.
     */
    void setPresentation(IPresentationComp aPresentation);
}
