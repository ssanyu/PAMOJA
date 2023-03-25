package Signatures;

import GrammarNotions.Context_Terms.CContext;
import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CContextTuple;

/**
 *
 * @author HP
 */
public class  CSortCtxt extends CContextTuple{

    /**
     *
     */
    protected CSortDefinition_List fSortDefs;
   
    /**
     *
     * @param aSortDefs
     */
    public CSortCtxt(CSortDefinition_List aSortDefs){
        fSortDefs=aSortDefs;
    }
    
    /**
     *
     * @param aSortDefs
     */
    protected void setSortDefs( CSortDefinition_List aSortDefs){
        fSortDefs=aSortDefs;
    }
    
    /**
     *
     * @return
     */
    public CSortDefinition_List getSortDefs(){
        return fSortDefs;
    }

    /**
     *
     * @return
     */
    @Override
    public CContextKind contextKind(){
        return CContextKind.ckItem;
    }

    @Override
    public int contextCount() {
        return 1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CContext getContext(int i) {
        if(i==0)
            return fSortDefs;
        else return super.getContext(i);
    }

    /**
     *
     * @param i
     * @param aContext
     */
    @Override
    public void setContext(int i, CContext aContext) {
        if(i==0)
            fSortDefs=(CSortDefinition_List)aContext;
        else  super.setContext(i,aContext);
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode(){
        return CSignatureCodes.scSortCtxt;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "SortCtxt";
    }
    
    
    
        
        
        
        
        


    
}