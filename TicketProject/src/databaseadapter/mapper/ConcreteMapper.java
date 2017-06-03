package databaseadapter.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Manuele
 */
public abstract class ConcreteMapper implements MapperInterface{
    protected String hostname;
    protected String port;
    protected String databaseName;
    protected String databaseURL;
    
    public ConcreteMapper() {
        hostname = "localhost";
        port = "3306";
        databaseName = "test";
        databaseURL = "jdbc:mysql://" + hostname + ":" + port + "/" + databaseName;
    }
    
    @Override
    public Object get(String id) {
        Object arg = getFromCache(id);
        if(arg == null) {
            arg = getFromStorage(id);
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
            System.out.println(exc.getMessage());
            return false;
        }
    }
    
    protected void executeSaveQuery(String toExecute) throws SQLException{
        Connection con = DriverManager.getConnection(databaseURL, "root", "gigidatome3");
        con.createStatement().executeUpdate(toExecute);
        con.close();
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
