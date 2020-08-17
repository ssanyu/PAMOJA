/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.SymbolStreamView;


import Components.Lexical.SymbolStream.ISymbolStreamComp;
import SymbolStream.CSymbolStream;
import javax.swing.*;
import java.awt.*;
import java.beans.*;


/**
 * An implementation of a table view for inspecting a symbol stream. When it observes a change in a SymbolStream component, it updates its table view.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSymbolStreamView extends JPanel implements PropertyChangeListener {
 
    private ISymbolStreamComp SymbolStream=null;
    private CSymbolStreamViewModel fSymbolStreamViewModel;
    private JTable fTable;

    /**
     * Creates an instance of a SymbolStreamView.
     */
    public CSymbolStreamView(){
      fSymbolStreamViewModel=new  CSymbolStreamViewModel();
      setSize(150,150);
      setLayout(new BorderLayout());
      add(fTable=new JTable(fSymbolStreamViewModel));
      fTable.setPreferredScrollableViewportSize(new Dimension(150, 70));
      fTable.setFillsViewportHeight(true);
      //Create the scroll pane and add the table to it.
      JScrollPane scrollPane = new JScrollPane(fTable);
      //Add the scroll pane to this panel.
      add(scrollPane);
    }
    
    /**
     * Returns a reference to a SymbolStream component.
     * @return  a reference to a SymbolStream component.
     */
    public ISymbolStreamComp getSymbolStream(){
        return SymbolStream;
    }

    /**
     * Connects to a SymbolStream component via the specified reference.
     * @param aSymbolStream a reference to a SymbolStream component.
     */
    public void setSymbolStream(ISymbolStreamComp aSymbolStream){
       CSymbolStream vSymbolStreamStructure;
       if(SymbolStream!=null){
              SymbolStream.removePropertyChangeListener(this);
       }
       SymbolStream=aSymbolStream;
       if(SymbolStream!=null){
              SymbolStream.addPropertyChangeListener(this);
              vSymbolStreamStructure=SymbolStream.getSymbolStreamStructure();
       }
       else{
           vSymbolStreamStructure=new CSymbolStream();
       }
       updateSymbolStreamView(vSymbolStreamStructure);
    }

    /**
     * Updates this SymbolStreamView with the specified symbolstream.
     * 
     * @param aSymbolStreamStructure CSymbolStream object.
     */
    public void updateSymbolStreamView(CSymbolStream aSymbolStreamStructure){
        if(aSymbolStreamStructure!=null){
            fSymbolStreamViewModel.updateRows(aSymbolStreamStructure);
        }
    }

    /**
     * Handles a property change from the SymbolStream component.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        Object source=evt.getSource();
        if(source==SymbolStream){
          updateSymbolStreamView(SymbolStream.getSymbolStreamStructure());
        }
    }
    
    /**
     * Clears the SymbolStreamView.
     */
    public void clear(){
        updateSymbolStreamView(new CSymbolStream());
    }

}


