package databaseadapter.mapper;

import databaseadapter.people.User;
import java.util.HashSet;
import java.util.Set;
import ticketCollector.Fine;

/**
 *
 * @author Manuele
 */
public class SimUserMapper implements MapperInterface{
    private Set<User> users;
    
    public SimUserMapper() {
        users = new HashSet<>();
    }
    
    @Override
    public Object get(String username) {
        for(User u : users) {
            if(u.getUsername().equals(username)) return u;
        }
        return null;
    }

    @Override
    public boolean save(Object arg) {
        if(arg instanceof User)
            return users.add((User)arg);
        else return false;
    }

    @Override
    public int getCacheSize() {
        return users.size();
    }
}
