package items;

/**
 *
 * @author Zubeer
 */
public class SimpleTicket implements Product{
    
    private final String description;
    private final String type;
    private double cost;
    private int duration;

    /**
     *
     * @param description
     * @param type
     * @param cost
     * @param duration
     */
    public SimpleTicket(String description, String type,double cost, int duration) {
        this.description = description;
        this.type=type;
        this.cost = cost;
        this.duration = duration;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
    
    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public double getCost() {
        return cost;
    }
            
    @Override
    public int getDuration() {
        return duration;
    }

    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Simple ticket, type: ").append(type);
        sb.append("  ,  duration: ").append(this.duration).append(" minutes");
        sb.append("  ,  cost:").append(cost);
        
        return sb.toString();
    }
}
