/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ssanyu
 */
public abstract class CNodeFactory {

    /**
     *
     */
    protected Map<String,Class> fLabeltoClass;

    /**
     *
     */
    protected Map<Integer,Class> fCodetoClass;
      
    /**
     *
     * @param aCode
     * @return
     */
    protected abstract Class codeToClass(int aCode);

    /**
     *
     * @param aLabel
     * @return
     */
    protected abstract Class labelToClass(String aLabel);
    
    /**
     *
     * @return
     */
    public Map<String, Class> getfLabeltoClass() {
        return fLabeltoClass;
    }

    /**
     *
     * @param fLabeltoClass
     */
    public void setfLabeltoClass(Map<String, Class> fLabeltoClass) {
        this.fLabeltoClass = fLabeltoClass;
    }

    /**
     *
     * @return
     */
    public Map<Integer, Class> getfCodetoClass() {
        return fCodetoClass;
    }

    /**
     *
     * @param fCodetoClass
     */
    public void setfCodetoClass(Map<Integer, Class> fCodetoClass) {
        this.fCodetoClass = fCodetoClass;
    }
      
    /**
     *
     * @param aClass
     * @return
     */
    public CNode makeNodeofClass(Class aClass){
        if (aClass == null) return null;
        try{
            
            CNode vNode = (CNode)(aClass.newInstance());
            return vNode;
            
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
       
      }
  
    /**
     *
     * @param aCode
     * @return
     */
    public CNode makeNodeofCode(int aCode){
          Class vClass=codeToClass(aCode);
          
          return makeNodeofClass(vClass);
      }
       
    /**
     *
     * @param aLabel
     * @return
     */
    public  CNode makeNodeofLabel(String aLabel){
          Class vClass=labelToClass(aLabel);
          
          return makeNodeofClass(vClass);

      }
      
    /**
     *
     * @param aClass
     * @param aNodes
     * @param aData
     * @return
     */
    public CNode makeNodeofClassArgs(Class aClass, ArrayList<CNode> aNodes, ArrayList<String> aData){
         CNode vNode = makeNodeofClass(aClass);
         switch (vNode.kind()){
             case HOLE:
                 // TODO
                 assert false: "Method CNodeFactory.makeNodeOfClassArgs does not yet support nodekind nkHole";
             return vNode;
             case TUPLE:
                 // check argument lengths
                 assert(aNodes.size()==vNode.count())&(aData.size()==vNode.dataCount()): 
                     String.format("CNodeFactory.makeNodeofLabelArgs failed:"
                         + " sortLabel=%s, count=%d, dataCount=%d, aNodes.size()=%d"
                         + ",aData.size()=%d .", 
                         vNode.sortLabel(), vNode.count(),vNode.dataCount(), 
                         aNodes.size(), aData.size());
         
                 // Assign node arguments
                 for (int i = 0; i < aNodes.size(); i++){
                      vNode.setNode(i, aNodes.get(i));
                 }
         
                 // Assign data arguments
                 for (int i = 0; i < aData.size(); i++){
                     vNode.setData(i, aData.get(i));
                 }
             return vNode;
             case LIST:
                 // check argument length of data
                 assert(aData.size()==vNode.dataCount()): 
                     String.format("CNodeFactory.makeNodeofLabelArgs failed:"
                         + " sortLabel=%s, dataCount=%d"
                         + ",aData.size()=%d .", 
                         vNode.sortLabel(), vNode.dataCount(), aData.size());
         
                 // Add node arguments
                 for (int i = 0; i < aNodes.size(); i++){
                      ((INodeList)vNode).add(aNodes.get(i));
                 }
         
                 // Assign data arguments
                 for (int i = 0; i < aData.size(); i++){
                     vNode.setData(i, aData.get(i));
                 }
             return vNode;
             case OPTION:
                 // TODO
                 assert false: "Method CNodeFactory.makeNodeOfClassArgs does not yet support nodekind nkOption";
             return vNode;
             default:
                 // This should never happen
                 assert false: "Method CNodeFactory.makeNodeOfClassArgs called with unknown nodekind";
                 return null;
         }        
    }
          
    /**
     *
     * @param aLabel
     * @param aNodes
     * @param aData
     * @return
     */
    public CNode makeNodeofLabelArgs(String aLabel,ArrayList<CNode> aNodes, ArrayList<String> aData){
        Class vClass=labelToClass(aLabel);
        return makeNodeofClassArgs(vClass, aNodes, aData);
    }
     
    /**
     *
     * @param aCode
     * @param aNodes
     * @param aData
     * @return
     */
    public CNode makeNodeofCodeArgs(int aCode,ArrayList<CNode> aNodes, ArrayList<String> aData){
        Class vClass=codeToClass(aCode);
        return makeNodeofClassArgs(vClass, aNodes, aData);
    }
    
    /**
     *
     * @param aLabel
     * @return
     */
    public CNode makeNodeWithHoles(String aLabel){
        CNode vNode;
        CNode vSon;
        
        vNode=makeNodeofLabel(aLabel);
        Class vClass=labelToClass(aLabel);
        if(vClass!=null){
        Constructor[] constructors = vClass.getConstructors();
        if(constructors.length>1) {
            Class [] paraTypes=constructors[1].getParameterTypes();
            for(int i=0,k=0; (i<vNode.count()&k<=paraTypes.length);i++,k++){
                
                vSon= makeNodeWithHoles(paraTypes[k].getSimpleName().substring(1));
                
                vNode.setNode(i,vSon);
            }
            for(int j=0; j<vNode.dataCount();j++){
                vNode.setData(j, "??");
            }
        }
       }
        return vNode; 
    }
}
