package creator;

import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class TicketMachineFactory {
    private static TicketMachineFactory instance;
    private int cod;
    
    private TicketMachineFactory() {
        cod = 0;
    }
    
    /**
     * Costruisce una nuova TicketMachine. Il codice della nuova TickeMachine è
     * incrementale (codice di quella precedente + 1), mentre l'indirizzo ip
     * viene generato casualmente (da cambiare)
     * @return Una nuova ticket machine
     */
    public TicketMachine createTicketMachine() {
        cod++;
        return new TicketMachine(5000, generateRandomIPAddress());
    }
    
    /**
     * Chiama l'istanza corrente della Factory. Nel caso la factory fosse già
     * istanziata, viene ritornata quell'istanza, mentre se non lo fosse, viene 
     * creata e ritornata una nuova Factory chiamando il costruttore privato
     * @return L'istanza corrente di TicketMachineFactory
     */
    public static synchronized TicketMachineFactory getInstance() {
        if(instance == null) {
            instance = new TicketMachineFactory();
        }
        return instance;
    }
    
    private String generateRandomIPAddress() {
//        StringBuilder str = new StringBuilder();
//        str.append((int) Math.floor(Math.random()*250 + 1)).append(".")
//           .append((int) Math.floor(Math.random()*250 + 1)).append(".")
//           .append((int) Math.floor(Math.random()*250 + 1)).append(".")
//           .append((int) Math.floor(Math.random()*250 + 1)).append(".");
//        return str.toString();
        return "192.168.1.9";
    }
}
