/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import Nodes.CNode;
import Nodes.CNodeKind;
import java.util.ArrayList;

/**
 *
 * @author jssanyu
 */
public class Catch_List extends CNode {
   private ArrayList<JCatchBlock> fList;

    /**
     *
     */
    public Catch_List(){
       fList=new ArrayList<>();
   }
   
    /**
     *
     * @param aNodes
     * @param aData
     */
    public Catch_List(ArrayList<JCatchBlock> aNodes,ArrayList<String> aData){
       fList=new ArrayList<>();
       for(int i=0;i<=aNodes.size()-1;i++)
           fList.add(aNodes.get(i));
       for(int i=0;i<=aData.size()-1;i++)
           setData(i,aData.get(i));
   }
   
    /**
     *
     * @return
     */
    @Override
   public int sortCode() {
       return JASTNodeCodes.scCatch_List;
   }
   
    /**
     *
     * @return
     */
    @Override
   public String sortLabel() {
       return "Catch_List";
   }
   
    /**
     *
     * @return
     */
    @Override
   public CNodeKind kind() {
       return CNodeKind.LIST;
   }
   @Override
   public int count() {
       return fList.size();
   }

    /**
     *
     * @param i
     * @param aNode
     * @return
     */
    @Override
   public boolean canSetNode(int i, CNode aNode) {
       return (0 <= i) && (i < count()) && (aNode instanceof JParameter);
   }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
   public void setNode(int i, CNode aNode) {
       // check pre-condition
       assert canSetNode(i, aNode):
       "Catch_List.setNode.pre failed:"+
       "i == " + Integer.toString(i)+
       "; count() == " + Integer.toString(count()) +"; aNode.className = " + aNode.getClass().getName();
       fList.set(i,(JCatchBlock)aNode);
   }

    /**
     *
     * @param i
     * @return
     */
    @Override
   public JCatchBlock getNode(int i) {
   // check pre-condition
       assert (0 <= i) && (i < count() ):
           "aCatch_List.getNode.pre failed: " +
           "i == " + Integer.toString(i) +
           "count() == " + Integer.toString(count());
       return fList.get(i);
   }

    /**
     *
     * @param aNode
     * @return
     */
    public int indexOf(JCatchBlock aNode){
       return fList.indexOf(aNode);
   }

    /**
     *
     * @param aNode
     */
    public void add(JCatchBlock aNode){
       fList.add(aNode);
   }

    /**
     *
     * @param i
     */
    public void delete(int i){
       fList.remove(i);
   }

    /**
     *
     * @param i
     * @param aNode
     */
    public void insert(int i , JCatchBlock aNode){
       fList.add(i, aNode);
   }

    /**
     *
     * @param aFrom
     * @param aTo
     */
    public void move(int aFrom, int aTo){
       fList.add(aTo,fList.get(aFrom));
       fList.remove(aFrom);
   }

    /**
     *
     * @param aNode
     */
    public void remove(JCatchBlock aNode) {
       fList.remove(fList.indexOf(aNode));
   }

    /**
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
       assert canSwap(i,j): String.format("In Catch_List.swap failed: "
           + "sortLabel="+sortLabel()+", i="+i+",j="+j+",count="+count());
       fList.add(i,fList.get(j));
       fList.add(j,fList.get(i));
   }

    /**
     *
     * @param i
     * @param j
     * @return
     */
    public boolean canSwap(int i, int j){
       return 0<=i & i<count() & 0<=j & j<count();
   }
}

    

