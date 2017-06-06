package databaseadapter;

class OptionDB {
    private long ticketCounter = 0; 
    private long ticketVersionCounter = 0;
    
    public OptionDB(){
    }

    public long getTicketCounter() {
        return ticketCounter;
    }

    public void setTicketCounter(long ticketCounter) {
        this.ticketCounter = ticketCounter;
    }

    public long getTicketVersionCounter() {
        return ticketVersionCounter;
    }

    public void setTicketVersionCounter(long ticketVersionCounter) {
        this.ticketVersionCounter = ticketVersionCounter;
    }
    
    
}
