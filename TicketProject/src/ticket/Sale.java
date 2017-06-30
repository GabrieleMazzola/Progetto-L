
package ticket;

import java.util.Date;


public class Sale {
    
    private String username;
    private long serialCode;
    private String id;
    private Date expiryDate;

    public Sale(Date expiryDate, long serialCode, String username, String id) {
        this.username = username;
        this.serialCode = serialCode;
        this.id = id;
        this.expiryDate = expiryDate;
    }

    public String getUsername() {
        return username;
    }

    public long getSerialCode() {
        return serialCode;
    }

    public String getId() {
        return id;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    
    
    
}
