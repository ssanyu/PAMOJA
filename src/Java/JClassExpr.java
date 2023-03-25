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
public class JClassExpr extends JExpression {

    /**
     *
     */
    public final JType type;

    /**
     *
     * @param type
     */
    public JClassExpr(JType type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setNode(int i, CNode aNode) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    
}
