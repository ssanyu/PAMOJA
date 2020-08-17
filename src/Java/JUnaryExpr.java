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
public class JUnaryExpr extends JExpression {

    /**
     *
     */
    public static enum Operator {

        /**
         *
         */
        positive, // +

        /**
         *
         */
                negative, // -

        /**
         *
         */
                preIncrement, // ++

        /**
         *
         */
                preDecrement, // --

        /**
         *
         */
                not, // !

        /**
         *
         */
                inverse, // ~

        /**
         *
         */
                posIncrement, // ++

        /**
         *
         */
                posDecrement, // --
    }

    /**
     *
     */
    public  JExpression expr;

    /**
     *
     */
    public  Operator op;

    /**
     *
     * @param expr
     * @param op
     */
    public JUnaryExpr(JExpression expr, Operator op) {
        this.expr = expr;
        this.op = op;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scUnaryExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "UnaryExpr";
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
        else return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           expr=(JExpression)aNode;
        else super.setNode(i, aNode);
    }

    @Override
    public int count() {
        return 1;
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
