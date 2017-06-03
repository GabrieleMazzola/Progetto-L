package databaseadapter.mapper;

import databaseadapter.cache.TicketCache;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import ticket.SeasonType;
import ticket.SingleType;
import ticket.Ticket;
import ticket.TicketType;

/**
 *
 * @author Manuele
 */
public class TicketMapper extends ConcreteMapper{
    private TicketCache cache;
    private final String tableName;
    
    public TicketMapper(String ticketTableName) {
        super();
        tableName = ticketTableName;
        cache = new TicketCache();
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
                t = new Ticket(code, convertToTicketType(typeString));
            }
            con.close();
        }
        catch(SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return t;
    }
    
    @Override
    protected String buildSelectQuery(String id) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE code = ").append(id);
        return str.toString();
    }
    
    @Override
    protected String buildSaveQuery(Object arg) {
        Ticket t = (Ticket)arg;
        StringBuilder str = new StringBuilder();
        String code = t.getCode();
        str.append("INSERT INTO ").append(tableName).append(" VALUES ('").append(code).append("'").append(", 'single');");
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
