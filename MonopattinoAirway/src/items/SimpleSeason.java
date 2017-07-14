package items;

/**
 *
 * @author Zubeer
 */
public class SimpleSeason implements Product{

    private String description;
    private String type;
    private double monthlyCost;
    private int duration;

    /**
     *
     * @param description
     * @param type
     * @param monthlyCost
     * @param duration
     */
    public SimpleSeason(String description, String type, double monthlyCost,int duration){
        this.description=description;
        this.type=type;
        this.monthlyCost=monthlyCost;
        this.duration=duration;
    }
    
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getType() {
        return type;
    }
    
    @Override
    public double getCost() {
        return monthlyCost*duration;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("TYPE: ").append(type);
        sb.append("  ,  DESCRIPTION: ").append(description);
        sb.append("  ,  DURATION: ").append(this.duration).append(" MONTHS");
        sb.append("  ,  MONTHLYCOST:").append(monthlyCost);
        sb.append("  ,  COST:").append(getCost());
        
        return sb.toString();
    }
    
}