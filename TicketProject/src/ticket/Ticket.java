package ticket;

/**
 *
 * @author Manuele
 */
public abstract class Ticket {
    
    protected double cost;
    
    //Convalida il biglietto
    public abstract void validate();
    //Ritorna vero se il biglietto è valido
    public abstract boolean isValid();
    
    public double getCost() {return cost;}
}
