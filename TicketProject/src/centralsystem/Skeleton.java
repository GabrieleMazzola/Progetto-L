package centralsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Skeleton extends Thread {

    Socket clientSocket;
    CSystem centralSystem;
    BufferedReader in;
    String inputData;
    PrintWriter out;
    JSONParser parser;
    

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
        System.out.println(inputData);
        StringBuilder result = new StringBuilder();
        try {
            obj = (JSONObject) parser.parse(inputData);

            switch ((String) obj.get("method")) {
                case "LOGIN":
                    result.append(callLogin((JSONObject) obj.get("data")));
                    break;
                case "CARDPAYMENT":
                    result.append(callCardPayment((JSONObject) obj.get("data")));
                    break;
                case "EXISTSTICKET":
                    result.append(callexistsTicket((JSONObject) obj.get("data")));
                    break;
                case "UPDATEMACHINESTATUS":
                    result.append(callupdateMachineStatus((JSONObject) obj.get("data")));
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
        boolean result = centralSystem.login((String) data.get("username"), (String) data.get("psw"));
        data = new JSONObject();
        data.put("data", result);

        return data.toString();
    }

    private String callCardPayment(JSONObject data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    private String callexistsTicket(JSONObject data) {
        boolean result = centralSystem.existsTicket((String) data.get("ticketCode"));
        data = new JSONObject();
        data.put("data", result);
        
        return data.toString();

    }

    private String callupdateMachineStatus(JSONObject data) {

        centralSystem.updateMachineStatus((int) data.get("machineCode"), (double) data.get("inkLevel"), (double) data.get("papaerLevel"), (boolean) data.get("active"));
        data = new JSONObject();
        data.put("data", true);
        
        return data.toString();
    }

}
