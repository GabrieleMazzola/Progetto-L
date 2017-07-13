package items;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Zubeer
 */
public class Fine {
    private String id;
    private String personCF;
    private String collectorUsername;
    private double amount;
    private LocalDate today, firstDeadline, secondDeadline;
    
    /**
     *
     * @param id
     * @param personCF
     * @param amount
     * @param collectorUsername
     */
    public Fine(String id, String personCF, double amount, String collectorUsername){
        this.id = id;
        this.personCF=personCF;
        this.collectorUsername = collectorUsername;
        today = LocalDate.now();
        firstDeadline = today.plus(15, ChronoUnit.DAYS);
        secondDeadline = today.plus(1, ChronoUnit.MONTHS);
        this.amount = amount;
    }
    
    public String getId() {
        return id;
    }

    public String getCollectorUsername() {
        return collectorUsername;
    }

    public void setCollectorUsername(String collectorUsername) {
        this.collectorUsername = collectorUsername;
    }
    
    public String getCfCode() {
        return personCF;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        
        sb.append("ID: ").append(this.id);
        sb.append("  ,  Collector: ").append(this.collectorUsername);
        sb.append("  ,  Person CF:").append(this.personCF);
        sb.append("  ,  Amount: ").append(this.amount);
        
        return sb.toString();
    }
    
}
