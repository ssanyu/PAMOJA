/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import Nodes.CNode;
import Nodes.CNodeKind;

/* Allocates a new method call expression object.
 * - expr :the expression which indicates an object or
 *         a class. This may be null for invocation on 'this'.
 * - name :the method name.
 * - args :the argumetns for this method.
 * @author jssanyu
 */

/**
 *
 * @author HP
 */

public class JMethodCallExpr extends JExpression {

    /**
     *
     */
    public JExpression expr;

    /**
     *
     */
    public String name;

    /**
     *
     */
    public Expression_List args;

    /**
     *
     * @param expr
     * @param name
     * @param args
     */
    public JMethodCallExpr(JExpression expr, String name,Expression_List args) {
        this.expr=expr;
        this.name = name;
        this.args = args;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scMethodCallExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "MethodCallNode";
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
            return expr;
        else if(i==1)
            return args;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           expr=(JExpression)aNode;
        else if(i==1)
            args=(Expression_List)aNode;
    }

    @Override
    public int count() {
        return 2;
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
