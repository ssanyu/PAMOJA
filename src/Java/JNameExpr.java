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
public class JNameExpr extends JExpression {

    /**
     *
     */
    public String name;

    /**
     *
     * @param name
     */
    public JNameExpr(String name){
        this.name = name;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
       return JASTNodeCodes.scNameExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "NameExpr";
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
        return super.getNode(i);
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void setNode(int i, CNode aNode) {
        super.setNode(i, aNode);
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
