package database.people;

public class Collector {
    private String  name, surname, cf, username, psw;

    public Collector(String name, String surname, String cf, String username, String psw) {
        this.name = name;
        this.surname = surname;
        this.cf = cf;
        this.username = username;
        this.psw = psw;
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
}
