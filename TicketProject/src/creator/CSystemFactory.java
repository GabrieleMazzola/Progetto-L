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
    private static CSystemFactory instance;
    private CSystem cSystemInstance;
    
    private CSystemFactory() {
        //TODO: istanziare cose come il numero della porta
    }
    
    /**
     * Fornisce l'istanza corrente del sistema centrale
     * @return 
     */
    public CSystem getCentralSystemInstance() {
        if(cSystemInstance == null)
            cSystemInstance = new CSystem();
        return cSystemInstance;
    }
    //IL CENTRAL SYSTEM DEVE ESSERE UN SINGLETON?
    
    /**
     * Chiama l'istanza corrente della Factory. Nel caso la factory fosse già
     * istanziata, viene ritornata quell'istanza, mentre se non lo fosse, viene 
     * creata e ritornata una nuova Factory chiamando il costruttore privato
     * @return L'istanza corrente di CSystemFactory
     */
    public static synchronized CSystemFactory getInstance() {
        if (instance == null)
            instance = new CSystemFactory();
        return instance;
    }
}
