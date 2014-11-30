package model.economy;

import java.io.Serializable;

public class Money implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private double amount;
    
    public Money(double startAmount) {
        this.amount = startAmount;
    }
    
    public void removeAmount(double amount)
    {
        if(this.amount - amount >= 0)
        {
            this.amount -= amount;
        }
    }
    
    public void addAmount(double amount)
    {
        this.amount += amount;
    }

    public double getAmount() {
        return amount;
    }
}
