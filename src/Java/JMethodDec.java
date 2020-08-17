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
public class JMethodDec extends JBodyDec {

    /**
     *
     */
    public JModifiers modifiers;

    /**
     *
     */
    public JType returnType;

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
     * @param returnType
     * @param name
     * @param parameters
     * @param statements
     */
    public JMethodDec(JModifiers modifiers,JType returnType, String name, Parameter_List parameters, Statement_List statements) {
        this.modifiers = modifiers;
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
        this.statements =statements;
    }
    
    /**
     *
     * @param modifiers
     * @param returnType
     * @param name
     * @param parameters
     * @param statements
     * @param aText
     */
    public JMethodDec(JModifiers modifiers,JType returnType, String name, Parameter_List parameters, Statement_List statements,String aText) {
        this.modifiers = modifiers;
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
        this.statements =statements;
        fText=aText;
    }

    /**
     *
     * @param aText
     */
    public JMethodDec(String aText){
        fText=aText;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scMethodDec;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "MethodDec";
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
            return returnType;
        else if(i==1)
            return parameters;
        else if(i==2)
            return statements;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           returnType=(JType)aNode;
        else if(i==1)
           parameters=(Parameter_List)aNode;
        else if(i==2)
            statements=(Statement_List)aNode;
    }

    @Override
    public int count() {
        return 3;
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
        else return super.getData(i);
   }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i,String aData){
        if(i==0)
             modifiers=null; // reconsider
        else if(i==1)
             name=aData;
        else super.setData(i,aData);
    }

    
}
