package centralsystem.commands;

import centralsystem.CSystem;
import console.LogCS;
import database.people.User;
import email.EmailDispatcher;
import items.Product;
import items.Sale;
import java.util.Date;
import enums.jsonenumerations.AddSale;
import enums.jsonenumerations.JsonFields;
import org.json.simple.JSONObject;
import singleton.DateOperations;
import singleton.ProductsSingleton;


public class CallAddSaleCommand extends Command{
    private EmailDispatcher emailDispatcher;
    public CallAddSaleCommand(CSystem centralSystem) {
        super(centralSystem);
        emailDispatcher = new EmailDispatcher();
    }
    
    /**
     * Prende un pacchetto JSON in ingresso, estrae data di vendita della Sale, codice seriale,
     * username dell'acquirente, tipo di biglietto acquistato, informazioni sul prodotto, e ip 
     * della ticket machine. Crea una Sale e chiama il metodo del CentralSystem per memorizzarla 
     * nel database.Viene mandata una email all'indirizzo associato all'username, con informazioni
     * sull'acquisto. Viene memorizzato il valore di ritorno del metodo del CentralSystem, e 
     * viene messo in un pacchetto JSON.
     * @param data
     * @return Una stringa che rappresenta il valore di ritorno del metodo addSale del CentralSystem
     */
    @Override
    public String execute(JSONObject data){
        centralSystem.addMessageToLog("Attempted selling ticket...");
        Date sellDate = new Date();
        try {
            sellDate = DateOperations.getInstance().parse((String)data.get(AddSale.SALEDATE.toString()));
        } catch (java.text.ParseException ex) {
            LogCS.getInstance().print("err", ex.toString());
            data = new JSONObject();
            data.put(JsonFields.DATA, false);
            return data.toString();
        }

        long serialCode = ((Long)data.get(AddSale.SERIALCODE.toString()));
        String username = ((String)data.get(AddSale.USERNAME.toString()));
        String type = ((String)data.get(AddSale.TYPE.toString()));
        Product productSold = ProductsSingleton.getInstance().getProducts().get(type);
        String sellerMachineIp = ((String)data.get(AddSale.SELLERMACHINEIP.toString()));

        
        Sale sale = new Sale(sellDate,  serialCode,  username, productSold, sellerMachineIp);
        centralSystem.addSale(sale);
        centralSystem.addMessageToLog("Sale added to database");
        
        User u = centralSystem.getUser(sale.getUsername());
        if(u != null)
            emailDispatcher.sendEmail(buildEmailMessage(sale), u.getEmail());
        
        data = new JSONObject();
        data.put(JsonFields.DATA, true);
        return data.toString();
    }
    
    private String buildEmailMessage(Sale s) {
        StringBuilder str = new StringBuilder();
        str.append("Hello ").append(s.getUsername()).append("!\nYou just bought a ticket! The details are:\n")
           .append("\nTicket code: ").append(s.getSerialCode())
           .append("\nDescription: ").append(s.getProduct().getDescription())
           .append("\nType: ").append(s.getType())
           .append("\nDuration: ").append(s.getProduct().getDuration())
           .append("\nCost: ").append(s.getProduct().getCost())
           .append("\nSale date: ").append(s.getSaleDate())
           .append("\nExpity date: ").append(s.getExpiryDate())
           .append("\n\nKeep this email! This is your ticket, valid until ").append(s.getExpiryDate()).append("!");
        
        return str.toString();
    }
}
