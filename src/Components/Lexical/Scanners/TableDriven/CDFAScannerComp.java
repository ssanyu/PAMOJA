package Components.Lexical.Scanners.TableDriven;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Automata.NFADFA.CDFA;
import Components.Lexical.ScanTables.IScanTableComp;
import Components.Lexical.Scanners.CScannerComp;
import Scanners.CDFAScanner;
import Sets.Alphabet;
import SymbolStream.CSymKind;
import SymbolStream.CSymbolStream;
import SymbolStream.Symbol;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;

/**
 * A class for DFAScanner component. Its an observer of a ScanTable component. When it receives a property change from a ScanTable component, it updates its scan tables and (re)scans the input string
 * and (re)generates a symbol stream.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CDFAScannerComp extends CScannerComp implements IDFAScannerComp {
   /** 
    * A ScanTable object
    */
    private IScanTableComp ScanTables; 
    /**
     * A DFAScanner object
     */
    private CDFAScanner Scanner;
    Symbol fSymbol=null;     
      
    /**
     * Creates a new DFAScanner component.
     */
    public CDFAScannerComp(){
       super();
       Scanner=new CDFAScanner();
    }
    
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawString("Table-driven Scanner",5,15);
    }

    /**
     * 
     * @return
     */
    public IScanTableComp getScanTables(){
        return ScanTables;
    }
    
    
    public void setScanTables(IScanTableComp aScanTables){
         CDFA vDFATableStructure;
         //remove old ScanTables
         if(ScanTables!=null){
              ScanTables.removePropertyChangeListener(this);
         }
         ScanTables=aScanTables;
         if(ScanTables!=null){
             ScanTables.addPropertyChangeListener(this);
             //obtain the DFAStructure 
              vDFATableStructure=ScanTables.getDFATableStructure();
         }
         else { //ScanTable object is not connected, return empty structure
              vDFATableStructure=new CDFA(new Alphabet());
         }
         updateScanner(vDFATableStructure);
    }
    
    /**
     * Updates a DFAScanner object with the specified DFA object.
     * 
     * @param aDFATableStructure the DFA object
     */
    public void updateScanner(CDFA aDFATableStructure){
        CDFAScanner vScanner=new CDFAScanner();
        vScanner.setDFA(aDFATableStructure);
        setScanner(vScanner);
    }
   
    /**
     *
     * @return
     */
    @Override
    public CDFAScanner getScanner() {
        return Scanner;
    }
    
    /**
     * Sets the specified DFAScanner object to this DFAScanner component and notifies observers.
     * @param aScanner the specified DFAScanner object
     */
    public void setScanner(CDFAScanner aScanner){
           // keep the old value
        CDFAScanner oldScanner=Scanner;
        // save the new value
        Scanner=aScanner;
       // fire the property change event, with a property that has changed
       support.firePropertyChange("Scanner",oldScanner,Scanner);
    }

  @Override
  public CSymbolStream getSymbolStream(){
      return SymbolStream;
  }
    
  @Override
  public  void setSymbolStream( CSymbolStream aSymbolStream){
       // keep the old value
       CSymbolStream oldSymbolStream=SymbolStream;
       // get the new value
       SymbolStream=aSymbolStream;
       Scanner.setSymbolStream(SymbolStream);
       // fire the property change event, with a property that has changed
       support.firePropertyChange("SymbolStream",oldSymbolStream,SymbolStream);
  }
  
  
  @Override
  public void createSymbolStream(String aText) {
     
       CSymbolStream vSymStream=new CSymbolStream();
        //setSymbolStream(vSymStream);  
       SymbolStream=vSymStream;
       Scanner.setSymbolStream(vSymStream);
        Symbol vSymbol;
            int vSymListSize;     
            vSymStream.addNewRow();
            Scanner.setText(aText);
            while(!Scanner.finished()){ 
                Scanner.nextSym();
                vSymbol=new Symbol(Scanner.getSym(),Scanner.getSymStart(),Scanner.getSymValue(),Scanner.getSymName(),Scanner.getSymLength(),Scanner.getSymKind());
                vSymStream.addSymbol(vSymbol); 
                if(Scanner.getfChar()=='\n'){
                    vSymStream.addNewRow();
                }
            }
            if(ScanTables!=null && ScanTables.getDFATableStructure()!=null){
               vSymListSize=ScanTables.getDFATableStructure().getSymbols().size();
               vSymbol=new Symbol(vSymListSize-1,-1,"0","endmarker",0, CSymKind.FIXED);
               //  vSymbol=new Symbol(-1,-1,"0","endmarker",0, CSymKind.FIXED);
            
               vSymStream.addSymbol(vSymbol);
               setSymbolStream(vSymStream);
               // Scanner.setSymbolStream(vSymStream);
            }
  }
  
  /**
     * Handles property change events from ScanTable component and updates its scan tables.
     * @param evt event object with the new value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
       Object source=evt.getSource();
           if(source==ScanTables){
             updateScanner(ScanTables.getDFATableStructure());
           }
    }

   
}


