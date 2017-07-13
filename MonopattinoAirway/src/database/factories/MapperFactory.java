package database.factories;

import database.interfaces.MapperFactoryInterface;

/**
 *
 * @author Zubeer
 */
public abstract class MapperFactory implements MapperFactoryInterface{
    
    /**
     *
     */
    protected static MapperFactory instance;
    
    /**
     *
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static synchronized MapperFactory getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(instance == null){
            instance = (MapperFactory)Class.forName(className).newInstance();
        }
        return instance;            
    }
    
}
