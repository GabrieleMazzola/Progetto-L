package database.mapper.realmapper;

import database.cache.SaleCache;
import database.interfaces.mapperinterfaces.SaleMapper;
import enums.databaseenumretions.DBSaleEnum;
import productsfactories.central.CentralProductsFactory;
import items.*;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;
import singleton.DateOperations;

public class DBSaleMapper extends ConcreteMapper implements SaleMapper{
    private final String tableName;
    
    public DBSaleMapper(String ticketTableName, String username, String password) {
        super(username, password);
        tableName = ticketTableName;
        cache = new SaleCache();
    }
    
    public Set<Sale> getSalesByType(String searchedType) {
        Set<Sale> sales = new HashSet();
        String query = buildSelectAllQuery("type", searchedType);
        try {
            ResultSet data = con.createStatement().executeQuery(query);
            while(data.next()) {
                
                String user = data.getString(DBSaleEnum.USER.toString());
                long serial = data.getLong(DBSaleEnum.SERIALCODE.toString());
                String type = data.getString(DBSaleEnum.TYPE.toString());
                Product productSold = CentralProductsFactory.getInstance().getProducts().get(type);
                String dateString = data.getString(DBSaleEnum.SELL_DATE.toString());
                String sellerMachineIp = data.getString(DBSaleEnum.SELLER_IP.toString());
                
                Date sellDate = DateOperations.getInstance().parse(dateString);
                Sale s = new Sale(sellDate, serial, user, productSold, sellerMachineIp);
                sales.add(s);
            }
        }
        catch(SQLException|ParseException exc) {
            System.err.println(exc.getMessage());
        }
        
        return sales;
    }
    
    @Override
    public Set<Sale> getAllSalesOf(String username) {
        Set<Sale> sales = new HashSet();
        String query = buildSelectAllQuery("user", username);
        System.out.println(query);
        try {
            ResultSet data = con.createStatement().executeQuery(query);
            while(data.next()) {
                
                String user = data.getString(DBSaleEnum.USER.toString());
                long serial = data.getLong(DBSaleEnum.SERIALCODE.toString());
                String type = data.getString(DBSaleEnum.TYPE.toString());
                Product productSold = CentralProductsFactory.getInstance().getProducts().get(type);
                String dateString = data.getString(DBSaleEnum.SELL_DATE.toString());
                String sellerMachineIp = data.getString(DBSaleEnum.SELLER_IP.toString());
                
                Date sellDate = DateOperations.getInstance().parse(dateString);
                Sale sale = new Sale(sellDate, serial, user, productSold, sellerMachineIp);
                
                sales.add(sale);
            }
        }
        catch(SQLException|ParseException exc) {
            System.err.println(exc.getMessage());
        }
        return sales;
    }
    
    
    @Override
    public Set<Sale> getAllValidSalesOf(String username) {
      Set<Sale> validSalesOf = new HashSet<>();
        Date now = new Date();
        
        for(Sale sale : getAllSalesOf(username)){
            if(checkSaleValidity(sale,now)){
                validSalesOf.add(sale);
            }
        }
        
        return validSalesOf;  
    }
    
    private boolean checkSaleValidity(Sale sale, Date now) {
        Date expiryDate = sale.getExpiryDate();
        return (now.before(expiryDate));
    }
    
    @Override
    public Set<Sale> getAllSales() {
        Set<Sale> sales = new HashSet();
        String query = buildSelectAllQuery();
        try {
            ResultSet data = con.createStatement().executeQuery(query);
            while(data.next()) {
                
                String user = data.getString(DBSaleEnum.USER.toString());
                long serial = data.getLong(DBSaleEnum.SERIALCODE.toString());
                String type = data.getString(DBSaleEnum.TYPE.toString());
                Product productSold = CentralProductsFactory.getInstance().getProducts().get(type);
                String dateString = data.getString(DBSaleEnum.SELL_DATE.toString());
                String sellerMachineIp = data.getString(DBSaleEnum.SELLER_IP.toString());
                
                Date sellDate = DateOperations.getInstance().parse(dateString);
                Sale sale = new Sale(sellDate, serial, user, productSold, sellerMachineIp);
                
                sales.add(sale);
            }
        }
        catch(SQLException|ParseException exc) {
            System.err.println(exc.getMessage());
        }
        return sales;
    }
    
    @Override
    protected Object getFromCache(String id) {
        return cache.get(id);
    }
    
    @Override
    protected Sale getFromStorage(String id) {
        Sale sale = null;
        String query = buildSelectQuery(id);
        try {
            ResultSet data = con.createStatement().executeQuery(query);
            if(data.next()) {
                
                String user = data.getString(DBSaleEnum.USER.toString());
                long serial = data.getLong(DBSaleEnum.SERIALCODE.toString());
                String type = data.getString(DBSaleEnum.TYPE.toString());
                Product productSold = CentralProductsFactory.getInstance().getProducts().get(type);
                String dateString = data.getString(DBSaleEnum.SELL_DATE.toString());
                String sellerMachineIp = data.getString(DBSaleEnum.SELLER_IP.toString());
                
                Date sellDate = DateOperations.getInstance().parse(dateString);
                sale = new Sale(sellDate, serial, user, productSold, sellerMachineIp);
            }
        }
        catch(SQLException|ParseException exc) {
            System.err.println(exc.getMessage());
        }
        return sale;
    }
    
    @Override
    protected String buildSelectQuery(String id) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE serialcode = ").append(id).append(";");
        return str.toString();
    }
    
    @Override
    protected String buildSaveQuery(Object arg) {
        Sale sale = (Sale)arg;
        
        //stesso ordine con cui salviamo nel DB
        long serialCode = sale.getSerialCode();
        String type = sale.getType();
        String user = sale.getUsername();
        String sellDate = DateOperations.getInstance().toString(sale.getSaleDate());
        String sellerMachineIp = sale.getSellerMachineIp();
        
        StringBuilder str = new StringBuilder();
        str.append("INSERT INTO ").append(tableName).append(" VALUES (").append(serialCode).append(", '").append(type).append("', '")
           .append(user).append("', '").append(sellDate).append("', '").append(sellerMachineIp).append("');");
        System.out.println(str.toString());
        return str.toString();
    }
    
    private String buildSelectAllQuery() {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName);
        return str.toString();
    }
    
    private String buildSelectAllQuery(String field, String value) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE ").append(field).append(" = '").append(value).append("';");
        return str.toString();
    }

}
