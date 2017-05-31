/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machines;

import JSONSingleton.JSONOperations;
import codegeneration.CodeHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Simone
 */
public class RequestCodesThread extends Thread{
    private int startNumber;
    private int numberOfCodes;
    private TicketMachine machine;
    private Socket socket;
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private JSONOperations JSONOperator;
    private String ipAdress;
    private int port;
    private CodeHandler code;
    ArrayList<Integer> serialNumbers = new ArrayList();
    
    public RequestCodesThread(TicketMachine machine,Socket socket,
            BufferedReader fromServer,PrintWriter toServer,JSONOperations JSONOperator,
            String ipAdress, int port, int numberOfCodes){
        
        super();
        this.machine = machine;
        this.socket = socket;
        this.fromServer  = fromServer;
        this.fromServer = fromServer;
        this.toServer = toServer;
        this.JSONOperator = JSONOperator;
        this.ipAdress = ipAdress;
        this.port = port;
        this.numberOfCodes = numberOfCodes;
        this.code = CodeHandler.getInstance();
        this.serialNumbers = new ArrayList();
    }
    
    /**
     * Viene stabilita una connessione con il server. Quando sono necessari nuovi
     * codici si prende in carico la lettura della stringa dal server
     */
    @Override
    public void run() {
        try{
            //machine.setRequestCodesActive();
            
            initConnection();
            //String packet = requestCodesJSONPacket();
            String packet = JSONOperator.requestCodesPacket(numberOfCodes);
            toServer.println(packet);
            //System.out.println(packet);

            //TODO thread per richiesta codici

            String line = fromServer.readLine();
            closeConnection();

            JSONParser parser = new JSONParser();

            //Struttura JSON di risposta : {"data":"String"}
            JSONObject obj = (JSONObject) parser.parse(line);
            startNumber = (((Long)obj.get("data")).intValue());    //salva in macchinetta
            makeSerialsArray(startNumber,startNumber+numberOfCodes);
            machine.addTicketSerials(serialNumbers);
        
            //machine.setRequestCodesInactive();
        }catch(IOException | ParseException ex) {
            ex.printStackTrace();
            closeConnection();
        }
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

    /**
     * 
     * @param startNumber da dove partono i biglietti validi
     * @param finalNumber è numero che ci segnala quanti biglietti dobbimo validare da starNumber
     * @return serialNumbers un vettore che contiene i codici validi da passare alla macchinetta
     */
    private ArrayList<Integer> makeSerialsArray(int startNumber, int finalNumber) {
      
        for (int i = startNumber; i < finalNumber; i++) {
            serialNumbers.add(i);
        }
        return serialNumbers;
    }
}
