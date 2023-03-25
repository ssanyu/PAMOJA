/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.Signature;

import Components.IPAMOJAComponent;
import Signatures.CSignature;

/**
 * Defines services for interacting with other components ( like, TreeEditor component).
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ISignatureComp extends IPAMOJAComponent{
       
     /**
     * Returns the structural representation of a signature.
     * 
     * @return the internal structure of a signature.
     */
       public CSignature getSignatureStructure();

    /**
     * Sets the value of SignatureStructure and notifies all observers about <code>SignatureStructure</code> property change.
     *
     * @param aSignatureStructure the internal structure of a signature.
     */
    public void setSignatureStructure(CSignature aSignatureStructure);
   
   
    /**
     * Returns the text representation of a signature.
     * 
     * @return the signature in text form.
     */
       public String getSignatureText();

     /**
     * Sets the value of SignatureText and notifies all observers about <code>SignatureText</code> property change.
     *
     * @param aSignatureText the text representation of a signature.
     */
    public void setSignatureText(String aSignatureText);
    
}
