/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableGenerators.LR;

/**
 *
 * @author jssanyu
 */
public class Shift extends Move {

    /**
     *
     */
    public boolean NT=false;
    /**
     *
     */
    public int state;
    
    /**
     *
     */
    public Shift(){}

    /**
     *
     * @param aState
     */
    public Shift(int aState){
        state=aState;
    }

    /**
     *
     * @return
     */
    public int getState() {
        return state;
    }
    
    @Override
    public String toString(){
        if(NT==false)
          return "S("+ state+")";
        else 
         return ""+state+"";   
    }
    
}
