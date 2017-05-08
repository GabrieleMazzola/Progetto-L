/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Boolean.TRUE;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Zubeer
 */
public class Skeleton extends Thread {

    Socket clientSocket = null;
    BufferedReader in = null;
    PrintWriter out = null;
    JSONParser parser = null;
    String inputData = null;
    
    CSystem centralSystem;
    
    public Skeleton(Socket clientSocket, CSystem centralSystem) {
        this.clientSocket = clientSocket;
        this.centralSystem = centralSystem;
        parser = new JSONParser();
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            String result = decodeRead(in.readLine());
            out.println(result);

            in.close();
            out.close();

        } catch (IOException ex) {
            System.err.println("Error: socket opening fail");
        }
    }

    private String decodeRead(String inputData) {
        JSONObject obj;
        StringBuilder result = new StringBuilder();
        try {
            obj = (JSONObject) parser.parse(inputData);
            
            switch ((String)obj.get("method")) {
                case "LOGIN":
                       result.append(callLogin((JSONObject)obj.get("data")));
                    break;
                case "CARDPAYMENT":
                       result.append(callCardPayment((JSONObject)obj.get("data")));
                    break;
                case "ISVALID":
                       result.append(callIsValid((JSONObject)obj.get("data")));
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (ParseException ex) {
            System.err.println("Error: packet parsing error " + inputData);
        }
        
        
        return result.toString();
    }
    private String callLogin(JSONObject data) {
          boolean result = centralSystem.login((String)data.get("username"),(String)data.get("psw"));
          data = new JSONObject();
          data.put("data",result);
         
          return data.toString();
    }

    private String callCardPayment(JSONObject jsonObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String callIsValid(JSONObject data) {
         boolean result = centralSystem.isValid((String)data.get("ticketCode"));
          data = new JSONObject();
          data.put("data",result);
         
          return data.toString();
    }
    

}
