/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.Abstraction;


import Abstractor.CAbstraction;
import Components.CPAMOJAComponent;
import Components.Concrete.ParseTree.IParseTreeComp;
import Components.Concrete.Parser.IParserComp;
import Components.INodeObject;
import GrammarNotions.ECFGNodes.CECFGNode;
import Nodes.CNode;
import Nodes.CNodeFactory;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Abstraction component holds a mapping from parse tree to AST.
 * <p>
 * It makes use of a node factory tool to generate the different kinds of language-specific nodes
 * of an AST. It observes parser components or ParseTree components and maintains consistency between its parse tree and the parse tree of
 * these components. When it receives a property change in the parse tree, it rebuilds its AST making use of a node factory tool and notifies its observers (e.g., the AST component).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CAbstractionComp extends CPAMOJAComponent implements IAbstractionComp,INodeObject,PropertyChangeListener {
    /**
     * The Abstractor object.
     */
    private CAbstraction Abstractor;
    
    /**
     * A reference to <code>ParseTree</code> object.
     */
    private IParseTreeComp ParseTree=null;
    
    /**
     * The NodeFactory object.
     */
    private CNodeFactory NodeFactory=null;
    /**
     * The AST object.
     */
    private CNode AST=null;
    
     /**
      * A reference to a <code>Parser</code> object.
      */
    private IParserComp Parser=null;

     /**
      * Creates a new instance of <code>CAbstractionComp</code>.
      */
    public CAbstractionComp() {
         super();
         Abstractor=new CAbstraction();
         AST=null;
    }

   /**
     * Paints the UI of the Abstractor component object. 
     * 
     * @param g the Abstractor component object to paint.
     */
   @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("Abstractor",10, 10);
    }
    
 
   /**
     * Accesses the interface of the ParseTree component. 
     *
     * @return the ParseTree component's interface.
     */
    @Override
    public IParseTreeComp getParseTree() {
        return ParseTree;
    }

    
    /**
     * Connects to the ParseTree component via its interface. 
     * Registers for property change events, retrieves the parse tree object and rebuilds its AST.
     *
     * @param aParseTree new value of <code>ParseTree</code>
     */
    @Override
    public void setParseTree(IParseTreeComp aParseTree) {
        CNode vParseTreeStructure;
        if(ParseTree!=null){
              ParseTree.removePropertyChangeListener(this);
       }
       ParseTree=aParseTree;
       if(ParseTree!=null){
              ParseTree.addPropertyChangeListener(this); 
              vParseTreeStructure=ParseTree.getParseTreeStructure();
              
       }else{
           vParseTreeStructure=null;
       }
       
       updateAbstraction(vParseTreeStructure);
      
    }
    
    
    /**
     * Access the interface of a Parser component.
     *
     * @return the Parser interface.
     */
    @Override
    public IParserComp getParser() {
        return Parser;
    }
    /**
     * Connect to a parser component via its interface.
     * Register for property change events, retrieve current value of parse tree object and rebuild the AST.
     *
     * @param aParser the parser interface.
     */
    @Override
    public void setParser(IParserComp aParser) {
         CNode vParseTreeStructure;
        if(Parser!=null){
              Parser.removePropertyChangeListener(this);
       }
       Parser=aParser;
       if(Parser!=null){
              Parser.addPropertyChangeListener(this);
              vParseTreeStructure=(CECFGNode)Parser.getParserResult().getNode();
       }else{
           vParseTreeStructure=null;
       }
       updateAbstraction(vParseTreeStructure);
      
       
    }


    /**
     * Return the value of AST
     *
     * @return the value of AST
     */
    @Override
    public CNode getAST() {
        return AST;
    }

    /**
     * Set the value of AST
     *
     * @param AST new value of AST
     */
    @Override
    public void setAST(CNode AST) {
        this.AST = AST;
    }


    /**
     * Get the NodeFactory object.
     *
     * @return the value of NodeFactory
     */
    public CNodeFactory getNodeFactory() {
        return NodeFactory;
    }

    /**
     * Set the NodeFactory object for creating AST nodes.
     *
     * @param aNodeFactory the CNodefactory object. 
     */
    @Override
    public void setNodeFactory(CNodeFactory aNodeFactory) {
         CAbstraction vAbstractor=new CAbstraction();
         AST=null;
         NodeFactory = aNodeFactory;
       if(NodeFactory!=null && ParseTree!=null&&ParseTree.getParseTreeStructure()!=null){
            vAbstractor.setNodeFactory(NodeFactory);
            vAbstractor.setParseTree(ParseTree.getParseTreeStructure());
            CNode vNode=vAbstractor.transformParseTree(ParseTree.getParseTreeStructure());
            vAbstractor.setTransformedParseTree(vNode);
            
            AST=vAbstractor.abstr((CECFGNode)vNode.getNode(0));
            vAbstractor.setAST(AST);
            setAbstractor(vAbstractor);
        }
       
      }

   /**
     * Build an AST from the given parse tree.
     * 
     * 
     * @param aParseTreeStructure the new value of a parse tree.
     */
    private void updateAbstraction(CNode aParseTreeStructure){
        CAbstraction vAbstraction=new CAbstraction();
        AST=null;
        vAbstraction.setParseTree(aParseTreeStructure);
        vAbstraction.setNodeFactory(NodeFactory);
        if(NodeFactory!=null && aParseTreeStructure!=null){
            CNode vNode=vAbstraction.transformParseTree(aParseTreeStructure);
            vAbstraction.setTransformedParseTree(vNode);
           AST=vAbstraction.abstr((CECFGNode)vNode.getNode(0));
           vAbstraction.setAST(AST);
           setAbstractor(vAbstraction);
        }
    }
   
    /**
     * Get the value of Abstractor
     *
     * @return the value of Abstractor
     */
    public CAbstraction getAbstractor() {
        return Abstractor;
    }

    /**
     * Set the value of Abstractor and notify observers about <code>Abstractor</code> property changes.
     *
     * @param aAbstractor the CAbstraction object.
     */
    public void setAbstractor(CAbstraction aAbstractor) {
        
         // keep the old value
        CAbstraction oldAbstractor=Abstractor;
        // save the new value
        Abstractor=aAbstractor;
        // fire the property change event, with a property that has changed
        support.firePropertyChange("Abstractor",oldAbstractor,Abstractor);
    }

    /**
     * Returns current value of AST.
     * 
     * @return the AST object.
     */
    @Override
    public CNode getNode() {
        return AST;
    }
    
    /**
     * Handles property change events from Parser and ParseTree components. If the property change is from a Parser component, retrieve the parse tree object of the parser, rebuild the AST and notify observers. 
     * Else, If the property change is from a Parsetree component, retrieve current parse tree, 
     * rebuild the AST and notify observers.
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
         CNode vNode=null;
        if(source==Parser)
            vNode=Parser.getParserResult().getNode();
        else if(source==ParseTree)
            vNode=ParseTree.getParseTreeStructure();
        updateAbstraction(vNode);
    }
    
}
