package GrammarNotions.ScanParse;

/**
 *
 * @author HP
 */
public interface ErrorMessages {

    /**
     *
     */
    String Messages[]={
	   "Unexpected character:",
	   "Unexpected symbol:",
           "0 expected:",
           "l expected:",
           "Letter or Digit expected:",
           "End of text expected:",
           "Character ' ' may not appear in character denotation:",
           "Character display expected:",
           "String display expected:",
		   
   };
	
	// Scan error codes

   int seWrongChar  = 0,

    // Parse error codes

    /**
     *
     */
    peWrongSymbol       = 1,

    /**
     *
     */
    peEpsExpected       = 2,

    /**
     *
     */
    peEmptyExpected     = 3,

    /**
     *
     */
    peIdExpected        = 4,

    /**
     *
     */
    peEndmarkerExpected = 5,

    /**
     *
     */
    peWrongCharacter    = 6,

    /**
     *
     */
    peCharExpected      = 7,

    /**
     *
     */
    peStringExpected    = 8;


}
