
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
        
        long serialCode = Long.valueOf("0");
        System.out.println(serialCode);
        Sale sal = Stub.getInstance().getSale("0");
        System.out.println(sal.getSerialCode());
    }
    
}
