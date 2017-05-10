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
    
    public static boolean check(String ccNumber){
            int sum = 0;
            boolean alternate = false;
            for (int i = ccNumber.length() - 1; i >= 0; i--){
                    int n = Integer.parseInt(ccNumber.substring(i, i + 1));
                    if (alternate){
                            n *= 2;
                            if (n > 9){
                                    n = (n % 10) + 1;
                            }
                    }
                    sum += n;
                    alternate = !alternate;
            }
            return (sum % 10 == 0);
    }
}
