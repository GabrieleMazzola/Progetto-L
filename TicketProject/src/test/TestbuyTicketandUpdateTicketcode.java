/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import machines.TicketMachine;
import paymentMethods.PaymentMethod;

/**
 *
 * @author Simone
 */
public class TestbuyTicketandUpdateTicketcode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        TicketMachine tMachine = new TicketMachine(5000, "127.0.0.1");
        TicketMachine tMachine2 = new TicketMachine(5000,"127.0.0.1");

        
        tMachine.setPaymentMethod(PaymentMethod.CASH);
        tMachine2.setPaymentMethod(PaymentMethod.CASH);
        System.out.println("Vendita Macchinetta1:");//teoricamente 0
        tMachine.insertMoney(2);
        System.out.println("Vendita Macchinetta2:");//teoricamnete dipende dai thread
        tMachine2.insertMoney(2);
        
        System.out.println("Il numero di biglietti dentro la macchinetta1 dopo vendita:"+tMachine.getSerialNumberSize());  //9 se thread concluso:19
        System.out.println("Il numero di biglietti dentro la macchinetta2 dopo vendita:"+tMachine2.getSerialNumberSize()); //9 se thread concluso:19
        
        /**
         * è un ciclo che serve per assicurarsi che il thread sia concluso con successo
         */
        for(int i=0;i<100000000;i++){}
        
        System.out.println("Il numero di biglietti dentro la macchinetta1:"+tMachine.getSerialNumberSize());  //19 o 9 se thread lento
        System.out.println("Il numero di biglietti dentro la macchinetta2:"+tMachine2.getSerialNumberSize()); //19 0 9 se thread lento
        
        System.out.println("Macchinetta1:"); //dipende dai thread teoricamente stampa 19 stringhe
        tMachine.printSerialNumberSize();
        System.out.println("Macchinetta2");  //dipende dai thread teoricamnete stmapa 19 stringhe
        tMachine2.printSerialNumberSize();
    }
    
}
