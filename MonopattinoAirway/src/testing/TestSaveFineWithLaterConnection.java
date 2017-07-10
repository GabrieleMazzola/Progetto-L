package testing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ticketcollector.TicketCollector;


public class TestSaveFineWithLaterConnection {


    public static void main(String[] args) throws IOException {
        TicketCollector tCollector = new TicketCollector("192.168.1.106");

        System.out.println("Stato controllore: "+tCollector.loginCollector("COLLECTOR", "COLLECTOR")+" == TRUE");
        System.out.println("Multe Offline: "+tCollector.getOfflineFinesNumber()+" == 0");
        
        System.out.println("Close Wi-Fi");        
        BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
        String ciao =line.readLine();
        
        tCollector.addFine("cf", 5);
        System.out.println("Multe offline: "+tCollector.getOfflineFinesNumber()+" == 1");        
       
        System.out.println("Open Wi-Fi");
        ciao = line.readLine();
       
        tCollector.addFine("cf2", 10);
        System.out.println("Multe offline: "+tCollector.getOfflineFinesNumber()+" == 0");
        
        tCollector.logOut();
        System.exit(0);
    }
    
}
