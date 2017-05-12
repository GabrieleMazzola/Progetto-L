package ticketCollector;

import java.util.ArrayList;

import centralsystem.CentralSystemCollectorInterface;


public class TicketCollector {
    private final int cod;					//codice della macchinetta fisica
    private final CentralSystemCollectorInterface stub;		
    private boolean connected = false;
    private String username;
    private String psw;
    private ArrayList<Fine> offlineFines;
    
    /**
     *
     * @param cod : codice identificativo della macchina
     * @param ipAddress : indirizzo IP del sistema centrale
     */
    public TicketCollector(int cod,String ipAddress){
        this.cod = cod;
        stub = new StubCollector(ipAddress, 5000);
        offlineFines = new ArrayList<>();
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
    	if(stub.collectorLogin(username,psw)){
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

    /**
     * Disconnette il controllore dalla macchina
     */
    public void logOut(){
    	if(!connected){
    		System.out.println("Controllore gia' disconnesso.");
    		return;
    	}
    	connected = false;
    	this.username = null;
    	this.psw = null;
    }    
   
    private boolean isLogged(){
    	if(!connected){
    		System.out.println("LoginControllore richiesto.");
    		return false;
    	}
    	return true;
    	
    }
    
    /**
     * Controllo degli abbonamenti //TODO
     */
    /*
    public boolean verifyValidation(String code){
    	//TODO
        return true;
    }
    */  

    /**
     * Crea una nuova multa. Nel caso in cui la connessione con il sistema centrale non sia possibile
     * la multa viene salvata nell'array delle multe offline e inviata
     * in un secondo momento.
     * @param cf : codice fiscale della persona multata
     * @param amount : cifra della multa
     */
    public void createFine(String cf, double amount){
    	if(isLogged()){
            Fine fine = new Fine(cf, amount);
            String test = "zuzzuprovolone";
            if(stub.centralSystemTEST(test).equals(test)){
            
            	stub.makeFine(fine);
            }else{
                offlineFines.add(fine);
                //TODO decidere come e ogni quanto provare ad inviare le multe offline
            }
        }
    }
}
