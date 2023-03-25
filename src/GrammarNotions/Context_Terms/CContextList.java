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
public abstract  class CContextList extends CContext implements IContextList, INodeList {

    /**
     *
     */
    protected ArrayList<Object> fList;
    
    /**
     *
     */
    public CContextList(){
        fList=new ArrayList<>();
    }

    /**
     *
     * @param aNodes
     * @param aData
     */
    public CContextList(ArrayList<CNode> aNodes,ArrayList<String> aData){
        fList=new ArrayList<>();
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
    public CContext getContext(int i){
        assert 0<=i & i<fList.size() : String.format("In CContextList.getContext failed: sortLabel=%s, i=%d, fListSize=%d",sortLabel(),i,fList.size());
        return (CContext)fList.get(i);
    }

    /**
     *
     * @param i
     * @param aContext
     */
    @Override
    public void setContext(int i, CContext aContext){
        assert canSetContext(i,aContext) : String.format("In CContextList.setContext failed: sortLabel=%s, i=%d, fListSize=%d, aContext.sortLabel=%s",sortLabel(),i,fList.size(),aContext.sortLabel());
        fList.set(i,aContext);
    }

    /**
     *
     * @return
     */
    @Override
    public int contextCount(){
        return fList.size();
    }

    /**
     *
     * @param aContext
     */
    @Override
    public void addContext(CContext aContext){
        assert canAddContext(aContext): String.format("In CContextList.addContext failed: sortLabel=%s, aContext.sortLabel()=%s",sortLabel(),aContext.sortLabel());
        fList.add(aContext);
    }

    /**
     *
     * @param i
     * @param aContext
     */
    @Override
    public void insertContext(int i, CContext aContext){
        fList.add(i,aContext);
    }

    /**
     *
     * @param aContext
     * @return
     */
    @Override
    public boolean canAddContext(CContext aContext){
        return eltClass().isInstance(aContext);
    }

    /**
     *
     * @param i
     * @param aContext
     * @return
     */
    @Override
    public boolean canInsertContext(int i, CContext aContext){
           return 0<=i & i<=count() & eltClass().isInstance(aContext);
    }

    /**
     *
     * @param i
     * @param aContext
     * @return
     */
    @Override
    public boolean canSetContext(int i,CContext aContext){
         return 0<=i & i<count() & eltClass().isInstance(aContext);
    }

    /**
     *
     * @return
     */
    @Override
    public CContext copy() {
        CContextList vResult=null;
        CItem vOldItem,vNewItem=null;
        try {
            vResult = (CContextList) nodeClass().newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(CContextList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CContextList.class.getName()).log(Level.SEVERE, null, ex);
        }
        //copy 1 by 1 all the data elements
        for(int i=0;i<=dataCount()-1;i++)
            vResult.setData(i,getData(i));
        //copy context elements in a way dependent on the contextkind
        switch(contextKind()){
            case ckSerial:
            case ckCollateral:
                for(int i=0; i<=contextCount()-1;i++)
                    vResult.addContext(getContext(i).auxCopy());
                for(int i=0; i<=contextCount()-1;i++)
                    getContext(i).clearAux();
            break;
            case ckRecursive:
                 for(int i=0; i<=contextCount()-1;i++){
                    assert getContext(i) instanceof CItem: String.format("In CContextList with contextKind=ckRecursive; expected subclass of CItem found %s", getContext(i).nodeClass());
                    vOldItem=(CItem)getContext(i);
                     try {
                            vNewItem = (CItem) vOldItem.nodeClass().newInstance();
                    } catch (InstantiationException ex) {
                            Logger.getLogger(CContextList.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                            Logger.getLogger(CContextList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    vOldItem.fAux=vNewItem;
                    vResult.addContext(vNewItem);
                 }
                for(int i=0; i<=contextCount()-1;i++){
                    vOldItem=(CItem)getContext(i);
                    vNewItem=vOldItem.fAux;
                    for(int j=0;j<=vOldItem.termCount()-1;j++)
                        vNewItem.setTerm(j,vOldItem.getTerm(j).copy());
                }
                 for(int i=0; i<=contextCount()-1;i++){
                     getContext(i).clearAux();
                 }
            break;
            default:
                assert false : String.format("In CContextList.copy: Unexpected contextKind value=%d", contextKind());
                break;
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
            return aNode instanceof CContext & canAddContext((CContext)aNode);
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
        return aNode instanceof CContext & canInsertContext(i,(CContext)aNode);
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
        assert canAdd(aNode): String.format("In CContextList.add failed: sortLabel=%s, aNode.sortLabel()=%s",sortLabel(),aNode.sortLabel());
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
    public void addAll(CContextList aList){
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
        return aNode instanceof CContext & canSetContext(i,(CContext)aNode);
    }

    @Override
    public int count(){
        return contextCount();
    }

    /**
     *
     * @param i
     */
    @Override
    public void delete(int i) {
        assert canDelete(i): String.format("In CCOntextList.delete failed: sortLabel=%s,i=%d,count=%d",sortLabel(),i,count());
        fList.remove(i);
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i){
        assert 0<=i & i<count(): String.format("In CCOntextList.getNode failed: sortLabel=%s,i=%d,count=%d",sortLabel(),i,count());
        return getContext(i);
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void setNode(int i, CNode aNode) {
        assert canSetNode(i,aNode) : String.format("In CContextList.setNode failed: sortLabel=%s, i=%d, anode.SortLabel=%s",sortLabel(),i,aNode.sortLabel());
        setContext(i,(CContext)aNode);
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void insert(int i, CNode aNode) {
        assert canInsert(i,aNode): String.format("In CCOntextList.inesrt failed: sortLabel=%s,i=%d,count=%d,eltSortLabel=%s,aNode.SortLabel=%s",sortLabel(),i,count(),eltSortLabel(),aNode.sortLabel());
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
        assert canMove(aFrom,aTo): String.format("In CContextList.move failed: sortLabel=%s, count()=%d, aFrom=%d,aTo=%d",sortLabel(),count(),aFrom,aTo);
        CContext vContext=(CContext)fList.get(aFrom);
        fList.add(aTo,vContext);
        fList.remove(aFrom);
    }

    /**
     *
     * @param aNode
     */
    @Override
    public void remove(CNode aNode) {
       assert canRemove(aNode): String.format("In CContextList.remove failed: sortLabel=%s, aNode.sortLabel()=%s",sortLabel(),aNode.sortLabel());
       fList.remove(fList.indexOf(aNode));
    }

    /**
     *
     * @param i
     * @param j
     */
    @Override
    public void swap(int i, int j) {
       assert canSwap(i,j): String.format("In CContextList.swap failed: sortLabel=%s, i=%d,j=%d,count=%d",sortLabel(),i,j,count());
       CContext vContext=(CContext)fList.get(i);
       fList.add(i,fList.get(j));
       fList.add(j,vContext);
    }

}
