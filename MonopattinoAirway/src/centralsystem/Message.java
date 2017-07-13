package centralsystem;

import java.util.Calendar;

/**
 *
 * @author Zubeer
 */
public class Message {
    private String message;
    private Calendar date;
    
    /**
     *
     * @param message
     * @param date
     */
    public Message(String message, Calendar date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public Calendar getDate() {
        return date;
    }
    
    @Override
    public String toString() {
        return message;
    }
}
