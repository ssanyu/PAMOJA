/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns;

import Boxes.CPatternDef_List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JOptionPane;

/**
 *
 * @author jssanyu
 */
public class CPatterns{

    /**
     *
     */
    protected CPatternDef_List fPatternDefs;

    /**
     *
     */
    public CPatterns() {
    }

    /**
     *
     * @param fPatternDefs
     */
    public CPatterns(CPatternDef_List fPatternDefs) {
        this.fPatternDefs = fPatternDefs;
    }

    /**
     *
     * @return
     */
    public CPatternDef_List getfPatternDefs() {
        return fPatternDefs;
    }

    /**
     *
     * @param fPatternDefs
     */
    public void setfPatternDefs(CPatternDef_List fPatternDefs) {
        this.fPatternDefs = fPatternDefs;
    }

    /**
     *
     * @return
     */
    public String toText() {
        StringBuilder vString=new StringBuilder();
        vString.append(";[Pattern Definitions]\n");
        for(int i=0;i<fPatternDefs.count();i++){
            vString.append(fPatternDefs.getElt(i).getName()).append('=').append(fPatternDefs.getElt(i).toText()).append("\n");
        }
       return vString.toString();
    }
    
    /**
     *
     * @param aPatternsText
     * @return
     */
    public static CPatterns fromText(String aPatternsText){
        CPatterns vPatternsStructure=null;
        CPatternDef_List vSDList;
        CPatternsParser vParser;
        String vLine=     null;
    	BufferedReader vReader=null;

        vParser=new CPatternsParser();
        vSDList=new  CPatternDef_List();
        //Parse patternDefinitions
        try{
        	 vReader=new BufferedReader(new StringReader(aPatternsText));
        	 vLine=vReader.readLine();
        	 while( vLine!=null){
                     if (!vLine.isEmpty() && vLine.charAt(0)!= ';'){
                         
                         vParser.setText(vLine);
                         while(vParser.getSym()==CPatternsCodes.syName){
                             vParser.parse();
                             vSDList.add(vParser.fPatternDef);
                         }
                                        }
                     vLine=vReader.readLine();
                 }
                 vReader.close();
          }catch (IOException e){
              JOptionPane.showMessageDialog(null,"Failed to parse the String.");
          }
                         
          
        vPatternsStructure=new CPatterns(vSDList);       
        return vPatternsStructure;
    }
    
}
