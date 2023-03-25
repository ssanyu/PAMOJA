/*====================================================
This file has been generated automatically

Date       : Mon Apr 02 10:03:44 CEST 2012
=====================================================*/

 
package Boxes; 

import Nodes.CNodeFactory;
import java.util.HashMap;
import java.util.Map;
 
/**
 *  A factory class containing methods for creating different kinds of box tree nodes.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CBoxesASTNodeFactory extends CNodeFactory  {

    /**
     *
     */
    protected Map<String,Class> fLabeltoClass;

    /**
     *
     */
    protected Map<Integer,Class> fCodetoClass;

    /**
     *
     */
    public CBoxesASTNodeFactory(){
        fLabeltoClass=new HashMap<String,Class>(){{
              put("Box",CBox.class);
              put("SelBox",CSelBox.class);
              put("IndBox",CIndBox.class);
              put("HorBox",CHorBox.class);
              put("VerBox",CVerBox.class);
              put("FlowListBox",CFlowListBox.class);
              put("TermBox",CTermBox.class);
              put("TermDataBox",CTermDataBox.class);
              put("HorSepListBox",CHorSepListBox.class);
              put("VerSepListBox",CVerSepListBox.class);
              put("ArgNodeBox",CArgNodeBox.class);
              put("ArgDataBox",CArgDataBox.class);
              put("PatternDef",CPatternDef.class);
              put("PatternCtxt",CPatternCtxt.class);
              put("Box_List",CBox_List.class);
              put("PatternDef_List",CPatternDef_List.class);

       }};
        fCodetoClass=new HashMap<Integer,Class>(){{
              put(new Integer(CBoxesSortCodes.scBox),CBox.class);
              put(new Integer(CBoxesSortCodes.scSelBox),CSelBox.class);
              put(new Integer(CBoxesSortCodes.scIndBox),CIndBox.class);
              put(new Integer(CBoxesSortCodes.scHorBox),CHorBox.class);
              put(new Integer(CBoxesSortCodes.scVerBox),CVerBox.class);
              put(new Integer(CBoxesSortCodes.scFlowListBox),CFlowListBox.class);
              put(new Integer(CBoxesSortCodes.scTermBox),CTermBox.class);
              put(new Integer(CBoxesSortCodes.scTermDataBox),CTermDataBox.class);
              put(new Integer(CBoxesSortCodes.scHorSepListBox),CHorSepListBox.class);
              put(new Integer(CBoxesSortCodes.scVerSepListBox),CVerSepListBox.class);
              put(new Integer(CBoxesSortCodes.scArgNodeBox),CArgNodeBox.class);
              put(new Integer(CBoxesSortCodes.scArgDataBox),CArgDataBox.class);
              put(new Integer(CBoxesSortCodes.scPatternDef),CPatternDef.class);
              put(new Integer(CBoxesSortCodes.scPatternCtxt),CPatternCtxt.class);
              put(new Integer(CBoxesSortCodes.scBox_List),CBox_List.class);
              put(new Integer(CBoxesSortCodes.scPatternDef_List),CPatternDef_List.class);

       }};
    }

    /**
     *
     * @param aCode
     * @return
     */
    protected Class codeToClass(int aCode) {
         Class vClass=fCodetoClass.get(aCode);
         return vClass;
    }

    /**
     *
     * @param aLabel
     * @return
     */
    protected Class labelToClass(String aLabel) {
         Class vClass=fLabeltoClass.get(aLabel);
         return vClass;
    }
}
