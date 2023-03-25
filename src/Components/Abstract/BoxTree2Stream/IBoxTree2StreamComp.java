/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.BoxTree2Stream;

import Boxes.CBox;
import Components.Abstract.AST2BoxTree.IAST2BoxTreeComp;
import Components.IPAMOJAComponent;
import SymbolStream.CSymbolStream;



/**
 * Defines services for interacting with other components ( like, AST2BoxTree and Stream2Text components).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IBoxTree2StreamComp extends IPAMOJAComponent{

   /**
     * Get the value of SymbolStream
     *
     * @return the value of SymbolStream
     */
    CSymbolStream getSymbolStream();

    /**
     * Set the value of symbolstream and notify observers about <code>SymbolStream</code> property changes.
     *
     * 
     * @param aSymbolStream
     */
    void setSymbolStream(CSymbolStream aSymbolStream);
    
     /**
     * Access the AST2BoxTree component via its interface.
     *
     * @return the ASTtoBoxTree interface
     */
    IAST2BoxTreeComp getASTtoBoxTree();

   /**
     * Connect to ASTtoBoxTree component via its interface.
     * Register for property change events, retrieve current value of a box tree object and regenerate the symbolstream.
     *
     * @param aASTtoBoxTree IAST2BoxTreeComp object.
     */
    void setASTtoBoxTree(IAST2BoxTreeComp aASTtoBoxTree);
    
     /**
     * Get the value of BoxTree
     *
     * @return the value of BoxTree
     */
    CBox getBoxTree();

     /**
     * Set the value of BoxTree
     *
     * @param aBoxTree new value of BoxTree
     */
    void setBoxTree(CBox aBoxTree);
    
}
