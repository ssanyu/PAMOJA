/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.ScanTablesView.NFADFATextView;




import Components.Lexical.ScanTables.IScanTableComp;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTextArea;


/**
 *
 * @author ssanyu
 */
public class CScanTablesTextView extends JTextArea implements PropertyChangeListener {
    private IScanTableComp ScanTables=null;

    /**
     *
     */
    public CScanTablesTextView(){
        super();
        setEditable(false);
    }

    /**
     *
     * @return
     */
    public IScanTableComp getScanTables(){
        return ScanTables;
    }

    /**
     *
     * @param aScanTables
     */
    public void setScanTables(IScanTableComp aScanTables){
        String vScannerText;
       if(ScanTables!=null){
              ScanTables.removePropertyChangeListener(this);
       }
       ScanTables=aScanTables;
       if(ScanTables!=null){
              ScanTables.addPropertyChangeListener(this);
              vScannerText=ScanTables.getDFATableText();
       }else{
              vScannerText= new String();
       }
       updateDFAScannerView(vScannerText);
    }

    /**
     *
     * @param aDFAScannerText
     */
    public void updateDFAScannerView(String aDFAScannerText){
           CScanTablesTextView.this.setText(aDFAScannerText);
    }

     //Handle a property change from another source
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if(evt.getSource()==ScanTables)
           setScanTables(getScanTables());
    }

}



