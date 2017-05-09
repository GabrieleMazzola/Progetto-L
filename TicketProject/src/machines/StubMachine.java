package machines;

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

    public StubMachine(String ipAdress, int port) {
        this.ipAdress = ipAdress;
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

    @Override
    public String requestCodes() {
        initConnection();
        String packet = requestCodesJSONPacket();
        toServer.println(packet);
        
        
        //TODO thread per richiesta codici
        closeConnection();
        return "CODICIBELLISSIMIINARRIVO";
    }

    @Override
    public boolean cardPayment(String cardNumber) {
        try{
            initConnection();
            String packet = cardPaymentJSONPacket(cardNumber);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            //Aspetto risposta da parte del server
            String line = fromServer.readLine();
            closeConnection();
            
            JSONParser parser = new JSONParser();
            
            //Struttura JSON di risposta : {"data":"boolean"}
            JSONObject obj = (JSONObject)parser.parse(line);
            return (Boolean)obj.get("data");
            
        }catch(IOException|ParseException ex){
            ex.printStackTrace();
            closeConnection();
            return false;
        }
    }
    
    private String cardPaymentJSONPacket(String cardNumber){
        //{"method":"CARDPAYMENT","data":{"cardNumber":"String"}}
        
        JSONObject root = new JSONObject();
        root.put("method", "CARDPAYMENT");
        JSONObject data = new JSONObject();
        data.put("cardNumber", cardNumber);
        root.put("data", data);
        return root.toJSONString();
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

    private String requestCodesJSONPacket() {
        //{"method":"REQUESTCODES"}
        
        JSONObject root = new JSONObject();
        root.put("method", "REQUESTCODES");
        return root.toJSONString();
    }


}
