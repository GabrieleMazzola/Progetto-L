/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import centralsystem.CSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ticketCollector.TicketCollector;




/**
 *
 * @author Simone
 */
public class TestTicketCollector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
            
        TicketCollector provaCollector = new TicketCollector(5000,"127.0.0.1");
        
        provaCollector.loginCollector("ADMIN", "ADMIN");
        boolean a = true;
        while(a){
            a = (new BufferedReader(new InputStreamReader(System.in))).readLine().equals("Start");
        }

        
        
        provaCollector.createFine("Ciao", 1000);
        System.out.println("ciao");
        provaCollector.createFine("Boh", 900);
        System.out.println("ciao2");    
      
        CSystem CS = new CSystem();
        provaCollector.createFine("Box", 1000);
        System.out.println("Ciao3");
    }
    
}
