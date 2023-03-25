/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.BoxTree2Stream;


import Boxes.CBox;
import Boxes.CBoxesSortCodes;
import Boxes.CHorBox;
import Boxes.CIndBox;
import Boxes.CSelBox;
import Boxes.CTermBox;
import Boxes.CTermDataBox;
import Boxes.CVerBox;
import Components.Abstract.AST2BoxTree.IAST2BoxTreeComp;
import Components.CPAMOJAComponent;
import SymbolStream.CSymKind;
import SymbolStream.CSymbolStream;
import SymbolStream.Symbol;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * BoxTree2Stream component with a mapping from box tree to symbolstream. 
 * <p>
 * It observes AST2BoxTree component. When it receives a property change from AST2BoxTree, it
 * updates its symbolstream and notifies its observers (e.g. StreamtoText component).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CBoxTree2StreamComp extends CPAMOJAComponent implements IBoxTree2StreamComp,PropertyChangeListener{
    /**
     * A reference to <code>ASTtoBoxTree</code> object.
     */

    private IAST2BoxTreeComp ASTtoBoxTree;
    /**
     * A symbolstream object.
     */
    private CSymbolStream SymbolStream;
    /**
     * A box tree object.
     */
    private CBox BoxTree;

    CSymbolStream vSymbolStream;
    
    /**
     * Creates a new instance of <code>CBoxTreetoStreamComp</code>.
     */
    public CBoxTree2StreamComp(){
        super();
        SymbolStream=new CSymbolStream();
        SymbolStream.addNewRow();
    }
    /**
     * Paints the UI of the BoxTree2Stream component object. 
     * 
     * @param g the BoxTree2Stream component object to paint.
     */
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("BoxTree2Stream",10, 10);
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
     * Set the value of BoxTree
     *
     * @param aBoxTree new value of BoxTree
     */
    @Override
    public void setBoxTree(CBox aBoxTree) {
        BoxTree = aBoxTree;
    }


    /**
     * Get the value of SymbolStream
     *
     * @return the value of SymbolStream
     */
    @Override
    public CSymbolStream getSymbolStream() {
        return SymbolStream;
    }

   /**
     * Set the value of symbolstream and notify observers about <code>SymbolStream</code> property changes.
     *
     * 
     * @param aSymbolStream
     */
    @Override
    public void setSymbolStream(CSymbolStream aSymbolStream) {
         // keep the old value
        CSymbolStream oldSymbolStream=SymbolStream;
        // save the new value
        SymbolStream=aSymbolStream;
        //SymbolStreamText=SymbolStreamStructure.toText();
        // fire the property change event, with a property that has changed
        support.firePropertyChange("SymbolStream",oldSymbolStream,SymbolStream);
    }


    /**
     * Access the AST2BoxTree component via its interface.
     *
     * @return the ASTtoBoxTree interface
     */
    public IAST2BoxTreeComp getASTtoBoxTree() {
        return ASTtoBoxTree;
    }

    /**
     * Connect to ASTtoBoxTree component via its interface.
     * Register for property change events, retrieve current value of a box tree object and regenerate the symbolstream.
     *
     * @param aASTtoBoxTree IAST2BoxTreeComp object.
     */
    @Override
    public void setASTtoBoxTree(IAST2BoxTreeComp aASTtoBoxTree) {
       CBox vBoxTree;
      
       if(ASTtoBoxTree!=null){
              ASTtoBoxTree.removePropertyChangeListener(this);
       }
       ASTtoBoxTree=aASTtoBoxTree;
       if(ASTtoBoxTree!=null){
          ASTtoBoxTree.addPropertyChangeListener(this);
           vBoxTree=ASTtoBoxTree.getBoxTree();
       }else{
            vBoxTree=null;
       }
       setBoxTree(vBoxTree);
       createStream();
       
      
    }
    /**
     * Creates a symbolstream.
     */
   private void createStream(){
      if(ASTtoBoxTree!=null && BoxTree!=null){
        vSymbolStream=new CSymbolStream();
        vSymbolStream.addNewRow();
        boxTreeToSymbolStream(BoxTree);
        setSymbolStream(vSymbolStream); 
      }
   }
   /**
    * Creates a symbolstream recursively from this box tree.
    * 
    * @param aBoxTree the CBox object.
    */
    private void boxTreeToSymbolStream(CBox aBoxTree){
        
        if(aBoxTree!=null){
        switch(aBoxTree.sortCode()){
            
            case CBoxesSortCodes.scSelBox: 
                CSelBox aSelBox=(CSelBox)aBoxTree;
                boxTreeToSymbolStream(aSelBox.getarg());
                break;
            case CBoxesSortCodes.scIndBox: 
                 CIndBox aIndBox=(CIndBox)aBoxTree;
                 vSymbolStream.indent();  
                 boxTreeToSymbolStream(aIndBox.getarg());
                 vSymbolStream.unIndent();
                 break;
            case CBoxesSortCodes.scTermBox: 
                CTermBox aTermBox=(CTermBox)aBoxTree;
                vSymbolStream.addSymbol(new Symbol(0,0,"",aTermBox.getsymName(),0,CSymKind.FIXED));
                break;
            case CBoxesSortCodes.scTermDataBox: 
                CTermDataBox aTermDataBox=(CTermDataBox)aBoxTree;
                vSymbolStream.addSymbol(new Symbol(0,0,aTermDataBox.getdata(),aTermDataBox.getsymName(),aTermDataBox.getdata().length(),CSymKind.VARIABLE));
                break;
            case CBoxesSortCodes.scHorBox: 
                 CHorBox aHorBox=(CHorBox)aBoxTree;
                 CBox vHBox;
                 if(aHorBox.getlist().termCount()>0){
                     
                   for(int i=0;i<aHorBox.getlist().termCount()-1;i++){
                        
                        vHBox=aHorBox.getlist().getElt(i);
                        
                        if(vHBox instanceof CTermBox){  
                           if(((CTermBox)vHBox).getsymName().length()!=0){
                             boxTreeToSymbolStream(vHBox);
                             if(aHorBox.gethSpace()!=0)
                                vSymbolStream.addFiller(aHorBox.gethSpace());  
                           }else{
                            //do nothing  
                           }
                       }else{
                          boxTreeToSymbolStream(vHBox);
                          
                          if(aHorBox.gethSpace()!=0)
                              vSymbolStream.addFiller(aHorBox.gethSpace());
                          
                       }
                    }
                    vHBox=aHorBox.getlist().getElt(aHorBox.getlist().termCount()-1);
                    
                    if(vHBox instanceof CTermBox){
                       if(((CTermBox)vHBox).getsymName().length()!=0){
                             
                           boxTreeToSymbolStream(vHBox);
                       }else{
                           //do nothing   
                        }
                        
                   }else{
                     
                      boxTreeToSymbolStream(vHBox);
       
                  }
                }
               
                 break;
            case CBoxesSortCodes.scVerBox: 
                 CVerBox aVerBox=(CVerBox)aBoxTree;
                 CBox vBox;
                 for(int i=0;i<aVerBox.getlist().termCount()-1;i++){ 
                    vBox=aVerBox.getlist().getElt(i);
                    
                    // should not add a new row in case of an empty symbol - need to be reimplemented
                    if(vBox instanceof CTermBox){
                        if(((CTermBox)vBox).getsymName().length()!=0){
                            boxTreeToSymbolStream(vBox);
                            vSymbolStream.addNewRow();  
                        }else{
                            //do nothing  
                        }
                    }else{
                         boxTreeToSymbolStream(vBox);
                         vSymbolStream.addNewRow(); 
                    }
                                    
                 }
                    vBox=aVerBox.getlist().getElt(aVerBox.getlist().termCount()-1);
                     
                    if(vBox instanceof CTermBox){
                         if(((CTermBox)vBox).getsymName().length()!=0){
                             
                            boxTreeToSymbolStream(vBox);
                        }else{
                            
                        }
                        
                    }else{
                         boxTreeToSymbolStream(vBox);
                        
                    }
                
                 break;
         }
        
        }
    }
    /**
     * Handle property change events. If the property change is from the AST2BoxTree component, retrieve its box tree object, regenerate the symbolstream and notify observers. 
     * 
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        //String property=evt.getPropertyName();
        if(source==ASTtoBoxTree){
             setBoxTree(ASTtoBoxTree.getBoxTree());    
             createStream();          
        }
    }
  
    
}
