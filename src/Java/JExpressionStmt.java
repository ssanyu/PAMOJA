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
public class JExpressionStmt extends JStatement {

    /**
     *
     */
    public  JExpression expr;

    /**
     *
     * @param expr
     */
    public JExpressionStmt(JExpression expr) {
        this.expr = expr;
    }
    
    /**
     *
     * @param aText
     */
    public JExpressionStmt(String aText){
        super.fText=aText;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scExprStmt;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "ExprStmt";
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
            return expr;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           expr=(JExpression)aNode;
    }

    @Override
    public int count() {
       return 1;
    }
}
