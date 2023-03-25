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
public class JCastExpr extends JExpression {

    /**
     *
     */
    public JExpression expr;

    /**
     *
     */
    public JType type;
    
    /**
     *
     * @param expr
     * @param type
     */
    public JCastExpr(JExpression expr, JType type){
        this.expr = expr;
        this.type = type;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scCastExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "CastExpr";
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
        else if(i==1)
            return type;
        else return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           expr=(JExpression)aNode;
        else if(i==1)
            type=(JType)aNode;
        else super.setNode(i, aNode);
    }
    @Override
    public int count() {
        return 2;
    }
}
