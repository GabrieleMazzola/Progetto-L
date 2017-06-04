package databaseadapter.mapper;

import databaseadapter.cache.FineCache;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import ticketCollector.Fine;

/**
 *
 * @author Manuele
 */
public class FineMapper extends ConcreteMapper{
    private final String tableName;
    
    public FineMapper(String finesTableName) {
        super();
        tableName = finesTableName;
        cache = new FineCache();
    }
    
    public Set<Fine> getAllFinesOf(String cf) {
        Set<Fine> fines = new HashSet<>();
        String query = buildSelectAllQuery(cf);
        try {
            Connection con = DriverManager.getConnection(databaseURL, "root", "gigidatome3");
            ResultSet data = con.createStatement().executeQuery(query);
            while(data.next()) {
                long id = data.getLong("ID");
                String cfDB = data.getString("CF");
                double amount = data.getDouble("AMOUNT");
                if(cfDB.equals(cf)) fines.add(new Fine(id, cf, amount));
            }
            con.close();
        }
        catch(SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return fines;
    }

    @Override
    protected Object getFromCache(String id) {
        return cache.get(id);
    }

    @Override
    protected Object getFromStorage(String id) {
        Fine f = null;
        String query = buildSelectQuery(id);
        try {
            Connection con = DriverManager.getConnection(databaseURL, "root", "gigidatome3");
            ResultSet data = con.createStatement().executeQuery(query);
            if(data.next()) {
                String cf = data.getString("CF");
                double amount = data.getDouble("AMOUNT");
                f = new Fine(Long.parseLong(id), cf, amount);
            }
            con.close();
        }
        catch(SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return f;
    }

    @Override
    protected String buildSaveQuery(Object arg) {
        Fine f = (Fine)arg;
        StringBuilder str = new StringBuilder();
        long id = f.getId();
        String cf = f.getCfCode();
        double amount = f.getAmount();
        str.append("INSERT INTO ").append(tableName).append(" VALUES (")
                .append(id).append(", '").append(cf).append("', ").append(amount).append(");");
        return str.toString();
    }

    @Override
    protected String buildSelectQuery(String id) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE id = ").append(id);
        return str.toString();
    }
    
    private String buildSelectAllQuery(String cfCode) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE cf = '").append(cfCode).append("'");
        return str.toString();
    }
}
