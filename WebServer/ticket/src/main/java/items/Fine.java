package items;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Fine {
    private String id;
    private String personCF;
    private String collectorUsername;
    private double amount;
    private LocalDate today, firstDeadline, secondDeadline;
    
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
    
    
    //TODO
    /*
    Creiamo nella fine il riferimento al collector che la crea --> salviamo l'username. DONE
    di conseguenza una multa sarà identificata da: username + contatoreFines    DONE
    
    sim:    si fa un "countAllFinesMadeBy(collectorUsername)" e si conta il numero di fines trovate -> restituiamo questo valore al momento del collectorLogin
    
    db:     modificare la table della Fine per aggiungere l'username del collector
            per il resto uguale ma tramite query
    
    ogni volta che si fa una multa, incrementiamo la variabile LOCALE del ticketCollector 
    se siamo offline, la multa verrà caricata successivamente. 
    */
}