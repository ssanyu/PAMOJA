/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.Scanners;


import Components.CPAMOJAComponent;
import SymbolStream.CSymbolStream;
import java.beans.PropertyChangeListener;
/**
 * This is an abstract base class for all Scanner components.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public abstract class CScannerComp extends CPAMOJAComponent implements IScannerComp,PropertyChangeListener{

    /**
     *
     */
    protected CSymbolStream SymbolStream;

    /**
     *
     */
    public CScannerComp(){
       super();
       SymbolStream=new CSymbolStream();
   }

  //Abstract methods
    @Override
  public abstract void createSymbolStream(String aText); 
    @Override
  public abstract CSymbolStream getSymbolStream(); 

    /**
     *
     * @param aStream
     */
    @Override
  public abstract void setSymbolStream(CSymbolStream aStream);
}