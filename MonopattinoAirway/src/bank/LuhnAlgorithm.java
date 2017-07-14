package bank;

/**
 *
 * @author Zubeer
 */
public class LuhnAlgorithm implements CheckValidityAlgorithm{
    
    
    /**
     * Applica l'algoritmo Luhn a ccNumber. Per farlo vengono moltiplicate per 
     * 2 le cifre di posto pari (nel caso si ottenga un numero maggiore di 10 si
     * fa la somma delle due cifre) per poi sommare le cifre così ottenute
     * alle cifre di posto dispari. Se il numero così ottenuto è divisibile per
     * 10 ccNumber è un numero di carta di credito valida.
     * Perché il metodo sia applicabile, ccNumber deve essere una stringa di
     * 16 valori numerici senza spazi.
     * @param ccNumber
     * @return 
     */
    @Override
    public boolean check(String ccNumber){
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
