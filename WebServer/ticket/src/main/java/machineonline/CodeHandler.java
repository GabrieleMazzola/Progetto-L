/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machineonline;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Zubeer
 */
public class CodeHandler {
    char KEY = 'k';
    
    private static final char[] simboli;

    static  {
        StringBuilder sb = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            sb.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            sb.append(ch);
        simboli = sb.toString().toCharArray();
    }
    
    
    private SecureRandom random = new SecureRandom();

    private CodeHandler() {
        
    }
    
    public static CodeHandler getInstance() {
        return codeHandlerHolder.INSTANCE;
    }

   
    
    private static class codeHandlerHolder {

        private static final CodeHandler INSTANCE = new CodeHandler();
    }
    // a = abbonamento; b = biglietto
    
    public String nextSessionId() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 4; i++) {
           sb.append(simboli[(int)((random.nextDouble())*simboli.length)]);
        }
        return sb.toString();
    }
    
    
    public String encoder(long  serialCode, String type) {
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        char[] casuale = nextSessionId().toCharArray();
        
        sb.append(casuale[0]);
        sb.append(casuale[1]);
        
        if(type.equals("A")) sb.append("A");
        else if(type.equals("B")) sb.append("B");
        sb.append(casuale[2]);
        sb.append(serialCode);
        sb.append(casuale[3]);
        byte[] encoding = new byte[sb.length()];
        
        for (int i = 0; i < encoding.length; i++) {
            encoding[i] = (byte)((sb.toString().charAt(i)) ^ KEY);
          //  System.out.println("i: " + i + " cod: " + encoding[i]);
            result.append(String.format("%1$03d", encoding[i]));
            result.append("");
        }
        return result.toString();
    }
    
    
    public String decoder(String inputString){
        StringBuilder sb = new StringBuilder();
        char[] inputData = new char[inputString.length()/3];
        int contatoreDec = 0;
        for (int i = 0; i <= inputString.length()-3; i+=3) {
            
           // System.out.println("i: " + i + " str: " + inputString.substring(i, i+3) + " ");
            inputData[contatoreDec] = (char)( KEY ^ Integer.parseInt(inputString.substring(i, i+3)) );
           // System.out.println( i + "in :" + inputData[contatoreDec]);
            contatoreDec++;
        }
        StringBuilder serialCode = new StringBuilder();
        for (int i = 4; i < inputData.length-1; i++) {
            serialCode.append(inputData[i]);
        }
        System.out.println("type: "+ inputData[2] + " cod: " + serialCode.toString());
        
    return "";
    }
    
}
