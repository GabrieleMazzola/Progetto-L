package exceptions;


public class TicketExausted extends Exception {


    public TicketExausted() {
    }


    public TicketExausted(String msg) {
        super(msg);
    }
}
