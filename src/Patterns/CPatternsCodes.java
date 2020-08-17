/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns;

import java.util.Hashtable;

/**
 *
 * @author jssanyu
 */
public class CPatternsCodes {
    
    // scanner symbols
    
    /**
     *
     */
        
    public static final int syTerm=0;

    /**
     *
     */
    public static final int syTermData=1;

    /**
     *
     */
    public static final int sySel=2;

    /**
     *
     */
    public static final int syInd=3;

    /**
     *
     */
    public static final int syNode=4;

    /**
     *
     */
    public static final int syData=5;

    /**
     *
     */
    public static final int syHor=6;

    /**
     *
     */
    public static final int syVer=7;

    /**
     *
     */
    public static final int syHorSep=8; 

    /**
     *
     */
    public static final int syVerSep=9;

    /**
     *
     */
    public static final int syEquals=10;

    /**
     *
     */
    public static final int syOpen=11;

    /**
     *
     */
    public static final int syClose=12;

    /**
     *
     */
    public static final int sySqOpen=13;

    /**
     *
     */
    public static final int sySqClose=14;

    /**
     *
     */
    public static final int syComma=15;

    /**
     *
     */
    public static final int syName=16;

    /**
     *
     */
    public static final int syNumber=17;

    /**
     *
     */
    public static final int syHorDataSep=18;
    
    /**
     *
     */
    public static final int syError=50;

    /**
     *
     */
    public static final int syEndmarker=51; // \0

    /**
     *
     */
    public static final int syAttribute=52; // '@'
    
    //Keywords
    static Hashtable<String,Integer> keywords=new Hashtable<String,Integer>(){{
                put("Sel",new Integer(sySel));
                put("Term",new Integer(syTerm));
                put("TermData",new Integer(syTermData));
                put("Hor",new Integer(syHor));
                put("Node",new Integer(syNode));
                put("Ver",new Integer(syVer));
                put("Ind",new Integer(syInd));
                put("Data",new Integer(syData));
                put("HorSep",new Integer(syHorSep)); 
                put("VerSep",new Integer(syVerSep));
                put("HorDataSep",new Integer(syHorDataSep));
                
               
        }}; 
    
}
