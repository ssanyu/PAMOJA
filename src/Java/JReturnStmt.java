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
public class JReturnStmt extends JStatement {

    /**
     *
     */
    public JExpression expr;

    /**
     *
     * @param expr
     */
    public JReturnStmt(JExpression expr) {
        this.expr = expr;
    }
    
    /**
     *
     * @param aText
     */
    public JReturnStmt(String aText){
        super.fText=aText;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
       return JASTNodeCodes.scReturn;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "ReturnNode";
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
}
