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
public class JSwitchStmt extends JStatement {

    /**
     *
     */
    public JExpression selector;

    /**
     *
     */
    public  SwitchEntryStmt_List entries;

    /**
     *
     * @param selector
     * @param entries
     */
    public JSwitchStmt(JExpression selector, SwitchEntryStmt_List entries) {
        this.selector = selector;
        this.entries = entries;
    }

    /**
     *
     * @param selector
     * @param entries
     */
    public JSwitchStmt(String selector, SwitchEntryStmt_List entries) {
        super.fText = selector;
        this.entries = entries;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scSwitch;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "SwitchStmt";
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
            return selector;
        else if(i==1)
            return entries;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           selector=(JExpression)aNode;
        else if(i==1)
           entries=( SwitchEntryStmt_List)aNode;
    }

    @Override
    public int count() {
        return 2;
    }


    
}
