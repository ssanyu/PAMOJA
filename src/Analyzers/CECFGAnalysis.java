/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Analyzers;


import GrammarNotions.Grammar.CGrammar;
import Sets.IntAlphabet;


/**
 * A class which stores ECFG analysis information associated with a production rule in a context free grammar. 
 * <p>
 * I.e, the sets: <code>First</code>, <code>Follow</code> and <code>Last</code> and the predicates: <code>null</code>, <code>reach</code>
 * and <code>empty</code>.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CECFGAnalysis {

   
    public boolean fReachable;
    public boolean fNullable;
    public boolean fEmpty;
    public IntAlphabet fFirst;
    public IntAlphabet fLast;
    public IntAlphabet fFollow;
    public boolean fELL1Det;
    public String fRoot;
    public String fPath;

   /**
     *  Creates a new <code> CECFGAnalysis </code>.
     */
    public CECFGAnalysis(){
        clear();
    }

    /**
     * Resets the CECFGAnalysis object.
     */
    public final void clear(){
        fEmpty=false;
        fNullable=false;
        fReachable=false;
        fELL1Det=false;
        fFirst=new IntAlphabet();
        fLast=new IntAlphabet();
        fFollow=new IntAlphabet();
        fRoot="";
        fPath="";
    }
    
    /**
     * Returns the string representation of the analysis information for a given grammar object.
     * 
     * @param aGrammar the grammar object.
     * @return the analysis information of a grammar in text form.
     */
    public String toText(CGrammar aGrammar){
        CGrammarAnalyzer vAnalyzer=new CGrammarAnalyzer(); 
        String s="";
        s=s+"\n  Det:"+fELL1Det+
            "\n  Root:"+fRoot+
            "\n  Path:"+fPath+
            "\n  Nullable:"+fNullable+
            "\n  Empty:"+fEmpty+
            "\n  Reachable:"+fReachable+
            "\n  First:"+vAnalyzer.setToString(aGrammar,fFirst)+
            "\n  Follow:"+vAnalyzer.setToString(aGrammar,fFollow)+
            "\n  Last:"+vAnalyzer.setToString(aGrammar,fLast);
        return s;
    }

}
