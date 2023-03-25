/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LL.LLTable;


import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class COutput {
    int prodNo;
    String prodName;
    boolean epsProd;
    int i; // index in a multistick
    int l; // length in a multidot
    String prod; // the actual production
    ArrayList<String> first;
    ArrayList<String> follow;

    /**
     *
     * @param prodNo
     * @param prodName
     * @param epsProd
     * @param i
     * @param l
     * @param prod
     * @param first
     * @param follow
     */
    public COutput(int prodNo, String prodName, boolean epsProd, int i, int l, String prod, ArrayList<String> first, ArrayList<String> follow) {
        this.prodNo = prodNo;
        this.prodName = prodName;
        this.epsProd = epsProd;
        this.i = i;
        this.l = l;
        this.prod = prod;
        this.first = first;
        this.follow = follow;
    }

    /**
     *
     */
    public COutput() {
        this.prodNo = 0;
        this.prodName = new String();
        this.epsProd = false;
        this.i = 0;
        this.l = 0;
        this.prod = new String();
        this.first = new ArrayList();
        this.follow = new ArrayList(); 
    }
    
    /**
     *
     * @return
     */
    public int getProdNo() {
        return prodNo;
    }

    /**
     *
     * @param prodNo
     */
    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    /**
     *
     * @return
     */
    public String getProdName() {
        return prodName;
    }

    /**
     *
     * @param prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     *
     * @return
     */
    public boolean isEpsProd() {
        return epsProd;
    }

    /**
     *
     * @param epsProd
     */
    public void setEpsProd(boolean epsProd) {
        this.epsProd = epsProd;
    }

    /**
     *
     * @return
     */
    public int getI() {
        return i;
    }

    /**
     *
     * @param i
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     *
     * @return
     */
    public int getL() {
        return l;
    }

    /**
     *
     * @param l
     */
    public void setL(int l) {
        this.l = l;
    }

    /**
     *
     * @return
     */
    public String getProd() {
        return prod;
    }

    /**
     *
     * @param prod
     */
    public void setProd(String prod) {
        this.prod = prod;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getFirst() {
        return first;
    }

    /**
     *
     * @param first
     */
    public void setFirst(ArrayList<String> first) {
        this.first = first;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getFollow() {
        return follow;
    }

    /**
     *
     * @param follow
     */
    public void setFollow(ArrayList<String> follow) {
        this.follow = follow;
    }
    
    
}
