package database.mapper.realmapper;

import database.cache.FineCache;
import database.interfaces.mapperinterfaces.FineMapper;
import enums.databaseenumretions.DBFineEnum;
import items.Fine;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DBFineMapper extends ConcreteMapper implements FineMapper{
    private final String tableName;
    
    public DBFineMapper(String finesTableName) {
        super();
        tableName = finesTableName;
        cache = new FineCache();
    }
    
    @Override
    public Set<Fine> getAllFinesMadeTo(String cf) {
        Set<Fine> fines = new HashSet<>();
        String query = buildSelectAllQuery(cf);
        try {
            ResultSet data = con.createStatement().executeQuery(query);
            while(data.next()) {
                String id = data.getString(DBFineEnum.ID.toString());
                String cfDB = data.getString(DBFineEnum.CF.toString());
                double amount = data.getDouble(DBFineEnum.AMOUNT.toString());
                String username = data.getString(DBFineEnum.USERNAME.toString());
                if(cfDB.equals(cf)) fines.add(new Fine(id, cf, amount,username));
            }
        }
        catch(SQLException exc) {
            System.err.println(exc.getMessage());
        }
        return fines;
    }
    
    @Override
    public Long countAllFinesMadeBy(String collectorUsername) {
        String query = buildCountQuery(collectorUsername);
        Long count = null;
        try {
            ResultSet data = con.createStatement().executeQuery(query);
            if(data.next()){
                count = (Long)data.getLong(DBFineEnum.COUNT.toString());
                System.out.println(count);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return(count);
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
            ResultSet data = con.createStatement().executeQuery(query);
            if(data.next()) {
                String cf = data.getString(DBFineEnum.CF.toString());
                double amount = data.getDouble(DBFineEnum.AMOUNT.toString());
                String username = data.getString(DBFineEnum.COLLECTOR_USERNAME.toString());
                f = new Fine(id, cf, amount, username);
            }
        }
        catch(SQLException exc) {
            System.err.println(exc.getMessage());
        }
        return f;
    }

    @Override
    protected String buildSaveQuery(Object arg) {
        Fine f = (Fine)arg;
        StringBuilder str = new StringBuilder();
        String id = f.getId();
        String cf = f.getCfCode();
        String cUsername = f.getCollectorUsername();
        double amount = f.getAmount();
        str.append("INSERT INTO ").append(tableName).append(" VALUES ('")
                .append(id).append("', '").append(cf).append("', ").append(amount).append(", '").append(cUsername).append("');");
        System.out.println(str.toString());
        return str.toString();
    }

    @Override
    protected String buildSelectQuery(String id) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE id = '").append(id).append("';");
        return str.toString();
    }
    
    private String buildSelectAllQuery(String cfCode) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE cf = '").append(cfCode).append("'");
        return str.toString();
    }

    private String buildCountQuery(String collectorUsername) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT COUNT(*) AS COUNT FROM ").append(tableName).append(" WHERE COLLECTOR_USERNAME = '").append(collectorUsername).append("'");
        return str.toString();
    }

}

