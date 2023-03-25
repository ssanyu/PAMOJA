package Signatures;

import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CItem;
import GrammarNotions.Context_Terms.CTerm;

/**
 *
 * @author HP
 */
public class  CSortDefinition extends CItem{

    /**
     *
     */
    protected CSortDefBody fBody;

    /**
     *
     * @param aName
     */
    public CSortDefinition(String aName){
        super(aName);
    }

    /**
     *
     * @param aName
     * @param aBody
     */
    public CSortDefinition(String aName,CSortDefBody aBody){
        super(aName);
        fBody=aBody;
    }
    
    /**
     *
     * @return
     */
    public CSortDefBody getBody(){
        return fBody;
    }

    /**
     *
     * @param aBody
     */
    public void setBody(CSortDefBody aBody){
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
        assert 0 <= i && i < termCount() : String.format("Precondition of CSortDefinition.getTerm() failed: i=%d",i);
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
                 assert aTerm instanceof CSortDefBody : String.format("Precondition of CSortDefinition.setTerm() failed: i=%d, aTerm.sortLabel=%s",i,aTerm.sortLabel());
                 fBody = (CSortDefBody)aTerm;
                 break;

             default:
                 assert false : String.format("Precondition of CSortDefinition.setTerm() failed: i=%d, CSortDefinition.termCount=%d",i,termCount());
                 break;

        }
    }
      
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CSignatureCodes.scSortDefinition;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "SortDefinition";
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