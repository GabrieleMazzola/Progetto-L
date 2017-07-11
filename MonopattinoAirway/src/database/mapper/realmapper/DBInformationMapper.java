package database.mapper.realmapper;

import database.cache.InformationCache;
import database.information.InformationUnit;
import database.interfaces.mapperinterfaces.InformationMapper;
import enums.databaseenumretions.DBInformationEnum;
import java.sql.*;

public class DBInformationMapper extends ConcreteMapper implements InformationMapper{
    private final String tableName;
    
    public DBInformationMapper(String finesTableName) {
        super();
        tableName = finesTableName;
        cache = new InformationCache();
    }

    @Override
    protected Object getFromCache(String id) {
        return null;
    }

    @Override
    protected Object getFromStorage(String key) {
        InformationUnit inf = null;
        String query = buildSelectQuery(key);
        try {
            ResultSet data = con.createStatement().executeQuery(query);
            if(data.next()) {
                String value = data.getString(DBInformationEnum.VALUE.toString());
                
                inf = new InformationUnit(key, value);
            }
        }
        catch(SQLException exc) {
            System.err.println(exc.getMessage());
        }
        
        return inf; 
    }

    @Override
    protected String buildSaveQuery(Object arg) {
        InformationUnit unit = (InformationUnit)arg;
        
        StringBuilder str = new StringBuilder();
        String key = unit.getKey();
        String value = unit.getValue();
        str.append("UPDATE ").append(tableName).append(" SET value = '")
                .append(value).append("' WHERE info_key = '").append(key).append("';");
        System.out.println(str.toString());
        return str.toString();
    }

    @Override
    protected String buildSelectQuery(String key) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM ").append(tableName).append(" WHERE INFO_KEY = '").append(key).append("';");
        return str.toString();
    }
}
