package ticket;

/**
 *
 * @author Manuele
 */
public class Ticket {
    private String code;
    private TicketType type;
    
    public Ticket(String code, TicketType type) {
        this.code = code;
        this.type = type;
    }
    
    public String getCode() {
        return code;
    }
    
    public double getCost() {
        return type.getCost();
    }
    
    public String getType() {
        return type.getType();
    }
}
