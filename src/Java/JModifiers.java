package Java;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class JModifiers extends ArrayList<Integer>{
/*
     * Access modifier flag constants from <em>The Java Virtual
     * Machine Specification</em>, Table 4.1.
     */

    /**
     *
     */
    
    public static final int PUBLIC	 =	Modifier.PUBLIC;

    /**
     *
     */
    public static final int PROTECTED	 =	Modifier.PROTECTED;

    /**
     *
     */
    public static final int PRIVATE	 =	Modifier.PRIVATE;

    /**
     *
     */
    public static final int STATIC	 =	Modifier.STATIC;

    /**
     *
     */
    public static final int FINAL	 =	Modifier.FINAL;

    /**
     *
     */
    public static final int ABSTRACT	 =	Modifier.ABSTRACT;
    
    
    private int mod = 0;
    
    /**
     *
     */
    public JModifiers(){
        super();
    }
    
    /**
     *
     * @param mod
     * @return
     */
    public static String toString( int mod ) {
        StringBuilder sb = new StringBuilder();
        int len;

        if ((mod & PUBLIC) != 0)        sb.append( "public " );
        if ((mod & PRIVATE) != 0)       sb.append( "private " );
        if ((mod & PROTECTED) != 0)     sb.append( "protected " );

        /* Canonical order */
        if ((mod & ABSTRACT) != 0)      sb.append( "abstract " );
        if ((mod & STATIC) != 0)        sb.append( "static " );
        if ((mod & FINAL) != 0)         sb.append( "final " );
        if ((len = sb.length()) > 0)    /* trim trailing space */
            return sb.toString().substring( 0, len-1 );
        return "";
    }
    
    /**
     *
     * @param aModifiers
     * @return
     */
    public static String toText(JModifiers aModifiers){
       
        StringBuilder s=new StringBuilder();
        if(aModifiers==null){
            return s.toString();
        }else{
            for(int i=0;i<aModifiers.size();i++){
                 if (aModifiers.get(i)==PUBLIC)        s.append( "public " );
                 else if (aModifiers.get(i)==PRIVATE)       s.append( "private " );
                 else if (aModifiers.get(i)==PROTECTED)     s.append( "protected " );
                 else if (aModifiers.get(i)==ABSTRACT)      s.append( "abstract " );
                 else if (aModifiers.get(i)==STATIC)        s.append( "static " );
                 else if (aModifiers.get(i)==FINAL)         s.append( "final " );
            }
            return s.toString();
        }
    }
        
  
        
    }

