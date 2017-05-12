package bank;

/**
 *
 * @author Manuele
 * CheckValidityAlgorithm viene utilizzata per implementare il patter Bridge
 */
public interface CheckValidityAlgorithm {
    
    public boolean check(String creditCardNumber);
}
