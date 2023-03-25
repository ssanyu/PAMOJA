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
public class JVariableDeclarationExpr extends JExpression {

    /**
     *
     */
    public JModifiers modifiers;

    /**
     *
     */
    public JType type;

    /**
     *
     */
    public String name;

    /**
     *
     */
    public JExpression init;

    /**
     *
     * @param modifiers
     * @param type
     * @param name
     * @param init
     */
    public JVariableDeclarationExpr(JModifiers modifiers, JType type, String name,JExpression init) {
        this.modifiers = modifiers;
        this.type = type;
        this.name=name;
        this.init = init;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scVarDecExpr;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "VarDecExpr";
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
            return type;
        else if(i==1)
            return init;
        else return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           type=(JType)aNode;
        else if(i==1)
           init=(JExpression)aNode;
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
        return 2;
    }

    
}
