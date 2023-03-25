 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseLog;

import Components.Concrete.Parser.IParserComp;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author HP
 */
public class CParseLogView extends JTable implements PropertyChangeListener{
    private ParseLogTableModel model; 
     /**
     * A reference to LRparser object.
     */
    private IParserComp Parser;
    
    /**
     *
     */
    public CParseLogView() {
        super();
        model=new ParseLogTableModel();
        setModel(model);
        setShowGrid(true);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);
     }
    
    /**
     *
     * @return
     */
    public IParserComp getParser() {
        return Parser;
    }

    /**
     *
     * @param aParser
     */
    public void setParser(IParserComp aParser) {
       
       if(Parser!=null){
              Parser.removePropertyChangeListener(this);
       }
       Parser=aParser;
       if(Parser!=null){
              Parser.addPropertyChangeListener(this);
              
       } else {
            this.Parser.removePropertyChangeListener(this);
       }
       
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source=evt.getSource();
        String property=evt.getPropertyName();
        if(source==Parser && property.equals("Logs")){
         model.stack=Parser.getParser().getStack();
         model.updateParseLogTable(Parser.getParser().getLogs());
        }else if(source==Parser && property.equals("ParseTree")){
          // model.updateParseLogTable(Parser.getParser().getLogEntries()); 
        }
    }
    
    /**
     *
     */
    public void clear(){
        model.updateParseLogTable(new ArrayList<>());
    }
}
