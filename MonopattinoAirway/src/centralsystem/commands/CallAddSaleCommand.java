package centralsystem.commands;

import centralsystem.CSystem;
import console.LogCS;
import database.people.User;
import email.EmailDispatcher;
import items.Product;
import items.Sale;
import java.util.Date;
import jsonenumerations.AddSale;
import jsonenumerations.JsonFields;
import org.json.simple.JSONObject;
import singleton.DateOperations;
import singleton.ProductsSingleton;


public class CallAddSaleCommand extends Command{
    private EmailDispatcher emailDispatcher;
    public CallAddSaleCommand(CSystem centralSystem) {
        super(centralSystem);
        emailDispatcher = new EmailDispatcher();
    }
    
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
        User u = centralSystem.getUser(sale.getUsername());
        centralSystem.addMessageToLog("Sale added to database");
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
