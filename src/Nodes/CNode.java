 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssanyu
 */
public abstract class CNode implements INode {

    /**
     *
     */
    protected HashMap<String,Object> fAnno;
    
    /**
     *
     */
    public CNode(){
        fAnno=new HashMap<>();
    }

    /**
     *
     * @param aNodes
     * @param aData
     */
    public CNode(ArrayList<CNode> aNodes, ArrayList<String> aData){
         fAnno=new HashMap<>();

        //check argument lengths
         assert (aNodes.size()==count())& (aData.size()==dataCount()): String.format("Constructor CNode with arguments failed: sortLabel=%s, count=%d, dataCount=%d, aNodes=%d,aData=%d .",sortLabel(), count(),dataCount(), aNodes.size(), aData.size());
        
        //Assign node arguments
        for(int i=0; i<=count()-1; i++){
            setNode(i,aNodes.get(i));
        }
        //Assign data arguments
        for(int i=0; i<=dataCount()-1;i++){
            setData(i,aData.get(i));
        }
    }
    
     
    @Override
    public String toString(){
        String vLabel = sortLabel();
        if(kind()==CNodeKind.HOLE){
            vLabel="<"+vLabel+">";
        }else{
          if(dataCount()>0){
            vLabel=vLabel+" [" +getData(0);
            for(int i = 1; i <=dataCount()-1; i++){
                vLabel=vLabel+"  "+getData(i);
            }
            vLabel=vLabel+" ]";
          }
        }
        return vLabel;
    }
    //Labelling

    /**
     *
     * @return
     */
        @Override
    public abstract int sortCode();

    /**
     *
     * @return
     */
    @Override
    public abstract String sortLabel();

    //Structure

    /**
     *
     * @param i
     * @param aNode
     * @return
     */
        @Override
    public abstract boolean canSetNode(int i, CNode aNode);

    /**
     *
     * @return
     */
    public abstract CNodeKind kind();   

    /**
     *
     * @param i
     * @return
     */
    @Override
    public abstract CNode getNode(int i);

    /**
     *
     * @param i
     * @param aNode
     */
    @Override
    public abstract void setNode(int i, CNode aNode);

    /**
     *
     * @return
     */
    @Override
    public abstract int count();
   
    
    //Data

    /**
     *
     * @param i
     * @return
     */
        @Override
    public String getData(int i){
        //Always fails because DataCount=0
        String s=String.format("CNode.getData.pre failed: i=%d, dataCount=%d .",i,dataCount());
        assert 0<=i & i<dataCount(): s;
        return s;
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i, String aData){
        //Always fails because DataCount()=0
   
        assert 0<=i & i<dataCount(): String.format("CNode.setData.pre failed: i=%d, dataCount=%d .",i,dataCount());
    
    }

    /**
     *
     * @param i
     * @param aData
     * @return
     */
    @Override
    public boolean canSetData(int i,String aData){
        return 0<=i & i<dataCount();
    }
  
    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 0;
    }
    //text

    /**
     *
     * @return
     */
        @Override
    public boolean hasText(){
        return false;

    }
    @Override
    public String getText(){
            return '<'+ sortLabel() + '>';
    }

    //Annotation

    /**
     *
     * @param aName
     * @param aObject
     * @return
     */
        @Override
    public boolean canSetAnno(String aName, Object aObject){
            return true;
    }

    /**
     *
     * @param aName
     * @return
     */
    @Override
    public Object getAnno(String aName){
         Object vResult=null;
         if(fAnno!=null && fAnno.containsKey(aName)){
             vResult=fAnno.get(aName);
         } else{
              vResult=new Object();
         }
        return vResult;
   }
    
    /**
     *
     * @param aName
     * @return
     */
    @Override
    public boolean hasAnno(String aName){
        boolean vResult;
        if(fAnno==null || fAnno.isEmpty()){
            vResult= false;
        } else {
            vResult=fAnno.containsKey(aName);
        }
        return vResult;
    }
   
    /**
     *
     * @param aName
     * @param aObject
     */
    @Override
    public void setAnno(String aName, Object aObject){
        assert ( canSetAnno(aName,aObject)) : String.format("CNode.setAnno().pre failed; aName=%s",aName);
        if(fAnno==null){
             fAnno=new HashMap<>();
        }
        fAnno.put(aName,aObject);
    }

    /**
     *
     */
    @Override
    public void clearAllAnno(){
        if(fAnno !=null){
            fAnno.clear();
        }
    }

    // Operations

    /**
     *
     * @return
     */
        public Class<? extends CNode> nodeClass(){
                 return this.getClass();
    }
    
    /**
     *
     * @return
     */
    @Override
   public CNode copyTree(){
        CNode vResult = null;
        // first copy the node itself
        try {
            vResult = nodeClass().newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(CNode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       // copy substructure, depending on the node kind
       if (vResult != null)
           switch (kind()){
               case TUPLE:
                   for(int i=0;i<=count()-1;i++){
                       vResult.setNode(i,getNode(i).copyTree());
                   }
                   for(int i=0;i<=dataCount()-1;i++){
                       vResult.setData(i,getData(i));
                   }
                   break;
               case LIST:
                   for(int i=0;i<=count()-1;i++){
                       ((INodeList)vResult).add(getNode(i).copyTree());
                   }
                   for(int i=0;i<=dataCount()-1;i++){
                       vResult.setData(i,getData(i));
                   }
                   break;
               case OPTION:
                   assert false: "CNode.copyTree not yet implemented for kind nkOption";
                   break;
               case HOLE:
                   assert false: "CNode.copyTree not yet implemented for kind nkHole";
                   break;
               default:
                   assert false: "unknown nodekind in CNode.copyTree";
           }
      return vResult;
   }

    /**
     *
     * @return
     */
    public HashMap<String, Object> getfAnno() {
        return fAnno;
    }

    /**
     *
     * @param fAnno
     */
    public void setfAnno(HashMap<String, Object> fAnno) {
        this.fAnno = fAnno;
    }

    
    
   
}