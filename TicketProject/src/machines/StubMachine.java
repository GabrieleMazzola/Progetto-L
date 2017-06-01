package machines;

import JSONSingleton.JSONOperations;
import centralsystem.CentralSystemTicketInterface;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private void initConnection() throws IOException {
            socket = new Socket(ipAdress, port);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toServer = new PrintWriter(socket.getOutputStream(), true);
    }

    private void closeConnection() throws IOException {
            fromServer.close();
            toServer.close();
            socket.close();
    }
    
    /**
     * Manda al server una richiesta di login con i dati per effettuarlo.
     * @param username
     * @param psw
     * @return Vero se il server riceve i dati e riesce ad effettuare il login.
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
            return false;
        }
    }
    
    
    
    /**
     * Richiede al server di effettuare un pagamento via carta di credito.
     * @param cardNumber
     * @param amount
     * @return Verp se la richiesta viene ricevuta e il pagamento effettuato.
     */
    @Override
    public boolean cardPayment(String cardNumber, double amount) {
        try {
            initConnection();
            //String packet = cardPaymentJSONPacket(cardNumber);
            String packet = JSONOperator.cardPaymentPacket(cardNumber, amount);
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
            return false;
        }
    }
    
    /**
     * Crea un nuovo utente con i dati specificati nei parametri. La richiesta
     * viene inoltrata al server che si occuperà di aggiungere il nuovo utente
     * al database
     * @param name
     * @param surname
     * @param username
     * @param cf
     * @param psw
     * @return 
     */
    @Override
    public boolean createUser(String name, String surname, String username,String cf, String psw) {
    try {
        initConnection();
        String packet = JSONOperator.createUser(name,surname,username,cf,psw);
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
        return false;
        }
    }

    @Override
    public boolean updateMachineStatus(int machineCode, double inkLevel, double paperLevel, boolean active, String ipAddress) {
        try {
            initConnection();
            String packet = JSONOperator.updateMachineStatusPacket(machineCode, inkLevel, paperLevel, active, ipAddress);
            toServer.println(packet);
            String line = fromServer.readLine();
            closeConnection();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(line);
            
            return (boolean)obj.get("data");
            
            } catch (ParseException ex) {
                System.err.println("Error: SubMachine.java - updateMachineStatus() parsing error");
            } catch (IOException ex) {
                System.err.println("Error: SubMachine.java - updateMachineStatus() fromServer read error");
            }
            
        return false;
    }
/**
     * Effettua una richiesta al server di inviare nuovi codici
     * @param numberOfCodes
     * @return I nuovi codici
     */
    @Override
    public long requestCodes(long numberOfCodes) {
        (new RequestCodesThread(machine,socket,fromServer,toServer,JSONOperator,ipAdress,port, numberOfCodes)).start();
        return 1;
        
    }
    
    public String getIPAddress() {
        String localAddress = null;
        try{
            initConnection();
            localAddress = socket.getLocalAddress().getHostAddress();
            closeConnection();
        }catch(IOException e){}
        return localAddress;
    }
}
