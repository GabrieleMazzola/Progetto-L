package bank;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Andrea
 * Bank consente il pagamento tramite carta di credito e altre forme non cash
 */
public class Bank {
    private CheckValidityAlgorithm algorithm;
    private Map<String,Double> accounts;
    
    //TODO: istanziare la banca con un possibile algoritmo diverso dal Luhn
    public Bank() {
        algorithm = new LuhnAlgorithm();
        initAccounts();
    }
    
    public boolean pay(String cCardNumber, double toPay) {
        if(accounts.containsKey(cCardNumber)) {
            return accounts.get(cCardNumber) - toPay >= 0;
        }
        else return false;
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
    
    private void initAccounts() {
        accounts = new HashMap();
        accounts.put("0000000000000000", 100d);
        accounts.put("1111111111111111", 0.5);
        accounts.put("2222222222222222", 20.5);
    }
}
