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
public class JAssertStmt extends JStatement {

    /**
     *
     */
    public JExpression check;

    /**
     *
     */
    public String msg;

    /**
     *
     * @param check
     * @param msg
     */
    public JAssertStmt(JExpression check, String msg) {
        this.check = check;
        this.msg = msg;
    }
    
    /**
     *
     * @param aText
     */
    public JAssertStmt(String aText){
        super.fText=aText;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scAssert;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "AssertStmt";
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
            return check;
        else return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           check=(JExpression)aNode;
        else super.setNode(i, aNode);
    }

    /**
     *
     * @return
     */
    @Override
    public int count() {
       return 1;
    }

    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 1;
    }
    
    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i) {
        if(i==0)
            return msg;
        else return super.getData(i);
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i, String aData) {
        if(i==0)
           msg=aData;
        else super.setData(i, aData);
    }
    
}
