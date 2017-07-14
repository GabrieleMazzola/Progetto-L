package ticketmachine.handlers;

/**
 *
 * @author Manuele
 * Gestisce le risorse di carta e inchiostro all'interno della TicketMachine
 */
public class ResourcesHandler {
    private final int maxPaper = 1000;
    private int paper;
    private final double maxInk = 500;
    private double ink;
    
    private final int paperToPrint;
    private final double inkToPrint;
    
    /**
     *
     */
    public ResourcesHandler() {
        paper = maxPaper;
        ink = maxInk;
        
        //Vengono settati ad un valore arbitrario
        paperToPrint = 1;
        inkToPrint = 5;
    }
    
    /**
     * Chiamato quando viene effettuata una vendita di un biglietto. I valori di
     * inchiostro e carta decrementano di un valore predefinito
     */
    public void printTicket() {
        if(hasEnoughResources()) {
            paper -= paperToPrint;
            ink -= inkToPrint;
        }
    }
    
    /**
     * 
     * @return La percentuale di carta rispetto al massimo all'interno del 
     * ResourceHandler. Viene chiamato quando si vuole verificare l'operatività
     * della TicketMachine
     */
    public double getPaperPercentage() {
        return (double)paper*100/(double)maxPaper;
    }
    /**
     * 
     * @return La percentuale di inchiostro rispetto al massimo all'interno del 
     * ResourceHandler. Viene chiamato quando si vuole verificare l'operatività
     * della TicketMachine
     */
    public double getInkPercentage() {
        return (double)ink*100/(double)maxInk;
    }
    
    /**
     *
     * @return
     */
    public boolean hasEnoughResources() {
        return paper >= paperToPrint && ink >= inkToPrint;
    }
}
