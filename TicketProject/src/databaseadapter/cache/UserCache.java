package databaseadapter.cache;

import databaseadapter.people.User;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Manuele
 */
public class UserCache implements CacheInterface{
    private Set<User> cache;
    
    public UserCache() {
        cache = new HashSet<>();
    }
    
    @Override
    public void add(Object arg) {
        if(arg instanceof User)
            cache.add((User)arg);
    }
    
    @Override
    public User get(String username) {
        for(User u : cache) {
            if(u.getUsername().equals(username)) return u;
        }
        return null;
    }
}
