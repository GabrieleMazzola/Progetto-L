package testing;

import centralsystem.CSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ticketmachine.TicketMachine;


public class TestLogIn {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        TicketMachine machine = new TicketMachine(1, 5000, "localhost");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        //Lettura dei dati
        System.out.println("Username:");
        String username = buffer.readLine();
        System.out.println("PWD:");
        String password = buffer.readLine();
        //LogIn
        machine.verifyLogin(username, password);
        //Vendita biglietto
        System.out.println("Inserire il tipo di biglietto da comprare:");
        String type = buffer.readLine();
//        machine.setTicketToSell(type);
//        System.out.println("Inserire monete:");
//        double money = Double.parseDouble(buffer.readLine());
//        machine.insertMoney(money);  
//        System.out.println("Usare debug per controllare se ha messo il ticket dentro il database");
    }
    
}
