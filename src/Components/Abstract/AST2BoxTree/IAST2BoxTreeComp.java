/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.AST2BoxTree;

import Boxes.CBox;
import Components.Abstract.AST.IASTComp;
import Components.Abstract.Patterns.IPatternsComp;
import Components.IPAMOJAComponent;
import Nodes.CNode;



/**
 * Provides services for mapping an AST to a Box tree and for interacting with other components. 
 * E.g, AST, Patterns and BoxTree2Stream components.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IAST2BoxTreeComp extends IPAMOJAComponent {
    
     /**
     * Accesses the interface of the AST component connected to this AST2BoxTree component. 
     *
     * @return the value of AST
     */
    IASTComp getAST();

    /**
     * Connects to the AST component via its interface.  
     * Registers for property change events, retrieves current value of AST object and regenerates the box tree.
     *
     * @param aAST new AST component's interface.
     */
    void setAST(IASTComp aAST);
     
    /**
     * Accesses the interface of a Patterns object connected to this AST2BoxTree component.
     *
     * @return the interface of a Patterns object.
     */
    IPatternsComp getPatterns();

    /**
     * Connects to the Patterns component via its interface.
     * Registers for property change events, retrieves current object of the patterns internal structure and regenerates the box tree.
     *
     * @param aPatterns the Patterns component interface.
     */
    void setPatterns(IPatternsComp aPatterns);
    
    /**
     * Get the value of BoxTree
     *
     * @return the value of BoxTree
    */
    CBox getBoxTree();

     /**
     * Set the value of BoxTree and notify observers about <code>BoxTree</code> property changes.
     *
     * @param aBoxTree new value of BoxTree
     */
    void setBoxTree(CBox aBoxTree);
     
   /**
     * Returns a box tree from a given AST node.
     * 
     * @param aNode the AST node
     * @return the box tree object
     */
    CBox format(CNode aNode );
     
     
     
    
}
