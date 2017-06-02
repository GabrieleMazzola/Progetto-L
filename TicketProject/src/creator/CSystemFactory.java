package creator;

import centralsystem.CSystem;

/**
 *
 * @author Manuele
 */
public class CSystemFactory {
    /*
    Da qui si possono settare cose come il numero della porta del server, il numero
    delle macchinette collegate... Per farlo tutto ciò che vogliamo si customizzabile
    deve andare nel costruttore del CSystem e fare degli attributi final qui dentro,
    che vengono istanziati nel costruttore
    */
    
    public CSystemFactory() {
        //TODO: istanziare cose come il numero della porta
    }
    
    /**
     * Fornisce l'istanza corrente del sistema centrale
     * @return 
     */
    public CSystem buildNewCentralSystem() {
        return new CSystem();
    }
}
