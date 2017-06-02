package databaseadapter.mapper;

import databaseadapter.cache.TicketCache;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public boolean save(Object arg) {
        Ticket t = (Ticket)arg;
        String query = buildSaveQuery(t);
        try {
            executeSaveQuery(query);
            return true;
        }
        catch(SQLException exc) {
            System.out.println(exc.getMessage());
            return false;
        }
    }
    
    @Override
    protected Object getFromCache(String id) {
        return cache.get(id);
    }
    
    @Override
    protected Ticket getFromStorage(String id) {
        String query = buildSelectQuery(id);
        try {
            ResultSet data = fetchData(query);
            String code = data.getString("CODE");
            TicketType type = data.getObject("TYPE", TicketType.class);
            return new Ticket(code, type);
        }
        catch(SQLException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }
    
    private ResultSet fetchData(String toExecute) throws SQLException{
        Connection con = DriverManager.getConnection(databaseURL, "root", "gigidatome3");
        ResultSet ris = con.createStatement().executeQuery(toExecute);
        con.close();
        return ris;
    }
    
    private String buildSelectQuery(String id) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append("WHERE cod = ").append(id);
        return str.toString();
    }
    
    private String buildSaveQuery(Ticket t) {
        StringBuilder str = new StringBuilder();
        String code = t.getCode();
        str.append("INSERT INTO ").append(tableName).append(" VALUES ('").append(code).append("'").append(", 'single');");
        return str.toString();
    }
}
