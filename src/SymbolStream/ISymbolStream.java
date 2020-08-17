/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SymbolStream;


/**
 *
 * @author ssanyu
 */
public interface ISymbolStream {
    
    /**
     * Returns the position of the cursor. 
     * @return 
     */
    public CPosition getPosition(); 
    /**
     * Returns the code of the symbol under the cursor.
     * @return 
     */
    public int getSymCode(); 
    /** 
     * Returns the name of the symbol under the cursor.
     * @return 
     */
    public String getSymName();
    /**
     * Returns the name of the symbol with the specified code in this stream.
     * @param aSym Code of the symbol whose name is to be returned.
     * @return The name of symbol whose code is specified.
     */
    String getSymName(int aSym);
    /**
     * Returns the value of the symbol under the cursor.
     * @return The value of the symbol under the cursor.
     */
    public String getSymValue();
    /**
     * Returns the length of the value of the symbol under the cursor.
     * @return The length of the sequence of characters represented by the 
     * symbol under the cursor.
     */
    public int getSymLength();
    /**
     * Returns the start position of the symbol under the cursor.
     * @return 
     */
    public int getSymStart();
    /**
     * Moves the cursor to the next symbol in the stream.
     */
    public void nextSym(); 
    /**
     * Moves the cursor to the specified position in this stream.
     * @param aPosition Position in the stream where the cursor is to be moved.
     */
    public void reCall(CPosition aPosition); 
    /**
     * Moves a cursor to the beginning of the stream.  
     */
    public void reSet(); 
    /**
     * Checks if this stream is exhausted.
     * @return  <code>true</code> if the cursor is at the last position in the 
     *                            stream; 
     *                  <code>false</code> otherwise.
     */
    public boolean finished();
    /**
     * Returns the number of symbols in this stream.
     * @return the number of symbols in this stream.
     */
    public int symbolCount(); 
    /**
     * Checks if the symbol with the specified code has data.
     * @param aSym Code of the symbol to be checked.
     * @return <code>true</code> if the symbol has a value that may keep
     *                           varying. 
     *                  <code>false</code> otherwise.
     */
    public boolean hasData(int aSym); 
    /**
     * Appends the specified symbol to the end of this stream.
     * @param aSym Symbol to be appended to this stream.
     */
    public void addSymbol(Symbol aSym); 
    /**
     * Creates a new line at the end of this stream.
     */
    public void addNewRow();

    /**
     *
     * @return
     */
    public int getSymbolStreamSize(); // returns Symbol stream size

    /**
     *
     * @param f
     */
    public void addFiller(int f); // adds a filler of size f to the current line

    /**
     *
     */
    public void indent(); //increase indentation of the current line

    /**
     *
     */
    public void unIndent(); // decrease indentation of the current line
}
