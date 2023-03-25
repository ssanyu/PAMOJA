/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;

import Nodes.CNode;
import Nodes.CNodeKind;
import Nodes.INodeList;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssanyu
 */
public abstract  class CTermList extends CTerm implements ITermList,INodeList {

    /**
     *
     */
    protected ArrayList<Object> fList;
    
    /**
     *
     */
    public CTermList(){
        fList=new ArrayList<Object>();
    }

    /**
     *
     * @param aNodes
     * @param aData
     */
    public CTermList(ArrayList<CNode> aNodes,ArrayList<String> aData){
        fList=new ArrayList<Object>();
        for(int i=0;i<=aNodes.size()-1;i++)
            add(aNodes.get(i));
        for(int i=0;i<=aData.size()-1;i++)
            setData(i,aData.get(i));
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CTerm getTerm(int i){
        assert 0<=i & i<fList.size() : String.format("In CTermList.getTerm failed: sortLabel=%s, i=%d, fListSize=%d",sortLabel(),i,fList.size());
        return (CTerm)fList.get(i);
    }
    @Override
    public void setTerm(int i, CTerm aTerm){
        assert canSetTerm(i,aTerm) : String.format("In CTermList.setTerm failed: sortLabel=%s, i=%d, fListSize=%d, aTerm.sortLabel=%s",sortLabel(),i,fList.size(),aTerm.sortLabel());
        fList.set(i,aTerm);
    }

    /**
     *
     * @return
     */
    @Override
    public int termCount(){
        return fList.size();
    }

    /**
     *
     * @param aTerm
     */
    @Override
    public void addTerm(CTerm aTerm){
        assert canAddTerm(aTerm): String.format("In CTermList.addTerm failed: sortLabel=%s, aTerm.sortLabel()=%s",sortLabel(),aTerm.sortLabel());
        fList.add(aTerm);
    }

    /**
     *
     * @param i
     * @param aTerm
     */
    @Override
    public void insertTerm(int i, CTerm aTerm){
        fList.add(i,aTerm);
    }

    /**
     *
     * @param aTerm
     * @return
     */
    @Override
    public boolean canAddTerm(CTerm aTerm){
        return eltClass().isInstance(aTerm);
    }
    @Override
    public boolean canInsertTerm(int i, CTerm aTerm){
           return 0<=i & i<=count() & eltClass().isInstance(aTerm);
    }

    /**
     *
     * @param i
     * @param aTerm
     * @return
     */
    @Override
    public boolean canSetTerm(int i,CTerm aTerm){
         return 0<=i & i<count() & eltClass().isInstance(aTerm);
    }

    /**
     *
     * @return
     */
    @Override
     public CTerm copy() {
        CTermList vResult=null;
        try {
            vResult = (CTermList) nodeClass().newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(CTermList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CTermList.class.getName()).log(Level.SEVERE, null, ex);
        }

        for(int i=0;i<=termCount()-1;i++){
            vResult.add(getTerm(i).copy());
        }

        for(int i=0;i<=dataCount()-1;i++){
            vResult.setData(i,getData(i));
        }
        return vResult;
    }

    /**
     *
     * @return
     */
    @Override
    public abstract String eltSortLabel();

    /**
     *
     * @return
     */
    @Override
    public abstract int eltSortCode();

    /**
     *
     * @return
     */
    @Override
    public abstract Class eltClass();

    /**
     *
     * @param aNode
     * @return
     */
    @Override
    public int indexOf(CNode aNode) {
        return fList.indexOf(aNode);
    }

    /**
     *
     * @param aNode
     * @return
     */
    @Override
    public boolean canAdd(CNode aNode){
            return aNode instanceof CTerm & canAddTerm((CTerm)aNode);
    }
    @Override
    public boolean canDelete(int i){
        return 0<=i & i < count();
    }

    /**
     *
     * @param i
     * @param aNode
     * @return
     */
    @Override
    public boolean canInsert(int i, CNode aNode){
        return aNode instanceof CTerm & canInsertTerm(i,(CTerm)aNode);
    }

    /**
     *
     * @param aFro
     * @param aTo
     * @return
     */
    @Override
    public boolean canMove(int aFro, int aTo){
        return 0<=aFro & aFro<count() & 0<=aTo & aTo< count();
    }

    /**
     *
     * @param aNode
     * @return
     */
    @Override
    public boolean canRemove(CNode aNode){
        return fList.indexOf(aNode)!=-1;
    }

    /**
     *
     * @param i
     * @param j
     * @return
     */
    @Override
    public boolean canSwap(int i, int j){
        return 0<=i & i<count() & 0<=j & j<count();
    }

    /**
     *
     * @param aNode
     */
    @Override
    public void add(CNode aNode) {
        assert canAdd(aNode): String.format("In CTermList.add failed: sortLabel=%s, aNode.sortLabel()=%s",sortLabel(),aNode.sortLabel());
        fList.add(aNode);
        
    }

    /**
     *
     * @param aNode
     * @return
     */
    public boolean contains(CNode aNode){
        return fList.contains(aNode);
    }

    /**
     *
     * @param aList
     */
    public void addAll(CTermList aList){
       for(int i=0;i<aList.count();i++){
           fList.add(aList.getNode(i));
       }
      
   }
    
    /**
     *
     * @param i
     * @param aNode
     * @return
     */
    @Override
    public boolean canSetNode(int i,CNode aNode){
        return aNode instanceof CTerm & canSetTerm(i,(CTerm)aNode);
    }

    @Override
    public int count(){
        return termCount();
    }

    /**
     *
     * @param i
     */
    @Override
    public void delete(int i) {
        assert canDelete(i): String.format("In CTermList.delete failed: sortLabel=%s,i=%d,count=%d",sortLabel(),i,count());
        fList.remove(i);
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i){
        assert 0<=i & i<count(): String.format("In CTermList.getNode failed: sortLabel=%s,i=%d,count=%d",sortLabel(),i,count());
        return getTerm(i);
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void setNode(int i, CNode aNode) {
        assert canSetNode(i,aNode) : String.format("In CTermList.setNode failed: sortLabel=%s, i=%d, anode.SortLabel=%s",sortLabel(),i,aNode.sortLabel());
        setTerm(i,(CTerm)aNode);
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void insert(int i, CNode aNode) {
        assert canInsert(i,aNode): String.format("In CTermList.insert failed: sortLabel=%s,i=%d,count=%d,eltSortLabel=%s,aNode.SortLabel=%s",sortLabel(),i,count(),eltSortLabel(),aNode.sortLabel());
        fList.add(i, aNode);
        
    }

    /**
     *
     * @return
     */
    @Override
    public CNodeKind kind(){
        return CNodeKind.LIST;
    }

    /**
     *
     * @param aFrom
     * @param aTo
     */
    @Override
    public void move(int aFrom, int aTo) {
        assert canMove(aFrom,aTo): String.format("In CTermList.move failed: sortLabel=%s, count()=%d, aFrom=%d,aTo=%d",sortLabel(),count(),aFrom,aTo);
        CTerm vTerm=(CTerm)fList.get(aFrom);
        fList.add(aTo,vTerm);
        fList.remove(aFrom);
    }

    /**
     *
     * @param aNode
     */
    @Override
    public void remove(CNode aNode) {
       assert canRemove(aNode): String.format("In CTermList.remove failed: sortLabel=%s, aNode.sortLabel()=%s",sortLabel(),aNode.sortLabel());
       fList.remove(fList.indexOf(aNode));
    }

    /**
     *
     * @param i
     * @param j
     */
    @Override
    public void swap(int i, int j) {
       assert canSwap(i,j): String.format("In CTermList.swap failed: sortLabel=%s, i=%d,j=%d,count=%d",sortLabel(),i,j,count());
       CTerm vTerm=(CTerm)fList.get(i);
       fList.add(i,fList.get(j));
       fList.add(j,vTerm);
    }

   }
