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
public class JSuperExpr extends JExpression {

    /**
     *
     */
    public JExpression classExpr;

    /**
     *
     * @param classExpr
     */
    public JSuperExpr(JExpression classExpr) {
        this.classExpr = classExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scSuperExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "SuperExpr";
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
