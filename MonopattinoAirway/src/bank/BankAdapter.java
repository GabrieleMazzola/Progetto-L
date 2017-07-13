package bank;

/**
 *
 * @author Zubeer
 */
public class BankAdapter {
   
    private Bank bank;
    
    /**
     *
     */
    public BankAdapter() {
        bank = new Bank();
    }
    
    /**
     *
     * @param bancomatNumber
     * @param pin
     * @return
     */
    public boolean checkBancomat(long bancomatNumber, int pin){
        return true;
    }
    
    /**
     *
     * @param cardNumber
     * @param amout
     * @return
     */
    public boolean paymentAttempt(String cardNumber, double amout) {
        return bank.pay(cardNumber, amout);
    }
    
    /**
     *
     * @param creditCardNumber
     * @return
     */
    public boolean checkCreditCard(String creditCardNumber){
        return bank.checkValidity(creditCardNumber);
    }
}
