/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;


import Components.Concrete.TreeBuilder.ITreeBuilderComp;
import Nodes.CNode;
import java.util.ArrayList;


/**
 * {@code CParseExt} extends {@code CParser} with tree building functions.
 * @see CParser 
 * 
 * @author jssanyu
 */
public  abstract class CParserExt extends CParser{

    /**
     *
     */
    protected boolean TreeBuilding;

    /**
     *
     */
    protected boolean UseLookAhead;

    /**
     *
     */
    protected ITreeBuilderComp TreeBuilder=null; //Reference to Tree builder
       
    /**
     *
     */
    public CParserExt(){
       super();
   }
   
    /**
     *
     * @return
     */
    public boolean isTreeBuilding(){
       return TreeBuilding;
   }

    /**
     *
     * @param aValue
     */
    public void setTreeBuilding(boolean aValue){
        TreeBuilding=aValue;
   }
   
    /**
     *
     * @return
     */
    public boolean isUseLookAhead(){
        return UseLookAhead;
   }

    /**
     *
     * @param aValue
     */
    public void setUseLookAhead(boolean aValue){
        UseLookAhead=aValue;
        
   }
   
    /**
     *
     * @param aTreeBuilder
     */
    public void setTreebuilder(ITreeBuilderComp aTreeBuilder){
       TreeBuilder=aTreeBuilder;
   }
    
   //Tree Builder helpers

    /**
     *
     * @param aRoot
     * @param aPath
     * @return
     */
       public CNode bdEps(String aRoot,String aPath){
        if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkEps(aRoot,aPath);
        else return null;
   }
    
    /**
     *
     * @param aRoot
     * @param aPath
     * @param aName
     * @param aValue
     * @return
     */
    public CNode bdTermData(String aRoot,String aPath, String aName, String aValue){
        if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkTermData(aRoot,aPath,aName,aValue);
        else return null;
    }
   
    /**
     *
     * @param aRoot
     * @param aPath
     * @param aName
     * @return
     */
    public CNode bdTerm(String aRoot,String aPath,String aName){
        if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkTerm(aRoot,aPath, aName);
        else return null;
    }
    
    /**
     *
     * @param aRoot
     * @param aPath
     * @param aName
     * @param aBody
     * @return
     */
    public CNode bdNonTerm(String aRoot,String aPath, String aName, CNode aBody){
         if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkProd(aRoot,aPath, aName, aBody);
         else return null;
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    public CNode bdMultiDot(String aRoot,String aPath,ArrayList<CNode> aArgs){
        if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkMultiDot(aRoot,aPath,aArgs);
        else return null;
    } 

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aIndex
     * @param aArg
     * @return
     */
    public CNode bdMultiStick(String aRoot,String aPath,int aIndex, CNode aArg){
        if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkMultiStick(aRoot,aPath,aIndex, aArg);
        else return null;
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    public CNode bdStar(String aRoot,String aPath,ArrayList<CNode> aArgs){
        if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkStar(aRoot,aPath,aArgs);
        else return null;
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    public CNode bdPlus(String aRoot,String aPath,ArrayList<CNode> aArgs){
        if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkPlus(aRoot,aPath,aArgs);
        else return null;
    }
    
    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @param aData
     * @return
     */
    public CNode bdTuple(String aRoot,String aPath,ArrayList<CNode> aArgs,ArrayList<String> aData){
        if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkTuple(aRoot,aPath, aArgs, aData);
        else return null;
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aPresent
     * @param aArg
     * @return
     */
    public CNode bdOption(String aRoot,String aPath,boolean aPresent, CNode aArg){
        if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkOption(aRoot,aPath, aPresent, aArg);
        else return null;
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    public CNode bdAlt(String aRoot, String aPath, ArrayList<CNode> aArgs){
        if(TreeBuilding && TreeBuilder!=null)
            return TreeBuilder.mkAlt(aRoot,aPath,aArgs);
        else 
            return null;
    }
   
}
