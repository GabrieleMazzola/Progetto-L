package test;

import JSONSingleton.JSONOperations;
import exceptions.TicketExausted;
import java.util.concurrent.TimeUnit;
import org.json.simple.parser.ParseException;
import ticket.SingleTicket;
import ticket.Ticket;

/**
 *
 * @author Manuele
 */
public class TestSingleTicket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException{
        JSONOperations op = JSONOperations.getInstance();
        String encoded = op.updateMachineStatus(1, 20, 20, true);
        System.out.println(encoded);
        
        String decoded = op.decodeRead(encoded);
        System.out.println(decoded);
    }
}
