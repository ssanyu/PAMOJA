package Signatures;


import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CContextList;

/**
 *
 * @author HP
 */
public class CMemberDefinition_List extends CContextList {
    
    /**
     *
     */
    public CMemberDefinition_List(){
        super();
    }

    /**
     *
     * @param i
     * @return
     */
    public CMemberDefinition getElt(int i){
        return (CMemberDefinition)getContext(i);
     }

    /**
     *
     * @param i
     * @param aMemberDefinition
     */
    protected void setElt(int i, CMemberDefinition aMemberDefinition){
        setContext(i,aMemberDefinition);
    }
    
    @Override
    public String eltSortLabel() {
        return "MemberDefinition";
    }

    /**
     *
     * @return
     */
    @Override
    public int eltSortCode() {
        return CSignatureCodes.scMemberDefinition;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CSignatureCodes.scMemberDefinition_List;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "MemberDefinition_List";
    }

    /**
     *
     * @return
     */
    @Override
    public Class eltClass() {
        return CMemberDefinition.class;
    }

    /**
     *
     * @return
     */
    @Override
    public CContextKind contextKind() {
        return CContextKind.ckCollateral;
    }
    
}