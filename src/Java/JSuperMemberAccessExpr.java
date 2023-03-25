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
public class JSuperMemberAccessExpr extends JExpression {

    /**
     *
     */
    public final String name;

    /**
     *
     * @param name
     */
    public JSuperMemberAccessExpr(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scSuperMemberAccessExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "SupMemAccExpr";
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i) {
         return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        super.setNode(i, aNode);
    }

    @Override
    public int count() {
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public CNodeKind kind() {
        return CNodeKind.HOLE;
    }
    
}
