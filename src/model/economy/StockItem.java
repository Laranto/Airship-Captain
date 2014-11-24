package model.economy;

public class StockItem {
    private Ware ware;
    private Integer amount;
    private Float price;
    
    public StockItem(Ware ware, Integer amount, Float price) {
        this.ware = ware;
        this.amount = amount;
        this.price = price;
    }

    public Ware getWare() {
        return ware;
    }

    public Integer getAmount() {
        return amount;
    }

    public Float getPrice(){
        return price;
    }
    
    public void setWare(Ware ware) {
        this.ware = ware;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }
}
