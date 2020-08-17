/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import GrammarNotions.Context_Terms.CContext;
import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CContextTuple;
import Nodes.CNodeKind;
 
/**
 *
 * @author HP
 */
public class CPatternCtxt extends CContextTuple  {

    /**
     *
     */
    protected CPatternDef_List fdefs;

    /**
     *
     */
    public CPatternCtxt(){
    }

    /**
     *
     * @param adefs
     */
    public CPatternCtxt(CPatternDef_List adefs){
        fdefs=adefs;
    }

    /**
     *
     * @return
     */
    public CPatternDef_List getdefs(){
        return fdefs;
    }

    /**
     *
     * @param adefs
     */
    public void setdefs(CPatternDef_List adefs){
        fdefs=adefs;
    }

    /**
     *
     * @return
     */
    public String sortLabel(){
        return "PatternCtxt";
    }

    /**
     *
     * @return
     */
    public int sortCode(){
        return CBoxesSortCodes.scPatternCtxt;
    }

    /**
     *
     * @return
     */
    public CNodeKind kind(){
        return CNodeKind.TUPLE;
    }

    /**
     *
     * @return
     */
    public int contextCount(){
        return super.contextCount() + 1;
    }

    /**
     *
     * @return
     */
    public CContextKind contextKind(){
        return CContextKind.ckItem;
    }

    /**
     *
     * @param i
     * @return
     */
    public CContext getContext(int i){
        assert 0<=i && i<contextCount() : String.format("Precondition of CPatternCtxt.getContext() failed: i=%d",i);
        switch(i-super.contextCount()){
             case 0:
                 return fdefs;

             default:
                 return super.getContext(i);

        }
    }

    /**
     *
     * @param i
     * @param aContext
     */
    public void setContext(int i,CContext aContext){
        assert 0<=i && i<contextCount() : String.format("Precondition of CPatternCtxt.setContext() failed: i=%d",i);
        switch(i-super.contextCount()){
             case 0:
                 assert aContext instanceof CPatternDef_List : String.format("Precondition of CPatternCtxt.setContext() failed: i=%d , aTerm.sortLabel=%s",i,aContext.sortLabel());
                 fdefs=(CPatternDef_List)aContext;
                 break;

             default:
                 super.setContext(i,aContext);
                 break;

        }
    }
}
