package GrammarNotions.ScanParse;

/**
 *
 * @author HP
 */
public interface IScannerBase {

    /**
     *
     * @param aText
     */
    public void setText(String aText);

    /**
     *
     */
    public void reSet();
	//void reCall(int aPos);

    /**
     *
     */
    	public void nextSym();

    /**
     *
     * @return
     */
    public boolean finished();
	
}
