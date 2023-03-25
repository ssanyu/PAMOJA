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
public class JTryStmt extends JStatement{

    /**
     *
     */
    public Statement_List stmts;

    /**
     *
     */
    public Catch_List  catches;

    /**
     *
     */
    public Statement_List finalies;

    /**
     *
     * @param stmts
     * @param catches
     * @param finalies
     */
    public JTryStmt(Statement_List stmts,Catch_List  catches,Statement_List finalies){
        this.stmts=stmts;
        this.catches=catches;
        this.finalies=finalies;
    }

    /**
     *
     * @param stmts
     * @param catches
     */
    public JTryStmt(Statement_List stmts, Catch_List  catches){
        this.stmts=stmts;
        this.catches=catches;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scTryStmt;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "TryStmt";
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
            return stmts;
        else if(i==1)
            return catches;
        else if(i==2)
            return finalies;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           stmts=(Statement_List)aNode;
        else if(i==1)
           catches=(Catch_List)aNode;
        else if(i==2)
            finalies=(Statement_List)aNode;
    }

    @Override
    public int count() {
        return 3;
    }
    
}
