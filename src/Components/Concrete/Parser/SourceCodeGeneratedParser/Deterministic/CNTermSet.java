/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.Parser.SourceCodeGeneratedParser.Deterministic;

/**
 *
 * @author HP
 */
public class CNTermSet {
    String name;
    String first;

    /**
     *
     * @param name
     * @param first
     */
    public CNTermSet(String name, String first) {
        this.name = name;
        this.first = first;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getFirst() {
        return first;
    }

    /**
     *
     * @param first
     */
    public void setFirst(String first) {
        this.first = first;
    }
    
    
}
