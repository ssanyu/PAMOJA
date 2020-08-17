/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.Grammar;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 *
 * @author ssanyu
 */
public class GrammarReader {


	private static LinkedHashMap<String,LinkedHashMap<String,String>> sections;


	/**
	 * Creates an empty Ini.
	 */
	public GrammarReader () {
            sections= new LinkedHashMap<>();

	}

    /**
     *
     * @return
     */
    public static HashMap getSections(){
            return sections;
        }

    /**
     *
     * @param br
     * @throws IOException
     */
    public static void readIniSections(BufferedReader br) throws IOException{
            String vSection="";
                    
            String line;
            LinkedHashMap<String,String> map=new LinkedHashMap<>();
            sections= new LinkedHashMap<>();
            
            line=br.readLine();
            while(line!=null){
                if (!line.isEmpty() && line.charAt(0)!= '!' && line.charAt(0)!= ' ') {
                  
                   if(line.charAt(0) == '[' ) {// Reads a new section if found
                       if(!vSection.isEmpty() && !map.isEmpty()){
                            sections.put(vSection, map);
                            map=new LinkedHashMap<>();
                            
                       }

                       vSection=line.substring(1, line.lastIndexOf(']'));
                       
                      
                   }else{
                       // extracts the key value parts
                         String vKey = line.substring(0,line.indexOf('='));
			  String vValue = line.substring(line.indexOf('=')+1);
                         map.put(vKey,vValue);
                   }
                }
                 
                // reads the next line
		line = br.readLine();
            }
           if(!vSection.isEmpty() && !map.isEmpty()){ // put values of last section
               sections.put(vSection, map);
               // map=new LinkedHashMap<>();
            }
      }

    /**
	 * String representation of the Ini file.
	 *
	 * @return String representation of the Ini file.
	 */
    @Override
    public String toString () {
		String toReturn = "";
		Object[] values = sections.keySet().toArray();

		for (int i = 0; i < values.length; i++) {
			toReturn +=getSectionString((String) values[i])+ "\n";
		}

		return toReturn;
	}

    /**
     *
     * @param title
     * @return
     */
    public String getSectionString (String title) {
                LinkedHashMap<String,String> vMap=sections.get(title);
                Object [] values =vMap.keySet().toArray();
                String s="";
                s+='['+title+']'+"\n";
                for (int i = 0; i < values.length; i++) {
                    s+=(String) values[i]+'='+vMap.get((String) values[i])+"\n";
                }
                return s;
	}

    /**
     *
     * @param title
     * @return
     */
    public static LinkedHashMap<String,String>getSection(String title){
        LinkedHashMap<String,String> vMap= sections.get(title);
                return vMap;
    }


}

