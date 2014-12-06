package model.economy;

import java.io.Serializable;

public class Money implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private double amount;

    public Money(double startAmount) {
        this.amount = startAmount;
    }

    public void removeAmount(double amount) throws ArithmeticException {
        if (this.amount - amount < 0) {
            throw new ArithmeticException("You need "+amount+" but only have "+this.amount+".");
        }
        this.amount -= amount;
        this.formatAmount();
    }

    public void addAmount(double amount) {
        if (amount >= 0) {
            this.amount += amount;
            this.formatAmount();
        }
    }
    
    public void changeAmount(double amount){
        if(amount>=0){
            addAmount(amount);
        }else{
            removeAmount(amount*-1);
        }
    }

    public double getAmount() {
        return amount;
    }

    private void formatAmount() {
        this.amount = (Math.round(this.amount * 100.0)) / 100.0;
    }
}
