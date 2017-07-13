package communication;

import centralsystem.CSystem;
import centralsystem.commands.*;
import console.LogCS;
import java.io.*;
import java.net.Socket;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.Date;
import enums.jsonenumerations.AddSale;
import enums.jsonenumerations.CardPayment;
import enums.jsonenumerations.CollectorLogin;
import enums.jsonenumerations.CreateUser;
import enums.jsonenumerations.ExistsTicket;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.MakeFine;
import enums.jsonenumerations.MyTickets;
import enums.jsonenumerations.MyValidTickets;
import enums.jsonenumerations.RequestCodes;
import enums.jsonenumerations.RequestFinesStartNumber;
import enums.jsonenumerations.StatisticsInformation;
import enums.jsonenumerations.TicketTypes;
import enums.jsonenumerations.UpdateMachineStatus;
import enums.jsonenumerations.UserLogin;
import java.net.SocketException;


public class Skeleton extends Thread {
    
    private CSystem csystem;
    private Socket clientSocket;
    private BufferedReader fromClient;
    private PrintWriter toClient;
    private Map<String,Command> commandMap;
    
    public Skeleton(Socket clientSocket,CSystem system){
        this.csystem = system;
        this.clientSocket = clientSocket;
        commandMap = new HashMap<>();
        initCommands();
    }

    @Override
    public void run() {
        try {
            fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            toClient = new PrintWriter(clientSocket.getOutputStream(),true);
            
            //LogCS.getInstance().print("out", "\n\n---------------------"); 
            LogCS.getInstance().print("err", "Time: " + (new Date()).toString());
            LogCS.getInstance().print("err", "ID :  "  + this.getId()); 
            LogCS.getInstance().print("err", "---------------------");
            
            while(!clientSocket.isClosed()){
                
                String request = fromClient.readLine();
                
                String result = decodeRequest(request);
                                
                if(result == null){
                    clientSocket.close();
                    break;
                }
                toClient.println(result);
                
                LogCS.getInstance().print("err", "Sent to client :  "  + result); 
                LogCS.getInstance().print("err", "---------------------");

            }
            LogCS.getInstance().print("err", "Connessione chiusa, ID chiuso :  "  + this.getId());
            
            fromClient.close();
            toClient.close();
            clientSocket.close();
        }catch (SocketException e){

        } catch (IOException ex) {
           ex.printStackTrace();
        } 
    }

    private synchronized String decodeRequest(String jsonRequest) {

        if(jsonRequest == null){
            return null;
        }
        
        JSONObject obj;
        JSONParser parser = new JSONParser();
       
        LogCS.getInstance().print("err", "---------------------"); 
        LogCS.getInstance().print("err", "Client della richiesta:  "  + this.getId()); 
        LogCS.getInstance().print("err", "Richiesta in ingresso: "  + jsonRequest);
        
        StringBuilder result = new StringBuilder();
       try{
            obj = (JSONObject) parser.parse(jsonRequest);            
            result.append(commandMap.get(((String) obj.get(JsonFields.METHOD.toString()))).execute((JSONObject) obj.get(JsonFields.DATA.toString())));           
        } catch (ParseException ex) {   
            ex.printStackTrace();
        }
        return result.toString();
    }

    private void initCommands() {
        commandMap.put(CreateUser.CREATEUSER.toString(), new CallCreateUserCommand(csystem));
        commandMap.put(MakeFine.MAKEFINE.toString(), new CallAddFineCommand(csystem));
        commandMap.put(CollectorLogin.COLLECTORLOGIN.toString(), new CallCollectorLoginCommand(csystem));
        commandMap.put(UserLogin.USERLOGIN.toString(), new CallUserLoginCommand(csystem));
        commandMap.put(CardPayment.CARDPAYMENT.toString(), new CallCardPaymentCommand(csystem));
        commandMap.put(ExistsTicket.EXISTSTICKET.toString(), new CallExistsTicketCommand(csystem));
        commandMap.put(MyTickets.MYTICKETS.toString(), new CallMyTicketsCommand(csystem));
        commandMap.put(RequestCodes.NUMBEROFCODES.toString(), new CallRequestCodesCommand(csystem));
        commandMap.put(UpdateMachineStatus.UPDATEMACHINESTATUS.toString(), new CallUpdateMachineStatusCommand(csystem));
        commandMap.put(AddSale.ADDSALE.toString(), new CallAddSaleCommand(csystem));
        commandMap.put(TicketTypes.TICKETTYPES.toString(), new CallTicketTypesCommand(csystem));
        commandMap.put(StatisticsInformation.STATISTICSINFORMATION.toString(), new CallStatisticsInformationCommand(csystem));
        commandMap.put(MyValidTickets.MYVALIDTICKETS.toString(), new CallMyValidTicketsCommand(csystem));
        commandMap.put(RequestFinesStartNumber.REQUESTFINESSTARTNUMBER.toString(), new CallRequestFinesStartNumberCommand(csystem));
    }    
}
