package databaseadapter.mapper;

import DateSingleton.DateOperations;
import databaseadapter.cache.TicketCache;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ticket.SeasonType;
import ticket.SingleType;
import ticket.Ticket;
import ticket.TicketType;

/**
 *
 * @author Manuele
 */
public class TicketMapper extends ConcreteMapper{
    private final String tableName;
    
    public TicketMapper(String ticketTableName) {
        super();
        tableName = ticketTableName;
        cache = new TicketCache();
    }
    
    public List<Ticket> getAllTicketsOf(String username) {
        List<Ticket> tickets = new ArrayList();
        String query = buildSelectAllQuery(username);
        try {
            Connection con = DriverManager.getConnection(databaseURL, "root", "gigidatome3");
            ResultSet data = con.createStatement().executeQuery(query);
            while(data.next()) {
               String code = data.getString("CODE");
                String typeString = data.getString("TYPE");
                String dateString = data.getString("EXPIRE_TIME");
                String owner = data.getString("USER");
                Ticket t = new Ticket(code, convertToTicketType(typeString));
                if(!dateString.equals("null")) {
                    Date date = DateOperations.getInstance().parse(dateString);
                    t.setExpiryDate(date);
                }
                if(owner != null) t.setOwner(owner);
                tickets.add(t);
            }
            con.close();
        }
        catch(SQLException|ParseException exc) {
            System.out.println(exc.getMessage());
        }
        return tickets;
    }
    
    @Override
    protected Object getFromCache(String id) {
        return cache.get(id);
    }
    
    @Override
    protected Ticket getFromStorage(String id) {
        Ticket t = null;
        String query = buildSelectQuery(id);
        try {
            Connection con = DriverManager.getConnection(databaseURL, "root", "gigidatome3");
            ResultSet data = con.createStatement().executeQuery(query);
            if(data.next()) {
                String code = data.getString("CODE");
                String typeString = data.getString("TYPE");
                String dateString = data.getString("EXPIRE_TIME");
                String owner = data.getString("USER");
                t = new Ticket(code, convertToTicketType(typeString));
                if(!dateString.equals("null")) {
                    Date date = DateOperations.getInstance().parse(dateString);
                    t.setExpiryDate(date);
                }
                if(owner != null) t.setOwner(owner);
            }
            con.close();
        }
        catch(SQLException|ParseException exc) {
            System.out.println(exc.getMessage());
        }
        return t;
    }
    
    @Override
    protected String buildSelectQuery(String id) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE code = ").append(id).append(";");
        return str.toString();
    }
    
    @Override
    protected String buildSaveQuery(Object arg) {
        Ticket t = (Ticket)arg;
        String date = DateOperations.getInstance().toString(t.getExpiryDate());
        StringBuilder str = new StringBuilder();
        String code = t.getCode();
        str.append("INSERT INTO ").append(tableName).append(" VALUES ('").append(code).append("'").append(", 'single', '")
           .append(date).append("', '").append(t.getOwner()).append("');");
        return str.toString();
    }
    
    private String buildSelectAllQuery(String username) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE user = '").append(username).append("';");
        return str.toString();
    }
    
    private TicketType convertToTicketType(String typeString) {
        switch(typeString.toUpperCase()) {
            case "SINGLE":
                return new SingleType();
            case "SEASON":
                return new SeasonType();
        }
        return null;
    }
}
