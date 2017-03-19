package exceptions;

/**
 *
 * @author Manuele
 */
public class TicketExausted extends Exception {

    /**
     * Creates a new instance of <code>TicketExausted</code> without detail
     * message.
     */
    public TicketExausted() {
    }

    /**
     * Constructs an instance of <code>TicketExausted</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public TicketExausted(String msg) {
        super(msg);
    }
}
