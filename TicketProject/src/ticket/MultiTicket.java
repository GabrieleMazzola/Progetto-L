package ticket;

import exceptions.TicketExausted;
import java.util.Calendar;

/**
 *
 * @author Manuele
 */
public class MultiTicket extends Ticket{
    private int maxTranspNum, transpNum;
    
    public MultiTicket(int maxTranspNum) {
        this.cost = cost;
        this.maxTranspNum = maxTranspNum;
        transpNum = -1;
        type = TicketType.MULTI;
    }
    
    private boolean hasTravelsLeft() {
        
        return transpNum < maxTranspNum - 1;
        //Uso transpNum come puntatore per accedere al vettore di Calendar.
        //Se transpNum < maxTranspNum -1, ho ancora almeno un viaggio disponibile
        //con il biglietto
    }

    public int getMaxTranspNum() {
        return maxTranspNum;
    }

    public int getTranspNum() {
        return transpNum;
    }
}
