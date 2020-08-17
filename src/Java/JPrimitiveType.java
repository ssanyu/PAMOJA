/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Java;

import Nodes.CNode;
import Nodes.CNodeKind;

/**
 *
 * @author jssanyu
 */
public class JPrimitiveType extends JType {
    
    /**
     *
     */
    public static JPrimitiveType NULL = new JPrimitiveType(Primitive.Null);

    /**
     *
     */
    public static JPrimitiveType BOOLEAN = new JPrimitiveType(Primitive.Boolean);

    /**
     *
     */
    public static JPrimitiveType CHAR = new JPrimitiveType(Primitive.Char);

    /**
     *
     */
    public static JPrimitiveType BYTE = new JPrimitiveType(Primitive.Byte);

    /**
     *
     */
    public static JPrimitiveType SHORT = new JPrimitiveType(Primitive.Short);

    /**
     *
     */
    public static JPrimitiveType INT = new JPrimitiveType(Primitive.Int);

    /**
     *
     */
    public static JPrimitiveType LONG = new JPrimitiveType(Primitive.Long);

    /**
     *
     */
    public static JPrimitiveType FLOAT = new JPrimitiveType(Primitive.Float);

    /**
     *
     */
    public static JPrimitiveType DOUBLE = new JPrimitiveType(Primitive.Double);

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return JASTNodeCodes.scPrimitiveType;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "PrimitiveType";
    }

    /**
     *
     * @return
     */
    @Override
    public CNodeKind kind() {
        return CNodeKind.TUPLE;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CNode getNode(int i) {
        assert false: String.format("CNode.getNode().pre failed: i=%d, count=%d .",i,count());
        return null;
    }

    @Override
    public void setNode(int i, CNode aNode) {
       assert false: String.format("CNode.setNode().pre failed: i=%d, count=%d .",i,count());
    }

    @Override
    public int count() {
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int dataCount() {
        return 1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i) {
        if(i==0)
            return type.toString();
        else return super.getData(i);
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i, String aData) {
        if(i==0)
           type=null;
        else super.setData(i, aData);
    }
    
    /**
     *
     */
    public enum Primitive {

        /**
         *
         */
        Null,

        /**
         *
         */
        Boolean,

        /**
         *
         */
        Char,

        /**
         *
         */
        Byte,

        /**
         *
         */
        Short,

        /**
         *
         */
        Int,

        /**
         *
         */
        Long,

        /**
         *
         */
        Float,

        /**
         *
         */
        Double
    }

    /**
     *
     */
    public Primitive type;

    private JPrimitiveType(Primitive type) {
        this.type = type;
    }

 
    
}
