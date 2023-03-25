/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import Nodes.CNodeKind;

/**
 *
 * @author jssanyu
 */
public class JComments extends JTypeDec {

    /**
     *
     */
    public String fComments;
    
    /**
     *
     * @param aText
     */
    public JComments(String aText){
        super(aText);
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
       return JASTNodeCodes.scComments;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "Comments";
    }

    /**
     *
     * @return
     */
    @Override
    public CNodeKind kind() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
