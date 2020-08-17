/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.TreeEditor;

import Nodes.CNode;
import Nodes.CNodeKind;
import Nodes.INodeList;
import Signatures.CSignature;
import Analyzers.CSignatureAnalyzer;
import static Nodes.CNodeKind.LIST;
import static Nodes.CNodeKind.TUPLE;
import Nodes.INodeOption;
import java.util.ArrayList;

/** 
 * A utility class implementing the various tree editing operations.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CTreeEngine {

    protected boolean fModified;

    protected CSignature fSignature;

    protected CSignatureAnalyzer fSignatureAnalyzer; 

    protected String fRootSortName;

    protected CNode fRoot;

    protected CNode fFocus; 
    protected ArrayList<CNode> fLeaves;

    protected ArrayList<CNode> fHoles;
    protected ArrayList<CNode> fRootPath;
    protected ArrayList<String> fAcceptableSorts;
    protected int fLeafIndex;
    protected int fHoleIndex;
    protected boolean vFound; // only for use by buildRootPath and BRP !!
    
    /**
     * Creates an instance of a TreeEngine.
     */
    public CTreeEngine(){
        fModified = false;
        fRoot = null;
        fFocus = null;
        fLeaves = new ArrayList<>();
        fHoles = new ArrayList<>();
        fRootPath = new ArrayList<>();
        fAcceptableSorts = new ArrayList<>();
        fLeafIndex = -1;
        fHoleIndex = -1;
        fSignatureAnalyzer=new CSignatureAnalyzer();
    }
   

    /**
     * Sets the RootSortName to the given name.
     * 
     * @param aRootSortName the name to set.
     */
       public void setRootSortName(String aRootSortName){
        assert canSetRootSort(aRootSortName):String.format("CTreeEngine.setRootSortName.pre failed; aRootSortName =%s", aRootSortName);
        if(fRoot!=null && !atMost(fRoot.sortLabel(),fRootSortName)){
            clear();
        }
        fRootSortName=aRootSortName;
    }
    
    /**
     * Returns the name of the root sort.
     * 
     * @return the String for the RootSortName.
     */
    public String getRootSortName(){
        return fRootSortName;
    }

    /**
     * Returns a signature object for this tree engine.
     * 
     * @return the Signature object.
     */
    public CSignature getSignature(){
        return fSignature;
    }
    
    /**
     * Assigns a signature object to this tree engine.
     * 
     * @param aSignature the signature object assigned.
     */
    public void setSignature(CSignature aSignature){
         fSignature=aSignature;
         fSignatureAnalyzer.setSignature(fSignature);
    }

    /**
     * Returns the root node of the AST.
     * 
     * @return the root node.
     */
    public CNode getRoot(){
        return fRoot;
    }

    /**
     * Returns a list of nodes that uniquely identify a path to anode in a tree, beginning from the root node. 
     * @return
     */
    public ArrayList<CNode> getRootPath(){
        return fRootPath;
    }

    /**
     *
     * @return
     */
    public ArrayList<CNode> getLeaves(){
        return fLeaves;
    }

    /**
     *
     * @return
     */
    public ArrayList<CNode> getHoles(){
        return fHoles;
    }

    /**
     * Sets the focus to the specified node.
     * 
     * @param aFocus the specified node to focus.
     */
    public void setFocus(CNode aFocus){
        assert canSetFocus(aFocus): String.format("CTreeEngine.SetFocus.pre failed.");
        fFocus=aFocus;
        adjustToFocus();
    }
    
    /**
     * Returns the current focus of the AST.
     * 
     * @return the currently focused node.
     */
    public CNode getFocus(){
        return fFocus;
    }
   
    /**
     * Checks if the given RootSortName can be set.
     * 
     * @param aRootSortName the given RootSortName to set.
     * @return <code>true</code> if the RootSortName can be set, otherwise <code>false</code>.
     */
    public boolean canSetRootSort(String aRootSortName){
        return validSort(aRootSortName);
    }
  
    /**
     * Validates if the given SortName exists in the list of sort definitions.
     * 
     * @param aSortName the sort name to validate.
     * @return <code>true</code> if the SortName is valid, else <code>false</code>.
     */
    public boolean validSort(String aSortName){
        boolean vResult;
        vResult=fSignature!=null;
        vResult=vResult && (fSignatureAnalyzer.indexOfSortDef(aSortName)!=-1);
        
        return vResult;
    }
   
    /**
     *
     * @param aSortName1
     * @param aSortName2
     * @return
     */
    public boolean atMost(String aSortName1, String aSortName2){
        return fSignatureAnalyzer.atMost(fSignatureAnalyzer.findSortDef(
                                                                aSortName1)
                                         ,fSignatureAnalyzer.findSortDef(
                                                                aSortName2));
    }
    
    /**
     *
     * @param aSortName1
     * @param aSortName2
     * @return
     */
    public boolean less(String aSortName1, String aSortName2){
        return fSignatureAnalyzer.less(fSignatureAnalyzer.findSortDef(
                                                                aSortName1)
                                         ,fSignatureAnalyzer.findSortDef(
                                                                aSortName2));
    }
    
    /**
     * Clears the AST and all its information.
     */
    public void clear(){
        if(fRoot!=null){
            fRoot=null;
            fLeaves.clear();
            fHoles.clear();
            fRootPath.clear();
            fAcceptableSorts.clear();
            fFocus=null;
        }
    }
  /**
   * Builds a RootPath as a list of nodes, from the root node to the focus node.
   */
   private void buildRootPath(){
       vFound=false;
       fRootPath.clear();
       if(fFocus!=null)
           BRP(fRoot);
   }
  
    /**
     * Builds a RootPath as a list of nodes, from the given root node to the focus node.
     * 
     * @param aNode the root node given.
     */
    protected void BRP(CNode aNode){
              
       fRootPath.add(aNode);
       
       if(aNode==fFocus)
           vFound=true;
       int i=0;
       while(i!=aNode.count() && !vFound){
           BRP(aNode.getNode(i));
           i=i+1;
       }
       if(!vFound){
           fRootPath.remove(fRootPath.size()-1);
       }
   } 
    
    /**
     * Builds a list of child nodes, from the root node to the focus node.
     */
    protected void buildLeaves(){
       fLeaves.clear();
       if(fRoot!=null)
           BL(fRoot);
   }
   
    /**
     * Builds a list of child nodes, from the given root node to the focus node.
     * 
     * @param aNode the root node given.
     */
    protected void BL(CNode aNode){
     if(aNode!=null){
       if(aNode.count()==0){
            fLeaves.add(aNode);
       }else{
           for(int i=0;i<aNode.count();i++){
               BL(aNode.getNode(i));
           }
       } 
     }      
   }
  
    /**
     * Builds a list of holes, from the list of child nodes.
     */
    protected void buildHoles(){
       CNode vNode;
       fHoles.clear();
       for(int i=0;i<fLeaves.size();i++){
           vNode=fLeaves.get(i);
           if(isHole(vNode)){
               fHoles.add(vNode);
           }
       }
   }
   

    /**
     * Checks if a given node is a hole.
     * 
     * @param aNode the node to check.
     * @return <code>true</code> if the node is a hole, else <code>false</code>.
     */
       public boolean isHole(CNode aNode){
        return aNode.kind()==CNodeKind.HOLE;
   }

    /**
     *
     */
    public void adjustToFocus(){
         adjustLeafIndex();
         adjustHoleIndex();
         buildRootPath();
         adjustAcceptableSorts();

   } 
   
    /**
     * Creates a list of acceptable sorts for the newly focused node.
     */
    public void adjustAcceptableSorts(){
       String vSortName;
       fAcceptableSorts.clear();
       vSortName=acceptableFocusSort();
       
        fAcceptableSorts.add(vSortName);
      
       if(fSignature!=null){
            for(int i=0; i<fSignatureAnalyzer.sortCount();i++){
                if(less(fSignatureAnalyzer.sortDef(i).getName(),vSortName)){
                    fAcceptableSorts.add(fSignatureAnalyzer.sortDef(i).getName());
                }
            }
       }
   }
   
    /**
     *
     */
    public void adjustHoleIndex(){
       fHoleIndex=fHoles.indexOf(fFocus);
  }
   
    /**
     *
     */
    public void adjustLeafIndex(){
      fLeafIndex=fLeaves.indexOf(fFocus);
  }
 
    /**
     * Returns the acceptableFocusSort for the focus.
     * 
     * @return the String representing the acceptableFocusSort.
     */
    public String acceptableFocusSort(){
      Father vFather;
      String vResult="";
      assert fFocus!=null : String.format("CTreeEngine.acceptableFocusSort.pre failed; focus = null.");
      if(fFocus==fRoot){
          if(isHole(fFocus))
              return fRootSortName;
          else return ancestor();
          
      }else{
          
          vFather=findFather(fFocus);
          assert vFather.fFather!=null : String.format("CTreeEngine.acceptableFocusSort.pre failed; father = null.");
          switch(vFather.fFather.kind()){
              case TUPLE:
                  vResult=memberSort(vFather.fFather.sortLabel(),vFather.fIndex);
                  break;
              case LIST:
                  assert vFather.fFather.kind()==CNodeKind.LIST:String.format("CTreeEngine.scceptableFocusSort failed; " +
              "Father of Focus does not support INodeList. " +
              " FFocus.SortLabel = %s, vFather.SortLabel = %s",
              fFocus.sortLabel(), vFather.fFather.sortLabel());
                  vResult=((INodeList)vFather.fFather).eltSortLabel();
                  break;
              case OPTION:
                  
               assert vFather.fFather.kind()==CNodeKind.OPTION:String.format("CTreeEngine.scceptableFocusSort failed; " +
              "Father of Focus does not support INodeOption. " +
              " FFocus.SortLabel = %s, vFather.SortLabel = %s",
              fFocus.sortLabel(), vFather.fFather.sortLabel());
                  vResult=((INodeOption)vFather.fFather).eltSortLabel();
                  break;
          }
          
          return vResult;
      }
    
  }

    /**
     * Returns the Father of a given node.
     * 
     * @param aNode the node whose Father to find.
     * @return the Father.
     */
    public Father findFather(CNode aNode){
     CNode vFather;
     int j;
     int i;
     if(fRootPath.size()>=2){
         vFather=(fRootPath.get(fRootPath.size()-2));
         j=-1;
         for(i=0;i<vFather.count();i++){
             if(vFather.getNode(i)==aNode){
                j=i;
             }
         }
     }else {
         vFather=null;
         j=-1;
     }
     return new Father(vFather,j);
   
 }

    /**
     *
     * @param aSortName
     * @param j
     * @return
     */
    public String memberSort(String aSortName,int j){
    return fSignatureAnalyzer.memberSort(fSignatureAnalyzer.findSortDef(aSortName), j).getName();
}

    /**
     * 
     * @return
     */
    public String ancestor(){
    
    return fSignatureAnalyzer.ancestor(fSignatureAnalyzer.findSortDef(fFocus.sortLabel())).getName();
}

    /**
     *
     * @return
     */
    public String baseAncestor(){
   return fSignatureAnalyzer.baseAncestor(fSignatureAnalyzer.findSortDef(fFocus.sortLabel())).getName();
}

    /**
     * Determines if a given tree contains the given node.
     * 
     * @param aTree the tree to search for the given node.
     * @param aNode the node to find in the given tree.
     * @return <code>true</code> if the given tree contains the given node, else <code>false</code>.
     */
    public boolean contains(CNode aTree, CNode aNode){
    if(aTree==null || aNode==null){
        return false;
    }else if(aTree==aNode){
        return true;
    }else{
        boolean vResult=false;
        for(int i=0;i<aTree.count();i++){
            if(contains(aTree.getNode(i),aNode)){
                vResult=true;
            }
        }
        return vResult;
    }
 }

    /**
     * Adds a source node to the target. The target node must be a list node, otherwise error.
     * 
     * @param aSource the node to be added to the target.
     * @param aTarget the List node in which to add.
     */
    public void add(CNode aSource, CNode aTarget){
    assert canAdd(aSource,aTarget):
            String.format("CTreeEngine.add.pre failed;"
            + " aSource.sortLabel=%s, aTarget.sortLabel=%s.",
            aSource.sortLabel(),aTarget.sortLabel());
        ((INodeList)aTarget).add(aSource);
 }

    /**
     * 
     * @param aSource
     * @param aTarget
     */
    public void setPresent(CNode aSource, CNode aTarget){
    assert canSetPresent(aSource,aTarget):
            String.format("CTreeEngine.setPresent.pre failed;"
            + " aSource.sortLabel=%s, aTarget.sortLabel=%s.",
            aSource.sortLabel(),aTarget.sortLabel());
        ((INodeOption)aTarget).setPresent(aSource);
 }

    /**
     *
     */
    public void setAbsent(){
    assert canSetAbsent():
            String.format("CTreeEngine.setAbsent.pre failed;");
    Father vFather;
    vFather=findFather(fFocus);
    if(vFather.fFather!=null){
        ((INodeOption)vFather.fFather).setAbsent();
         fFocus=vFather.fFather;
    }
 }
 

    /**
     * Determines if the given node can be set as the root node.
     * 
     * @param aNode the node to set.
     * @return <code>true</code> if <code>aNode</code> can be set as a root node, else <code>false</code>.
     */
    public boolean canSetTree(CNode aNode){
        return atMost(aNode.sortLabel(),fRootSortName);
    }

   
    /**
     * Determines if a focused node can be deleted.
     * 
     * @return <code>true</code> if the focus can be deleted, else <code>false</code>.
     */
    public boolean canDelete(){
    return (!isHole(fFocus) && fFocus.kind()!=CNodeKind.LIST && fFocus!=fRoot);
}

   /**
     * Determines if the root of the tree can be cleared.
     * 
     * @return <code>true</code> if the root can be cleared, else <code>false</code>.
     */
    public boolean canClear(){
    return (fRoot.equals(fFocus) && !isHole(fFocus));
}

    /**
     *
     * @param aSortName
     * @return
     */
    public boolean canExpand(String aSortName){
       return isHole(fFocus) && atMost(aSortName,acceptableFocusSort());
}

    /**
     * Determines if a given node (source) can replace a specified target.
     * 
     * @param aSource the node to replace a target.
     * @param aTarget the node to be replaced.
     * @return <code>true</code> a source can replace a target, else <code>false</code>.
     */
    public boolean canReplace(CNode aSource,CNode aTarget){
    return contains(fRoot,aTarget);
}

    /**
     *
     * @param aFocus
     * @return 
     */
    public boolean canReplace(CNode aFocus){
       return isHole(aFocus);
    }

    /**
     * Checks if a given node can be focused.
     * 
     * @param aNode the node to focus.
     * @return <code>true</code> a can be a focus, else <code>false</code>.
     */
    public boolean canSetFocus(CNode aNode){
    return contains(fRoot,aNode);
} 

    /**
     * Checks if a given node can be added to the target. The target must be a list node.
     * 
     * @param aSource the node to add.
     * @param aTarget the list of nodes.
     * @return <code>true</code> a source can be added to the target, else <code>false</code>.
     */
    public boolean canAdd(CNode aSource, CNode aTarget){
     return (aTarget!=null) 
           && (aTarget.kind()==CNodeKind.LIST)
           && (((INodeList)aTarget).canAdd(aSource));
          
}

    /**
     *
     * @param aSource
     * @param aTarget
     * @return
     */
    public boolean canSetPresent(CNode aSource, CNode aTarget){
     return (aTarget!=null) 
           && (aTarget.kind()==CNodeKind.OPTION)
           && (((INodeOption)aTarget).isPresent()==false);
          
}

    /**
     *
     * @return
     */
    public boolean canSetAbsent(){
    Father vFather;
    vFather=findFather(fFocus);
    //System.out.println(vFather.fFather);
     return (vFather.fFather!=null) 
           && (vFather.fFather.kind()==CNodeKind.OPTION)
           && (((INodeOption)vFather.fFather).isPresent()==true);
          
}

    /**
     *
     * @return
     */
    public boolean canInsertBeforeorAfter(){
   Father vFather;
    
    vFather=findFather(fFocus);
    
    return (vFather.fFather!=null) 
           && (vFather.fFather.kind()==CNodeKind.LIST)
           && (vFather.fFather).count()>0;
 
}

    /**
     *
     * @param aSource
     * @param aTarget
     * @return
     */
    public boolean canInsertBefore(CNode aSource,CNode aTarget){
    Father vFather;
    
    vFather=findFather(aTarget);
    
    return (vFather.fFather!=null) 
           && (vFather.fFather.kind()==CNodeKind.LIST)
           && (((INodeList)vFather.fFather).canInsert(vFather.fIndex, aSource));

}

    /**
     * Determines if the given source node can be inserted after the given target.
     * 
     * @param aSource the node to insert after.
     * @param aTarget the target node.
     * @return <code>true</code> a source can be inserted after the target, else <code>false</code>.
     */
    public boolean canInsertAfter(CNode aSource,CNode aTarget){
    Father vFather;
    
    vFather=findFather(aTarget);
    
    return (vFather.fFather!=null) 
           && (vFather.fFather.kind()==CNodeKind.LIST)
           && (((INodeList)vFather.fFather).canInsert(vFather.fIndex+1, aSource));

}

    /**
     * Determines if the specified node can be removed from a list.
     * 
     * @param aTarget the node to remove.
     * @return <code>true</code> aTarget can be removed from the list, else <code>false</code>.
     */
    public boolean canRemove(CNode aTarget){
    Father vFather;
    
    vFather=findFather(aTarget);
    return (vFather.fFather!=null) 
           && (vFather.fFather.kind()==CNodeKind.LIST)
           && (((INodeList)vFather.fFather).canRemove(aTarget));
         // && (1<=vFather.fFather.count());  //reconsider; this condition
           //enables the last element to be removed from the list.
}

  
    /**
     *
     * @return
     */
    public boolean hasHoles(){
    return holeCount()!=0;
    }


    /**
     *
     * @return
     */
    public int holeCount(){
    return fHoles.size();
}

    /**
     * Returns the ith leaf from the list of leaves.
     * 
     * @param i the index of the leaf to return.
     * @return the leaf with the specified index.
     */
    public CNode leaf(int i){
    assert 0<=i && i<leafCount():
            String.format("CTreeEngine.Leaf.pre failed; i=%d, leafCount = %d",
            i, leafCount());
    return fLeaves.get(i);
}

    /**
     * Returns the number of leaves in the list.
     * 
     * @return the number of leaves.
     */
    public int leafCount(){
    return fLeaves.size();
}

    /**
     * Returns the number of nodes in the root path.
     * 
     * @return number of nodes in the root path.
     */
    public int rootPathCount(){
  return fRootPath.size();
}

    /**
     * Returns the ith node in the root path.
     * 
     * @param i the index of the node to return.
     * @return the node with the specified index.
     */
    public CNode rootPathNode(int i){
    assert 0<=i && i< rootPathCount():
            String.format("CTreeEngine.rootPathNode.pre failed; i=%d, "
            + "rootPathCount = %d",
            i, rootPathCount());
    return fRootPath.get(i);
}

    /**
     * Sets the given tree to the this editor component.
     * 
     * @param aTree the tree to be set.
     */
    public void setTree(CNode aTree){
    assert canSetTree(aTree):
            String.format("CTreeEngine.SetTree.pre failed; aTree.Sortlabel = %s",
            aTree.sortLabel());
    clear();
    fRoot=aTree;
    fRootSortName=fRoot.sortLabel();
    buildLeaves();
    buildHoles();
    setFocus(fRoot);
}

    /**
     *
     * @param aSortName
     * @return
     */
    public boolean isAcceptableSort(String aSortName){
    return atMost(aSortName, acceptableFocusSort());
}

    /**
     * Returns the ith acceptable sort.
     * 
     * @param i the index of the acceptable sort to return.
     * @return the acceptable sort at the specified index.
     */
    public String acceptableSort(int i){
    assert 0<=i && i<acceptableSortCount() :
            String.format("CTreeEngine.acceptableSort.pre failed; "
            + "i=%d, acceptableSortCount = %d",i,leafCount());
    return fAcceptableSorts.get(i);
    
}

    /**
     * Returns the number of acceptable sorts.
     * 
     * @return the number of acceptable sorts in this list.
     */
    public int acceptableSortCount(){
    return fAcceptableSorts.size();
}

    /**
     * Inserts the given source node after the given target.
     * 
     * @param aSource the node to insert after.
     * @param aTarget the target node.
     * 
     */
    public void insertAfter(CNode aSource,CNode aTarget){
    Father vFather;
    assert canInsertAfter(aSource,aTarget):
            String.format("CTreeEngine.insertAfter.pre failed;"
            + " aSource.sortLabel=%s, aTarget.sortLabel=%s.",
            aSource.sortLabel(),aTarget.sortLabel());
    vFather=findFather(aTarget);
    ((INodeList)vFather.fFather).insert(vFather.fIndex+1, aSource);
}

    /**
     * Inserts the given source node before the given target.
     * 
     * @param aSource the node to insert before.
     * @param aTarget the target node.
     * 
     */
    public void insertBefore(CNode aSource,CNode aTarget){
    Father vFather;
    assert canInsertBefore(aSource,aTarget):
            String.format("CTreeEngine.insertBefore.pre failed;"
            + " aSource.sortLabel=%s, aTarget.sortLabel=%s.",
            aSource.sortLabel(),aTarget.sortLabel());
    vFather=findFather(aTarget);
    ((INodeList)vFather.fFather).insert(vFather.fIndex, aSource);
}

    /**
     * Removes the given node from the list.
     * 
     * @param aTarget the node to be removed.
     */
    public void remove(CNode aTarget){
    Father vFather;
    assert canRemove(aTarget):
            String.format("CTreeEngine.remove.pre failed;"
            + " aTarget.sortLabel=%s.",
            aTarget.sortLabel());
    vFather=findFather(aTarget);
    ((INodeList)vFather.fFather).remove(aTarget);
   
    if(((INodeList)vFather.fFather).count()==vFather.fIndex){
        vFather.fIndex=(vFather.fIndex)-1;
    }
    if(((INodeList)vFather.fFather).count()==0){ // added to be able to remove the last list element
        setFocus(vFather.fFather);
    }else{
     setFocus(vFather.fFather.getNode(vFather.fIndex));
    }
}
    /**
     * Replaces the given <code>aTarget</code> with the given <code>aSource</code>.
     * 
     * @param aSource the node to replace.
     * @param aTarget the node to be replaced.
     */
    public void replace(CNode aSource,CNode aTarget){
     Father vFather;
     assert canReplace(aSource,aTarget):"CTreeEngine.replace.pre failed";
     
     if(fRoot==aTarget){
         fRoot=aSource; //Do not free aTarget; there may be other references
     }else{
         vFather=findFather(aTarget);
         assert vFather.fFather!=null:"CTreeEngine.replace: father of aTarget not found";
         (vFather.fFather).setNode(vFather.fIndex, aSource);  //do not free aTarget
     }
 }
}
