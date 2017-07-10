package database.mapper.simmapper;

import database.interfaces.MapperInterface;
import database.interfaces.mapperinterfaces.UserMapper;
import database.people.User;
import java.util.HashSet;
import java.util.Set;

public class SimUserMapper implements MapperInterface,UserMapper{
    private Set<User> users;
    
    public SimUserMapper() {
        users = new HashSet<>();
        initUser();
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

    private void initUser() {
        users.add(new User("ADMIN", "ADMIN", "ADMIN", "ADMIN", "ADMIN", ""));
    }
}