package model.economy;

import java.io.Serializable;


/**
 * Manages the money amount of the captiain
 */
public class Money implements Serializable {

    private static final long serialVersionUID = 1L;
    private double amount;

    /**
     * @param double the startAmount of the player
     */
    public Money(double startAmount) {
        this.amount = startAmount;
    }

    /**
     * removes an amount by the given number
     * @param double the amount which should be removed
     * @throws ArithmeticException when the amount which should be removed is higher
     * than the available amount
     */
    public void removeAmount(double amount) throws ArithmeticException {
        if (this.amount - amount < 0) {
            throw new ArithmeticException("You need "+amount+" but only have "+this.amount+".");
        }
        this.amount -= amount;
        this.formatAmount();
    }

    /**
     * adds and amount to the current money
     * @param double the amount which should be added
     */
    public void addAmount(double amount) {
        if (amount >= 0) {
            this.amount += amount;
            this.formatAmount();
        }
    }
    
    /**
     * changes the current money by the given amount, this could be
     * either a positive or a negative number
     * @param double the amount which should be changed
     */
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
