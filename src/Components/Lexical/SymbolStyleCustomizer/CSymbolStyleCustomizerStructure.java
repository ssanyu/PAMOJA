/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components.Lexical.SymbolStyleCustomizer;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Defines the structural representation of a symbolstylecustomizer.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSymbolStyleCustomizerStructure{
    private ArrayList<CategoryAttributes> FCategoryToAttributes; //<Category,<color,font>>
    private ArrayList<SymbolCategory> FSymbolToCategory; //<Symbol,Category>
   
    /**
     * Creates a new SymbolStyleCustomizer object.
     */
    public CSymbolStyleCustomizerStructure(){
       FCategoryToAttributes=new ArrayList<>();
       FSymbolToCategory=new ArrayList<>();
       initArrayLists();
    }


    /**
     * Creates a new SymbolStyleCustomizer object with the specified information.
     * @param aCategoryToAttributes
     * @param aSymbolToCategory
     */
        public CSymbolStyleCustomizerStructure(ArrayList<CategoryAttributes> aCategoryToAttributes,ArrayList<SymbolCategory> aSymbolToCategory){
         FCategoryToAttributes = aCategoryToAttributes;
         FSymbolToCategory = aSymbolToCategory;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<CategoryAttributes> getFCategoryToAttributes() {
        return FCategoryToAttributes;
    }

    /**
     *
     * @param FCategoryToAttributes
     */
    public void setFCategoryToAttributes(ArrayList<CategoryAttributes> FCategoryToAttributes) {
        this.FCategoryToAttributes = FCategoryToAttributes;
    }

    /**
     *
     * @return
     */
    public ArrayList<SymbolCategory> getFSymbolToCategory() {
        return FSymbolToCategory;
    }

    /**
     *
     * @param FSymbolToCategory
     */
    public void setFSymbolToCategory(ArrayList<SymbolCategory> FSymbolToCategory) {
        this.FSymbolToCategory = FSymbolToCategory;
    }

    /**
     *
     */
    public final void initArrayLists(){
       addCategoryAndAttributes("Error",new Font("Monospaced",Font.PLAIN,13),Color.RED);
       addCategoryAndAttributes("Hole",new Font("Monospaced",Font.PLAIN,13),Color.RED);
       addCategoryAndAttributes("Default",new Font("Monospaced",Font.PLAIN,13),Color.BLACK);
       addCategoryAndAttributes("Identifier",new Font("Monospaced",Font.PLAIN,13),Color.GREEN);
       addCategoryAndAttributes("Keyword",new Font("Monospaced",Font.PLAIN,13),Color.blue);
       addCategoryAndAttributes("Number",new Font("Monospaced",Font.PLAIN,13),Color.MAGENTA);
       addCategoryAndAttributes("Operator",new Font("Monospaced",Font.PLAIN,13),Color.BLACK);
       
       addSymbolAndCategory("Error","Error");
       addSymbolAndCategory("endmarker","Default");
        
    }
    
    /**
     *
     * @param aCategory
     * @param aFont
     * @param aColor
     */
    public final void addCategoryAndAttributes(String aCategory,Font aFont,Color aColor){
        if(indexOfCategory(aCategory)==-1){ 
             FCategoryToAttributes.add(new CategoryAttributes(aCategory,new FCAttributes(aFont,aColor)));
        }
        
    }

    /**
     *
     * @param aSymbol
     * @param aCategory
     */
    public final void addSymbolAndCategory(String aSymbol,String aCategory){
       if(!aCategory.isEmpty() ||(indexOfCategory(aCategory)!=-1 && indexOfSymbol(aSymbol)==-1)){
           FSymbolToCategory.add(new SymbolCategory(aSymbol,aCategory));
       }
    }

    /**
     *
     * @param aSymbol
     * @return
     */
    public int indexOfSymbol(String aSymbol){
        int i = FSymbolToCategory.size() - 1;
        while ( (i != -1) && !aSymbol.equals(FSymbolToCategory.get(i).FSymbolName) ){
           i--;
        }
        return i;
    }

    /**
     *
     * @param aCategory
     * @return
     */
    public int indexOfCategory(String aCategory){
        int i = FCategoryToAttributes.size() - 1;
        while ( (i != -1) && !aCategory.equals(FCategoryToAttributes.get(i).FCategory) ){
           i--;
        }
        return i;
    }

    /**
     * Returns a string representation of a symbolstylecustomizer.
     * @return the string representation of a symbolstylecustomizer.
     */
    public String toText(){
        StringBuilder vString=new StringBuilder();

        vString.append("\n!Category Attributes\n\n");
        for(int i=0;i<FCategoryToAttributes.size();i++){
            vString.append(FCategoryToAttributes.get(i).toString());
            vString.append("\n");
        }

        vString.append("\n!Symbol To Category\n");
        vString.append("[SymbolCategory]\n\n");
        for(int i=0;i<FSymbolToCategory.size();i++){
            vString.append(FSymbolToCategory.get(i).toString());
            vString.append("\n");
        }
        return vString.toString();
    }

    /**
     * Returns the structural representation of the specified symbolstylecustomizer text.
     * @param aSymbolStyleCustomizerText the specified symbolstylecustomizer text.
     * @return the structural representation of the specified symbolstylecustomizer text.
     */
    public static CSymbolStyleCustomizerStructure fromText(String aSymbolStyleCustomizerText){
        CSymbolStyleCustomizerStructure vSymbolStyleCustomizerStructure;
        BufferedReader vReader;
        SymbolStyleReader vIniReader;

        vSymbolStyleCustomizerStructure=new  CSymbolStyleCustomizerStructure();
        try{

        	vReader=new BufferedReader(new StringReader(aSymbolStyleCustomizerText));
                vIniReader=new SymbolStyleReader();
                vIniReader.readIniSections(vReader);
                vReader.close();

        } catch (IOException e) {
  	        //System.out.println("Could not read string");
	        throw new Error();
        }
        LinkedHashMap<String,ArrayList<Attributes>> vSections=new LinkedHashMap<String,ArrayList<Attributes>>();
        vSections=vIniReader.getSections();
        ArrayList<Attributes> vAttributes=new ArrayList<>();
        String vSection;
        Font vFont;
        Color vColor;

        Object vList[]=vSections.keySet().toArray();
        for(int k=0;k<vSections.size()-1;k++){// Get Category Attributes
             //get Category
             vSection=(String)vList[k];
             vAttributes=vSections.get((String)vList[k]);
             //create a Font Object
             String vFontValue=vAttributes.get(0).Value;
             String[] tempFont=vFontValue.split(",");
             int fontStyle;
             if(tempFont[2].equalsIgnoreCase("plain"))
                  fontStyle=0;
             else if(tempFont[2].equalsIgnoreCase("bold"))
                  fontStyle=1;
             else if(tempFont[2].equalsIgnoreCase("italic"))
                 fontStyle=2;
             else fontStyle=3;
             vFont=new Font(tempFont[0],fontStyle,Integer.parseInt(tempFont[1]));

             //Create a Color object
             String vColorValue=vAttributes.get(1).Value;
             String[] tempColor=vColorValue.split(",");//r=0,g=0,b=0
             String[] tempR=tempColor[0].split("=");
             int r=Integer.parseInt(tempR[1]);
             String[] tempG=tempColor[1].split("=");
             int g=Integer.parseInt(tempG[1]);
             String[] tempB=tempColor[2].split("=");
             int b=Integer.parseInt(tempB[1]);
             vColor=new Color(r,g,b);
             
             // Add Category attributes
             vSymbolStyleCustomizerStructure.addCategoryAndAttributes(vSection, vFont, vColor);

         }
         // Get Symbol Categories
          vSection=(String)vList[vSections.size()-1];
          vAttributes=vSections.get(vSection);
          for(int i=0;i<vAttributes.size();i++){
               String vSymbol=vAttributes.get(i).Key;
               String vCategory=vAttributes.get(i).Value;
               vSymbolStyleCustomizerStructure.addSymbolAndCategory(vSymbol, vCategory);
            
          }
        
       
        return vSymbolStyleCustomizerStructure;
    }

    /**
     * Returns the font of a given symbol.
     * @param aSymbolName the name of a symbol
     * @return the Font returned
     */
    public Font symbolNameToFont(String aSymbolName){
        String vCategory=null;
        Font vFont=null;
        if(aSymbolName!=null){
            vCategory=FSymbolToCategory.get(indexOfSymbol(aSymbolName)).FCategory;
        } 
        
        if(vCategory!=null){
           vFont=FCategoryToAttributes.get(indexOfCategory(vCategory)).FAttributes.fFont;
        }
       return vFont;
    }
     
    /**
     * Returns the color of a given symbol
     * @param aSymbolName the name of a symbol
     * @return the Color returned.
     */
    public Color symbolNameToColor(String aSymbolName){
        String vCategory;
        Color vColor=Color.BLACK;
        int vIndex=indexOfSymbol(aSymbolName);
        vCategory=FSymbolToCategory.get(vIndex).FCategory;
        if(vCategory!=null){
           vColor=FCategoryToAttributes.get(indexOfCategory(vCategory)).FAttributes.fColor;
        }
        return vColor;
   }
}

