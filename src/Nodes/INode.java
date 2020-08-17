/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes;

/**
 *
 * @author ssanyu
 */
/* Level 0: Provides access to methods for general tree operations */
public interface INode {
    // labelling

    /**
     *
     * @return
     */
        int sortCode();

    /**
     *
     * @return
     */
    String sortLabel();

    // Structure

    /**
     *
     * @param i
     * @param aNode
     * @return
     */
        boolean canSetNode(int i, CNode aNode);

    /**
     *
     * @return
     */
    CNodeKind kind();

    /**
     *
     * @param i
     * @return
     */
    CNode getNode(int i);

    /**
     *
     * @param i
     * @param aNode
     */
    void setNode(int i, CNode aNode);

    /**
     *
     * @return
     */
    int count();
   
    //Data

    /**
     *
     * @param i
     * @param aData
     * @return
     */
        boolean canSetData(int i,String aData);

    /**
     *
     * @return
     */
    int dataCount();

    /**
     *
     * @param i
     * @return
     */
    String getData(int i);

    /**
     *
     * @param i
     * @param aData
     */
    void setData(int i, String aData);

    //text

    /**
     *
     * @return
     */
        boolean hasText();

    /**
     *
     * @return
     */
    String getText();

    //Annotation

    /**
     *
     * @param aName
     * @param aObject
     * @return
     */
        boolean canSetAnno(String aName, Object aObject);

    /**
     *
     * @param aName
     * @return
     */
    boolean hasAnno(String aName);

    /**
     *
     * @param aName
     * @return
     */
    Object getAnno(String aName);

    /**
     *
     * @param aName
     * @param aObject
     */
    void setAnno(String aName, Object aObject);

    /**
     *
     */
    void clearAllAnno();
   
    // Operations

    /**
     *
     * @return
     */
        CNode copyTree();
    
    
    //getter/setter
  //  CNode getNode();
  //  void  setNode(CNode aNode);
}
