package model.economy;

import java.io.Serializable;
import java.text.NumberFormat;

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
        this.formatAmount();
    }
    
    public void addAmount(double amount)
    {
        this.amount += amount;
        this.formatAmount();        
    }

    public double getAmount() {
        return amount;
    }
    
    private void formatAmount()
    {
        this.amount = (Math.round(this.amount*100.0 ))/100.0;
    }
}
