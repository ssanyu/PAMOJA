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
public class JReferenceType extends JType {

    /**
     *
     */
    public JType type;

    /**
     *
     * @param type
     */
    public JReferenceType(JType type) {
        this.type = type;
       
    }
    
    /**
     *
     * @param aText
     */
    public JReferenceType(String aText){
        fText=aText;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scRefType;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "RefTypeNode";
    }

    /**
     *
     * @return
     */
    @Override
    public CNodeKind kind() {
        return CNodeKind.TUPLE;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i) {
        if(i==0)
            return type;
        else return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           type=(JType)aNode;
        else super.setNode(i, aNode);
    }

    @Override
    public int count() {
        return 1;
    }
}
