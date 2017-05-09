package databaseadapter;


public class UserDB {
    private String name,surname,CF,mail,password;

    public UserDB(String name, String surname, String CF,String password) {
        this.name = name;
        this.surname = surname;
        this.CF = CF;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String oldPsw,String newPsw) {
        if(oldPsw.equals(this.password)){
            this.password = newPsw;
            return true;
        }
        return false;
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
