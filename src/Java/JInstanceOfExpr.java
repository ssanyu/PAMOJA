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
public class JInstanceOfExpr extends JExpression {

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
    public JInstanceOfExpr(JExpression expr, JType type) {
        this.expr = expr;
        this.type = type;
    }

    /**
     *
     * @param aText
     */
    public JInstanceOfExpr(String aText){
        super.fText=aText;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scInstanceOfExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "Instanceof";
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
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           expr=(JExpression)aNode;
        else if(i==1)
            type=(JType)aNode;
        
    }

    @Override
    public int count() {
        return 2;
    }
}
