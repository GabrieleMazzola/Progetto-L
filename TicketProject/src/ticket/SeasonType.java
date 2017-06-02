package ticket;

/**
 *
 * @author Manuele
 */
public class SeasonType implements TicketType{
    private double cost;
    private int durationInMonth;
    
    public SeasonType(int duration) {
        durationInMonth = duration;
        this.cost = 10*duration;
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
