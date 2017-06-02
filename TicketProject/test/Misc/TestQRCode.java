package Misc;

import com.google.zxing.WriterException;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import machines.QRCodeHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Manuele
 */
public class TestQRCode {
    
    public TestQRCode() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateQRCode() {
        QRCodeHandler qrCode = QRCodeHandler.getInstance();
        try {
            String path = qrCode.buildQRCodeFromString("That's a nice way to test, Watson", "Test");
            File qrCodeImage = new File(path);
            assertTrue(qrCodeImage != null);
        }
        catch(IOException|WriterException exc) {
            assertTrue(false);
        }
    }
}
