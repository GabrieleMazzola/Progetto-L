/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Francesco
 */
public class TicketTypeNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>TicketTypeNotFoundException</code>
     * without detail message.
     */
    public TicketTypeNotFoundException() {
    }

    /**
     * Constructs an instance of <code>TicketTypeNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public TicketTypeNotFoundException(String msg) {
        super(msg);
    }
}
