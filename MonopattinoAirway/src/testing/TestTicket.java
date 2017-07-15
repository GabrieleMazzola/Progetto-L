package testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ticketmachine.TicketMachine;


public class TestTicket {
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        TicketMachine machine = new TicketMachine(1, 5000, "192.168.1.9");
        
        //Dato che la macchinetta deve attendere che il thread iniziale sia concluso mettiamo un buffered reader di controllo
        while(true){
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Inserire il tipo di biglietto da comprare:");
            String type = buffer.readLine();
//            machine.setTicketToSell(type);
//            System.out.println("Inserire monete:");
//            double money = Double.parseDouble(buffer.readLine());
//            machine.insertMoney(money);   
        }    
        
    }
}
