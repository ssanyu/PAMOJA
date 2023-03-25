package Java;

import Nodes.CNode;
import Nodes.CNodeKind;

/**
 *
 * @author HP
 */
public class JConstructorInvocationStmt extends JStatement{

    /**
     *
     */
    public String invocation;

    /**
     *
     * @param invocation
     */
    public JConstructorInvocationStmt(String invocation) {
       this.invocation=invocation;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scConstInvocStmt;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "ConstInvocStmt";
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
            return invocation;
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
           invocation=aData;
        else super.setData(i, aData);
    }
    
}
