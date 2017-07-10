package communication;


import centralsystem.interfaces.CentralSystemCollectorInterface;
import items.Fine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import jsonenumerations.JsonFields;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import singleton.JSONOperations;


public class StubCollector implements CentralSystemCollectorInterface{
    private String ipAdress;
    private int port;
    private Socket socket;
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private ArrayList<Fine> offlineFines; 
    
    public StubCollector(String ipAddress,int port) throws IOException {
        this.ipAdress = ipAddress;
        this.port = port;
        offlineFines = new ArrayList<>();
        socket = new Socket();
        socket.setSoTimeout(500);
        initConnection();
       
    }
    
    public void initConnection() throws IOException {
        socket = new Socket(ipAdress, port);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream(), true);
    }
    
    public void closeConnection() {
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
    public Boolean existsTicket(long serialcode) {
       
        try{
            
            testConnection();
            String packet = JSONOperations.getInstance().existsTicketPacket(serialcode);
            toServer.println(packet); 
            String line = fromServer.readLine();
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);

            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean)obj.get(JsonFields.DATA.toString());
        }catch(ParseException ex){
            ex.printStackTrace();
        }catch(IOException ex){
           closeConnection();
        }
        return null;
    }

    @Override
    public boolean addFine(Fine f){
        addOfflineFine(f);

        try{
            
            testConnection();
            boolean ret = saveFinesOnline();
            
            //Struttura JSON di risposta : {"data":"boolean"}
            return ret;
        }catch(ParseException ex){
            ex.printStackTrace();
            return false;
        }catch(IOException ex){
            closeConnection();
            return true;
        }
    }

    @Override
    public boolean collectorLogin(String username, String psw) {
        try {

            
            String packet = JSONOperations.getInstance().collectorLoginPacket(username, psw);
            toServer.println(packet);                           //Invio verso server della richiesta JSON
            
            String line = fromServer.readLine();
            
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            
            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean)obj.get(JsonFields.DATA.toString());
                
        }catch(IOException|ParseException ex){
            ex.printStackTrace();
            return false;
        }        
    }
    
    private void addOfflineFine(Fine fine){
        this.offlineFines.add(fine);
    }
    
    private boolean saveFinesOnline() throws IOException, ParseException{

        while(!socket.isClosed() && !offlineFines.isEmpty()){
            Fine f = offlineFines.get(0);
            String packet = JSONOperations.getInstance().makeFinePacket(f);
            toServer.println(packet); //Invio verso server della richiesta JSON
            String line = fromServer.readLine(); 
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            
            if(!(Boolean)obj.get(JsonFields.DATA.toString()))
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

    private void testConnection() throws IOException  {
        if(socket.isClosed()){
                initConnection();
                
        }
    }

    public Long requestFinesStartNumber(String collectorUsername) {
        try {
            
            String packet = JSONOperations.getInstance().requestFinesStartNumberPacket(collectorUsername);
            toServer.println(packet);                           //Invio verso server della richiesta JSON
            
            String line = fromServer.readLine();
            JSONParser parser = new JSONParser();               
            JSONObject obj = (JSONObject)parser.parse(line);
            
            return (Long)obj.get(JsonFields.DATA.toString());
        }catch(IOException|ParseException ex){
            ex.printStackTrace();
        } 
        return null;
    }
}