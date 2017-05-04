/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicketCollector;

/**
 *
 * @author Simone
 */
public class FineException extends RuntimeException {

    /**
     * Creates a new instance of <code>FineException</code> without detail
     * message.
     */
    public FineException() {
        super("Errore fine");
    }

    /**
     * Constructs an instance of <code>FineException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FineException(String msg) {
        super(msg);
    }
}
