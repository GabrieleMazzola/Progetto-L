package database.interfaces;

public interface CacheInterface {
    public void add(Object arg);
    public Object get(String code);
    public int getSize();
}

