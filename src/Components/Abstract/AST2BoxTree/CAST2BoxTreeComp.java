/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.AST2BoxTree;

import Boxes.*;
import Components.Abstract.AST.IASTComp;
import Components.Abstract.Patterns.IPatternsComp;
import Components.CPAMOJAComponent;
import Components.INodeObject;
import Nodes.CNode;
import Nodes.CNodeKind;
import Nodes.INodeOption;
import Patterns.CPatterns;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A component which holds a mapping from AST to Box tree according to some pattern definitions. 
 * <p>
 * The AST2BoxTree component observes the AST and Patterns components. 
 * When it receives a property change in any of the components, it reconstructs the box tree and notifies its observers.
 *
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CAST2BoxTreeComp extends CPAMOJAComponent implements IAST2BoxTreeComp,INodeObject,PropertyChangeListener{

    /**
     * A reference to <code>Patterns</code> component.
     */
    private IPatternsComp Patterns=null;
    /**
     * A reference to <code>AST</code> component.
     */
    private IASTComp AST=null;
    
       
    /**
     * The pattern definitions.
     */
    private CPatterns PatternDefns;
    /**
     * The AST.
     */
    private CNode ASTNode=null;
   
    /**
     * The box tree.
     */
    private CBox BoxTree=null;
   
    
   

    /**
     * Creates a new instance of <code>CASTtoBoxTreeComp</code>.
     */
    public CAST2BoxTreeComp(){
        super();
        PatternDefns=new CPatterns();
        
    }
    /**
     * Paints the UI of the AST2BoxTree component object. 
     * 
     * @param g the AST2BoxTree component object to paint.
     */
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("AST2Box",10, 10);
    }

   

    /**
     * Get the value of BoxTree
     *
     * @return the value of BoxTree
    */
    @Override
    public CBox getBoxTree() {
        return BoxTree;
    }

    /**
     * Set the value of BoxTree and notify observers about <code>BoxTree</code> property changes.
     *
     * @param aBoxTree new value of BoxTree
     */
    @Override
    public void setBoxTree(CBox aBoxTree){
        // keep the old value
        CBox oldBoxTree=BoxTree;
        // save the new value
        BoxTree=aBoxTree;
        
        // fire the property change event, with a property that has changed
        support.firePropertyChange("BoxTree",oldBoxTree,BoxTree);
    }


    /**
     * Get the value of ASTNode
     *
     * @return the value of ASTNode
     */
    public CNode getASTNode() {
        return ASTNode;
    }

    /**
     * Set the value of ASTNode
     *
     * @param aASTNode new value of ASTNode
     */
    public void setASTNode(CNode aASTNode) {
        ASTNode = aASTNode;
        CBox box=createBoxTree(ASTNode);
        setBoxTree(box);
        
    }

   

    /**
     * Get the value of PatternDefns
     *
     * @return the value of PatternDefns
     */
    public CPatterns getPatternDefns() {
        return PatternDefns;
    }

    /**
     * Set the value of PatternDefns
     *
     * @param aPatternDefns new value of PatternDefns
     */
    public void setPatternDefns(CPatterns aPatternDefns) {
       PatternDefns = aPatternDefns;
       CBox box=createBoxTree(ASTNode);
       setBoxTree(box);
    }


    /**
     * Accesses the interface of the AST component connected to this AST2BoxTree component. 
     *
     * @return the value of AST
     */
    @Override
    public IASTComp getAST() {
        return AST;
    }
    
    /**
     * Connects to the AST component via its interface.  
     * Registers for property change events, retrieves current value of AST object and regenerates the box tree.
     *
     * @param aAST new AST component's interface.
     */
    @Override
    public void setAST(IASTComp aAST) {
       CNode vASTStructure;
      
       if(AST!=null){
              AST.removePropertyChangeListener(this);
       }
       AST=aAST;
       if(AST!=null){
          AST.addPropertyChangeListener(this);
           vASTStructure=AST.getASTStructure();
           
       }else{
            vASTStructure=null;
            
       }
       setASTNode(vASTStructure);
       
    }
    /**
     * Returns a box tree from a given AST node.
     * 
     * @param aNode the AST used in the construction of a box tree.
     * @return the box tree corresponding to the given AST node.
     */
    public CBox createBoxTree(CNode aNode){
         CBox vBoxtree=null;
         if(Patterns!=null && aNode!=null && PatternDefns.getfPatternDefs()!=null){
            vBoxtree=format(aNode);
        }
        return vBoxtree;
        
    }
    /**
     * Accesses the interface of a Patterns object connected to this AST2BoxTree component.
     *
     * @return the interface of a Patterns object.
     */
    @Override
    public IPatternsComp getPatterns() {
        return Patterns;
    }

    /**
     * Connects to the Patterns component via its interface.
     * Registers for property change events, retrieves current object of the patterns internal structure and regenerates the box tree.
     *
     * @param aPatterns the Patterns component interface.
     */
    @Override
    public void setPatterns(IPatternsComp aPatterns) {
        CPatterns vPatternsStructure;
       if(Patterns!=null){
              Patterns.removePropertyChangeListener(this);
       }
       Patterns=aPatterns;
       if(Patterns!=null){
          Patterns.addPropertyChangeListener(this);
           vPatternsStructure=Patterns.getPatternsStructure();
       }else{
           vPatternsStructure=null;
       }
       setPatternDefns(vPatternsStructure);
      }
    
    /**
     * Returns a box tree from a given AST node.
     * 
     * @param aNode the AST node
     * @return the box tree object
     */
    @Override
    public CBox format(CNode aNode ){
        return ASTTreeToBoxTree(getPattern(aNode),aNode);
    }
    /**
     * Recursively converts the given AST node to a box tree according to the given pattern definitions.
     * 
     * @param aPattern the pattern definitions.
     * @param aNode the AST node to convert.
     * @return the box tree obtained.
     */
    protected CBox ASTTreeToBoxTree(CBox aPattern,CNode aNode){
        CBox_List vList;
        CBox_List vRow;
        int vIndex;
        if(aPattern!=null && aNode!=null){
           switch(aPattern.sortCode()){
            case CBoxesSortCodes.scSelBox: 
                CSelBox aSelBox=(CSelBox)aPattern;
                CBox vResult=new CSelBox(ASTTreeToBoxTree(aSelBox.getarg(),aNode));
                vResult.setNode(aNode);
                return vResult;
            case CBoxesSortCodes.scIndBox: 
                CIndBox aIndBox=(CIndBox)aPattern;
                return new CIndBox(ASTTreeToBoxTree(aIndBox.getarg(),aNode));
               
            case CBoxesSortCodes.scHorBox: 
                 CHorBox aHorBox=(CHorBox)aPattern;
                 vList=new CBox_List();
                 
                 for(int i=0;i<aHorBox.getlist().termCount();i++){
                     
                     vList.addTerm(ASTTreeToBoxTree(aHorBox.getlist().getElt(i),aNode));
                                        
                 }
                 return new CHorBox(vList,aHorBox.gethSpace());
                
            case CBoxesSortCodes.scVerBox: 
                 CVerBox aVerBox=(CVerBox)aPattern;
                 vList=new CBox_List();
                 for(int i=0;i<aVerBox.getlist().termCount();i++){
                     vList.addTerm(ASTTreeToBoxTree(aVerBox.getlist().getElt(i),aNode));
                 }
                 return new CVerBox(vList,aVerBox.getvSpace());
                
            case CBoxesSortCodes.scFlowListBox: 
                 CFlowListBox aFlowListBox=(CFlowListBox)aPattern;
                 vList=new CBox_List();
                 for(int i=0;i<aFlowListBox.getlist().termCount();i++){
                     vList.addTerm(ASTTreeToBoxTree(aFlowListBox.getlist().getElt(i),aNode));
                 }
                 return new CFlowListBox(vList,aFlowListBox.gethSpace(),aFlowListBox.getvSpace());
               
            case CBoxesSortCodes.scTermBox: 
                CTermBox aTermBox=(CTermBox)aPattern;
                return new CTermBox(aTermBox.getsymName()); 
            
            
                
            case CBoxesSortCodes.scTermDataBox: 
                 CTermDataBox aTermDataBox=(CTermDataBox)aPattern;
                 return new CTermDataBox(aTermDataBox.getsymName(),aTermDataBox.getdata()); 
                

            case CBoxesSortCodes.scArgNodeBox: 
                CArgNodeBox aArgNodeBox=(CArgNodeBox)aPattern;
                vIndex=aArgNodeBox.getindex();
                if((0<=vIndex) && (vIndex<aNode.count())){
                    if(aNode.getNode(vIndex).kind()==CNodeKind.OPTION ){
                      if(!((INodeOption)aNode.getNode(vIndex)).isPresent()){
                         vResult=new CSelBox(new CTermBox(""));
                         vResult.setNode(aNode.getNode(vIndex));
                          return vResult;
                      } else {
                          //System.out.println(aNode);
                          return format(aNode.getNode(vIndex));
                          
                        } 
                    }else{
                        return format(aNode.getNode(vIndex));
                    }
                }  
                break;
            case CBoxesSortCodes.scArgDataBox: 
                CArgDataBox aArgDataBox=(CArgDataBox)aPattern;
                vIndex=aArgDataBox.getindex();
                if((0<=vIndex) && (vIndex<aNode.dataCount())){
                    return new CTermDataBox(aArgDataBox.getsymName(),aNode.getData(vIndex));
                }  
                break;
            case CBoxesSortCodes.scHorSepListBox: 
                CHorSepListBox aHorSepListBox=(CHorSepListBox)aPattern;
                vList=new CBox_List();
                
                vList.addTerm(new CTermBox(aHorSepListBox.getopen().getsymName()));
               
                if(aNode.count()!=0){
                    vList.addTerm(format(aNode.getNode(0)));
                    for(int i=1;i<aNode.count();i++){
                        vList.addTerm(new CTermBox(aHorSepListBox.getsep().getsymName())); 
                        vList.addTerm(format(aNode.getNode(i)));
                    }
                }
                vList.addTerm(new CTermBox(aHorSepListBox.getclose().getsymName())); 
                return new CHorBox(vList,1);
            /* How do we determine the location of the separator? i.e whether it should be
             * on the rhs or lhs.
             */
            
            case CBoxesSortCodes.scVerSepListBox: 
               CVerSepListBox aVerSepListBox=(CVerSepListBox)aPattern;
               vList=new CBox_List();
               
               if(aNode.count()==0){
                   vList.addTerm(new CTermBox(aVerSepListBox.getopen().getsymName()));
                   vList.addTerm(new CTermBox(aVerSepListBox.getclose().getsymName()));
               }else{
                   //create first row from open and element 0
                   vRow=new CBox_List();
                   
                   vRow.addTerm(new CTermBox(aVerSepListBox.getopen().getsymName())); 
                   vRow.addTerm(format(aNode.getNode(0)));
                   vList.addTerm(new CHorBox(vRow,1));
                   
                   // add remaining rows from separator and element i
                   for(int i=1;i<aNode.count();i++){
                       vRow=new CBox_List();
                       vRow.addTerm(new CTermBox(aVerSepListBox.getsep().getsymName()));
                       vRow.addTerm(format(aNode.getNode(i)));
                        vList.addTerm(new CHorBox(vRow,1));
                   }
                  // add row with close if nonempty
                   if(aVerSepListBox.getclose().getsymName().length()!=0){ // Needs reconsideration
                       vList.addTerm(new CTermBox(aVerSepListBox.getclose().getsymName()));
                   }
               }
                return new CVerBox(vList,0);
    
                
                
                
         /*    RHS separator
               case CBoxesSortCodes.scVerSepListBox: 
               CVerSepListBox aVerSepListBox=(CVerSepListBox)aPattern;
               vList=new CBox_List();
               
               if(aNode.count()==0){
                   vList.addTerm(new CTermBox(aVerSepListBox.getopen().getsymName()));
                   vList.addTerm(new CTermBox(aVerSepListBox.getclose().getsymName()));
                  // System.out.println(aVerSepListBox.getopen().getsymName()+"=="+aVerSepListBox.getclose().getsymName());
               }else if(aNode.count()==1){
                  vRow=new CBox_List();
                  vRow.addTerm(new CTermBox(aVerSepListBox.getopen().getsymName()));
                  vRow.addTerm(format(aNode.getNode(0)));
                  vRow.addTerm(new CTermBox(aVerSepListBox.getclose().getsymName()));
                  vList.addTerm(new CHorBox(vRow,0));
               }else{
                   //create first row from open and element 0
                   vRow=new CBox_List();
                   vRow.addTerm(new CTermBox(aVerSepListBox.getopen().getsymName())); 
                   vRow.addTerm(format(aNode.getNode(0)));
                   vRow.addTerm(new CTermBox(aVerSepListBox.getsep().getsymName()));
                   //vList.addTerm(new CHorBox(vRow,0)); //vList.addTerm(new CHorBox(vRow,1));
                   vList.addTerm(new CHorBox(vRow,0));
                   // add remaining rows from element i and separator
                   for(int i=1;i<aNode.count()-1;i++){
                       vRow=new CBox_List();
                       vRow.addTerm(format(aNode.getNode(i)));
                       vRow.addTerm(new CTermBox(aVerSepListBox.getsep().getsymName()));
                       vList.addTerm(new CHorBox(vRow,0)); //vList.addTerm(new CHorBox(vRow,1));
                   }
                   //add last row without separator
                       vRow=new CBox_List();
                       vRow.addTerm(format(aNode.getNode(aNode.count()-1)));
                      // vRow.addTerm(new CTermBox(aVerSepListBox.getsep().getsymName()));
                       vList.addTerm(new CHorBox(vRow,0)); //vList.addTerm(new CHorBox(vRow,1));
                   // add row with close if nonempty
                   if(aVerSepListBox.getclose().getsymName().length()!=0){ // Needs reconsideration
                       vList.addTerm(new CTermBox(aVerSepListBox.getclose().getsymName()));
                   }
               }
                return new CVerBox(vList,0); */
            
                      
            
            default: assert false:String.format("Unexpected sortcode=%d in CFormatter.ASTTreeToBoxTree",aPattern.sortCode());
           
        }
        }
        
       return null;   
        
    }
   
    /**
     * Returns a box pattern for a given node. 
     * 
     * @param aNode the node object.
     * @return the box pattern associated with the given node.
     */
    protected CBox getPattern(CNode aNode){ 
        String  vSortLabel=null;
        if(aNode!=null){
            vSortLabel=aNode.sortLabel();
        }
        if(vSortLabel!=null&&Patterns!=null){
        for(int i=0;i<Patterns.getPatternsStructure().getfPatternDefs().contextCount();i++){
            if(vSortLabel.equals(Patterns.getPatternsStructure().getfPatternDefs().getElt(i).getName())){
                return Patterns.getPatternsStructure().getfPatternDefs().getElt(i).getpattern();
            }
        }
       
        }
        return null;
    }     
    
   /**
     * Returns the structural representation of the box tree.
     *
     * @return the box tree structure.
     */
    
    @Override
    public CNode getNode() {
        return BoxTree;
    }
      
    /**
     * Handles property change events from the AST and Patterns components. If the property change is from the AST component, 
     * retrieve its AST object, regenerate a box tree and notify observers. Else, If the 
     * property change is from the Patterns component, retrieve its pattern structure object, 
     * regenerate the box tree and notify observers.
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        String property=evt.getPropertyName();
        if(source==AST && property.equals("ASTStructure")){
            setASTNode(AST.getASTStructure());
       }else if(source==Patterns){
            setPatternDefns(Patterns.getPatternsStructure());
            
        }
         
         
    }
   
}

/* code to display separator on LHS

case CBoxesSortCodes.scVerSepListBox: 
               CVerSepListBox aVerSepListBox=(CVerSepListBox)aPattern;
               vList=new CBox_List();
               
               if(aNode.count()==0){
                   vList.addTerm(new CTermBox(aVerSepListBox.getopen().getsymName()));
                   vList.addTerm(new CTermBox(aVerSepListBox.getclose().getsymName()));
               }else{
                   //create first row from open and element 0
                   vRow=new CBox_List();
                   
                   vRow.addTerm(new CTermBox(aVerSepListBox.getopen().getsymName())); 
                   vRow.addTerm(format(aNode.getNode(0)));
                   vList.addTerm(new CHorBox(vRow,1));
                   
                   // add remaining rows from separator and element i
                   for(int i=1;i<aNode.count();i++){
                       vRow=new CBox_List();
                       vRow.addTerm(new CTermBox(aVerSepListBox.getsep().getsymName()));
                       vRow.addTerm(format(aNode.getNode(i)));
                        vList.addTerm(new CHorBox(vRow,1));
                   }
                  // add row with close if nonempty
                   if(aVerSepListBox.getclose().getsymName().length()!=0){ // Needs reconsideration
                       vList.addTerm(new CTermBox(aVerSepListBox.getclose().getsymName()));
                   }
               }
                return new CVerBox(vList,0);



*/
/*
Code to display separator on the rhs
case CBoxesSortCodes.scVerSepListBox: 
               CVerSepListBox aVerSepListBox=(CVerSepListBox)aPattern;
               vList=new CBox_List();
               
               if(aNode.count()==0){
                   vList.addTerm(new CTermBox(aVerSepListBox.getopen().getsymName()));
                   vList.addTerm(new CTermBox(aVerSepListBox.getclose().getsymName()));
                  // System.out.println(aVerSepListBox.getopen().getsymName()+"=="+aVerSepListBox.getclose().getsymName());
               }else if(aNode.count()==1){
                  vRow=new CBox_List();
                  vRow.addTerm(new CTermBox(aVerSepListBox.getopen().getsymName()));
                  vRow.addTerm(format(aNode.getNode(0)));
                  vRow.addTerm(new CTermBox(aVerSepListBox.getclose().getsymName()));
                  vList.addTerm(new CHorBox(vRow,0));
               }else{
                   //create first row from open and element 0
                   vRow=new CBox_List();
                   vRow.addTerm(new CTermBox(aVerSepListBox.getopen().getsymName())); 
                   vRow.addTerm(format(aNode.getNode(0)));
                   vRow.addTerm(new CTermBox(aVerSepListBox.getsep().getsymName()));
                   //vList.addTerm(new CHorBox(vRow,0)); //vList.addTerm(new CHorBox(vRow,1));
                   vList.addTerm(new CHorBox(vRow,0));
                   // add remaining rows from element i and separator
                   for(int i=1;i<aNode.count()-1;i++){
                       vRow=new CBox_List();
                       vRow.addTerm(format(aNode.getNode(i)));
                       vRow.addTerm(new CTermBox(aVerSepListBox.getsep().getsymName()));
                       vList.addTerm(new CHorBox(vRow,0)); //vList.addTerm(new CHorBox(vRow,1));
                   }
                   //add last row without separator
                       vRow=new CBox_List();
                       vRow.addTerm(format(aNode.getNode(aNode.count()-1)));
                      // vRow.addTerm(new CTermBox(aVerSepListBox.getsep().getsymName()));
                       vList.addTerm(new CHorBox(vRow,0)); //vList.addTerm(new CHorBox(vRow,1));
                   // add row with close if nonempty
                   if(aVerSepListBox.getclose().getsymName().length()!=0){ // Needs reconsideration
                       vList.addTerm(new CTermBox(aVerSepListBox.getclose().getsymName()));
                   }
               }
                return new CVerBox(vList,0);
*/