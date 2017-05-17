/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import centralsystem.CSystem;
import codegeneration.CodeHandler;

/**
 *
 * @author Zubeer
 */
public class TestServerSocket {
    public static void main(String[] args) {
       // CSystem cs = new CSystem();
       CodeHandler code =  CodeHandler.getInstance();
       String ciao;
       double  c=999999999999L;
        for (long  i = 0; i < c; i+=10000000) {
                ciao = code.encoder(i, "A");
                //System.out.println(ciao);
                code.decoder(ciao);
        }
        
    }
}
