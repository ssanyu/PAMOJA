/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Abstract.PanelTree;

/**
 * Enumeration for the state of a panel. 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public enum CPanelState {

    /**
     * All panels inside the selection
     */
    INNER,  

    /**
     * All panels outside the selection
     */
        OUTER, 

    /**
     * Selected panel
     */
        THIS,  
}
