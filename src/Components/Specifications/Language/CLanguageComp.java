/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Specifications.Language;

import Components.Abstract.Signature.CSignatureComp;
import Components.CPAMOJAComponent;
import Components.Concrete.Grammar.CGrammarComp;
import Components.INodeObject;
import Nodes.CNode;
import java.awt.Graphics;


/**
 * A composite component which holds lexical syntax, concrete syntax and abstract syntax 
 * specifications. It is composed of a Grammar component and a Signature component.
 * A Language component notifies its observers of changes in its specifications.
 * 
 * @see Components.Concrete.Grammar.CGrammarComp
 * @see Components.Abstract.Signature.CSignatureComp
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CLanguageComp extends CPAMOJAComponent implements ILanguageComp,INodeObject {
    /**
     * An instance of a Grammar subcomponent.
     */
    private CGrammarComp grammarComp;
    /**
     * An instance of a Signature subcomponent.
     */
    private CSignatureComp signatureComp;
    
    /**
     * String representation of a grammar.
     */
    private String grammar;
    /**
     * String representation of a signature.
     */
    private String signature; 
   
    /**
     * Creates a new Language component.
     */
    public CLanguageComp() {
        super();
        grammarComp = new CGrammarComp();
        signatureComp=new CSignatureComp();
        grammar=grammarComp.getGrammarText();
        signature=signatureComp.getSignatureText();
    }

    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("Language",10, 10);
    }
    
    
    @Override
    public String getGrammar() {
        return grammar;
    }

    /**
     * Sets a new grammar and notifies observers.
     * @param grammar
     */
    public void setGrammar(String grammar) {
         //keep the old value
        String oldGrammar=this.grammar;
        //get the new value
        this.grammar=grammar;
        this.grammarComp.setGrammarText(grammar);
        support.firePropertyChange("grammar",oldGrammar,this.grammar);
                
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getSignature() {
        return signature;
    }

    /**
     * Sets a new signature and notifies observers.
     * @param signature
     */
    public void setSignature(String signature) {
         //keep the old value
        String oldSignature=this.signature;
        //get the new value
        this.signature=signature;
        this.signatureComp.setSignatureText(signature);
        support.firePropertyChange("signature",oldSignature,this.signature);
                
    }

    /**
     *
     * @return
     */
    @Override
    public CGrammarComp getGrammarComp() {
        return grammarComp;
    }

    /**
     *
     * @return
     */
    @Override
    public CSignatureComp getSignatureComp() {
        return signatureComp;
    }

    /**
     *
     * @param grammarComp
     */
    @Override
    public void setGrammarComp(CGrammarComp grammarComp) {
        this.grammarComp = grammarComp;
    }

    /**
     *
     * @param signatureComp
     */
    @Override
    public void setSignatureComp(CSignatureComp signatureComp) {
        this.signatureComp = signatureComp;
    }

    /**
     *
     * @return
     */
    @Override
    public CNode getNode() {
        return grammarComp.getGrammarStructure(); // not yet implemented
    }
    
}
