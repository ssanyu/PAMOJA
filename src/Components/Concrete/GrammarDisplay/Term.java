/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components.Concrete.GrammarDisplay;

/**
 *
 * @author HP
 */
public class Term {
    String name;
    String body;

    /**
     *
     * @param name
     * @param body
     */
    public Term(String name, String body) {
        this.name = name;
        this.body = body;
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
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }
    
    
    
}
