/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.AST;

import Components.Abstract.Abstraction.IAbstractionComp;
import Components.IPAMOJAComponent;
import Nodes.CNode;

/**
 * Provides methods for interacting connecting with other components. E.g, the Abstractor and AST2BoxTree components.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IASTComp extends IPAMOJAComponent{
     /**
     * Gets the structural representation of the AST.
     *
     * @return the AST stored in this component.
     */
    CNode getASTStructure();

    /**
     * Set the AST structure and notifies all observers about <code>AST</code> property change.
     * 
     * @param aASTStructure the CNode object describing the AST structure.
     */
    void setASTStructure(CNode aASTStructure);

       
    
    /**
     * Connects to the Abstractor component via its interface. It sets the value of <code>Abstractor</code>, registers for
     * property change events and retrieves current value of AST object.
     * 
     * @param aAbstraction an instance of the Abstractor component.
     */
        void setAbstractor(IAbstractionComp aAbstraction);

    /**
     * Gets the value of Abstractor component connected to this AST component.
     *
     * @return the Abstractor object connected to this AST object.
     */
    IAbstractionComp getAbstractor();
}
