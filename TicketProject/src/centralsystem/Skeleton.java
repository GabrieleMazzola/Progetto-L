package centralsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
    

    public Skeleton(Socket clientSocket, CSystem centralSystem) {
        this.clientSocket = clientSocket;
        this.centralSystem = centralSystem;
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
            String result = decodeRead(in.readLine());
            out.println(result);

            in.close();
            out.close();

        } catch (IOException ex) {
            System.err.println("Error: socket opening fail");
        }
    }
    
    /**
     * Viene interpretata la stringa in ingresso. In base al suo valore viene
     * fatta un'azione diversa
     * @param inputData
     * @return 
     */
    private String decodeRead(String inputData) {
        JSONObject obj;
        StringBuilder result = new StringBuilder();
        try {
            obj = (JSONObject) parser.parse(inputData);

            switch (((String) obj.get("method")).trim().toUpperCase()) {

                case "TEST":
                    result.append(callCentralSystemTEST((JSONObject) obj.get("data")));
                    break;
                case "CREATEUSER":
                    result.append(callCreateUser((JSONObject) obj.get("data")));
                    break;
                case "MAKEFINE":
                    result.append(callMakeFine((JSONObject) obj.get("data")));
                    break;
                case "COLLECTORLOGIN":
                    result.append(callCollectorLogin((JSONObject) obj.get("data")));
                    break;
                case "USERLOGIN":
                    result.append(callUserLogin((JSONObject) obj.get("data")));
                    break;
                case "CARDPAYMENT":
                    result.append(callCardPayment((JSONObject) obj.get("data")));
                    break;
                case "EXISTSTICKET":
                    result.append(callexistsTicket((JSONObject) obj.get("data")));
                    break;
                case "REQUESTCODES":
                    result.append(callRequestCodes());
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
    
    private String callCreateUser(JSONObject data) {
    	String name = ((String) data.get("name"));
    	String surname = ((String) data.get("surname"));
    	String username = ((String) data.get("username"));
    	String cf = ((String) data.get("cf"));
    	String psw = ((String) data.get("psw"));
        boolean result = centralSystem.createUser(name,surname,username,cf,psw);
        data = new JSONObject();
        data.put("data",result);
        
        return data.toJSONString();
	}

    private String callCardPayment(JSONObject data) {
        boolean result = centralSystem.cardPayment((String) data.get("cardNumber"));
        data = new JSONObject();
        data.put("data",result);
        
        return data.toJSONString();
    }
    
    private String callexistsTicket(JSONObject data) {
        boolean result = centralSystem.existsTicket((String) data.get("ticketCode"));
        data = new JSONObject();
        data.put("data", result);
        
        return data.toJSONString();

    }

    private String callRequestCodes() {
        String result = centralSystem.requestCodes();
        JSONObject data = new JSONObject();
        data.put("data", result);
        
        return data.toJSONString();
    }

    private String callCentralSystemTEST(JSONObject data) {
        String result = centralSystem.centralSystemTEST((String) data.get("test"));
        data = new JSONObject();
        data.put("data", result);

        return data.toJSONString();
    }

    private String callCollectorLogin(JSONObject data) {
        boolean result = centralSystem.collectorLogin((String) data.get("username"), (String) data.get("psw"));
        data = new JSONObject();
        data.put("data", result);

        return data.toJSONString();
    }

    private String callUserLogin(JSONObject data) {
        boolean result = centralSystem.userLogin((String) data.get("username"), (String) data.get("psw"));
        data = new JSONObject();
        data.put("data", result);

        return data.toJSONString();
    }

    private String callMakeFine(JSONObject data) {
        Fine fine = new Fine((String)data.get("cf"),(Double)data.get("amount"));
        boolean result = centralSystem.addFine(fine);
        data = new JSONObject();
        data.put("data", result);

        return data.toJSONString();
    } 
    private String callupdateMachineStatus(JSONObject data) {
        centralSystem.updateMachineStatus(((Double)data.get("machineCode")).intValue(), (double) data.get("inkLevel"), (double) data.get("paperLevel"), (boolean) data.get("active"));
        data = new JSONObject();
        data.put("data", true);
        return data.toString();
    }

}
