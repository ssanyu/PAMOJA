/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.TreeBuilder;


import Components.CPAMOJAComponent;
import Nodes.CNode;
import java.util.ArrayList;

/**
 * An abstract base class for all TreeBuilder components.
 * 
 * @author jssanyu
 */
public abstract class CTreeBuilderComp extends CPAMOJAComponent implements ITreeBuilderComp{
    
    
    @Override
    public abstract CNode mkEps(String aRoot,String aPath);

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aName
     * @param aValue
     * @return
     */
    @Override
    public abstract CNode mkTermData(String aRoot,String aPath, String aName, String aValue); 

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aName
     * @return
     */
    @Override
    public abstract CNode mkTerm(String aRoot,String aPath,String aName); 

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aName
     * @param aBody
     * @return
     */
    @Override
    public abstract CNode mkProd(String aRoot,String aPath,String aName, CNode aBody); 

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    @Override
    public abstract CNode mkMultiDot(String aRoot,String aPath,ArrayList<CNode> aArgs); 

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aIndex
     * @param aArg
     * @return
     */
    @Override
    public abstract CNode mkMultiStick(String aRoot,String aPath,int aIndex, CNode aArg);

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aPresent
     * @param aArg
     * @return
     */
    @Override
    public abstract CNode mkOption(String aRoot,String aPath,boolean aPresent, CNode aArg);

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    @Override
    public abstract CNode mkStar(String aRoot,String aPath, ArrayList<CNode> aArgs);

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    @Override
    public abstract CNode mkPlus(String aRoot,String aPath, ArrayList<CNode> aArgs);
    @Override
    public abstract CNode mkTuple(String aRoot,String aPath,ArrayList<CNode> aChilds,ArrayList<String> aData);   

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    @Override
    public abstract CNode mkAlt(String aRoot, String aPath,ArrayList<CNode> aArgs);
   
}
