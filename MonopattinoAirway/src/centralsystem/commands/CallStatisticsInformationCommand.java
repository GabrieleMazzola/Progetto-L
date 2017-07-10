package centralsystem.commands;

import centralsystem.CSystem;
import items.Sale;
import java.util.Set;
import jsonenumerations.JsonFields;
import jsonenumerations.MyTickets;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import singleton.DateOperations;

public class CallStatisticsInformationCommand extends Command{
    
    public CallStatisticsInformationCommand(CSystem centralSystem) {
        super(centralSystem);
    }

    @Override
    public String execute(JSONObject data) {
            centralSystem.addMessageToLog("attempted statistics information..");
            
            Set<Sale> saleList = centralSystem.getAllSales();
            JSONArray jsonList = new JSONArray();
            data = new JSONObject();
            
        for (Sale sale : saleList) {
            JSONObject jsonSale = new JSONObject();  
            
            jsonSale.put(MyTickets.SERIALCODE.toString(),sale.getSerialCode()); 
            jsonSale.put(MyTickets.SALEDATE.toString(), DateOperations.getInstance().toString(sale.getSaleDate()));
            jsonSale.put(MyTickets.TYPE.toString(), sale.getType());
            jsonSale.put(MyTickets.SELLERMACHINEIP.toString(), sale.getSellerMachineIp());
            jsonSale.put(MyTickets.USERNAME.toString(), sale.getUsername());
            
            jsonList.add(jsonSale);
            
        }
        
        centralSystem.addMessageToLog("Request successfully handled");
        data.put(JsonFields.DATA.toString(), jsonList);
        return data.toString();
    }
    
}
