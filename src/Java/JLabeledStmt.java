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
public class JLabeledStmt extends JStatement {

    /**
     *
     */
    public  String label;

    /**
     *
     */
    public JStatement stmt;

    /**
     *
     * @param label
     * @param stmt
     */
    public JLabeledStmt(String label, JStatement stmt) {
        this.label = label;
        this.stmt = stmt;
     }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scLabelStmt;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "LabelStmt";
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
            return stmt;
        else return super.getNode(i);
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           stmt=(JStatement)aNode;
        else super.setNode(i, aNode);
    }

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


    
}
