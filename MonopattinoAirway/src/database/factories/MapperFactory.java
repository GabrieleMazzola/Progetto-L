package database.factories;

import database.interfaces.MapperFactoryInterface;

/**
 *
 * @author Zubeer
 */
public abstract class MapperFactory implements MapperFactoryInterface{
    
    protected static MapperFactoryInterface instance;
    
    public static synchronized MapperFactoryInterface getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(instance == null){
            instance = (MapperFactoryInterface)Class.forName(className).newInstance();
        }
        return instance;            
    }
    
}
