/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;

import Nodes.CNode;
import Nodes.CNodeKind;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssanyu
 */
    public abstract class CContextTuple extends CContext implements IContextTuple {

    /**
     *
     */
    public CContextTuple(){
       super();
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
       return  aNode instanceof CContext & canSetContext(i,(CContext)aNode);
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
      assert 0<=i & i<count(): String.format("In CContextTuple.getContext.pre failed: sortLabel=%s, i=%d,count=%d ",sortLabel(),i,count());
      return getContext(i);
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void setNode(int i, CNode aNode) {
       assert canSetNode(i,aNode): String.format("In CContextTuple.setNode.pre failed: sortLabel=%s, aNode.SortLabel=%s, i=%d,count=%d,contextCount=%d ",sortLabel(),aNode.sortLabel(),i,contextCount());
       setContext(i,(CContext)aNode);
    }

    @Override
    public int count() {
        return contextCount();
    }
 // context methods

    /**
     *
     * @param i
     * @param aContext
     * @return
     */
        @Override
    public boolean canSetContext(int i, CContext aContext) {
        return 0<=i & i<contextCount();
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
     * @return
     */
    @Override
    public CContext copy() {
        CContextTuple vResult=null;
        CItem vNewItem=null, vOldItem;
        try {
            
            vResult = (CContextTuple) nodeClass().newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(CContextTuple.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CContextTuple.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0; i<=dataCount()-1;i++){
            vResult.setData(i, getData(i));
        }
         switch(contextKind()){
             case ckSerial:
             case ckCollateral:
                 for(int i=0;i<=contextCount()-1;i++){
                     vResult.setContext(i, getContext(i).auxCopy());
                 }
                 for(int i=0;i<=contextCount()-1;i++){
                    getContext(i).clearAux();
                 }
             case ckRecursive:
                  for(int i=0;i<=contextCount()-1;i++){
                    assert getContext(i) instanceof CItem: String.format("In CContextTuple.copy with contextKind= ckRecursive; expected subclass of CItem, found %s",getContext(i).nodeClass().getClass().getName());
                    vOldItem=(CItem)getContext(i);
                    try {
                             vNewItem = (CItem) vOldItem.nodeClass().newInstance();
                    } catch (InstantiationException ex) {
                            Logger.getLogger(CContextTuple.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                            Logger.getLogger(CContextTuple.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    vOldItem.fAux=vNewItem;
                    vResult.setContext(i, vNewItem);
                  }
                  for(int i=0;i<=contextCount()-1;i++){
                      vOldItem=(CItem)getContext(i);
                      vNewItem=vOldItem.fAux;
                      for(int j=0;j<=vOldItem.termCount()-1;j++){
                          vNewItem.setTerm(j,vOldItem.getTerm(j).copy());
                      }
                  }
                  for(int i=0;i<=contextCount()-1;i++){
                      getContext(i).clearAux();
                  }
             default: assert false: String.format("UnExpected contextKind value %d", contextKind());
    }
         return vResult;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CContext getContext(int i) {
       assert 0<=i & i<contextCount(): String.format("In CContextTuple.getContext.pre failed: sortLabel=%s, i=%d,contextCount=%d ",sortLabel(),i,contextCount());
       return null;
    }

    /**
     *
     * @param i
     * @param aContext
     */
    @Override
    public void setContext(int i, CContext aContext) {
         assert canSetContext(i,aContext): String.format("In CContext.setContext.pre failed: sortLabel=%s, i=%d,contextCount=%d ",sortLabel(),i,contextCount());
      
    }

  }
