package databaseadapter;

import java.util.Date;

/**
 *
 * @author Gabriele
 */
public class TicketDB {
    private String id_type;
    private int serialCode;
    private String id_user;
    private boolean active;
    private Date expireTime;
    private static int counter = 0;
    
    
    

    public TicketDB(Date expireTime, int serialCode, String username, String ticketType) {
        this.id_type = ticketType;
        this.serialCode = serialCode;
        this.id_user = username;
        this.active = active;
        this.expireTime = expireTime;
    }

    public String getType() {
        return id_type;
    }

    public int getCode() {
        return serialCode;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ticket number: ").append(this.serialCode);
        sb.append("\nType : ").append(this.id_type).append("\nStatus: ").append(this.active).append("\n");
        return sb.toString();
    }

    String getUsername() {
        return this.id_user;
    }

    public Date getExpireTime() {
        return expireTime;
    }
    
    
    
    
    
    
}
