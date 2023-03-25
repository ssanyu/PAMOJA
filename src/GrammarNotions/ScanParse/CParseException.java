/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrammarNotions.ScanParse;

/**
 *
 * @author ssanyu
 */
public class CParseException extends Exception implements ErrorMessages {
	private static final long serialVersionUID = 1L;

    /**
     *
     */
    public int fSymPos;

    /**
     *
     */
    public int fMsg;
	
    /**
     *
     */
    public CParseException(){}

    /**
     *
     * @param aMsg
     */
    public CParseException(String aMsg){
              super(aMsg);
        }
        
    /**
     *
     * @param aMsg
     * @param aSymPos
     */
    public CParseException(int aMsg,int aSymPos) {
	      fMsg=aMsg;
              fSymPos=aSymPos;
        }

    /**
     *
     * @return
     */
    public String handleParseError(){
              return("Parse Error:"+Messages[fMsg]+" at position "+ fSymPos);
        }
}

