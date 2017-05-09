/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

/**
 *
 * @author Andrea
 */
public class User {
    
    private String nome;
    
    private String Cognome;

    public User(String nome, String Cognome) {
        this.nome = nome;
        this.Cognome = Cognome;
    }
    
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return Cognome;
    }
    
}
