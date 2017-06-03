package databaseadapter.mapper;

import databaseadapter.User;
import databaseadapter.cache.UserCache;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Manuele
 */
public class UserMapper extends ConcreteMapper{
    private UserCache cache;
    private final String tableName;
    
    public UserMapper(String userTableName) {
        super();
        tableName = userTableName;
        cache = new UserCache();
    }
    
    @Override
    protected Object getFromCache(String id) {
        return cache.get(id);
    }
    
    @Override
    protected User getFromStorage(String username) {
        User u = null;
        String query = buildSelectQuery(username);
        System.out.println("UserMapper - 33: " + query);
        try {
            Connection con = DriverManager.getConnection(databaseURL, "root", "gigidatome3");
            ResultSet data = con.createStatement().executeQuery(query);
            if(data.next()) {
                String name = data.getString("NAME");
                String surname = data.getString("SURNAME");
                String cf = data.getString("CF");
                //username = data.getString("USENAME");
                String psw = data.getString("PSW");
                u = new User(name, surname, cf, username, psw);
            }
            con.close();
        }
        catch(SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return u;
    }
    
    @Override
    protected String buildSelectQuery(String id) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE username = '").append(id).append("'");
        return str.toString();
    }
    
    @Override
    protected String buildSaveQuery(Object arg) {
        User u = (User)arg;
        StringBuilder str = new StringBuilder();
        String name = u.getName();
        String surname = u.getSurname();
        String cf = u.getCf();
        String username = u.getUsername();
        String psw = u.getPsw();
        str.append("INSERT INTO ").append(tableName)
                .append(" VALUES ('").append(name).append("', '").append(surname).append("', '")
                .append(cf).append("', '").append(username).append("', '").append(psw).append("');");
        return str.toString();
    }
}
