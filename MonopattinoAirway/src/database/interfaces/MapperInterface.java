package database.interfaces;

/**
 *
 * @author Manuele
 */
public interface MapperInterface {
    public Object get(String id);
    public boolean save(Object arg);
    public int getCacheSize();
}

