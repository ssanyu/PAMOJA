/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.SymbolStyleCustomizer;

import java.io.*;
import java.util.*;


/** 
 * A utility class for reading the textual representation of symbol styles.
 *
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class SymbolStyleReader {


	private LinkedHashMap<String,ArrayList<Attributes>> sections;


	/**
	 * Creates an empty Ini.
	 */
	public SymbolStyleReader () {
            sections= new LinkedHashMap<String,ArrayList<Attributes>>();

	}

    /**
     *
     * @return
     */
    public LinkedHashMap<String,ArrayList<Attributes>> getSections(){
            return sections;
        }

    /**
     *
     * @param br
     * @throws IOException
     */
    public void readIniSections(BufferedReader br) throws IOException{
            String vSection="";
            String vKey="";
            String vValue="";
            String line="";
            ArrayList<Attributes> vList=new ArrayList<>();
            
            line=br.readLine();
            while(line!=null){
                if (line.length() > 0 && line.charAt(0) != '!') {
                    
                   //Iginores comments
                    if(line.charAt(0) == '!'){
                         // reads the next line
                        line = br.readLine();
                    } else if(line.charAt(0) == '[' ) {// Reads a new section if found
                       if(vSection.length()>0 && !vList.isEmpty()){
                            sections.put(vSection, vList);
                            vList=new ArrayList<>();
                       }
                       vSection=line.substring(1, line.lastIndexOf("]"));
                  
                   }else{
                       // extracts the key value parts
			 vKey = line.substring(0, line.indexOf("="));
			 vValue = line.substring(line.indexOf("=")+1);
                         vList.add(new Attributes(vKey,vValue));
                         
                   }
                 
                }
                 
                // reads the next line
		line = br.readLine();
            }
            if(vSection.length()>0 && !vList.isEmpty()){ // put values of last section
               sections.put(vSection, vList);
               vList=new ArrayList<>();
            }
            
            

    }

	

}

