/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machines;

/**
 *
 * @author Andrea
 */
public class Tank {
    
    private float value;    
    private int quantity;

    public Tank(float value) {
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

    public float getValue() {
        return value;
    }    
}
