/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import Nodes.CNode;

/**
 *JTerm is the base class for all Java classes.
 * The fText property makes it possible to create plain text (rather than a
 * full tree) for any construct.
 *
 * @author jssanyu
 */
public abstract class JTerm extends CNode{

    /**
     *
     */
    public String fText;

    /**
     *
     */
    public JTerm(){
        super();
	fText="";
    }

    /**
     *
     * @param aText
     */
    public JTerm(String aText){
        super();
        fText=aText;
    }
        
    /**
     *
     * @param i
     * @return
     */
    @Override
     public String getData(int i){
        switch(i){
            case 0: return fText; 
            default:{
                assert false: 
                    "Error in JTerm.getData:" + Integer.toString(i);
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
            case 0: fText=aData; break;
            default:{
                assert false: 
                    "Error in JTerm.setData:" + Integer.toString(i);
            }
       }
    }
    
    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 1;
    }
    
    @Override
    public String getText(){
        return fText;
    }
    
    /**
     *
     * @param i
     * @param aNode
     * @return
     */
    @Override
    public boolean canSetNode(int i, CNode aNode) {
        return (0 <= i) && (i < count()) && (aNode instanceof JTerm);
    }
    
    /**
     *
     * @return
     */
    @Override
    public int count() {
        return 0;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i) {
       assert 0<=i & i<count(): String.format("In JTerm.getNode() failed: sortLabel=%s, i=%d,count=%d",sortLabel(),i,count());
       return null;
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void setNode(int i, CNode aNode) {
       assert canSetNode(i,aNode): String.format("In JTerm.setNode() failed: sortLabel=%s, i=%d,count=%d",sortLabel(),i,count());
    }
  
    @Override
        public String toString(){
        String vLabel = sortLabel();
        if(dataCount()>0){
            vLabel=vLabel+" ["+getData(0);
            for(int i = 1; i <=dataCount()-1; i++){ 
                vLabel=vLabel+"  "+getData(i);
            }
            vLabel=vLabel+" ]";
        }
        
       return vLabel;
    }
    
}
