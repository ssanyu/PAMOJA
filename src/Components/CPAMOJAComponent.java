/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;

/**
 * A PAMOJAComponent is an object which can be displayed on the screen and which a user can customize and interact with. Examples of PAMOJA components are the <code>RichTextView</code>, <code>Language</code>, and <code>DFAScanner</code>.
 * <p> 
 * The <code> CPAMOJAComponent</code> class is the supper class of all components of the framework that provides some essential functionality,
   such as, the observer mechanism, the persistency mechanism, and so on.
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CPAMOJAComponent extends JComponent implements IPAMOJAComponent {

    /**
     *
     */
    protected PropertyChangeSupport support;
   
    /**
     * Creates a new PAMOJA component.
     */
    public CPAMOJAComponent(){
       support= new PropertyChangeSupport(this);
       setPreferredSize(new Dimension(43, 17));
   }
     
   /**
     * Paints the UI of the component object. 
     * 
     * @param g the component object to paint.
     */
   @Override
    public void paintComponent(Graphics g) {
       g.setColor(Color.ORANGE);           // Set background color
       g.fillRect(0, 0, getWidth(), getHeight());  // Fill area with background.
       g.setColor(Color.BLACK);             // Restore color for drawing.
    }
    
   /**
     * Add a PropertyChangeListener to the listener list.
     * 
     * @param l  the PropertyChangeListener to be added
     */
   @Override
   public synchronized void addPropertyChangeListener(PropertyChangeListener l){
       if(support!=null){
         support.addPropertyChangeListener(l);
       } 
   }
   
   /**
     * Remove a PropertyChangeListener from the listener list.
     * 
     * @param l the PropertyChangeListener to be removed
     */
   @Override
   public synchronized void  removePropertyChangeListener(PropertyChangeListener l){
       if(support!=null){
         support.removePropertyChangeListener(l);
       }
   }
}
