package email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


public class EmailDispatcher {
    
    /**
     * Tramite la libreria Apache Commons Mail, riceve
     * un indirizzo a cui mandare l'emeail, e il testo da inserire,
     * e manda la mail relativa attraverso un server SMTP
     * @param text
     * @param sendTo 
     */
    public void sendEmail(String text,String sendTo){
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.libero.it");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("monopattinoarway@libero.it", "passwordfarlocca"));
            email.setSSLOnConnect(true);
            email.setFrom("monopattinoarway@libero.it");
            email.setSubject("Welcome To MonopattinoAirway!");
            email.setMsg(text);
            email.addTo(sendTo);
            email.send();
        } catch (EmailException ex) {
            System.out.println("Inserted non existing mail. Will not send email");
        }
    }
}
