
package databaseadapter;

/**
 *
 * @author Gabriele
 */
public class UserDB {
    private String name,surname,CF,mail;

    public UserDB(String name, String surname, String CF) {
        this.name = name;
        this.surname = surname;
        this.CF = CF;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCF() {
        return CF;
    }

    public String getMail() {
        return mail;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Name: ").append(this.name);
        sb.append("\nSurname : ").append(this.surname).append("\nCF: ").append(this.CF).append("\n");
        return sb.toString();
    }
    
    
    
}
