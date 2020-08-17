/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

import SLR.SLRAutomata.Move;



/**
 *
 * @author jssanyu
 */
public class CParseLogEntry {

    /**
     *
     */
    public String stackContents;

    /**
     *
     */
    public String symName;

    /**
     *
     */
    public Move action;
    
    /**
     *
     * @param aStackContents
     * @param aSymName
     * @param aAction
     */
    public CParseLogEntry(String aStackContents,
                          String aSymName,
                          Move aAction){
        stackContents=aStackContents;
        symName=aSymName;
        action=aAction;
        
    }
    
    @Override
    public String toString(){
        StringBuilder str=new StringBuilder();
        
        str.append(stackContents);
        str.append("        ");
        str.append(symName).append(" ").append("        ");
        str.append(action.toString());
        
        return str.toString();
    }
}
