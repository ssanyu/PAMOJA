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
public class JIfStmt extends JStatement {

    /**
     *
     */
    public JExpression condition;

    /**
     *
     */
    public Statement_List thenStmt;

    /**
     *
     */
    public Statement_List elseStmt;
    
    /**
     *
     */
    public Statement_List defaultStmt;

    /**
     *
     * @param condition
     * @param thenStmt
     * @param elseStmt
     */
    public JIfStmt(JExpression condition, Statement_List thenStmt, Statement_List elseStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
       
    }
    
    /**
     *
     * @param condition
     * @param thenStmt
     * @param elseStmt
     * @param defaultStmt
     */
    public JIfStmt(JExpression condition, Statement_List thenStmt, Statement_List elseStmt,Statement_List defaultStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
        this.defaultStmt = defaultStmt;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scIf;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "IfStmtNode";
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
            return condition;
        else if(i==1)
            return thenStmt;
        else if(i==2)
            return elseStmt;
        else if(i==3)
            return defaultStmt;
        else return null;
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           condition=(JExpression)aNode;
        else if(i==1)
            thenStmt=(Statement_List)aNode;
        else if(i==2)
            elseStmt=(Statement_List)aNode;
        else if(i==3)
            defaultStmt=(Statement_List)aNode;
    }

    /**
     *
     * @return
     */
    @Override
    public int count() {
        return 4;
    }

    
}
