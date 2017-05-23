package ticket;

/**
 *
 * @author Manuele
 */
public abstract class Ticket {
    protected String code;
    protected static double cost;
    protected TicketType type;
    
    public String getCode() {
        return code;
    }
    public double getCost() {
        return cost;
    }
    public boolean isType(TicketType type) {
        return this.type.equals(type);
    }
}
