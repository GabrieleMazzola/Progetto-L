package test;

import com.google.zxing.WriterException;
import java.io.IOException;
import machines.QRCodeHandler;

/**
 *
 * @author Manuele
 */
public class TestQRCode {

    /**
     * @param args the command line arguments
     * @throws com.google.zxing.WriterException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws WriterException, IOException{
        QRCodeHandler qrCode = QRCodeHandler.getInstance();
        qrCode.buildQRCodeFromString("This is how I roll, bitches");
    }
}
