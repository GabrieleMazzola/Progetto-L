package ticketCollector;

import ticketCollector.Fine;
import centralsystem.CentralSystemCollectorInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class StubCollector implements CentralSystemCollectorInterface{

    String ipAdress;
    int port;
    Socket socket;
    BufferedReader fromServer;
    PrintWriter toServer;
    
    public StubCollector(String ipAddress,int port){
        this.ipAdress = ipAddress;
        this.port = port;
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
    
    private void closeConnection(){
        try {
            fromServer.close();
            toServer.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public boolean existsTicket(String ticketCode) {
        try{
            initConnection();

            String packet = existsJSONPacket(ticketCode);
            System.out.println(packet);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            String line = fromServer.readLine();
            closeConnection();

            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);

            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean)obj.get("data");
        }catch(IOException|ParseException ex){
            ex.printStackTrace();
            closeConnection();
            return false;
        }
    }

    @Override
    public void makeFine(Fine f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean login(String username, String psw) {
        try {
            initConnection();
            
            String packet = loginJSONPacket(username, psw);
            toServer.println(packet);                           //Invio verso server della richiesta JSON
            
            String line = fromServer.readLine();
            closeConnection();
            
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            
            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean)obj.get("data");
                
        }catch(IOException|ParseException ex){
            ex.printStackTrace();
            closeConnection();
            return false;
        }        
    }
    
    private String loginJSONPacket(String username, String psw) {
        //{"method":"LOGIN","data":{"username":"String","psw":"String"}}

        JSONObject root = new JSONObject();
        root.put("method", "LOGIN");
        JSONObject data = new JSONObject();
        data.put("username", username);
        data.put("psw", psw);
        root.put("data", data);

        return root.toJSONString();
    }

    private String existsJSONPacket(String ticketCode) {
        //{"method":"EXISTSTICKET","data":{"ticketCode":"String"}}
        
        JSONObject root = new JSONObject();
        root.put("method", "EXISTSTICKET");
        JSONObject data = new JSONObject();
        data.put("ticketCode", ticketCode);
        root.put("data", data);

        return root.toJSONString();
    }
    
}
