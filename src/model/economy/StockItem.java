package model.economy;

import java.io.Serializable;

/**
 * This hold an StockItem, this means it hold a ware and 
 * the amount of the ware which is in the stock. The ware itself
 * doesn't know how many there is of his kind in the stock.
 */
public class StockItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Ware ware;
    private Integer amount;
    
    public StockItem(Ware ware, Integer amount) {
        this.ware = ware;
        this.amount = amount;
    }

    public Ware getWare() {
        return ware;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setWare(Ware ware) {
        this.ware = ware;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
