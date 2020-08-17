/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.PanelTree;

import Boxes.CArgDataBox;
import Boxes.CBox;
import Boxes.CBoxesSortCodes;
import Boxes.CHorBox;
import Boxes.CIndBox;
import Boxes.CSelBox;
import Boxes.CTermBox;
import Boxes.CTermDataBox;
import Boxes.CVerBox;
import Components.Abstract.AST2BoxTree.CAST2BoxTreeComp;
import Components.CoreHybridEditor.ICoreHybridEditorComp;
import Components.IPAMOJAComponent;
import Components.Specifications.Language.ILanguageComp;
import Components.Specifications.Presentation.CSymbolStyleCustomizerStructure;
import Components.Specifications.Presentation.IPresentationComp;
import GrammarNotions.RegExpr.CLexemeDef_List;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.RegExpr.CRE_Char;
import GrammarNotions.RegExpr.CRE_MultiStick;
import GrammarNotions.SyntaxExpr.CTerminalDef_List;
import Nodes.CNode;
import Nodes.CNodeKind;
import Nodes.INodeList;
import Nodes.INodeOption;
import Patterns.CPatterns;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.IllegalComponentStateException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 * A special kind of tree editor which displays an AST of a program as a hierarchy of nested panels and allows graphical editing of the AST.
 * <p>
 * In each panel it writes the program text specific to that panel. It is composed of two subcomponents:
 * <ul>
 * <li> <code>AST2BoxTree</code> component - a mapping which yields an intermediate box tree which acts as a
