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
    
    public String getHour(Date data){
    	Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
    	calendar.setTime(data);   // assigns calendar to given date 
    	return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)); // gets hour in 24h format
    }
    public String getMinute(Date data){
    	Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
    	calendar.setTime(data);   // assigns calendar to given date 
    	return String.valueOf(calendar.get(Calendar.MINUTE)); // gets hour in 24h format
    }
    public String getDay(Date data){
    	Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
    	calendar.setTime(data);   // assigns calendar to given date 
    	return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)); // gets hour in 24h format
    }
    public String getMonth(Date data){
    	Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
    	calendar.setTime(data);   // assigns calendar to given date 
    	return String.valueOf(calendar.get(Calendar.MONTH)); // gets hour in 24h format
    }
    public String getYear(Date data){
    	Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
    	calendar.setTime(data);   // assigns calendar to given date 
    	return String.valueOf(calendar.get(Calendar.YEAR)); // gets hour in 24h format
    }
    
}
