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
public class JBinaryExpr extends JExpression {

    /**
     *
     */
    public static enum Operator {

        /**
         *
         */
        or, // ||

        /**
         *
         */
                and, // &&

        /**
         *
         */
                binOr, // |

        /**
         *
         */
                binAnd, // &

        /**
         *
         */
                xor, // ^

        /**
         *
         */
                equals, // ==

        /**
         *
         */
                notEquals, // !=

        /**
         *
         */
                less, // <

        /**
         *
         */
                greater, // >

        /**
         *
         */
                lessEquals, // <=

        /**
         *
         */
                greaterEquals, // >=

        /**
         *
         */
                lShift, // <<

        /**
         *
         */
                rSignedShift, // >>

        /**
         *
         */
                rUnsignedShift, // >>>

        /**
         *
         */
                plus, // +

        /**
         *
         */
                minus, // -

        /**
         *
         */
                times, // *

        /**
         *
         */
                divide, // /

        /**
         *
         */
                remainder, // %
    }

    /**
     *
     */
    public  JExpression left;

    /**
     *
     */
    public  JExpression right;

    /**
     *
     */
    public  Operator op;

    /**
     *
     * @param left
     * @param right
     * @param op
     */
    public JBinaryExpr(JExpression left, JExpression right, Operator op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    /**
     *
     * @param aText
     */
    public JBinaryExpr(String aText){
        super.fText=aText;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scBinaryExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "BinaryExpr";
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
            return left;
        else if(i==1)
            return right;
        else return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           left=(JExpression)aNode;
        else if(i==1)
           right=(JExpression)aNode;
        else super.setNode(i, aNode);
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
