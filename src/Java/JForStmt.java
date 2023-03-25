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
public class JForStmt extends JStatement{

    /**
     *
     */
    public Expression_List init;

    /**
     *
     */
    public Expression_List update;

    /**
     *
     */
    public Expression_List iterable;

    /**
     *
     */
    public Statement_List body;

    /**
     *
     * @param init
     * @param iterable
     * @param update
     * @param body
     */
    public JForStmt(Expression_List init, Expression_List iterable, Expression_List update, Statement_List body) {
        this.iterable = iterable;
        this.init = init;
        this.update = update;
        this.body = body;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scFor;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "ForStmt";
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
            return init;
        else if(i==1)
            return update;
        else if(i==2)
            return iterable;
        else if(i==1)
            return body; 
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           init=(Expression_List)aNode;
        else if(i==1)
            update=(Expression_List)aNode;
        else if(i==2)
            iterable=(Expression_List)aNode;
        else if(i==3)
            body=(Statement_List)aNode;
    }

    @Override
    public int count() {
        return 4;
    }


    
}
