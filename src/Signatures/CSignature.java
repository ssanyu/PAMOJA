package Signatures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class CSignature{

    /**
     *
     */
    protected CSortCtxt fBaseSortCtxt;

    /**
     *
     */
    protected CSortCtxt fUserSortCtxt;
    
    /**
     *
     */
    public CSignature(){
      initBaseSortCtxt();
      fUserSortCtxt=new CSortCtxt(new CSortDefinition_List());
    }
    
    private void initBaseSortCtxt(){
      CSortDefinition vCtxt=new CSortDefinition("Context",null);
      CSortDefinition vCtxtTuple=
          new CSortDefinition(
              "ContextTuple",
              new CSortDefBody(
                  new CMemberDefinition_List(),
                  new CSortUse("Context",vCtxt)
              )
          );
      CSortDefinition vTerm=new CSortDefinition("Term",null);
      CSortDefinition vTermTuple=
          new CSortDefinition(
              "TermTuple",
              new CSortDefBody(
                  new CMemberDefinition_List(),
                  new CSortUse("Term",vTerm)
              )
          );
      CSortDefinition vData=new CSortDefinition("Data",null);
      CSortDefinition vString=
          new CSortDefinition(
              "String",
              new CSortDefBody(
                  new CMemberDefinition_List(),
                  new CSortUse("Data",vData)
              )
          );
      CSortDefinition vInt=
          new CSortDefinition(
              "int",
              new CSortDefBody(
                  new CMemberDefinition_List(),
                  new CSortUse("Data",vData)
              )
          );
      
      CMemberDefinition_List memList=new CMemberDefinition_List();
      memList.add(
         new CMemberDefinition(
             "name",
             new CMemberDefBody(
                 new CSortUse(
                     "String",
                     vString
                 ),
                ' ',
                'D'
            )
        )
      );
      CSortDefinition vItem=
          new CSortDefinition(
              "Item",
              new CSortDefBody(
                  memList,
                  new CSortUse("Ctxt",vCtxt)
              )
          );
      
      
     
      CSortDefinition_List vList=new CSortDefinition_List();
      
      vList.addContext(vData);
      vList.addContext(vCtxt);
      vList.addContext(vCtxtTuple);
      vList.addContext(vTerm);
      vList.addContext(vTermTuple);
      vList.addContext(vItem);
      vList.addContext(vString);
      vList.addContext(vInt);
      
      fBaseSortCtxt=new CSortCtxt(vList); 
    }
    
    /**
     *
     * @param aUserSortCtxt
     */
    public CSignature(CSortCtxt aUserSortCtxt){
        initBaseSortCtxt();
        fUserSortCtxt=aUserSortCtxt;
    }
    
    private void setUserSortCtxt(CSortCtxt aUserSortCtxt){
          fUserSortCtxt=aUserSortCtxt;
    }
    
    /**
     *
     * @return
     */
    public CSortCtxt getUserSortCtxt(){
        return fUserSortCtxt;
    }
    
    /**
     *
     * @return
     */
    public CSortCtxt getBaseSortCtxt(){
        return fBaseSortCtxt;
    }
    
    /**
     *
     * @param aSignatureText
     * @return
     */
    public static CSignature fromText(String aSignatureText){
        CSortDefinition_List vSDList;
        CSignatureParser vParser;
        String vLine=null;
    	BufferedReader vReader=null;

        vParser=new CSignatureParser();
        vSDList=new CSortDefinition_List();
        //Parse SortDefinitions
        try{
        	 vReader=new BufferedReader(new StringReader(aSignatureText));
        	 vLine=vReader.readLine();
        	 while( vLine!=null){
                     if (!vLine.isEmpty() && vLine.charAt(0)!= ';'){
                         vParser.setText(vLine);
                         while(vParser.getSym()==CSignatureCodes.syId){
                             vParser.parse();
                             vSDList.addContext(vParser.fSortDefn);
                         }
                     }
                     vLine=vReader.readLine();
                 }
                 vReader.close();
          }catch (IOException e){
              JOptionPane.showMessageDialog(null,"Failed to parse the String.");
          }
                         
          return new CSignature(new CSortCtxt(vSDList));      
               
        }
        	
    /**
     *
     * @return
     */
    public String toText(){
         CSortDefBody vBody;
         StringBuilder vResult=new StringBuilder();
                   
         for (int i=0;i<fUserSortCtxt.getSortDefs().contextCount();i++){
             vBody=fUserSortCtxt.getSortDefs().getElt(i).getBody();
             if(vBody!=null){
                vResult.append(fUserSortCtxt.getSortDefs().getElt(i).getName());
                vResult.append(" ");
                vResult.append('=');
                vResult.append(" ");
                vResult.append(fUserSortCtxt.getSortDefs().getElt(i).getBody().toText());
                vResult.append('\n');
             }else{
                vResult.append(fUserSortCtxt.getSortDefs().getElt(i).getName());
                vResult.append(" ");
                vResult.append('=');
                vResult.append(" ");
                vResult.append("<");
                vResult.append('\n'); 
             }
         }
         return vResult.toString();
     }   
        
}