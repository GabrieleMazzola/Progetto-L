package creator;

import machines.TicketMachine;

/**
 *
 * @author Manuele
 */
public class TicketMachineFactory {
    private static int cod;
    
    public TicketMachineFactory() {
        cod = 0;
    }
    
    /**
     * Costruisce una nuova TicketMachine. Il codice della nuova TickeMachine è
     * incrementale (codice di quella precedente + 1), mentre l'indirizzo ip
     * viene generato casualmente (da cambiare)
     * @return Una nuova ticket machine
     */
    public TicketMachine createTicketMachine(int port, String ip) {
        cod++;
        return new TicketMachine(cod, port, ip);
    }
}
