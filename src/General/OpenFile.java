/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author HP
 */
public class OpenFile {
    
    private JFileChooser fc;
    private java.io.File fFile;
    private String title;
    private Component parent;
    private String textRead=new String();

    /**
     *
     * @param t
     * @param p
     * @return
     */
    public String open(String t, Component p){
        boolean status = false;
        status = openFile ();
        if(status==false){
           return textRead;
        }
      return textRead;
    }
    
    private boolean openFile(){
       
        fc = new JFileChooser ();
        fc.setDialogTitle (title);
        // Choose only files, not directories
        fc.setFileSelectionMode ( JFileChooser.FILES_ONLY);
        // Start in current directory
        fc.setCurrentDirectory (new java.io.File("."));
        int result=fc.showOpenDialog(parent);
        if (result == JFileChooser.CANCEL_OPTION) {
          return true;
        } else if (result == JFileChooser.APPROVE_OPTION) {
            fFile = fc.getSelectedFile ();
            // Invoke the readFile method in this class
            String file_string = readFile (fFile);
            if (file_string != null){
             textRead=file_string;  
            }else{
              return false;
            }        
            return false;
       }
       return true;

    }
    
    /** Use a BufferedReader wrapped around a FileReader to read
    * the text data from the given file.
     * @param file
     * @return 
   **/
  public String readFile (java.io.File file) {

    StringBuffer fileBuffer;
    String fileString=null;
    String line;

    try {
      FileReader in = new FileReader (file);
      BufferedReader dis = new BufferedReader (in);
      fileBuffer = new StringBuffer () ;
      while ((line = dis.readLine ()) != null) {
                fileBuffer.append(line).append ("\n");
      }

      in.close ();
      fileString = fileBuffer.toString ();
    }
    catch  (IOException e ) {
      return null;
    }
    return fileString;
  } // readFile
    
}
