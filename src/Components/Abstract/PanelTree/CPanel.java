/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.PanelTree;

import Boxes.CBox;
import Nodes.CNode;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * A class implementing a JPanel instance which may contain other objects such as, source code texts, JLabels and other Panels.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CPanel extends JPanel{
    private CPanelTreeComp tree; //reference to whole panel tree
    private ArrayList<CPanel> children;  //list of inner panels which correspond to the sons of the AST
    private CPanelState state;
    private CPanelState preState; 
    private int darknessLevel; // 1...4
    private int incrementLevel; //0..1
    private boolean selectable;
    private CBox layoutPatterns;
    private CNode node;
    
    /**
     * Creates a Panel with the specified PanelTree (a set of nested panels) component.
     * 
     * @param aTree the nested panel.
     */
    public CPanel(CPanelTreeComp aTree) {
        super();
        setLayout(null);
        tree=aTree;
        children= new ArrayList<CPanel>();
        state=CPanelState.OUTER;
        preState=CPanelState.OUTER;
        darknessLevel=1;
        incrementLevel=0;
        selectable = false;
       
        addMouseListener( new MouseEvents());
        addMouseMotionListener( new MouseEvents());
   }

    /**
     * Returns a PanelTree component associated with this Panel.
     * 
     * @return the CPanelTreeComp
     */
    public CPanelTreeComp getTree() {
        return tree;
    }

    /**
     * Sets the PanelTree component to this panel.
     * 
     * @param tree the nested PanelTree component.
     */
    public void setTree(CPanelTreeComp tree) {
        this.tree = tree;
    }

    /**
     * Returns the state of this panel.
     * 
     * @return the panel state.
     * @see CPanelState
     */
    public CPanelState getState() {
        return state;
    }

    /**
     * Sets the given state to this panel.
     * 
     * @param aState the CPanelState value.
     * @see CPanelState
     */
    public void setState(CPanelState aState) {
        state = aState;
        if(state==CPanelState.OUTER){
           darknessLevel=1;
        }else if(state==CPanelState.THIS ||state==CPanelState.INNER){
           darknessLevel=3;  
        }
       // if(selectable){
           this.setBackground(tree.getBackgroundColor(darknessLevel+incrementLevel));
            
       // }
      
    }  

    /**
     * Returns the prestate of this Panel before focus.
     * 
     * @return the prestate of this panel.
     * @see CPanelState
     */
    public CPanelState getPreState() {
        return preState;
    }

    /**
     * Sets the prestate of this Panel to the given state before its focus.
     * 
     * @param aPreState the CPanelState value.
     */
    public void setPreState(CPanelState aPreState) {
        preState = aPreState;
       // if(selectable){
            this.setBackground(tree.getBackgroundColor(darknessLevel+incrementLevel));
      //  }
    }

    /**
     * Returns the level of darkness for this Panel. Darkness Level goes upto a maximum of 4.
     * 
     * @return the darkness level in integer form.
     */
    public int getDarknessLevel() {
        return darknessLevel;
    }

    /**
     * Sets the darkness level of this panel to the specified level.
     * 
     * @param darknessLevel the integer value.
     */
    public void setDarknessLevel(int darknessLevel) {
        this.darknessLevel = darknessLevel;
    }

    /**
     *
     * @return
     */
    public int getIncrementLevel() {
        return incrementLevel;
    }

    /**
     *
     * @param incrementLevel
     */
    public void setIncrementLevel(int incrementLevel) {
        this.incrementLevel = incrementLevel;
    }

    /**
     * Reports if this panel can be selected by the user. A panel (with source text) corresponding to a particular node in the AST is selectable.
     * 
     * @return <code>true</code> if a panel is capable of being selected. Otherwise, <code>false</code>.
     */
    public boolean isSelectable() {
        return selectable;
    }

    /**
     * Sets the flag that determines whether this panel is selectable. 
     * 
     * @param selectable if true then this panel can be selected by the user; otherwise this panel cannot be selected by the user.
     */
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
        if(this.getParent() instanceof CPanel)
           ((CPanel)this.getParent()).setSelectable(!selectable);
       
    }

    /**
     * Returns the box patterns used in the formating of source text on this panel.
     * 
     * @return the box patterns used.
     */
    public CBox getLayoutPatterns() {
        return layoutPatterns;
    }

    /**
     * Sets the box patterns to be used in the formating of source text on this panel.
     * 
     * @param layoutPatterns an instance of CBox used for source text formatting.
     */
    public void setLayoutPatterns(CBox layoutPatterns) {
        this.layoutPatterns = layoutPatterns;
    }

    /**
     * Returns an AST node associated with this panel.
     * 
     * @return the AST node for this panel.
     */
    public CNode getNode() {
        return node;
    }

    /**
     * Sets the AST node associated with this panel.
     * 
     * @param node the CNode object for this panel.
     */
    public void setNode(CNode node) {
        this.node = node;
    }

    /**
     * Adds a child panel to a list of inner panels contained by this panel. The list of inner panels should correspond to the sons of the node in the AST which is associated with this panel.
     * 
     * @param aPanel the child panel to be added to the list.
     */
    public void addChild(CPanel aPanel){
        children.add(aPanel);
        add(aPanel);
        revalidate();
        repaint();
    }
    
    /**
     * Obtain index of the specified panel within its parent Panel.
     * 
     * @param aPanel the specified panel.
     * @return the index of the specified panel.
     */
        public int getIndex(CPanel aPanel){
        int result=-1;
        CPanel vPanel=(CPanel) aPanel.getParent();
        for(int i=0;i<vPanel.getComponentCount();i++){
            if(aPanel.equals(vPanel.getComponent(i)))
                result=i;
        }
        return result;
    }
    
  
 /*   public void addChildren(ArrayList<CPanel> aPanels){
      
        for(int i=0;i<aPanels.size();i++){
            add(aPanels.get(i));
            revalidate();
            repaint();
       }
    }
 */
    
    /**
     * Gets the ith child panel in this panel.
     * 
     * @param i the index of the child panel to get.
     * @return the ith child panel in this panel.
     */
    public CPanel getChild(int i){
        if ((0 <= i) && (i < children.size()))
          return children.get(i);
        else return null;
    }
    
    /**
     * Gets the number of child panels contained in this panel.
     * 
     * @return the number of child panels in this panel.
     */
    public int noOfChildren(){
        return children.size();
    }

    /**
     * Gets all the children of this panel.
     * 
     * @return an ArrayList of all child panels in this panel.
     */
    public ArrayList<CPanel> getChildren() {
        return children;
    }

    /**
     * Assign this panel its children.
     * 
     * @param children the ArrayList of child panels to be assigned.
     */
    public void setChildren(ArrayList<CPanel> children) {
        this.children = children;
    }
  /*  
   //invoke a RichText editor in the position of the panel that has been double clicked.
   public void invokeRichTextEdit(CPanel aPanel){
           CRichTextView richTextView = new CRichTextView();
           CBoxTreetoStreamComp boxTree=new CBoxTreetoStreamComp();
           CSymbolstreamToTextComp streamToText=new CSymbolstreamToTextComp();
                      
           if(aPanel.isSelectable()){
              String panelText=getPanelText(aPanel);
              
              boxTree.setBoxTree(aPanel.getLayoutPatterns());
              streamToText
              int i=getIndex(aPanel);
              CPanel p= (CPanel) aPanel.getParent();
              p.remove(i);
              richTextView.setText(panelText);
             // richTextView.setPreferredSize(new Dimension(aPanel.getSize().width+10, aPanel.getSize().height+5));
              richTextView.setPreferredSize(aPanel.getSize());
              richTextView.setMinimumSize(aPanel.getSize());
              p.add(richTextView, i);
           }else if (aPanel.getParent()!=null && aPanel.getParent() instanceof CPanel){
              invokeRichTextEdit((CPanel) aPanel.getParent());
           }
   }  
   // Obtian the text of the panel that has been double clicked
    String s="";
   public String getPanelText(CPanel aPanel){
       if(aPanel.noOfChildren()==0 && aPanel instanceof CTextPanel){
           s=s+((CTextPanel)aPanel).getText(); 
       }else{
            for(int i=0;i<aPanel.noOfChildren();i++){
                if(aPanel.getChild(i) instanceof CTextPanel ){
                    s=s+(((CTextPanel)aPanel.getChild(i)).getText());
              
                }else{
                    getPanelText(aPanel.getChild(i)) ;
                }
            }
            s=s+"\n";
       }
       return s;
   }
  */ 
    
   /**
    * Inner class which handles MouseListener events
    */ 
   
   class MouseEvents implements MouseMotionListener,MouseListener{
   CPanel source ;
   Color background;
   
   @Override
   public void mouseEntered(MouseEvent evt){}
   
   @Override
   public void mouseExited(MouseEvent evt) {}
   
   public void mousePressed(MouseEvent evt) {}
   
   @Override
   public void mouseClicked(MouseEvent evt) {
     if(tree.isStructuralMode()){
        Component vSource = (Component) evt.getSource();
        source=(CPanel) evt.getSource();
        if(evt.getClickCount()==1){
          tree.select(source);
          while(vSource.getParent()!=null){
              vSource=vSource.getParent();
              if(vSource instanceof MouseListener ){//& vSource instanceof JPanel){
                  ((MouseListener)vSource).mouseClicked(evt);
              }
          } 
      }else if(evt.getClickCount()==2){
            tree.select(source);
            tree.onPanelSelect(evt);
      }
     }  
    }
   
   
    @Override
    public void mouseMoved(MouseEvent e) {
       if(tree.isStructuralMode()){
        source=(CPanel) e.getSource();
        if(preState!=CPanelState.THIS) 
           tree.preSelect(source);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
       if(tree.isStructuralMode()){
        source=(CPanel) e.getSource();
        tree.select(source);
        tree.showPopupMenu(e);
       } 
    }
   

}
 /*   //invoke a RichText editor in the position of the panel that has been double clicked.
   public void invokeRichTextEdit(CPanel aPanel){
           CRichTextView richTextView = new CRichTextView();
           //CBoxTreetoStreamComp boxTree=new CBoxTreetoStreamComp();
          // CSymbolstreamToTextComp streamToText=new CSymbolstreamToTextComp();
                      
           if(aPanel.isSelectable()&& tree.getTreeEditor().getFocus().dataCount()==1){
               
              String panelText=tree.getTreeEditor().getFocus().getData(0);
              System.out.println(panelText);
              //check if valid data
              IdentifierValidator id=new IdentifierValidator();
              NumberValidator num=new NumberValidator();
              
              if(!panelText.isEmpty() && (id.validate(panelText)|| num.validate(panelText)|| panelText.equals("?"))){
                 richTextView.setText(panelText);
                 int i=getIndex(aPanel);
                CPanel p= (CPanel) aPanel.getParent();
                p.remove(i);
              
                richTextView.setPreferredSize(new Dimension(aPanel.getSize().width+5, aPanel.getSize().height));
             //richTextView.setPreferredSize(aPanel.getSize());
             //richTextView.setMinimumSize(aPanel.getSize());
                richTextView.setMinimumSize(new Dimension(aPanel.getSize().width+5, aPanel.getSize().height));
                 p.add(richTextView, i);
                p.revalidate();
                p.repaint();
              }
              
              
           }
            
           
   }*/  
}
