package database.factories;

import database.interfaces.MapperFactoryInterface;

public abstract class MapperFactory implements MapperFactoryInterface{
    
    private static MapperFactory instance;
    
    public static synchronized MapperFactory getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(instance == null){
            instance = (MapperFactory)Class.forName(className).newInstance();
        }
        return instance;            
    }
    
}
