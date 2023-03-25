/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FALayout;

import GrammarNotions.RegExpr.CRE;
import GrammarNotions.RegExpr.CRE_Char;
import GrammarNotions.RegExpr.CRE_Empty;
import GrammarNotions.RegExpr.CRE_Eps;
import GrammarNotions.RegExpr.CRE_List;
import GrammarNotions.RegExpr.CRE_MultiDot;
import GrammarNotions.RegExpr.CRE_MultiStick;
import GrammarNotions.RegExpr.CRE_Star;
import GrammarNotions.RegExpr.CRE_String;

/**
 *
 * @author Ssanyu
 */
public class CFATester {
    public CFATester(){
        
    }
    
    public CRE createEps(){
       // "Eps"
       CRE_Eps c1=new CRE_Eps();
           return c1;
    }
    public CRE createEmpty(){
       // "Eps"
       CRE_Empty c1=new CRE_Empty();
           return c1;
    }
    public CRE createName(){
       // "abcd"
       CRE_String c1=new CRE_String("abcjyuuyjuughjhjhjh");
           return c1;
    }
    
   public CRE createDot(){
       // 'a'.'b'."xyz"
       CRE_Char c1=new CRE_Char('a');
       CRE_Char c2=new CRE_Char('b');
       CRE_String c3=new CRE_String("xyz");
       
       CRE_List aList =new CRE_List();
       aList.add(c1);
       aList.add(c2);
       aList.add(c3);
       CRE_MultiDot dot=new CRE_MultiDot(aList);
       return dot;
    }
   
   public CRE createStick(){
       // a |Hello | e
       CRE_Char c1=new CRE_Char('a');
       CRE_Char c2=new CRE_Char('e');
       CRE_String c3=new CRE_String("Hello");
       CRE_String c4=new CRE_String("good");
       CRE_Char c5=new CRE_Char('r');
       
       CRE_List aList =new CRE_List();
       aList.add(c1);
       aList.add(c3);
       aList.add(c2);
       aList.add(c4);
       aList.add(c5);
       CRE_MultiStick dot=new CRE_MultiStick(aList);
       return dot;
    }
   
     public CRE createDot2(){
       // (a |b)*.a.b.b
       CRE_Char c1=new CRE_Char('a');
       CRE_Char c2=new CRE_Char('b');
             
       CRE_List aList =new CRE_List();
       aList.add(c1);
       aList.add(c2);
       CRE_MultiStick dot=new CRE_MultiStick(aList);
       CRE_Star s=new CRE_Star(dot);
       
       CRE_List aList1 =new CRE_List();
       aList1.add(s);
       aList1.add(c1);
       aList1.add(c2);
        aList1.add(c2);
       CRE_MultiDot d=new CRE_MultiDot(aList1);
       return d;
    }
   
   public CRE createStar(){
       // f*
       
       CRE_Char c2=new CRE_Char('f');
       CRE_Star c1=new CRE_Star(c2);
       
       
       return c1;
    }

   }
    

