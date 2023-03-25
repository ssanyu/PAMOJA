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
public class JFieldAccessExpr extends JExpression {

    /**
     *
     */
    public JExpression object;

    /**
     *
     */
    public String field;

    /**
     *
     * @param object
     * @param field
     */
    public JFieldAccessExpr(JExpression object,String field) {
        this.object = object;
        this.field = field;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scFieldAccessExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "FieldAccessNode";
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
            return object;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           object=(JExpression)aNode;
    }

    /**
     *
     * @return
     */
    @Override
    public int count() {
       return 1;
    }



    
}
