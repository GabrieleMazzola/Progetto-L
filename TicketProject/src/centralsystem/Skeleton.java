package centralsystem;

import DateSingleton.DateOperations;
import databaseadapter.TicketDB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ticketCollector.Fine;

public class Skeleton extends Thread {
    Socket clientSocket;
    CSystem centralSystem;
    BufferedReader in;
    String inputData;
    PrintWriter out;
    JSONParser parser;
    DateOperations operator;

    public Skeleton(Socket clientSocket, CSystem centralSystem) {
        this.clientSocket = clientSocket;
        this.centralSystem = centralSystem;
        this.operator = DateOperations.getInstance();
        parser = new JSONParser();
    }
    
    /**
     * Si mette in ascolto del client fino a che questo invia una socket. La richiesta
     * arriva dal client come pacchetto JSON. Il pacchetto JSON viene quindi decodificato
     * e ne viene letto il metodo (la richiesta del client). I metodi supportati 
     * sono:
     * <p> -TEST: fa un test di connessione</p>
     * <p> -CREATEUSER: aggiunge un utente con i dati specificati nel resto del pacchetto
     * JSON</p>
     * <p> COLLECTORLOGIN: effettua il login per il controllore. I dati del login
     * sono specificati nel resto del pacchetto JSON </p>
     * <p> -USERLOGIN: effettua il login per l'utente. I dati del login sono
     * specificati nel resto del pacchetto JSON</p>
     * <p> -CARDPAYMENT: effettua un pagamento tramite carta di credito </p>
     * <p> -EXISTSTICKET: verifica l'esistenza di un biglietto </p>
     * <p> -REQUESTCODES: richiesta da parte della macchinetta di inviare nuovi 
     * codici</p>
     */
    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            LogCS.getInstance().stampa("out", "Client connesso:  "  + clientSocket.getInetAddress()); 
             LogCS.getInstance().stampa("out", "Connesso:  "  + this.getId()); 
            while(!clientSocket.isClosed()) {
                String result = decodeRead(in.readLine());
                out.println(result);
                LogCS.getInstance().stampa("out", "Risposta:  "  + result); 
            }
            LogCS.getInstance().stampa("out", "Chiusa:  "  + this.getId()); 
            in.close();
            out.close();
        } catch (IOException ex) {
            System.err.println("Error: socket opening fail");
            System.err.println(ex);
        }
    }
    
    /**
     * Viene interpretata la stringa in ingresso. In base al suo valore viene
     * fatta un'azione diversa
     * @param inputData
     * @return 
     */
    private synchronized String decodeRead(String inputData) {
        if(inputData == null){
            return "404 - COMANDO ERRATO";
        }
        
        JSONObject obj;
        LogCS.getInstance().stampa("out", "Richiesta in ingresso: "  + inputData);
        LogCS.getInstance().stampa("out", "Client della richiesta:  "  + this.getId()); 
        StringBuilder result = new StringBuilder();
        if(inputData == null) return null;
        try {
            centralSystem.addMessageToLog(inputData);
            obj = (JSONObject) parser.parse(inputData);
            
            switch (((String) obj.get("method")).trim().toUpperCase()) {

                case "TEST":
                    centralSystem.notifyChange("Attempted test...");
                    result.append(callCentralSystemTEST((JSONObject) obj.get("data")));
                    break;
                case "CREATEUSER":
                    centralSystem.notifyChange("Attempted creating user...");
                    result.append(callCreateUser((JSONObject) obj.get("data")));
                    break;
                case "MAKEFINE":
                    centralSystem.notifyChange("Attempted making new fine...");
                    result.append(callMakeFine((JSONObject) obj.get("data")));
                    break;
                case "COLLECTORLOGIN":
                    centralSystem.notifyChange("Attempted collector login...");
                    result.append(callCollectorLogin((JSONObject) obj.get("data")));
                    break;
                case "USERLOGIN":
                    centralSystem.notifyChange("Attempted user login...");
                    result.append(callUserLogin((JSONObject) obj.get("data")));
                    break;
                case "CARDPAYMENT":
                    centralSystem.notifyChange("Attempted card payment...");
                    result.append(callCardPayment((JSONObject) obj.get("data")));
                    break;
                case "EXISTSTICKET":
                    centralSystem.notifyChange("Request exists ticket...");
                    result.append(callexistsTicket((JSONObject) obj.get("data")));
                    break;
                case "MYTICKETS":
                    centralSystem.notifyChange("Requesting user codes...");
                    result.append(callMyTickets((JSONObject) obj.get("data")));
                    break; 
                case "REQUESTCODES":
                    centralSystem.notifyChange("Requesting new codes...");
                    result.append(callRequestCodes((JSONObject) obj.get("data")));
                    break;    
                case "UPDATEMACHINESTATUS":
                    result.append(callupdateMachineStatus((JSONObject) obj.get("data")));
                    break;
                case "ADDTICKETSALE":
                    centralSystem.notifyChange("Attempted selling ticket...");
                    result.append(callAddTicketSale((JSONObject) obj.get("data")));
                    break;
                default:
                    LogCS.getInstance().stampa("err", "METODO INESISTENTE");
            }
        } catch (ParseException ex) {
            System.err.println("Error: packet parsing error " + inputData);
        }
        //System.out.println(result.toString());
        //centralSystem.addMessageToLog(result.toString());
        return result.toString();
    }
    
    private String callCreateUser(JSONObject data) {
        
    	String name = ((String) data.get("name"));
    	String surname = ((String) data.get("surname"));
    	String username = ((String) data.get("username"));
    	String cf = ((String) data.get("cf"));
    	String psw = ((String) data.get("psw"));
        boolean result = centralSystem.createUser(name,surname,username,cf,psw);
        data = new JSONObject();
        data.put("data",result);
        
        String notify;
        if(result) notify = "User " + username + " created succesfully";
        else notify = "Something went wrong";
        centralSystem.notifyChange(notify + ". " + Calendar.getInstance().getTime());
        
        return data.toJSONString();
	}

    private String callCardPayment(JSONObject data) {
        String cardNumber = (String) data.get("cardNumber");
        double amount = (double) data.get("amount");
        boolean result = centralSystem.cardPayment(cardNumber, amount);
        data = new JSONObject();
        data.put("data",result);
        
        String notify;
        if(result) notify = "Payment of " + amount + " from " + cardNumber + " was successful";
        else notify = "Something went wrong";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());
        
        return data.toJSONString();
    }
    
    private String callexistsTicket(JSONObject data) {
        String ticketCode = (String) data.get("ticketCode");
        boolean result = centralSystem.existsTicket(Integer.parseInt(ticketCode));
        data = new JSONObject();
        data.put("data", result);
        
        String notify;
        if(result) notify = "Ticket " + ticketCode + " found";
        else notify = "Ticket " + ticketCode + " not found";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());
        
        return data.toJSONString();
    }

    private String callRequestCodes(JSONObject data) {
        int numberOfCodes = centralSystem.requestCodes(((Long)data.get("numberOfCodes")).intValue());
        data.put("data", numberOfCodes);
        
        String notify = "Codes requested to the Central System";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());
        
        return data.toJSONString();
    }

    private String callCentralSystemTEST(JSONObject data) {
        String result = centralSystem.centralSystemTEST((String) data.get("test"));
        data = new JSONObject();
        data.put("data", result);
        
        String notify;
        if(result.equals("test")) notify = "Test successful";
        else notify = "Something went wrong";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());

        return data.toJSONString();
    }

    private String callCollectorLogin(JSONObject data) {
        String username = (String) data.get("username");
        String psw = (String) data.get("psw");
        boolean result = centralSystem.collectorLogin(username, psw);
        data = new JSONObject();
        data.put("data", result);
        
        String notify;
        if(result) notify = "Login as " + username + " was successful";
        else notify = "Login as " + username + " was not successful";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());
        
        return data.toJSONString();
    }

    private String callUserLogin(JSONObject data) {
        String username = (String) data.get("username");
        String psw = (String) data.get("psw");
        boolean result = centralSystem.userLogin(username, psw);
        data = new JSONObject();
        data.put("data", result);
        
        String notify;
        if(result) notify = "Login as " + username + " was successful";
        else notify = "Login as " + username + " was not successful";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());

        return data.toJSONString();
    }

    private String callMakeFine(JSONObject data) {
        String cf = (String)data.get("cf");
        double amount = (Double)data.get("amount");
        Fine fine = new Fine(cf,amount);
        boolean result = centralSystem.addFine(fine);
        data = new JSONObject();
        data.put("data", result);
        
        String notify;
        if(result) notify = "Fine of " + amount + "to " + cf + " was successfully added";
        else notify = "Could not add the new fine";
        centralSystem.notifyChange(notify+ ". " + Calendar.getInstance().getTime());

        return data.toJSONString();
    } 
    
    private String callupdateMachineStatus(JSONObject data) {
        int machineCode = ((Double)data.get("machineCode")).intValue();
        double inkLevel = (double) data.get("inkLevel");
        double paperLevel = (double) data.get("paperLevel");
        boolean active = (boolean) data.get("active");
        //System.out.println(clientSocket.getRemoteSocketAddress().toString());
        centralSystem.updateMachineStatus(machineCode, inkLevel, paperLevel, active, clientSocket.getRemoteSocketAddress().toString());
        data = new JSONObject();
        data.put("data", true);
        return data.toString();
    }
    
    private String callAddTicketSale(JSONObject data) {
        Date expiryDate = new Date();
        try {
            expiryDate = operator.parse((String)data.get("expiryDate"));
        } catch (java.text.ParseException ex) {
            LogCS.getInstance().stampa("err", ex.toString());
            data = new JSONObject();
            data.put("data", false);
            return data.toString();
        }
        int serialCode = ((Long)data.get("serial")).intValue();
        String username =(String) data.get("username");
        String ticketType =(String) data.get("ticketType");
        
        centralSystem.addTicketSale( expiryDate,  serialCode,  username, ticketType);
        data = new JSONObject();
        data.put("data", true);
        return data.toString();
    }

    
    private String callMyTickets(JSONObject data) {
        String username = (String)data.get("username");
        data = new JSONObject();
        ArrayList<TicketDB> listaBiglietti =  centralSystem.MyTicket(username);
        JSONArray JList = new JSONArray();
 
        for (TicketDB ticket : listaBiglietti) {
            JSONObject jTicket = new JSONObject();    
            jTicket.put("id",ticket.getCode());
            jTicket.put("expire",operator.toString(ticket.getExpireTime()));
            jTicket.put("type", ticket.getType());
            JList.add(jTicket);
            
        }
        
        
        data.put("data", JList);
        return data.toString();
    }
    
}
