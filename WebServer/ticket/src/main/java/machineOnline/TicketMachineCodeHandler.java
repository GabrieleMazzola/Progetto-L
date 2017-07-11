package machineOnline;

import java.util.ArrayList;
import java.util.List;

public class TicketMachineCodeHandler {
    private final int numberOfCodes = 10, threshold = 7;
    private List<Long> serials;
    private TicketOnline machine;
    
    public TicketMachineCodeHandler(TicketOnline machine) {
        this.machine = machine;
        this.serials = new ArrayList<>();
        startUpdateSerial();
    }
    
    /**
     * Controlla se il numero di codici seriali è sotto il limite
     * @return Vero se il numero di codici è sotto soglia e non c'è un thread
     * già attivo per richiedere i codici
     */
    public boolean mustRequestCodes() {
        return lowCodesAmount();
    }
    
    /**
     * Inizia il thread per la richiesta dei codici.
     */
    public void startUpdateSerial(){
        machine.requestCodes(numberOfCodes);
    }
    
    /**
     * Aggiunge i codici nella lista a quelli già presenti
     * @param serialNumbers 
     */
    public void endUpdateSerial(List<Long> serialNumbers){
        this.serials.addAll(serialNumbers);
    }
    
    /**
     * Rimuove il primo elemento della lista di codici
     * @return Il primo elemento della lista dei codici
     */
    public long popSerialNumber() {
        return serials.remove(0);
    }
    
    public int getSerialListLenght() {
        return serials.size();
    }
    
    public boolean hasSerials() {
        return serials.size() > 0;
    }
    
    private boolean lowCodesAmount(){
        return serials.size() <= threshold;
    }

	public List<Long> getSerialList() {
		return this.serials;
	}
}
