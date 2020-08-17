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
public class JFieldDec extends JBodyDec {

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
     * @param modifiers
     * @param type
     * @param name
     */
    public JFieldDec(JModifiers modifiers,JType type, String name) {
        this.modifiers = modifiers;
        this.type = type;
        this.name= name;
    }

    /**
     *
     * @param aText
     */
    public JFieldDec(String aText){
        fText=aText;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scFieldDec;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "FieldDec";
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
        else return super.getNode(i);
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           type=(JType)aNode;
        else super.setNode(i, aNode);
    }

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
        return 2;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i){
        if(modifiers==null){
            return super.getData(i);
        }else{
            switch(i){
                case 0: return JModifiers.toText(modifiers);
                case 1: return name;
                default:return super.getData(i);
            }
        }    
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i,String aData){ // Needs reconsideration
        
        switch(i){
            case 0: modifiers.set(i,null); break;
            case 1: name=aData; break;
            default:super.setData(i,aData);
        }
    }
    
  
  
    
}
