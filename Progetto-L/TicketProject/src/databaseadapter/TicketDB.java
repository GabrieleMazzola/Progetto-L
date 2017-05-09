/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseadapter;

/**
 *
 * @author Gabriele
 */
public class TicketDB {
    private TicketType type;
    private String code;
    private boolean active;
    private static int counter = 0;

    public TicketDB(TicketType type) {
        this.type = type;
        this.active = false;
        this.code = "" + (counter++);
    }

    public TicketType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ticket number: ").append(this.code);
        sb.append("\nType : ").append(this.type).append("\nStatus: ").append(this.active).append("\n");
        return sb.toString();
    }
    
    
    
    
    
    
}
