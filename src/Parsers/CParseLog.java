/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parsers;

/**
 *
 * @author HP
 */
public class CParseLog {
    String inputRead;
    String inputUnread;
    CParseStack stack;
    String action;
    String production=new String();

    /**
     *
     * @param inputRead
     * @param inputUnread
     * @param stack
     * @param action
     * @param production
     */
    public CParseLog(String inputRead, String inputUnread, CParseStack stack, String action, String production) {
        this.inputRead = inputRead;
        this.inputUnread = inputUnread;
        this.stack = stack;
        this.action = action;
        this.production = production;
    }

    /**
     *
     * @param inputRead
     * @param inputUnread
     * @param stack
     * @param action
     */
    public CParseLog(String inputRead, String inputUnread, CParseStack stack, String action) {
        this.inputRead = inputRead;
        this.inputUnread = inputUnread;
        this.stack = stack;
        this.action = action;
    }

    /**
     *
     * @return
     */
    public String getInputRead() {
        return inputRead;
    }

    /**
     *
     * @param inputRead
     */
    public void setInputRead(String inputRead) {
        this.inputRead = inputRead;
    }

    /**
     *
     * @return
     */
    public String getInputUnread() {
        return inputUnread;
    }

    /**
     *
     * @param inputUnread
     */
    public void setInputUnread(String inputUnread) {
        this.inputUnread = inputUnread;
    }

    /**
     *
     * @return
     */
    public CParseStack getStack() {
        return stack;
    }

    /**
     *
     * @param stack
     */
    public void setStack(CParseStack stack) {
        this.stack = stack;
    }

    /**
     *
     * @return
     */
    public String getProduction() {
        return production;
    }

    /**
     *
     * @param production
     */
    public void setProduction(String production) {
        this.production = production;
    }

    /**
     *
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     *
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "CParseLog{" + "inputRead=" + inputRead + ", inputUnread=" + inputUnread + ", stack=" + stack + ", action=" + action + ", production=" + production + '}';
    }

    

        
    
}
