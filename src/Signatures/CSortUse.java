package Signatures;

import GrammarNotions.Context_Terms.CItem;
import GrammarNotions.Context_Terms.CTermTuple;

/* if sortDef!=null then sortName==sortDef.name
 * SortUse=D[name:String] R[sortDef:SortDefinition]<_Term
*/

/**
 *
 * @author HP
 */

public class CSortUse extends CTermTuple {
   private String fName;
   private CSortDefinition fSortDef;

    /**
     *
     * @param aName
     * @param aSortDef
     */
    public CSortUse(String aName,CSortDefinition aSortDef){
        fName=aName;
        fSortDef=aSortDef;
    }

    /**
     *
     * @param aSortDef
     */
    public void setSortDef(CSortDefinition aSortDef){
        fSortDef = aSortDef;
    }

    /**
     *
     * @return
     */
    public CSortDefinition getSortDef(){
        return fSortDef;
    }

    /**
     *
     * @param aName
     */
    protected void setName(String aName){
        assert fSortDef==null : String.format("CSortUse.setName() failed: aName=%s",aName);
    }

    /**
     *
     * @return
     */
    public String getName(){
       if(fSortDef==null)
            return fName;
       else return fSortDef.getName();
         
    }

    /**
     *
     * @return
     */
    @Override
     public int sortCode(){
            return CSignatureCodes.scSortUse;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel(){
        return "SortUse";
    }
    
    /**
     *
     * @return
     */
    @Override
    public int dataCount(){
        return 1;
    }

    /**
     *
     * @return
     */
    @Override
    public int referenceCount(){
        return 1;
    }
      
    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i){
        if(i==0)
            return getName();
        else return super.getData(i);
   }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i,String aData){
        if(i==0)
            setName(aData);
        else super.setData(i,aData);
    }
    @Override
    public CItem getReference(int i){
        if(i==0)
            return fSortDef;
        else return super.getReference(i);
    }

    /**
     *
     * @param i
     * @param aRef
     */
    @Override
    public void setReference(int i,CItem aRef){
        if(i==0)
            fSortDef=(CSortDefinition)aRef;
        else super.setReference(i,aRef);
    }
   
    /**
     *
     * @return
     */
    public String toText(){
        
       if(fSortDef!=null){
           return fSortDef.getName();
       }else{
         return fName;
       }
    }
   
    // TO DO
 
    /**
     *
     * @param aName
     * @return
     */
        public static CSortUse fromText(String aName){ 
      // return new CSortUse(aName,fSortDef);
        return null;
    }
}