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
    
    public boolean mustRequestCodes() {
        return lowCodesAmount() && !threadActive;
    }
    
    public void startUpdateSerial(){
        threadActive = true;
        stub.requestCodes(numberOfCodes);
    }
    
    public void endUpdateSerial(List<Long> serialNumbers){
        threadActive = false;
        this.serials.addAll(serialNumbers);
    }
    
    public long popSerialNumber() {
        return serials.remove(0);
    }
    
    private boolean lowCodesAmount(){
        return serials.size() <= threshold;
    }
    
    //__________________Metodi per l'inizializazione______________________
    /**
     * Inizializa il vettore che conterrà i codici validi per questa macchinetta.
     * Richiede al CS i primi biglietti e la macchinetta non inizierà a lavorare
     * finche non ha completato la richiesta
    */
    private synchronized void initSerialNumber() {
        try{
            this.startUpdateSerial();
            while(!threadActive){this.wait(1000);} //metodo da sistemare
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    void print() {
        System.out.println("Stampo numeri");
        System.out.println(serials);
    }
}
