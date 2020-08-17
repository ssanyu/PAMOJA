/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;

import Nodes.CNode;
import Nodes.CNodeKind;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssanyu
 */
public abstract class CTermTuple extends CTerm implements ITermTuple {

    /**
     *
     */
    public CTermTuple(){
        super();
   }

    /**
     *
     * @param aNodes
     * @param aData
     */
    public CTermTuple(ArrayList<CNode> aNodes, ArrayList<String> aData){
       super(aNodes,aData);
    }
    //Override CTerm methods

    /**
     *
     * @param i
     * @param aTerm
     * @return
     */
        @Override
    public boolean canSetTerm(int i, CTerm aTerm) {
         return 0<=i & i<termCount();
    }

    @Override
    public CTerm getTerm(int i) {
        //Always fails because termCount=0

        assert 0<=i & i<termCount():  String.format("CTermTuple.getTerm.pre failed: i=%d, termCount=%d .",i,termCount());
        return null;

    }

    /**
     *
     * @return
     */
    @Override
    public int termCount() {
        return 0;
    }

    @Override
    public void setTerm(int i, CTerm aTerm) {
        //Always fails because termCount=0
        assert canSetTerm(i,aTerm): String.format("CTermTuple.setTerm.pre failed: sortLabel=%s, i=%d, termCount=%d .",sortLabel(),i,termCount());
    }

    /**
     *
     * @return
     */
    @Override
    public CTerm copy() {
        CTermTuple vResult=null;
        try {
            //create a node of same class as self
            vResult = (CTermTuple) nodeClass().newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(CTermTuple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CTermTuple.class.getName()).log(Level.SEVERE, null, ex);
        }
        //copy all context elements
        for(int i=0;i<=contextCount()-1;i++){
            vResult.setContext(i, getContext(i).auxCopy());
        }
        
        //copy all term elements
        for(int i=0;i<=termCount()-1;i++){
            vResult.setTerm(i,getTerm(i).copy());
        }
        
        //Copy all reference elements
        for(int i=0;i<=referenceCount()-1;i++){
            if(getReference(i).fAux!=null){
                vResult.setReference(i, getReference(i).fAux);
            }else{
                vResult.setReference(i,getReference(i));
            }
        }

        //copy data fields
        for(int i=0;i<=dataCount()-1;i++){
            vResult.setData(i,getData(i));
        }

        for(int i=0;i<=contextCount()-1;i++){
            getContext(i).clearAux();
        }

        return vResult;
    }
    // Override CNode methods

    /**
     *
     * @param i
     * @param aNode
     * @return
     */
        @Override
    public boolean canSetNode(int i, CNode aNode) {
        boolean vResult;
        vResult=0<=i & i<count();
        if(i<contextCount()){
            vResult=vResult & aNode instanceof CContext & canSetContext(i,(CContext)aNode);
        }else{
            vResult=vResult & aNode instanceof CTerm & canSetTerm(i-contextCount(),(CTerm)aNode);
        }
        return vResult;
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
       if(i<contextCount()){
           return getContext(i);
       }else {
           return getTerm(i-contextCount());
       }
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void setNode(int i, CNode aNode) {
        assert canSetNode(i,aNode):  String.format("CTermTuple.setNode.pre failed: sortLabel=%s, aNode.sortLabel=%s, i=%d, count=%d, contextCount=%d,termCount=%d",sortLabel(),aNode.sortLabel(),i,count(),contextCount(),termCount());
        if(i<contextCount()){
            setContext(i,(CContext)aNode);
        }else{
            setTerm(i-contextCount(),(CTerm)aNode);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int count() {
        return contextCount() + termCount();
    }

    //ITermTuple methods

    /**
     *
     * @param i
     * @param aContext
     * @return
     */
        @Override
    public boolean canSetContext(int i, CContext aContext) {
        return 0<=1 & i<contextCount();
    }

    @Override
    public CContextKind contextKind() {
        return CContextKind.ckSerial;
    }

    /**
     *
     * @return
     */
    @Override
    public int contextCount() {
        return 0;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CContext getContext(int i) {
         //Always fails because contextCount=0
        assert 0<=i & i< contextCount():  String.format("CTermTuple.getContext() failed: i=%d, contextCount=%d .",i,contextCount());
        return null;
    }

    @Override
    public void setContext(int i, CContext aContext) {
        //Always fails because contextCount=0

        assert canSetContext(i,aContext):  String.format("CTermTuple.setContext() failed: i=%d, contextCount=%d .",i,contextCount());
    }

    /**
     *
     * @param i
     * @param aRef
     * @return
     */
    @Override
    public boolean canSetReference(int i, CItem aRef) {
        return 0<=i & i< referenceCount();
    }

    /**
     *
     * @return
     */
    @Override
    public int referenceCount() {
        return 0;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CItem getReference(int i) {
        //Always fails because referenceCount=0
      
        assert 0<=i & i<referenceCount():  String.format("CTermTuple.getReference() failed: i=%d, referenceCount=%d .",i,referenceCount());
        return null;

    }

    /**
     *
     * @param i
     * @param aRef
     */
    @Override
    public void setReference(int i, CItem aRef) {
       //Always fails because referenceCount=0
       assert canSetReference(i,aRef):  String.format("CTermTuple.setReference.pre failed: i=%d, referenceCount=%d .",i,referenceCount());

    }
}
