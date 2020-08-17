/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CContextList;
 
/**
 * Resizable array implementation of a list of pattern definitions. 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CPatternDef_List extends CContextList  {
 
    /**
     *
     */
    public CPatternDef_List(){} 

    /**
     *
     * @param i
     * @return
     */
    public CPatternDef getElt(int i){
      return (CPatternDef)getContext(i);
   }

    /**
     *
     * @param i
     * @param aPatternDef
     */
    protected void setElt(int i, CPatternDef aPatternDef){
      setContext(i,aPatternDef);
   }
   @Override
   public String eltSortLabel() {
      return "PatternDef";
   }
   @Override
   public int eltSortCode() {
       return CBoxesSortCodes.scPatternDef;
   }

    /**
     *
     * @return
     */
    public int sortCode() {
       return CBoxesSortCodes.scPatternDef_List;
   }

    /**
     *
     * @return
     */
    public String sortLabel() {
        return "PatternDef_List";
   }

    /**
     *
     * @return
     */
    @Override
   public Class eltClass() {
       return CPatternDef.class;
   }

    /**
     *
     * @return
     */
    public CContextKind contextKind() {
       return CContextKind.ckRecursive;
   }
}
