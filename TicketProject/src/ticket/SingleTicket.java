package ticket;

import java.util.Calendar;

/**
 *
 * @author Manuele
 */
public class SingleTicket extends Ticket{
    public SingleTicket() {
        this.cost = 1.30;
        type = TicketType.SINGLE;
    }
}
