package testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ticketcollector.TicketCollector;


public class TestTicketCollector {

    public static void main(String[] args) throws IOException {
        
        TicketCollector tcollector = new TicketCollector("192.168.1.163");
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        tcollector.loginCollector("COLLECTOR", "COLLECTOR");
        
        
        while(true){
            System.out.println("Controllo se esiste il codice: ");
            String serialcode = buf.readLine();
            Boolean t = tcollector.existsTicket(Long.parseLong(serialcode));
            System.out.println("Validità: "+t);
            
                System.out.println("Fai la multa: ");
                String cf = buf.readLine();
                System.out.println("Di quanto: ");
                String amount = buf.readLine();
                tcollector.addFine(cf, Double.parseDouble(amount));
                System.out.println("Multe offline: "+tcollector.getOfflineFinesNumber());
                
        }
        
    }
    
}
