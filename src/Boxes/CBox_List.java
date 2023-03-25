/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Context_Terms.CTermList;
 
/**
 * Resizable array implementation of a list of boxes. 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CBox_List extends CTermList  {
 
    /**
     *
     */
    public CBox_List(){} 

    /**
     *
     * @param i
     * @return
     */
    public CBox getElt(int i){
       return (CBox)getTerm(i);
   }

    /**
     *
     * @param i
     * @param aBox
     */
    protected void setElt(int i, CBox aBox){
       setTerm(i,aBox);
   }
   @Override
   public String eltSortLabel() {
       return "Box";
   }
   @Override
   public int eltSortCode() {
       return CBoxesSortCodes.scBox;
   }

    /**
     *
     * @return
     */
    @Override
   public int sortCode() {
       return CBoxesSortCodes.scBox_List;
   }

    /**
     *
     * @return
     */
    @Override
   public String sortLabel() {
        return "Box_List";
   }

    /**
     *
     * @return
     */
    @Override
   public Class eltClass() {
       return CBox.class;
   }

    /**
     *
     * @param i
     * @param aTerm
     * @return
     */
    @Override
   public boolean canSetTerm(int i, CTerm aTerm) {
       return 0<=i & i<termCount();
   }
}