building plan for the panel tree.</li>
 * <li> <code>Box2PanelTree</code> component - a mapping from a box tree to a panel tree.</li>
 * </ul>
 * A PanelTree component collaborates with a <code>Language</code> and a <code>Presentation</code> component to generate structured formatted text. 
 * When it observes a change in the AST of a <code>TreeEditor</code> it regenerates the panel tree to correspond to the structure of
 * the AST. Additionally, it interacts with a <code> TreeEditor</code> to allow structural
 * editing of programs using mouse operations.
 * 
 * @see Components.Abstract.AST2BoxTree.CAST2BoxTreeComp
 * @see Components.Specifications.Language.CLanguageComp
 * @see Components.Specifications.Presentation.CPresentationComp
 * @see Components.Abstract.TreeEditor.CTreeEditorComp
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CPanelTreeComp extends javax.swing.JPanel implements Serializable,IPAMOJAComponent,IPanelTreeComp,PropertyChangeListener{
    private CPanel rootPanel; // will store the output of boxTree2PanelTree(CBox aBox)
    private ColorShades colorShades;
    private boolean darkening;
    private int hSpace;
    private int vSpace;
    private Dimension labelSize;
    private Dimension childSize;
    private Dimension horGroupSize;
    private Dimension verGroupSize=new Dimension();
    private Color textColor;
    private Font fontStyle;
    private boolean structuralMode;
    private CNode ast;
        
    private CAST2BoxTreeComp boxTree;
    private IPresentationComp presentation;
    private ILanguageComp language;
    private ICoreHybridEditorComp hybridEditor;
     
    private CBox boxTreeStructure;
    private CTerminalDef_List TerminalDefns;
    private CLexemeDef_List lexemeDefns;
    private CPatterns MenuPatternDefns;
    private CSymbolStyleCustomizerStructure symbolStyle;
    private HashMap<String,CNode> templates;
     
   // private JScrollPane sp;
    private PropertyChangeSupport support= new PropertyChangeSupport(this);  
    

     /**
     * Creates new PanelTree component.
     */
    public CPanelTreeComp() {
        super();
        initComponents();
        
        setLayout(null);
        ast=null;
        rootPanel=null;
        colorShades=new ColorShades();
        darkening = false;
        structuralMode=true;
        TerminalDefns=new CTerminalDef_List();
        lexemeDefns=new CLexemeDef_List();
        symbolStyle=new CSymbolStyleCustomizerStructure();
        templates=new HashMap<String,CNode>();
        boxTree=new CAST2BoxTreeComp();
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Returns a box tree associated with this panel tree.
     * 
     * @return the box tree for this panel tree.
     */
    public CBox getBoxTreeStructure() {
        return boxTreeStructure;
    }

    /**
     * Assigns the given box tree to this panel tree.
     * 
     * @param boxTreeStructure the box tree assigned.
     */
    public void setBoxTreeStructure(CBox boxTreeStructure) {
        this.boxTreeStructure = boxTreeStructure;
    }

    /**
     * Returns the panel containing the panel tree.
     * 
     * @return the container panel for the panel tree.
     */
    public CPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * Sets the given container panel for this panel tree.
     * 
     * @param rootPanel the container panel to be set.
     */
    public void setRootPanel(CPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    /**
     * Returns a table of language-construct templates allowable at the focused node.
     * 
     * @return a HashMap of templates.
     */
    public HashMap<String, CNode> getTemplates() {
        return templates;
    }

    /**
     * Sets the given HashMap of language-construct templates allowable at a focused node.
     * 
     * @param templates the given HashMap of templates.
     */
   public void setTemplates(HashMap<String, CNode> templates) {
        this.templates = templates;
        
    }

    /**
     * Returns the AST associated with this panel tree.
     * 
     * @return the AST of this panel tree.
     */
    public CNode getAst() {
        return ast;
    }

    /**
     * Sets the AST of this panel tree.
     * 
     * @param ast the AST to be set.
     */
    public void setAst(CNode ast) {
        this.ast = ast;
      
    }

    /**
     * Determines if the Panel tree component is in editing mode.
     * 
     * @return <code>true</code> if the panel tree is in editing mode, otherwise <code>false</code>.
     */
    public boolean isStructuralMode() {
        return structuralMode;
    }

    /**
     * Sets the structuralMode of the panel tree.
     * 
     * @param structuralMode if the panel tree is in editing mode then <code>true</code>, if not in editing mode, <code>false</code>.
     */
    public void setStructuralMode(boolean structuralMode) {
        this.structuralMode = structuralMode;
    }

    /**
     * Returns pattern definitions for formatting the popup menu items.
     * 
     * @return the pattern definitions for the popup menu items.
     */
    public CPatterns getMenuPatternDefns() {
        return MenuPatternDefns;
    }

    /**
     * Sets the pattern definitions for formatting the popup menu items.
     * 
     * @param MenuPatternDefns the pattern definitions to set.
     */
    public void setMenuPatternDefns(CPatterns MenuPatternDefns) {
        this.MenuPatternDefns = MenuPatternDefns;
    }

    /**
     * Returns the SymbolStyleCustomizer object associated with this panel tree.
     * 
     * @return the SymbolStyleCustomizer object
     * @see CSymbolStyleCustomizerStructure
     */
    public CSymbolStyleCustomizerStructure getSymbolStyle() {
        return symbolStyle;
    }

    /**
     * Sets the SymbolStyleCustomizer object associated with this panel tree.
     * 
     * @param symbolStyle the SymbolStyleCustomizer object to set.
     */
    public void setSymbolStyle(CSymbolStyleCustomizerStructure symbolStyle) {
        this.symbolStyle = symbolStyle;
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * Returns a list of terminal definitions of the language associated with this panel tree.
     * 
     * @return the list of terminal definitions.
     */
    
    public CTerminalDef_List getTerminalDefns() {
        return TerminalDefns;
    }

    /**
     * Sets a list of terminal definitions of the language associated with this panel tree.
     * 
     * @param TerminalDefns the list of terminal definitions to set.
     */
    public void setTerminalDefns(CTerminalDef_List TerminalDefns) {
        this.TerminalDefns = TerminalDefns;
    }

    /**
     * Returns a list of lexeme definitions of the language associated with this panel tree.
     * 
     * @return the list of lexeme definitions.
     */
    public CLexemeDef_List getLexemeDefns() {
        return lexemeDefns;
    }

    /**
     * Sets a list of lexeme definitions of the language associated with this panel tree.
     * 
     * @param lexemeDefns the list of lexeme definitions to set.
     */
    public void setLexemeDefns(CLexemeDef_List lexemeDefns) {
        this.lexemeDefns = lexemeDefns;
    }
   
     /**
     * Access a Presentation component via its interface.
     *
     * @return the Presentation interface
     */
    public IPresentationComp getPresentation() {
        return presentation;
    }

    /**
     * Connect to a Presentation component via its interface.
     * Register for property change events, retrieve box patterns and symbol styles from the Presentation component and (re)create this panel tree.
     *
     * @param aPresentation IPresentationComp object.
     */
    @Override
    public void setPresentation(IPresentationComp aPresentation) {
        if(presentation!=null){
              presentation.removePropertyChangeListener(this);
       }
      presentation=aPresentation;
       if(presentation!=null){
              presentation.addPropertyChangeListener(this);
             
       } else {
            presentation.removePropertyChangeListener(this);
       }
       setMenuPatternDefns(presentation.getPatternsComp().getMenuPatternsStructure());
       setSymbolStyle(presentation.getSymbolStyleCustomizerStructure());
       boxTree.setPatterns(presentation.getPatternsComp());
       createPanelTree();
    }
    
   /**
     * Access a Language component via its interface.
     *
     * @return the Language interface
     *
     */
    @Override
    public ILanguageComp getLanguage() {
        return language;
    }

    /**
     * Connect to a Language component via its interface.
     * Register for property change events, retrieve the lexeme and terminal definitions from the Language component and (re)create this panel tree.
     *
     * @param aLanguage ILanguageComp object.
     */
    @Override
    public void setLanguage(ILanguageComp aLanguage) {
         CTerminalDef_List vTerminalDefns;
         CLexemeDef_List vLexemeDefns;
       if(language!=null){
              language.removePropertyChangeListener(this);
       }
       language=aLanguage;
       if(language!=null){
           language.addPropertyChangeListener(this);
           vTerminalDefns=language.getGrammarComp().getGrammarStructure().getGrammarContext().getTerminalDefs();
           vLexemeDefns=language.getGrammarComp().getGrammarStructure().getGrammarContext().getLexemeDefs();
       }else{
           vTerminalDefns= new  CTerminalDef_List();
           vLexemeDefns= new  CLexemeDef_List();
       }
       setTerminalDefns(vTerminalDefns);
       setLexemeDefns(vLexemeDefns);
       createPanelTree();
    }
    
    /**
     * Access a CoreHybridEditor component via its interface.
     *
     * @return the CoreHybridEditor interface
     *
     */
    @Override
    public ICoreHybridEditorComp getHybridEditor(){
        return hybridEditor;
    }
    
    /**
     * Connect to a CoreHybridEditor component via its interface.
     * Register for property change events, retrieve the AST from the CoreHybridEditor component and (re)create this panel tree.
     *
     * @param aHybridEditor ICoreHybridEditorComp object.
     */
    @Override
    public void setHybridEditor(ICoreHybridEditorComp aHybridEditor){
       CNode vNode=null;
       if(hybridEditor!=null){
              hybridEditor.removePropertyChangeListener(this);
       }
       hybridEditor=aHybridEditor;
       if(hybridEditor!=null){
              hybridEditor.addPropertyChangeListener(this);
              vNode=hybridEditor.getAst();
       } 
       setAst(vNode);
       createPanelTree();
   }
    
   /**
     * Creates a panel tree.
     */
   public void createPanelTree(){
     
     boxTreeStructure=boxTree.createBoxTree(ast);
     if(boxTreeStructure!=null && language!=null &&TerminalDefns!=null){
         if(presentation!=null && presentation.getSymbolStyleCustomizerStructure()!=null)
             createPanelTreeWithSymbolStyles();
         else createPanelTreeWithoutSymbolStyles();
     }
     
   }

    /**
     * Creates a panel tree with syntax highlighting.
     */
    public void createPanelTreeWithSymbolStyles(){
       showPanelTree(boxTreeStructure, TerminalDefns);
            
    }

    /**
     * Creates a panel tree without syntax highlighting.
     */
    public void createPanelTreeWithoutSymbolStyles(){
        fontStyle=new Font("Monospaced",Font.PLAIN,13);
        textColor=Color.black;
        showPanelTree(boxTreeStructure, TerminalDefns);
           
   }
    
     /**
     * Handle property change events from CoreHybridEditor, Language and Presentation Components. If the property change is from:
     * <ul>
     * <li> <code>CoreHybridEditor</code> - if property is <code>ast</code> or <code>focus</code>retrieve its AST and update the panel tree. 
     *      If property is <code>structualEditing</code>, update the panel tree mode. </li> 
     * <li> <code>Language</code> - retrieve the lexeme and terminal definitions from the Language component and (re)create this panel tree.</li>
     * <li> <code>Presentation</code> - retrieve box patterns and symbol styles from the Presentation component and (re)create this panel tree.</li>
     * </ul>
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        Object source = evt.getSource();
        String property=evt.getPropertyName();
        CTerminalDef_List vTerminalDefns;
        CLexemeDef_List vLexemeDefns;
        if (source==hybridEditor){
            if(property.equals("ast")||property.equals("focus")){
              setAst(hybridEditor.getAst());
              setTemplates(hybridEditor.getTreeEditor().getTemplates());
              createPanelTree();
             }else if(property.equals("structualEditing")){
               setStructuralMode(hybridEditor.isStructureMode());
            }
        }else if(source==language){
            vTerminalDefns=language.getGrammarComp().getGrammarStructure().getGrammarContext().getTerminalDefs();
            vLexemeDefns=language.getGrammarComp().getGrammarStructure().getGrammarContext().getLexemeDefs();
            setTerminalDefns(vTerminalDefns);
            setLexemeDefns(vLexemeDefns);
            createPanelTree();
        }else if(source==presentation){
            setMenuPatternDefns(presentation.getPatternsComp().getMenuPatternsStructure());
            setSymbolStyle(presentation.getSymbolStyleCustomizerStructure());
            boxTree.setPatterns(presentation.getPatternsComp());
            createPanelTree();
        }
        
    }
    
  /*  void display(CPanel aRoot){
        System.out.println(aRoot +"=="+ aRoot.noOfChildren());
        for(int i=0;i<aRoot.noOfChildren();i++){
            display(aRoot.getChild(i));
        }
        
    }*/
    
   /**
    * Add a PropertyChangeListener to the listener list. The listener is registered for all properties.
    * 
    * @param l the PropertyChangeListener to be added 
    */
   @Override
   public synchronized void addPropertyChangeListener(PropertyChangeListener l){
       if(support!=null){
          support.addPropertyChangeListener(l);
       }
   }
   
   /**
    * Remove a PropertyChangeListener from the listener list. This removes a PropertyChangeListener that was registered for all properties, and has no effect if registered for only one or more specified properties. 
    * 
    * @param l the PropertyChangeListener to be removed
    */
   @Override
   public synchronized void removePropertyChangeListener(PropertyChangeListener l){
       if(support!=null){
         support.removePropertyChangeListener(l);
       }
   }
  
    /**
     * Recursively maps a given box tree to a panel tree using the given terminal definitions of a language.
     * 
     * @param aBox the box tree to map.
     * @param aTerminalDefns the terminal definitions to used.
     * @return the panel tree generated from the box tree.
     */
    public CPanel boxTree2PanelTree(CBox aBox,CTerminalDef_List aTerminalDefns){
     CPanel vResult=new CPanel(this);
     int vX, vY;
     vResult.setSelectable(true);
    
     Insets insets=vResult.getInsets();
     vX=insets.left;
     vY=insets.top;
    
     CSelBox aSel=(CSelBox)aBox; 
     
     vResult.setLayoutPatterns(aSel); // assign a panel its box pattern layout so that you can get its formated text
     vResult.setNode(aSel.getNode()); // assign a panel its corresponding ast
     if(!(aSel.getarg() instanceof CSelBox) )
         aSel.getarg().setNode(aSel.getNode());
    // System.out.println(aSel.getarg() + " "+aSel.getNode());
     format(aSel.getarg(),aTerminalDefns,vResult,vX,vY);
    // System.out.println("Max: "+vResult.getMaximumSize());
    // System.out.println("Min: "+vResult.getMinimumSize());
    // System.out.println("Pre: "+vResult.getPreferredSize());
     return vResult;
  } 

    /**
     * An auxiliary method with facilities for mapping a given box tree to a panel, writing text pieces in a panel and placing the panels in their parent containers at 
     * the specified points.
     * 
     * @param aBox the box tree to be mapped.
     * @param aTerminalDefns the terminal definitions to used.
     * @param aResult the resultant panel after mapping.
     * @param aX the x coordinate
     * @param aY the y coordinate
     */
    public void format(CBox aBox,CTerminalDef_List aTerminalDefns, CPanel aResult,int aX,int aY){
     CPanel vChild; 
     JLabel vLabel;
     int vInd=6;
     int vMaxW=0;
     switch(aBox.sortCode()){
              case CBoxesSortCodes.scSelBox:
                 CSelBox aSel=(CSelBox)aBox;
                 childSize=new Dimension();
                 vChild=boxTree2PanelTree(aSel,aTerminalDefns);
                 if(aSel.getarg() instanceof CTermDataBox){
                    vChild.setPreferredSize(labelSize);
                    vChild.revalidate();
                    vChild.repaint(); 
                 }
                 childSize=vChild.getPreferredSize();
                 vChild.setBounds(aX,aY,childSize.width,childSize.height);
                 aResult.addChild(vChild);
                 aResult.setPreferredSize(childSize);
                 aResult.revalidate();
                 aResult.repaint();
                 
              break;
             
              case CBoxesSortCodes.scIndBox:
                    CIndBox aInd=(CIndBox)aBox;
                    childSize=new Dimension();
                    vChild=boxTree2PanelTree(aInd.getarg(),aTerminalDefns);
                    if(aInd.getarg() instanceof CSelBox && ((CSelBox)aInd.getarg()).getNode()!=null && ((CSelBox)aInd.getarg()).getNode().kind()==CNodeKind.OPTION){
                      if(((CSelBox)aInd.getarg()).getarg() instanceof CTermBox) { 
                      childSize=labelSize;
                      vChild.setPreferredSize(childSize);
                      aX=aX+vInd;
                      vChild.setBounds(aX,aY,childSize.width,childSize.height);
                      }else{
                        childSize=vChild.getPreferredSize();
                        aX=aX+vInd;
                        vChild.setBounds(aX,aY,childSize.width,childSize.height);  
                      }
                    }else{    
                       childSize=vChild.getPreferredSize();
                       aX=aX+vInd;
                       vChild.setBounds(aX,aY,childSize.width,childSize.height);
                    }
                    aResult.addChild(vChild);
                    
                    break;
              case CBoxesSortCodes.scTermBox:
                   CTermBox aTerm=(CTermBox)aBox;
                   labelSize=new Dimension();
                   String vSym;
                   if(aBox.getNode()!=null && aBox.getNode().kind()==CNodeKind.OPTION){
                       vLabel=new JLabel("+"); 
                       vLabel.setForeground(Color.DARK_GRAY);
                       // create a line border with the specified color and width
                       Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
                       // set the border of this component
                       vLabel.setBorder(border);
                     //  if(isStructuralMode()){//  this does not work first time
                       vLabel.setToolTipText("Add a "+((INodeOption)aBox.getNode()).sortLabel());
                       vLabel.addMouseListener(new MouseAdapter(){  
                       public void mouseClicked(MouseEvent e){  
                         if(isStructuralMode()){
                          select((CPanel)((JLabel)e.getSource()).getParent());
                          showPopupMenu(e);
                         }
                     }  
                      }); 
                    //  } 
                      labelSize=vLabel.getPreferredSize();
                      labelSize=new Dimension(labelSize.width,labelSize.height/2);
                      vLabel.setPreferredSize(labelSize);
                      vLabel.setBounds(aX,aY,labelSize.width,labelSize.height);
                      aResult.add(vLabel);
                   }else{
                    if(symbolStyle.indexOfSymbol(aTerm.getsymName())!=-1){
                        textColor=symbolStyle.symbolNameToColor(aTerm.getsymName());
                        fontStyle=symbolStyle.symbolNameToFont(aTerm.getsymName());
                    }
                   vSym=getSymbol(aTerm.getsymName(),aTerminalDefns);
                   vLabel=new JLabel(vSym);
                   vLabel.setFont(fontStyle);
                   vLabel.setForeground(textColor);
                   labelSize=vLabel.getPreferredSize();
                   vLabel.setBounds(aX,aY,labelSize.width,labelSize.height);
                   aResult.add(vLabel);
                   }
                   break;                                                    
               case CBoxesSortCodes.scTermDataBox:
                  CTermDataBox aTermData=(CTermDataBox)aBox;
                  labelSize=new Dimension();
                  if(symbolStyle.indexOfSymbol(aTermData.getsymName())!=-1){
                   textColor=symbolStyle.symbolNameToColor(aTermData.getsymName());
                   fontStyle=symbolStyle.symbolNameToFont(aTermData.getsymName());
                  }
                  JLabel iLabel=new JLabel(aTermData.getdata());
                  iLabel.setFont(fontStyle);
                  iLabel.setForeground(textColor);
                  labelSize=iLabel.getPreferredSize();
                  iLabel.setBounds(aX,aY,labelSize.width, labelSize.height);
                  aResult.add(iLabel);
                  aResult.setPreferredSize(labelSize);
                  aResult.revalidate();
                  aResult.repaint(); 
                  break;  
                case CBoxesSortCodes.scHorBox:
                   CHorBox aHor=(CHorBox)aBox;
                   childSize=new Dimension();
                   horGroupSize=new Dimension();  
                   CBox vBox; 
                   
                   if(aHor.getlist().termCount()>0){                               
                   for(int i=0;i<aHor.getlist().termCount()-1;i++){
                       vBox=aHor.getlist().getElt(i);
                       if(vBox instanceof CSelBox){
                            vChild= boxTree2PanelTree(vBox,aTerminalDefns);
                            if(((CSelBox)vBox).getarg() instanceof CTermDataBox){
                               vChild.setPreferredSize(labelSize);
                               vChild.revalidate();
                               vChild.repaint(); 
                            
                               childSize=vChild.getPreferredSize();
                               vChild.setBounds(aX,aY,childSize.width,childSize.height);
                               aResult.addChild(vChild);
                               aResult.revalidate();
                               aResult.repaint(); 
                               hSpace=aHor.gethSpace();
                               if(hSpace>0){hSpace=hSpace*5;}
                               aX=aX+childSize.width+hSpace;
                               horGroupSize.height=Math.max(horGroupSize.height,childSize.height);
                            }else if (((CSelBox)vBox).getarg() instanceof CTermBox  ){
                              if(((CSelBox)vBox).getarg().getNode() !=null && ((CSelBox)vBox).getNode().kind()==CNodeKind.OPTION){
                                    vChild.setPreferredSize(labelSize);
                                    vChild.revalidate();
                                    vChild.repaint(); 
                                    childSize=vChild.getPreferredSize();
                                    int aY1= labelSize.height/2;
                                    vChild.setBounds(aX,aY1+2,childSize.width,childSize.height);
                                    aResult.addChild(vChild);
                                    aResult.revalidate();
                                    aResult.repaint(); 
                                    hSpace=aHor.gethSpace();
                                    if(hSpace>0){hSpace=hSpace*5;}
                                    aX=aX+childSize.width+hSpace;
                                    horGroupSize.height=Math.max(horGroupSize.height,childSize.height);
                                }else{
                                vChild.setPreferredSize(labelSize);
                                vChild.revalidate();
                                vChild.repaint(); 
                            
                                childSize=vChild.getPreferredSize();
                                vChild.setBounds(aX,aY,childSize.width,childSize.height);
                                aResult.addChild(vChild);
                                aResult.revalidate();
                                aResult.repaint(); 
                                hSpace=aHor.gethSpace();
                                if(hSpace>0){hSpace=hSpace*5;}
                                aX=aX+childSize.width+hSpace;
                                horGroupSize.height=Math.max(horGroupSize.height,childSize.height);
                                
                               }
                            }else{
                               childSize=vChild.getPreferredSize();
                               vChild.setBounds(aX,aY,childSize.width,childSize.height);
                               aResult.addChild(vChild);
                               aResult.revalidate();
                               aResult.repaint(); 
                               hSpace=aHor.gethSpace();
                               if(hSpace>0){hSpace=hSpace*5;}
                               aX=aX+childSize.width+hSpace;
                               horGroupSize.height=Math.max(horGroupSize.height,childSize.height);
                            }
                       }else if(vBox instanceof CTermBox){
                             if(((CTermBox)vBox).getsymName().length()!=0){
                                  format(vBox,aTerminalDefns,aResult,aX,aY);
                                  hSpace=aHor.gethSpace();
                                  if(hSpace>0){hSpace=hSpace*5;}
                                  aX=aX+labelSize.width+hSpace;
                                  horGroupSize.height=Math.max(horGroupSize.height,labelSize.height);
                             }else{
                            //do nothing  
                             }
                       }else{ 
                         
                         format(vBox,aTerminalDefns,aResult,aX,aY);
                         hSpace=aHor.gethSpace(); 
                         if(hSpace>0){hSpace=hSpace*5;}
                         if(vBox instanceof CTermDataBox){
                            aX=aX+labelSize.width+hSpace;
                            horGroupSize.height=Math.max(horGroupSize.height,labelSize.height);
                         }else if(vBox instanceof CIndBox){
                            aX=aX+(childSize.width+vInd)+hSpace;
                            horGroupSize.height=Math.max(horGroupSize.height,childSize.height);
                         }else if(vBox instanceof CHorBox){
                            aX=aX+this.horGroupSize.width+hSpace;
                            horGroupSize.height=Math.max(horGroupSize.height,this.horGroupSize.height);
                         }else if(vBox instanceof CVerBox){
                            aX=aX+verGroupSize.width+hSpace;
                            horGroupSize.height=Math.max(horGroupSize.height,verGroupSize.height);
                        }
                      }
                    }
                   
                   //get the last element
                      vBox=aHor.getlist().getElt(aHor.getlist().termCount()-1);
                      if(vBox instanceof CSelBox){
                            vChild= boxTree2PanelTree(vBox,aTerminalDefns);
                            if(((CSelBox)vBox).getarg() instanceof CTermDataBox){
                               vChild.setPreferredSize(labelSize);
                               vChild.revalidate();
                               vChild.repaint(); 
                               childSize=vChild.getPreferredSize();
                               vChild.setBounds(aX,aY,childSize.width,childSize.height);
                               aResult.addChild(vChild);
                               aResult.revalidate();
                               aResult.repaint(); 
                              // hSpace=aHor.gethSpace();
                              // if(hSpace>0){hSpace=hSpace*5;}
                               aX=aX+childSize.width;//+hSpace;
                               horGroupSize.height=Math.max(horGroupSize.height,childSize.height);
                            }else if (((CSelBox)vBox).getarg() instanceof CTermBox  ){
                              if(((CSelBox)vBox).getarg().getNode() !=null && ((CSelBox)vBox).getNode().kind()==CNodeKind.OPTION){
                                    vChild.setPreferredSize(labelSize);
                                    vChild.revalidate();
                                    vChild.repaint(); 
                                    childSize=vChild.getPreferredSize();
                                    int aY1= labelSize.height/2;
                                    vChild.setBounds(aX,aY1+2,childSize.width,childSize.height);
                                    aResult.addChild(vChild);
                                   // hSpace=aHor.gethSpace();
                                  //  if(hSpace>0){hSpace=hSpace*5;}
                                    aX=aX+childSize.width;//+hSpace;
                                    horGroupSize.height=Math.max(horGroupSize.height,childSize.height);
                                }else{
                                vChild.setPreferredSize(labelSize);
                                vChild.revalidate();
                                vChild.repaint(); 
                            
                                childSize=vChild.getPreferredSize();
                                vChild.setBounds(aX,aY,childSize.width,childSize.height);
                                aResult.addChild(vChild);
                               // hSpace=aHor.gethSpace();
                               // if(hSpace>0){hSpace=hSpace*5;}
                                aX=aX+childSize.width;//+hSpace;
                                horGroupSize.height=Math.max(horGroupSize.height,childSize.height);
                               }
                            }else{
                               childSize=vChild.getPreferredSize();
                               vChild.setBounds(aX,aY,childSize.width,childSize.height);
                               aResult.addChild(vChild);
                               aResult.revalidate();
                               aResult.repaint(); 
                              // hSpace=aHor.gethSpace();
                              // if(hSpace>0){hSpace=hSpace*5;}
                               aX=aX+childSize.width;//+hSpace;
                               horGroupSize.height=Math.max(horGroupSize.height,childSize.height);
                            }
                       }else if(vBox instanceof CTermBox){
                             if(((CTermBox)vBox).getsymName().length()!=0){
                                  format(vBox,aTerminalDefns,aResult,aX,aY);
                                  aX=aX+labelSize.width;
                                  horGroupSize.height=Math.max(horGroupSize.height,labelSize.height);
                             }else{
                            //do nothing  
                             }
                       }else{  
                         format(vBox,aTerminalDefns,aResult,aX,aY);
                         if(vBox instanceof CTermDataBox){
                            aX=aX+labelSize.width;
                            horGroupSize.height=Math.max(horGroupSize.height,labelSize.height);
                         }else if(vBox instanceof CIndBox){
                            aX=aX+(childSize.width+vInd);
                            horGroupSize.height=Math.max(horGroupSize.height,childSize.height);
                         }else if(vBox instanceof CHorBox){
                            aX=aX+this.horGroupSize.width;
                            horGroupSize.height=Math.max(horGroupSize.height,this.horGroupSize.height);
                         }else if(vBox instanceof CVerBox){
                            aX=aX+verGroupSize.width;
                            horGroupSize.height=Math.max(horGroupSize.height,verGroupSize.height);
                         }
                      }
                   }
                       
                if(aHor.getNode()!=null && aHor.getNode().kind()==CNodeKind.LIST){
                    
                   CPanel vExtend=new CPanel(this);
                   vExtend.setSelectable(true);
                   vExtend.setNode(aHor.getNode());
                   labelSize=new Dimension();
                   childSize=new Dimension();
                   vLabel=new JLabel("+"); 
                   vLabel.setForeground(Color.DARK_GRAY);
                   // create a line border with the specified color and width
                   Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
                  // set the border of this component
                   vLabel.setBorder(border);
                  
                    vLabel.setToolTipText("Add a "+((INodeList)aHor.getNode()).eltSortLabel());
                    vLabel.addMouseListener(new MouseAdapter(){  
                    public void mouseClicked(MouseEvent e){  
                      if(isStructuralMode()){
                         select((CPanel)((JLabel)e.getSource()).getParent());
                          showPopupMenu(e);
                      }
                    } 
                    }); 
                   
                   labelSize=vLabel.getPreferredSize();
                   labelSize=new Dimension(labelSize.width,labelSize.height/2);
                   vLabel.setPreferredSize(labelSize);
                   vLabel.setBounds(0,0,labelSize.width,labelSize.height);
                                  
                   vExtend.add(vLabel);
                   vExtend.setPreferredSize(labelSize);
                   childSize=vExtend.getPreferredSize();
                   aY= labelSize.height/2;
                   vExtend.setBounds(aX,aY+2,childSize.width,childSize.height);
                   aResult.addChild(vExtend);
                   aX=aX+childSize.width+hSpace;
                   horGroupSize.height=Math.max(horGroupSize.height,childSize.height*2);
                 }
                   horGroupSize.width=aX;
                   
                   aResult.setPreferredSize(horGroupSize);
                   aResult.revalidate();
                   aResult.repaint();
                   
                  break;
               case CBoxesSortCodes.scVerBox: 
                 CVerBox aVer=(CVerBox)aBox;
                 verGroupSize=new Dimension();        
                 childSize=new Dimension();
                 CBox vVBox;
                 vSpace=aVer.getvSpace();
                 for(int i=0;i<aVer.getlist().termCount();i++){
                    vVBox=aVer.getlist().getElt(i);
                    
                    if(vVBox instanceof CSelBox){
                        vChild= boxTree2PanelTree(vVBox,aTerminalDefns);
                        if (((CSelBox)vVBox).getarg() instanceof CTermBox  ){
                              if(((CSelBox)vVBox).getarg().getNode() !=null && ((CSelBox)vVBox).getNode().kind()==CNodeKind.OPTION){
                                    vChild.setPreferredSize(labelSize);
                                    childSize=vChild.getPreferredSize();
                                    vChild.setBounds(aX,aY,childSize.width,childSize.height);
                                    aResult.addChild(vChild);
                                    aY=aY+childSize.height+vSpace+2;
                                    verGroupSize.width=Math.max(verGroupSize.width,childSize.width);
                              
                              }else{
                                  childSize=vChild.getPreferredSize();
                                  vChild.setBounds(aX,aY,childSize.width,childSize.height);
                                  aResult.addChild(vChild);
                                  aY=aY+childSize.height+vSpace;
                                  verGroupSize.width=Math.max(verGroupSize.width,childSize.width);
                              }
                        }else{
                            childSize=vChild.getPreferredSize();
                            vChild.setBounds(aX,aY,childSize.width,childSize.height);
                            aResult.addChild(vChild);
                            aY=aY+childSize.height+vSpace;
                            verGroupSize.width=Math.max(verGroupSize.width,childSize.width);
                        }   
                        }else if(vVBox instanceof CTermBox){
                             if(((CTermBox)vVBox).getsymName().length()!=0){
                                 format(vVBox,aTerminalDefns,aResult,aX,aY);
                                  aY=aY+labelSize.height+vSpace;
                                  verGroupSize.width=Math.max(verGroupSize.width,labelSize.width);
                             }else{
                            //do nothing  
                             }
                    }else{ 
                         format(vVBox,aTerminalDefns,aResult,aX,aY); 
                         if(vVBox instanceof CTermDataBox){
                            aY=aY+labelSize.height+vSpace;
                            verGroupSize.width=Math.max(verGroupSize.width,labelSize.width);
                         }else if(vVBox instanceof CIndBox){
                            aY=aY+childSize.height+vSpace+1;
                            verGroupSize.width=Math.max(verGroupSize.width,childSize.width+vInd);
                         }else if(vVBox instanceof CHorBox){
                            aY=aY+horGroupSize.height+vSpace;
                            verGroupSize.width=Math.max(verGroupSize.width,horGroupSize.width);
                         }else if(vVBox instanceof CVerBox){
                            aY=aY+this.verGroupSize.height+vSpace;
                            verGroupSize.width=Math.max(verGroupSize.width,this.verGroupSize.width);
                         }
                    }
                    vMaxW=Math.max(vMaxW,verGroupSize.width);
                }
                
            if(aVer.getNode()!=null && aVer.getNode().kind()==CNodeKind.LIST){
                  CPanel vExtend=new CPanel(this);
                  vExtend.setSelectable(true);
                  vExtend.setNode(aVer.getNode());
                  labelSize=new Dimension();
                  childSize=new Dimension();
                   vLabel=new JLabel("+"); 
                   vLabel.setForeground(Color.DARK_GRAY);
                   // create a line border with the specified color and width
                   Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
                  // set the border of this component
                   vLabel.setBorder(border);
                  // if(isStructuralMode()){ //  this does not work first time
                    vLabel.setToolTipText("Add a " +((INodeList)aVer.getNode()).eltSortLabel());
                    vLabel.addMouseListener(new MouseAdapter(){  
                    public void mouseClicked(MouseEvent e){  
                      if(isStructuralMode()){
                      select((CPanel)((JLabel)e.getSource()).getParent());
                      showPopupMenu(e);
                      }
                    }  
                    }); 
                 //  }
                   labelSize=vLabel.getPreferredSize();
                   labelSize=new Dimension(labelSize.width,labelSize.height/2);
                   vLabel.setBounds(0,0,labelSize.width,labelSize.height);
                   vExtend.add(vLabel);
                   vExtend.setPreferredSize(labelSize);
                   childSize=vExtend.getPreferredSize();
                   vExtend.setBounds(aX,aY,childSize.width,childSize.height);
                   aResult.addChild(vExtend);
                   aY=aY+childSize.height+vSpace+2;
                   verGroupSize.width=Math.max(verGroupSize.width,childSize.width);
                   vMaxW=Math.max(vMaxW,verGroupSize.width);
               }
                verGroupSize.height=aY;
                verGroupSize.width=vMaxW;
                aResult.setPreferredSize(verGroupSize);
                aResult.revalidate();
                aResult.repaint();
                break;
                default: assert false:String.format("Unexpected sortcode=%d in CPanelTree.format",aBox.sortCode());
                break;
           }
                 
 }
    /**
     * Returns a string representation of the terminal value for the given terminal name, from the given TerminalDef_List.
     * 
     * @param aName the terminal name.
     * @param aTerminals the TerminalDef_List
     * @return the terminal value.
     */
    private String getSymbol(String aName,CTerminalDef_List aTerminals){
      String vSym="";
          for(int l=0;l<aTerminals.contextCount();l++){
            if(aTerminals.getElt(l).getName().equals(aName)){
              vSym=aTerminals.getElt(l).getBody().toText();
              if(vSym.charAt(0)=='"' || vSym.charAt(0)=='\'')
                vSym=vSym.substring(1,vSym.length()-1);
              
            }
          }     
      return vSym;
}/**
 * Returns a list of all lexeme values for the given lexeme name, from the given LexemeDef_List.
 * 
 * @param aName the lexeme name
 * @param aLexemes LexemeDef_List
 * @return a list of all lexeme values.
 */

private ArrayList<String> getSymbols(String aName,CLexemeDef_List aLexemes){
     ArrayList<String> vSymbols=new ArrayList();
     CRE vRE;
     String vSym;
          for(int l=0;l<aLexemes.contextCount();l++){
             
            if(aLexemes.getElt(l).getName().equals(aName)){
              vRE=aLexemes.getElt(l).getBody();
              if(vRE instanceof CRE_MultiStick){
                  CRE_MultiStick vMult=(CRE_MultiStick) vRE;
                  for(int i=0;i<vMult.List().count();i++){
                      vSym=vMult.List().getElt(i).toText();
                      vSymbols.add(vSym.substring(1, vSym.length()-1));
                  }
              }else if(vRE instanceof CRE_Char){
                  CRE_Char vChar=(CRE_Char) vRE;
                  vSymbols.add(vChar.Char()+"");
              }
            }
          }     
      return vSymbols;
}

    /**
     * Maps the given box tree to a panel tree and sets its initial size.
     * 
     * @param aBox the box tree to be mapped.
     * @param aTerminalDefns the list of terminals.
     */
    public void showPanelTree(CBox aBox,CTerminalDef_List aTerminalDefns){ 
    
     rootPanel=boxTree2PanelTree(aBox,aTerminalDefns);
     //addNodeReferences(rootNode,rootPanel);
    Dimension vSize=rootPanel.getPreferredSize();
    // System.out.println(rootPanel.getPreferredSize());
     rootPanel.setBounds(0,0,vSize.width,vSize.height);
     removeAll();
     add(rootPanel);
     setPreferredSize(vSize);
     setMinimumSize(vSize);
     setMaximumSize(vSize);
     revalidate();
     repaint();
     recSetState(rootPanel,CPanelState.OUTER);
     
} 
/* 
private void addNodeReferences(CNode aRoot, CPanel aPanel){
    aPanel.setNode(aRoot);
    for(int i=0,j=0;(i<aRoot.count() & j<aPanel.noOfChildren()); i++,j++){
        addNodeReferences(aRoot.getNode(i),aPanel.getChild(i));
    }
    
} */
   
    /**
     *
     * @param aPanel
     */
    public void setInc(CPanel aPanel){
      if(aPanel.getState()!=CPanelState.THIS) {
      aPanel.setIncrementLevel(1);
      for(int i=0;i<aPanel.noOfChildren();i++)
         setInc(aPanel.getChild(i));
      }
   }
  
    /**
     *
     * @param aPanel
     */
    public void clearInc(CPanel aPanel){
    aPanel.setIncrementLevel(0);
    for(int i=0;i<aPanel.noOfChildren();i++)
         clearInc(aPanel.getChild(i));
   }
   
    /**
     *
     * @param aPanel
     */
    public void preSelect(CPanel aPanel){
      if(aPanel.isSelectable()){
          clearInc(rootPanel);
          setInc(aPanel);
          recSetPreState(rootPanel,CPanelState.OUTER);
          recSetPreState(aPanel,CPanelState.INNER);
          aPanel.setPreState(CPanelState.THIS);
      }else if(aPanel.getParent()!=null && aPanel.getParent()instanceof CPanel){
          preSelect((CPanel) aPanel.getParent());
      }
  }

    /**
     * Recursively set the state of the given panel.
     * 
     * @param aPanel the given panel.
     * @param aState the state to set.
     */
    public void recSetState(CPanel aPanel, CPanelState aState){
     aPanel.setState(aState);
     for(int i=0;i<aPanel.noOfChildren();i++){
         recSetState(aPanel.getChild(i),aState);
     }
 }

    /**
     * Recursively set the prestate of the given panel.
     * 
     * @param aPanel the given panel.
     * @param aPreState the state to set.
     */
    public void recSetPreState(CPanel aPanel, CPanelState aPreState){
     aPanel.setPreState(aPreState);
     for(int i=0;i<aPanel.noOfChildren();i++){
         recSetPreState(aPanel.getChild(i),aPreState);
     }
 }

    /**
     * Puts focus on the specified panel.
     * 
     * @param aPanel the panel to focus.
     */
    public void select(CPanel aPanel){
     if(aPanel.isSelectable()){
         recSetState(rootPanel,CPanelState.OUTER);
         recSetState(aPanel,CPanelState.INNER);
         aPanel.setState(CPanelState.THIS);
         hybridEditor.getTreeEditor().updateFocus(aPanel.getNode());
     }else if(aPanel.getParent()!=null && aPanel.getParent() instanceof CPanel){
         select((CPanel) aPanel.getParent());
     }
 }
 
    /**
     * Returns BackgroundColor for the specified index.
     * 
     * @param aIndex the index of the BackgroundColor 
     * @return the BackgroundColor corresponding to the index.
     */
    public Color getBackgroundColor(int aIndex){
     if(darkening){
         return colorShades.getColor(5-aIndex);
     }else return colorShades.getColor(aIndex);
 }    

  /*  @Override
    public void setTreeEditor(ITreeEditorComp aTreeEditor) {
       HashMap<String,CNode> vTemplates;
       
       if(hybridEditor.getTreeEditor()!=null){
              hybridEditor.getTreeEditor().removePropertyChangeListener(this);
       }
       hybridEditor.getTreeEditor()=aTreeEditor;
       if(hybridEditor.getTreeEditor()!=null){
          hybridEditor.getTreeEditor().addPropertyChangeListener(this);
           vTemplates=hybridEditor.getTreeEditor().getTemplates();
       }else{
           vTemplates=new HashMap<String,CNode>();
       }
       setTemplates(vTemplates);
       
    }*/

    /**
     * 
     * @param e
     */
    
    
    public void onPanelSelect(MouseEvent e){
        /*if(e.getSource() instanceof CPanel){
           this.getParent().dispatchEvent(e);
        }*/
        createPanelTree();
        
    }
   
    
 /*   
    public void onPanelSelect(MouseEvent e){
        if(e.getSource() instanceof CPanel){
           // invokeRichTextEdit((CPanel)e.getSource());
        }
        
    }
   
    //invoke a RichText editor in the position of the panel that has been double clicked.
   public void invokeRichTextEdit(CPanel aPanel){
       // there is a problem to recalculate the offsets of panels 
           CRichTextView richTextView = new CRichTextView();
           //CBoxTreetoStreamComp boxTree=new CBoxTreetoStreamComp();
          // CSymbolstreamToTextComp streamToText=new CSymbolstreamToTextComp();
                      
           if(aPanel.isSelectable()&& hybridEditor.getTreeEditor().getFocus().dataCount()==1){
               
              String panelText=hybridEditor.getTreeEditor().getFocus().getData(0);
              System.out.println(panelText);
              //check if valid data
              IdentifierValidator id=new IdentifierValidator();
              NumberValidator num=new NumberValidator();
              
              if(!panelText.isEmpty() && (id.validate(panelText)|| num.validate(panelText)|| panelText.equals("????"))){
                 richTextView.setText(panelText);
                 int i=rootPanel.getIndex(aPanel);
                CPanel p= (CPanel) aPanel.getParent();
                richTextView.setBounds(aPanel.getBounds());
                 p.remove(i);
             
              
              //  richTextView.setPreferredSize(new Dimension(aPanel.getSize().width+5, aPanel.getSize().height));
             //richTextView.setPreferredSize(aPanel.getSize());
             //richTextView.setMinimumSize(aPanel.getSize());
                //richTextView.setMinimumSize(new Dimension(aPanel.getSize().width+5, aPanel.getSize().height));
                p.add(richTextView, i);
                p.revalidate();
                p.repaint();
              }
              
              
           }
            
           
   }  
   // Obtian the text of the panel that has been double clicked
    
   public String getPanelText(CPanel aPanel){
       String s="";
       if(aPanel.noOfChildren()==0 && aPanel.getComponentCount()==1){
           s=s+(((JLabel)aPanel.getComponent(0)).getText()); 
       }else{
            s="";
       }
       return s;
   }

  */  

    /**
     * Invokes a popup menu with a list allowable language templates.
     * 
     * @param e an event which indicates in which panel tree component a mouse action occurred.
     */
      
   
    public void showPopupMenu(MouseEvent e){
     CNode vFocus;
     JPopupMenu popupmenu = new JPopupMenu("EditOperations"); 
     popupmenu.setBorder(new BevelBorder(BevelBorder.RAISED));
     JMenuItem vItem;
     JMenu menuItem;
     templates=hybridEditor.getTreeEditor().getTemplates();
     vItem=null;
     //Obtain focus and create menu items according to focus
     if(e.getSource() instanceof JLabel && isStructuralMode() ){
         vFocus=((CPanel)((JLabel)e.getSource()).getParent()).getNode();
         if(vFocus.kind()==CNodeKind.LIST && !templates.isEmpty()){
            for(Map.Entry<String,CNode> template :templates.entrySet()){
               String vTemp=template.getKey();
               if(vTemp.charAt(0)=='<' && vTemp.charAt(vTemp.length()-1)=='>'){
                   vItem = new JMenuItem(vTemp);
               }
            }
            
            popupmenu.add(vItem);
            vItem.addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                    String vTemp="";
                    for(Map.Entry<String,CNode> template :templates.entrySet()){
                        vTemp=template.getKey();
                        if(vTemp.charAt(0)=='<'&& vTemp.charAt(vTemp.length()-1)=='>'){
                            vTemp=vTemp.substring(1, vTemp.length()-1);
                            hybridEditor.add(vTemp); 
                            
                        }
                    }
               }
           });
            popupmenu.addSeparator();
            for(Map.Entry<String,CNode> template :templates.entrySet()){
               String vTemp=template.getKey();
               if(vTemp.charAt(0)=='<' && vTemp.charAt(vTemp.length()-1)=='>'){
                   // do nothimg
               }else{
                vItem = new JMenuItem(vTemp);
                popupmenu.add(vItem);
                vItem.addActionListener(new ActionListener(){
 
               @Override
               public void actionPerformed(ActionEvent e) {
                  hybridEditor.add(vTemp); 
                            
               }
            }); 
               }
            }
          }else if (vFocus.kind()==CNodeKind.OPTION && !templates.isEmpty()){
            for(Map.Entry<String,CNode> template :templates.entrySet()){
               String vTemp=template.getKey();
               vItem = new JMenuItem(vTemp);
               popupmenu.add(vItem);
               vItem.addActionListener(new ActionListener(){
 
               @Override
               public void actionPerformed(ActionEvent e) {
                 hybridEditor.setPresent(vTemp);
                 
               }
            }); 
               
            }
          }
          popupmenu.show(e.getComponent(), e.getX(), e.getY()); //displays the popup menu at x, y position within the component c. 
          
     }else{
      vFocus=hybridEditor.getTreeEditor().getFocus();
      vItem = new JMenuItem("Delete");
      
      if(hybridEditor.getTreeEditor().canSetAbsent()){
         popupmenu.add(vItem);
         vItem.setEnabled(hybridEditor.getTreeEditor().canDelete());
         vItem.addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.setAbsent();
                
               }
           });
         popupmenu.addSeparator(); 
          
      }else if(hybridEditor.getTreeEditor().canDelete()){
        popupmenu.add(vItem);
        vItem.setEnabled(hybridEditor.getTreeEditor().canDelete());
        vItem.addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.delete();
                
               }
           });
         popupmenu.addSeparator();
     }
    // templates for replacing;
       if(!templates.isEmpty() && hybridEditor.getTreeEditor().canReplace(vFocus)){
        if(templates.size()==1 && getMenuPattern(templates.entrySet().iterator().next().getKey())==null){
          hybridEditor.replace(templates.entrySet().iterator().next().getValue());
          
        }else{
          for(Map.Entry<String,CNode> template :templates.entrySet()){
           String vTemp=template.getKey(); 
           // check if key exists in menu templates 
           CBox vBox=getMenuPattern(vTemp);
            if(vBox==null){
               
                vItem = new JMenuItem(vTemp);
                popupmenu.add(vItem);
                vItem.addActionListener(new ActionListener(){
                     @Override
                     public void actionPerformed(ActionEvent e) {
                        hybridEditor.replace(vTemp);
                        
                     }
                });
            }else{
              CNode vNode= template.getValue();
              CBox hBox;
              String vTermName;
              String vLexName, vSymName;
              ArrayList<String> vLexemes=new ArrayList();
              String s="";
              menuItem=new JMenu(template.getKey());
              int vIndex=0;
              CHorBox aHor=(CHorBox)vBox;
              for(int i=0;i<aHor.getlist().termCount();i++){
                 hBox=aHor.getlist().getElt(i);
                 switch(hBox.sortCode()){
                     case CBoxesSortCodes.scArgNodeBox: 
                          s=s+" ....";
                     break;
                     case CBoxesSortCodes.scArgDataBox: 
                         
                          CArgDataBox aArgDataBox=(CArgDataBox)hBox;
                          vIndex=aArgDataBox.getindex();
                          if((0<=vIndex) && (vIndex<vNode.dataCount())){
                            vTermName=aArgDataBox.getsymName();
                            vLexName=getSymbol(vTermName,TerminalDefns);
                            vLexemes=getSymbols(vLexName,lexemeDefns);
                           }
                          if(!vLexemes.isEmpty())
                              s=s+" "+vLexemes.get(0);
                      break; 
                    case CBoxesSortCodes.scTermBox:
                         CTermBox aTerm=(CTermBox)hBox;
                         vSymName=getSymbol(aTerm.getsymName(),TerminalDefns);
                         s=s+" "+vSymName;
                    default: break;
                }
            }
            if(vLexemes.isEmpty()){
                vItem = new JMenuItem(s);
          
                popupmenu.add(vItem);
                vItem.addActionListener(new ActionListener(){
                     @Override
                     public void actionPerformed(ActionEvent e) {
                        hybridEditor.replace(template.getValue());  
                        
               }
                }); 
            }else if(vLexemes.size()==1){
                String vLexValue=vLexemes.get(0);
                template.getValue().setData(vIndex, vLexValue);
                vItem = new JMenuItem(template.getKey());
          
                popupmenu.add(vItem);
                vItem.addActionListener(new ActionListener(){
                     @Override
                     public void actionPerformed(ActionEvent e) {
                        hybridEditor.replace(template.getValue()); 
                        
                }
                }); 
            }else{
                String vLexValue=vLexemes.get(0);
                template.getValue().setData(vIndex, vLexValue);
                vItem = new JMenuItem(s);
                if(templates.size()>1){
                    menuItem.setEnabled(hybridEditor.getTreeEditor().canReplace(vFocus));
                    menuItem.add(vItem);
                }else{
                 popupmenu.add(vItem);
                 
                }
                vItem.addActionListener(new ActionListener(){
                     @Override
                     public void actionPerformed(ActionEvent e) {
                        hybridEditor.replace(template.getValue(),vLexValue);   
                        
                }
                }); 
            }     
            for(int j=1;j<vLexemes.size();j++){ 
              s="";  
              for(int k=0;k<aHor.getlist().termCount();k++){
                 hBox=aHor.getlist().getElt(k);
                 switch(hBox.sortCode()){
                     case CBoxesSortCodes.scArgNodeBox: 
                          s=s+" ...";
                     break;
                     case CBoxesSortCodes.scArgDataBox: 
                          s=s+" "+vLexemes.get(j);
                      break; 
                    case CBoxesSortCodes.scTermBox:
                        CTermBox aTerm=(CTermBox)hBox;
                        vSymName=getSymbol(aTerm.getsymName(),TerminalDefns);
                        s=s+" "+vSymName;
                    default: break;
                } 
            }
            String vLex=vLexemes.get(j);
            template.getValue().setData(vIndex, vLex);
            vItem = new JMenuItem(s);
            if(templates.size()>1){
                   menuItem.add(vItem);
                   popupmenu.add(menuItem);
            }else{
                 popupmenu.add(vItem);
               
            }
            vItem.addActionListener(new ActionListener(){
                     @Override
                     public void actionPerformed(ActionEvent e) {
                        hybridEditor.replace(template.getValue(),vLex);  
                        
                    }
                });  
            }
            } 
        }
     }
        popupmenu.addSeparator();
     }
     } 
   
    vItem = new JMenuItem("Remove"); 
    if(hybridEditor.getTreeEditor().canRemove()){
        popupmenu.add(vItem);
        vItem.setEnabled(hybridEditor.getTreeEditor().canRemove());
        vItem.addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.remove();
                
               }
           });
    }
    
    if(hybridEditor.getTreeEditor().canInsert()){
        menuItem=new JMenu("Insert Before");
        menuItem.setEnabled(hybridEditor.getTreeEditor().canInsert());
        for(Map.Entry<String,CNode> template :templates.entrySet()){
            String vTemp=template.getKey();
            vItem=new JMenuItem(vTemp);
            menuItem.add(vItem);
            vItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    hybridEditor.insertBefore(vTemp,vFocus);
                    
                }
            });
        }
        popupmenu.add(menuItem);
    
    
        menuItem=new JMenu("Insert After");
        menuItem.setEnabled(hybridEditor.getTreeEditor().canInsert());
        for(Map.Entry<String,CNode> template :templates.entrySet()){
            String vTemp=template.getKey();
            vItem=new JMenuItem(vTemp);
            menuItem.add(vItem);
            vItem.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    hybridEditor.insertAfter(vTemp,vFocus);
                    
                }
            });
        }
    popupmenu.add(menuItem);  
    popupmenu.addSeparator();
    }
    
     
    if(hybridEditor.getTreeEditor().canClear()){
        vItem = new JMenuItem("Clear");
        popupmenu.add(vItem);
        vItem.setEnabled(hybridEditor.getTreeEditor().canClear());
        vItem.addActionListener(new ActionListener(){
 
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.clear();
                
               }
           });
    }
     if(e.isPopupTrigger())
         try {
             popupmenu.show(e.getComponent(), e.getX(), e.getY()); //displays the popup menu at x, y position within the component c. 
         }catch (IllegalComponentStateException ex) {
          // throw new java.awt.IllegalComponentStateException(ex.getMessage() + " component=[" + e.getComponent().getClass().getName() + "] parent=" + e.getComponent().getParent());
      }    
    
    
    
    
    }
 
    /**
     *
     * @param aPattern
     * @param aNode
     * @return
     */
    public ArrayList<String> lexemeSymbols(CBox aPattern,CNode aNode){
      CBox vBox;
      String vTermName;
      String vLexName, vSymName;
      ArrayList<String> vLexemes=new ArrayList();
      
      int vIndex;
      if(aPattern!=null){
       CHorBox aHor=(CHorBox)aPattern;
       for(int i=0;i<aHor.getlist().termCount();i++){
        vBox=aHor.getlist().getElt(i);
        switch(vBox.sortCode()){
          case CBoxesSortCodes.scArgDataBox: 
                CArgDataBox aArgDataBox=(CArgDataBox)vBox;
                vIndex=aArgDataBox.getindex();
                if((0<=vIndex) && (vIndex<aNode.dataCount())){
                   vTermName=aArgDataBox.getsymName();
                   vLexName=getSymbol(vTermName,TerminalDefns);
                   vLexemes=getSymbols(vLexName,lexemeDefns);
                }
          break; 
          case CBoxesSortCodes.scTermBox:
              CTermBox aTerm=(CTermBox)vBox;
              vSymName=getSymbol(aTerm.getsymName(),TerminalDefns);
              vLexemes.add(vSymName);
          default: break;
        }
      }
     }
    return vLexemes;  
  }      

    /**
     *
     * @param aTemplate
     * @return
     */
    protected CBox getMenuPattern(String aTemplate){ 
    if(aTemplate.length()!=0 && MenuPatternDefns!=null){
        for(int i=0;i<MenuPatternDefns.getfPatternDefs().contextCount();i++){
            if(aTemplate.equals(MenuPatternDefns.getfPatternDefs().getElt(i).getName())){
                return MenuPatternDefns.getfPatternDefs().getElt(i).getpattern();
            }
        }
        
    }
        return null;
}     

    /**
     *
     * @param aNode
     * @return
     */
    protected String getStringTemplate( CNode aNode){
    CBox vBox;
    String vResult;
    CAST2BoxTreeComp box =new CAST2BoxTreeComp();
    box.setPatternDefns(MenuPatternDefns);
    vBox=box.format(aNode);
    vResult=createTemp(vBox);
    return vResult;
}

    /**
     *
     * @param vBox
     * @return
     */
    protected String createTemp(CBox vBox){
    String s="";
    
    switch(vBox.sortCode()){
        case CBoxesSortCodes.scTermDataBox:
            CTermDataBox aTermData=(CTermDataBox)vBox;
            s=s+aTermData.getdata();
            
        break;
        case CBoxesSortCodes.scTermBox:
            CTermBox aTerm=(CTermBox)vBox;
            s=s+getSymbol(aTerm.getsymName(),TerminalDefns);
            
        break;    
        case CBoxesSortCodes.scHorBox:
            CHorBox aHor=(CHorBox)vBox;
            for(int i=0;i<aHor.getlist().termCount();i++){
               s=s+" "+createTemp(aHor.getlist().getElt(i)); 
                         
            }
        break;
    }
    
    return s;
}

