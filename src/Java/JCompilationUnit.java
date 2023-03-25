/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;


import Nodes.CNode;
import Nodes.CNodeKind;

/**
 *The CompilationUnit class presents for the whole parse tree in a file.
 *It consists of : package statement, import declaration list and type declaration list

 * @author jssanyu
 */
public class JCompilationUnit extends JTerm {

    /**
     *
     */
    public String pakage;

    /**
     *
     */
    public ImportDec_List imports;

    /**
     *
     */
    public TypeDec_List types;
    
    //aText=string for comments

    /**
     *
     * @param pakage
     * @param imports
     * @param aText
     * @param types
     */
        public JCompilationUnit(String pakage, ImportDec_List imports,String aText, TypeDec_List types) {
        this.pakage = pakage;
        this.imports = imports;
        fText=aText;
        this.types = types;
    }

    /**
     *
     * @param pakage
     * @param imports
     * @param types
     */
    public JCompilationUnit(String pakage, ImportDec_List imports, TypeDec_List types) {
        this.pakage = pakage;
        this.imports = imports;
        this.types = types;
    }
    
    /**
     *
     * @param aText
     */
    public JCompilationUnit(String aText) {
        super.fText=aText;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scCompUnit;
    }            

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "CompilationUnit";
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
            return imports;
        else if(i==1)
            return types;
        else return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
        if(i==0)
           imports=(ImportDec_List)aNode;
        else if(i==1)
            types=(TypeDec_List)aNode;
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
        return super.dataCount()+1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
     public String getData(int i){
        switch(i){
            case 0: 
                return pakage;
            case 1:
                return super.getData(0);
            default:{
                assert false: 
                    "Error in JCompilationUnit.getData:" + Integer.toString(i);
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
            case 0: pakage=aData; break;
            case 1: super.setData(0,aData);
            default:{
                assert false: 
                    "Error in JCompilationUnit.setData:" + Integer.toString(i);
            }
       }
    }
}
