/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.ECFGNodes;

import Nodes.CNode;
import Nodes.CNodeKind;
import Nodes.INodeList;
import java.util.ArrayList;

/**
 * CECFGAbstractListNode is an abstract class which defines the common part of
 * star, plus, multidot and tuple nodes:
 * - It descends from CECFGNode
 * - It has a list of subtrees
 * - It is the ancestor of CStarNode, CPlusNode, CMultiDotNode and CTupleNode
 *
 * DO NOT CONFUSE WITH THE NO LONGER USED CLASS CECFGNodeList
 *
 * @author CHemerik
 */

public abstract class CECFGAbstractListNode extends CECFGNode implements INodeList{

    /**
     *
     */
    protected ArrayList<CECFGNode> fList;

    // constructors

    /**
     *
     */
        public CECFGAbstractListNode(){
         super("","");
         fList = new ArrayList<>();
    }

    /**
     *
     * @param aRoot
     * @param aPath
     */
    public CECFGAbstractListNode(String aRoot, String aPath){
        super(aRoot, aPath);
        fList = new ArrayList<>();
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aList
     */
    public CECFGAbstractListNode(String aRoot, String aPath,
               ArrayList<CECFGNode> aList){
        super(aRoot, aPath);
        fList = aList;
    }

    /**
     *
     * @return
     */
    @Override
    public CNodeKind kind(){
        return CNodeKind.LIST;
    }

    @Override
    public int count() {
        return fList.size();
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i) {
        // check pre-condition
        assert (0 <= i) && (i < count() ):
            "CECFGAbstractListNode.getNode.pre failed: " +
            "i == " + Integer.toString(i) +
            "count() == " + Integer.toString(count());

        return fList.get(i);
    }

    /**
     *
     * @param i
     * @param aTerm
     */
    @Override
    public void setNode(int i, CNode aTerm) {
        // check pre-condition
        assert canSetNode(i, aTerm):
            "CECFGAbstractListNode.setNode.pre failed: " +
            "i == " + Integer.toString(i) +
            "; count() == " + Integer.toString(count()) +
            "; aTerm.className = " + aTerm.getClass().getName();
        

        fList.set(i, (CECFGNode)aTerm);
    }
  /*  
    @Override
    public abstract String eltSortLabel() ;

    @Override
    public abstract int eltSortCode() ;

    @Override
    public abstract Class eltClass() ;
*/

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
    public boolean canAdd(CNode aNode) {
         return aNode instanceof CECFGNode & canAddECFGNode((CECFGNode)aNode);
    }

    /**
     *
     * @param aNode
     * @return
     */
    public boolean canAddECFGNode(CECFGNode aNode){
        return eltClass().isInstance(aNode);
    }
    @Override
    public boolean canDelete(int i) {
         return 0<=i & i < count();
    }

    /**
     *
     * @param i
     * @param aNode
     * @return
     */
    @Override
    public boolean canInsert(int i, CNode aNode) {
        return aNode instanceof CECFGNode & canInsertECFGNode(i,(CECFGNode)aNode);
    }
    
    /**
     *
     * @param i
     * @param aNode
     * @return
     */
    public boolean canInsertECFGNode(int i, CECFGNode aNode){
           return 0<=i & i<=count() & eltClass().isInstance(aNode);
    }

    /**
     *
     * @param aFro
     * @param aTo
     * @return
     */
    @Override
    public boolean canMove(int aFro, int aTo) {
        return 0<=aFro & aFro<count() & 0<=aTo & aTo< count();
    }

    /**
     *
     * @param aNode
     * @return
     */
    @Override
    public boolean canRemove(CNode aNode) {
        return fList.indexOf(aNode)!=-1;
    }

    /**
     *
     * @param i
     * @param j
     * @return
     */
    @Override
    public boolean canSwap(int i, int j) {
        return 0<=i & i<count() & 0<=j & j<count();
    }

    /**
     *
     * @param aNode
     */
    @Override
    public void add(CNode aNode) {
        assert canAdd(aNode): String.format("In CECFGAbstractListNode.add failed: sortLabel=%s, aNode.sortLabel()=%s",sortLabel(),aNode.sortLabel());
        fList.add((CECFGNode)aNode);
    }

    /**
     *
     * @param i
     */
    @Override
    public void delete(int i) {
        assert canDelete(i): String.format("In CECFGAbstractListNode.delete failed: sortLabel=%s,i=%d,count=%d",sortLabel(),i,count());
        fList.remove(i);
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void insert(int i, CNode aNode) {
         assert canInsert(i,aNode): String.format("In CECFGAbstractListNode.insert failed: sortLabel=%s,i=%d,count=%d,eltSortLabel=%s,aNode.SortLabel=%s",sortLabel(),i,count(),eltSortLabel(),aNode.sortLabel());
        fList.add(i,(CECFGNode)aNode);
    }

    /**
     *
     * @param aFrom
     * @param aTo
     */
    @Override
    public void move(int aFrom, int aTo) {
        assert canMove(aFrom,aTo): String.format("In CECFGAbstractListNode.move failed: sortLabel=%s, count()=%d, aFrom=%d,aTo=%d",sortLabel(),count(),aFrom,aTo);
        CECFGNode vNode=(CECFGNode)fList.get(aFrom);
        fList.add(aTo,vNode);
        fList.remove(aFrom);
    }

    /**
     *
     * @param aNode
     */
    @Override
    public void remove(CNode aNode) {
        assert canRemove(aNode): String.format("In CECFGAbstractListNode.remove failed: sortLabel=%s, aNode.sortLabel()=%s",sortLabel(),aNode.sortLabel());
       fList.remove(fList.indexOf(aNode));
    }
    
    /**
     *
     * @param i
     * @param j
     */
    @Override
    public void swap(int i, int j) {
       assert canSwap(i,j): String.format("In CECFGAbstractListNode.swap failed: sortLabel=%s, i=%d,j=%d,count=%d",sortLabel(),i,j,count());
       CECFGNode vNode=(CECFGNode)fList.get(i);
       fList.add(i,fList.get(j));
       fList.add(j,vNode);
    }
    
    /**
     *
     * @return
     */
    @Override
    public String eltSortLabel() {
       return "ECFGNode";
    }

    /**
     *
     * @return
     */
    @Override
    public int eltSortCode() {
        return CECFGNodeCodes.scECFGNode;
    }

    /**
     *
     * @return
     */
    @Override
    public Class eltClass() {
         return CECFGNode.class;
    }

}
