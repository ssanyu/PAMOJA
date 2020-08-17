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
public class JSwitchEntryStmt extends JStatement {

    /**
     *
     */
    public  JExpression label;

    /**
     *
     */
    public  Statement_List stmts;

    /**
     *
     * @param label
     * @param stmts
     */
    public JSwitchEntryStmt(JExpression label, Statement_List stmts) {
        this.label = label;
        this.stmts = stmts;
    }
    
    /**
     *
     * @param label
     * @param stmts
     */
    public JSwitchEntryStmt(String label,Statement_List stmts){
        super.fText=label;
        this.stmts = stmts;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scSwitchEntry;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "SwitchEntryStmt";
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
            return label;
        else if(i==1)
            return stmts;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           label=(JExpression)aNode;
        else if(i==1)
           stmts=(Statement_List)aNode;
    }

    /**
     *
     * @return
     */
    @Override
    public int count() {
        return 2;
    }


    
}
