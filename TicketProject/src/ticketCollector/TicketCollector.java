package ticketCollector;


public class TicketCollector {
    private final int cod;					//codice della macchinetta fisica
    private final StubCollector stub;		
    private boolean connected = false;
    private String username;
    private String psw;
    
    public TicketCollector(int cod,String ipAddress){
        this.cod = cod;
        stub = new StubCollector(ipAddress, 5000);
    }
    
    
    /**
     * Deve essere eseguito SOLO all'inizio della giornata lavorativa
     * da parte del controllore. Controlla la presenza del controllore nel database
     */
    public boolean loginCollector(String username,String psw){
    	if(connected){
    		System.out.println("Controllore gia' connesso.Logout eseguito, rieffettuare login.");
    		logOut();
    		return false;
    	}
    	if(stub.loginCollector(username,psw)){
    		connected = true;
    		System.out.println("Controllore loggato!");
    		this.username = username;
    		this.psw = psw;
    		return true;
    	}else{
    		connected = false;
    		System.out.println("Login controllore fallito!");
    		return false;
    	}
    }

    private void logOut(){
    	if(!connected){
    		System.out.println("Controllore gia' disconnesso.");
    		return;
    	}
    	connected = false;
    	this.username = null;
    	this.psw = null;
    }    
   
    private boolean checkConnection(){
    	if(!connected){
    		System.out.println("LoginControllore richiesto.");
    		return false;
    	}
    	return true;
    }
    
    /**
     * Controllo degli abbonamenti //TODO
     */
    public boolean verifyValidation(String code){
    	if(checkConnection()){
	    	//TODO
	    		
            if(stub.existsTicket(code))
                return true;
             //return stub.isValid(idTicket); //Da aggiungere allo stub
            return false;
    	}else{
    		//TODO exception
    		return false;
    	}
    }
      

    
    public boolean makeFine(String cf, double amount){
    	if(checkConnection()){
            Fine fine = new Fine(cf, amount);
            return stub.makeFine(fine);
    	}else{
    		//TODO exception
    		return false;
    	}
    }
}
