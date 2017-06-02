package ticketCollector;


import JSONSingleton.JSONOperations;
import centralsystem.CentralSystemCollectorInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class StubCollector implements CentralSystemCollectorInterface{
    private String ipAdress;
    private int port;
    private Socket socket;
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private JSONOperations JSONOperator;
    private ArrayList<Fine> offlineFines; 
    
    public StubCollector(String ipAddress,int port){
        this.ipAdress = ipAddress;
        this.port = port;
        this.JSONOperator = JSONOperations.getInstance();   //pattern singleton
        offlineFines = new ArrayList<>();
    }
    
    private void initConnection() throws IOException {
        socket = new Socket(ipAdress, port);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream(), true);
    }
    
    private void closeConnection() throws IOException{
        fromServer.close();
        toServer.close();
        socket.close();
    }
    
    @Override
    public boolean existsTicket(int ticketCode) {
        try{
            initConnection();

            String packet = JSONOperator.existsTicketPacket(ticketCode);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            String line = fromServer.readLine();
            closeConnection();

            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);

            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean)obj.get("data");
        }catch(IOException|ParseException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean makeFine(Fine f){
        addOfflineFine(f);
        try{
            initConnection();
            boolean ret = saveFinesOnline();
            closeConnection();
            
            //Struttura JSON di risposta : {"data":"boolean"}
            return ret;
        }catch(IOException|ParseException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean collectorLogin(String username, String psw) {
        try {
            initConnection();
            
            String packet = JSONOperator.collectorLoginPacket(username, psw);
            toServer.println(packet);                           //Invio verso server della richiesta JSON
            
            String line = fromServer.readLine();
            closeConnection();
            
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            
            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean)obj.get("data");
                
        }catch(IOException|ParseException ex){
            ex.printStackTrace();
            return false;
        }        
    }

    @Override
    public String centralSystemTEST(String sentTest) {
        return sentTest;
    }
    
    private void addOfflineFine(Fine fine){
        this.offlineFines.add(fine);
    }
    
    private boolean saveFinesOnline() throws IOException, ParseException{
        System.out.println("Connesso: " + socket.isConnected());
        while(socket.isConnected() && !offlineFines.isEmpty()){
            Fine f = offlineFines.get(0);
            String packet = JSONOperator.makeFinePacket(f);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            String line = fromServer.readLine();
            
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            if(!(Boolean)obj.get("data"))
                return false;
            else{
                offlineFines.remove(0);
            }
        }
        return true;
    }
    
    public int getOfflineFinesSize() {
        return offlineFines.size();
    }
}
