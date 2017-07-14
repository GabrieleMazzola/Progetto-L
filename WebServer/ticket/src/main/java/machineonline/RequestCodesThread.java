package machineonline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import jsonenumerations.JsonFields;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import singleton.JSONOperations;


public class RequestCodesThread extends Thread{
    private long startNumber;
    private long numberOfCodes;
    private TicketOnline machine;
    private Socket socket;
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private List<Long> serialNumbers = new ArrayList<>();
    
    public RequestCodesThread(TicketOnline machine,String systemAddress, int systemPort,long numberOfCodes) throws IOException{
        
        super();
        this.machine = machine;
        this.socket = new Socket(systemAddress, systemPort);
        this.numberOfCodes = numberOfCodes;
        this.serialNumbers = new ArrayList<>();
        
        
    }
    
    /**
     * Viene stabilita una connessione con il server. Quando sono necessari nuovi
     * codici si prende in carico la lettura della stringa dal server
     */
    @Override
    public void run() {
        try{
            toServer = new PrintWriter(socket.getOutputStream(),true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String packet = JSONOperations.getInstance().requestCodesPacket(numberOfCodes);
            
            System.out.println("Sending to system : "+ packet);
            toServer.println(packet);

            String line = fromServer.readLine();
            System.out.println("result : " + line);
            JSONParser parser = new JSONParser();
            
            socket.close();
            toServer.close();
            fromServer.close();
            
            //Struttura JSON di risposta : {"DATA":"String"}            
            JSONObject obj = (JSONObject) parser.parse(line);
            startNumber = (((Long)obj.get(JsonFields.DATA.toString())).intValue());
            makeSerialsArray(startNumber,startNumber+numberOfCodes);
            machine.addTicketSerials(serialNumbers);   
            machine.printSerials();
            //machine.setRequestCodesInactive();
        }catch(IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 
     * @param startNumber da dove partono i biglietti validi
     * @param finalNumber è numero che ci segnala quanti biglietti dobbimo validare da starNumber
     * @return serialNumbers un vettore che contiene i codici validi da passare alla macchinetta
     */
    private List<Long> makeSerialsArray(long startNumber, long finalNumber) {
      
        for (long i = startNumber; i < finalNumber; i++) {
            serialNumbers.add(i);
        }
        return serialNumbers;
    }
}
