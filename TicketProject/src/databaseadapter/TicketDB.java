/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseadapter;

import java.util.Date;

/**
 *
 * @author Gabriele
 */
public class TicketDB {
    private TicketType id_type;
    private int serialCode;
    private String id_user;
    private boolean active;
    private Date expireTime;
    private static int counter = 0;
    
    
    

    public TicketDB(Date expiryDate, int serialCode, String username, String ticketType) {
        this.id_type = id_type;
        this.serialCode = serialCode;
        this.id_user = id_user;
        this.active = active;
        this.expireTime = expireTime;
    }

    public TicketType getType() {
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
    
    
    
    
    
    
}
