/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.ParseLog;

import Parsers.CParseLog;
import Parsers.CParseStack;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class ParseLogTableModel extends AbstractTableModel{
        private String[] columns ={"Input Read", "Input Unread", "Stack", "Action"};
        private ArrayList<CParseLog> parseLogs; 
        private Object fData[][];

    /**
     *
     */
    public CParseStack stack;
        
        ParseLogTableModel(){
            parseLogs = new ArrayList();
        }
    
    /**
     *
     * @param row
     * @return
     */
    public CParseLog getRow(int row) {
            return parseLogs.get(row);
        }
        public int getRowCount() {
            return parseLogs.size();
        }
        public int getColumnCount() {
            return columns.length;
        }
        public String getColumnName(int col) {
            return columns[col];
        }
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            
            if (columnIndex == 0) 
                return String.class;
            else if (columnIndex == 1)
                return  Integer.class;
            else if (columnIndex == 2){
                if(stack.isRightAlign())
                    return Integer.class;
                else return String.class;
            }else return String.class;
            
        }
        
        public Object getValueAt(int row, int col) {
            CParseLog parseLog = parseLogs.get(row);
            switch(col) {
                case 0: return parseLog.getInputRead();
                        
                case 1: return parseLog.getInputUnread();
                case 2: return parseLog.getStack().toString();
                case 3: if(!parseLog.getProduction().isEmpty())
                           return parseLog.getAction()+ " "+ parseLog.getProduction();
                       else return parseLog.getAction();
            }
            return "";
        }

    /**
     *
     * @return
     */
    public  ArrayList<CParseLog> getData() {
            return parseLogs;
        }
        
    /**
     *
     * @param aParseLogs
     */
    public void updateParseLogTable(ArrayList<CParseLog> aParseLogs){
            parseLogs=aParseLogs;
            fData=new Object[getRowCount()][getColumnCount()];
            for(int i=0;i<getRowCount();i++){
                for(int j=0;j<getColumnCount();j++){
                    setValueAt(i,j);
                }
            }
            
         fireTableDataChanged();
        }
              
    /**
     *
     * @param row
     * @param col
     */
    public void setValueAt(int row, int col) {
            CParseLog parseLog = parseLogs.get(row);
            switch(col) {
                case 0: fData[row][col]=parseLog.getInputRead();
                        break; 
                case 1: fData[row][col]=parseLog.getInputUnread(); break;
                case 2: fData[row][col]=parseLog.getStack().toString(); break;
                case 3: fData[row][col]=parseLog.getAction()+ " "+ parseLog.getProduction();break;
            }
                         
            fireTableCellUpdated(row, col);
        }
        
       private String getStackString(ArrayList<Integer> s){
            String stack="";
            for(int i=0;i<s.size();i++)
                stack=stack+"  "+s.get(i);
            return stack;
    }
       
   
       
    }
    
    