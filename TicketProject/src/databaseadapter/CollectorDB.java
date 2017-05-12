/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseadapter;

/**
 *
 * @author Andrea
 */
public class CollectorDB {
    
    private String name,surname,CF,username,password;

    public CollectorDB(String name, String surname,String username, String CF,String password) {
        this.name = name;
        this.surname = surname;
        this.CF = CF;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername(){
        return this.username;
    }
    
    public boolean setPassword(String oldPsw,String newPsw) {
        if(oldPsw.equals(this.password)){
            this.password = newPsw;
            return true;
        }
        return false;
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

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Name: ").append(this.name);
        sb.append("\nSurname : ").append(this.surname).append("\nCF: ").append(this.CF).append("\n");
        return sb.toString();
    }
}
