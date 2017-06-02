package machines;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuele
 */
public class TicketMachineCodeHandler {
    private final int numberOfCodes = 30, threshold = 20;
    private List<Long> serials;
    private StubMachine stub;
    //Per controllare se il thread è attivo
    private boolean threadActive;
    
    public TicketMachineCodeHandler(StubMachine stub) {
        this.stub = stub;
        this.serials = new ArrayList();
        threadActive = false;
        initSerialNumber();
    }
    
    public void setActive(boolean threadActive) {
        this.threadActive = threadActive;
    }
    
    /**
     * Controlla se il numero di codici seriali è sotto il limite
     * @return Vero se il numero di codici è sotto soglia e non c'è un thread
     * già attivo per richiedere i codici
     */
    public boolean mustRequestCodes() {
        return lowCodesAmount() && !threadActive;
    }
    
    /**
     * Inizia il thread per la richiesta dei codici.
     */
    public void startUpdateSerial(){
        threadActive = true;
        stub.requestCodes(numberOfCodes);
    }
    
    /**
     * Aggiunge i codici nella lista a quelli già presenti
     * @param serialNumbers 
     */
    public void endUpdateSerial(List<Long> serialNumbers){
        threadActive = false;
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
    
    private boolean lowCodesAmount(){
        return serials.size() <= threshold;
    }
    
    //__________________Metodi per l'inizializazione______________________
    /**
     * Inizializa il vettore che conterrà i codici validi per questa macchinetta.
     * Richiede al CS i primi biglietti
    */
    private synchronized void initSerialNumber() {
        try{
            this.startUpdateSerial();
            while(!threadActive){this.wait(1000);} //metodo da sistemare
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
