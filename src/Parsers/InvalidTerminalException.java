package Parsers;

/**
 *
 * @author HP
 */
public class InvalidTerminalException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    /**
     *
     */
    public CParseError fErrorObject;
	
    /**
     *
     * @param aErrorObject
     */
    public InvalidTerminalException(CParseError aErrorObject) {
	     fErrorObject=aErrorObject;
        }
        
    @Override
        public String toString(){
        return fErrorObject.toString();
    }

}
