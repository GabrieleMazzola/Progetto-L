package gui;

/**
 *
 * @author Manuele
 */
public class User {
    
    private String name, surname, username, cf, psw;
    
    public User(String name, String surname, String cf, String username, String password) {
        
        this.name = name;
        this.surname = surname;
        this.cf = cf;
        this.username = username;
        psw = password;
    }
    
    public String getUsername() {
        
        return username;
    }
}
