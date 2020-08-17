/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.ScanParse;

/**
 *
 * @author ssanyu
 */
public class CScanException extends Exception implements ErrorMessages {
	
	private static final long serialVersionUID = 1L;
	  private int fPos;

    /**
     *
     */
    public int fMsg;

    /**
     *
     */
    public char fChar;
        
    /**
     *
     */
    public CScanException(){
              super();
          }
          
    /**
     *
     * @param aMsg
     */
    public CScanException(String aMsg){
              super(aMsg);
          }
          
    /**
     *
     * @param aMsg
     * @param aChar
     */
    public CScanException(int aMsg, char aChar){
                    fMsg=aMsg;
                    fChar=aChar;
          }

    /**
     *
     * @param aMsg
     * @param aChar
     * @param aPos
     */
    public CScanException(int aMsg,char aChar, int aPos) {
		   
		    fMsg=aMsg;
                    fChar=aChar;
                    fPos=aPos;
	  }

    /**
     *
     * @param aMsg
     * @param aChar
     * @param aPos
     * @return
     */
    public String handleScanError(int aMsg,char aChar,int aPos){
                 return("Scan Error:"+Messages[aMsg]+":"+ aChar+" at position "+ aPos);
       
        
    }
}


