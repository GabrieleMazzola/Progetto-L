package bank;
/**
 *
 * @author Andrea
 * Bank consente il pagamento tramite carta di credito e altre forme non cash
 */
public class Bank {
    private CheckValidityAlgorithm algorithm;
    
    //TODO: istanziare la banca con un possibile algoritmo diverso dal Luhn
    public Bank() {
        algorithm = new LuhnAlgorithm();
    }
    
    /**
     * Controlla la validità del numero di carta di credito indicato 
     * secondo il proprio algoritmo di verifica. L'algoritmo di verifica viene
     * specificato al momento della creazione (per il momento viene istanziato
     * sempre un algoritmo di tipo Luhn).
     * @param creditCardNumber
     * @return  Vero se il numero di carta è valido, falso altrimenti
     */
    public boolean checkValidity(String creditCardNumber) {
        return algorithm.check(creditCardNumber);
    }
}
