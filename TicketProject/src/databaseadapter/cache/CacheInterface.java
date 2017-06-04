package databaseadapter.cache;

import ticket.Ticket;

/**
 *
 * @author Manuele
 */
public interface CacheInterface {
    public void add(Object arg);
    public Object get(String code);
    public int getSize();
}
