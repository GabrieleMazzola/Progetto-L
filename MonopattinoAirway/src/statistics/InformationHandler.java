package statistics;

import enums.jsonenumerations.*;
import items.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.Date;
import java.util.logging.*;
import org.jfree.data.general.DefaultPieDataset;
import org.json.simple.*;
import org.json.simple.parser.*;
import singleton.*;


public class InformationHandler {
    Map<String, Product> productMap;
    List<Sale> saleList;
    List<Sale> saleLoggedList;
    List<Sale> saleNonloggedList;
    
    
    Socket socket;
    PrintWriter toServer;
    BufferedReader fromServer;
    

    public InformationHandler(String cSystemIP) throws IOException{
        initInformations(cSystemIP);
    }

    private void initInformations(String cSystemIP) throws IOException {
        
        socket = new Socket(cSystemIP,5000);
        toServer = new PrintWriter(socket.getOutputStream(),true);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        initProductList();


        String packet = "{\"METHOD\":\"STATISTICSINFORMATION\"}";
        toServer.println(packet); 
        String reply  = fromServer.readLine();
        System.out.println("Reply: " + reply);

        parseStatisticsInformation(reply);
        
        updateLoggedUsersSales();
        updateNonloggedUsersSales();

        System.out.println(saleList);
    }

