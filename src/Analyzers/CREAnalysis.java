/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Analyzers;

import Sets.Alphabet;



/**
 * A class which stores the RE analysis information associated with a production rule in a lexical grammar.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CREAnalysis {

    
    public boolean fNullable;

   
    public Alphabet fFirst;
    
   /**
     * Resets the CREAnalysis object.
     */
    public void clear(){
        fNullable=false;
        fFirst=new Alphabet();
    }
    
    /**
     * Returns the string representation of the analysis information for a lexical grammar object.
     * 
     * @return the analysis information of a lexical grammar in text form.
     */
    @Override
    public String toString(){
       
        String s="";
        s=s+"\n  Nullable:"+fNullable+
            "\n  First:{"+fFirst.toString()+'}';
            
        return s;
    }
    
    
}
