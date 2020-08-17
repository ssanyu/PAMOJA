package Signatures;

import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CItem;
import GrammarNotions.Context_Terms.CTerm;

/**
 *
 * @author HP
 */
public class  CMemberDefinition extends CItem{

    /**
     *
     */
    protected CMemberDefBody fBody;

    /**
     *
     * @param aName
     * @param aBody
     */
    public CMemberDefinition(String aName,CMemberDefBody aBody){
        super(aName);
        fBody=aBody;
    }
    
    /**
     *
     * @return
     */
    public CMemberDefBody getBody(){
        return fBody;
    }

    /**
     *
     * @param aBody
     */
    public void setBody(CMemberDefBody aBody){
        fBody=aBody;
    }
        
    /**
     *
     * @return
     */
    @Override
    public int termCount(){
        return 1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CTerm getTerm(int i){
        assert 0 <= i && i < termCount() : String.format("Precondition of CMemberDefinition.getTerm() failed: i=%d",i);
        switch(i){
             case 0:
                 return fBody;
             default:
                 return null;

        }
    }

    /**
     *
     * @param i
     * @param aTerm
     */
    @Override
    public void setTerm(int i,CTerm aTerm){
        switch(i){
             case 0:
                 assert aTerm instanceof CMemberDefBody : String.format("Precondition of CMemberDefinition.setTerm() failed: i=%d, aTerm.sortLabel=%s",i,aTerm.sortLabel());
                 fBody = (CMemberDefBody)aTerm;
                 break;

             default:
                 assert false : String.format("Precondition of CMemberDefinition.setTerm() failed: i=%d, CMemberDefinition.termCount=%d",i,termCount());
                 break;

        }
    }
     
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CSignatureCodes.scMemberDefinition;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "MemberDefinition";
    }

    /**
     *
     * @return
     */
    @Override
    public CContextKind contextKind() {
        return CContextKind.ckItem;
    }
    
    
    
}