    private void initProductList() {
        productMap = new HashMap<>();
        
        try {
            String packet = JSONOperations.getInstance().requestTicketTypesPacket();
            toServer.println(packet);
            String line = fromServer.readLine();
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(line);
            JSONArray typesArray = (JSONArray) obj.get(JsonFields.DATA.toString());
            
            for(int i = 0; i<typesArray.size(); i++){
                JSONObject prodObj = (JSONObject)typesArray.get(i);
                
                String type = (String)prodObj.get(TicketTypes.TYPE.toString());
                Double cost = (Double)prodObj.get(TicketTypes.COST.toString());
                String description = (String)prodObj.get(TicketTypes.DESCRIPTION.toString());
                Long bufferDuration = (Long)prodObj.get(TicketTypes.DURATION.toString());
                Integer duration = Integer.valueOf(bufferDuration.toString());
                

        productMap.put("T1",new SimpleTicket("Short Ticket","T1",1.30,90));
        productMap.put("T2",new SimpleTicket("Medium Ticket","T2",1.60,120));
        productMap.put("T3",new SimpleTicket("Long Ticket","T3",1.90,150));
        productMap.put("T4",new SimpleTicket("Very Short Ticket","T4",1, 1));
        productMap.put("S1",new SimpleSeason("Monthly Season","S1",5,1));
        productMap.put("S2",new SimpleSeason("Semestral Season","S2",3,6));
        productMap.put("S3",new SimpleSeason("Annual Season","S3",2, 12));
        productMap.put("S4", new SimpleSeason("Trimestral Season", "S4", 3.5, 3));
        productMap.put("P1",new PhisicalSimpleTicket("Physical Short Ticket","P1",1.30,90));
        productMap.put("P2",new PhisicalSimpleTicket("Physical Medium Ticket","P2",1.60,120));
        productMap.put("P3",new PhisicalSimpleTicket("Physical Long Ticket","P3",1.90,150));
        productMap.put("P4",new PhisicalSimpleTicket("Very Short Physical Ticket","P4",1, 1));
        productMap.put("Q1",new PhisicalSimpleSeason("Physical Monthly Season","Q1",5,1));
        productMap.put("Q2",new PhisicalSimpleSeason("Physical Semestral Season","Q2",3,6));
        productMap.put("Q3",new PhisicalSimpleSeason("Physical Annual Season","Q3",2, 12)); 
    
        
        
        
            }  
        } catch (ParseException ex) {
                System.err.println("Error: SubMachine.java - updateMachineStatus() parsing error");
        } catch (IOException ex) {
            Logger.getLogger(InformationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void parseStatisticsInformation(String reply) {
        try {
            JSONParser parse = new JSONParser();
            JSONObject obj = (JSONObject) parse.parse(reply);
            
            JSONArray jsonSaleList = (JSONArray)obj.get("DATA");
            saleList = new ArrayList<>();
            
            for (int i = 0; i < jsonSaleList.size(); i++) {
                JSONObject objIesimo = (JSONObject)jsonSaleList.get(i);
                String type = (String)objIesimo.get("TYPE");
                Product prod = productMap.get(type);
                
                Long serialCode = (Long)objIesimo.get("SERIALCODE");
                Date saleDate = DateOperations.getInstance().parse((String)objIesimo.get("SALEDATE"));
                String username = (String)objIesimo.get("USERNAME");
                String sellerMachineIP = (String)objIesimo.get("SELLERMACHINEIP");
                
                saleList.add(new Sale(saleDate, serialCode, username, prod, sellerMachineIP));
                
            }
            
        } catch (ParseException|java.text.ParseException ex) {
            System.err.println("PARSING EXCEPTION");
        }
    }
    
    /**
     * Aggiornamento statistics information 
     */
    public void update(){
        try {
            String packet = "{\"METHOD\":\"STATISTICSINFORMATION\"}";
            toServer.println(packet);
            String reply  = fromServer.readLine();
            System.out.println("Reply: " + reply);
            
            parseStatisticsInformation(reply);
            
            this.updateLoggedUsersSales();
            this.updateNonloggedUsersSales();
            
        } catch (IOException ex) {
            Logger.getLogger(InformationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    private void updateLoggedUsersSales(){
        
        List<Sale> loggedSales = new ArrayList<>();
        
        for(Sale s : saleList){
            if(!s.getUsername().equals("-")){
                loggedSales.add(s);
            }
        }

                
        
        saleLoggedList = loggedSales;
    }    
    
    private void updateNonloggedUsersSales(){
        
        List<Sale> nonLoggedSales = new ArrayList<>();
        
        for(Sale s : saleList){
            if(s.getUsername().equals("-")){
                nonLoggedSales.add(s);
            }
        }
        
        this.saleNonloggedList = nonLoggedSales;
    }
    
    public Map<String,Product> getProductMap() {
        return productMap;
    }

    public List<Sale> getSaleList() {
        return saleList;
    } 

    public List<Sale> getSaleLoggedList() {
        return saleLoggedList;
    }

    public List<Sale> getSaleUnloggedList() {
        return saleNonloggedList;
    }
/**
 * Dataset per la creazione di un grafico a torta riferito alle vendite di biglietti fatte ad utenti registrati
 * @return Un dataset filtrato dei bliglietti venduti ad utenti registrati
 */    
    public DefaultPieDataset cookLoggedPie(){
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        Set<String> types = productMap.keySet();
        
        
        
        for(String s : types){
            if(s.charAt(0) == 'S' || s.charAt(0) == 'T'){
                dataset.setValue(s, new Double(0));
            }
        }
        
        for(Sale s : saleLoggedList){
            String type = s.getType();
            dataset.setValue(type, dataset.getValue(type).intValue() + 1);

        }
        
        return dataset;
        
    }
/**
 * Dataset per la creazione di un grafico a torta riferito alle vendite di biglietti fatte ad utenti non registrati
 * @return Un dataset filtrato dei biglietti venduti ad utenti non registrati (biglietti fisici)
 */    
    public DefaultPieDataset cookNonloggedPie(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        Set<String> types = productMap.keySet();
        
                
        for(String s : types){
            if(s.charAt(0) == 'Q' || s.charAt(0) == 'P'){
                dataset.setValue(s, new Double(0));
            }
        }

        
        for(Sale s : saleNonloggedList){
            String type = s.getType();
            dataset.setValue(type, new Double(dataset.getValue(type).intValue() + 1));
        }
        
        return dataset;
    }
    
}
