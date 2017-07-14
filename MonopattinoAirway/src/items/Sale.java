package items;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Zubeer
 */
public class Sale {
    
    private String username;
    private Long serialCode;
    private Date sellDate, expiryDate;
    private Product product;
    private String sellerMachineIp;
    
    /**
     *
     * @param saleDate
     * @param serialCode
     * @param username
     * @param product
     * @param sellerMachineIp
     */
    public Sale(Date saleDate, Long serialCode, String username, Product product, String sellerMachineIp) {
        this.username = username;
        this.serialCode = serialCode;
        this.sellDate = saleDate;
        this.product = product;
        this.sellerMachineIp = sellerMachineIp;
        calculateExpiryDate();
    }




    public String getSellerMachineIp() {
        return sellerMachineIp;
    }
    
    public String getUsername() {
        return username;
    }

    public Long getSerialCode() {
        return serialCode;
    }

    public String getType() {
        return product.getType();
    }

    public Date getSaleDate() {
        return sellDate;
    }
    
    public Date getExpiryDate() {
        return expiryDate;
    }

    private void calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(sellDate);
        
        int toAdd = 0;
        switch(product.getType().charAt(0)) {
            case 'T':
            case 'P':
                toAdd = Calendar.MINUTE;
                break;
            case 'S':
            case 'Q':
                toAdd = Calendar.MONTH;
                break;
        }
        cal.add(toAdd, product.getDuration());
        expiryDate = cal.getTime();

    }
    
    public Product getProduct() {
        return product;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("\n");
        
        sb.append("SerialCode: ").append(this.serialCode);
        sb.append("  ,  Username: ").append(this.username);
        sb.append("  ,  IP: ").append(this.sellerMachineIp);
        sb.append("  ,  sellDate: ").append(this.sellDate);
        sb.append("  ,  expiryDate:").append(this.expiryDate);
        sb.append("  ,  PRODUCT: ").append(this.product);
        
        return sb.toString();
    }
}
