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
public class JBlockStatement extends JStatement {

    /**
     *
     */
    public Statement_List stmts;

    /**
     *
     * @param stmts
     */
    public JBlockStatement(Statement_List stmts) {
        this.stmts = stmts;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scBlockStmt;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "BlockStmt";
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
            return stmts;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           stmts=(Statement_List)aNode;
    }

    @Override
    public int count() {
       return 1;
    }


    
}
