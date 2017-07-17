
import centralsystem.Stub;
import items.Sale;
import singleton.SerialEncryption;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zubeer
 */
public class TestEncryption {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        long serialCode = Long.valueOf("13");
        String ciao = SerialEncryption.getInstance().encryptSerial("10");
        System.out.println(ciao);
        
        String res1 = SerialEncryption.getInstance().decryptSerial("QVC6E4X6342REPILIRXEECX2IM4U4UDDAQFVVV4LVD65RLMDTAKZT23F3LKVLUMTSE43GPP5YSJWJ3LR67URZ7RXCF7CP3TVVNJTY4A=");
        String res = SerialEncryption.getInstance().decryptSerial("QVC6E4X6342REPILIRXEECX2IM4U4UDDAQFVVV4LVD65RLMDTAKZT23F3LKVLUMTSE43GPP5YSJWJ3LR67URZ7RXCF7CP3TVVNJTY4A=");
        System.out.println(res1);
        System.out.println(res);
        
    }
    
}
