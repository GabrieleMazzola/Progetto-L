package ticketcollector;

import centralsystem.interfaces.CentralSystemCollectorInterface;
import communication.StubCollector;
import items.Fine;
import java.io.IOException;
import java.util.Observable;


public class TicketCollector extends Observable{
    private final CentralSystemCollectorInterface stub;
    private CollectorOperation operation;

    
    public TicketCollector(String ipAddress) throws IOException {
        stub = new StubCollector(ipAddress, 5000);
    }
    
    public void setOperation(CollectorOperation newOperation) {
        operation = newOperation;
        notifyChange(operation);
    }
/**
 *Login del collector
 * @param username
 * @param psw
 * @return Ritorna vero se connesso. Se connesso richiede il numero di inizio per le multe e restituisce vero altrimenti restituisce falso
 */    
    public boolean loginCollector(String username,String psw){
    	return stub.collectorLogin(username, psw);
    }
    
/**
 * Ticket esistente
 * @param code
 * @return Vero se il ticket esiste già
 */    
    public Boolean existsTicket(long code){
    	return stub.existsTicket(code);
    }
/**
 * Aggiungi nuova multa 
 * @param cf
 * @param amount
 * @return Vero se viene aggiunta 
 */    
    public Boolean addFine(Fine fine){
        return stub.makeFine(fine);
    }

    public Long requestFinesStartNumber(String username) {
        return stub.requestFinesStartNumber(username);
    }
    
    private void notifyChange(Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}
