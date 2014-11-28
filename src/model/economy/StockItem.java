package model.economy;

import java.io.Serializable;

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
