package bank;

public class BankAdapter {
   
    private Bank bank;
    
    public BankAdapter() {
        bank = new Bank();
    }
    
    public boolean checkBancomat(long bancomatNumber, int pin){
        return true;
    }
    
    public boolean paymentAttempt(String cardNumber, double amout) {
        return bank.pay(cardNumber, amout);
    }
    
    public boolean checkCreditCard(String creditCardNumber){
        return bank.checkValidity(creditCardNumber);
    }
}
