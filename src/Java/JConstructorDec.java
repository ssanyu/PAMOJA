/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import Nodes.CNode;
import Nodes.CNodeKind;

/**
 *
 * @author jssanyu
 */
public class JConstructorDec extends JBodyDec {

    /**
     *
     */
    public JModifiers modifiers;

    /**
     *
     */
    public String name;

    /**
     *
     */
    public Parameter_List parameters;

    /**
     *
     */
    public Statement_List statements;

    /**
     *
     * @param modifiers
     * @param name
     * @param parameters
     * @param statements
     */
    public JConstructorDec( JModifiers modifiers,String name, Parameter_List parameters,Statement_List statements) {
        this.modifiers = modifiers;
        this.name = name;
        this.parameters = parameters;
        this.statements = statements;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scConstDec;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "ConstructorDec";
    }

    /**
     *
     * @return
     */
    @Override
    public CNodeKind kind() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i) {
         if(i==0)
            return parameters;
        else if(i==1)
            return statements;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           parameters=(Parameter_List)aNode;
        else if(i==1)
            statements=(Statement_List)aNode;
    }

    @Override
    public int count() {
        return 2;
    }

    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 2;
    }
    
    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i){
        if(i==0)
             return JModifiers.toText(modifiers); 
        else if(i==1)
             return name;
        else return null;
   }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i,String aData){
        if(i==0)
             modifiers=null;   // Reconsider
        else if(i==2)
            name=aData; 
   }
    
}
