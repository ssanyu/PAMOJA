/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.TreeBuilder;



import GrammarNotions.ECFGNodes.CAltNode;
import GrammarNotions.ECFGNodes.CECFGNode;
import GrammarNotions.ECFGNodes.CEpsNode;
import GrammarNotions.ECFGNodes.CMultiDotNode;
import GrammarNotions.ECFGNodes.CMultiStickNode;
import GrammarNotions.ECFGNodes.CNonTermNode;
import GrammarNotions.ECFGNodes.COptionNode;
import GrammarNotions.ECFGNodes.CPlusNode;
import GrammarNotions.ECFGNodes.CStarNode;
import GrammarNotions.ECFGNodes.CTermNode;
import GrammarNotions.ECFGNodes.CTermValueNode;
import GrammarNotions.ECFGNodes.CTupleNode;
import Nodes.CNode;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * An implementation of a tree builder for constructing parse trees.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CECFGTreeBuilderComp extends CTreeBuilderComp{
    
    /**
     *
     */
    protected boolean MultiStick;

    /**
     *
     */
    protected boolean NonTerminal;

    /**
     *
     */
    protected boolean NoDataTerminal;

     

    /**
     * Downcasts a value of class ArrayList of CNode to a value of class ArrayList of ECFGNode. 
     * In general, this is not correct. Solved here by creating a new list
     * and copying each element with a checked downcast.
     * 
     * @param aList a list of values of type CNode to downcast.
     * @return a list of values of type CECFGNode.
     */
        protected ArrayList<CECFGNode> downcastArrayList(ArrayList<CNode> aList){
        ArrayList<CECFGNode> vList = new ArrayList<>();
        for(int i = 0; i < aList.size(); i++){
            CNode vNode = aList.get(i);
            if (vNode == null){
                vList.add(null);
            }else{
                assert vNode instanceof CECFGNode:
                "Error in CECFGTreeBuilder.downcastArrayList: " +
                "i == " + Integer.toString(i) +
                "aList.get(i).getClass().getName == " +
                aList.get(i).getClass().getName();
                vList.add( (CECFGNode)vNode );
            }
        }
        return (ArrayList<CECFGNode>) vList;
    }
    
    /**
     * Creates an instance of a ECFGTreeBuilder component.
     */
    public CECFGTreeBuilderComp(){
        super();
    }

     @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("ParseTreeBuilder",5, 10);
    }
    
    
    public boolean isMultiStick(){
        return MultiStick;
        
    }
    
    
    public void setMultiStick(boolean aMultiStick){
        MultiStick=aMultiStick;
    }

   
    public boolean isNonTerminal(){
        return NonTerminal;
        
    }
    
   
    public void setNonTerminal(boolean aNonTerminal){
        NonTerminal=aNonTerminal;
    }

   
    public boolean isNoDataTerminal(){
        return NoDataTerminal;
        
    }
    
   
    public void setNoDataTerminal(boolean aNoDataTerminal){
        NoDataTerminal=aNoDataTerminal;
    }
    
    @Override
    public CNode mkEps(String aRoot,String aPath) {
        return new CEpsNode(aRoot, aPath);
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aName
     * @param aValue
     * @return
     */
    @Override
    public CNode mkTermData(String aRoot,String aPath, String aName, String aValue) {
        return new CTermValueNode(aRoot, aPath, aName, aValue);
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aName
     * @return
     */
    @Override
    public CNode mkTerm(String aRoot,String aPath, String aName) {
        if(isNoDataTerminal())
           return new CTermNode(aRoot, aPath, aName);
        else return null;
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    @Override
    public CNode mkMultiDot(String aRoot,String aPath, ArrayList<CNode> aArgs) {
        return new CMultiDotNode(aRoot, aPath, downcastArrayList(aArgs) );
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aIndex
     * @param aArg
     * @return
     */
    @Override
    public CNode mkMultiStick(String aRoot,String aPath, int aIndex, CNode aArg) {
       if(isMultiStick())
           return new CMultiStickNode(aRoot, aPath, aIndex, (CECFGNode)aArg);
       else return aArg;
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    @Override
    public CNode mkStar(String aRoot,String aPath, ArrayList<CNode> aArgs) {
       return new CStarNode(aRoot, aPath, downcastArrayList(aArgs) );
    }

    @Override
    public CNode mkTuple(String aRoot,String aPath, ArrayList<CNode> aArgs, ArrayList<String> aData) {
       return new CTupleNode(aRoot, aPath, downcastArrayList(aArgs), aData);
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aName
     * @param aBody
     * @return
     */
    @Override
    public CNode mkProd(String aRoot,String aPath,String aName, CNode aBody) {
        if(isNonTerminal())
            return new CNonTermNode(aRoot, aPath, aName, (CECFGNode)aBody);
        else return aBody;
    }
 
    /**
     *
     * @param aRoot
     * @param aPath
     * @param aPresent
     * @param aArg
     * @return
     */
    @Override
    public CNode mkOption(String aRoot, String aPath, boolean aPresent, CNode aArg) {
       return new COptionNode(aRoot, aPath, aPresent, (CECFGNode)aArg);
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    @Override
    public CNode mkPlus(String aRoot, String aPath, ArrayList<CNode> aArgs) {
        return new CPlusNode(aRoot, aPath, downcastArrayList(aArgs) );
    }

    /**
     *
     * @param aRoot
     * @param aPath
     * @param aArgs
     * @return
     */
    @Override
    public CNode mkAlt(String aRoot, String aPath,ArrayList<CNode> aArgs){
        return new CAltNode( aRoot,  aPath, downcastArrayList(aArgs));
    }
    
    
    
}
