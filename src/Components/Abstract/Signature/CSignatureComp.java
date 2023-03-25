package Components.Abstract.Signature;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Analyzers.CSignatureAnalyzer;
import Components.CPAMOJAComponent;
import Components.INodeObject;
import Nodes.CNode;
import Signatures.*;
import java.awt.Graphics;
import java.util.ArrayList;

/** Holds the signature (abstract syntax) and maintains its consistency. It notifies its observers of changes in the signature.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSignatureComp extends CPAMOJAComponent implements ISignatureComp,INodeObject {
    
   private String SignatureText=""; //Textual representation
   private CSignature SignatureStructure; //Internal representation
   
   private ArrayList<String> fErrorList;
   private StringBuilder fErrors;;
   
   private CSignatureAnalyzer fAnalyzer;
  
    /**
     * Creates an instance of a Signature component.
     */
    public CSignatureComp() {
        super();
        SignatureStructure=new CSignature();
        SignatureText=SignatureStructure.toText();
        fErrorList=new ArrayList<String>();
        fErrors=new StringBuilder();
   }
   
    /**
     * Paints the UI of the Signature component object. 
     * 
     * @param g the Signature component object to paint.
     */
   @Override
   public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Signature",5,10);
   }
   
    /**
     * Returns signature error messages.
     * 
     * @return the error messages.
     */
    public StringBuilder getErrorList(){
       return fErrors;
    }
 
    /**
     * Returns the text representation of a signature.
     * 
     * @return the signature in text form.
     */
    @Override
   public String getSignatureText(){
      return SignatureText;
   }

    /**
     * Returns the structural representation of a signature.
     * 
     * @return the internal structure of a signature.
     */
    @Override
   public CSignature getSignatureStructure() {
        return SignatureStructure;
   }

    /**
     * Sets the value of SignatureStructure and notifies all observers about <code>SignatureStructure</code> property change.
     *
     * @param aSignatureStructure the internal structure of a signature.
     */
    @Override
   public void setSignatureStructure(CSignature aSignatureStructure){
        // keep the old value
        CSignature oldSignatureStructure=SignatureStructure;
        // save the new value
        SignatureStructure=aSignatureStructure;
        // Update textual representation of Signature
        SignatureText=SignatureStructure.toText();
        support.firePropertyChange("SignatureStructure",
                                    oldSignatureStructure,
                                    SignatureStructure);
   }
    
     /**
     * Sets the value of SignatureText and notifies all observers about <code>SignatureText</code> property change.
     *
     * @param aSignatureText the text representation of a signature.
     */
    @Override
   public void setSignatureText(String aSignatureText) {
       CSignature vSignatureStructure;
       //keep the old value
       String oldSignatureText=SignatureText;
       //Get the new Value
       SignatureText=aSignatureText; 
       
       //Create internal structure
       vSignatureStructure=new CSignature();
       vSignatureStructure=CSignature.fromText(SignatureText);
       
       //Update references in the Signature
       updateAllSorts(vSignatureStructure);
       // check if there are any Errors
       if(!fErrorList.isEmpty()){
           SignatureText=oldSignatureText;
           for(int i=0;i<fErrorList.size();i++){
                fErrors.append(String.format("Type: %s is not declared", fErrorList.get(i))).append("\n");
           }
       }else{
           SignatureStructure=vSignatureStructure;
       }

       //Fire a property change event
       support.firePropertyChange("SignatureText",
                                   oldSignatureText,
                                   SignatureText);
   }

    /**
     * Returns the signature node object.
     * 
     * @return the node object of the signature.
     */
    @Override
   public CNode getNode() {
         return (CNode)SignatureStructure.getUserSortCtxt();
   }
   
   private void updateAllSorts(CSignature aSignatureStructure){
       CSortDefBody vBody;
       fErrorList=new ArrayList<String>();
       fErrors=new StringBuilder();
       fAnalyzer=new CSignatureAnalyzer();
       fAnalyzer.setSignature(aSignatureStructure);
       //update references 
       for(int i=0;i<aSignatureStructure.getUserSortCtxt().getSortDefs().contextCount();i++){
           vBody=aSignatureStructure.getUserSortCtxt().getSortDefs().getElt(i).getBody();
           updateAllMembersInASort(vBody.getMemDefs());
           if(vBody!=null){
            updateSortUseRef(vBody.getAncestor());
           } 
       }
   }
   
   private void updateAllMembersInASort(CMemberDefinition_List aList){
       //update reference of each member
       for(int i=0;i<aList.contextCount();i++){
           updateSortUseRef(aList.getElt(i).getBody().getType());
       }
   }
   
   private void updateSortUseRef(CSortUse aUse){
       if (aUse.getSortDef() == null){
           CSortDefinition vSortDef = fAnalyzer.findSortDef(aUse.getName());
           if(vSortDef != null){
               aUse.setSortDef(vSortDef);
           }else{
               updateErrorList(aUse.getName());   
           }
       }
  }
   private void updateErrorList(String aName){
       if(!aName.equals("") && !(fErrorList.contains(aName))){
               fErrorList.add(aName);
       }
   }
  
}
