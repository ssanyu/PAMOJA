/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.CoreHybridEditor;

import Components.Abstract.TreeEditor.CTreeEditorComp;
import Components.CPAMOJAComponent;
import Components.Formatter.IAST2TextComp;
import Components.INodeObject;
import Components.Specifications.Language.ILanguageComp;
import Components.Specifications.Presentation.IPresentationComp;
import Components.Text2AST.IText2ASTComp;
import GrammarNotions.SyntaxExpr.CSE;
import GrammarNotions.SyntaxExpr.CSE_MultiDot;
import GrammarNotions.SyntaxExpr.CSE_Sym;
import Nodes.CNode;
import Nodes.CNodeFactory;
import Nodes.CNodeKind;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A small and flexible component providing the core functionality of hybrid editing. It is composed of a TreeEditor component 
 * working on a focused subtree of the overall AST with tree editing commands, and a String object. 
 * Additional functionality is obtained by connecting it to its collaborators, via
 * references and/or the observer mechanism. Collaborators include: Language, Presentation, Text2AST, AST2Text, SyntaxHighlighter and PanelTreeView components, and a NodeFactory.
 * 
 * @see Components.Abstract.TreeEditor.CTreeEditorComp
 * @see Components.Specifications.Language.CLanguageComp
 * @see Components.Specifications.Presentation.CPresentationComp
 * @see Components.Text2AST.CText2ASTComp
 * @see Components.Formatter.CAST2TextComp
 * @see Components.Lexical.SyntaxHighlighter.CSyntaxHighlighterComp
 * @see Components.Abstract.PanelTree.CPanelTreeComp
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CCoreHybridEditorComp extends CPAMOJAComponent implements INodeObject,ICoreHybridEditorComp,PropertyChangeListener{
    private CNodeFactory nodeFactory;
    private ILanguageComp language;
    private IPresentationComp presentation;
    private IText2ASTComp text2ast;
    private IAST2TextComp formatter;
    private CTreeEditorComp treeEditor;
    
    private String text;
    private CNode focus;
    private CNode ast;
    private boolean structualEditing;
    
    /**
     * Creates an instance of a CoreHybridEditor component.
     */
    public CCoreHybridEditorComp() {
        treeEditor=new CTreeEditorComp();
        text=new String();
        ast=treeEditor.getTree();
        focus=treeEditor.getFocus();
        structualEditing=treeEditor.isTreeEditing();
    }

    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("CoreHybridEditor",10, 10);
    } 
    
    /**
     *
     */
    @Override
    public void toText(){
        CNode vFocus;
           vFocus=treeEditor.getFocus();
           if(formatter!=null){
                formatter.setAst(vFocus);
                if(!formatter.getText().isEmpty()){
                    setText(formatter.getText());
                    treeEditor.setTreeEditing(false);
                    setStructureMode(treeEditor.isTreeEditing());
                }
           }
    }
    
    /**
     *
     */
    @Override
    public void toStructure(){
         
         if(treeEditor.getFocus().kind()==CNodeKind.LIST){
              
                text2ast.setNonTerminal(treeEditor.getFocus().sortLabel());
                           
           }else{
                           
                text2ast.setNonTerminal(treeEditor.getAcceptableFocusSort()); // you will use the value of acceptable sort.
                
           }
           
           text2ast.setText(text);
           if(text2ast.getAst()!=null){
                treeEditor.setTreeEditing(true);
                setStructureMode(treeEditor.isTreeEditing());
                treeEditor.replaceSubTree(text2ast.getAst());
                setAst(treeEditor.getTree());
                setFocus(treeEditor.getFocus());
                setText("");
           }else{
               setFocus(null);
           }
          
    }

    /**
     *
     */
    public void abort(){
        treeEditor.setTreeEditing(true);
        setStructureMode(treeEditor.isTreeEditing());
        setText("");
        
    }

    /**
     *
     * @return
     */
    public CTreeEditorComp getTreeEditor() {
        return treeEditor;
    }

    /**
     *
     * @return
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    @Override
    public void setText(String text) {
        // keep the old value
        String oldText=this.text;
        // save the new value
        this.text=text;
        // fire the property change event, with a property that has changed
        support.firePropertyChange("text",oldText,this.text);
    }

    /**
     *
     * @param text
     */
    public void updateText(String text){
        this.text=text;
    }

    /**
     *
     * @return
     */
    public CNode getAst() {
        return ast;
    }

    /**
     *
     * @param ast
     */
    public void setAst(CNode ast) {
        // set the null tree
        CNode oldAst=null;
        // assign the new value
        this.ast=ast;
        // fire the property change event, with a property that has changed
        support.firePropertyChange("ast",oldAst,this.ast);
    }

    /**
     *
     * @return
     */
    public CNode getFocus() {
        return focus;
    }

    /**
     *
     * @param focus
     */
    public void setFocus(CNode focus) {
          
       // keep the old value
        CNode oldFocus=this.focus;
       // save the new value
        this.focus=focus;
       // fire the property change event, with a property that has changed
        support.firePropertyChange("focus",oldFocus,this.focus);
    }
   
   /**
     *
     * @return
     */
    public CNodeFactory getNodeFactory() {
        return nodeFactory;
   }

    /**
     *
     * @param nodeFactory
     */
    public void setNodeFactory(CNodeFactory nodeFactory) {
        
        this.nodeFactory = nodeFactory;
        updateNodeFactoryEnabledComponents(this.nodeFactory);
        if(language!=null){
            setRootHole( language);
        }
    }

    /**
     *
     * @return
     */
    public ILanguageComp getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     */
    @Override
    public void setLanguage(ILanguageComp language){
      if(this.language!=null){
              this.language.removePropertyChangeListener(this);
       }
       this.language=language;
       if(this.language!=null){
              this.language.addPropertyChangeListener(this);
             
       } else {
            this.language.removePropertyChangeListener(this);
       }
       updateLanguageEnabledComponents(this.language);
      
       
    }

    /**
     *
     * @return
     */
    public IPresentationComp getPresentation() {
        return presentation;
    }

    /**
     *
     * @param presentation
     */
    @Override
    public void setPresentation(IPresentationComp presentation){
      if(this.presentation!=null){
              this.presentation.removePropertyChangeListener(this);
       }
       this.presentation=presentation;
       if(this.presentation!=null){
              this.presentation.addPropertyChangeListener(this);
             
       } else {
            this.presentation.removePropertyChangeListener(this);
       }
       updatePresentationEnabledComponents(this.presentation);
     }
    
    /**
     *
     * @return
     */
    @Override
    public IText2ASTComp getText2ast() {
        return text2ast;
    }

    /**
     *
     * @param text2ast
     */
    @Override
    public void setText2ast(IText2ASTComp text2ast){
      if(this.text2ast!=null){
              this.text2ast.removePropertyChangeListener(this);
       }
       this.text2ast=text2ast;
       if(this.text2ast!=null){
              this.text2ast.addPropertyChangeListener(this);
             
       } else {
            this.text2ast.removePropertyChangeListener(this);
       }
       if(language!=null && text2ast.getLanguage()==null)
          text2ast.setLanguage(language);
       if(nodeFactory!=null && text2ast.getNodeFactory()==null)
          text2ast.setNodeFactory(nodeFactory);
    }
    
    /**
     *
     * @return
     */
    @Override
    public IAST2TextComp getFormatter() {
        return formatter;
    }

    /**
     *
     * @param formatter
     */
    @Override
    public void setFormatter(IAST2TextComp formatter){
      if(this.formatter!=null){
              this.formatter.removePropertyChangeListener(this);
       }
       this.formatter=formatter;
       if(this.formatter!=null){
              this.formatter.addPropertyChangeListener(this);
             
       } else {
            this.formatter.removePropertyChangeListener(this);
       }
       if(language!=null && formatter.getLanguage()==null)
         formatter.setLanguage(language);
       if(presentation!=null && formatter.getPresentation()==null)
         formatter.setPresentation(presentation);
    }

    /**
     *
     * @return
     */
    @Override
    public CNode getNode() {
       return ast;
    }
