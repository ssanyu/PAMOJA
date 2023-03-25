/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Concrete.Grammar;


import GrammarNotions.RegExpr.CLexemeDef;
import GrammarNotions.RegExpr.CLexemeDef_List;
import GrammarNotions.RegExpr.CRE;
import GrammarNotions.RegExpr.CRE_Empty;
import GrammarNotions.ScanParse.CGrammarParser;
import GrammarNotions.ScanParse.CGrammarScanner;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/** 
 * An implementation of a table model for lexeme definitions of a grammar.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CLexemeTableModel extends AbstractTableModel {

    /**
     *
     */
    protected String[] fColumnNames={"Name","Regular Expression",""};

    /**
     *
     */
    protected ArrayList<Symbols> fData;
    private Class[] types = new Class [] {
             String.class,String.class,Object.class
    };
    private  Symbols symbol;

    /**
     *
     */
    public  CLexemeDef_List fDefs;

    /**
     *
     */
    public static final int NAME_INDEX=0;

    /**
     *
     */
    public static final int VALUE_INDEX=1;

    /**
     *
     */
    public static final int HIDDEN_INDEX=2;

    /**
     *
     */
    public CLexemeTableModel(){
      fData=new ArrayList<>();
    }

    @Override
    public int getColumnCount() {
       return fColumnNames.length;
    }

    @Override
    public int getRowCount() {
       return fData==null ? 0 : fData.size();
    }

    @Override
    public String getColumnName(int col) {
       return fColumnNames[col];
    }
    @Override
    public Class getColumnClass(int c) {
       return types[c];
    }
    @Override
    public Object getValueAt(int row, int col) {
        symbol=(Symbols) fData.get(row);
        switch(col){
            case NAME_INDEX:
                return  symbol.name;
            case VALUE_INDEX:
                return symbol.value;
            default: return new Object();
        }
    }
    /*
     * Don't need to implement this method unless your table's
     * editable.
    */
    @Override
    public boolean isCellEditable(int row, int col) {
      if(col==HIDDEN_INDEX) return false;
              else return true;

    }

  /*
   * Don't need to implement this method unless your table's
   * data can change.
  */
  @Override
    public void setValueAt(Object value, int row, int col) {
       symbol=(Symbols)fData.get(row);
       switch(col){
           case 0: symbol.name=(String)value; break;
           case 1: symbol.value=(String)value; break;
           default:  JOptionPane.showMessageDialog(null,"Invalid Index");
       }

       fireTableCellUpdated(row, col);
    }

    /**
     *
     * @return
     */
    public boolean hasEmptyRow(){
       if (fData.isEmpty()) return false;
       symbol=(Symbols)fData.get(fData.size()-1);
       if(symbol.name.trim().equals("")&& symbol.value.trim().equals("")){
           return true;
       }else return false;
    }

    /**
     *
     */
    public void addEmptyRow(){
        fData.add(new Symbols());
        fireTableRowsInserted(fData.size()-1,fData.size()-1);
    }

    /**
     *
     * @param aDefs
     */
    public void updateLexemeTable(CLexemeDef_List aDefs){

        fDefs=aDefs;
        int vRows=fDefs.count();
        fData=new ArrayList<>(vRows);
        for(int i=0;i<vRows;i++){
            fData.add(i,new Symbols(fDefs.getElt(i).getName(),fDefs.getElt(i).getBody().toText()));

        }
        fireTableDataChanged();
    }
    
    /**
     *
     * @param aData
     */
    public void updateLexemeDefList(ArrayList<Symbols> aData){
       boolean vSuccess;
       CRE vRE;
       CLexemeDef_List vList = new CLexemeDef_List();
       String vName,vValue;
       CGrammarScanner vScanner;
       CGrammarParser vParser;

       vScanner=new CGrammarScanner();
       vParser=new CGrammarParser();
       vParser.fScanner=vScanner;
       fData=aData;
       for(int i=0;i<fData.size();i++){
           vName=((Symbols)fData.get(i)).name;
           vValue=((Symbols)fData.get(i)).value;
           vParser.fScanner.setText(vValue);
           vParser.reSet();
           vSuccess=vParser.parseRE();
           if(vSuccess){
               vRE=vParser.fRETree;
           }else{
               JOptionPane.showMessageDialog(null,"Error in parsing the definition of lexeme:"+vName);
               vRE=new CRE_Empty();
           }
            vList.add(new CLexemeDef(vName,vRE));
        }
        fDefs=vList;
        
    }
/*    public void setTableModel(){
        int r=4;
        int x=3;
        //fData=new Vector();
        dvd.title="Hello There";
        dvd.artist="Milly d";
        for(int i=0;i<r;i++){
             fData.add(i,dvd);

        }
     fireTableDataChanged();
    }*/


}
