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
   
    private Bank the_bank;
    
    public boolean checkBancomat(long bancomatNumber, int pin){
        return true;
    }
    
    public boolean checkCreditCard(String creditCardNumber){
        return true;
    }
}
