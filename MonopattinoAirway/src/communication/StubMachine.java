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
import productsfactories.central.CentralProductsFactory;
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

    
    public StubMachine(String systemAddress, int systemPort, TicketMachine machine) throws IOException {
        this.systemAddress = systemAddress;
        this.systemPort = systemPort;
        this.machine = machine;
        initConnection();
    }
    

    private void initConnection() throws IOException {
        socket = new Socket(systemAddress, systemPort);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public boolean userLogin(String username, String psw) {
        try {
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
    
    @Override
    public boolean cardPayment(String cardNumber, double amount) {
        try {
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
    
    @Override
    public boolean updateMachineStatus(MachineStatus status) {
        try {
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

    @Override
    public long requestCodes(long numberOfCodes) {
        try {
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
    public boolean addSale(Sale sale) {
        try {
            String packet = JSONOperations.getInstance().addSale(sale);
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
    
    public Map<String, Product> getProductList() {
        
        Map<String, Product> products = new HashMap<>();
        try {
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
                
//                products.put(type, ProductsFactory.getInstance().buildTicket(description, type, cost, duration));
                products.put(type, CentralProductsFactory.getInstance().buildTicket(type));
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
}
