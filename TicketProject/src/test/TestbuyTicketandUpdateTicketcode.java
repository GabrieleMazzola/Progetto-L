/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import machines.TicketMachine;

/**
 *
 * @author Simone
 */
public class TestbuyTicketandUpdateTicketcode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        TicketMachine tMachine = new TicketMachine(0, 5000, "127.0.0.1");
        TicketMachine tMachine2 = new TicketMachine(1, 5000,"127.0.0.1");
        
        System.out.println("Vendita Macchinetta1:");//teoricamente 0
        tMachine.insertMoney(2);
        System.out.println("Vendita Macchinetta2:");//teoricamnete dipende dai thread
        tMachine2.insertMoney(2);
                
        /**
         * è un ciclo che serve per assicurarsi che il thread sia concluso con successo
         */
        for(int i=0;i<100000000;i++){}
    }
    
}
