/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Signatures;

import java.util.ArrayList;

/**
 *
 * @author jssanyu
 */
public class CRoles {

    /**
     *
     */
    public static char CONTEXT='C';

    /**
     *
     */
    public static char TERM='T';

    /**
     *
     */
    public static char DATA='D';
  
    /**
     *
     */
    public static char REFERENCE='R';  
     
     //Roles permitted for Base sorts

    /**
     *
     */
         public static ArrayList<Character> TERMROLES=new ArrayList<Character>(){{
             add('C');
             add('T');
             add('D');
             add('R');
     }};

    /**
     *
     */
    public static ArrayList<Character> CTXTROLES=new ArrayList<Character>(){{
             add('C');
     }};

    /**
     *
     */
    public static ArrayList<Character> ITEMROLES=new ArrayList<Character>(){{
             add('T');
             add('D');
     }};

    /**
     *
     */
    public static ArrayList<Character> DATAROLES=new ArrayList<>();
}

   