/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CParseStack {
    private ArrayList<Object> list;
    private boolean rightAlign;

    /**
     *
     */
    public CParseStack() {
        list=new ArrayList<>();
        rightAlign=false;
    }

    /**
     *
     * @return
     */
    public boolean isRightAlign() {
        return rightAlign;
    }

    /**
     *
     * @param rightAlign
     */
    public void setRightAlign(boolean rightAlign) {
        this.rightAlign = rightAlign;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Object> getList() {
        return list;
    }

    /**
     *
     * @param list
     */
    public void setList(ArrayList<Object> list) {
        this.list = list;
    }

    /**
     *
     */
    public void pop(){
        list.remove(0);
    }

    /**
     *
     * @param i
     */
    public void pop(int i){
        list.remove(i);
    }

    /**
     *
     * @param o
     */
    public void push(Object o){
        list.add(o);
    }

    /**
     *
     * @param i
     * @param o
     */
    public void push(int i, Object o){
        list.add(i,o);
    }

    /**
     *
     * @param s
     */
    public void pushAll(CParseStack s){
        list.addAll(s.getList());
    }

    /**
     *
     * @return
     */
    public Object peek(){
        return list.get(0);
    }

    /**
     *
     * @param i
     * @return
     */
    public Object peek(int i){
        return list.get(i);
    }

    /**
     *
     * @return
     */
    public int size(){
        return list.size();
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    @Override
    public String toString() {
        String s="";
        for(int i=0;i<list.size();i++)
        s=s+" "+list.get(i);
        return s;
    }
    
    
}
