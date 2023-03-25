/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

/**
 *
 * @author HP
 */
public abstract class Action {

    /**
     *
     */
    protected Backup backup;
    
    /**
     *
     * @return
     */
    public Backup getBackup() {
        return backup;
    }

    /**
     *
     * @param backup
     */
    public void setBackup(Backup backup) {
        this.backup = backup;
    }
    
    /**
     *
     */
    public abstract void backup();

    /**
     *
     * @return
     */
    public abstract boolean execute();

    /**
     *
     */
    public abstract void undo();
}
