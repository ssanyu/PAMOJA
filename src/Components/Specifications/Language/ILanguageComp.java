/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Specifications.Language;

import Components.Abstract.Signature.CSignatureComp;
import Components.Concrete.Grammar.CGrammarComp;
import Components.IPAMOJAComponent;

/**
 * Interface providing services for interacting with all language enabled components.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface ILanguageComp extends IPAMOJAComponent{
    
    /**
     * Returns a String representation of a Grammar instance.
     * @return a String representation of a Grammar instance.
     */
    String getGrammar();

    /**
     * Returns a String representation of a Signature instance.
     * @return a String representation of a Signature instance.
     */
    String getSignature();

    /**
     * Returns an instance of a Grammar subcomponent.
     * @return an instance of a Grammar subcomponent.
     */
    CGrammarComp getGrammarComp();

    /**
     * Returns an instance of a Signature subcomponent.
     * @return an instance of a Signature subcomponent.
     */
    CSignatureComp getSignatureComp();

    /**
     * Sets the specified Grammar component.
     * @param GrammarComp
     */
    void setGrammarComp(CGrammarComp GrammarComp);

    /**
     * Sets the specified Signature component.
     * @param SignatureComp
     */
    void setSignatureComp(CSignatureComp SignatureComp);
    
}
