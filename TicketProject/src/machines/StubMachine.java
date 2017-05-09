package machines;

import JSONSingleton.JSONOperations;
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
    JSONOperations JSONOperator;

    public StubMachine(String ipAdress, int port) {
        this.ipAdress = ipAdress;
        this.port = port;
        JSONOperator = JSONOperations.getInstance();
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

    private void closeConnection() {
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

            //String packet = loginJSONPacket(username, psw);
            String packet = JSONOperator.loginPacket(username, psw);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            String line = fromServer.readLine();
            closeConnection();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(line);

            //Struttura JSON di risposta : {"data":"boolean"}
            return (Boolean) obj.get("data");

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            closeConnection();
            return false;
        }
    }

    @Override
    public String requestCodes() {
        initConnection();
        //String packet = requestCodesJSONPacket();
        String packet = JSONOperator.requestCodesPacket();
        toServer.println(packet);

        //TODO thread per richiesta codici
        closeConnection();
        return "CODICIBELLISSIMIINARRIVO";
    }

    @Override
    public boolean cardPayment(String cardNumber) {
        try {
            initConnection();
            //String packet = cardPaymentJSONPacket(cardNumber);
            String packet = JSONOperator.cardPaymentPacket(cardNumber);
            toServer.println(packet);                           //Invio verso server della richiesta JSON

            //Aspetto risposta da parte del server
            String line = fromServer.readLine();
            closeConnection();

            JSONParser parser = new JSONParser();

            //Struttura JSON di risposta : {"data":"boolean"}
            JSONObject obj = (JSONObject) parser.parse(line);
            return (Boolean) obj.get("data");

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            closeConnection();
            return false;
        }
    }

    public boolean checkCreditCard(String credCardNumber) {
        return true;
    }
}
