package bank;

public class LuhnAlgorithm implements CheckValidityAlgorithm{
    /**
     * 
     * @param ccNumber
     * @return Applica l'algoritmo Luhn a ccNumber. Ritorna vero se la somma delle
     * cifre di posto pari (moltiplicate per 2) e quelle di posto dispari è divisibile
     * per 10
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
