/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ActionHistory {
    private ArrayList<Action> history=new ArrayList();
    
    /**
     *
     * @param action
     */
    public void  push(Action action){
        history.add(action);
    }
    
    /**
     *
     * @return
     */
    public Action pop(){
        return history.remove(history.size()-1);
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return history.isEmpty();
    }
    
    /**
     *
     * @return
     */
    public int size(){
        return history.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public Action peek(int i){
        return history.get(i);
    }
}
