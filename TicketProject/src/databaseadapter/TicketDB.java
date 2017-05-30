package databaseadapter;

public class TicketDB {
    private TicketType type;
    private String code;
    private boolean active;
    private int validityTime;
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
