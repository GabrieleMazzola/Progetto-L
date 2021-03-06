package centralsystem.commands;

import centralsystem.CSystem;
import items.Sale;
import java.util.Set;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.GetSalesByUsername;
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
            
            jsonSale.put(GetSalesByUsername.SERIALCODE.toString(),sale.getSerialCode()); 
            jsonSale.put(GetSalesByUsername.SALEDATE.toString(), DateOperations.getInstance().toString(sale.getSaleDate()));
            jsonSale.put(GetSalesByUsername.TYPE.toString(), sale.getType());
            jsonSale.put(GetSalesByUsername.SELLERMACHINEIP.toString(), sale.getSellerMachineIp());
            jsonSale.put(GetSalesByUsername.USERNAME.toString(), sale.getUsername());
            
            jsonList.add(jsonSale);
            
        }
        
        centralSystem.addMessageToLog("Request successfully handled");
        data.put(JsonFields.DATA.toString(), jsonList);
        return data.toString();
    }
    
}
