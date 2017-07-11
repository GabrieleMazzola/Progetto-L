package database.mapper.realmapper;

import database.interfaces.CacheInterface;
import database.interfaces.MapperInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConcreteMapper implements MapperInterface{
    protected String hostname;
    protected String port;
    protected String databaseName;
    protected String databaseURL;
    protected CacheInterface cache;
    protected Connection con;
    
    public ConcreteMapper(String username, String password) {
        hostname = "localhost";
        port = "3306";
        databaseName = "emettitrici";
        databaseURL = "jdbc:mysql://" + hostname + ":" + port + "/" + databaseName;
        try {
            con = DriverManager.getConnection(databaseURL, username, password);
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Object get(String id) {
        Object arg = getFromCache(id);
        if(arg == null) {
            arg = getFromStorage(id);
            cache.add(arg);
        }
        
        return arg;
    }
    
    @Override
    public boolean save(Object arg) {
        String query = buildSaveQuery(arg);
        try {
            executeSaveQuery(query);
            return true;
        }
        catch(SQLException exc) {
            System.err.println(exc.getMessage());
            return false;
        }
    }
    
    @Override
    public int getCacheSize() {
        return cache.getSize();
    }
    
    protected void executeSaveQuery(String toExecute) throws SQLException{
        con.createStatement().executeUpdate(toExecute);
    }
    
    //Cerca l'oggetto nella cache
    protected abstract Object getFromCache(String id);
    //Cerca l'oggetto nel database
    protected abstract Object getFromStorage(String id);
    //Costruisce la query di salvataggio
    protected abstract String buildSaveQuery(Object arg);
    //Costruisce la query di selezione
    protected abstract String buildSelectQuery(String id);
}
