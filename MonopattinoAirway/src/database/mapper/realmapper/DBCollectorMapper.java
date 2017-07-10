package database.mapper.realmapper;

import database.cache.UserCache;
import database.interfaces.mapperinterfaces.CollectorMapper;
import database.people.Collector;
import java.sql.*;


public class DBCollectorMapper extends ConcreteMapper implements CollectorMapper{
    private final String tableName;
    
    public DBCollectorMapper(String userTableName) {
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
            ResultSet data = con.createStatement().executeQuery(query);
            if(data.next()) {
                String name = data.getString("NAME");
                String surname = data.getString("SURNAME");
                String cf = data.getString("CF");
                String psw = data.getString("PSW");
                c = new Collector(name, surname, cf, username, psw);
            }
        }
        catch(SQLException exc) {
            System.err.println(exc.getMessage());
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
