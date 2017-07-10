package ticketmachine.handlers;


public class Tank {
    
    private double value;    
    private int quantity;

    public Tank(double value) {
        this.value = value;
        this.quantity = 10;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
   public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
   
   public void subtractQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        return value;
    }    
}
