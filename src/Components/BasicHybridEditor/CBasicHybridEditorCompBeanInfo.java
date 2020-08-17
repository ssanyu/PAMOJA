package Components.BasicHybridEditor;







import java.beans.*;

/**
 * This class implements Java's BeanInfo interface to restrict the BasicHybridEditor's features exposed to the public. That is, the properties, methods and events.
 *
 * 
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CBasicHybridEditorCompBeanInfo extends SimpleBeanInfo {



    // Bean descriptor information will be obtained from introspection.                          

    private static BeanDescriptor beanDescriptor = null;

    private static BeanDescriptor getBdescriptor(){
    // Here you can add code for customizing the BeanDescriptor.

     // create the bean descriptor object

        beanDescriptor = new BeanDescriptor(CBasicHybridEditorComp.class,null);

        // set the display name

        beanDescriptor.setDisplayName("BasicHybridEditor");
        beanDescriptor.setShortDescription("A component that has facilities simillar to hybridEditor including inbuilt text2AST and AST2Text capabilities");

         return beanDescriptor;     }                         

    // Property identifiers
    private static final int PROPERTY_ast = 0; 
    private static final int PROPERTY_language= 1;
    private static final int PROPERTY_nodeFactory = 2;
    private static final int PROPERTY_presentation = 3;
    
    

   // private static final int PROPERTY_grammarStructure = 2;

   

   

    // Properties information will be obtained from introspection.                      

    private static PropertyDescriptor[] properties = null;

    private static PropertyDescriptor[] getPdescriptor(){

                          



    // Here you can add code for customizing the properties array.

         properties = new PropertyDescriptor[4];



        try {
             properties[PROPERTY_ast] = new PropertyDescriptor ( "ast", CBasicHybridEditorComp.class, "getAst", "setAst" ); // NOI18N
             properties[PROPERTY_language] = new PropertyDescriptor ( "language", CBasicHybridEditorComp.class, "getLanguage", "setLanguage" ); // NOI18N
             properties[PROPERTY_nodeFactory] = new PropertyDescriptor ( "nodeFactory", CBasicHybridEditorComp.class, "getNodeFactory", "setNodeFactory" ); // NOI18N
             properties[PROPERTY_presentation] = new PropertyDescriptor ( "presentation", CBasicHybridEditorComp.class, "getPresentation", "setPresentation" ); // NOI18N
                    
        }

        catch(IntrospectionException e) {

            e.printStackTrace();

        }



        return properties;     }                     

    // EventSet identifiers

    private static final int EVENT_propertyChangeListener = 0;

    // Event set information will be obtained from introspection.                  

    private static EventSetDescriptor[] eventSets = null;

    private static EventSetDescriptor[] getEdescriptor(){

                      



    // Here you can add code for customizing the event sets array.

      eventSets = new EventSetDescriptor[1];



        try {

            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( CBasicHybridEditorComp.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N

        }

        catch(IntrospectionException e) {

            e.printStackTrace();

        }

        return eventSets;     }                 



    // Method information will be obtained from introspection.                   

    private static MethodDescriptor[] methods = null;

    private static MethodDescriptor[] getMdescriptor(){

                       



    // Here you can add code for customizing the methods array.

    

        return methods;     }                  



    private static java.awt.Image iconColor16 = null;                    

    private static java.awt.Image iconColor32 = null;

    private static java.awt.Image iconMono16 = null;

    private static java.awt.Image iconMono32 = null;                  

    private static String iconNameC16 = null;                 

    private static String iconNameC32 = null;

    private static String iconNameM16 = null;

    private static String iconNameM32 = null;               



    private static int defaultPropertyIndex = -1;               

    private static int defaultEventIndex = -1;             



    

                      



    // Here you can add code for customizing the Superclass BeanInfo.



                     

	

    /**

     * Gets the bean's <code>BeanDescriptor</code>s.

     * 

     * @return BeanDescriptor describing the editable

     * properties of this bean.  May return null if the

     * information should be obtained by automatic analysis.

     */

    public BeanDescriptor getBeanDescriptor() {

	return getBdescriptor();

    }



    /**

     * Gets the bean's <code>PropertyDescriptor</code>s.

     * 

     * @return An array of PropertyDescriptors describing the editable

     * properties supported by this bean.  May return null if the

     * information should be obtained by automatic analysis.

     * <p>

     * If a property is indexed, then its entry in the result array will

     * belong to the IndexedPropertyDescriptor subclass of PropertyDescriptor.

     * A client of getPropertyDescriptors can use "instanceof" to check

     * if a given PropertyDescriptor is an IndexedPropertyDescriptor.

     */

    public PropertyDescriptor[] getPropertyDescriptors() {

	return getPdescriptor();

    }



    /**

     * Gets the bean's <code>EventSetDescriptor</code>s.

     * 

     * @return  An array of EventSetDescriptors describing the kinds of 

     * events fired by this bean.  May return null if the information

     * should be obtained by automatic analysis.

     */

    public EventSetDescriptor[] getEventSetDescriptors() {

	return getEdescriptor();

    }



    /**

     * Gets the bean's <code>MethodDescriptor</code>s.

     * 

     * @return  An array of MethodDescriptors describing the methods 

     * implemented by this bean.  May return null if the information

     * should be obtained by automatic analysis.

     */

    public MethodDescriptor[] getMethodDescriptors() {

	return getMdescriptor();

    }



    /**

     * A bean may have a "default" property that is the property that will

     * mostly commonly be initially chosen for update by human's who are 

     * customizing the bean.

     * @return  Index of default property in the PropertyDescriptor array

     * 		returned by getPropertyDescriptors.

     * <P>	Returns -1 if there is no default property.

     */

    public int getDefaultPropertyIndex() {

        return defaultPropertyIndex;

    }



    /**

     * A bean may have a "default" event that is the event that will

     * mostly commonly be used by human's when using the bean. 

     * @return Index of default event in the EventSetDescriptor array

     *		returned by getEventSetDescriptors.

     * <P>	Returns -1 if there is no default event.

     */

    public int getDefaultEventIndex() {

        return defaultEventIndex;

    }



    /**

     * This method returns an image object that can be used to

     * represent the bean in toolboxes, toolbars, etc.   Icon images

     * will typically be GIFs, but may in future include other formats.

     * <p>

     * Beans aren't required to provide icons and may return null from

     * this method.

     * <p>

     * There are four possible flavors of icons (16x16 color,

     * 32x32 color, 16x16 mono, 32x32 mono).  If a bean choses to only

     * support a single icon we recommend supporting 16x16 color.

     * <p>

     * We recommend that icons have a "transparent" background

     * so they can be rendered onto an existing background.

     *
     * @param iconType
     * @return  An image object representing the requested icon.  May

     *    return null if no suitable icon is available.

     */

     

    @Override

  public java.awt.Image getIcon(int iconType) {

                return null;

        }



 

   

   

   

   

}



