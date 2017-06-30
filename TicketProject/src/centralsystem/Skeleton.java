package centralsystem;

import DateSingleton.DateOperations;
import commands.CallAddTicketSaleCommand;
import commands.CallCardPayementCommand;
import commands.CallCentralSystemTestCommand;
import commands.CallCollectorLoginCommand;
import commands.CallCreateUserCommand;
import commands.CallExistsTicketCommand;
import commands.CallMakeFineCommand;
import commands.CallMyTicketsCommand;
import commands.CallRequestCodesCommand;
import commands.CallUpdateMachineStatusCommand;
import commands.CallUserLoginCommand;
import commands.Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ticket.Ticket;
import ticketCollector.Fine;

public class Skeleton extends Thread {
    Socket clientSocket;
    CSystem centralSystem;
    BufferedReader in;
    String inputData;
    PrintWriter out;
    JSONParser parser;
    DateOperations operator;
    Map<String,Command> callCommand = new HashMap<>();

    public Skeleton(Socket clientSocket, CSystem centralSystem) {
        this.clientSocket = clientSocket;
        this.centralSystem = centralSystem;
        this.operator = DateOperations.getInstance();
        parser = new JSONParser();
        initCommands();
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
            
            LogCS.getInstance().print("out", "\n\n---------------------"); 
            LogCS.getInstance().print("out", "Time: " + (new Date()).toString());
            LogCS.getInstance().print("out", "Client connesso:  "  + clientSocket.getInetAddress()); 
            LogCS.getInstance().print("out", "ID :  "  + this.getId()); 
            LogCS.getInstance().print("out", "---------------------"); 
            
            while(!clientSocket.isClosed()) {
                String result = decodeRead(in.readLine());
                out.println(result);
                
                LogCS.getInstance().print("out", "Sending to client :  "  + result); 
                LogCS.getInstance().print("out", "---------------------"); 
            }
            
            LogCS.getInstance().print("out", "Connessione chiusa, ID chiuso :  "  + this.getId()); 
            
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
       
        LogCS.getInstance().print("out", "\n---------------------"); 
        LogCS.getInstance().print("out", "Client della richiesta:  "  + this.getId()); 
        LogCS.getInstance().print("out", "Richiesta in ingresso: "  + inputData);
        
        StringBuilder result = new StringBuilder();
        try {
            centralSystem.addMessageToLog(inputData);
            obj = (JSONObject) parser.parse(inputData);            
            result.append(callCommand.get(((String) obj.get("method")).trim().toUpperCase()).execute((JSONObject) obj.get("data")));           
        } catch (ParseException ex) {
            System.err.println("Error: packet parsing error " + inputData);
        }       
        centralSystem.addMessageToLog(result.toString());
        return result.toString();
    }
    private void initCommands(){
        callCommand.put("TEST", new CallCentralSystemTestCommand(centralSystem));
        callCommand.put("CREATEUSER", new CallCreateUserCommand(centralSystem));
        callCommand.put("MAKEFINE", new CallMakeFineCommand(centralSystem));
        callCommand.put("COLLECTORLOGIN", new CallCollectorLoginCommand(centralSystem));
        callCommand.put("USERLOGIN", new CallUserLoginCommand(centralSystem));
        callCommand.put("CARDPAYMENT", new CallCardPayementCommand(centralSystem));
        callCommand.put("EXISTSTICKET", new CallExistsTicketCommand(centralSystem));
        callCommand.put("MYTICKETS", new CallMyTicketsCommand(centralSystem));
        callCommand.put("REQUESTCODES", new CallRequestCodesCommand(centralSystem));
        callCommand.put("UPDATEMACHINESTATUS", new CallUpdateMachineStatusCommand(centralSystem,clientSocket));
        callCommand.put("ADDTICKETSALE", new CallAddTicketSaleCommand(centralSystem));
    }
}
