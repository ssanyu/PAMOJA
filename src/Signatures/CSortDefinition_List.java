package Signatures;


import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CContextList;

/**
 *
 * @author HP
 */
public class CSortDefinition_List extends CContextList {
    
    /**
     *
     */
    public CSortDefinition_List(){
        super();
    }

    /**
     *
     * @param i
     * @return
     */
    public CSortDefinition getElt(int i){
        return (CSortDefinition)getContext(i);
     }

    /**
     *
     * @param i
     * @param aSortDefinition
     */
    protected void setElt(int i, CSortDefinition aSortDefinition){
        setContext(i,aSortDefinition);
    }
    
    @Override
    public String eltSortLabel() {
        return "SortDefinition";
    }

    @Override
    public int eltSortCode() {
        return CSignatureCodes.scSortDefinition;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CSignatureCodes.scSortDefinition_List;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "SortDefinition_List";
    }

    /**
     *
     * @return
     */
    @Override
    public Class eltClass() {
        return CSortDefinition.class;
    }

    /**
     *
     * @return
     */
    @Override
    public CContextKind contextKind() {
        return CContextKind.ckRecursive;
    }
    
}