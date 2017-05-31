package test;

import JSONSingleton.JSONOperations;
import exceptions.TicketExausted;
import java.util.concurrent.TimeUnit;
import org.json.simple.parser.ParseException;
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
        String encoded = op.updateMachineStatusPacket(1, 20, 20, true, "a");
        System.out.println(encoded);
    }
}
