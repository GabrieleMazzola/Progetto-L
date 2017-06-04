package databaseadapter;

class OptionDB {
    private long ticketCounter = 0; 
    
    public OptionDB(){
    }

    public long getTicketCounter() {
        return ticketCounter;
    }

    public void setTicketCounter(long ticketCounter) {
        this.ticketCounter = ticketCounter;
    }
}
