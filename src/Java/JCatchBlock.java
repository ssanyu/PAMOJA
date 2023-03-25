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
public class JCatchBlock extends JTerm {

    /**
     *
     */
    public JParameter para;

    /**
     *
     */
    public Statement_List stmts;
    
    /**
     *
     * @param para
     * @param stmts
     */
    public JCatchBlock(JParameter para, Statement_List stmts){
        this.para=para;
        this.stmts=stmts;
    }

    /**
     *
     * @param aText
     */
    public JCatchBlock(String aText){
        fText=aText;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scCatchBlock;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "CatchBlock";
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
            return para;
        else if(i==1)
            return stmts;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           para=(JParameter)aNode;
        else if(i==1)
           stmts=(Statement_List)aNode;
    }

    @Override
    public int count() {
        return 2;
    }
    

}
