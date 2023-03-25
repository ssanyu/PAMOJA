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
public class JClassOrInterfaceType extends JType {

    /**
     *
     */
    public String name;
    
    /**
     *
     * @param name
     */
    public JClassOrInterfaceType(String name) {
        
        this.name = name;
       
    }

    @Override
    public String toString(){
        return name;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scClassOrInterfaceType;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "ClassOrInterfaceType";
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
        assert false: String.format("CNode.getNode().pre failed: i=%d, count=%d .",i,count());
        return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
       assert false: String.format("CNode.setNode().pre failed: i=%d, count=%d .",i,count());
    }

    @Override
    public int count() {
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int dataCount() {
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
            return name;
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
           name=aData;
        else super.setData(i, aData);
    }
    
}
