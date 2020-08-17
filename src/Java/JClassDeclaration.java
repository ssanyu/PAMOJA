/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import Nodes.CNode;
import Nodes.CNodeKind;


/**
 * Constructs a class declaration with the following elements:
 * - List of modifiers
 * - class name
 * - name of the base class 
 * - List of interfaces the class implements
 * - members of the class (e.g fields, constructor(s) and method(s))
 * @author jssanyu
 */
public class JClassDeclaration extends JTypeDec{

    /**
     *
     */
    public JModifiers modifiers;

    /**
     *
     */
    public String  baseClass;

    /**
     *
     */
    public ClassOrInterfaceType_List interfaces;

    /**
     *
     */
    public BodyDec_List members;

    /**
     *
     * @param modifiers
     * @param name
     * @param baseClass
     * @param interfaces
     * @param members
     */
    public JClassDeclaration(JModifiers modifiers, String name, String baseClass,ClassOrInterfaceType_List interfaces,BodyDec_List members) {
        super(name);
        this.modifiers = modifiers;
        this.baseClass= baseClass;
        this.interfaces=interfaces;
        this.members = members;
    }
    
    /**
     *
     * @param modifiers
     * @param name
     * @param baseClass
     * @param interfaces
     * @param aMemText
     */
    public JClassDeclaration(JModifiers modifiers, String name, String baseClass,ClassOrInterfaceType_List interfaces,String aMemText){
        super(name);
        this.modifiers = modifiers;
        this.baseClass= baseClass;
        this.interfaces=interfaces;
        fText=aMemText;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scClassDec;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "ClassDec";
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
            return interfaces;
        else if(i==1)
            return members;
        else return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           interfaces=(ClassOrInterfaceType_List)aNode;
        else if(i==1)
            members=(BodyDec_List)aNode;
        else super.setNode(i, aNode);
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
        return 3;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i){
        if(i==0)
            return name;
        else if(i==1)
            return JModifiers.toText(modifiers);
        else if(i==2)
            return baseClass;
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
             name=aData;
        else if(i==1)
             modifiers=null;  // needs reconsideration
        else if(i==2)
            baseClass=aData;
    }

    
}
