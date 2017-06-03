package ticket;

/**
 *
 * @author Manuele
 */
public class SeasonType implements TicketType{
    private double cost;
    private int durationInMonth;
    
    public SeasonType() {
        durationInMonth = 1;
        this.cost = 10;
    }
    
    @Override
    public double getCost() {
        return cost;
    }
    
    public int getDuration() {
        return durationInMonth;
    }
    
    @Override
    public String getType() {
        return "Season";
    }
    
    public void renew() {
        throw new UnsupportedOperationException("Operazione non ancora supportata");
    }
}