//displays the popup menu at x, y position within the component c. 
    
     
    // popupmenu.addSeparator();
     
   
    
    
    //CPanel source=(CPanel) e.getSource();
    //System.out.println("Focus:"+hybridEditor.getTreeEditor().getFocus());
  /*  if(source.getNode().kind()==CNodeKind.LIST && !templates.isEmpty()){
        vItem = new JMenuItem(templates.get(0));
        popupmenu.add(vItem);
            vItem.addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                   String vTemp=templates.get(0);
                   if(vTemp.charAt(0)=='<'&& vTemp.charAt(vTemp.length()-1)=='>'){
                       vTemp=vTemp.substring(1, vTemp.length()-1);
                   }
                   hybridEditor.getTreeEditor().add(vTemp);
               }
           });
        popupmenu.addSeparator();
        for (int i=1;i<templates.size();i++) {
            String template=templates.get(i);
            vItem = new JMenuItem(template);
            popupmenu.add(vItem);
            vItem.addActionListener(new ActionListener(){
 
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.getTreeEditor().add(template);
               }
           });
     }
        
    }else{
     
     vItem = new JMenuItem("Cut");
     popupmenu.add(vItem);
     vItem = new JMenuItem("Copy");
     popupmenu.add(vItem);
     vItem = new JMenuItem("Paste");
     popupmenu.add(vItem);
     vItem = new JMenuItem("Delete");
     popupmenu.add(vItem);
     vItem.setEnabled(hybridEditor.getTreeEditor().canDelete());
     vItem.addActionListener(new ActionListener(){
 
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.getTreeEditor().delete();
               }
           });
     if(!templates.isEmpty()){
       popupmenu.addSeparator();
     }
     for (String template : templates) {
            vItem = new JMenuItem(template);
            popupmenu.add(vItem);
            vItem.addActionListener(new ActionListener(){
 
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.getTreeEditor().replace(template);
               }
           });
     } 
     popupmenu.addSeparator();
     vItem = new JMenuItem("Clear");
     popupmenu.add(vItem);
     vItem.setEnabled(hybridEditor.getTreeEditor().canClear());
     vItem.addActionListener(new ActionListener(){
 
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.getTreeEditor().clear();
               }
           });
     
                               
       //this.add(popupmenu);   
 }*/
    
}
//

