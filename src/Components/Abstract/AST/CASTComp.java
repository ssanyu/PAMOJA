/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.AST;

import Components.Abstract.Abstraction.IAbstractionComp;
import Components.CPAMOJAComponent;
import Components.INodeObject;
import Nodes.CNode;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class creates an AST component which holds the structural representation of an AST.
 * <p>
 * The AST component observes the Abstractor component and maintains consistency between its AST and the AST of the Abstractor component.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CASTComp extends CPAMOJAComponent implements IASTComp,INodeObject,PropertyChangeListener {
    /**
     * Internal representation of an AST.
     */
    private CNode ASTStructure;
    
    
    /**
     * A reference to <code>Abstractor</code> object.
     */
    private IAbstractionComp Abstractor;
    
    /**
     * Creates a new instance of <code>CASTComp</code>.
     */
    public CASTComp() {
         super();
    }
 
    /**
     * Creates a new instance of <code>CASTComp</code> with the given AST.
     *
     * @param aASTStructure the initial structural AST representation.
    */
    public CASTComp(CNode aASTStructure) {
       ASTStructure = aASTStructure;
    }

    /**
     * Paints the UI of the AST component object. 
     * 
     * @param g the AST component object to paint.
     */
   @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("AST",10, 10);
    } 
    
       
    /**
     * Accesses the interface of the Abstractor component connected to this AST component.
     *
     * @return the Abstractor object connected to this AST object.
     */
    @Override
    public IAbstractionComp getAbstractor() {
        return Abstractor;
    }
    
    
    /**
     * Connects to the Abstractor component via its interface. It sets the value of <code>Abstractor</code>, registers for
     * property change events and retrieves current value of AST object.
     * 
     * @param aAbstractor an instance of the Abstractor component.
     */
    @Override
    public void setAbstractor(IAbstractionComp aAbstractor) {
       CNode vASTStructure;
       if(Abstractor!=null){
              Abstractor.removePropertyChangeListener(this);
       }
       Abstractor=aAbstractor;
       if(Abstractor!=null){
           Abstractor.addPropertyChangeListener(this);
           vASTStructure=Abstractor.getAST();
       }else{
            vASTStructure=null;
       }
       setASTStructure(vASTStructure);
    }

   /**
     * Gets the structural representation of the AST.
     *
     * @return the AST stored in this component.
     */
    @Override
    public CNode getASTStructure() {
        return ASTStructure;
    }

    /**
     * Set the AST structure and notifies all observers about <code>ASTStructure</code> property change.
     * 
     * @param aASTStructure the CNode object describing the AST structure.
     */
    @Override
    public void setASTStructure(CNode aASTStructure) {
       
                // keep the old value
        CNode oldASTStructure=ASTStructure;
        // save the new value
        ASTStructure=aASTStructure;
        // fire the property change event, with a property that has changed
        
        support.firePropertyChange("ASTStructure",oldASTStructure,ASTStructure);
    }
    /**
     * This method gets called when a property of the Abstractor Component is changed. It deDetermines if the property change is from the <code>AbstractorComp</code> component. 
     * If so, it retrieves the AST object of the <code>AbstractorComp</code>, updates its AST object and notifies its observers.
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        if(source==Abstractor){
           setASTStructure(Abstractor.getAST());
        }
    }
    
    

    /**
     * Returns the structural representation of the AST .
     *
     * @return the AST stored in this component.
     */
    @Override
    public CNode getNode() {
         return ASTStructure;
    }

   
}
