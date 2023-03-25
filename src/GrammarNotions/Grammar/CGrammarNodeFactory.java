/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Grammar;

import GrammarNotions.ECFGNodes.*;
import Nodes.CNodeFactory;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ssanyu
 */
public class CGrammarNodeFactory extends CNodeFactory {

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
    public CGrammarNodeFactory(){
        fLabeltoClass=new HashMap<String,Class>(){{
                put("ECFGNode",CECFGNode.class);
                put("EpsNode",CEpsNode.class);
                put("TermNode",CTermNode.class);
                put("TermValueNode",CTermValueNode.class);
                put("NonTermNode",CNonTermNode.class);
                put("MultiDotNode",CMultiDotNode.class);
                put("MultiStickNode",CMultiStickNode.class);
                put("StarNode",CStarNode.class);
                put("PlusNode",CPlusNode.class);
                put("OptionNode",COptionNode.class);
                put("TupleNode",CTupleNode.class);
                put("AltNode",CAltNode.class);
                
        }};
    
        fCodetoClass=new HashMap<Integer,Class>(){{
                put(new Integer(CECFGNodeCodes.scECFGNode),CECFGNode.class);
                put(new Integer(CECFGNodeCodes.scEpsNode),CECFGNode.class);
                put(new Integer(CECFGNodeCodes.scTermNode),CTermNode.class);
                put(new Integer(CECFGNodeCodes.scTermValueNode),CTermValueNode.class);
                put(new Integer(CECFGNodeCodes.scNonTermNode),CNonTermNode.class);
                put(new Integer(CECFGNodeCodes.scMultiDotNode),CMultiDotNode.class);
                put(new Integer(CECFGNodeCodes.scMultiStickNode),CMultiStickNode.class); 
                put(new Integer(CECFGNodeCodes.scStarNode),CStarNode.class);
                put(new Integer(CECFGNodeCodes.scPlusNode),CPlusNode.class);
                put(new Integer(CECFGNodeCodes.scOptionNode),COptionNode.class);
                put(new Integer(CECFGNodeCodes.scTupleNode),CTupleNode.class);
                put(new Integer(CECFGNodeCodes.scAltNode),CAltNode.class);
              
      }};
    
    }

    /**
     *
     * @param aCode
     * @return
     */
    @Override
    protected Class codeToClass(int aCode) {
        Class vClass;
        vClass=fCodetoClass.get(aCode);
        return vClass;
    }

    /**
     *
     * @param aLabel
     * @return
     */
    @Override
    protected Class labelToClass(String aLabel) {
       Class vClass;
       vClass=fLabeltoClass.get(aLabel);
       return vClass;
    }
 
}
