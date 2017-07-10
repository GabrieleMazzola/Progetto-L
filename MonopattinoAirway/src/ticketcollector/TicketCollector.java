package ticketcollector;

import communication.StubCollector;
import items.Fine;
import java.io.IOException;

public class TicketCollector {
    
    private Long finesStartNumber;
    private final StubCollector stub;		
    private boolean connected;
    private String username;
    

    public TicketCollector(String ipAddress) throws IOException {
        stub = new StubCollector(ipAddress, 5000);
        connected = false;
    }
    
    public void initConnection() throws IOException{
        stub.initConnection();
    }
    
   
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
            stub.closeConnection();
        }    
    }    
    
    public Boolean existsTicket(long code){
    	return stub.existsTicket(code);
    }
    
    public boolean addFine(String cf, double amount){
        if(connected){
            Fine fine = new Fine(username+finesStartNumber, cf, amount, username);
            finesStartNumber++;
            if(stub.addFine(fine)) {
                return true;
            }
        }
        return false;
    }
    
    public int getOfflineFinesNumber() {
        return stub.getOfflineFinesSize();
    }

    private Long requestFinesStartNumber() {
        return stub.requestFinesStartNumber(username);
    }
}