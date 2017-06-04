package databaseadapter.mapper;

import databaseadapter.cache.UserCache;
import databaseadapter.people.Collector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Manuele
 */
public class CollectorMapper extends ConcreteMapper{
    private final String tableName;
    
    public CollectorMapper(String userTableName) {
        super();
        tableName = userTableName;
        cache = new UserCache();
    }
    
    @Override
    protected Object getFromCache(String id) {
        return cache.get(id);
    }
    
    @Override
    protected Collector getFromStorage(String username) {
        Collector c = null;
        String query = buildSelectQuery(username);
        try {
            Connection con = DriverManager.getConnection(databaseURL, "root", "gigidatome3");
            ResultSet data = con.createStatement().executeQuery(query);
            if(data.next()) {
                String name = data.getString("NAME");
                String surname = data.getString("SURNAME");
                String cf = data.getString("CF");
                //username = data.getString("USENAME");
                String psw = data.getString("PSW");
                c = new Collector(name, surname, cf, username, psw);
            }
            con.close();
        }
        catch(SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return c;
    }
    
    @Override
    protected String buildSelectQuery(String id) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE username = '").append(id).append("'");
        return str.toString();
    }
    
    @Override
    protected String buildSaveQuery(Object arg) {
        Collector c = (Collector)arg;
        StringBuilder str = new StringBuilder();
        String name = c.getName();
        String surname = c.getSurname();
        String cf = c.getCf();
        String username = c.getUsername();
        String psw = c.getPsw();
        str.append("INSERT INTO ").append(tableName)
                .append(" VALUES ('").append(name).append("', '").append(surname).append("', '")
                .append(cf).append("', '").append(username).append("', '").append(psw).append("');");
        return str.toString();
    }
}
