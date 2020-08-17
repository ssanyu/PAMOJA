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
public class JBreakStmt extends JStatement{

    /**
     *
     */
    public String id;

    /**
     *
     */
    public JBreakStmt() {
       id="break";
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scBreak;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "BreakStmt";
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
        return super.getNode(i);
    }

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
            return id;
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
           id=aData;
        else super.setData(i, aData);
    }
    
}
