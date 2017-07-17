/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.progettol.webserver;

import centralsystem.Stub;
import items.Sale;
import singleton.SerialEncryption;

/**
 *
 * @author Zubeer
 */
public class TicketChecker {
    String value;
    public TicketChecker(String value) {
        this.value = value;
        
    }
    
    public  Sale getTicket(){
        Sale sale;
        try{
            String serialCode = SerialEncryption.getInstance().decryptSerial(value);
            sale = Stub.getInstance().getSale(serialCode);
            return sale;
        }catch(Exception e){
            System.err.println("Errore nella verifica della sale, valore inserita: " + value);
        }
        return null;
    } 
    
    
}
