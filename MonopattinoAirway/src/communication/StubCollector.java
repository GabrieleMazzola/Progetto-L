package communication;


import centralsystem.interfaces.CentralSystemCollectorInterface;
import items.Fine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import enums.jsonenumerations.JsonFields;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import singleton.JSONOperations;


public class StubCollector implements CentralSystemCollectorInterface{
    private String ipAdress;
    private int port;
    private Socket socket;
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private List<Fine> offlineFines; 
    
    /**
     * Richiede ipAddress del CentralSystem(SocketHandler), e porta di comunicazione.
     * Inizializza i relativi campi,le connessioni, i metodi di comunicazione, e la lista
     * di multe salvate offline.
     * @param ipAddress
     * @param port
     * @throws IOException 
     */
    public StubCollector(String ipAddress,int port) throws IOException {
        this.ipAdress = ipAddress;
        this.port = port;
        offlineFines = new ArrayList<>();
        initConnection();
       
    }
    
    public void initConnection() throws IOException {
        socket = new Socket(ipAdress, port);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream(), true);
        socket.setSoTimeout(500);
    }
    
    public void closeConnection() {
        if(!socket.isClosed()){
            try{    
                fromServer.close();
                toServer.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }    
    }
    
    /**
     * Viene sempre fatto un test sull' integrità della connessione
     * Attraverso il Singleton dei JSONOperation viene creato un pacchetto JSON che contiene il
     * codice seriale da verificare, e questo viene spedito al SocketHandler del CentralSystem.
     * Viene decodificata la risposta dal pacchetto JSON inviato in risposta dal SocketHandler.
     * In caso di errore della comunicazione viene chiusa la connessione.
     * @param serialcode
     * @return Vero se il biglietto esiste, falso altrimenti e null se avviene un errore di connessione
     * o di parsing del pacchetto JSON di risposta
     */
    @Override
    public Boolean existsTicket(long serialcode) {
       
        try{
            
            testConnection();
            String packet = JSONOperations.getInstance().existsTicketPacket(serialcode);
            toServer.println(packet); 
            String line = fromServer.readLine();
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);

            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean)obj.get(JsonFields.DATA.toString());
        }catch(ParseException ex){
            ex.printStackTrace();
        }catch(IOException ex){
           closeConnection();
        }
        return null;
    }

    /**
     * Comunica al SocketHandler tramite JSON una multa da immagazzinare nel database.
     * In caso di interruzione della connessione, questa viene chiusa, e viene sempre
     * fatto un test di integrità, e un tentativo di riconnessione all'inizio del metodo.
     * Se non c'è connessione le multe vengono salvate offline, e poi rimandate 
     * al ripristino della connessione.
     * @param f
     * @return Vero se si riesce/riescono ad inviare la/le Fine, falso in caso contrario, e null
     * in caso di errore durante il parsing del pacchetto JSON
     */
    @Override
    public Boolean addFine(Fine f){
        addOfflineFine(f);

        try{
            
            testConnection();
            Boolean ret = saveFinesOnline();
            
            //Struttura JSON di risposta : {"data":"boolean"}
            return ret;
        }catch(ParseException ex){
            ex.printStackTrace();
            return null;
        }catch(IOException ex){
            closeConnection();
            return false;
        }
    }

    @Override
    public boolean collectorLogin(String username, String psw) {
        try {
            
            String packet = JSONOperations.getInstance().collectorLoginPacket(username, psw);
            toServer.println(packet);                           //Invio verso server della richiesta JSON
            
            String line = fromServer.readLine();
            
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            
            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean)obj.get(JsonFields.DATA.toString());
                
        }catch(IOException|ParseException ex){
            ex.printStackTrace();
            return false;
        }        
    }
    
    private void addOfflineFine(Fine fine){
        this.offlineFines.add(fine);
    }
    
    /*Metodo privato che permette di comunicare al server le Fines salvate offline.
     *Finchè la connessione è abilitata, e la lista delle fines offline non è vuota, viene creato
     *un pacchetto JSON per ogni Fine nella lista, e questa viene comunicata al server.
    */
    private Boolean saveFinesOnline() throws IOException, ParseException{

        while(!socket.isClosed() && !offlineFines.isEmpty()){
            Fine f = offlineFines.get(0);
            String packet = JSONOperations.getInstance().makeFinePacket(f);
            toServer.println(packet); //Invio verso server della richiesta JSON
            String line = fromServer.readLine(); 
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            
            if(!(Boolean)obj.get(JsonFields.DATA.toString()))
                return null;
            else{
                offlineFines.remove(0);
            }
        }
        
        if(offlineFines.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getOfflineFinesSize() {
        return offlineFines.size();
    }

    private void testConnection() throws IOException  {
        if(socket.isClosed()){
                initConnection();
                
        }
    }

    /**
     * Richiede al SocketHandler, inviando un pacchetto JSON con l'username del Collector,
     * di inviare il numero di multe fatte dallo specifico Collector fino a quel momento.
     * @param collectorUsername
     * @return Un long, che è il numero di multe fatte dal Collector, o null se avviene un problema
     * di comunicazione o di parsing del pacchetto JSON di risposta
     */
    public Long requestFinesStartNumber(String collectorUsername) {
        try {
            
            String packet = JSONOperations.getInstance().requestFinesStartNumberPacket(collectorUsername);
            toServer.println(packet);                           //Invio verso server della richiesta JSON
            
            String line = fromServer.readLine();
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            
            return (Long)obj.get(JsonFields.DATA.toString());
        }catch(IOException|ParseException ex){
            ex.printStackTrace();
        } 
        return null;
    }
}