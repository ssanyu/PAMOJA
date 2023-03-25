/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Lexical.SymbolStream;


import Components.CPAMOJAComponent;
import Components.Concrete.Flattener.IFlattenerComp;
import Components.Lexical.Scanners.IScannerComp;
import SymbolStream.CSymbolStream;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * A component holding a symbol stream.
 * Its an observer of components which generate a symbol stream. When it receives a property change from these components, it updates its symbolstream.
 *  
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSymbolStreamComp extends CPAMOJAComponent implements ISymbolStreamComp,PropertyChangeListener {
    private String         SymbolStreamText;
    private CSymbolStream  SymbolStreamStructure;
    private IScannerComp   Scanner;
    private IFlattenerComp Flattener;
    
   
    /**
     * creates a new SymbolStream component.
     */
        public CSymbolStreamComp(){
        super();
        SymbolStreamStructure=new CSymbolStream();
        SymbolStreamText=SymbolStreamStructure.toText();
    }
    
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       g.drawString("SymbolStream",10, 10);
    }
    
    /**
     *
     * @param aScanner
     */
    @Override
    public void setScanner(IScannerComp aScanner){
        CSymbolStream vSymbolStreamStructure;
        
       if(Scanner!=null){
           Scanner.removePropertyChangeListener(this);
       }
       Scanner=aScanner;
       if(Scanner!=null){
           Scanner.addPropertyChangeListener(this);
           vSymbolStreamStructure=Scanner.getSymbolStream();
       }else{
           vSymbolStreamStructure= new CSymbolStream();
       }
       setSymbolStreamStructure(vSymbolStreamStructure);
    }

    /**
     *
     * @param aFlattener
     */
    @Override
    public void setFlattener(IFlattenerComp aFlattener){
        CSymbolStream vSymbolStreamStructure;
       if(Flattener!=null){
              Flattener.removePropertyChangeListener(this);
       }
       Flattener=aFlattener;
       if(Flattener!=null){
           Flattener.addPropertyChangeListener(this);
           vSymbolStreamStructure=Flattener.getSymbolStream();
       }else{
            vSymbolStreamStructure= new CSymbolStream();
       }
       setSymbolStreamStructure(vSymbolStreamStructure);
    }
    
    /**
     *
     * @return
     */
    @Override
    public IScannerComp getScanner(){
        return Scanner;
    }
    
    /**
     *
     * @return
     */
    @Override
    public CSymbolStream getSymbolStreamStructure() {
        return SymbolStreamStructure;
    }

    @Override
    public void setSymbolStreamStructure(CSymbolStream aSymbolStreamStructure) {
        // keep the old value
        CSymbolStream oldSymbolStreamStructure=SymbolStreamStructure;
        // save the new value
        SymbolStreamStructure=new CSymbolStream();
        SymbolStreamStructure.addAll(aSymbolStreamStructure.getSymbolStream());
        // Update textual representation of Stream structure
        SymbolStreamText=SymbolStreamStructure.toText();
        // fire the property change event, with a property that has changed
        support.firePropertyChange("SymbolStreamStructure",oldSymbolStreamStructure,SymbolStreamStructure);
    }

    /**
     *
     * @return
     */
    @Override
    public String getSymbolStreamText() {
        return SymbolStreamText;
    }

    /**
     *
     * @param aSymbolStreamText
     */
    @Override
    public void setSymbolStreamText(String aSymbolStreamText){
      //keep the old value
      String oldSymbolStreamText=SymbolStreamText;
      //Get the new Value
      SymbolStreamText=aSymbolStreamText;
      SymbolStreamStructure=CSymbolStream.fromText(aSymbolStreamText);
      //Fire a property change event
      support.firePropertyChange("SymbolStreamText",oldSymbolStreamText,SymbolStreamText);
    }
  /**
   * Handles property change events from other components, like Scanners and Flatteners, and updates its symbol stream.
   * @param evt 
   */
   @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        String property=evt.getPropertyName();
        
        if(source ==Scanner && property.equals("SymbolStream")){
           setSymbolStreamStructure(Scanner.getSymbolStream());
           
        }else if(source==Flattener && property.equals("SymbolStream")){
            setSymbolStreamStructure(Flattener.getSymbolStream());
        }
    }
    
    /**
     * Clears the symbol stream.
     */
    public void clear(){
        SymbolStreamStructure=new CSymbolStream();
        setSymbolStreamText(SymbolStreamStructure.toText());
    }

    /**
     *
     * @return
     */
    @Override
    public IFlattenerComp getFlattener() {
        return Flattener;
    }
}
