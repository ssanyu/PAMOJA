/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodes;

/**
 *
 * @author Ssanyu
 */
public interface INodeOption extends INode{

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
     */
    public void setAbsent(); 

    /**
     *
     * @param aNode
     */
    public void setPresent(CNode aNode);

    /**
     *
     * @return
     */
    public boolean isPresent();

    /**
     *
     * @return
     */
    public CNode getArg(); 
  
}
