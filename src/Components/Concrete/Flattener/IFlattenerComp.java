/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Flattener;

import Components.Concrete.ParseTree.IParseTreeComp;
import Components.IPAMOJAComponent;
import SymbolStream.CSymbolStream;

/**
 * Defines services for interacting with other components( like, ParseTree component).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IFlattenerComp extends IPAMOJAComponent {
    
    /**
     *
     * @return
     */
    IParseTreeComp getParseTree();

    /**
     * Connects to <code>ParseTreeComp</code> component via its interface -- Set the value of <code>ParseTree</code>.
     * Register for property change events, retrieve current value of ParseTree object and regenerate the symbolstream.
     *
     * @param aParseTree new value of ParseTree
     */
    void setParseTree(IParseTreeComp aParseTree);
    
    //internal representation of stream

    /**
     *
     * @return
     */
        CSymbolStream getSymbolStream();

    /**
     *
     * @param aSymbolStream
     */
    void setSymbolStream(CSymbolStream aSymbolStream);

   
  // void setSymbolStreamText(String aSymbolStreamText); 
}
