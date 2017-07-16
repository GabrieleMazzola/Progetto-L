package database.factories;

import database.interfaces.MapperFactoryInterface;

public abstract class MapperFactory implements MapperFactoryInterface{
    
    protected static MapperFactoryInterface instance;
    
    /**
     * istanzia, a seconda del className inserito, una factory per i mapper simulati,
     * o fisici
     * @param className
     * @return Un'istanza del SimMapperFactory o del DBMapperFactory
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public static synchronized MapperFactoryInterface getInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(instance == null){
            instance = (MapperFactoryInterface)Class.forName(className).newInstance();
        }
        return instance;            
    }
    
}