/**
     * Handle property change events from Language and Presentation Components. If the property change is from:
     * <ul>
     * <li> <code>Language</code> - retrieve the Language object</li>
     * <li> <code>Presentation</code> - retrieve the Presentation object</li>
     * </ul>
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
       // String property=evt.getPropertyName();
        if(source==language){
            updateLanguageEnabledComponents(language);
        }else if(source==presentation){
           updatePresentationEnabledComponents(presentation);
        }
    }
    private void updateLanguageEnabledComponents(ILanguageComp language){
       
        treeEditor.setLanguage(language);
        
        if(text2ast!=null && text2ast.getLanguage()==null )
            text2ast.setLanguage(language);
        
        if(formatter!=null && formatter.getLanguage()==null)
           formatter.setLanguage(language);
        
        setRootHole(language);
    }

    private void setRootHole(ILanguageComp language){
        CSE vStartExpr;
        vStartExpr=language.getGrammarComp().getGrammarStructure().getStartExpr();
        assert(vStartExpr instanceof CSE):"CCoreHybridEditorComp.updateLanguageEnabledComponents() failed:Start Expression should be a syntax expression"; 
        CSE_MultiDot vDot=(CSE_MultiDot)vStartExpr;
        assert(vDot instanceof CSE_MultiDot): "Grammar should be augumented"; 
        createNodewithHoles(((CSE_Sym)vStartExpr.getTerm(0)).getName());
    }
    private void updateNodeFactoryEnabledComponents(CNodeFactory nodeFactory){
      treeEditor.setNodeFactory(nodeFactory);
      if(text2ast!=null) {
         text2ast.setNodeFactory(nodeFactory);
         
      }
       
   }
    
   private void updatePresentationEnabledComponents(IPresentationComp presentation){
       if(formatter!=null && formatter.getPresentation()==null){
           formatter.setPresentation(presentation);
       }
       
   }
   private void createNodewithHoles(String aHole){
       CNode vNode=null;
       if(nodeFactory!=null && !aHole.isEmpty()){
           vNode= nodeFactory.makeNodeWithHoles(aHole);
            if(vNode!=null){
               treeEditor.insertFirstItemIntoLists(vNode);
               treeEditor.updateTree(vNode);
               setAst(treeEditor.getTree());
               setFocus(treeEditor.getFocus());
            }
       }
     
   }

    /**
     *
     * @return
     */
    @Override
    public boolean isStructureMode() {
        return structualEditing;
    }

    /**
     *
     * @param structualEditing
     */
    @Override
    public void setStructureMode(boolean structualEditing) {
        // keep the old value
        boolean oldStructualEditing=this.structualEditing;
       // save the new value
        this.structualEditing=structualEditing;
       // fire the property change event, with a property that has changed
        support.firePropertyChange("structualEditing",oldStructualEditing,this.structualEditing);
        
    }
   
    /**
     *
     * @param template
     */
    @Override
 public void add(String template){
     treeEditor.add(template);
     setAst(treeEditor.getTree());
 }  
 
    /**
     *
     */
    @Override
 public void remove(){
     treeEditor.remove();
     setAst(treeEditor.getTree());
 }
 
    /**
     *
     * @param aNode
     */
    @Override
 public void replace(CNode aNode){
    treeEditor.replace(aNode);
    setAst(treeEditor.getTree()); 
 }
 
    /**
     *
     * @param aNode
     */
    @Override
    public void replaceSubTree(CNode aNode){
    treeEditor.replaceSubTree(aNode);
    setAst(treeEditor.getTree());  
 }
 
    /**
     *
     * @param aSelectedTemplate
     */
    public void replace(String aSelectedTemplate){
    treeEditor.replace( aSelectedTemplate);
    setAst(treeEditor.getTree());   
 }

    /**
     *
     * @param aNode
     * @param aData
     */
    public void replace(CNode aNode, String aData){
    treeEditor.replace( aNode, aData);
    setAst(treeEditor.getTree());   
 }

    /**
     *
     */
    public void delete(){
     treeEditor.delete();
     setAst(treeEditor.getTree());
 }

    /**
     *
     */
    public void clear(){
     treeEditor.clear();
     setAst(treeEditor.getTree());
 }

    /**
     *
     * @param aTemplate
     * @param vFocus
     */
    public void insertBefore(String aTemplate,CNode vFocus){
     treeEditor.insertBefore(aTemplate,vFocus);
     setAst(treeEditor.getTree());  
 }

    /**
     *
     * @param aTemplate
     * @param vFocus
     */
    public void insertAfter(String aTemplate,CNode vFocus){
     treeEditor.insertAfter(aTemplate,vFocus);
     setAst(treeEditor.getTree());  
 }
    
    /**
     *
     * @param aTemplate
     */
    public void setPresent(String aTemplate){
     treeEditor.setPresent(aTemplate);
     setAst(treeEditor.getTree());  
 }   

    /**
     *
     */
    public void setAbsent(){
     treeEditor.setAbsent();
     setAst(treeEditor.getTree());  
 }
  
}
