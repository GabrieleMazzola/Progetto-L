package ticket;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Manuele
 */
public class Ticket {
    private String code;
    private TicketType type;
    private Date expiryDate;
    private String owner;
    
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

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
