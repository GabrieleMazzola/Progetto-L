/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author Andrea
 */
public class CreditCardAlgorithm {
    private static int[] codes;
    
    public static boolean isValidNumber(String code) {
        int[] codes = getCodeFromString(code);
    }
    
    private static int getDoubledEvenDigitsSum() {
        int[] cifre = doubleEvenDigits();
        int tot = 0;
        
        for(int num : cifre) tot += num;
        
        return tot;
    }
    
    private static int getOddDigitsSum() {
        
    }
    
    private static int[] doubleEvenDigits() {
        int[] cifre = new int[codes.length/2];
        
        for(int i = 0; i < codes.length; i += 2) {
            cifre[i] = codes[i]*2;
            if(cifre[i] >= 10) cifre[i] -= 9; //Sommiamo le cifre pari moltiplicate per due (se la cifra i è maggiore di 
                                              //10 vanno sommate le singole cifre)
        }
        
        return cifre;
    }
    
    private static int[] getCodeFromString(String code) {
        String[] buffer = code.split(" ");
        int[] codes = new int[16];
        return codes;
    }
}
