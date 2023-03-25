/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.TreeBuilder;

import Components.IPAMOJAComponent;
import Nodes.CNode;
import java.util.ArrayList;



/**
 * An interface which all TreeBuilder components can implement. It provides methods for creating parse trees and for collaborating with Parser components.
 *@author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ITreeBuilderComp extends IPAMOJAComponent{

    /**
     * Determines if this TreeBuilder should include muitistick nodes in the parse tree.
     * 
     * @return <code>true</code> if the treebuilder should include multistick nodes in the parse tree, otherwise <code>false</code>.
     */
    boolean isMultiStick();

    /**
     * Sets the MultiStick mode of this TreeBuilder to the given value.
     * @param aMultiStick <code>true</code> if multistick nodes can be included in the parse tree, else <code>false</code>.
     */
    void setMultiStick(boolean aMultiStick);

      /**
     * Determines if this TreeBuilder should include nonterminal nodes in the parse tree.
     * 
     * @return <code>true</code> if the treebuilder should include nonterminal nodes in the parse tree, otherwise <code>false</code>.
     */
    boolean isNonTerminal();

    /**
     * Sets the NonTerminal mode of this TreeBuilder to the given value.
     * @param aNonTerminal <code>true</code> if nonterminal nodes can be included in the parse tree, else <code>false</code>.
     */
    void setNonTerminal(boolean aNonTerminal);

     /**
     * Determines if this TreeBuilder should include terminal nodes without data in the parse tree.
     * 
     * @return <code>true</code> if the treebuilder should include terminal nodes without data in the parse tree, otherwise <code>false</code>.
     */
    boolean isNoDataTerminal();

    /**
     * Sets the NoDataTerminal mode of this TreeBuilder to the given value.
     * @param aNoDataTerminal <code>true</code> if terminal nodes without data can be included in the parse tree, else <code>false</code>.
     */
    void setNoDataTerminal(boolean aNoDataTerminal);
    
    /**
     * Returns an Epsilon node.
     * 
     * @param aRoot
     * @param aPath
     * @return Epsilon node
     */
    CNode mkEps(String aRoot,String aPath);

    /**
     * Returns a Terminal node for the specified name.
     * @param aRoot
     * @param aPath
     * @param aName the name of a terminal.
     * @return Terminal node 
     */
    CNode mkTerm(String aRoot,String aPath,String aName); 

    /**
     * Returns a terminal-value node for the specified name and value.
     * @param aRoot
     * @param aPath
     * @param aName the name of a terminal .
     * @param aValue the value of the terminal.
     * @return terminal-value node
     */
    CNode mkTermData(String aRoot,String aPath, String aName, String aValue); 

    /**
     * Returns a nonterminal node with the specified name and body.
     * 
     * @param aRoot
     * @param aPath
     * @param aName the name of a nonterminal
     * @param aBody the body of a nonterminal.
     * @return nonterminal node
     */
    CNode mkProd(String aRoot,String aPath, String aName, CNode aBody); 

    /**
     * Returns a multidot node for the specified array of nodes.
     * @param aRoot
     * @param aPath
     * @param aArgs the array of nodes.
     * @return multidot node
     */
    CNode mkMultiDot(String aRoot,String aPath,ArrayList<CNode> aArgs); 

    /**
     * Returns a multistick node for the specified node and its index.
     * @param aRoot
     * @param aPath
     * @param aIndex the position of the node in a multistick rule
     * @param aArg the node at the specified position.
     * @return multistick node 
     */
    CNode mkMultiStick(String aRoot,String aPath,int aIndex, CNode aArg);

    /**
     * Returns a tuple node with the specified list of nodes and list of data.
     * @param aRoot
     * @param aPath
     * @param aArgs list of nodes
     * @param aData list of data
     * @return tuple node
     */
    CNode mkTuple(String aRoot,String aPath,ArrayList<CNode> aArgs,ArrayList<String> aData); 

    /**
     * Returns a star node for the specified list of nodes.
     * @param aRoot
     * @param aPath
     * @param aArgs the list of nodes.
     * @return star node
     */
    CNode mkStar(String aRoot,String aPath,ArrayList<CNode> aArgs);

     /**
     * Returns a plus node for the specified list of nodes.
     * @param aRoot
     * @param aPath
     * @param aArgs the list of nodes.
     * @return plus node
     */
    CNode mkPlus(String aRoot,String aPath,ArrayList<CNode> aArgs);

    /**
     * Returns an option node with the specified flag and node.
     * @param aRoot
     * @param aPath
     * @param aPresent <code>true</code> if a node exists, else <code>false</code>.
     * @param aArg the node.
     * @return option node
     */
    CNode mkOption(String aRoot,String aPath,boolean aPresent,CNode aArg);

    /**
     * Returns an Alt node for the specified list of nodes.
     * @param aRoot
     * @param aPath
     * @param aArgs the list of nodes.
     * @return Alt node
     */
    CNode mkAlt(String aRoot, String aPath,ArrayList<CNode> aArgs);
      
}
