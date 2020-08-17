/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Components;
import java.beans.PropertyChangeListener;

/**
 * Interface providing add and remove listener methods to be implemented by all components of the framework.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public interface IPAMOJAComponent{
    /**
     * Add a PropertyChangeListener to the listener list.
     * 
     * @param l  the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener l);

    /**
     * Remove a PropertyChangeListener from the listener list.
     * 
     * @param l the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener l);

  
}
