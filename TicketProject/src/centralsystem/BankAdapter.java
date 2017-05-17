/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralsystem;

import bank.Bank;

/**
 *
 * @author Andrea
 */
public class BankAdapter {
   
    private Bank bank;
    
    public BankAdapter() {
        bank = new Bank();
    }
    
    public boolean checkBancomat(long bancomatNumber, int pin){
        return true;
    }
    
    public boolean paymentAttempt(String creditCardNumber, double amout) {
        return bank.pay(creditCardNumber, amout);
    }
    
    public boolean checkCreditCard(String creditCardNumber){
        return bank.checkValidity(creditCardNumber);
    }
}
