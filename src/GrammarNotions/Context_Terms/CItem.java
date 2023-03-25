/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Context_Terms;


import General.CGeneral;
import Nodes.CNode;
import Nodes.CNodeKind;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssanyu
 */
public abstract class CItem extends CContext implements IItem {

    /**
     *
     */
    protected String fName;

    /**
     *
     */
    protected  CItem fAux;

    /**
     *
     */
    public CItem(){
       super();
    }

    /**
     *
     * @param aName
     */
    public CItem(String aName){
        assert canSetName(aName): String.format("In CItem.create failed;sortLabel=%s, aName=%s",sortLabel(),aName);
        fName=aName;
    }

    /**
     *
     * @param aName
     */
    @Override
    public void setName(String aName){
        assert canSetName(aName): String.format("In CItem.setName() failed: sortLabel=%s, aName=%s",sortLabel(),aName);
        fName=aName;
    }

    /**
     *
     * @return
     */
    @Override
    public String getName(){
        return fName;
     }

    /**
     *
     * @return
     */
    @Override
    public CContext auxCopy(){
       CItem vResult=null;
        try {
            //Create new Item of the same class
            vResult = (CItem) nodeClass().newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(CItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CItem.class.getName()).log(Level.SEVERE, null, ex);
        }
       //Make Faux refer to the newly created item
       fAux=vResult;

       //Copy data elements
       for(int i=0;i<=dataCount()-1;i++){
           vResult.setData(i, getData(i));
       }

       //Copy term elements
      for(int i=0;i<=termCount()-1;i++){
           vResult.setTerm(i, getTerm(i).copy());
      }
       return vResult;
    }
   /* @Override
    public void clearAux(){
        fAux=new CItem();
    }*/

    //Structure

    /**
     *
     * @param i
     * @param aNode
     * @return
     */
        @Override
    public boolean canSetNode(int i, CNode aNode) {
        return aNode instanceof CTerm & canSetTerm(i,(CTerm)aNode);
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
       assert 0<=i & i<count(): String.format("In CItem.getNode(): sortLabel=%s, i=%d,count=%d",sortLabel(),i,count());
       return getTerm(i);
    }

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public void setNode(int i, CNode aNode) {
        assert canSetNode(i,aNode): String.format("In CItem.setNode() failed: sortLabel=%s, aNode.sortLabel()=%s,i=%d,count=%d,termCount()=%d",sortLabel(),aNode.sortLabel(),i,count(),termCount());
        setTerm(i,(CTerm)aNode);
    }

    @Override
    public int count() {
       return termCount();
    }
    // Data

    /**
     *
     * @param i
     * @param aData
     * @return
     */
    @Override
    public boolean canSetData(int i, String aData) {
       return i==0 & canSetName(aData);
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i) {
        if(i==0){
            return fName;
        }else{
            assert false: String.format("CItem.getData() failed: sortLabel=%s, i=%d,dataCount=%d ",sortLabel(),i,dataCount());
            return null;
        }
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i, String aData) {
        assert canSetData(i,aData): String.format("CItem.setData() failed: sortLabel=%s, i=%d,count=%d, aData=%s",sortLabel(),i,dataCount(),aData);
        if(i==0){
           fName=aData;
        }else{
            assert false: String.format("In CItem.setData() failed:i=%d,dataCount=%d",i,dataCount());
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

    //Context methods

    /**
     *
     * @param i
     * @param aContext
     * @return
     */
        @Override
    public boolean canSetContext(int i, CContext aContext) {
        return false;
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
        assert 0<=i & i<contextCount() : String.format("CItem.getContext() failed: sortLabel=%s, i=%d,count=%d,contextCount=%d ",sortLabel(),i,contextCount());
        return null;
    }

    /**
     *
     * @param i
     * @param aContext
     */
    @Override
    public void setContext(int i, CContext aContext) {
        assert 0<=i & i<contextCount(): String.format("In CItem.setContext() failed: sortLabel=%s, i=%d,contextCount=%d",sortLabel(),i,contextCount());
    }

    /**
     *
     * @return
     */
    @Override
    public CContext copy(){
        CContext vResult=auxCopy();
        clearAux();
        return vResult;
    }
    // Data fields
    @Override
    public boolean canSetName(String aName) {
       return CGeneral.isIdentifier(aName, CGeneral.csLetter, CGeneral.csLetterDigit);
    }

   //Item methods
    @Override
    public boolean canSetTerm(int i, CTerm aTerm) {
        return 0<=i & i<termCount();
    }

    /**
     *
     * @return
     */
    @Override
    public int termCount() {
        return 0;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CTerm getTerm(int i) {
       assert 0<=i & i<termCount(): String.format("In CItem.getTerm() failed: sortLabel=%s, i=%d,Termcount=%d",sortLabel(),i,termCount());
       return null;
    }

    /**
     *
     * @param i
     * @param aTerm
     */
    @Override
    public void setTerm(int i, CTerm aTerm) {
       assert canSetTerm(i,aTerm): String.format("In CItem.setTerm() failed: sortLabel=%s, i=%d,termCount=%d",sortLabel(),i,termCount());
    }
    
    /**
     *
     * @return
     */
    @Override
    public CContextKind contextKind(){
        return CContextKind.ckItem;
    }

}
