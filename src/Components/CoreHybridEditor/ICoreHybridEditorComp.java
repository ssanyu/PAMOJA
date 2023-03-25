/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.CoreHybridEditor;

import Components.Abstract.TreeEditor.CTreeEditorComp;
import Components.Formatter.IAST2TextComp;
import Components.IPAMOJAComponent;
import Components.Specifications.Language.ILanguageComp;
import Components.Specifications.Presentation.IPresentationComp;
import Components.Text2AST.IText2ASTComp;
import Nodes.CNode;
import Nodes.CNodeFactory;

/**
 * Defines services for interacting with other components and classes ( like, Language, Presentation, Text2AST, AST2Text, SyntaxHighlighter, PanelTreeView components and NodeFactory class).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ICoreHybridEditorComp extends IPAMOJAComponent{

    /**
     * Sets the specified AST to this CoreHybridEditor component.
     * @param ast an AST specified.
     */
    void setAst(CNode ast);

    /**
     * Returns an AST held by this CoreHybridEditor component.
     * @return an AST held by this CoreHybridEditor component
     */
    CNode getAst();
    
    /**
     * Sets the specified node as the focus.
     * 
     * @param focus the specified node.
     */
    void setFocus(CNode focus);

    /**
     * Returns the focus.
     * @return the current focus.
     */
    CNode getFocus();
    
    /**
     * Sets the specified text as the text corresponding to the focus.
     * @param text the text of the focus.
     */
    void setText(String text);

    /**
     * Returns the focused text.
     * @return the text for the focus.
     */
    String getText();

    /**
     * Updates the focused text with the specified text.
     * @param text the specified text. 
     */
    void updateText(String text);
    
    /**
     * Returns a TreeEditor contained in this CoreHybridEditor component.
     * @return  the TreeEditor component.
     */
    public CTreeEditorComp getTreeEditor();
    /**
     * Returns a reference to the Language component.
     * @return an interface to the Language component.
     */
    ILanguageComp getLanguage();

    /**
     * Connects to the Language component via the specified interface.
     * @param language the interface to the Language component.
     */
    void setLanguage(ILanguageComp language);
    
     /**
     * Returns a reference to the Text2AST component.
     * @return an interface to the Text2AST component.
     */
    IText2ASTComp getText2ast();

     /**
     * Connects to the Text2AST component via the specified interface.
     * @param text2ast the interface to the Text2AST component.
     */
    void setText2ast(IText2ASTComp text2ast);
    
      /**
     * Returns a reference to the Formatter component.
     * @return an interface to the Formatter component.
     */
    IAST2TextComp getFormatter();

    /**
     * Connects to the Formatter component via the specified interface.
     * @param formatter the interface to the Formatter component.
     */
    void setFormatter(IAST2TextComp formatter);
    
      /**
     * Returns a reference to the NodeFactory.
     * @return an instance of a NodeFactory.
     */
    CNodeFactory getNodeFactory();

    /**
     * Sets the specified nodefactory to this editor.
     * @param nodeFactory amn instance of a nodefactory.
     */
    void setNodeFactory(CNodeFactory nodeFactory);
    
      /**
     * Returns a reference to the Presentation component.
     * @return an interface to the Presentation component.
     */
    IPresentationComp getPresentation();

     /**
     * Connects to the Presentation component via the specified interface.
     * @param presentation the interface to the Presentation component.
     */
    void setPresentation(IPresentationComp presentation);
 
    /**
     *
     */
        void remove();

    /**
     *
     * @param aNode
     */
    void replace(CNode aNode);

    /**
     *
     * @param aNode
     */
    void replaceSubTree(CNode aNode);

    /**
     *
     * @param aSelectedTemplate
     */
    void replace(String aSelectedTemplate);

    /**
     *
     * @param aNode
     * @param aData
     */
    void replace(CNode aNode, String aData);

    /**
     *
     */
    void delete();

    /**
     *
     */
    void clear();

    /**
     *
     * @param aSelectedTemplate
     */
   void add(String aSelectedTemplate);

    /**
     *
     * @param aTemplate
     * @param vFocus
     */
    void insertBefore(String aTemplate,CNode vFocus);

    /**
     *
     * @param aTemplate
     * @param vFocus
     */
    void insertAfter(String aTemplate,CNode vFocus);

    /**
     *
     * @param aSelectedTemplate
     */
    void setPresent(String aSelectedTemplate);

    /**
     * Sets a focused optional node to absent.
     */
    void setAbsent();
    
    /**
     * Switches the editor from structure mode to text mode.
     */
    void toText();

    /**
     * Switches the editor from text mode to structure mode.
     */
    void toStructure();

    /**
     * Aborts text editing and switches the editor from text mode to structure mode.
     */
    void abort();
    
    /**
     * Determines if the editor is in structure mode.
     * 
     * @return <code>true</code> if in structure mode, else <code>false</code> 
     */
    boolean isStructureMode();

    /**
     * Sets the structureMode of the editor.
     * 
     * @param structureMode if the editor is in structure editing mode then <code>true</code>, else <code>false</code>.
     */
    void setStructureMode(boolean structureMode);
  
}
