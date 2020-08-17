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
public class JThisExpr extends JExpression {

    /**
     *
     */
    public static final JThisExpr SINGLETON = new JThisExpr(null);

    /**
     *
     */
    public JExpression classExpr;

    /**
     *
     * @param classExpr
     */
    public JThisExpr(JExpression classExpr) {
        this.classExpr = classExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scThisExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "ThisExpr";
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
            return classExpr;
        else return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           classExpr=(JExpression)aNode;
        else super.setNode(i, aNode);
    }

    @Override
    public int count() {
        return 1;
    }
    
}
