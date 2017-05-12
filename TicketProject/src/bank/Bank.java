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
     * 
     * @param creditCardNumber
     * @return Controlla la validità del numero di carta di credito indicato 
     * secondo il proprio algoritmo di verifica. Ritorna vero se il numero di carta
     * di credito risulta essere valido, falso altrimenti
     */
    public boolean checkValidity(String creditCardNumber) {
        return algorithm.check(creditCardNumber);
    }
}
