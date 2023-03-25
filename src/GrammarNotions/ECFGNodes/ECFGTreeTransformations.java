/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GrammarNotions.ECFGNodes;


/**
 *
 * @author jssanyu
 */
import Nodes.CNode;
import Nodes.INodeList;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ECFGTreeTransformations{
 /*  public static CECFGNode trMultiDotToTuple(CECFGNode aNode){
        ArrayList<CECFGNode> ts = new ArrayList();
        ArrayList<String> ds = new ArrayList();
        
        
            for (int i = 0; i < aNode.count(); i++){
                CECFGNode v = (CECFGNode) aNode.getNode(i);
                switch (v.sortCode()){
                    case CECFGNodeCodes.scTermNode: 
                        // ignore terms without data
                        break;
                    case CECFGNodeCodes.scTermValueNode:
                        if (v.getRoot().equals(aNode.getRoot())){
                            ds.add( ((CTermValueNode) v).getSymValue() );
                        }else{
                            ts.add( trMultiDotToTuple(v));    
                        }
                        break;
                    default:
                        ts.add( trMultiDotToTuple(v));
                }
            }
             
        return new CTupleNode(aNode.getRoot(), aNode.getPath(), ts, ds);
    } */
    
     // This method takes a tree of CECFGNodes as parameter and init replaces
    // all occurrences of CMultiDotNodes by CTupleNodes

    /**
     *
     * @param ANode
     * @return
     */
       public static CECFGNode trMultiDotToTuple(CECFGNode ANode){
        CNode vResult = null;

        // first copy the node itself
        try {
            if(ANode!=null){
               vResult = ANode.nodeClass().newInstance();
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(CNode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CECFGNode vResult2 = (CECFGNode) vResult;
       // copy substructure, depending on the node kind
       if (vResult2 != null)
           switch (ANode.kind()){
               case TUPLE:
                   if (ANode.sortCode() == CECFGNodeCodes.scTermNode){
                       // replace CTermNode by CTupleNode
                       ArrayList<CECFGNode> ts = new ArrayList<>();
                       ArrayList<String> ds = new ArrayList<>();
                       return new CTupleNode(ANode.getRoot(), ANode.getPath(), ts, ds);
                   }else if (ANode.sortCode() == CECFGNodeCodes.scTermValueNode){
                       // replace CTermValueNode by CTupleNode
                       ArrayList<CECFGNode> ts = new ArrayList<>();
                       ArrayList<String> ds = new ArrayList<>();
                       ds.add( ((CTermValueNode)ANode).getSymValue() );
                       return new CTupleNode(ANode.getRoot(), ANode.getPath(), ts, ds);
                       
                   }else{ // default case  e.g NonTerminals, Multisticks
                        
                       for(int i=0;i<=ANode.count()-1;i++){
                           vResult2.setNode(i,trMultiDotToTuple( (CECFGNode)ANode.getNode(i)));
                       }
                       for(int i=0;i<=ANode.dataCount()-1;i++){
                           
                           vResult2.setData(i,ANode.getData(i));
                       }
                   }
                   break;
               case LIST:
                   if (ANode.sortCode() == CECFGNodeCodes.scMultiDotNode){
                       // replace CMultiDotNode by CTupleNode
                       ArrayList<CECFGNode> ts = new ArrayList<>();
                       ArrayList<String> ds = new ArrayList<>();
        
                       for (int i = 0; i < ANode.count(); i++){
                           CECFGNode v = (CECFGNode) ANode.getNode(i);
                           switch (v.sortCode()){
                               case CECFGNodeCodes.scTermNode: 
                                   // ignore terms without data
                               break;
                               case CECFGNodeCodes.scTermValueNode:
                                   if (v.getRoot().equals(ANode.getRoot())){
                                      ds.add( ((CTermValueNode) v).getSymValue() );
                                   }else{
                                      ts.add( trMultiDotToTuple(v));    
                                   }
                               break;
                               default:
                                   ts.add( trMultiDotToTuple(v));
                           }
                       }
                       return new CTupleNode(ANode.getRoot(), ANode.getPath(), ts, ds);
                       
                   }else{ // default case e.g Alt, Star Nodes
                       for(int i=0;i<=ANode.count()-1;i++){
                           ((INodeList)vResult2).add(trMultiDotToTuple((CECFGNode)ANode.getNode(i)));
                       }
                       for(int i=0;i<=ANode.dataCount()-1;i++){
                           vResult2.setData(i,ANode.getData(i));
                       }
                   }
                   break;
               case OPTION:
                  // assert false: "CNode.copyTree not yet implemented for kind OPTION";
                   COptionNode op=(COptionNode)ANode;
                   return new COptionNode(op.fRoot, op.fPath, op.isPresent(), trMultiDotToTuple(op.fArg));
               
               case HOLE:
                   assert false: "CNode.copyTree not yet implemented for kind HOLE";
                   break;
               default:
                   assert false: "unknown nodekind in CNode.copyTree";
           }
      return vResult2;
   }
    
}