/*
public void showPopupMenu(MouseEvent e){
     
    JPopupMenu popupmenu = new JPopupMenu("EditOperations"); 
     JMenuItem vItem;
     JMenu subMenu;
     vItem = new JMenuItem("Cut");
     popupmenu.add(vItem);
     vItem = new JMenuItem("Copy");
     popupmenu.add(vItem);
     vItem = new JMenuItem("Paste");
     popupmenu.add(vItem);
     vItem = new JMenuItem("Delete");
     popupmenu.add(vItem);
     vItem.setEnabled(hybridEditor.getTreeEditor().canDelete());
     popupmenu.addSeparator();
     for (String template : templates) {
            subMenu = new JMenu(template);
           
            JMenuItem replace=new JMenuItem("Replace");
            subMenu.add(replace);
            replace.setEnabled(hybridEditor.getTreeEditor().canReplace(template));
            replace.addActionListener(new ActionListener(){
 
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.getTreeEditor().replace(template);
               }
           });
            
            JMenuItem insertBefore=new JMenuItem("Insert Before");
            subMenu.add(insertBefore);
            insertBefore.setEnabled(hybridEditor.getTreeEditor().canInsertBefore(template));
            insertBefore.addActionListener(new ActionListener() {
 
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.getTreeEditor().insertBefore(template);
               }
           });
            JMenuItem insertAfter=new JMenuItem("Insert After");
            subMenu.add(insertAfter);
            insertAfter.setEnabled(hybridEditor.getTreeEditor().canInsertAfter(template));
            insertAfter.addActionListener(new ActionListener() {
 
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.getTreeEditor().insertAfter(template);
               }
           });
            popupmenu.add(subMenu);
     } 
     popupmenu.addSeparator();
     vItem = new JMenuItem("Clear");
     popupmenu.add(vItem);
     if(e.isPopupTrigger())
          popupmenu.show(e.getComponent(), e.getX(), e.getY());  //displays the popup menu at x, y position within the component c. 
                               
       //this.add(popupmenu);   
 }

/* if(hybridEditor.getTreeEditor().canInsert()) {
     for (String template : templates) {
            menuItem = new JMenu(template);
            
            JMenuItem insertBefore=new JMenuItem("Insert Before");
            menuItem.add(insertBefore);
            insertBefore.addActionListener(new ActionListener() {
                @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.getTreeEditor().insertBefore(template,vFocus);
               }
           });
            JMenuItem insertAfter=new JMenuItem("Insert After");
            menuItem.add(insertAfter);
            insertAfter.addActionListener(new ActionListener() {
 
               @Override
               public void actionPerformed(ActionEvent e) {
                hybridEditor.getTreeEditor().insertAfter(template,vFocus);
               }
           });
            popupmenu.add(menuItem);
hybridEditor.getTreeEditor().add(vTemp); 
                            setAst(hybridEditor.getTreeEditor().getTree());
                            createPanelTree();
      } 
    } */
       
   
