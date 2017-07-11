package machineOnline;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import items.Product;
import items.SimpleSeason;
import items.SimpleTicket;
import jsonenumerations.JsonFields;
import jsonenumerations.TicketTypes;
import singleton.JSONOperations;

public class StubOnline{
    String systemAddress;
    int systemPort;
    Socket socket;
    BufferedReader fromServer;
    PrintWriter toServer;
    private TicketOnline machine;
    private Thread codesThread;

    
    public StubOnline(String ipAdress, int port, TicketOnline machine) throws IOException {
        this.systemAddress = ipAdress;
        this.systemPort = port;
        this.machine = machine;
        if(!initConnection()){
        	System.err.println("PROBLEMA NELLA CONNESSIONE");
        };
    }

    private boolean initConnection() throws IOException{
        socket = new Socket(systemAddress, systemPort);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream(), true);   
        return true;

    }

    
    public boolean cardPayment(String cardNumber, double amount) {
        try {
            String packet = JSONOperations.getInstance().cardPaymentPacket(cardNumber, amount);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            //Aspetto risposta da parte del server
            String line = fromServer.readLine();
            
            JSONParser parser = new JSONParser();

            //Struttura JSON di risposta : {"DATA":"boolean"}
            JSONObject obj = (JSONObject) parser.parse(line);
            return (Boolean) obj.get(JsonFields.DATA.toString());

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Effettua una richiesta al server di inviare nuovi codici
     * @return I nuovi codici
     */
    public long requestCodes(long numberOfCodes) {
        
    	System.out.println("RICHIESTA CODICI...");
    	
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
            	ex.printStackTrace();
            }    

        return 0;
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
                
                switch(type.charAt(0)){
                    case 'T':
                        products.put(type, new SimpleTicket(description, type, cost, duration));
                        break;
                    case 'S':
                        products.put(type, new SimpleSeason(description, type, cost/duration, duration));
                        break;
                    default:
                    	
                }
            }
                
            
            
        } catch (ParseException ex) {
                System.err.println("Error: SubMachine.java - updateMachineStatus() parsing error");
        } catch (IOException ex) {
            System.err.println("Error: SubMachine.java - updateMachineStatus() fromServer read error");
        }
        return products;
    }
    
}
