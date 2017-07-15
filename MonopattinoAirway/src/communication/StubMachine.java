package communication;

import centralsystem.interfaces.CentralSystemTicketInterface;
import items.Product;
import items.Sale;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.TicketTypes;
import java.util.ArrayList;
import java.util.List;
import productsfactories.client.ClientProductsFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import singleton.JSONOperations;
import ticketmachine.*;

public class StubMachine implements CentralSystemTicketInterface {
    
    private String systemAddress;
    private int systemPort;
    private Socket socket;
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private TicketMachine machine;
    private Thread codesThread;
    private List<Sale> offlineSales;
    
    public StubMachine(String systemAddress, int systemPort, TicketMachine machine) throws IOException {
        this.systemAddress = systemAddress;
        this.systemPort = systemPort;
        this.machine = machine;
        offlineSales = new ArrayList<>();
        initConnection();

    }
    

    private void initConnection() throws IOException {
        socket = new Socket(systemAddress, systemPort);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream(), true);
        socket.setSoTimeout(500);
    }
    
    private void closeConnection() {
    
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

    @Override
    public boolean userLogin(String username, String psw) {
        try {
            testConnection();
            //String packet = loginJSONPacket(username, psw);
            String packet = JSONOperations.getInstance().userLoginPacket(username, psw);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            String line = fromServer.readLine();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(line);

            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean) obj.get(JsonFields.DATA.toString());

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Invia al SocketHandler le informazioni riguardanti la carta di credito e 
     * il pagamento da effettuare,mendiante pacchetto JSON.
     * @param cardNumber
     * @param amount
     * @return il valore booleano di ritorno proveniente da socketHandler, o
     * falso se va male il parsing del JSON di risposta.
     */
    @Override
    public boolean cardPayment(String cardNumber, double amount) {
        try {
            testConnection();
            //String packet = cardPaymentJSONPacket(cardNumber);
            String packet = JSONOperations.getInstance().cardPaymentPacket(cardNumber, amount);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            //Aspetto risposta da parte del server
            String line = fromServer.readLine();
            
            JSONParser parser = new JSONParser();

            //Struttura JSON di risposta : {"data":"boolean"}
            JSONObject obj = (JSONObject) parser.parse(line);
            return (Boolean) obj.get(JsonFields.DATA.toString());

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createUser(String name, String surname,String cf, String username, String psw, String email) {
        try {
            testConnection();
            String packet = JSONOperations.getInstance().createUser(name,surname,cf,username,psw, email);
            toServer.println(packet);                           

            //Aspetto risposta da parte del server
            String line = fromServer.readLine();

            JSONParser parser = new JSONParser();

            //Struttura JSON di risposta : {"data":"boolean"}
            JSONObject obj = (JSONObject) parser.parse(line);
            return (Boolean) obj.get(JsonFields.DATA.toString());

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return false;
            }
    }
    
    /**
     * Comunica al SocketHandler il machine status della TicketMachine da aggiornare,
     * attraverso la creazione di un pacchetto JSON
     * @param status
     * @return il booleano contenuto nel pacchetto JSON di ritorno dal SocketHandler
     */
    @Override
    public boolean updateMachineStatus(MachineStatus status) {
        try {
            testConnection();
            String packet = JSONOperations.getInstance().updateMachineStatusPacket(status);
            toServer.println(packet);
            String line = fromServer.readLine();
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(line);
            
            return (boolean)obj.get(JsonFields.DATA.toString());
            
            } catch (ParseException ex) {
                System.err.println("Error: SubMachine.java - updateMachineStatus() parsing error");
            } catch (IOException ex) {
                System.err.println("Error: SubMachine.java - updateMachineStatus() fromServer read error");
            }
            
        return false;
    }

    /**
     * Lancia il thread che si occupa della richiesta di un contatore di codici validi
     * per i biglietti, al CentralSystem, e crea una mutua esclusione sulla possibilità
     * di operare di questi ultimi
     * @param numberOfCodes
     * @return zero
     */
    @Override
    public long requestCodes(long numberOfCodes) {
        try {
            testConnection();
            if(codesThread == null){
                codesThread = new RequestCodesThread(machine, systemAddress, systemPort, numberOfCodes);
                codesThread.start();
            }
            if(!codesThread.isAlive()) {
                    codesThread = new RequestCodesThread(machine, systemAddress, systemPort, numberOfCodes);
                    codesThread.start();
                }
            } catch (IOException ex) {    
            Logger.getLogger(StubMachine.class.getName()).log(Level.SEVERE, null, ex);
        }    

        return 0;
    }
    
    public String getClientIPAddress() {
        String localAddress = null;
        localAddress = socket.getLocalAddress().getHostAddress();
        return localAddress;
    }

    @Override
    public Boolean addSale(Sale sale) {
        
        this.offlineSales.add(sale);
        
        try {
            
            testConnection();
            Boolean ret = saveSaleOffline();
            
            return ret;
            } catch (ParseException ex) {
                System.err.println("Error: SubMachine.java - updateMachineStatus() parsing error");
                return null;
            } catch (IOException ex) {
                closeConnection();
                return false;
        }

    }
    
    public Boolean saveSaleOffline() throws IOException, ParseException{
    
        while(!socket.isClosed() && !offlineSales.isEmpty()){
            Sale sale = offlineSales.get(0);
            String packet = JSONOperations.getInstance().addSale(sale);
            toServer.println(packet);
            String line = fromServer.readLine();
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(line);
            
            if(!(boolean)obj.get(JsonFields.DATA.toString()))
                return null;
            else{
                offlineSales.remove(0);
            }
        }
        
        if(offlineSales.isEmpty())
            return true;
        
        return false;
    }
    
    /**
     * Effettua una richiesta al SocketHandler che richiede le tipologie di
     * Product istanziate. Estrae le informazioni dei relativi product dal JSON di ritorno,
     * e le mette in una mappa.
     * @return La mappa con tutte le istanze dei Product acquistabili
     */
    public Map<String, Product> getProductList() {
        
        Map<String, Product> products = new HashMap<>();
        try {
            testConnection();
            String packet = JSONOperations.getInstance().requestTicketTypesPacket();
            toServer.println(packet);
            String line = fromServer.readLine();
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(line);
            JSONArray typesArray = (JSONArray) obj.get(JsonFields.DATA.toString());
            
            for(int i = 0; i<typesArray.size(); i++){
                JSONObject prodObj = (JSONObject)typesArray.get(i);
                
                String type = (String)prodObj.get(TicketTypes.TYPE.toString());
                Double cost = (Double)prodObj.get(TicketTypes.COST.toString());
                String description = (String)prodObj.get(TicketTypes.DESCRIPTION.toString());
                Long bufferDuration = (Long)prodObj.get(TicketTypes.DURATION.toString());
                Integer duration = Integer.valueOf(bufferDuration.toString());
                
                Product newProd = ClientProductsFactory.getInstance().buildTicket(description, type, cost,duration);
                if(newProd != null)
                	products.put(type,newProd);
            }
      
        } catch (ParseException ex) {
                System.err.println("Error: SubMachine.java - updateMachineStatus() parsing error");
        } catch (IOException ex) {
            System.err.println("Error: SubMachine.java - updateMachineStatus() fromServer read error");
        }
        return products;
    }

    public void userEmailRequest(String email) {
        try {
            testConnection();
            String packet = JSONOperations.getInstance().requestEmailTo(email);
            toServer.println(packet);
            String line = fromServer.readLine();
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(line);
            
            } catch (ParseException ex) {
                System.err.println("Error: SubMachine.java - updateMachineStatus() parsing error");
            } catch (IOException ex) {
                System.err.println("Error: SubMachine.java - updateMachineStatus() fromServer read error");
        }
    }

    private void testConnection() throws IOException {
        if(socket.isClosed())
            initConnection();
    }
    
    public int getOfflineSaleSize(){
        return offlineSales.size();
    }
    
}
