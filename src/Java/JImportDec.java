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
public class JImportDec extends JTerm {
    
    /**
     *
     */
    public String name;

    /**
     *
     * @param name
     */
    public JImportDec(String name) {
        this.name = name;
        
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scImport;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "ImportNode";
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
    public int dataCount(){
        return 1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
     public String getData(int i){
        switch(i){
            case 0: return name; 
            default:{
                assert false: 
                    "Error in JImportDec.getData:" + Integer.toString(i);
                return ""; // to satisfy compiler
            }
        }
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i, String aData){
        switch(i){
            case 0: name=aData; break;
            default:{
                assert false: 
                    "Error in JImportDec.setData:" + Integer.toString(i);
            }
       }
    }
    
}
