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
public final class JParameter extends JTerm {

    /**
     *
     */
    public JType type;

    /**
     *
     */
    public String paraName;

    /**
     *
     * @param type
     * @param paraName
     */
    public JParameter(JType type,String paraName) {
        this.type = type;
        this.paraName = paraName;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scPara;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "ParaNode";
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

    /**
     *
     * @return
     */
    @Override
    public int count() {
        return 1;
    }

    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i) {
        if(i==0)
            return paraName;
        else return super.getData(i);
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i, String aData) {
        if(i==0)
           paraName=aData;
        else super.setData(i, aData);
    }

    
}
