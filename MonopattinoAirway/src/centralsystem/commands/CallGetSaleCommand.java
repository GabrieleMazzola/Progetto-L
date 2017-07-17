/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralsystem.commands;

import centralsystem.CSystem;
import enums.jsonenumerations.AddSale;
import enums.jsonenumerations.JsonFields;
import enums.jsonenumerations.MakeFine;
import enums.jsonenumerations.GetSalesByUsername;
import enums.jsonenumerations.GetSale;
import items.Fine;
import items.Sale;
import org.json.simple.JSONObject;
import singleton.DateOperations;

/**
 *
 * @author Zubeer
 */
public class CallGetSaleCommand extends Command{

    public CallGetSaleCommand(CSystem centralSystem) {
        super(centralSystem);
    }

    @Override
    public String execute(JSONObject data) {
        
        centralSystem.addMessageToLog("Attempted making get Sale by SerialCode...");
        
        
        String serialCode = (Long.toString((Long)data.get(GetSale.SERIALCODE.toString())));

        System.out.println("CallGetSaleCommand.java : " + serialCode + " data: " + data.toString());
        Sale sale = centralSystem.getSale(serialCode);
        data = new JSONObject();
        if(sale == null){
            data.put(JsonFields.DATA.toString(), "false");
        //TODO TEST THIS
        }else{
            data.put(GetSale.SERIALCODE.toString(),sale.getSerialCode()); 
            data.put(GetSale.SALEDATE.toString(), DateOperations.getInstance().toString(sale.getSaleDate()));
            data.put(GetSale.TYPE.toString(), sale.getType());
            data.put(GetSale.SELLERMACHINEIP.toString(), sale.getSellerMachineIp());
            data.put(GetSale.USERNAME.toString(), sale.getUsername());

        }
        return data.toJSONString();

    }
    
}
