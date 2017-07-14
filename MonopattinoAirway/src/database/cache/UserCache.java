package database.cache;

import database.interfaces.CacheInterface;
import database.people.User;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Zubeer
 */
public class UserCache implements CacheInterface{
    private Set<User> cache;
    
    /**
     *
     */
    public UserCache() {
        cache = new HashSet<>();
    }
    
    /**
     *
     * @param arg
     */
    @Override
    public void add(Object arg) {
        if(arg instanceof User)
            cache.add((User)arg);
    }
    
    /**
     *
     * @param username
     * @return
     */
    @Override
    public User get(String username) {
        for(User u : cache) {
            if(u.getUsername().equals(username)) return u;
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return cache.size();
    }
}
