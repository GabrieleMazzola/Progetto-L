/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base32;

/**
 *
 * @author Zubeer
 */
public class SerialEncryption {
    
    PrivateKey privateKey;
    PublicKey publicKey;
    Base32 base32;
    private SerialEncryption() {
        System.out.println(getClass().getResource("/keys/private.key"));
        keyFactory("keys/");
        base32  = new Base32();
    }
    
    public static SerialEncryption getInstance() {
        return SerialEncryptionHolder.INSTANCE;
    }
    
    private static class SerialEncryptionHolder {

        private static final SerialEncryption INSTANCE = new SerialEncryption();
    }
    
    
    
    
    public  void keyFactory(String path)  {
        FileInputStream fis = null;
        try {
            // Read Public Key.
            File filePublicKey = new File(getClass().getResource("/keys/public.key").toURI().getPath());
            fis = new FileInputStream(getClass().getResource("/keys/public.key").toURI().getPath());
            byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
            fis.read(encodedPublicKey);
            fis.close();
            // Read Private Key.
            File filePrivateKey = new File(getClass().getResource("/keys/private.key").toURI().getPath());
            fis = new FileInputStream(getClass().getResource("/keys/private.key").toURI().getPath());
            byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
            fis.read(encodedPrivateKey);
            fis.close();

            // Generate KeyPair.
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
            publicKey = keyFactory.generatePublic(publicKeySpec);
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
            privateKey = keyFactory.generatePrivate(privateKeySpec);
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException ex) {
            Logger.getLogger(SerialEncryption.class.getName()).log(Level.SEVERE, null, ex);
      } catch (URISyntaxException ex) {
            Logger.getLogger(SerialEncryption.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(SerialEncryption.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
	}


    public  String decryptSerial(String serialEncrypted) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(cipher.doFinal(base32.decode(serialEncrypted)));
            
        } catch (NoSuchAlgorithmException  | NoSuchPaddingException  | IllegalBlockSizeException  | BadPaddingException | InvalidKeyException ex ) {        
            Logger.getLogger(SerialEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public  String encryptSerial(String serialCode){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            String risultato = base32.encodeToString(cipher.doFinal(serialCode.getBytes())); 
            return risultato;
        } catch (NoSuchAlgorithmException  | NoSuchPaddingException  | IllegalBlockSizeException  | BadPaddingException | InvalidKeyException ex ) {        
            Logger.getLogger(SerialEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
