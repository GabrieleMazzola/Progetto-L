package singleton;

import java.text.*;
import java.util.Date;
import java.util.*;

public class DateOperations {
    
    private static DateOperations instance;

    private DateOperations(){
    }
    
    public static synchronized DateOperations getInstance() {
        if (instance == null) {
            instance = new DateOperations();
        }
        return instance;
    }
    
    /**
     * Decodifica la data codificata precedentemente
     * @param input: Data già codificata secondo lo standard ISO
     * @return L'oggetto DATE corrispondente alla decodifica
     * @throws java.text.ParseException 
     */
    public Date parse(String input) throws java.text.ParseException {
        if(input != null) {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); //standard ISO
            df.setTimeZone(tz);
            return df.parse(input);
        }
        return null;
    }

    /**
     * codifica una date in una stringa secondo uno standard ISO
     * @param date : Data da codificare prima dell'invio JSON
     * @return la Stringa che rappresenta la data
     */
    public String toString(Date date) {
        if(date != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ITALY);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return dateFormat.format(date);
        }
        return null;
    }
    
}
