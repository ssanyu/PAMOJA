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
public class JDoStmt extends JStatement{

    /**
     *
     */
    public JExpression condition;

    /**
     *
     */
    public Statement_List body;

    /**
     *
     * @param condition
     * @param body
     */
    public JDoStmt(JExpression condition, Statement_List body) {
        this.condition = condition;
        this.body = body;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scDo;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "DoStmt";
    }

    /**
     *
     * @param i
     * @param aNode
     * @return
     */
    @Override
    public boolean canSetNode(int i, CNode aNode) {
        throw new UnsupportedOperationException("Not supported yet.");
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
            return body;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           condition=(JExpression)aNode;
        else if(i==1)
          body=(Statement_List)aNode;
    }

    @Override
    public int count() {
        return 2;
    }



    
}
