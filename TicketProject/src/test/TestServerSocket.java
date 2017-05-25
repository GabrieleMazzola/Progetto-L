/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import centralsystem.CSystem;
import centralsystem.LogCS;
import codegeneration.CodeHandler;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Zubeer
 */
public class TestServerSocket {
    public static void main(String[] args) {
//        CSystem cs = new CSystem();
       CodeHandler code =  CodeHandler.getInstance();
       String ciao;
       double  c=999999999999L;
        for (long  i = 0; i < c; i+=10000000) {
                ciao = code.encoder(i, "A");
                //System.out.println(ciao);
         //       code.decoder(ciao);
       // }
       
       
        
    }
}
