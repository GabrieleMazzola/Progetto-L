package machines;

import JSONSingleton.JSONOperations;
import centralsystem.CentralSystemTicketInterface;
import java.io.*;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class StubMachine implements CentralSystemTicketInterface {
    String ipAdress;
    int port;
    Socket socket;
    BufferedReader fromServer;
    PrintWriter toServer;
    JSONOperations JSONOperator;
    private TicketMachine machine;

    public StubMachine(String ipAdress, int port, TicketMachine machine) {
        this.ipAdress = ipAdress;
        this.port = port;
        JSONOperator = JSONOperations.getInstance();
        this.machine = machine;
    }

    private void initConnection() {
        try {
            socket = new Socket(ipAdress, port);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toServer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            fromServer.close();
            toServer.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * 
     * @param username
     * @param psw
     * @return Manda al server una richiesta di login con i dati per effettuarlo.
     * Se il server riceve i dati e riesce ad effettuare il login il metodo ritorna
     * vero, altrimenti ritorna falso
     */
    @Override
    public boolean userLogin(String username, String psw) {
        try {
            initConnection();

            //String packet = loginJSONPacket(username, psw);
            String packet = JSONOperator.userLoginPacket(username, psw);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            String line = fromServer.readLine();
            closeConnection();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(line);

            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean) obj.get("data");

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            closeConnection();
            return false;
        }
    }
    
    /**
     * 
     * @return Effettua una richiesta al server di inviare nuovi codici. Usato
     * quando la macchinetta sta per finire i codici disponibili
     */
    @Override
    public String requestCodes() {
        
        (new RequestCodesThread(machine,socket,fromServer,toServer,JSONOperator,ipAdress,port)).start();
        return "Thread Attivo";
        
    }
    
    /**
     * 
     * @param cardNumber
     * @return Richiede al server di effettuare un pagamento via carta di credito.
     * Se la richiesta viene ricevuta e il pagamento effettuato il metodo ritorna
     * vero, altrimenti ritorna falso
     */
    @Override
    public boolean cardPayment(String cardNumber) {
        try {
            initConnection();
            //String packet = cardPaymentJSONPacket(cardNumber);
            String packet = JSONOperator.cardPaymentPacket(cardNumber);
            //System.out.println(packet);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            //Aspetto risposta da parte del server
            String line = fromServer.readLine();
            closeConnection();

            JSONParser parser = new JSONParser();

            //Struttura JSON di risposta : {"data":"boolean"}
            JSONObject obj = (JSONObject) parser.parse(line);
            return (Boolean) obj.get("data");

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            closeConnection();
            return false;
        }
    }

    @Override
    public boolean createUser(String name, String surname, String username,String cf, String psw) {
    try {
        initConnection();
        String packet = JSONOperator.createUser(name,surname,username,cf,psw);
        //System.out.println(packet);
        toServer.println(packet);                           //Invio verso server della richiesta JSON
        
        //Aspetto risposta da parte del server
        String line = fromServer.readLine();
        closeConnection();
        
        JSONParser parser = new JSONParser();
        
        //Struttura JSON di risposta : {"data":"boolean"}
        JSONObject obj = (JSONObject) parser.parse(line);
        return (Boolean) obj.get("data");
        
    } catch (IOException | ParseException ex) {
        ex.printStackTrace();
        closeConnection();
        return false;
        }
    }
}
