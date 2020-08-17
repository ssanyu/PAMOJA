/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes;

/**
 *
 * @author ssanyu
 */
public interface INodeList extends INode {

    /**
     *
     * @return
     */
    String eltSortLabel();

    /**
     *
     * @return
     */
    int eltSortCode();

    /**
     *
     * @return
     */
    Class eltClass();

    /**
     *
     * @param aNode
     * @return
     */
    int indexOf(CNode aNode);

    /**
     *
     * @param aNode
     * @return
     */
    boolean canAdd(CNode aNode);

    /**
     *
     * @param i
     * @return
     */
    boolean canDelete(int i);

    /**
     *
     * @param i
     * @param aNode
     * @return
     */
    boolean canInsert(int i, CNode aNode);

    /**
     *
     * @param aFro
     * @param aTo
     * @return
     */
    boolean canMove(int aFro, int aTo);

    /**
     *
     * @param aNode
     * @return
     */
    boolean canRemove(CNode aNode);

    /**
     *
     * @param i
     * @param j
     * @return
     */
    boolean canSwap(int i, int j);

    /**
     *
     * @param aNode
     */
    void add(CNode aNode);

    /**
     *
     * @param i
     */
    void delete(int i);

    /**
     *
     * @param i
     * @param aNode
     */
    void insert(int i , CNode aNode);

    /**
     *
     * @param aFrom
     * @param aTo
     */
    void move(int aFrom, int aTo);

    /**
     *
     * @param aNode
     */
    void remove(CNode aNode);

    /**
     *
     * @param i
     * @param j
     */
    void swap(int i, int j);

}
