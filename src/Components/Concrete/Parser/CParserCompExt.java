package Components.Concrete.Parser;


import Components.Concrete.TreeBuilder.ITreeBuilderComp;
import GrammarNotions.ECFGNodes.CECFGNode;
import Nodes.CNode;
import java.util.ArrayList;

/**
 * This class extends abstract base class <code>CParserComp</code> with tree building services. Both interpreting parsers and table-driven parser
 * components descend from this class.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */

public  abstract class CParserCompExt extends CParserComp implements IParserCompExt{

    /**
     * A parse tree object.
     */
    protected CECFGNode ParseTree;
    /**
     * A reference to Tree builder object.
     */
    protected ITreeBuilderComp TreeBuilder=null; 

    /**
     *
     */
    protected ArrayList<CNode> NodeStack; //Stack of trees 
    
    /**
    * An option to indicate whether a parser should be lookahead or not.
    */
    protected boolean UseLookAhead;
  /**
    * An option to indicate whether a parser should construct a parse tree or not.
    */
    protected boolean TreeBuilding;
   
    /**
     * Creates a new instance of <code>CParserCompExt</code>.
     */   
    public CParserCompExt(){
        super();
        NodeStack=new ArrayList<>();
    }
     /**
     * Get the value of TreeBuilder
     *
     * @return the value of TreeBuilder
     */
    public ITreeBuilderComp getTreeBuilder() {
        return TreeBuilder;
    }

    /**
     * Connects to the specified TreeBuilder component.
     * @param aTreeBuilder the TreeBuilder to connect.
     */
    public void setTreeBuilder(ITreeBuilderComp aTreeBuilder) {
       if(TreeBuilder!=null){
              TreeBuilder.removePropertyChangeListener(this);
       }
       TreeBuilder=aTreeBuilder;
       if(TreeBuilder!=null){
              TreeBuilder.addPropertyChangeListener(this);
       }
    }
    
    /**
     *
     * @return
     */
    public ArrayList<CNode> getNodeStack() {
        return NodeStack;
    }

    /**
     *
     * @param NodeStack
     */
    public void setNodeStack(ArrayList<CNode> NodeStack) {
        this.NodeStack =new ArrayList();
        this.NodeStack.addAll(NodeStack);
    }

     /**
     * Determines if the Parser component is in tree building mode.
     * 
     * @return <code>true</code> if the parser is in tree building mode, otherwise <code>false</code>.
     */
        public boolean isTreeBuilding(){
        return TreeBuilding;
    }

    /**
     * Sets the tree building mode of this Parser to the specified value.
     * @param aValue if tree building mode is allowed for this parser then <code>true</code>, else <code>false</code>.
     */
    public void setTreeBuilding(boolean aValue){
        TreeBuilding=aValue;
    }

   /**
     * Determines if this Parser component is using lookahead..
     * 
     * @return <code>true</code> if the parser is using lookahead, otherwise <code>false</code>.
     */
    public boolean isUseLookAhead(){
        return UseLookAhead;
    }

    /**
     * Sets the lookahead mode of this Parser to the specified value.
     * @param aValue if lookahead mode is allowed for this parser then <code>true</code>, else <code>false</code>.
     */
    public void setUseLookAhead(boolean aValue){
        UseLookAhead=aValue;
    }
    
    /**
     * Returns a parse tree. This an abstract method to be implemented by the descendants.
     * @return the ECFG parse tree.
     */
    public abstract CECFGNode getParseTree();
    
    /**
     * Sets the specified parse tree to this parser component.
     * This an abstract method to be implemented by the descendants.
     * @param aParseTree the parse tree to set.
     */
    public abstract void setParseTree(CECFGNode aParseTree);
}