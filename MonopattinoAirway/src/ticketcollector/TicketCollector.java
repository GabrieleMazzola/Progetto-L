package ticketcollector;

import communication.StubCollector;
import items.Fine;
import java.io.IOException;
import java.util.Observable;

/**
 *
 * @author Zubeer
 */
public class TicketCollector extends Observable{
    
    private Long finesStartNumber;
    private final StubCollector stub;		
    private boolean connected;
    private String username;
    private CollectorOperation operation;
/**
 * Avvio di un nuovo StubCollector
 * @param ipAddress
 * @throws IOException 
 */
    public TicketCollector(String ipAddress) throws IOException {
        stub = new StubCollector(ipAddress, 5000);
        connected = false;
    }

    public String getUsername() {
        return username;
    }
    
    /**
     *
     * @throws IOException
     */
    public void initConnection() throws IOException{
        stub.initConnection();
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
    	if(connected){
            return true;
    	}
    	if(stub.collectorLogin(username,psw)){
            connected = true;
            this.username = username;
            finesStartNumber = requestFinesStartNumber();
            return true;
    	}else{
            connected = false;
            finesStartNumber = null;
            return false;
    	}
    }

    public void logOut(){
    	if(connected){
            connected = false;
            this.username = null;
            this.finesStartNumber = null;
        }    
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
    public Boolean addFine(String cf, double amount){
        if(connected){
            Fine fine = new Fine(username+finesStartNumber, cf, amount, username);
            finesStartNumber++;
            
            return stub.addFine(fine);
        }
        return null;
    }
/**
 * Acquisizione offline del numero delle multe 
 * @return Vero se vengono acquisite 
 */    
    public int getOfflineFinesNumber() {
        return stub.getOfflineFinesSize();
    }

    private Long requestFinesStartNumber() {
        return stub.requestFinesStartNumber(username);
    }
    
    private void notifyChange(Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}
