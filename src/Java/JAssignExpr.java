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
public class JAssignExpr extends JExpression {

    /**
     *
     */
    public static enum Operator {

     /**
      *
      */
     assign, // =

        /**
         *
         */
                plus, // +=

        /**
         *
         */
                minus, // -=

     /**
      *
      */
             star, // *=

     /**
      *
      */
             slash, // /=

        /**
         *
         */
                and, // &=

        /**
         *
         */
                or, // |=

     /**
      *
      */
             xor, // ^=

     /**
      *
      */
             rem, // %=

        /**
         *
         */
                lShift, // <<=

        /**
         *
         */
                rSignedShift, // >>=

        /**
         *
         */
                rUnsignedShift, // >>>=
    }

    /**
     *
     */
    public  JExpression target;

    /**
     *
     */
    public  JExpression value;

    /**
     *
     */
    public  Operator op;

    /**
     *
     * @param target
     * @param value
     * @param op
     */
    public JAssignExpr(JExpression target, JExpression value, Operator op) {
        this.target = target;
        this.value = value;
        this.op = op;
    }
    
    /**
     *
     * @param aText
     */
    public JAssignExpr(String aText){
        super.fText=aText;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scAssignExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "AssignExpr";
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
            return target;
        else if(i==1)
            return value;
        else return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           target=(JExpression)aNode;
        else if(i==1)
           value=(JExpression)aNode;
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
