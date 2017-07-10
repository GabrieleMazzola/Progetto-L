package database.people;

public class User {
    private String  name, surname, cf, username, psw, email;

    public User(String name, String surname, String cf, String username, String psw, String email) {
        this.name = name;
        this.surname = surname;
        this.cf = cf;
        this.username = username;
        this.psw = psw;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCf() {
        return cf;
    }

    public String getUsername() {
        return username;
    }

    public String getPsw() {
        return psw;
    }
    
    public String getEmail() {
        return email;
    }
}
